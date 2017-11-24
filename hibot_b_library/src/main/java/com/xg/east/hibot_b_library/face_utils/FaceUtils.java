package com.xg.east.hibot_b_library.face_utils;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.baidu.aip.face.AipFace;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xg.east.hibot_b_library.FaceCallBack;
import com.xg.east.hibot_b_library.FaceEntity;
import com.xg.east.hibot_b_library.FaceRemote;
import com.xg.east.hibot_b_library.SerialSend;
import com.xg.east.hibot_b_library.bean.WW;
import com.xg.east.hibot_b_library.entity.DetectBaiduEnt;
import com.xg.east.hibot_b_library.service.FaceService;
import com.xg.east.hibot_b_library.service.utils.ServiceUtils;
import com.xg.east.hibot_b_library.utils.FileUtils;
import com.xg.east.hibot_b_library.utils.PictureUtil;
import com.xg.east.hibot_b_library.utils.ShowLog;
import com.xg.east.hibot_b_library.utils.UsefulUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static android.content.Context.BIND_AUTO_CREATE;

//import android.hardware.Camera;

/**
 * 项目名称：Test
 * 创建人：East
 * 创建时间：2017/4/7 15:18
 * 邮箱：EastRiseWM@163.com
 */

public class FaceUtils {
    private  final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private static FaceUtils mFaceUtils;
    private boolean isBind = false;
    private ProgressDialog mProgressDialog;
    private List<WW.FacesEntity> faces;
    private boolean isFindFace = false;
    private String faceId;
    private String personId;
    public OnFaceListener mOnFaceListener;//人脸信息监听器
    private FaceRemote mFaceRemote;
    public boolean inited = false;//是否已经调用过init方法
//    private Handler mHandler = new Handler();
    private Handler mMainHandler;
    public boolean isCameraOpen = false;//是否在人脸跟踪
    boolean isAppRun = false;//是否还在运行app
    public static final String APP_ID = "10166337";
    public static final String API_KEY = "dScY3WeugQdWNbkbgV6NdpGf";
    public static final String SECRET_KEY = "F4fBr2wU8ALRKcFm3TzHFUhzrdqIy2VG";
    public AipFace mClient;
    private ExecutorService mSingleThrExe;
    private String mFileName;// 把用户传递过来的人脸图片保存到本地的地址
    /**
     * 图片文件放到服务器上的存储地址
     */
    public static final String UPLOAD_HELE_FILE = "file/upload/uploadAPP";
    /**
     * blackbox_后台域名
     */
    public static String BB_DOMAIN_UPFILE = "http://bbox.blackboxes.cn/";
    private FaceCallBack.Stub mFaceCallBack = new FaceCallBack.Stub() {
        @Override
        public void sendData(FaceEntity fe) throws RemoteException {
            SerialSend.sendAllBytesToSerial(fe.getData());
        }

        @Override
        public void onPreviewFrame(FaceEntity fe) throws RemoteException {
            mOnFaceListener.onPreviewFrame(fe.getData());
        }

        @Override
        public void onCameraOpen(boolean cameraOpen) throws RemoteException {
            isCameraOpen=cameraOpen;
        }


        @Override
        public void onFindFace(boolean findFace) throws RemoteException {
            mOnFaceListener.onFindFace(findFace);
        }

        @Override
        public void onFaceFllowWithMoving(int state) throws RemoteException {
            mOnFaceListener.onFaceFllowWithMoving(state);
        }

        @Override
        public void dealDiscernFail(String error) throws RemoteException {
            ShowLog.d(TAG+"dealDiscernFail","error："+error);
            mOnFaceListener.dealDiscernFail(error);
        }

        @Override
        public void dealDiscernResult(String result,double score/*, FaceEntity fe*/) throws RemoteException {
            ShowLog.d(TAG+"dealDiscernResult","result："+result+"|score|："+score);
//            DetectWithAttribute.FacesBean.AttributesBean attributesBean = new DetectWithAttribute.FacesBean.AttributesBean();
//            attributesBean.setAge(fe.getAge());
//            attributesBean.setAttractive(fe.getAttractive());
//            attributesBean.setBeard(fe.getBeard());
//            attributesBean.setEye_open(fe.getEye_open());
//            attributesBean.setEyeglass(fe.getEyeglass());
//            attributesBean.setGender(fe.getGender());
//            attributesBean.setMask(fe.getMask());
//            attributesBean.setMouth_open(fe.getMouth_open());
//            attributesBean.setSmile(fe.getSmile());
//            attributesBean.setSunglass(fe.getSunglass());
            mOnFaceListener.dealDiscernResult(result,score/*, attributesBean*/);
        }

        @Override
        public void incognizant() throws RemoteException {
            ShowLog.d(TAG+"incognizant","incognizant被调用");
            mOnFaceListener.incognizant();
        }
    };

    private FaceUtils() {
        mSingleThrExe= Executors.newSingleThreadExecutor();
        // 初始化一个FaceClient
        mClient = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        mClient.setConnectionTimeoutInMillis(2000);
        mClient.setSocketTimeoutInMillis(60000);
    }

    public static FaceUtils getInstance() {
        if (mFaceUtils == null) {
            mFaceUtils = new FaceUtils();
        }
        return mFaceUtils;
    }

    public static boolean hasInstance() {
        if (mFaceUtils == null)
            return false;
        else
            return true;
    }

    /**
     * 绑定服务，初始化数据
     */
    public void initData(Application application) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //全局的读取超时时间
        builder.readTimeout(30000, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(30000, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(30000, TimeUnit.MILLISECONDS);
        //超时后是否重试
        builder.retryOnConnectionFailure(false);
        OkGo.getInstance().init(application)
                .setOkHttpClient(builder.build());
        mMainHandler=new Handler(application.getMainLooper());
        isAppRun = true;
        //判断服务服务是否已经存在,并杀掉
        ServiceUtils.killRunningService(application, "com.xg.east.hibot_b_library.service.FaceService");
        mContext = application;
        final Intent intent = new Intent(application, FaceService.class);
        Log.d("initData", "bindBefore");
        application.bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
        //服务保活
        mMainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isAppRun) {
                    if (!ServiceUtils.isServiceRunning(mContext, "com.xg.east.hibot_b_library.service.FaceService")) {
                        mContext.bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                        mMainHandler.postDelayed(this, 2000);
                    } else {
                        mMainHandler.postDelayed(this, 1000);
                    }
                }
            }
        }, 2000);
    }


    /**
     * 初始化数据
     *
     * @param onFaceListener 人脸监听
     */
    public void initData(Application application, OnFaceListener onFaceListener) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //全局的读取超时时间
        builder.readTimeout(30000, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(30000, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(30000, TimeUnit.MILLISECONDS);
        //超时后是否重试
        builder.retryOnConnectionFailure(false);
        OkGo.getInstance().init(application)
                .setOkHttpClient(builder.build());
        mMainHandler=new Handler(application.getMainLooper());
        isAppRun = true;
        //判断服务服务是否已经存在,并杀掉
        ServiceUtils.killRunningService(application, "com.xg.east.hibot_b_library.service.FaceService");
        mContext = application;
        mOnFaceListener = onFaceListener;
        final Intent intent = new Intent(application, FaceService.class);
        application.bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
        //服务保活
        mMainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isAppRun) {
                    if (!ServiceUtils.isServiceRunning(mContext, "com.xg.east.hibot_b_library.service.FaceService")) {
                        mContext.bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                        mMainHandler.postDelayed(this, 2000);
                    } else {
                        mMainHandler.postDelayed(this, 1000);
                    }
                }
            }
        }, 2000);
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mFaceRemote = FaceRemote.Stub.asInterface(service);
            Log.d("initData", "bindSucess");
            isBind = true;
            inited = true;
            try {
                mFaceRemote.registerCallBack(mFaceCallBack);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
        }
    };


    /**
     * 设置人脸监听
     *
     * @param onFaceListener
     */
    public void setOnFaceListener(OnFaceListener onFaceListener) {
        mOnFaceListener = onFaceListener;
    }


    /**
     * 打开摄像头并开启人脸跟踪
     */
    public void openCameraAndFaceFollow() {
        if (inited) {
            try {
                mFaceRemote.openCameraAndFaceFollow();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mContext, "请先调用 FaceUtils.initData 方法", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 重新开启人脸跟踪
     */
    public void restartFaceFollow() {
        if (inited) {
            try {
                mFaceRemote.restartFaceFollow();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mContext, "请先调用 FaceUtils.initData 方法", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 停止人脸跟踪，但是摄像头还是开着
     */
    public void stopFaceFollow() {
        if (inited) {
            try {
                mFaceRemote.stopFaceFollow();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mContext, "请先调用 FaceUtils.initData 方法", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 关闭人脸跟踪也会关闭摄像头
     */
    public void closeFaceFollowAndCamera() {
        if (inited) {
            try {
                mFaceRemote.closeFaceFollowAndCamera();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mContext, "请先调用 FaceUtils.initData 方法", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 开始人脸识别
     */
    public void startFaceDiscern() {
        if (inited) {
            try {
                mFaceRemote.startFaceDiscern();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mContext, "请先调用 FaceUtils.initData 方法", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 开始人脸识别
     */
    public void stopFaceDiscern() {
        if (inited) {
            try {
                mFaceRemote.stopFaceDiscern();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mContext, "请先调用 FaceUtils.initData 方法", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 根据人脸运到人跟前（比如语音指令（你过来））
     */
    public void runWithFaceFollow() {
        if (inited) {
            try {
                mFaceRemote.runWithFaceFollow();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mContext, "请先调用 FaceUtils.initData 方法", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 释放SDK
     */
    public void release() {
        isAppRun = false;
        if (isBind) {
            try {
                mFaceRemote.release();
//            closeFaceFollowAndCamera();
                mFaceRemote.unregisterCallBack(mFaceCallBack);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mContext.unbindService(mServiceConnection);
            isBind = false;
        }
    }


    /**
     * 人脸录入（注意：如果一张图片有多张人脸只录入图片中人脸最大的人）
     *
     * @param img  人脸录入需要的图片
     * @param name 保存的人名
     */
    public void faceEntry(final Context mContext, final Bitmap img, final String name) {
        PictureUtil.deleteChildFile(new File("/sdcard/myFolder"));
        mFileName = PictureUtil.saveImage(img, "/sdcard/faceTemp","faceSave");
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("正在检测人脸，请稍等...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        if (UsefulUtil.isOnline(mContext)) {
            mSingleThrExe.execute(new Runnable() {
                @Override
                public void run() {
                    final byte[] bitmapBytes = Bitmap2Bytes(img);
                    final JSONObject res = mClient.detect(bitmapBytes, new HashMap<String, String>());
                    try {
                        ShowLog.d(TAG + "detect", res.toString());
                        final String error_msg = res.getString("error_msg");
                        if (mMainHandler != null) {
                            mMainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressDialog.dismiss();
                                    Toast.makeText(mContext, error_msg, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        try {
                            final int resultNum = res.getInt("result_num");
                            if (resultNum > 0) {
                                isFindFace=true;
                                DetectBaiduEnt detectBaiduEnt=new Gson().fromJson(res.toString(),new TypeToken<DetectBaiduEnt>(){}.getType());
                                List<DetectBaiduEnt.ResultBean> beanList = detectBaiduEnt.getResult();
                                Paint paint = new Paint();
                                paint.setColor(Color.GREEN);
                                paint.setStrokeWidth(3);

                                //create a new canvas
                                Bitmap bitmap = Bitmap.createBitmap(img.getWidth(), img.getHeight(), img.getConfig());
                                Canvas canvas = new Canvas(bitmap);
                                canvas.drawBitmap(img, new Matrix(), null);
                                int index = 0;
                                int Distance = beanList.get(0).getLocation().getWidth();
                                for (int j = 1; j < beanList.size(); j++) {
                                    int two = beanList.get(j).getLocation().getWidth();
                                    if (two > Distance) {
                                        index = j;
                                        Distance = two;
                                    }
                                }
                                DetectBaiduEnt.ResultBean resultBean = beanList.get(index);
                                //use the red paint
                                DetectBaiduEnt.ResultBean.LocationBean location = resultBean.getLocation();
                                canvas.drawLine(location.getLeft(), location.getTop(), location.getLeft()+location.getWidth(), location.getTop(), paint);
                                canvas.drawLine(location.getLeft(), location.getTop()+location.getHeight(), location.getLeft()+location.getWidth(), location.getTop()+location.getHeight(), paint);
                                canvas.drawLine(location.getLeft(), location.getTop(), location.getLeft(), location.getTop()+location.getHeight(), paint);
                                canvas.drawLine(location.getLeft()+location.getWidth(), location.getTop(), location.getLeft()+location.getWidth(), location.getTop()+location.getHeight(), paint);
                                Bitmap detectedImg = bitmap;
                                mOnFaceListener.getDetectedImg(detectedImg);
                                if (mMainHandler != null) {
                                    mMainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            saveAddFace(mContext, name,bitmapBytes);
                                        }
                                    });
                                }

                            } else {
                                if (mMainHandler != null) {
                                    mMainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            mProgressDialog.dismiss();
                                            Toast.makeText(mContext, "没有发现人脸", Toast.LENGTH_SHORT).show();
                                            isFindFace = false;
                                        }
                                    });
                                }
                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                            mProgressDialog.dismiss();
                            isFindFace = false;
                        }
                    }
                }
            });
        } else {
            mProgressDialog.dismiss();
            Toast.makeText(mContext, "请检查网络连接", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveAddFace(final Context mContext, final String name , final byte[] bitmapBytes) {
        if (UsefulUtil.isOnline(mContext)) {
            mProgressDialog.setMessage("正在人脸录入中.....");
            // 先保存图片到服务器文件中
            OkGo.<String>post(BB_DOMAIN_UPFILE+UPLOAD_HELE_FILE)
                    .tag(mContext)
                    .params("file",new File(mFileName))
                    .params("folderName","heidou_face")
                    .params("desType","1")
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            String body = response.body();
                            ShowLog.i(body);
                            try {
                                JSONObject object = new JSONObject(body);
                                int statusCode = Integer.parseInt(object.getString("statusCode"));
                                if(statusCode == 200){
                                    JSONArray array = object.getJSONArray("object");
                                    final String url = array.getString(0);
                                    mSingleThrExe.execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                JSONObject object1 = new JSONObject();
                                                object1.put("name",name);
                                                object1.put("url",url);
                                                JSONObject res =mClient.addUser("uid1", object1.toString(), Arrays.asList(/*bbgroup1*/"bbgroup2"), bitmapBytes, new HashMap<String, String>());
                                                ShowLog.d(TAG + "saveAddFace", res.toString());
                                                final String error_msg = res.getString("error_msg");
                                                if (error_msg != null) {
                                                    if (mMainHandler != null) {
                                                        mMainHandler.post(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                mProgressDialog.dismiss();
                                                                ShowLog.d("error", error_msg);
                                                                Toast.makeText(mContext, "错误信息:" + error_msg, Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                if (mMainHandler != null) {
                                                    mMainHandler.post(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            mProgressDialog.dismiss();
                                                            FileUtils.deleteFile("/sdcard/faceTemp/faceSave.jpg");
                                                            Toast.makeText(mContext, "添加成功", Toast.LENGTH_SHORT).show();
                                                            mOnFaceListener.onFaceEntrySuccessed();
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    });
                                }else{
                                    mProgressDialog.dismiss();
                                    String msg = object.getString("message");
                                    ShowLog.i(msg);
                                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                mProgressDialog.dismiss();
                                String error_msg = e.getMessage();
                                e.printStackTrace();
                                ShowLog.i(error_msg);
                                Toast.makeText(mContext, error_msg, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            mProgressDialog.dismiss();
                            ShowLog.i(response.getException().getMessage());
                            Toast.makeText(mContext, response.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            mProgressDialog.dismiss();
            Toast.makeText(mContext, "请检查网络连接", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 人脸的回调接口
     */
    public interface OnFaceListener extends Parcelable {

        /**
         * @param data 摄像头的预览数据
         */
        void onPreviewFrame(byte[] data);

        /**
         * @param findFace 是否检测到人脸
         */
        void onFindFace(boolean findFace);

        /**
         * 根据人脸运到到人跟前
         *
         * @param state 停止时的状态（0：已经到了人跟前
         *              1：红外检测到了障碍物
         *              2：底盘发生了碰撞   ）
         */
        void onFaceFllowWithMoving(int state);

        /**
         * @param bitmap 获取人脸检测后的图片（在原图上用绿色框框住人脸的图片）
         */
        void getDetectedImg(Bitmap bitmap);

        /**
         * 保存人脸成功的回调
         */
        void onFaceEntrySuccessed();

        /**
         *
         * 人脸请求失败
         *
         * @param error
         */
        void dealDiscernFail(String error);

        /**
         * @param result         识别到的人名
         * @param score          相似度分数
         */
        void dealDiscernResult(String result,double score/*, DetectWithAttribute.FacesBean.AttributesBean attributesBean*/);/**/

        /**
         * 数据库中没有该人脸
         */
        void incognizant();
    }

    byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }


}
