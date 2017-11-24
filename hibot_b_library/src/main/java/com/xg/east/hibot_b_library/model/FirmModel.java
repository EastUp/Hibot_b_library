package com.xg.east.hibot_b_library.model;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xg.east.hibot_b_library.SerialSend;
import com.xg.east.hibot_b_library.entity.FWNewestChangeBean;
import com.xg.east.hibot_b_library.entity.FirmWareBean;
import com.xg.east.hibot_b_library.service.usbSendType.ClassicType;
import com.xg.east.hibot_b_library.service.usbSendType.LightType;
import com.xg.east.hibot_b_library.service.usbSendType.MainCtl;
import com.xg.east.hibot_b_library.service.usbSendType.MotorPType;
import com.xg.east.hibot_b_library.utils.FileToBytes;
import com.xg.east.hibot_b_library.utils.FileUtils;
import com.xg.east.hibot_b_library.utils.MD5;
import com.xg.east.hibot_b_library.utils.ShowLog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by EastRiseWM on 2016/12/23.
 */

public class FirmModel {
    private static String mFrimwareName;//要更新固件数据，根据名字来判断给哪个设备ID发送数据
    private static byte[] mBytes = new byte[0];//下载下来的文件转换成的字节数组
    public static boolean isFirstReceiveUp = true;//是否第一次收到设备需要第几包的命令
    private static boolean isUpdateFinish = false;
    private static Context mContext;

    //以下是返回结果标识
    private static final int START_UPDATE = 7;
    private static final int UPDATE_OK = 8;
    private static final int DATA_ERROR = 9;

    //在主界面升级时，是否正在下载升级的文件
    public static boolean onMainResumeDownFile = false;

    public FirmModel(Context context) {
        mContext = context;
    }

    private static ArrayList<byte[]> mList = new ArrayList();//需要发送用来查询当前固件版本的字节数组
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET_ERROR:
                    errorCount++;
                    if (errorCount > 2) {
                        index++;
                        errorCount = 0;
                        sendData();
                    } else {
                        sendData();
                    }
                    break;
                default:
                    break;
            }
        }
    };
    private final static int GET_ERROR = 0;//没接收到返回，获取版本失败
    private static int index = 0;//需要发送的byte[]下标
    private static int errorCount = 0;//发送失败没返回的次数
    private static final String url = "http://bbox.blackboxes.cn/cat/firmware/getList";//获取固件列表
    private static List<FirmWareBean> mBeanList = new ArrayList<>();//需要返还给presenter让RecycleView显示数据的BeanList
    private static HashMap<String, String> map = new HashMap<>();//返回targetId对应的设备名字
    private static HashMap<String, Integer> map2 = new HashMap<>();//返回设备名字对应的targetId
    private static int serverIndex = 0;//请求服务器的下标
    private static int mUdIndex;//需要更新的固件列表的下标
    private static List<FirmWareBean> mUdFirmwareList = new ArrayList<>();//需要更新的固件列表
    private static List<byte[]> mUpdateDataList = new ArrayList<>();
    private static boolean searchedMotor = false;//是否已经请求过Motor的当前版本或最新版本
    //    private static FWNewestBean mFwNewestBean;//已经获取到的MOTOR的FWNewestBean
    private static FWNewestChangeBean mFwNewestBean;//已经获取到的MOTOR的FWNewestBean
    private static int packetSize = 2000;//每包的大小

    //获取固件版本需要发送的数据
    public static void getVersionSendData() {
        isFirstReceiveUp=false;
        searchedMotor = false;
        index = 0;
        serverIndex = 0;
        map = new HashMap<>();
        mList = new ArrayList();
        mBeanList = new ArrayList<>();
//        v.showProgressDialog();
        SerialSend.updateFirmState=true;

        map.put("0100", "MOTOR0");
        map.put("0101", "MOTOR1");
        map.put("0102", "MOTOR2");
        map.put("0103", "MOTOR3");
        map.put("0104", "MOTOR4");
        map.put("0105", "MOTOR5");
        map.put("0106", "MOTOR6");
        map.put("0107", "MOTOR7");
        map.put("0108", "MOTOR8");
        map.put("0109", "MOTOR9");
        map.put("010a", "MOTOR10");
        map.put("010b", "MOTOR11");
//        map.put("0200", "POWER");
        map.put("0300", "FOOTY");
        map.put("0400", "HEAD");
        map.put("0500", "FOOTN");
        map.put("00fc", "F407");

        map2.put("MOTOR0", 0x0100);
        map2.put("MOTOR1", 0x0101);
        map2.put("MOTOR2", 0x0102);
        map2.put("MOTOR3", 0x0103);
        map2.put("MOTOR4", 0x0104);
        map2.put("MOTOR5", 0x0105);
        map2.put("MOTOR6", 0x0106);
        map2.put("MOTOR7", 0x0107);
        map2.put("MOTOR8", 0x0108);
        map2.put("MOTOR9", 0x0109);
        map2.put("MOTOR10", 0x010A);
        map2.put("MOTOR11", 0x010B);
        byte[] bytes0 = MotorPType.getFwVersion(0x0100);
        byte[] bytes1 = MotorPType.getFwVersion(0x0101);
        byte[] bytes2 = MotorPType.getFwVersion(0x0102);
        byte[] bytes3 = MotorPType.getFwVersion(0x0103);
        byte[] bytes4 = MotorPType.getFwVersion(0x0104);
        byte[] bytes5 = MotorPType.getFwVersion(0x0105);
        byte[] bytes6 = MotorPType.getFwVersion(0x0106);
        byte[] bytes7 = MotorPType.getFwVersion(0x0107);
        byte[] bytes8 = MotorPType.getFwVersion(0x0108);
        byte[] bytes9 = MotorPType.getFwVersion(0x0109);
        byte[] bytesA = MotorPType.getFwVersion(0x010A);
        byte[] bytesB = MotorPType.getFwVersion(0x010B);
        byte[] bytesC = ClassicType.getFwVersion();
//        byte[] bytesE = EleType.getFwVersion();//电池的固件版本
        byte[] headFwVersion = LightType.getHeadFwVersion();
        byte[] bytesM = MainCtl.getFwVersion();
        mList.add(bytes0);
        mList.add(bytes1);
        mList.add(bytes2);
        mList.add(bytes3);
        mList.add(bytes4);
        mList.add(bytes5);
        mList.add(bytes6);
        mList.add(bytes7);
        mList.add(bytes8);
        mList.add(bytes9);
        mList.add(bytesA);
        mList.add(bytesB);
        mList.add(bytesC);
//        mList.add(bytesE);
        mList.add(headFwVersion);
        mList.add(bytesM);
        sendData();
    }

    static Runnable ErrorRun = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(GET_ERROR);
        }
    };

    //发送数据,发送完毕并查询服务器上的版本号
    private static void sendData() {
        if (index < mList.size()) {
            SerialSend.sendAllBytesToSerial(mList.get(index));
            mHandler.postDelayed(ErrorRun, 800);
        } else {
            if (mBeanList.size()>0) {
                String name = mBeanList.get(serverIndex).getFrimwareName();
                if (name.contains("MOTOR")) {
                    if (!searchedMotor) {
                        getNewestVersion("MOTOR");
                    }
                } else {
                    getNewestVersion(name);
                }
            }else{
                SerialSend.updateFirmState=false;
                SerialSend.getInstance().mOnFirmwareListener.dismissProgressDialog();
                SerialSend.getInstance().mOnFirmwareListener.onToastMessage("固件版本获取失败，请检查通讯");
            }
        }
    }

    //获取返回的固件版本号
    public static void getVersion(String targetId, int version) {
        FirmWareBean firmWareBean = new FirmWareBean();
        firmWareBean.setCurentVersion(version);
        String name = map.get(targetId);
        if (name.contains("MOTOR")) {
            firmWareBean.setType(0);
        }/* else if (name.equals("POWER")) {
            firmWareBean.setType(1);
        } */ else if (name.contains("FOOTY")) {
            firmWareBean.setType(2);
        } else if (name.contains("FOOTN")) {
            firmWareBean.setType(2);
        } else if (name.equals("HEAD")) {
            firmWareBean.setType(3);
        } else if (name.equals("F407")) {
            firmWareBean.setType(4);
        }
        firmWareBean.setFrimwareName(name);
        mBeanList.add(firmWareBean);
        mHandler.removeCallbacks(ErrorRun);
        index++;
        errorCount = 0;
        sendData();
        SerialSend.updateFirmState=true;
        SerialSend.getInstance().mOnFirmwareListener.onProgressMessage(false,"正在获取" + name + "的版本", index, 16,0,index);
//        v.setMsgAndProgress("正在获取" + name + "的版本", index, 16);
    }


    /**
     * 根据名字向服务器请求最新的固件版本
     */
    private static void getNewestVersion(final String name) {
        try {
            JSONObject object = new JSONObject();
            object.put("type", 4);
            object.put("name", name);
            OkHttpUtils
                    .postString()
                    .url(url)
                    .content(object.toString())
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            SerialSend.updateFirmState=false;
                            SerialSend.getInstance().mOnFirmwareListener.dismissProgressDialog();
                            SerialSend.getInstance().mOnFirmwareListener.onToastMessage("请求失败");
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            ShowLog.v("modelResult", name + "||" + response);
                            FWNewestChangeBean fwNewestBean = new Gson().fromJson(response, new TypeToken<FWNewestChangeBean>() {
                            }.getType());
                            if (name.contains("MOTOR")) {
                                searchedMotor = true;
                                mFwNewestBean = fwNewestBean;
                            }
                            mBeanList.get(serverIndex).setFWNewestBean(fwNewestBean);
                            //接着请求最新版本
                            searchNewVersion();
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 更新固件
     *
     * @param firmWareBeanList 可更新的固件列表
     */
    public void updateFw(List<FirmWareBean> firmWareBeanList) {
//        v.showProgressDialog();
        SerialSend.updateFirmState=true;
        mUdFirmwareList = firmWareBeanList;
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "firmwareFiles");
        ShowLog.v("filePath", file.getAbsolutePath());
        if (!file.exists()) {
            file.mkdirs();
        }
        mUdIndex = 0;//需要更新的固件的下标
        FirmWareBean firmWareBean = firmWareBeanList.get(mUdIndex);
        updateFw(firmWareBean,mUdIndex);
    }

    /**
     * 固件一个一个的更新
     */
    public static void updateFw(final FirmWareBean firmWareBean, final int index) {
//        FWNewestBean fwNewestBean = firmWareBean.getFWNewestBean();
        FWNewestChangeBean fwNewestBean = firmWareBean.getFWNewestChangeBean();
        final String frimwareName = firmWareBean.getFrimwareName();
        final FWNewestChangeBean.ObjectBean objectBean = fwNewestBean.getObject().get(0);
        OkHttpUtils
                .get()
                .url(objectBean.getUrl())
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory() + File.separator + "firmwareFiles", (frimwareName.contains("MOTOR") ? "MOTOR" : frimwareName) + ".bin") {
                    @Override
                    public void inProgress(float progress, long total, int id) {
                        SerialSend.getInstance().mOnFirmwareListener.onProgressMessage(false,"正在下载" + frimwareName + "最新固件", (int) progress, (int) total,1,index);
//                        v.setMsgAndProgress("正在下载" + frimwareName + "最新固件", (int) progress, (int) total);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
//                        FirmwareUpdateFg f = (FirmwareUpdateFg) v;
//                        Toast.makeText(f.getContext(), "下载失败", Toast.LENGTH_SHORT).show();
                        SerialSend.getInstance().mOnFirmwareListener.onToastMessage("下载失败");
                    }

                    @Override
                    public void onResponse(File response, int id) {
//                        FirmwareUpdateFg f = (FirmwareUpdateFg) v;
//                        v.dissMissProgressDialog();
//                        Toast.makeText(f.getContext(), "下载完成", Toast.LENGTH_SHORT).show();
                        SerialSend.updateFirmState=false;
                        SerialSend.getInstance().mOnFirmwareListener.dismissProgressDialog();
                        SerialSend.getInstance().mOnFirmwareListener.onToastMessage("下载完成");
                        mBytes = FileToBytes.getBytes(response);
                        mUpdateDataList = new ArrayList<byte[]>();//重新初始化要更新的数据列
                        int len = 0;//第几包
                        int i = mBytes.length / packetSize;
                        int i1 = mBytes.length % packetSize;
                        while (len < i) {
                            byte[] bytes1 = new byte[packetSize];
                            System.arraycopy(mBytes, len * packetSize, bytes1, 0, bytes1.length);
                            mUpdateDataList.add(bytes1);
                            len++;
                        }
                        if (i1 > 0) {
                            byte[] bytes1 = new byte[i1];
                            System.arraycopy(mBytes, i * packetSize, bytes1, 0, bytes1.length);
                            mUpdateDataList.add(bytes1);
                        }

                        boolean checkMD5 = MD5.checkMD5(mBytes, objectBean.getFileMD5());
                        if (!checkMD5) {//文件下载错误
//                            Toast.makeText(f.getContext(), frimwareName + "更新文件下载错误", Toast.LENGTH_SHORT).show();
                            SerialSend.getInstance().mOnFirmwareListener.onToastMessage(frimwareName + "更新文件下载错误");
                            mUdIndex++;
                            if (mUdIndex < mUdFirmwareList.size()) {
                                FirmWareBean firmWareBean = mUdFirmwareList.get(mUdIndex);
                                updateFw(firmWareBean,mUdIndex);
                            } else {
                                SerialSend.getInstance().mOnFirmwareListener.dismissProgressDialog();
//                                v.dissMissProgressDialog();
                                SerialSend.updateFirmState=false;
                            }
                        } else {
                            mFrimwareName = frimwareName;
                            if (mFrimwareName.contains("MOTOR")) {
                                byte[] bytes1 = MotorPType.intoBootloader(map2.get(mFrimwareName));
                                SerialSend.sendAllBytesToSerial(bytes1);
                            } else {
                                switch (mFrimwareName) {
//                                    case "POWER":
//                                        SerialSend.sendAllBytesToSerial(EleType.intoBootloader());
//                                        break;
                                    case "FOOTY":
                                        SerialSend.sendAllBytesToSerial(ClassicType.intoBootloader());
                                        break;
                                    case "FOOTN":
                                        SerialSend.sendAllBytesToSerial(ClassicType.intoBootloader());
                                        break;
                                    case "HEAD":
                                        SerialSend.sendAllBytesToSerial(LightType.intoBootloader());
                                        break;
                                    case "F407":
                                        SerialSend.sendAllBytesToSerial(MainCtl.intoBootloader());
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                });
    }

    /**
     * @param context  上下文
     * @param targetId 目标ID
     * @param index    发送包的Index
     */
    public static void getSendIndexData(final Context context, String targetId, int index, int identification) {
        if (identification != START_UPDATE && identification != UPDATE_OK && identification != DATA_ERROR) {
            mContext = context;
            isUpdateFinish = false;
            mHandler.removeCallbacks(udFinishRun);
            mFrimwareName = map.get(targetId.trim());
            ShowLog.v("FirmModel", targetId+"||"+mFrimwareName);
            if (mFrimwareName.contains("MOTOR")) {
                if (index < mUpdateDataList.size()) {
                    byte[] bytes1 = MotorPType.sendUpdata(map2.get(mFrimwareName), index, mUpdateDataList.get(index));
                    SerialSend.sendAllBytesToSerial(bytes1);
                    SerialSend.getInstance().mOnFirmwareListener.onProgressMessage(false,"正在更新" + mFrimwareName, index + 1, mUpdateDataList.size(),2,mUdIndex);
//                    v.setMsgAndProgress("正在更新" + mFrimwareName, index + 1, mUpdateDataList.size());
//                    v.showProgressDialog();
                    SerialSend.updateFirmState=true;
                } else {
                    SerialSend.sendAllBytesToSerial(MotorPType.sendUdEnd(map2.get(mFrimwareName), mBytes));
                    mUdIndex++;//需要更新的固件的下标
                    if (mUdIndex < mUdFirmwareList.size()) {
                        FirmWareBean firmWareBean = mUdFirmwareList.get(mUdIndex);
                        updateFw(firmWareBean,mUdIndex);
                    } else {
                    /*每次完了就监听一下*/
                        mHandler.postDelayed(udFinishRun, 4000);
                    }
                }
            } else {
                switch (mFrimwareName) {
//                    case "POWER":
//                        if (index < mUpdateDataList.size()) {
//                            SerialSend.sendAllBytesToSerial(EleType.sendUpdata(index, mUpdateDataList.get(index)));
//                            v.setMsgAndProgress("正在更新" + mFrimwareName, index + 1, mUpdateDataList.size());
//                        } else {
//                            SerialSend.sendAllBytesToSerial(EleType.sendUdEnd(mBytes));
//                            mUdIndex++;//需要更新的固件的下标
//                            if (mUdIndex < mUdFirmwareList.size()) {
//                                FirmWareBean firmWareBean = mUdFirmwareList.get(mUdIndex);
//                                updateFw(firmWareBean,mUdIndex);
//                            } else {
//                            /*每次完了就监听一下*/
//                                mHandler.postDelayed(udFinishRun, 4000);
//                            }
//                        }
//                        break;
                    case "FOOTY":
                        if (index < mUpdateDataList.size()) {
                            SerialSend.sendAllBytesToSerial(ClassicType.sendUpdata(index, mUpdateDataList.get(index)));
                            SerialSend.getInstance().mOnFirmwareListener.onProgressMessage(false,"正在更新" + mFrimwareName, index + 1, mUpdateDataList.size(),2,mUdIndex);
//                            v.setMsgAndProgress("正在更新" + mFrimwareName, index + 1, mUpdateDataList.size());
//                            v.showProgressDialog();
                            SerialSend.updateFirmState=true;
                        } else {
                            SerialSend.sendAllBytesToSerial(ClassicType.sendUdEnd(mBytes));
                            mUdIndex++;//需要更新的固件的下标
                            if (mUdIndex < mUdFirmwareList.size()) {
                                FirmWareBean firmWareBean = mUdFirmwareList.get(mUdIndex);
                                updateFw(firmWareBean,mUdIndex);
                            } else {
                            /*每次完了就监听一下*/
                                mHandler.postDelayed(udFinishRun, 4000);
                            }
                        }
                        break;
                    case "FOOTN":
                        if (index < mUpdateDataList.size()) {
                            SerialSend.sendAllBytesToSerial(ClassicType.sendUpdata(index, mUpdateDataList.get(index)));
                            SerialSend.getInstance().mOnFirmwareListener.onProgressMessage(false,"正在更新" + mFrimwareName, index + 1, mUpdateDataList.size(),2,mUdIndex);
//                            v.setMsgAndProgress("正在更新" + mFrimwareName, index + 1, mUpdateDataList.size());
//                            v.showProgressDialog();
                            SerialSend.updateFirmState=true;
                        } else {
                            SerialSend.sendAllBytesToSerial(ClassicType.sendUdEnd(mBytes));
                            mUdIndex++;//需要更新的固件的下标
                            if (mUdIndex < mUdFirmwareList.size()) {
                                FirmWareBean firmWareBean = mUdFirmwareList.get(mUdIndex);
                                updateFw(firmWareBean,mUdIndex);
                            } else {
                            /*每次完了就监听一下*/
                                mHandler.postDelayed(udFinishRun, 4000);
                            }
                        }
                        break;
                    case "HEAD":
                        if (index < mUpdateDataList.size()) {
                            SerialSend.sendAllBytesToSerial(LightType.sendUpdata(index, mUpdateDataList.get(index)));
                            SerialSend.getInstance().mOnFirmwareListener.onProgressMessage(false,"正在更新" + mFrimwareName, index + 1, mUpdateDataList.size(),2,mUdIndex);
//                            v.setMsgAndProgress("正在更新" + mFrimwareName, index + 1, mUpdateDataList.size());
//                            v.showProgressDialog();
                            SerialSend.updateFirmState=true;
                        } else {
                            SerialSend.sendAllBytesToSerial(LightType.sendUdEnd(mBytes));
                            mUdIndex++;//需要更新的固件的下标
                            if (mUdIndex < mUdFirmwareList.size()) {
                                FirmWareBean firmWareBean = mUdFirmwareList.get(mUdIndex);
                                updateFw(firmWareBean,mUdIndex);
                            } else {
                            /*每次完了就监听一下*/
                                mHandler.postDelayed(udFinishRun, 4000);
                            }
                        }
                        break;
                    case "F407":
                        if (index < mUpdateDataList.size()) {
                            SerialSend.sendAllBytesToSerial(MainCtl.sendUpdata(index, mUpdateDataList.get(index)));
                            SerialSend.getInstance().mOnFirmwareListener.onProgressMessage(false,"正在更新" + mFrimwareName, index + 1, mUpdateDataList.size(),2,mUdIndex);
//                            v.setMsgAndProgress("正在更新" + mFrimwareName, index + 1, mUpdateDataList.size());
//                            v.showProgressDialog();
                            SerialSend.updateFirmState=true;
                        } else {
                            SerialSend.sendAllBytesToSerial(MainCtl.sendUdEnd(mBytes));
                            mUdIndex++;//需要更新的固件的下标
                            if (mUdIndex < mUdFirmwareList.size()) {
                                FirmWareBean firmWareBean = mUdFirmwareList.get(mUdIndex);
                                updateFw(firmWareBean,mUdIndex);
                            } else {
                            /*每次完了就监听一下*/
                                mHandler.postDelayed(udFinishRun, 4000);
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }


    }

    /*升级完成了的提醒*/
    static Runnable udFinishRun = new Runnable() {
        @Override
        public void run() {
//            v.dissMissProgressDialog();
            SerialSend.updateFirmState=false;
            SerialSend.getInstance().mOnFirmwareListener.dismissProgressDialog();
            SerialSend.getInstance().mOnFirmwareListener.onToastMessage("升级成功");
//            Toast.makeText(mContext, "升级成功", Toast.LENGTH_SHORT).show();
//            v.restorePd();
            getVersionSendData();
        }
    };

    /**
     * 在打开程序就收到了BootLoader需要的命令
     */
    public static void getSendIndexDataOnFirst(final Context context, String targetId, int index, int identification) {
        if (identification != START_UPDATE && identification != UPDATE_OK && identification != DATA_ERROR && !onMainResumeDownFile) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap = new HashMap<>();
            hashMap.put("0100", "MOTOR0");
            hashMap.put("0101", "MOTOR1");
            hashMap.put("0102", "MOTOR2");
            hashMap.put("0103", "MOTOR3");
            hashMap.put("0104", "MOTOR4");
            hashMap.put("0105", "MOTOR5");
            hashMap.put("0106", "MOTOR6");
            hashMap.put("0107", "MOTOR7");
            hashMap.put("0108", "MOTOR8");
            hashMap.put("0109", "MOTOR9");
            hashMap.put("010a", "MOTOR10");
            hashMap.put("010b", "MOTOR11");
//            hashMap.put("0200", "POWER");
            hashMap.put("0300", "FOOTY");
            hashMap.put("0400", "HEAD");
            hashMap.put("0500", "FOOTN");
            hashMap.put("00fc", "F407");
            HashMap<String, Integer> map2 = new HashMap<>();
            map2.put("MOTOR0", 0x0100);
            map2.put("MOTOR1", 0x0101);
            map2.put("MOTOR2", 0x0102);
            map2.put("MOTOR3", 0x0103);
            map2.put("MOTOR4", 0x0104);
            map2.put("MOTOR5", 0x0105);
            map2.put("MOTOR6", 0x0106);
            map2.put("MOTOR7", 0x0107);
            map2.put("MOTOR8", 0x0108);
            map2.put("MOTOR9", 0x0109);
            map2.put("MOTOR10", 0x010A);
            map2.put("MOTOR11", 0x010B);

            mFrimwareName = hashMap.get(targetId.trim());
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "firmwareFiles", (mFrimwareName.contains("MOTOR") ? "MOTOR" : mFrimwareName) + ".bin");
            if (!file.exists()) {//本地文件夹中不存在该文件则重新下载
                FirmModel.onMainResumeDownFile = true;
                try {
                    JSONObject object = new JSONObject();
                    object.put("type", 4);
                    object.put("name", mFrimwareName);
                    OkHttpUtils
                            .postString()
                            .url(url)
                            .content(object.toString())
                            .mediaType(MediaType.parse("application/json; charset=utf-8"))
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    FirmModel.onMainResumeDownFile = false;
//                                    Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
                                    SerialSend.getInstance().mOnFirmwareListener.onToastMessage("请求失败");
                                    SerialSend.getInstance().mOnFirmwareListener.dismissProgressDialog();
//                                    if (progressDialog.isShowing())
//                                        progressDialog.dismiss();
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    ShowLog.v("modelResult", response);
                                    FWNewestChangeBean fwNewestBean = new Gson().fromJson(response, new TypeToken<FWNewestChangeBean>() {
                                    }.getType());

                                    final FWNewestChangeBean.ObjectBean objectBean = fwNewestBean.getObject().get(0);
                                    OkHttpUtils
                                            .get()
                                            .url(objectBean.getUrl())
                                            .build()
                                            .execute(new FileCallBack(Environment.getExternalStorageDirectory() + File.separator + "firmwareFiles", (mFrimwareName.contains("MOTOR") ? "MOTOR" : mFrimwareName) + ".bin") {
                                                @Override
                                                public void inProgress(float progress, long total, int id) {
                                                    SerialSend.getInstance().mOnFirmwareListener.onProgressMessage(true,"正在下载" + mFrimwareName + "最新固件",(int) progress,(int) total,1,mUdIndex);
//                                                    progressDialog.setMessage("正在下载" + mFrimwareName + "最新固件");
//                                                    progressDialog.setProgress((int) progress);
//                                                    progressDialog.setProgress((int) total);
//                                                    progressDialog.show();
                                                }

                                                @Override
                                                public void onError(Call call, Exception e, int id) {
                                                    FirmModel.onMainResumeDownFile = false;
                                                    SerialSend.getInstance().mOnFirmwareListener.onToastMessage("下载失败");
                                                    SerialSend.getInstance().mOnFirmwareListener.dismissProgressDialog();
                                                    SerialSend.updateFirmState=false;
//                                                    if (progressDialog.isShowing())
//                                                        progressDialog.dismiss();
//                                                    Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onResponse(File response, int id) {
                                                    SerialSend.updateFirmState=false;
                                                    SerialSend.getInstance().mOnFirmwareListener.dismissProgressDialog();
//                                                    if (progressDialog.isShowing())
//                                                        progressDialog.dismiss();
                                                    SerialSend.getInstance().mOnFirmwareListener.onToastMessage("下载完成");
                                                    boolean checkMD5 = MD5.checkMD5(mBytes, objectBean.getFileMD5());
                                                    if (!checkMD5) {//文件下载错误
                                                        SerialSend.getInstance().mOnFirmwareListener.onToastMessage("更新文件下载错误");
                                                        FileUtils.deleteFile(Environment.getExternalStorageDirectory() + File.separator + "firmwareFiles" + File.separator + (mFrimwareName.contains("MOTOR") ? "MOTOR" : mFrimwareName) + ".bin");
                                                    }
                                                    FirmModel.onMainResumeDownFile = false;
                                                }
                                            });

                                }
                            });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                mBytes = FileToBytes.getBytes(file);
                mUpdateDataList = new ArrayList<byte[]>();//重新初始化要更新的数据列
                int len = 0;//第几包
                int i = mBytes.length / packetSize;
                int i1 = mBytes.length % packetSize;
                while (len < i) {
                    byte[] bytes1 = new byte[packetSize];
                    System.arraycopy(mBytes, len * packetSize, bytes1, 0, bytes1.length);
                    mUpdateDataList.add(bytes1);
                    len++;
                }
                if (i1 > 0) {
                    byte[] bytes1 = new byte[i1];
                    System.arraycopy(mBytes, i * packetSize, bytes1, 0, bytes1.length);
                    mUpdateDataList.add(bytes1);
                }

                SerialSend.getInstance().mOnFirmwareListener.onProgressMessage(true,"正在升级" + mFrimwareName,index + 1,mUpdateDataList.size(),2,mUdIndex);
//                progressDialog.setMessage("正在升级" + mFrimwareName);
//                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                progressDialog.setMax(mUpdateDataList.size());
//                progressDialog.setProgress(index + 1);
//                progressDialog.show();
                if (mFrimwareName.contains("MOTOR")) {
                    if (index < mUpdateDataList.size()) {
                        byte[] bytes1 = MotorPType.sendUpdata(map2.get(mFrimwareName), index, mUpdateDataList.get(index));
                        SerialSend.sendAllBytesToSerial(bytes1);
                        SerialSend.getInstance().mOnFirmwareListener.onProgressMessage(true,"正在升级" + mFrimwareName,index + 1,mUpdateDataList.size(),2,mUdIndex);
//                        progressDialog.setProgress(index + 1);
                    } else {
                        byte[] sendUdEnd = MotorPType.sendUdEnd(map2.get(mFrimwareName), mBytes);
                        SerialSend.sendAllBytesToSerial(sendUdEnd);
                        SerialSend.getInstance().mOnFirmwareListener.dismissProgressDialog();
//                        progressDialog.dismiss();
                        SerialSend.updateFirmState = false;
                        Toast.makeText(context, mFrimwareName + "升级成功", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    switch (mFrimwareName) {
//                        case "POWER":
//                            if (index < mUpdateDataList.size()) {
//                                SerialSend.sendAllBytesToSerial(EleType.sendUpdata(index, mUpdateDataList.get(index)));
//                                progressDialog.setProgress(index + 1);
//                            } else {
//                                SerialSend.sendAllBytesToSerial(EleType.sendUdEnd(mBytes));
//                                progressDialog.dismiss();
//                                SerialSend.updateFirmState = false;
//                                Toast.makeText(context, mFrimwareName + "升级成功", Toast.LENGTH_SHORT).show();
//                            }
//                            break;
                        case "FOOTY":
                            if (index < mUpdateDataList.size()) {
                                SerialSend.sendAllBytesToSerial(ClassicType.sendUpdata(index, mUpdateDataList.get(index)));
                                SerialSend.getInstance().mOnFirmwareListener.onProgressMessage(true,"正在升级" + mFrimwareName,index + 1,mUpdateDataList.size(),2,mUdIndex);
//                                progressDialog.setProgress(index + 1);
                            } else {
                                SerialSend.sendAllBytesToSerial(ClassicType.sendUdEnd(mBytes));
                                SerialSend.getInstance().mOnFirmwareListener.dismissProgressDialog();
//                                progressDialog.dismiss();
                                SerialSend.updateFirmState = false;
                                Toast.makeText(context, mFrimwareName + "升级成功", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "FOOTN":
                            if (index < mUpdateDataList.size()) {
                                SerialSend.sendAllBytesToSerial(ClassicType.sendUpdata(index, mUpdateDataList.get(index)));
                                SerialSend.getInstance().mOnFirmwareListener.onProgressMessage(true,"正在升级" + mFrimwareName,index + 1,mUpdateDataList.size(),2,mUdIndex);
//                                progressDialog.setProgress(index + 1);
                            } else {
                                SerialSend.sendAllBytesToSerial(ClassicType.sendUdEnd(mBytes));
                                SerialSend.getInstance().mOnFirmwareListener.dismissProgressDialog();
//                                progressDialog.dismiss();
                                SerialSend.updateFirmState = false;
                                Toast.makeText(context, mFrimwareName + "升级成功", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "HEAD":
                            if (index < mUpdateDataList.size()) {
                                SerialSend.sendAllBytesToSerial(LightType.sendUpdata(index, mUpdateDataList.get(index)));
                                SerialSend.getInstance().mOnFirmwareListener.onProgressMessage(true,"正在升级" + mFrimwareName,index + 1,mUpdateDataList.size(),2,mUdIndex);
//                                progressDialog.setProgress(index + 1);
                            } else {
                                SerialSend.sendAllBytesToSerial(LightType.sendUdEnd(mBytes));
                                SerialSend.getInstance().mOnFirmwareListener.dismissProgressDialog();
//                                progressDialog.dismiss();
                                SerialSend.updateFirmState = false;
                                Toast.makeText(context, mFrimwareName + "升级成功", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "F407":
                            if (index < mUpdateDataList.size()) {
                                SerialSend.sendAllBytesToSerial(MainCtl.sendUpdata(index, mUpdateDataList.get(index)));
                                SerialSend.getInstance().mOnFirmwareListener.onProgressMessage(true,"正在升级" + mFrimwareName,index + 1,mUpdateDataList.size(),2,mUdIndex);
//                                progressDialog.setProgress(index + 1);
                            } else {
                                SerialSend.sendAllBytesToSerial(MainCtl.sendUdEnd(mBytes));
                                SerialSend.getInstance().mOnFirmwareListener.dismissProgressDialog();
//                                progressDialog.dismiss();
                                SerialSend.updateFirmState = false;
                                Toast.makeText(context, mFrimwareName + "升级成功", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        default:
                            break;
                    }
                }
            }

        }
    }

    /**
     * 请求最新版本，并判断前面是否有请求过MOTOR
     */
    private static void searchNewVersion() {
        serverIndex++;
        if (serverIndex < mBeanList.size()) {//根据取得的多少个固件的当前版本去请求固件的最新版本
            String name = mBeanList.get(serverIndex).getFrimwareName();
            if (name.contains("MOTOR")) {
                if (!searchedMotor) {
                    getNewestVersion(name);
                } else {
                    mBeanList.get(serverIndex).setFWNewestBean(mFwNewestBean);
                    searchNewVersion();
                }
            } else {
                getNewestVersion(name);
            }
        } else {
//            v.showInfo(mBeanList);
//            v.dissMissProgressDialog();//隐藏dialog
            SerialSend.getInstance().mOnFirmwareListener.onShowInfo(mBeanList);
            SerialSend.getInstance().mOnFirmwareListener.dismissProgressDialog();
            SerialSend.updateFirmState=false;
        }
    }


}
