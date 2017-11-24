// Communicate.aidl
package com.xg.east.hibot_b_library;

// Declare any non-default types here with import statements
import com.xg.east.hibot_b_library.USEntity;
import com.xg.east.hibot_b_library.ICallBack;

interface Communicate {
   void sendToSerial(inout USEntity us);
   void init();
   void registerCallBack(ICallBack cb);
   void unregisterCallBack(ICallBack cb);
}
