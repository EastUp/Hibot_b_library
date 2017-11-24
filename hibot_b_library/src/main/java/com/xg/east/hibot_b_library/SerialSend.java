package com.xg.east.hibot_b_library;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import com.ivsign.android.IDCReader.IDCReaderSDK;
import com.xg.east.hibot_b_library.action.HandAction;
import com.xg.east.hibot_b_library.entity.FirmWareBean;
import com.xg.east.hibot_b_library.face_utils.FaceUtils;
import com.xg.east.hibot_b_library.id_card_discern.AppConfig;
import com.xg.east.hibot_b_library.id_card_discern.CopyFileToSD;
import com.xg.east.hibot_b_library.id_card_discern.NationDeal;
import com.xg.east.hibot_b_library.model.FirmModel;
import com.xg.east.hibot_b_library.service.CommunicateService;
import com.xg.east.hibot_b_library.service.usbSendType.ClassicType;
import com.xg.east.hibot_b_library.service.usbSendType.EleType;
import com.xg.east.hibot_b_library.service.usbSendType.LightType;
import com.xg.east.hibot_b_library.service.usbSendType.MotorPType;
import com.xg.east.hibot_b_library.service.utils.FormatChange;
import com.xg.east.hibot_b_library.service.utils.ServiceUtils;
import com.xg.east.hibot_b_library.utils.ShowLog;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static android.content.Context.BIND_AUTO_CREATE;
import static com.xg.east.hibot_b_library.action.HandAction.allStepToZero;

/**
 * 项目名称：Test
 * 创建人：East
 * 创建时间：2017/4/7 11:34
 * 邮箱：EastRiseWM@163.com
 */

public class SerialSend {
    private Context mContext;
    private static SerialSend sSerialSend;
    private final String TAG = this.getClass().getSimpleName();
    private Handler mHandler, mHandlerResolve;
    private Handler mHandlerSearch;
    private static Handler mHandler2 = new Handler();
    private HandlerThread mSonHanThr;
    private Handler mSonHandlerCheck;
    static boolean isBind = false;
    public static boolean isReceive = true;//是否收到结束命令
    //    private boolean isCrashResolve = false;//前后是否碰撞、是否执行了机身禁止的命令
    private static Communicate mCommunicate;/*aidl*/

    private int lowEle = 0;//查询几次出现电池电量低的情况
    private boolean eleWarn = false;//低电量提醒，是否显示了低电量状态。
    private int batterySearchCount = 10;/*查询电量的次数*/

    public static int mARDgree = 0, mBRDgree = 0;/*头部两个电机的角度*/
    public static int mAMaxDegree = 1900;
    private boolean isLeftHand = false, isRightHand = false;//4个传感器的必须变化一次才能再次触发// ;
    public boolean isDancing = false;/*表演时间*/
    public boolean notResumeCAIYIShow = false;
    public boolean isMusicFinish = true;
    public ExecutorService mCachedThreadPool;//线程池，根据程序的运行情况自动来调整线程池中的线程数量
    private static boolean mHasUsbPermission = false;/*是否已经获得了USB权限*/
    private OnHardwareListener mOnHardwareListener;//硬件信息监听器
    public boolean forwardLeftCrash = false, forwardRightCrash = false, backCrash = false;//碰撞
//    private boolean voiceAwaken = false, headTouch = false, leftHandTouch = false, rightHandTouch = false;//是否唤醒、头部触摸、左右手触摸
    private boolean batteryWarning = false;//低电量警告
    private boolean firstFindUsb = true;//是否第一次开机的检测到usb
    public static boolean isActionFinish = true;
    public static boolean lockClassic = false;//是否锁住底盘
    public static boolean brush = true;//底盘是否是有刷直流电机
    private int getReceiveCount = 0;//记录接收到结束帧的次数
    private boolean getClassicData = false;//是否收到了底盘查询的指令
    public static Object lock = new Object();//给动作上的锁
    public double mInfrared1;
    public double mInfrared2;
    public double mInfrared3;
    public static boolean updateFirmState = false;//是否正在固件升级
    private FirmModel mFirmModel;
    public OnFirmwareListener mOnFirmwareListener;
    String[] decodeInfo = new String[10];//身份证信息
    int Readflage = -99;
    boolean isAppRun = false;//是否还在运行app
    private static int flagConStauts=0;//是否连接的标志
    private SerialSend() {
    }

    //使用单例模式
    public static SerialSend getInstance() {
        if (sSerialSend == null) {
            sSerialSend = new SerialSend();
        }
        return sSerialSend;
    }

    public void init(final Context context) {
        isAppRun=true;
//        CopyFileToSD cFileToSD = new CopyFileToSD();
//        cFileToSD.initDB(context);

        //判断服务服务是否已经存在
        ServiceUtils.killRunningService(context,"com.xg.east.hibot_b_library.service.CommunicateService");

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
        mContext = context;
        mFirmModel = new FirmModel(mContext);
        HandlerThread handlerThread = new HandlerThread("dance");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());
        HandlerThread handlerThread1=new HandlerThread("search");
        handlerThread1.start();
        mHandlerSearch=new Handler(handlerThread1.getLooper());
        mSonHanThr=new HandlerThread("checkSav");
        mSonHanThr.start();
        mSonHandlerCheck=new Handler(mSonHanThr.getLooper());
        mHandlerResolve = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 1) {
                    byte[] bytes = (byte[]) msg.obj;
                    try {
                        resolve(bytes);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });
        final Intent intent = new Intent(context, CommunicateService.class);
        context.bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        mCachedThreadPool = Executors.newCachedThreadPool();
        mSonHandlerCheck.post(new Runnable() {
            @Override
            public void run() {
                Runnable checkRun=this;
                if (flagConStauts>10) {
                    ServiceUtils.killRunningService(context,"com.xg.east.hibot_b_library.service.CommunicateService");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    context.bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                    mSonHandlerCheck.postDelayed(this,3000);
                }else{
                    mSonHandlerCheck.post(checkRun);
                }
            }
        });
//        //服务保活
//        mHandler2.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if(isAppRun){
//                    if(!ServiceUtils.isServiceRunning(mContext,"com.xg.east.hibot_b_library.service.CommunicateService")){
//                        mContext.bindService(intent, serviceConnection, BIND_AUTO_CREATE);
//                        mHandler2.postDelayed(this,2000);
//                    }else{
//                        mHandler2.postDelayed(this,1000);
//                    }
//                }
//            }
//        },2000);
    }

    /*初始化SDK*/
    public void init(final Context context, OnHardwareListener hardwareListener) {
        isAppRun=true;
//        CopyFileToSD cFileToSD = new CopyFileToSD();
//        cFileToSD.initDB(context);

        //判断服务服务是否已经存在,并杀掉
        ServiceUtils.killRunningService(context,"com.xg.east.hibot_b_library.service.CommunicateService");

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(5000L, TimeUnit.MILLISECONDS)
                .readTimeout(5000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
        mOnHardwareListener = hardwareListener;
        mContext = context;
        HandlerThread handlerThread = new HandlerThread("dance");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());
        HandlerThread handlerThread1=new HandlerThread("search");
        handlerThread1.start();
        mHandlerSearch=new Handler(handlerThread1.getLooper());
        mSonHanThr=new HandlerThread("checkSav");
        mSonHanThr.start();
        mSonHandlerCheck=new Handler(mSonHanThr.getLooper());
        mHandlerResolve = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 1) {
                    byte[] bytes = (byte[]) msg.obj;
                    try {
                        resolve(bytes);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });
        final Intent intent = new Intent(context, CommunicateService.class);
        context.bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        mCachedThreadPool = Executors.newCachedThreadPool();
        mSonHandlerCheck.post(new Runnable() {
            @Override
            public void run() {
                Runnable checkRun=this;
                if (flagConStauts>10) {
                    ServiceUtils.killRunningService(context,"com.xg.east.hibot_b_library.service.CommunicateService");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    context.bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                    mSonHandlerCheck.postDelayed(this,3000);
                }else{
                    mSonHandlerCheck.post(checkRun);
                }
            }
        });
        //服务保活
//        mHandler2.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if(isAppRun){
//                    if(!ServiceUtils.isServiceRunning(mContext,"com.xg.east.hibot_b_library.service.CommunicateService")){
//                        mContext.bindService(intent, serviceConnection, BIND_AUTO_CREATE);
//                        mHandler2.postDelayed(this,2000);
//                    }else{
//                        mHandler2.postDelayed(this,1000);
//                    }
//                }
//            }
//        },2000);
    }

    public void setOnHardwareListener(OnHardwareListener hardwareListener) {
        mOnHardwareListener = hardwareListener;
    }


    /**
     * 释放SDK
     */
    public void release() {
        isAppRun=false;
        if (FaceUtils.hasInstance()) {
            FaceUtils.getInstance().release();
        }
        if (isBind) {
            try {
                mCommunicate.unregisterCallBack(mICallBack);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mContext.unbindService(serviceConnection);
            isBind = false;
        }
    }


    /**
     * 处理数据返回的结果
     */
    private ICallBack.Stub mICallBack = new ICallBack.Stub() {
        @Override
        public void receiveData(final USEntity ue) throws RemoteException {
            flagConStauts=0;
            Message msg = mHandlerResolve.obtainMessage();
            msg.what = 1;
            msg.obj = ue.getBytes();
            mHandlerResolve.sendMessage(msg);
        }

        @Override
        public void receiveEndOrder() throws RemoteException {
            if (getReceiveCount < 3) {
                getReceiveCount++;
            } else if (getReceiveCount == 3) {
                if (!getClassicData)
                    brush = false;
            }
            mHandler2.removeCallbacks(changeEndRun);//取消1000ms以后IsReceive=true的线程
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isReceive = true;
        }

        @Override
        public void hasUsbPermission() throws RemoteException {
            mHasUsbPermission = true;
            if (firstFindUsb/* && !hasOpenedSearchRunnable*/) {
                firstFindUsb = false;
                byte[] bytes = EleType.onPower();//给电机上电
                sendAllBytesToSerial(bytes);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                allStepToZero();
//                hasOpenedSearchRunnable = true;
                mHandlerSearch.postDelayed(mRunnable, 1000);
            }
        }

        @Override
        public void disConnectUsb() throws RemoteException {
            mHasUsbPermission = false;
        }
    };


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isBind = true;
            mCommunicate = Communicate.Stub.asInterface(service);
            try {
                mCommunicate.registerCallBack(mICallBack);
            } catch (Exception e) {
                e.printStackTrace();
                //Log.e(TAG, e.getMessage());
            }
            try {
                mCommunicate.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
            mHasUsbPermission=false;
//            Intent intent = new Intent(mContext, CommunicateService.class);
//            mContext.bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        }
    };

    /**
     * 每隔300ms改变isReceive
     */
    private static Runnable changeEndRun = new Runnable() {
        @Override
        public void run() {
            isReceive = true;
        }
    };

    Runnable mRunnable = new Runnable() {//一直查询电池和底盘的状态
        @Override
        public void run() {
            if (mHasUsbPermission && isReceive && !updateFirmState) {
                byte[] bytes = EleType.getBatteryData();//电池的状态
                byte[] bytes1 = ClassicType.getClassicData();//底盘的状态
                byte[] bytes2 = LightType.headStatus();//头部数据的状态
                byte[] bytes3 = MotorPType.stepMpStatus(0x010A);//头部数据的状态
                byte[] bytes4 = MotorPType.stepMpStatus(0x010B);//头部数据的状态
                byte[] bytes5 = LightType.getUWBData();//获取 uwb A数据
                byte[] bytes6 = LightType.getUWBDataC();//获取 uwb C数据
                sendAllBytesToSerial(bytes, bytes1, bytes2, bytes3, bytes4, bytes5, bytes6);
                mHandlerSearch.postDelayed(this, 300);
            } else {
                mHandlerSearch.post(this);
            }
        }
    };

    /*拼凑在一起发送*/
    public static void sendAllBytesToSerial(final byte[]... listBytes) {
        flagConStauts++;
        if (isBind&&mHasUsbPermission) {
            isReceive = false;
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
            try {
                mHandler2.postDelayed(changeEndRun, 2000);
                mCommunicate.sendToSerial(new USEntity(sorce));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void searchStatusInSleepTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析返回来的参数(低位前，高位后)
     */
    private void resolve(byte[] bytes) {
        String s = FormatChange.ByteArrToHex(bytes);
        int aByte = (int) bytes[9];
        int a = aByte > 0 ? aByte : (0 - aByte);
        String targetId1 = FormatChange.intToHexString(bytes[6], 1);
        String targetId2 = FormatChange.intToHexString(bytes[7], 1);
        String targetId = targetId2 + targetId1;//哪个电击返回的结果
        String type1 = FormatChange.intToHexString(bytes[10], 1);
        String type2 = FormatChange.intToHexString(bytes[11], 1);//type2+type1=帧类型
        String type = type2 + type1;
        String s1 = FormatChange.intToHexString(bytes[12], 1);
        String s2 = FormatChange.intToHexString(bytes[13], 1);//s2+s1=帧编号
        String s3 = s2 + s1;
        if ((type).equalsIgnoreCase("00A0") && (s3).equalsIgnoreCase("00A0")) {//进入Bootloader模式设备请求发送数据包
            String head = s.substring(30, 32) + s.substring(28, 30);
            String end = s.substring(44, 46) + s.substring(42, 44);
            String indexData1 = s.substring(34, 36);
            String indexData2 = s.substring(36, 38);
            if (head.equalsIgnoreCase("f77f") && end.equalsIgnoreCase("7557")) {
                int indexData = Integer.parseInt(indexData2 + indexData1, 16);//获取需要发送第几包数据
                int identification = Integer.parseInt(s.substring(38, 40), 16);//标识
                ShowLog.v("Index", "包数：" + indexData1 + "|" + indexData2 + "|" + indexData);
                if (FirmModel.isFirstReceiveUp) {//是否刚刚初始化就处于bootloader模式
                    updateFirmState = true;
                    FirmModel.getSendIndexDataOnFirst(mContext, targetId, indexData, identification);
                } else {
                    FirmModel.getSendIndexData(mContext, targetId, indexData, identification);
                }
            }
        } else if (((type).equalsIgnoreCase("0001") && (s3).equalsIgnoreCase("000A"))
                || (targetId.equals("0200") && (type).equalsIgnoreCase("0002") && (s3).equalsIgnoreCase("0003"))
                || (brush && targetId.equals("0300") && (type).equalsIgnoreCase("0003") && (s3).equalsIgnoreCase("0006"))
                || (!brush && targetId.equals("0500") && (type).equalsIgnoreCase("0003") && (s3).equalsIgnoreCase("0006"))
                || (targetId.equals("0400") && (type).equalsIgnoreCase("0004") && (s3).equalsIgnoreCase("0005"))
                || (targetId.equalsIgnoreCase("00FC") && (type).equalsIgnoreCase("0004") && (s3).equalsIgnoreCase("0005"))) {//固件版本返回
            String version1 = s.substring(28, 30);
            String version2 = s.substring(30, 32);
            int version = Integer.parseInt(version2 + version1, 16);
            FirmModel.getVersion(targetId, version);
        } else if ((type).equalsIgnoreCase("0001") && (s3).equalsIgnoreCase("0002")) {/*步进电机状态的读取*/
            String trouble1 = s.substring(34, 36);
            String trouble2 = s.substring(36, 38);
            String trouble = trouble2 + trouble1;/*故障编号*/
            int troubleIndex = Integer.parseInt(trouble, 16);
            String aRDgress1 = s.substring(42, 44);
            String aRDgress2 = s.substring(44, 46);
            Integer integer = Integer.valueOf(aRDgress2 + aRDgress1, 16);
            if (integer >= 32768)
                integer -= 65536;
            Integer dgree = integer;/*实际角度*/
            switch (targetId) {
                case "010a":
                    mARDgree = dgree;
                    //Log.v("degreeA", dgree + "||" + targetId);
                    break;
                case "010b":
                    mBRDgree = dgree;
                    //Log.v("degreeB", dgree + "" + "||" + targetId);
                    break;
            }
//            if (troubleIndex != 0 && troubleIndex != 7) {
////                //Log.e(TAG, targetId + "|||" + troubleIndex);
//                isTrouble = true;
//                MotorBean motorBean = new MotorBean();
//                motorBean.setTargetId(targetId);
//                motorBean.setDegree(dgree);
//                motorBean.setTrouble(troubleIndex);
//                mTroubleList.add(motorBean);
//            }
        } else if (targetId.equals("0200") && (type).equalsIgnoreCase("0002") && (s3).equalsIgnoreCase("0002")) {//电池状态
            String dyStr = s.substring(28, 32);//电池当前电压
            String dlStr = s.substring(32, 36);//电池输出电流
            String dlcdStr = s.substring(36, 40);//电池充电电流
            String rlStr = s.substring(40, 44);//电池额定容量
            String eddyStr = s.substring(44, 48);//电池额定电压
            String sydlPercentStr0 = s.substring(48, 50);//剩余电量百分比
            String sydlPercentStr1 = s.substring(50, 52);//剩余电量百分比
            String sydlPercentStr = sydlPercentStr1 + sydlPercentStr0;//剩余电量百分比
            String cdqStr = s.substring(52, 54);//当前是否连接充电器
            int mIsCharge = Integer.parseInt(cdqStr, 16);
            int parseInt = Integer.parseInt(sydlPercentStr, 16);
            if (parseInt / 100 <= 30) {
                if (lowEle < 5) {
                    lowEle++;
                } else {
                    batteryWarning = true;
                    lowEle = 0;
//                    mContext.sendBroadcast(new Intent(BATTERYWARN));
                }
            } else {
                batteryWarning = false;
            }
            String batteryLevel = (parseInt / 100) + "%";
            if (mOnHardwareListener != null)
                mOnHardwareListener.onBattery(batteryLevel, batteryWarning);
        }else if (targetId.equals("0200") && (type).equalsIgnoreCase("0002") && (s3).equalsIgnoreCase("000b")) {//身份证
            byte[] bytes1=new byte[bytes.length-18];
            System.arraycopy(bytes,14,bytes1,0,bytes1.length);
            String dataStr=FormatChange.ByteArrToHex(bytes1);
            Log.v("resultCard", FormatChange.ByteArrToHex(bytes1));
//            Log.v("resultCard", "bytes1：----"+FormatChange.ByteArrToHex(bytes));
            Log.d("infoLenth",Integer.parseInt(dataStr.substring(20,24),16)+"");
            byte[] bytes2=new byte[Integer.parseInt(dataStr.substring(20,24),16)];
            System.arraycopy(bytes1,14,bytes2,0,bytes2.length);
            try {
                String TmpStr = new String(bytes2, "UTF16-LE");
                TmpStr = new String(TmpStr.getBytes("UTF-8"));
                decodeInfo[0] = TmpStr.substring(0, 15);
                decodeInfo[1] = TmpStr.substring(15, 16);
                decodeInfo[2] = TmpStr.substring(16, 18);
                decodeInfo[3] = TmpStr.substring(18, 26);
                decodeInfo[4] = TmpStr.substring(26, 61);
                decodeInfo[5] = TmpStr.substring(61, 79);
                decodeInfo[6] = TmpStr.substring(79, 94);
                decodeInfo[7] = TmpStr.substring(94, 102);
                decodeInfo[8] = TmpStr.substring(102, 110);
                decodeInfo[9] = TmpStr.substring(110, 128);
                if (decodeInfo[1].equals("1"))
                    decodeInfo[1] = "男";
                else
                    decodeInfo[1] = "女";
                try {
                    int code = Integer.parseInt(decodeInfo[2]
                            .toString());
                    Log.d("info","code="+code+"---decodeInfo[2]="+decodeInfo[2]);
                    decodeInfo[2] = NationDeal.decodeNation(code);
                    Log.d("info","2code="+code+"---decodeInfo[2]="+decodeInfo[2]+"--"+NationDeal.decodeNation(code));
                } catch (Exception e) {
                    Log.d("info",e.getMessage());
                    decodeInfo[2] = "";
                }
                for (int i = 0; i < decodeInfo.length; i++) {
                    Log.d("info","decodeInfo["+i+"]"+decodeInfo[i]);
                }

                // 照片解码
                try {
                    int ret = IDCReaderSDK.Init();
                    if (ret == 0){
                        byte[] datawlt = new byte[1384];
                        byte[] byLicData = { (byte) 0x05,
                                (byte) 0x00, (byte) 0x01,
                                (byte) 0x00, (byte) 0x5B,
                                (byte) 0x03, (byte) 0x33,
                                (byte) 0x01, (byte) 0x5A,
                                (byte) 0xB3, (byte) 0x1E,
                                (byte) 0x00 };
                        for (int i = 0; i < 1295; i++) {
                            datawlt[i] = bytes1[i];
                        }
                        int t = IDCReaderSDK.unpack(datawlt,
                                byLicData);
                        if (t == 1) {
                            Readflage = 1;// 读卡成功
                        } else {
                            Readflage = 6;// 照片解码异常
                        }
                    } else {
                        Readflage = 6;// 照片解码异常
                    }
                } catch (Exception e) {
                    Readflage = 6;// 照片解码异常
                }
                if(Readflage > 0) {
                    if (Readflage == 1) {
                        FileInputStream fis = new FileInputStream(
                                Environment.getExternalStorageDirectory()
                                        + "/wltlib/zp.bmp");
                        Bitmap bmp = BitmapFactory.decodeStream(fis);
                        fis.close();
                        mOnHardwareListener.onIDCard(bmp,decodeInfo[0],decodeInfo[1],decodeInfo[2],decodeInfo[3],decodeInfo[4],decodeInfo[5],decodeInfo[6],decodeInfo[7],decodeInfo[8],decodeInfo[9],null);
                    } else {
                        mOnHardwareListener.onIDCard(null,decodeInfo[0],decodeInfo[1],decodeInfo[2],decodeInfo[3],decodeInfo[4],decodeInfo[5],decodeInfo[6],decodeInfo[7],decodeInfo[8],decodeInfo[9],"照片解码失败，请检查路径"
                                + AppConfig.RootFile);
                    }
                }else{
                    if (Readflage == -2) {
                        mOnHardwareListener.onIDCard(null,null,null,null,null,null,null,null,null,null,null,"连接异常");
                    }
                    if (Readflage == -3) {
                        mOnHardwareListener.onIDCard(null,null,null,null,null,null,null,null,null,null,null,"无卡或卡片已读过");
                    }
                    if (Readflage == -4) {
                        mOnHardwareListener.onIDCard(null,null,null,null,null,null,null,null,null,null,null,"无卡或卡片已读过");
                    }
                    if (Readflage == -5) {
                        mOnHardwareListener.onIDCard(null,null,null,null,null,null,null,null,null,null,null,"读卡失败");
                    }
                    if (Readflage == -99) {
                        mOnHardwareListener.onIDCard(null,null,null,null,null,null,null,null,null,null,null,"操作异常");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (targetId.equals("0200") && (type).equalsIgnoreCase("0002") && (s3).equalsIgnoreCase("000e")){
            Log.i(TAG,"resolse IC卡 识别 ===>"+s);
            byte[] data = new byte[bytes.length - 18];
            for (int i = 0; i < data.length; i++) {
                data[i] = bytes[i+14];
            }
            mOnHardwareListener.onICCard(data);
        } else if (targetId.equals("0300") && (type).equalsIgnoreCase("0003") && (s3).equalsIgnoreCase("0005")) {//底盘模块数据反馈
            getClassicData = true;
            //Log.i("classicInfo",s);
            String statusStr = s.substring(28, 30);//旋转状态（0：没有角度旋转，1：正在角度旋转）
            String leftSpeedStr = s.substring(32, 34) + s.substring(30, 32);//底盘模块左轮速度
            String rightSpeedStr = s.substring(36, 38) + s.substring(34, 36);//底盘模块右轮速度
            String runStatusStr = s.substring(38, 40);//底盘模块当前运动状态
            String oneStr = s.substring(40, 42);//壁障红外左距离
            String twoStr = s.substring(42, 44);//壁障红外中距离
            String threeStr = s.substring(44, 46);//壁障红外右距离
            String chargeRedLeft = s.substring(48, 50) + s.substring(46, 48);//充电红外左距离
            String chargeRedRight = s.substring(52, 54) + s.substring(50, 52);//充电红外右距离
            String chargeRedBackLeft = s.substring(56, 58) + s.substring(54, 56);//充电红外后左距离
            String chargeRedBackRight = s.substring(60, 62) + s.substring(58, 60);//充电红外后右距离
            String Str1 = s.substring(64, 66) + s.substring(62, 64);//超声波左距离
            String Str2 = s.substring(68, 70) + s.substring(66, 68);//超声波中距离
            String Str3 = s.substring(72, 74) + s.substring(70, 72);//超声波右距离
            String crash1 = s.substring(74, 76);//碰撞开关左
            String crash2 = s.substring(76, 78);//碰撞开关右
            String crash3 = s.substring(78, 80);//碰撞开关后
            String pitchStr = s.substring(82, 84) + s.substring(80, 82);//pitch角
            String yawStr = s.substring(86, 88) + s.substring(84, 86);//roll角
            String rollStr = s.substring(90, 92) + s.substring(88, 90);//yaw角
            int eBAR=Integer.parseInt(s.substring(92,94),16);//障碍返回状态
           /*
                    eBAR_NULL = 0,//前面遇到障碍
                    eBAR_FORW = 1,//前面遇到障碍
                    eBAR_BACK = 2,//后面遇到障碍
                    eBAR_LEFT = 3,//左边遇到障碍
                    eBAR_RIGHT = 4,//右边遇到障碍*/
            int staus = Integer.parseInt(statusStr, 16);
            if (mOnHardwareListener != null) {
                if (staus == 1) {
                    mOnHardwareListener.onRotate(true);/*获取当前的状态是否在旋转*/
                } else if (staus == 0) {
                    mOnHardwareListener.onRotate(false);
                }
            }
            int c1 = Integer.parseInt(crash1, 16);
            int c2 = Integer.parseInt(crash2, 16);
            int c3 = Integer.parseInt(crash3, 16);
            //Log.v("SerialSend", staus + "--" + c1 + "--" + c2 + "--" + c3);

            double leftWheelSpeed = (double) Integer.parseInt(leftSpeedStr, 16);
            double rightWheelSpeed = (double) Integer.parseInt(rightSpeedStr, 16);

            double mCsb = (double) Integer.parseInt(Str1, 16) / (double) 10;
            double mCsb2 = (double) Integer.parseInt(Str2, 16) / (double) 10;
            double mCsb3 = (double) Integer.parseInt(Str3, 16) / (double) 10;

            double infrared1 = (double) Integer.parseInt(oneStr, 16);
            double infrared2 = (double) Integer.parseInt(twoStr, 16);
            double infrared3 = (double) Integer.parseInt(threeStr, 16);
            if (c1 == 1) {
                forwardLeftCrash = true;
//                isCrashResolve = false;
            } else if (c1 == 0) {
                forwardLeftCrash = false;
            }
            if (c2 == 1) {
                forwardRightCrash = true;
//                isCrashResolve = false;
            } else if (c2 == 0) {
                forwardRightCrash = false;
            }
            if (c3 == 1) {
                backCrash = true;
//                isCrashResolve = false;
            } else if (c3 == 0) {
                backCrash = false;
            }
            double pitch = (double) Integer.parseInt(pitchStr, 16) / (double) 100;
            double yaw = (double) Integer.parseInt(yawStr, 16) / (double) 100;
            double roll = (double) Integer.parseInt(rollStr, 16) / (double) 100;
            if (mOnHardwareListener != null) {
                mOnHardwareListener.onLeftRightWheelSpeed(leftWheelSpeed, rightWheelSpeed);
                mOnHardwareListener.onUltrasonic(mCsb, mCsb2, mCsb3);
                mOnHardwareListener.onInfrared(infrared1, infrared2, infrared3);
                mOnHardwareListener.onCrash(eBAR,forwardLeftCrash, forwardRightCrash, backCrash);
                mOnHardwareListener.onPYR(pitch, yaw, roll);
            }

        } else if (targetId.equals("0500") && (type).equalsIgnoreCase("0003") && (s3).equalsIgnoreCase("0005")) {//底盘模块数据反馈
            //Log.i("classicInfo",s);
            String statusStr = s.substring(28, 30);//旋转状态（0：没有角度旋转，1：正在角度旋转）
            String leftSpeedStr = s.substring(32, 34) + s.substring(30, 32);//底盘模块左轮速度
            String rightSpeedStr = s.substring(36, 38) + s.substring(34, 36);//底盘模块右轮速度
            String runStatusStr = s.substring(38, 40);//底盘模块当前运动状态
            String oneStr = s.substring(40, 42);//壁障红外左距离
            String twoStr = s.substring(42, 44);//壁障红外中距离
            String threeStr = s.substring(44, 46);//壁障红外右距离
            String chargeRedLeft = s.substring(48, 50) + s.substring(46, 48);//充电红外左距离
            String chargeRedRight = s.substring(52, 54) + s.substring(50, 52);//充电红外右距离
            String chargeRedBackLeft = s.substring(56, 58) + s.substring(54, 56);//充电红外后左距离
            String chargeRedBackRight = s.substring(60, 62) + s.substring(58, 60);//充电红外后右距离
            String Str1 = s.substring(64, 66) + s.substring(62, 64);//超声波左距离
            String Str2 = s.substring(68, 70) + s.substring(66, 68);//超声波中距离
            String Str3 = s.substring(72, 74) + s.substring(70, 72);//超声波右距离
            String crash1 = s.substring(74, 76);//碰撞开关左
            String crash2 = s.substring(76, 78);//碰撞开关右
            String crash3 = s.substring(78, 80);//碰撞开关后
            String pitchStr = s.substring(82, 84) + s.substring(80, 82);//pitch角
            String yawStr = s.substring(86, 88) + s.substring(84, 86);//roll角
            String rollStr = s.substring(90, 92) + s.substring(88, 90);//yaw角
            int eBAR=Integer.parseInt(s.substring(92,94),16);//障碍返回状态
           /*
                    eBAR_NULL = 0,//前面遇到障碍
                    eBAR_FORW = 1,//前面遇到障碍
                    eBAR_BACK = 2,//后面遇到障碍
                    eBAR_LEFT = 3,//左边遇到障碍
                    eBAR_RIGHT = 4,//右边遇到障碍*/
            int staus = Integer.parseInt(statusStr, 16);
            if (mOnHardwareListener != null) {
                if (staus == 1) {
                    mOnHardwareListener.onRotate(true);/*获取当前的状态是否在旋转*/
                } else if (staus == 0) {
                    mOnHardwareListener.onRotate(false);
                }
            }
            int c1 = Integer.parseInt(crash1, 16);
            int c2 = Integer.parseInt(crash2, 16);
            int c3 = Integer.parseInt(crash3, 16);
            //Log.v("SerialSend", staus + "--" + c1 + "--" + c2 + "--" + c3);

            double leftWheelSpeed = (double) Integer.parseInt(leftSpeedStr, 16);
            double rightWheelSpeed = (double) Integer.parseInt(rightSpeedStr, 16);

            double mCsb = (double) Integer.parseInt(Str1, 16) / (double) 10;
            double mCsb2 = (double) Integer.parseInt(Str2, 16) / (double) 10;
            double mCsb3 = (double) Integer.parseInt(Str3, 16) / (double) 10;

            mInfrared1 = (double) Integer.parseInt(oneStr, 16);
            mInfrared2 = (double) Integer.parseInt(twoStr, 16);
            mInfrared3 = (double) Integer.parseInt(threeStr, 16);
            if (c1 == 1) {
                forwardLeftCrash = true;
//                isCrashResolve = false;
            } else if (c1 == 0) {
                forwardLeftCrash = false;
            }
            if (c2 == 1) {
                forwardRightCrash = true;
//                isCrashResolve = false;
            } else if (c2 == 0) {
                forwardRightCrash = false;
            }
            if (c3 == 1) {
                backCrash = true;
//                isCrashResolve = false;
            } else if (c3 == 0) {
                backCrash = false;
            }
            double pitch = (double) Integer.parseInt(pitchStr, 16) / (double) 100;
            double yaw = (double) Integer.parseInt(yawStr, 16) / (double) 100;
            double roll = (double) Integer.parseInt(rollStr, 16) / (double) 100;
            if (mOnHardwareListener != null) {
                mOnHardwareListener.onLeftRightWheelSpeed(leftWheelSpeed, rightWheelSpeed);
                mOnHardwareListener.onUltrasonic(mCsb, mCsb2, mCsb3);
                mOnHardwareListener.onInfrared(mInfrared1, mInfrared2, mInfrared3);
                mOnHardwareListener.onCrash(eBAR,forwardLeftCrash, forwardRightCrash, backCrash);
                mOnHardwareListener.onPYR(pitch, yaw, roll);
            }

        } else if (targetId.equals("0400") && (type).equalsIgnoreCase("0004") && (s3).equalsIgnoreCase("0003")) {//头部状态
            String voiceDegree0 = s.substring(28, 30);//音源角度  （0-359度）
            String voiceDegree1 = s.substring(30, 32);//音源角度  （0-359度）
            String voiceDegree = voiceDegree1 + voiceDegree0;
            String voiceDegreeChange = s.substring(32, 34);//音源角度是否改变（0没有改变，1改变）
            String touchSwitch = s.substring(34, 36);//触摸开关（0断开，1闭合）
            String leftHandTouchStr = s.substring(36, 38);//触摸开关是否触发（0没有触发，1触发过）
            String rightHandTouchStr = s.substring(38, 40);//触摸开关是否触发（0没有触发，1触发过）
            //Log.v("HeadStatus", voiceDegree + "--" + voiceDegreeChange + "--" + "--" + touchSwitch + "--" + leftHandTouch+"--"+rightHandTouch);
            int reallyVoiceDegree = Integer.parseInt(voiceDegree, 16);//实际的音源角度
            int voiceChange = Integer.parseInt(voiceDegreeChange, 16);
            int leftHand = Integer.parseInt(leftHandTouchStr, 16);
            int rightHand = Integer.parseInt(rightHandTouchStr, 16);
            if (mOnHardwareListener != null) {
                mOnHardwareListener.onAwaken(voiceChange == 1?true:false, reallyVoiceDegree);
                mOnHardwareListener.onHeadTouch(Integer.parseInt(touchSwitch, 16) == 1?true:false);
                mOnHardwareListener.onHandTouch(leftHand == 1?true:false, rightHand == 1?true:false);
            }
        } else if (targetId.equals("0400") && (type).equalsIgnoreCase("0004") && (s3).equalsIgnoreCase("0008")) {//获取 uwb 数据
            byte[] bytes1 = new byte[63];
            System.arraycopy(bytes, 14, bytes1, 0, 63);
            if (mOnHardwareListener != null) {
                mOnHardwareListener.onUWBDataA(bytes1);/*获取当前的状态是否在旋转*/
            }
            //TODO 处理 uwb 数据
        } else if (targetId.equals("0400") && (type).equalsIgnoreCase("0004") && (s3).equalsIgnoreCase("000a")) {//获取 uwb 数据
            byte[] bytes1 = new byte[63];
            System.arraycopy(bytes, 14, bytes1, 0, 63);
            if (mOnHardwareListener != null) {
                mOnHardwareListener.onUWBDataC(bytes1);/*获取当前的状态是否在旋转*/
            }
            //TODO 处理 uwb 数据
        }
    }


    /*开始跳舞*/
    public void startDance() {
        isDancing = true;
        isMusicFinish = false;
        notResumeCAIYIShow = true;
        Runnable danceRun = getDanceRb(mHandler);
        mCachedThreadPool.execute(danceRun);
    }

    /*停止跳舞*/
    public void stopDance() {
        isMusicFinish = true;
        notResumeCAIYIShow = false;
        showTimeFinish();
    }


    /**
     * 黑豆才艺里面跳舞
     */
    public Runnable getDanceRb(final Handler mHandler) {
        Runnable danceRun = new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                if (isReceive) {
                    HandAction.dance1();
                    HandAction.twoJuQiDianTou();
                    if (!isMusicFinish && notResumeCAIYIShow) {
                        HandAction.twoJuQiDianTou();
                        if (!isMusicFinish && notResumeCAIYIShow) {
                            HandAction.twoJuQiDianTou();
                            if (!isMusicFinish && notResumeCAIYIShow) {
                                HandAction.twoJuQiDianTou();
                                if (!isMusicFinish && notResumeCAIYIShow) {
                                    HandAction.twoJuQiDianTou();
                                    if (!isMusicFinish && notResumeCAIYIShow) {
                                        HandAction.closeChayao();
                                        HandAction.dance2();
                                        HandAction.closeRightUpLeftDown();
                                        danceTogher(/*isMusicFinish, */mHandler);
                                    } else {
                                        closeFirstStep();
                                    }
                                }else{
                                    closeFirstStep();
                                }
                            }else{
                                closeFirstStep();
                            }
                        }else{
                            closeFirstStep();
                        }
                    }else{
                        closeFirstStep();
                    }
//                } else {
//                    mHandler.post(this);
//                }
            }

            /**
             * 关闭跳舞第一步
             */
            private void closeFirstStep() {
                HandAction.closeChayao();
                byte[] eyeControl = LightType.eyeControll(0,
                        0, 0, 255,
                        0, 0, 255,
                        0, 0, 255,
                        0, 0, 255,
                        0, 0, 255,
                        0, 0, 255,
                        0, 0, 255,
                        0, 0, 255);
                byte[] topHeadControl = LightType.topHeadControll(0, 0, 0, 255);
                byte[] earControll = LightType.earControll(0, 0, 0, 255);
//                        //底盘
//                byte[] bytes52 = ClassicType.runWithSpeed(-15, 15, 4);
                byte[] bytes52 = new byte[0];
                sendAllBytesToSerial(eyeControl, topHeadControl, earControll,/*bytesL01, bytesL11, bytesL21, bytesL41, bytesR51, bytesR61, bytesR71, bytesR91,*/ bytes52/*, bytesL31, bytesR81, bytesHA1, bytesHB1*/);
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                showTimeFinish();//跳舞结束了
            }
        };
        return danceRun;
    }


    private void danceTogher(/*final boolean flag,*/ final Handler mHandler) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (!isMusicFinish && notResumeCAIYIShow) {
                    HandAction.dance3();
                    HandAction.closeLeftUpRightDown();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (!isMusicFinish && notResumeCAIYIShow) {
                                HandAction.dance4();
                                HandAction.closeLeftRightUp();
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (!isMusicFinish && notResumeCAIYIShow) {
                                            HandAction.dance5();
                                            HandAction.closeChayao();
                                            mHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (!isMusicFinish && notResumeCAIYIShow) {
                                                        HandAction.dance6();
                                                        HandAction.closeLeftChayaoRightSee();
                                                        mHandler.post(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                if (!isMusicFinish && notResumeCAIYIShow) {
                                                                    HandAction.dance7();
                                                                    HandAction.closeRightChayaoLeftSee();
                                                                    mHandler.post(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            if (!isMusicFinish && notResumeCAIYIShow) {
                                                                                HandAction.dance8();
                                                                                HandAction.closeRightChayaoLeftSee();
                                                                                mHandler.post(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        if (!isMusicFinish && notResumeCAIYIShow) {
                                                                                            HandAction.dance9();
                                                                                            HandAction.closeLeftChayaoRightSee();
                                                                                            mHandler.post(new Runnable() {
                                                                                                @Override
                                                                                                public void run() {
                                                                                                    if (!isMusicFinish && notResumeCAIYIShow) {
                                                                                                        HandAction.dance10();
                                                                                                        HandAction.closeAtemanLeftUp();
                                                                                                        mHandler.post(new Runnable() {
                                                                                                            @Override
                                                                                                            public void run() {
                                                                                                                if (!isMusicFinish && notResumeCAIYIShow) {
                                                                                                                    HandAction.dance11();
                                                                                                                    mHandler.post(new Runnable() {
                                                                                                                        @Override
                                                                                                                        public void run() {
                                                                                                                            if (!isMusicFinish && notResumeCAIYIShow) {
                                                                                                                                HandAction.dance12();
                                                                                                                                mHandler.post(new Runnable() {
                                                                                                                                    @Override
                                                                                                                                    public void run() {
                                                                                                                                        if (!isMusicFinish && notResumeCAIYIShow) {
                                                                                                                                            HandAction.dance13();
                                                                                                                                            HandAction.closeBeforeDanceThree();
                                                                                                                                            mHandler.post(new Runnable() {
                                                                                                                                                @Override
                                                                                                                                                public void run() {
                                                                                                                                                    if (!isMusicFinish && notResumeCAIYIShow) {
                                                                                                                                                        HandAction.dance14();
                                                                                                                                                        HandAction.closeRightUpLeftDown();
                                                                                                                                                        mHandler.post(new Runnable() {
                                                                                                                                                            @Override
                                                                                                                                                            public void run() {
                                                                                                                                                                if (!isMusicFinish && notResumeCAIYIShow) {
                                                                                                                                                                    danceTogher(mHandler);
                                                                                                                                                                } else {
                                                                                                                                                                    //关闭右高左低
                                                                                                                                                                    mHandler.post(new Runnable() {
                                                                                                                                                                        @Override
                                                                                                                                                                        public void run() {
                                                                                                                                                                            if (isReceive) {
                                                                                                                                                                                HandAction.closeRightUpLeftDown(mContext);
                                                                                                                                                                                showTimeFinish();//跳舞结束了
                                                                                                                                                                            } else {
                                                                                                                                                                                mHandler.post(this);
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    });
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        });
                                                                                                                                                    } else {
                                                                                                                                                        //关闭原舞蹈第三步
                                                                                                                                                        mHandler.post(new Runnable() {
                                                                                                                                                            @Override
                                                                                                                                                            public void run() {
                                                                                                                                                                if (isReceive) {
                                                                                                                                                                    HandAction.closeBeforeDanceThree(mContext);
                                                                                                                                                                    showTimeFinish();//跳舞结束了
                                                                                                                                                                } else {
                                                                                                                                                                    mHandler.post(this);
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        });
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            });
                                                                                                                                        } else {
                                                                                                                                            //关闭原舞蹈第二步
                                                                                                                                            mHandler.post(new Runnable() {
                                                                                                                                                @Override
                                                                                                                                                public void run() {
                                                                                                                                                    if (isReceive) {
                                                                                                                                                        HandAction.closeBeforeDanceTwo(mContext);
                                                                                                                                                        showTimeFinish();//跳舞结束了
                                                                                                                                                    } else {
                                                                                                                                                        mHandler.post(this);
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            });
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                });
                                                                                                                            } else {
                                                                                                                                //关闭原舞蹈第一步
                                                                                                                                mHandler.post(new Runnable() {
                                                                                                                                    @Override
                                                                                                                                    public void run() {
                                                                                                                                        if (isReceive) {
                                                                                                                                            HandAction.closeBeforeDanceOne(mContext);
                                                                                                                                            showTimeFinish();//跳舞结束了
                                                                                                                                        } else {
                                                                                                                                            mHandler.post(this);
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                });
                                                                                                                            }
                                                                                                                        }
                                                                                                                    });
                                                                                                                } else {
                                                                                                                    //关闭奥特曼姿势，左边起
                                                                                                                    mHandler.post(new Runnable() {
                                                                                                                        @Override
                                                                                                                        public void run() {
                                                                                                                            if (isReceive) {
                                                                                                                                HandAction.closeAtemanLeftUp(mContext);
                                                                                                                                showTimeFinish();//跳舞结束了
                                                                                                                            } else {
                                                                                                                                mHandler.post(this);
                                                                                                                            }
                                                                                                                        }
                                                                                                                    });
                                                                                                                }
                                                                                                            }
                                                                                                        });
                                                                                                    } else {
                                                                                                        //关闭左前右后
                                                                                                        mHandler.post(new Runnable() {
                                                                                                            @Override
                                                                                                            public void run() {
                                                                                                                if (isReceive) {
                                                                                                                    HandAction.closeLeftQianRightHou(mContext);
                                                                                                                    showTimeFinish();//跳舞结束了
                                                                                                                } else {
                                                                                                                    mHandler.post(this);
                                                                                                                }
                                                                                                            }
                                                                                                        });
                                                                                                    }
                                                                                                }
                                                                                            });
                                                                                        } else {
                                                                                            //关闭右前左后
                                                                                            mHandler.post(new Runnable() {
                                                                                                @Override
                                                                                                public void run() {
                                                                                                    if (isReceive) {
                                                                                                        HandAction.closeRightQianLeftHou(mContext);
                                                                                                        showTimeFinish();//跳舞结束了
                                                                                                    } else {
                                                                                                        mHandler.post(this);
                                                                                                    }
                                                                                                }
                                                                                            });
                                                                                        }
                                                                                    }
                                                                                });
                                                                            } else {
                                                                                //关闭右手叉腰左边看
                                                                                mHandler.post(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        if (isReceive) {
                                                                                            HandAction.closeRightChayaoLeftSee(mContext);
                                                                                            showTimeFinish();//跳舞结束了
                                                                                        } else {
                                                                                            mHandler.post(this);
                                                                                        }
                                                                                    }
                                                                                });
                                                                            }
                                                                        }
                                                                    });
                                                                } else {
                                                                    //关闭左手叉腰右边看
                                                                    mHandler.post(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            if (isReceive) {
                                                                                HandAction.closeLeftChayaoRightSee(mContext);
                                                                                showTimeFinish();//跳舞结束了
                                                                            } else {
                                                                                mHandler.post(this);
                                                                            }
                                                                        }
                                                                    });
                                                                }
                                                            }
                                                        });
                                                    } else {
                                                        //关闭叉腰
                                                        mHandler.post(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                if (isReceive) {
                                                                    HandAction.closeChayao(mContext);
                                                                    showTimeFinish();//跳舞结束了
                                                                } else {
                                                                    mHandler.post(this);
                                                                }
                                                            }
                                                        });
                                                    }
                                                }
                                            });
                                        } else {
                                            //关闭一起高
                                            mHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (isReceive) {
                                                        HandAction.closeLeftRightUp(mContext);
                                                        showTimeFinish();//跳舞结束了
                                                    } else {
                                                        mHandler.post(this);
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });
                            } else {
                                //关闭左高右低
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (isReceive) {
                                            HandAction.closeLeftUpRightDown(mContext);
                                            showTimeFinish();//跳舞结束了
                                        } else {
                                            mHandler.post(this);
                                        }
                                    }
                                });
                            }
                        }
                    });
                } else {
                    //关闭右高左低
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (isReceive) {
                                HandAction.closeRightUpLeftDown(mContext);
                                showTimeFinish();//跳舞结束了
                            } else {
                                mHandler.post(this);
                            }
                        }
                    });
                }
            }
        });
    }

    /**
     * 跳舞结束
     */
    private void showTimeFinish() {
        /*跳舞结束*/
        isDancing = false;
    }

    /**
     * 查询数据结果的返回
     */
    public interface OnHardwareListener {
        /**
         * @param batteryLevel   电池电量
         * @param batteryWarning 低电量提醒
         */
        void onBattery(String batteryLevel, boolean batteryWarning);

        /**
         * @param isRotate 底盘是否还在旋转
         */
        void onRotate(boolean isRotate);

        /**
         * @param left  左轮速度
         * @param right 右轮速度
         */
        void onLeftRightWheelSpeed(double left, double right);

        /**
         * @param left   左边红外
         * @param middle 中间红外
         * @param right  右边红外
         */
        void onInfrared(double left, double middle, double right);

        /**
         * @param left   左边超声波
         * @param middle 中间超声波
         * @param right  右边超声波
         */
        void onUltrasonic(double left, double middle, double right);

        /**
         * @param pitch 底盘的pitch角
         * @param yaw   底盘的yaw角
         * @param roll  底盘的roll角
         */
        void onPYR(double pitch, double yaw, double roll);

        /**
         *
         * @param eBAR  0,//没有遇到障碍
         *               1,//前面遇到障碍
         *               2,//后面遇到障碍
         *              3,//左边遇到障碍
         *              4,//右边遇到障碍
         * @param forwardLeftCrash  左前是否碰撞
         * @param forwardRightCrash 右前是否碰撞
         * @param backCrash         后面是否碰撞
         */
        void onCrash(int eBAR, boolean forwardLeftCrash, boolean forwardRightCrash, boolean backCrash);

        /**
         * @param headTouch 头部是否被触摸
         */
        void onHeadTouch(boolean headTouch);

        /**
         * @param awaken      是否被唤醒
         * @param voiceDegree 声源的角度
         */
        void onAwaken(boolean awaken, int voiceDegree);

        /**
         * @param leftHandTouch  左手是否被触摸
         * @param rightHandTouch 右手是否被触摸
         */
        void onHandTouch(boolean leftHandTouch, boolean rightHandTouch);

        /**
         * @param data 获取底盘的uwb A帧数据
         */
        void onUWBDataA(byte[] data);

        /**
         * @param data 获取底盘的uwb C帧数据
         */
        void onUWBDataC(byte[] data);

        /**
         * @param bitmap    身份证头像
         * @param name      姓名
         * @param sex       性别
         * @param nation    民族
         * @param birthdate 出生日期
         * @param address   住址
         * @param IDNumber  身份证号码
         * @param idIssued  签发机关
         * @param exDateStart   有效日期的起始日期
         * @param exDateEnd     有效日期的结束日期
         * @param newAddress    最新住址
         * @param errorMsg      错误信息（包括照片解码异常，读卡失败等信息，一般为空）
         */
        void onIDCard(Bitmap bitmap,String name,String sex,String nation,String birthdate,String address,String IDNumber,String idIssued,String exDateStart,String exDateEnd,String newAddress,String errorMsg);

        /**
         * IC卡的数据返回
         * @param data 读取IC卡返回回来的数据
         */
        void onICCard(byte[] data);
    }

    /**
     * 获取固件版本号
     */
    public void getFirmwareVersion() {
        updateFirmState = true;//进入固件升级模式
        mFirmModel.getVersionSendData();
    }

    /**
     * 开始固件升级
     */
    public void startUpdateFirmware(List<FirmWareBean> firmWareBeanList) {
        updateFirmState = true;//进入固件升级模式
        mFirmModel.updateFw(firmWareBeanList);
    }


    /**
     * 设置固件升级监听器
     *
     * @param onFirmwareListener
     */
    public void setOnFirmwareListener(OnFirmwareListener onFirmwareListener) {
        this.mOnFirmwareListener = onFirmwareListener;
    }


    public interface OnFirmwareListener {
        /**
         * @param alreadyInBootloader 是否一开始就处于bootloader模式
         * @param msg                 现在在哪一步了
         * @param progress            当前进度条的progress
         * @param max                 进度条的最大值
         * @param state               当前固件状态（
         *                              0：获取固件版本
         *                              1：下载固件文件
         *                              2：更新固件
         *                            ）
         * @param index             当前正在操作固件list的下标
         */
        void onProgressMessage(boolean alreadyInBootloader, String msg, int progress, int max,int state,int index);

        /**
         * 提示的消息
         *
         * @param msg
         */

        //is：代表一个固件升级完成
        void onToastMessage(String msg);

        /**
         * 隐藏进度对话框
         */
        void dismissProgressDialog();

        /**
         * 获取到的固件版本信息
         *
         * @param firmWareBeen
         */
        void onShowInfo(List<FirmWareBean> firmWareBeen);
    }
}
