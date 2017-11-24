package com.xg.east.hibot_b_library.face_utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xg.east.hibot_b_library.entity.BDIdentify;
import com.xg.east.hibot_b_library.service.FaceService;
import com.xg.east.hibot_b_library.utils.PictureUtil;
import com.xg.east.hibot_b_library.utils.ShowLog;
import com.xg.east.hibot_b_library.utils.UsefulUtil;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by EastRiseWM on 2017/2/21.
 */

/*人脸识别的开关*/

public class FaceDiscernUtil {
    final String TAG=this.getClass().getSimpleName();
    private final String URL="https://api-cn.faceplusplus.com/facepp/v3/search";
    private String faceset_token;
    Handler handlerText;
    HandlerThread handlerThreadText;
    Handler handlerAdmin;
    HandlerThread handlerThreadAdmin;
    Handler handlerTrain;
    HandlerThread handlerThreadTrain;
    final public static String APIKEY = "5ktoG917Et3bbNY_DkKYMvWDgnFYXRFo";
    final public static String APISecret = "q9h0fiU3yGA7CDoRq0tJA2mo1ULo-Cq-";
    int BACK_CAMERA = 0;
    int FRONT_CAMERA = 1;
    Bitmap tem;
    byte[] tmp;
    Bitmap bmp;
    String faceId;
    private String path;
    private int mGender;//性别（性别，值为 0~100，小于 50 代表女性，反之代表男性）
    private int mIndex;//检测到最大的人脸的下标
    private int mSmile;//笑容（微笑的程度，值为 0~100，越大代表微笑程度越深）
    private int mEyeglass;//是否带眼镜（戴眼镜，值为 0~100，小于 50 代表未戴眼镜，反之代表戴眼镜）
    DiscernResult mDiscernResult;
    boolean startDiscern = true;/*是否开始人脸检测*/
    private int mI;
    private boolean isRequesting=false;
    private FaceService mFaceService;
    ExecutorService mSingleExeSer;
    public FaceDiscernUtil(FaceService faceService) {
        mFaceService=faceService;
        mSingleExeSer= Executors.newSingleThreadExecutor();
        handlerThreadTrain = new HandlerThread("train");
        handlerThreadTrain.start();
        handlerTrain = new Handler(handlerThreadTrain.getLooper());

        handlerThreadText = new HandlerThread("viewText");
        handlerThreadText.start();
        handlerText = new Handler(handlerThreadText.getLooper());

        handlerThreadAdmin = new HandlerThread("admin");
        handlerThreadAdmin.start();
        handlerAdmin = new Handler(handlerThreadAdmin.getLooper());

        startDiscern = false;
    }
    public interface DiscernResult {
        void dealDiscernResult(String result, double score/*, DetectWithAttribute.FacesBean.AttributesBean attributesBean*/);/*识别到的人名*/

        void incognizant();/*不认识*/
    }

    public void setDiscernResult(DiscernResult discernResult) {
        mDiscernResult = discernResult;
    }

    public void startFaceDiscern() {
        startDiscern = true;
        isRequesting=false;
    }

    public void stopFaceDiscern() {
        startDiscern = false;
    }

    public void dealFaceAtOnPreview(final byte[] data, final Camera camera, final Context context, final int cameraState) {
        if (startDiscern) {
            handlerText.post(new Runnable() {
                @Override
                public void run() {
                    if(!isRequesting){
                        isRequesting=true;
                        try {
                            mI = mI + 1;
                            mI = 0;
                            Log.d("handlerText", "运行中2");
                            tem = yuv2bitmap(data, camera);
                            if (cameraState == FRONT_CAMERA) {
                                tem = bitmapRotate(-90, tem);
                                path = PictureUtil.saveCroppedImage(tem);
                            } else {
                                path = PictureUtil.saveCroppedImage(tem);
                            }

                            if (UsefulUtil.isOnline(mFaceService)) {
                                mSingleExeSer.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        JSONObject res = FaceUtils.getInstance().mClient.identifyUser(Arrays.asList("bbgroup2"), path, new HashMap<String, Object>());
                                        ShowLog.d(TAG+":dealFaceAtOnPreview",res.toString());
                                        try {
                                            String error_msg = res.getString("error_msg");
                                            mFaceService.changePreSize(320,240);
                                            try {
                                                mFaceService.mFaceCallBack.dealDiscernFail(error_msg);
                                            } catch (Exception e1) {
                                                e1.printStackTrace();
                                            }
                                        } catch (Exception e1) {
                                            e1.printStackTrace();
                                            BDIdentify bdIdentify=new Gson().fromJson(res.toString(),new TypeToken<BDIdentify>(){}.getType());
                                            String user_info = bdIdentify.getResult().get(0).getUser_info();
                                            ShowLog.d(TAG+":dealFaceAtOnPreview",res.toString()+"\nuserinfo:"+(bdIdentify.getResult_num()>0? user_info :""));
                                            if(bdIdentify.getResult_num()>0){
                                                Double score = bdIdentify.getResult().get(0).getScores().get(0);
                                                mFaceService.changePreSize(320,240);
                                                if(score >0){
                                                    if(score>30){
                                                        delete(new File("/sdcard/myFolder"));
                                                        try {
                                                            mFaceService.mFaceCallBack.dealDiscernResult(user_info,score);
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    }else{
                                                        try {
                                                            mFaceService.mFaceCallBack.incognizant();
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                }else{
                                                    try {
                                                        mFaceService.mFaceCallBack.incognizant();
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }else{
                                                mFaceService.changePreSize(320,240);
                                                try {
                                                    mFaceService.mFaceCallBack.incognizant();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            mFaceService.changePreSize(320,240);
                                        }
                                    }
                                });
                            }else{
                                mFaceService.changePreSize(320,240);
                                mFaceService.mFaceCallBack.dealDiscernFail("网路异常");
                            }


//                            OkGo.<String>post(URL)
//                                    .params("api_key",APIKEY)
//                                    .params("api_secret",APISecret)
//                                    .params("image_file",new File(path))
//                                    .params("faceset_token",faceset_token)
//                                    .execute(new StringCallback() {
//                                        @Override
//                                        public void onSuccess(Response<String> response) {
//                                            Log.d("onSuccessSearch",response.body());
//                                            try {
//                                                JSONObject object=new JSONObject(response.body());
//                                                String error_message = object.getString("error_message");
//                                                if (error_message == null) {
//                                                    SearchFacEnt searchFacEnt=new Gson().fromJson(response.body(),new TypeToken<SearchFacEnt>(){}.getType());
//
//                                                }
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            }
//
//                                            delete(new File("/sdcard/myFolder"));
//                                        }
//
//                                        @Override
//                                        public void onError(Response<String> response) {
//                                            try {
//                                                mFaceService.mFaceCallBack.dealDiscernFail(response.getException().getMessage());
//                                            } catch (Exception e) {
//                                                e.printStackTrace();
//                                            }
//                                            delete(new File("/sdcard/myFolder"));
//                                        }
//                                    });
                            /*手动对已经使用完的bitmap进行内存回收*/
                            if (tem != null && !tem.isRecycled()) {
                                tem.recycle();
                                tem = null;
                            }
                            if (bmp != null && !bmp.isRecycled()) {
                                bmp.recycle();
                                bmp = null;
                            }
//手动运行垃圾回收器，会对整个内存进行扫描，理论上比较耗时,实际内存的检测结果没有很大变化。。。可能自动回收也做的比较好吧
                            System.gc();
                        } catch (Exception e) {
                            e.printStackTrace();
                            delete(new File("/sdcard/myFolder"));
                        }
                    }
                }
            });
        }
    }


    //   把yuv转换成bitmap
    public Bitmap yuv2bitmap(byte[] mData, Camera myCamera) {
        try {
            Camera.Size size = myCamera.getParameters().getPreviewSize(); //获取预览大小
            final int w = size.width;  //宽度
            final int h = size.height;
//        final int w = 640;  //宽度
//        final int h = 480;
            Log.d("size", w + ":" + h + "");
            final YuvImage image = new YuvImage(mData, ImageFormat.NV21, w, h, null);
            ByteArrayOutputStream os = new ByteArrayOutputStream(mData.length);
            if (!image.compressToJpeg(new Rect(0, 0, w, h), 100, os)) {
                return null;
            }
            tmp = os.toByteArray();
            bmp = BitmapFactory.decodeByteArray(tmp, 0, tmp.length);
            tmp = null;
            return bmp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //旋转bitmap方法2
    protected Bitmap bitmapRotate(float degrees, Bitmap baseBitmap) {
        // 创建一个和原图一样大小的图片
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth(),
                baseBitmap.getHeight(), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Paint paint = new Paint();
        Matrix matrix = new Matrix();
        // 根据原图的中心位置旋转
        matrix.setRotate(degrees, baseBitmap.getWidth() / 2,
                baseBitmap.getHeight() / 2);
        canvas.drawBitmap(baseBitmap, matrix, paint);
        baseBitmap.recycle();
        return afterBitmap;
    }

    /**
     * @param file 删除掉子文件
     */
    public static void delete(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i]);
            }
        }

    }

}
