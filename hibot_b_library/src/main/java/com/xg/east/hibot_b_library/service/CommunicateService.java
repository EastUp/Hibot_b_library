package com.xg.east.hibot_b_library.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.xg.east.hibot_b_library.Communicate;
import com.xg.east.hibot_b_library.ICallBack;
import com.xg.east.hibot_b_library.R;
import com.xg.east.hibot_b_library.USEntity;
import com.xg.east.hibot_b_library.serial.SerialPort;
import com.xg.east.hibot_b_library.service.usb.FrameOne;
import com.xg.east.hibot_b_library.service.usbSendType.ClassicType;
import com.xg.east.hibot_b_library.service.usbSendType.MotorPType;
import com.xg.east.hibot_b_library.service.utils.FormatChange;
import com.xg.east.hibot_b_library.utils.ShowLog;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CommunicateService extends Service {

    public final static String ACTION_RECEIVE_RIGHT = "blackbox.test.right.receive";//收到正确的返回
    public final static String ACTION_RECEIVE_DATA = "blackbox.test.data";//收到正确的返回
    public final static String ACTION_USB_DEVICE_DETACHED = "blackbox.test.USB_DEVICE_DETACHED";
    public final static String ACTION_USB_DEVICE_ATTACHED = "blackbox.test.USB_DEVICE_ATTACHED";
    private final  String TAG = this.getClass().getSimpleName();
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private UsbManager manager;
    private UsbDevice device = null;
    UsbInterface[] usbinterface = null;
    UsbEndpoint[][] endpoint = new UsbEndpoint[5][5];
    public UsbDeviceConnection connection = null;
    private int myvid = 1155, mypid = 22336;
    UsbDataReceiveThread mUsbDataReceiveThread = null;
    byte[] normalData = new byte[1000];
    private int lenth = 0;
    private int findHeadFlag = 0;//帧头,0未找到,1找到;
    private int sendCount = 0;
    private int rightCount = 0, falseCount = 0;
    private int sendByteCount = 0, receiveByteCount = 0;
    public boolean isUsbconnect = true;//Usb是否连接，决定要不要连续发送数据.
    private boolean canUsbReceiveData=true;//是否停掉接收Usb数据的线程
    private static String LOG = "TestActivity";

    private static String SERIVAL_PORT = "/dev/ttyS2";
    //	private static String SERIVAL_BAND = "115200";
    private static String SERIVAL_BAND = "1152000";

    /** 串口句柄 **/
    protected SerialPort mSerialPort;
    /** 串口输出 **/
    protected OutputStream mOutputStream;
    /** 串口输入 **/
    private InputStream mInputStream;
    /** 串口读线程 **/
//    private ReadThread mReadThread;

    /** 开与关的切换 **/
    private boolean hasOpenFlag = false;
    private boolean isRegister=false;//判断广播是否被注册了
    private ICallBack mICallBack;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //Log.v("sendResult", sendCount + "正确次数:" + rightCount);
        }
    };

    public CommunicateService() {
    }

    Communicate.Stub mCommunicate=new Communicate.Stub() {
        @Override
        public void sendToSerial(USEntity us) throws RemoteException {
            CommunicateService.this.writeToSerial(us.getBytes());
        }

        @Override
        public void init() throws RemoteException {
            initialize();
        }

        @Override
        public void registerCallBack(ICallBack cb) throws RemoteException {
            mICallBack=cb;
        }

        @Override
        public void unregisterCallBack(ICallBack cb) throws RemoteException {
            mICallBack=null;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            try {
               return super.onTransact(code, data, reply, flags);
            } catch (RuntimeException e) {
                Log.w("MyClass", "Unexpected remote exception", e);
                throw e;
            }
//            return super.onTransact(code, data, reply, flags);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("Foreground Service Start");
        builder.setContentTitle("Robot");
        builder.setAutoCancel(true);
        builder.setContentText("Robot通信正在运行");
//        PendingIntent contentIntent=PendingIntent.getActivity(this,0,new Intent(this, IndexActivity.class),0);
        Intent intent=new Intent();
        intent.setAction("BtopenA");
        PendingIntent contentIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        builder.setContentIntent(contentIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Notification notification = builder.build();
        startForeground(2, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_USB_PERMISSION);
        filter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        filter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        registerReceiver(UsbReceiver, filter);
        isRegister=true;
//        return mBinder;
        return mCommunicate;
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onRebind(Intent intent) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_USB_PERMISSION);
        filter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        filter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        registerReceiver(UsbReceiver, filter);
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        unregisterReceiver(UsbReceiver);
        closeSerial();
        try {
            mICallBack.disConnectUsb();//Usb断开连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        canUsbReceiveData=false;//关闭接收的线程
        if (connection != null) {
            connection.releaseInterface(usbinterface[1]);
            connection.close();
        }
        normalData = new byte[1000];
        findHeadFlag = 0;
        lenth = 0;
        getDataState = 0;//重新接收帧头
        stopSelf();
        return true;
    }



    public void initialize() {
        openSerial();
        findUsbDevice();
    }


    /*
     * 寻找device设备
     */
    void findUsbDevice() {
        manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
        //Log.e(TAG, "get device list  = " + deviceList.size());
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        while (deviceIterator.hasNext()) {
            device = deviceIterator.next();
            //Log.i(TAG, "vid: " + device.getVendorId() + "\t pid: " + device.getProductId());
            if (device.getVendorId() == myvid && device.getProductId() == mypid) {
                break;
            }
        }
        if (device != null && device.getVendorId() == myvid && device.getProductId() == mypid) {
            //Log.i(TAG, "找到设备:" + device.getVendorId() + "\t pid: " + device.getProductId());
        } else {
            //Log.i(TAG, "未发现支持设备");
            Toast.makeText(getApplicationContext(), "未发现USB设备，需要检查安卓主板和主控板之间的硬件连接", Toast.LENGTH_LONG).show();// 显示时间较短
            return;
        }

        if (manager.hasPermission(device)) {
            //Log.i(TAG, "拥有访问权限");
        } else {
            //Log.i(TAG, "未获得访问权限");
        }

        //Log.i(TAG, device.getDeviceName());

        //Log.i(TAG, "接口数为：" + device.getInterfaceCount());

        usbinterface = new UsbInterface[device.getInterfaceCount()];
        for (int i = 0; i < device.getInterfaceCount(); i++) {
            usbinterface[i] = device.getInterface(i);
            //Log.i(TAG, "接口" + i + "的端点数为：" + usbinterface[i].getEndpointCount());
            for (int j = 0; j < usbinterface[i].getEndpointCount(); j++) {
                endpoint[i][j] = usbinterface[i].getEndpoint(j);
                if (endpoint[i][j].getDirection() == 0) {
                    //Log.i(TAG, "端点" + j + "的数据方向为输出");
                } else {
                    //Log.i(TAG, "端点" + j + "的数据方向为输入");
                }
            }
        }

        PendingIntent pi = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
        if (manager.hasPermission(device)) {
            // 第1处（共3处）写开启连接的方法，调用之后会开启USB，并且启动定时读取USB缓存的操作
//            OpenConnection();
            if (mUsbDataReceiveThread != null) {
                mUsbDataReceiveThread = null;
            }
            // 初始化接收线程
            mUsbDataReceiveThread = new UsbDataReceiveThread();

            // 启动接收线程
            mUsbDataReceiveThread.start();

            try {
                mICallBack.hasUsbPermission();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                mICallBack.disConnectUsb();
            } catch (Exception e) {
                e.printStackTrace();
            }
            manager.requestPermission(device, pi);
        }
    }

    /**
     * 所有动作归零运动
     */
    private void allStepToZero() {
        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);
        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 5000);
        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
        sendAllBytesToSerial(bytesHA1, bytesHB1, bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81);
    }

    /*拼凑在一起发送*/
    public void sendAllBytesToSerial(byte[]... listBytes) {
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
        writeToSerial(sorce);
    }

    /*
     * 接收数据
     */
    class UsbDataReceiveThread extends Thread {

        public UsbDataReceiveThread() {
            if (connection != null) {
                connection.close();
            }
            if(usbinterface[1]==null){
                usbinterface = new UsbInterface[device.getInterfaceCount()];
                for (int i = 0; i < device.getInterfaceCount(); i++) {
                    usbinterface[i] = device.getInterface(i);
                    ShowLog.i(TAG, "接口" + i + "的端点数为：" + usbinterface[i].getEndpointCount());
                    for (int j = 0; j < usbinterface[i].getEndpointCount(); j++) {
                        endpoint[i][j] = usbinterface[i].getEndpoint(j);
                        if (endpoint[i][j].getDirection() == 0) {
                            ShowLog.i(TAG, "端点" + j + "的数据方向为输出");
                        } else {
                            ShowLog.i(TAG, "端点" + j + "的数据方向为输入");
                        }
                    }
                }
            }
            connection = manager.openDevice(device);
            connection.claimInterface(usbinterface[1], true);
        }

        @Override
        public void run() {
            synchronized (this) {
                while (canUsbReceiveData) {
                    // TODO Auto-generated method stub
                    int datalength;
                    if (connection == null) break;
                    byte[] recieveByte = new byte[1000];
                    datalength = connection.bulkTransfer(endpoint[1][1], recieveByte, recieveByte.length, 1000);//返回的接收到的数据
                    try {
                        if (datalength > 0) {
                            receiveByteCount += datalength;
    //                        //Log.v("send",sendByteCount+"--"+receiveByteCount);
    //                        decode(datalength, recieveByte);
                            byte[] getData=new byte[datalength];
                            System.arraycopy(recieveByte,0,getData,0,datalength);
                            //Log.v("sendBackdatalength", datalength + "--|" + recieveByte[0] + "--|" + recieveByte[1] + "---|" + FormatChange.ByteArrToHex(getData));
                            decode(getData);
                            //Log.v("sendBackdataSendReceive",sendByteCount+"--"+receiveByteCount+"---"+sendCount+"--"+rightCount);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    private final BroadcastReceiver UsbReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //Log.e(TAG, action);
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    //允许权限申请
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if (device != null) {
                            // 第2处（共3处）写开启连接的方法，调用之后会开启USB，并且启动定时读取USB缓存的操作
                            //OpenConnection();
                            if (mUsbDataReceiveThread != null) {
                                try {
                                    mUsbDataReceiveThread.interrupt();
                                    mUsbDataReceiveThread.stop();
                                    mUsbDataReceiveThread.destroy();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                mUsbDataReceiveThread = null;
                            }
                            // 初始化接收线程
                            mUsbDataReceiveThread = new UsbDataReceiveThread();
                            // 启动接收线程
                            mUsbDataReceiveThread.start();

                            try {
                                mICallBack.hasUsbPermission();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        //Log.d(TAG, "permission denied for device " + device);
                    }
                }
            } else if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action)) {
                manager = (UsbManager) getSystemService(Context.USB_SERVICE);
                HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
                ShowLog.e(TAG, "get device list  = " + deviceList.size());
                Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
                while (deviceIterator.hasNext()) {
                    device = deviceIterator.next();
                    ShowLog.i(TAG, "vid: " + device.getVendorId() + "\t pid: " + device.getProductId());
                    if (device.getVendorId() == myvid && device.getProductId() == mypid) {
                        break;
                    }
                }
                if (device != null && device.getVendorId() == myvid && device.getProductId() == mypid) {
//                    Toast.makeText(context, "U盘被拔出", Toast.LENGTH_SHORT).show();
                } else {
                    ShowLog.i(TAG, "未发现支持设备");
                    closeSerial();//关闭串口
//                    mHasUsbPermission = false;
                    try {
                        mICallBack.disConnectUsb();//Usb断开连接
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    canUsbReceiveData=false;//关闭接收的线程
                    Toast.makeText(context, "USB设备被拔出", Toast.LENGTH_SHORT).show();
                    if (connection != null) {
                        connection.releaseInterface(usbinterface[1]);
                        connection.close();
                    }
                    normalData = new byte[1000];
                    findHeadFlag = 0;
                    lenth = 0;
                    getDataState = 0;//重新接收帧头
                    sendBroadcast(new Intent(ACTION_USB_DEVICE_DETACHED));
                    isUsbconnect = false;//Usb没有连接上
                }
            } else if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(action)) {
                if(!isUsbconnect){
                    Toast.makeText(context,"USB设备已连接", Toast.LENGTH_SHORT).show();
                    canUsbReceiveData=true;
                    getDataState = 0;//重新接收帧头
                    openSerial();
                    findUsbDevice();
                    sendBroadcast(new Intent(ACTION_USB_DEVICE_ATTACHED));
                    isUsbconnect = true;//Usb连接上
                }else{
//                    Toast.makeText(context, "U盘已插入", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private void broadCastSend() {
        Intent intent = new Intent(ACTION_RECEIVE_RIGHT);
//        intent.putExtra("result","正确次数:" + rightCount);
        sendBroadcast(intent);
    }

    private void broadCastData(byte[] bytes) {
        //Log.v("sendBroad", "send");
        Intent intent = new Intent(ACTION_RECEIVE_DATA);
        intent.putExtra("result", bytes);
//        intent.putExtra("result","正确次数:" + rightCount);
        sendBroadcast(intent);
    }


    /**解析
     * @param datalength  数据总长
     * @param recieveByte 收到的数组
     */
    private void decode(int datalength, byte[] recieveByte) {
        if (findHeadFlag == 0) {
            byte[] by = new byte[0];
            if(recieveByte[0]!=-73){
                for (int i = 0; i < datalength; i++) {
                    if (recieveByte[i] == -73) {
                        datalength -= i;
                        by = new byte[recieveByte.length - i];
                        System.arraycopy(recieveByte,i,by,0,by.length);
                        break;
                    }
                }
            }else{
                by=recieveByte;
            }

            if (by.length > 0 && by[0] == -73) {//第一个字节为帧头
                //Log.v("sendQQQQQ", "there");
                normalData = new byte[1000];
                lenth = 0;
                findHeadFlag = 1;
                boolean findEnd = false;
                int index = 0;
                for (int i = 0; i < datalength; i++) {
                    if (by[i] == -67) {
                        try {
                            System.arraycopy(by,0,normalData,0,i+1);
                        } catch (Exception e) {
                            e.printStackTrace();
                            normalData = new byte[1000];
                            findHeadFlag = 0;
                            lenth = 0;
//                            datalength -= (i + 1);
//                            if (datalength > 0) {
//                                byte[] byw = new byte[by.length - i - 1];
//                                System.arraycopy(by,i+1,byw,0,byw.length);
//                                decode(datalength, byw);
//                            }
                            return;
                        }
                        lenth += i + 1 - index;
                        index = i + 1;

                        String s1 = FormatChange.intToHexString((int) normalData[2], 1);
                        String s2 = FormatChange.intToHexString((int) normalData[3], 1);
                        int parseInt = Integer.parseInt(s2 + s1, 16);
                        //Log.v("sendQQQQQ", "there--" + lenth + "--" + parseInt + "--" + i);
                        if (parseInt == lenth - 8) {
                            //Log.v("sendQQQQQ", "there--" /*+ FormatChange.ByteArrToHex(normalData)*/);
                            findEnd = true;
                            rightCount++;
                            String s3 = FormatChange.intToHexString((int) normalData[6], 1);
                            String s4 = FormatChange.intToHexString((int) normalData[7], 1);
                            if ((s4 + s3).equalsIgnoreCase("00FE")) {
                                try {
                                    mICallBack.receiveEndOrder();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
//                                broadCastSend();
                            } else {
                                try {
                                    mICallBack.receiveData(new USEntity(normalData,lenth));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
//                                broadCastData(normalData);
                            }
                            normalData = new byte[1000];
                            findHeadFlag = 0;
                            lenth = 0;
                            datalength -= (i + 1);
                            if (datalength > 0) {
                                byte[] byw = new byte[by.length - i - 1];
                                System.arraycopy(by,i+1,byw,0,byw.length);
                                decode(datalength, byw);
                            }
                            return;
                        }
                    }
                }
                if (!findEnd) {
                    normalData = new byte[1000];
                    findHeadFlag = 1;
                    lenth = 0;
                    System.arraycopy(by,0,normalData,lenth,datalength);
                    lenth = datalength;
                }
            }
        } else {
            //Log.v("sendQQQQQ", "here");
            boolean findEnd = false;
            int index = 0;
            int fromOne = lenth;
            for (int i = 0; i < datalength; i++) {
                if (recieveByte[i] == -67) {
                    //Log.e("Error",FormatChange.ByteArrToHex(normalData));
                    try {
                        System.arraycopy(recieveByte,0,normalData,lenth,i+1);
                        lenth += i + 1 - index;
                        index = i + 1;
                    } catch (Exception e) {
                        e.printStackTrace();
                        //Log.e("Error","错误数据，重新接收");
                        normalData = new byte[1000];
                        findHeadFlag = 0;
                        lenth = 0;
                    }

                    String s1 = FormatChange.intToHexString((int) normalData[2], 1);
                    String s2 = FormatChange.intToHexString((int) normalData[3], 1);
                    int parseInt = Integer.parseInt(s2 + s1, 16);
                    //Log.v("sendQQQQQ", "here--" + lenth + "--" + parseInt + "--" + i);
                    if (parseInt == lenth - 8) {
                        //Log.v("sendQQQQQ", "here--" /*+ FormatChange.ByteArrToHex(normalData)*/);
                        findEnd = true;
                        rightCount++;
                        String s3 = FormatChange.intToHexString((int) normalData[6], 1);
                        String s4 = FormatChange.intToHexString((int) normalData[7], 1);
                        if ((s4 + s3).equalsIgnoreCase("00FE")) {
                            try {
                                mICallBack.receiveEndOrder();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
//                            broadCastSend();
                        } else {
//                            broadCastData(normalData);
                            try {
                                mICallBack.receiveData(new USEntity(normalData,lenth));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        normalData = new byte[1000];
                        findHeadFlag = 0;
                        lenth = 0;
                        datalength -= (i + 1);
                        if (datalength > 0) {
                            byte[] byw = new byte[recieveByte.length - i - 1];
                            System.arraycopy(recieveByte,i+1,byw,0,byw.length);
                            decode(datalength, byw);
                        }
                        return;
                    }
                }
            }
            if (!findEnd) {
                findHeadFlag = 1;
                lenth = fromOne;
                //Log.v("sendLength",datalength+"--"+lenth);
                System.arraycopy(recieveByte,0,normalData,lenth,datalength);
                fromOne += datalength;
                lenth = fromOne;
            }
        }
    }

    /**
     * TODO 打开串口
     */
    private void openSerial(){

        String serivalPort = SERIVAL_PORT;
        String serivalBaud = SERIVAL_BAND;
        //Log.v(LOG,"串口:"+serivalPort+" 波特率:"+serivalBaud);
        try {
            mSerialPort = new SerialPort(new File(serivalPort),Integer.parseInt(serivalBaud),0);
            hasOpenFlag = true;
            mOutputStream = mSerialPort.getOutputStream();
            mInputStream = mSerialPort.getInputStream();
        } catch (NumberFormatException e) {
//            Toast.makeText(this, "打开串口"+"串口:"+serivalPort+" 波特率:"+serivalBaud+"失败.\n"+e.toString(), Toast.LENGTH_LONG).show();
            mSerialPort.close();
        } catch (SecurityException e) {
//            Toast.makeText(this, "打开串口"+"串口:"+serivalPort+" 波特率:"+serivalBaud+"失败.\n"+e.toString(), Toast.LENGTH_LONG).show();
            mSerialPort.close();
        } catch (IOException e) {
//            Toast.makeText(this, "打开串口"+"串口:"+serivalPort+" 波特率:"+serivalBaud+"失败.\n"+e.toString(), Toast.LENGTH_LONG).show();
            mSerialPort.close();
        }
    }


    /**
     * TODO 向串口写入数据
     */
    private void writeToSerial(byte[] sendData){
        if(mOutputStream == null){
            Toast.makeText(this, "写串口没有打开 mOutputStream为空", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(sendData.length > 0){
            try {
                sendCount += 2048;
                mOutputStream.write(sendData);
                mOutputStream.flush();
                //Log.v("sendBackData", FormatChange.ByteArrToHex(sendData));
            } catch (IOException e) {
                //Log.e("Error",e.getMessage());
                Toast.makeText(getApplicationContext(), "发送数据错误", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "发送数据为空", Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * TODO 关闭串口
     */
    private void closeSerial(){
        hasOpenFlag = false;

        if(mInputStream!= null){
            try {
                mInputStream.close();
            } catch (IOException e) {
                Toast.makeText(this, "关闭串口失败", Toast.LENGTH_SHORT).show();
            }
            mInputStream = null;
        }
        if(mOutputStream != null){
            try {
                mOutputStream.close();
            } catch (IOException e) {
                Toast.makeText(this, "关闭串口失败", Toast.LENGTH_SHORT).show();
            }
            mOutputStream = null;
        }
//
//        if (mReadThread != null){
//            mReadThread.interrupt();
//        }

        if(mSerialPort != null){
            mSerialPort.close();
        }
        mSerialPort = null;
        Toast.makeText(this, "关闭串口成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
//        closeSerial();
        closeSerial();
        try {
            mICallBack.disConnectUsb();//Usb断开连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        canUsbReceiveData=false;//关闭接收的线程
        if (connection != null) {
            connection.releaseInterface(usbinterface[1]);
            connection.close();
        }
        normalData = new byte[1000];
        findHeadFlag = 0;
        lenth = 0;
        getDataState = 0;//重新接收帧头
        try {
            if (isRegister) {
                unregisterReceiver(UsbReceiver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }




    static final int getDataBufferLength = 1500;
//    static final int getDataBufferLength = 1000;

    static final byte CMD_FRAME_HEADER_L = (byte) 0xB7;
    static final byte CMD_FRAME_HEADER_H = (byte) 0x8C;
    static final byte CMD_FRAME_TAIL_L = (byte) 0xAC;
    static final byte CMD_FRAME_TAIL_H = (byte) 0xBD;

    byte[] getDataBuffer = new byte[getDataBufferLength];
    int getDataCount = 0;
    int getDataState = 0;//获取到帧头或帧尾

    void decode(byte[] getData){
        for(int i=0; i<getData.length; i++){
            getDataBuffer[getDataCount] = getData[i];
            getDataCount++;
            if(getDataCount >= getDataBufferLength){
                getDataCount = 0;
            }
            switch(getDataState){
                case 0:
                    if(getData[i] == CMD_FRAME_HEADER_L){//接收到第一个帧头
                        getDataState = 1;
                    }
                    break;

                case 1:
                    if(getData[i] == CMD_FRAME_HEADER_H)//接收到第二个帧头
                    {
                        getDataState=2;
                    }
                    break;

                case 2:
                    if(getData[i] == CMD_FRAME_TAIL_L)//接收到第一个帧尾
                    {
                        getDataState=3;
                    }
                    break;

                case 3:
                    if(getData[i] == CMD_FRAME_TAIL_H)   //接收到第二个帧尾
                    {
                        getDataState=0;
                        try {
                            alaysisData();//开始解析数据
                        } catch (Exception e) {
                            e.printStackTrace();
                            getDataCount = 0;//解析完从头开始
                        }
                    }
                    else if(getData[i] == CMD_FRAME_TAIL_L)//又出现了第一个帧尾
                    {
                        getDataState = 3;
                    }
                    else                                //是伪帧尾，从新开始找第一个帧尾
                    {
                        getDataState = 2;
                    }
                    break;
                default: getDataState = 0;break;
            }

        }
    }


    void alaysisData(){
        //再验证下帧头帧尾
        if(getDataBuffer[0] == CMD_FRAME_HEADER_L && getDataBuffer[1] == CMD_FRAME_HEADER_H &&
                getDataBuffer[getDataCount-2] == CMD_FRAME_TAIL_L && getDataBuffer[getDataCount-1] == CMD_FRAME_TAIL_H){

            int frameLen = bulidUint16(getDataBuffer[2],getDataBuffer[3]);
            int crc = bulidUint16(getDataBuffer[getDataCount-3],getDataBuffer[getDataCount-4]);//获取CRC值
            byte[] frameData = new byte[frameLen];
            System.arraycopy(getDataBuffer, 4, frameData, 0, frameLen);
            int getCrcData = FrameOne.crc16(frameData,frameLen);
            if(crc == getCrcData){//看下CRC是否OK
                byte[] realGetData= new byte[getDataCount];
                System.arraycopy(getDataBuffer, 0, realGetData, 0, getDataCount);
                //Log.v("receiveData",FormatChange.ByteArrToHex(realGetData)+"=="+getDataCount);

                String s3 = FormatChange.intToHexString((int) realGetData[6], 1);
                String s4 = FormatChange.intToHexString((int) realGetData[7], 1);
                if ((s4 + s3).equalsIgnoreCase("00FE")) {
                    //Log.v("s4+s3",true+"");
                    try {
                        mICallBack.receiveEndOrder();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        mICallBack.receiveData(new USEntity(realGetData,getDataCount));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        getDataCount = 0;//解析完从头开始
    }

    int bulidUint16(byte low,byte high){
        int data = ((high & 0xff) << 8) | (low & 0xff);
        return data;
    }

}
