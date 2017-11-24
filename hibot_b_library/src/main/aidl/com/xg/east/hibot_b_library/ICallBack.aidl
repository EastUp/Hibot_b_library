// CallBack.aidl
package com.xg.east.hibot_b_library;

// Declare any non-default types here with import statements
import com.xg.east.hibot_b_library.USEntity;

interface ICallBack {
    void receiveData(inout USEntity ue);
    void receiveEndOrder();
    void hasUsbPermission();
    void disConnectUsb();
}
