package com.xg.east.hibot_b_library.service.usbSendType;


import com.xg.east.hibot_b_library.service.usb.FrameOne;
import com.xg.east.hibot_b_library.service.usb.FrameThree;
import com.xg.east.hibot_b_library.service.usb.FrameTwo;

/**
 * Created by EastRiseWM on 2016/12/23.
 */

public class MainCtl {
    //获取407控制固件版本号
    public static byte[] getFwVersion(){
        byte[] bytes = new byte[0];
        byte[] two = FrameTwo.two(0x00FC, 0x0000, 0x0004, 0x0005, bytes, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x05);
        return output;
    }

    //让f407进入 bootloader 模式
    public static byte[] intoBootloader(){
        byte[] bytes = new byte[0];
        byte[] two = FrameTwo.two(0x00FD, 0x0000, 0x0004, 0x0006, bytes, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x05);
        return output;
    }

    //发送更新数据
    public static byte[] sendUpdata(int indexData,byte[] data){
        byte[] bytes = FrameThree.send14(indexData, data);
        return bytes;
    }

    //所有更新数据发送完毕指令
    public static byte[] sendUdEnd(byte[] data){
        byte[] bytes = FrameThree.send15(data);
        return bytes;
    }

}
