package com.xg.east.hibot_b_library.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

import com.faceplusplus.api.FaceDetecter;
import com.xg.east.hibot_b_library.FaceCallBack;
import com.xg.east.hibot_b_library.FaceEntity;
import com.xg.east.hibot_b_library.FaceRemote;
import com.xg.east.hibot_b_library.R;
import com.xg.east.hibot_b_library.face_utils.FaceDiscernUtil;
import com.xg.east.hibot_b_library.face_utils.FloatWindowUtils;
import com.xg.east.hibot_b_library.face_utils.MainPresenter2;
import com.xg.east.hibot_b_library.service.usbSendType.ClassicType;
import com.xg.east.hibot_b_library.utils.PictureUtil;
import com.xg.east.hibot_b_library.utils.ScreenUtils;
import com.xg.east.hibot_b_library.utils.ShowLog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FaceService extends Service implements SurfaceHolder.Callback, Camera.PreviewCallback {
    private final String TAG = this.getClass().getSimpleName();
    private SurfaceView surfaceView_preview;
    SurfaceHolder surfaceHolder;
    Camera camera;
    Handler handler;
    SurfaceHolder holder;
    private int width;
    private int height;
    int BACK_CAMERA = 0;
    int FRONT_CAMERA = 1;
    int cameraState = FRONT_CAMERA;//1为前置摄像头，0为后置摄像头
    byte[] tmp;
    Bitmap bmp;
    boolean previewing = false;//是否正在检测
    public FaceDetecter faceDetecter = null;
    public int widthDistance = 180, heightDistance = 90;//头部转的度数和平板上移动的距离的关系
    public FaceDiscernUtil mFaceDiscernUtil;/*人脸识别的工具类*/
    private int screenWidth = 0, screenHeight = 0;/*屏幕的宽高*/
    private boolean canUse = false;//摄像头是否可用
    private MainPresenter2 mPresenter2;
    public boolean isCameraOpen = false;//是否在打开摄像头
    public boolean faceFollowEnable = false;//是否允许人脸跟踪
    public FaceCallBack mFaceCallBack;
    private ExecutorService mSingleExeSer;
    @Override
    public void onCreate() {
        super.onCreate();
        mSingleExeSer= Executors.newSingleThreadExecutor();
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("Foreground Service Start");
        builder.setContentTitle("Face");
        builder.setAutoCancel(true);
        builder.setContentText("人脸服务正在运行");
//        PendingIntent contentIntent=PendingIntent.getActivity(this,0,new Intent(this, IndexActivity.class),0);
        Intent intent=new Intent();
        intent.setAction("BtopenA");
        PendingIntent contentIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        builder.setContentIntent(contentIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Notification notification = builder.build();
        startForeground(3, notification);

        mPresenter2 = new MainPresenter2(this);
        screenWidth = ScreenUtils.getScreenWidth(this);
        screenHeight = ScreenUtils.getScreenHeight(this);
        handler = new Handler(/*handlerThread.getLooper()*/Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 1) {
                    try {
                        initCamera();
                        ShowLog.d(TAG, "openCameraAndFaceFollow");
                        faceFollowEnable = true;
                        isCameraOpen = true;
                        if (mFaceCallBack != null) {
                            mFaceCallBack.onCameraOpen(isCameraOpen);
                        }
                        FloatWindowUtils.clear();
                        surfaceView_preview = new SurfaceView(FaceService.this);
                        FloatWindowUtils.addView(FaceService.this.getApplicationContext(), surfaceView_preview, Gravity.TOP | Gravity.RIGHT, false);
                        surfaceHolder = surfaceView_preview.getHolder();
                        surfaceHolder.addCallback(FaceService.this);
                        surfaceView_preview.setKeepScreenOn(true);
//                        initCamera();
                    } catch (Exception e) {
                        faceFollowEnable = true;
                        isCameraOpen = false;
                        if (mFaceCallBack != null) {
                            try {
                                mFaceCallBack.onCameraOpen(isCameraOpen);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                        ShowLog.d(TAG, e.getMessage());
                        Toast.makeText(FaceService.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else if (msg.what == 2) {
                    String msgContent = (String) msg.obj;
                    Toast.makeText(FaceService.this, msgContent, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        surfaceView_preview = new SurfaceView(this);
        surfaceHolder = surfaceView_preview.getHolder();
        surfaceHolder.addCallback(this);
        surfaceView_preview.setKeepScreenOn(true);
        FloatWindowUtils.addView(getApplicationContext(), surfaceView_preview, Gravity.TOP | Gravity.RIGHT, false);
        mFaceDiscernUtil = new FaceDiscernUtil(this);
        mFaceDiscernUtil.setDiscernResult(new FaceDiscernUtil.DiscernResult() {
            @Override
            public void dealDiscernResult(String result,double score/*, DetectWithAttribute.FacesBean.AttributesBean attributesBean*/) {
//                FaceEntity faceEntity = new FaceEntity(attributesBean.getAge(), attributesBean.getAttractive(), attributesBean.getSmile()
//                        , attributesBean.getGender(), attributesBean.getMask(), attributesBean.getEye_open(), attributesBean.getMouth_open()
//                        , attributesBean.getBeard(), attributesBean.getEyeglass(), attributesBean.getSunglass());
//                mOnFaceListener.dealDiscernResult(result,attributesBean);
                if (mFaceCallBack != null) {
                    try {
                        mFaceCallBack.dealDiscernResult(result, score);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void incognizant() {
//                mOnFaceListener.incognizant();
                if (mFaceCallBack != null) {
                    try {
                        mFaceCallBack.incognizant();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //初始化检测器
        faceDetecter = new FaceDetecter();
        if (!faceDetecter.init(getApplicationContext(), "fb198f15357a0bb25a00dbe71a53c721")) {
            Log.d("检测器初始化", "有错误");
        }
    }

    public FaceService() {
    }

    FaceRemote.Stub mFaceRemote = new FaceRemote.Stub() {
        @Override
        public void openCameraAndFaceFollow() throws RemoteException {
            ShowLog.d(TAG, "openCameraAndFaceFollow");
            if(!isCameraOpen){
                handler.sendEmptyMessage(1);
            }else{
                Toast.makeText(FaceService.this, "摄像头已经打开了的", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void restartFaceFollow() throws RemoteException {
            if (!faceFollowEnable)
                faceFollowEnable = true;
        }

        @Override
        public void stopFaceFollow() throws RemoteException {
            faceFollowEnable = false;
        }

        @Override
        public void closeFaceFollowAndCamera() throws RemoteException {
            closeCamera();
            FloatWindowUtils.clear();
            isCameraOpen = false;
        }

        @Override
        public void startFaceDiscern() throws RemoteException {
            changePreSize(800,600);
            mFaceDiscernUtil.startFaceDiscern();
        }

        @Override
        public void stopFaceDiscern() throws RemoteException {
            changePreSize(320,240);
            mFaceDiscernUtil.stopFaceDiscern();
        }

        @Override
        public void runWithFaceFollow() throws RemoteException {
            if (camera != null && isCameraOpen && faceFollowEnable)
                mPresenter2.IsfaceFollowWithBody = true;
            else {
                Message msg = new Message();
                msg.what = 2;
                msg.obj = "请开启人脸跟踪";
                handler.sendMessage(msg);
            }
        }

        @Override
        public void release() throws RemoteException {
            closeCamera();
            FloatWindowUtils.clear();
            isCameraOpen = false;
            if (faceDetecter != null) {
                faceDetecter.release(getApplicationContext());
            }
//            closeCamera();
        }

        @Override
        public void registerCallBack(FaceCallBack cb) throws RemoteException {
            mFaceCallBack = cb;
        }

        @Override
        public void unregisterCallBack(FaceCallBack cb) throws RemoteException {
            mFaceCallBack = null;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mFaceRemote;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        closeCamera();
        return super.onUnbind(intent);
    }

    @Override
    public void onPreviewFrame(final byte[] data, final Camera camera) {
        ShowLog.d("test-onPreviewFrame", "运行中1"+"width："+camera.getParameters().getPreviewSize().width+"---htight:"+camera.getParameters().getPreviewSize().height);
//        mOnFaceListener.onPreviewFrame(data,camera);
        if (mFaceCallBack != null) {
            try {
                mFaceCallBack.onPreviewFrame(new FaceEntity(data));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        handler.post(new Runnable() {
            FaceDetecter.Face[] faceinfo = null;

            @Override
            public void run() {
                if (faceFollowEnable) {
                    ShowLog.d("Timer10detecTtest-handler1", "运行中2");
                    if (cameraState == FRONT_CAMERA) {
                    } else {
                        if (camera != null) {
                            Bitmap tem2 = yuv2bitmap(data, camera);
                            if (tem2 != null && !tem2.isRecycled()) {
                                Bitmap comp = PictureUtil.comp(tem2, 120f, 160f);
                                ShowLog.d("Timer101", "压缩完毕");
                                if (comp != null) {
                                    faceinfo = faceDetecter.findFaces(comp);
                                }
                            }
                            if (tem2 != null && !tem2.isRecycled()) {
                                tem2.recycle();
                                tem2 = null;
                            }
                        }
                        ShowLog.d("Timer101detecT-hand", "后置摄像头检测人脸中");
                    }
                    ShowLog.v("Timer102onpreview", "UI运行");
                    if (faceinfo == null) {
                        if (mFaceCallBack != null) {
                            try {
                                mFaceCallBack.onFindFace(false);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
//                    mOnFaceListener.onFindFace(false);
                    } else {
                        mFaceDiscernUtil.dealFaceAtOnPreview(data, camera, FaceService.this.getApplicationContext(), cameraState);
                        if (mFaceCallBack != null) {
                            try {
                                mFaceCallBack.onFindFace(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
//                        mOnFaceListener.onFindFace(true);
                    }
                    mPresenter2.setFaceInfo(faceinfo);
                    int left = 0;
                    int right = screenWidth;
                    int top = 0;
                    int bottom = screenHeight;
                    ShowLog.v("Timer103", "left--right");
                    mPresenter2.startFaceDetect(left, right, top, bottom, widthDistance, heightDistance);/*人脸跟踪的代码实现*/
                }
            }
        });
        ShowLog.v("Timer104", "sdsadsa");
//        mFaceDiscernUtil.dealFaceAtOnPreview(data, camera, this.getApplicationContext(), cameraState);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (previewing) {
            camera.stopPreview();
            return;
        }
        if (camera != null) {
            this.holder = holder;
            //       设置摄像头的预览参数
            try {
                camera.setPreviewDisplay(holder);
                Camera.Parameters mParameters = camera.getParameters();
                mParameters.setPreviewSize(320, 240);
                camera.setParameters(mParameters);
                camera.setPreviewCallback(this);
                camera.startPreview();
                previewing = true;
                Log.v("surfaceChanged", "运行中");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.v("camera", "Camera is null");
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    /**
     * 把yuv转换成bitmap
     *
     * @param mData
     * @param myCamera
     * @return
     */
    public Bitmap yuv2bitmap(byte[] mData, Camera myCamera) {
        try {
            if (canUse) {
                Camera.Size size = myCamera.getParameters().getPreviewSize(); //获取预览大小
                final int w = size.width;  //宽度
                final int h = size.height;
                //            final int w = 640;  //宽度
                //            final int h = 480;
                ShowLog.d("size", w + ":" + h + "");
                final YuvImage image = new YuvImage(mData, ImageFormat.NV21, w, h, null);
                ByteArrayOutputStream os = new ByteArrayOutputStream(mData.length);
                if (!image.compressToJpeg(new Rect(0, 0, w, h), 100, os)) {
                    return null;
                }
                tmp = os.toByteArray();
                bmp = BitmapFactory.decodeByteArray(tmp, 0, tmp.length);
                tmp = null;
                return bmp;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 初始化摄像头
     */
    private synchronized void initCamera() {
        //       获取相机对象，设置摄像头参数
//        camera = Camera.open(cameraState);
        ShowLog.d(TAG, "initCamera");
        cameraState = BACK_CAMERA;

        camera = Camera.open(cameraState);
        canUse = true;
        ShowLog.v("cameraNub", Camera.getNumberOfCameras() + "");
        Camera.getNumberOfCameras();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        ShowLog.v("display", display.getWidth() + "--" + display.getHeight());
        Camera.Parameters pars = camera.getParameters();
        List<Camera.Size> supportedPreviewSizes = pars.getSupportedPreviewSizes();
        for (int ii = 0; ii < supportedPreviewSizes.size(); ii++) {
            ShowLog.v("size", supportedPreviewSizes.get(ii).width + "宽" + supportedPreviewSizes.get(ii).height + "高");
        }
//实际测试如果预览图像的分辨率太大，识别速度大大降低，所以手动设置了下
        width = 320;
        height = 240;
//        width = 640;
//        height = 480;
        pars.setPreviewSize(width, height);
//        camera.setParameters(pars);
//        Camera.Size size = camera.getParameters().getPreviewSize();
//        width = size.width;
//        height = size.height;
//        camera.getParameters().setPreviewSize(320,240);
//        ShowLog.v("Size",width+"--"+height+"--"+camera.getParameters().getPreviewSize().width);

        Camera.Size size = camera.getParameters().getPreviewSize();
        width = size.width;
        height = size.height;
        Log.d("Size", width + "--" + height + "--" + camera.getParameters().getPreviewSize().width);
    }

    /**
     *  修改预览大小
     *
     * @param width
     * @param height
     */
    public void changePreSize(final int width, final int height){
//        mSingleExeSer.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    if(previewing){
//                        camera.stopPreview();
//                    }
//                    Log.d("changePreSize", "运行中");
//                    //       设置摄像头的预览参数
//                    camera.setPreviewDisplay(holder);
//                    Camera.Parameters mParameters = camera.getParameters();
//                    mParameters.setPreviewSize(width, height);
//                    camera.setParameters(mParameters);
//                    camera.setPreviewCallback(FaceService.this);
//                    camera.startPreview();
//                    previewing = true;
//                } catch (Exception e) {
//                    e.printStackTrace();
////            Log.d(TAG+"surfaceChanged",e.getMessage());
//                }
//            }
//        });
    }


    /**
     * 关闭摄像头
     */
    private synchronized void closeCamera() {
        try {
            if (camera != null) {
                Log.d("closeCamera", "1");
                canUse = false;
                camera.setPreviewCallback(null);
                Log.d("closeCamera", "2");
                camera.stopPreview();
                Log.d("closeCamera", "3");
                camera.release();
                Log.d("closeCamera", "4");
                camera = null;
                Log.d("closeCamera", "5");
                previewing = false;
                isCameraOpen=false;
                if (mFaceCallBack != null) {
                    mFaceCallBack.onCameraOpen(isCameraOpen);
                }
            }
        } catch (Exception e) {
            Log.e("closeCameraError", ((e!=null&&e.getMessage()!=null)?e.getMessage():""));
            e.printStackTrace();
        }
    }

    public void sendData(byte[]... listBytes) {
        ArrayList<byte[]> list = new ArrayList<byte[]>();
        for (int i = 0; i < listBytes.length; i++) {
            byte[] bytes = listBytes[i];
            list.add(bytes);
        }
        byte[] by = ClassicType.endOrder();
        list.add(by);
        int size = 0;
        for (int i = 0; i < list.size(); i++) {
            size += list.get(i).length;
        }
        byte[] sorce = new byte[size];
        for (int i = 0; i < list.size(); i++) {
            int index = 0;
            for (int k = 0; k < i; k++) {
                index += list.get(k).length;
            }
            byte[] bytes = list.get(i);
            System.arraycopy(bytes, 0, sorce, index, bytes.length);
        }
        if (mFaceCallBack != null) {
            try {
                mFaceCallBack.sendData(new FaceEntity(sorce));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
