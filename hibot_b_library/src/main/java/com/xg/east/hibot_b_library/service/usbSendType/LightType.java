package com.xg.east.hibot_b_library.service.usbSendType;


import com.xg.east.hibot_b_library.service.usb.FrameOne;
import com.xg.east.hibot_b_library.service.usb.FrameThree;
import com.xg.east.hibot_b_library.service.usb.FrameTwo;

/**
 * Created by EastRiseWM on 2016/8/31.
 */
public class LightType {
    //眼睛灯光的效果控制
    public static byte[] eyeControll(int controlMode,int r1,int g1,int b1,int r2,int g2,int b2,int r3,int g3,int b3,int r4,int g4,int b4,
                                     int r5,int g5,int b5,int r6,int g6,int b6,int r7,int g7,int b7,int r8,int g8,int b8){
        byte[] send6 = FrameThree.send6(controlMode, r1, g1, b1, r2, g2, b2, r3, g3, b3, r4, g4, b4, r5, g5, b5, r6, g6, b6, r7, g7, b7, r8, g8, b8);
        byte[] two = FrameTwo.two(0x0400, 0x0000, 0x0004, 0x0000, send6, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x05);
        return output;
    }

    //耳朵灯光控制效果
    public static byte[] earControll(int controlMode,int r1,int g1,int b1){
        byte[] send7 = FrameThree.send7(controlMode, r1, g1, b1);
        byte[] two = FrameTwo.two(0x0400, 0x0000, 0x0004, 0x0001, send7, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x05);
        return output;
    }

    //头顶灯光效果控制
    public static byte[] topHeadControll(int controlMode,int r1,int g1,int b1){
        byte[] send8 = FrameThree.send8(controlMode, r1, g1, b1);
        byte[] two = FrameTwo.two(0x0400, 0x0000, 0x0004, 0x0002, send8, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x05);
        return output;
    }

    //头部参数读取
    public static byte[] headStatus(){
        byte[] send8 = new byte[0];
        byte[] two = FrameTwo.two(0x0400, 0x0000, 0x0004, 0x0003, send8, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x05);
        return output;
    }

    //声源增强
    public static byte[] headVoice(int number){
        byte[] send8 = FrameThree.send9(number);;
        byte[] two = FrameTwo.two(0x0400, 0x0000, 0x0004, 0x0004, send8, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x05);
        return output;
    }

    //获取头部固件版本号
    public static byte[] getHeadFwVersion(){
        byte[] bytes = new byte[0];
        byte[] two = FrameTwo.two(0x0400, 0x0000, 0x0004, 0x0005, bytes, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x05);
        return output;
    }

    //让头部进入 bootloader 模式
    public static byte[] intoBootloader(){
        byte[] bytes = new byte[0];
        byte[] two = FrameTwo.two(0x0400, 0x0000, 0x0004, 0x0006, bytes, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x05);
        return output;
    }


    /** 让讯飞语音模块复位
     * @return
     */
    public static byte[]  resetModule(){
        byte[] bytes = new byte[0];
        byte[] two = FrameTwo.two(0x0400, 0x0000, 0x0004, 0x0007, bytes, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x05);
        return output;
    }

    /** 获取 uwb 数据
     * @return
     */
    public static byte[]  getUWBData(){
        byte[] bytes = new byte[0];
        byte[] two = FrameTwo.two(0x0400, 0x0000, 0x0004, 0x0008, bytes, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x05);
        return output;
    }

    /**   无线麦开关控制
     * @return
     */
    public static byte[] wirelessMicControll(int on_off){
        byte[] bytes= FrameThree.send17(on_off);
        byte[] two = FrameTwo.two(0x0400, 0x0000, 0x0004, 0x0009, bytes, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x01);
        return output;
    }

    /** 获取 uwb C帧数据
     * @return
     */
    public static byte[]  getUWBDataC(){
        byte[] bytes = new byte[0];
        byte[] two = FrameTwo.two(0x0400, 0x0000, 0x0004, 0x000A, bytes, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x05);
        return output;
    }


    //发送更新数据
    public static byte[] sendUpdata(int indexData,byte[] data){
        byte[] bytes = FrameThree.send14(indexData, data);
        byte[] bytes1 = FrameTwo.two(0x0400, 0x0000, 0x00A0, 0x00A0, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x05);
        return output;
    }

    //所有更新数据发送完毕指令
    public static byte[] sendUdEnd(byte[] data){
        byte[] bytes = FrameThree.send15(data);
        byte[] bytes1 = FrameTwo.two(0x0400, 0x0000, 0x00A0, 0x00A0, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x05);
        return output;
    }

}
