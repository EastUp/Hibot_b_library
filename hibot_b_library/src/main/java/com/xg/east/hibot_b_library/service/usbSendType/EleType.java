package com.xg.east.hibot_b_library.service.usbSendType;


import com.xg.east.hibot_b_library.service.usb.FrameOne;
import com.xg.east.hibot_b_library.service.usb.FrameThree;
import com.xg.east.hibot_b_library.service.usb.FrameTwo;

import java.io.UnsupportedEncodingException;

/**
 * 电池模块控制命令
 */
public class EleType {
    //电机电源断电
    public static byte[] offPower(){
        byte[] bytes=new byte[0];
        byte[] bytes1 = FrameTwo.two(0x0200, 0x0000, 0x0002, 0x0000, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x02);
        return output;
    }
    //电机上电
    public static byte[] onPower(){
        byte[] bytes=new byte[0];
        byte[] bytes1 = FrameTwo.two(0x0200, 0x0000, 0x0002, 0x0001, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x02);
        return output;
    }
    //读取电池状态
    public static byte[] getBatteryData(){
        byte[] bytes=new byte[0];
        byte[] bytes1 = FrameTwo.two(0x0200, 0x0000, 0x0002, 0x0002, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x02);
        return output;
    }

    //获取电源控制固件版本号
    public static byte[] getFwVersion(){
        byte[] bytes=new byte[0];
        byte[] bytes1 = FrameTwo.two(0x0200, 0x0000, 0x0002, 0x0003, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x02);
        return output;
    }

    //让电源控制进入 bootloader 模式
    public static byte[] intoBootloader(){
        byte[] bytes=new byte[0];
        byte[] bytes1 = FrameTwo.two(0x0200, 0x0000, 0x0002, 0x0004, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x02);
        return output;
    }


    /** 复位电源
     * @return
     */
    public static byte[] resetPower(){
        byte[] bytes=new byte[0];
        byte[] bytes1 = FrameTwo.two(0x0200, 0x0000, 0x0002, 0x0006, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x02);
        return output;
    }

    /** 设置电池信息
     * @return
     */
    public static byte[] setBatteryInfo(){
        byte[] bytes=new byte[0];
        byte[] bytes1 = FrameTwo.two(0x0200, 0x0000, 0x0002, 0x0007, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x02);
        return output;
    }

    /** 开启风扇
     * @return
     */
    public static byte[] openFan(){
        byte[] bytes=new byte[0];
        byte[] bytes1 = FrameTwo.two(0x0200, 0x0000, 0x0002, 0x0008, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x02);
        return output;
    }

    /** 关闭风扇
     * @return
     */
    public static byte[] closeFan(){
        byte[] bytes=new byte[0];
        byte[] bytes1 = FrameTwo.two(0x0200, 0x0000, 0x0002, 0x0009, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x02);
        return output;
    }

    /** 打印数据
     * @param content   数据内容
     * @return
     */
    public static byte[] print(String content){
        byte[] bytes= new byte[0];
        try {
            bytes = content.getBytes("gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] bytes1 = FrameTwo.two(0x0200, 0x0000, 0x0002, 0x000A, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x02);
        return output;
    }


    //发送更新数据
    public static byte[] sendUpdata(int indexData,byte[] data){
        byte[] bytes = FrameThree.send14(indexData, data);
        byte[] bytes1 = FrameTwo.two(0x0200, 0x0000, 0x00A0, 0x00A0, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x02);
        return output;
    }

    //所有更新数据发送完毕指令
    public static byte[] sendUdEnd(byte[] data){
        byte[] bytes = FrameThree.send15(data);
        byte[] bytes1 = FrameTwo.two(0x0200, 0x0000, 0x00A0, 0x00A0, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x02);
        return output;
    }

}
