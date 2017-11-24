package com.xg.east.hibot_b_library.service.usbSendType;


import com.xg.east.hibot_b_library.SerialSend;
import com.xg.east.hibot_b_library.service.usb.FrameOne;
import com.xg.east.hibot_b_library.service.usb.FrameThree;
import com.xg.east.hibot_b_library.service.usb.FrameTwo;

/**
 *  底盘模块控制命令
 */
public class ClassicType {
    /** 机身静止
     * @return
     */
    public static byte[] motionless(){
        byte[] bytes=new byte[0];
        if(SerialSend.brush) {
            byte[] bytes1 = FrameTwo.two(0x0300, 0x0000, 0x0003, 0x0000, bytes, 1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x03);
            return output;
        }else{
            byte[] bytes1 = FrameTwo.two(0x0500, 0x0000, 0x0003, 0x0000, bytes, 1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x05);
            return output;
        }

    }

    /** 自由运动-不充电(1:开启      0：关闭)
     * @param control
     * @return
     */
    public static byte[] libertyMoveNoCharge(int control){
        byte[] bytes= FrameThree.send18(control);
        if(SerialSend.brush) {
            byte[] bytes1 = FrameTwo.two(0x0300, 0x0000, 0x0003, 0x0001, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x03);
            if (!SerialSend.lockClassic) {
                return output;
            }else{
                if(control==0)
                    return output;
                else
                    return new byte[0];
            }
        }else{
            byte[] bytes1 = FrameTwo.two(0x0500, 0x0000, 0x0003, 0x0001, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x05);
            if (!SerialSend.lockClassic) {
                return output;
            }else{
                if(control==0)
                    return output;
                else
                    return new byte[0];
            }
        }
    }

    /** 自由运动-充电(1:开启      0：关闭)
     * @return
     */
    public static byte[] libertyMoveCharge(int control){
        byte[] bytes=FrameThree.send18(control);
        if(SerialSend.brush) {
            byte[] bytes1 = FrameTwo.two(0x0300, 0x0000, 0x0003, 0x0002, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x03);
            if (!SerialSend.lockClassic) {
                return output;
            }else{
                if(control==0)
                    return output;
                else
                    return new byte[0];
            }
        }else {
            byte[] bytes1 = FrameTwo.two(0x0500, 0x0000, 0x0003, 0x0002, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x05);
            if (!SerialSend.lockClassic) {
                return output;
            }else{
                if(control==0)
                    return output;
                else
                    return new byte[0];
            }
        }
    }

    /**  角度旋转
     * @param mode  运动方式（0：向左旋转，1：向右旋转）
     * @param degree  旋转角度(单位：度)
     * @return
     */
    public static byte[] stepRun(int  mode,int degree){
        byte[] bytes= FrameThree.send4(mode,degree);
        if(SerialSend.brush) {
            byte[] bytes1 = FrameTwo.two(0x0300, 0x0000, 0x0003, 0x0003, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x03);
            if (!SerialSend.lockClassic) {
                return output;
            }else
                return new byte[0];
        }else{
            byte[] bytes1 = FrameTwo.two(0x0500, 0x0000, 0x0003, 0x0003, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x05);
            if (!SerialSend.lockClassic) {
                return output;
            }else
                return new byte[0];
        }

    }

    /** 速度控制
     * @param left
     * @param right
     * @param time
     * @return
     */
    public static byte[] runWithSpeed(int left,int right,int time){
        byte[] bytes=FrameThree.send5(left,right,time);
        if(SerialSend.brush) {
            byte[] bytes1 = FrameTwo.two(0x0300, 0x0000, 0x0003, 0x0004, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x03);
            if (!SerialSend.lockClassic) {
                return output;
            }else{
                if(left==0&&right==0)
                    return output;
                else
                    return new byte[0];
            }
        }else{
            byte[] bytes1 = FrameTwo.two(0x0500, 0x0000, 0x0003, 0x0004, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x05);
            if (!SerialSend.lockClassic) {
                return output;
            }else{
                if(left==0&&right==0)
                    return output;
                else
                    return new byte[0];
            }
        }

    }

    /** 数据反馈
     * @return
     */
    public static byte[] getClassicData(){
        byte[] bytes=new byte[0];
        if(SerialSend.brush) {
            byte[] bytes1 = FrameTwo.two(0x0300, 0x0000, 0x0003, 0x0005, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x03);
            return output;
        }else{
            byte[] bytes1 = FrameTwo.two(0x0500, 0x0000, 0x0003, 0x0005, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x05);
            return output;
        }

    }


    /** 结束指令
     * @return
     */
    public static byte[] endOrder(){
        byte[] bytes=new byte[0];
        byte[] bytes1 = FrameTwo.two(0x00FE, 0x0000, 0, 0, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0xFE, 0x00);
        return output;
    }


    /** 自主避障运动
     * @param status
     * @return
     */
    public static byte[] obsAvoidance(int status){
        byte[] bytes=FrameThree.send13(status);
        if(SerialSend.brush) {
            byte[] bytes1 = FrameTwo.two(0x0300, 0x0000, 0x0003, 0x0008, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x03);
            if (!SerialSend.lockClassic) {
                return output;
            }else{
                if(status==0)
                    return output;
                else
                    return new byte[0];
            }
        }else{
            byte[] bytes1 = FrameTwo.two(0x0500, 0x0000, 0x0003, 0x0008, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x05);
            if (!SerialSend.lockClassic) {
                return output;
            }else{
                if(status==0)
                    return output;
                else
                    return new byte[0];
            }
        }

    }


    /** 获取底盘控制固件版本号
     * @return
     */
    public static byte[] getFwVersion(){
        byte[] bytes=new byte[0];
        if(SerialSend.brush) {
            byte[] bytes1 = FrameTwo.two(0x0300, 0x0000, 0x0003, 0x0006, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x03);
            return output;
        }else{
            byte[] bytes1 = FrameTwo.two(0x0500, 0x0000, 0x0003, 0x0006, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x05);
            return output;
        }

    }


    /** 让底盘控制进入 bootloader 模式
     * @return
     */
    public static byte[] intoBootloader(){
        byte[] bytes=new byte[0];
        if(SerialSend.brush) {
            byte[] bytes1 = FrameTwo.two(0x0300, 0x0000, 0x0003, 0x0007, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x03);
            return output;
        }else{
            byte[] bytes1 = FrameTwo.two(0x0500, 0x0000, 0x0003, 0x0007, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x05);
            return output;
        }

    }

    /** 让底盘控制进入 UWB  定位 模式
     * @param currentX  机器人当前 X X  坐标
     * @param currentY  机器人当前 Y Y  坐标
     * @param expectX   机器人期望 X X  坐标
     * @param expectY   机器人期望 Y Y  坐标
     * @param deflection  地磁场方向与地图地磁偏角
     * @return
     */
    public static byte[] classicIntoUWB(int currentX,int currentY,int expectX,int expectY,int deflection){
        byte[] bytes=FrameThree.send21(currentX,currentY,expectX,expectY,deflection);
        if(SerialSend.brush) {
            byte[] bytes1 = FrameTwo.two(0x0300, 0x0000, 0x0003, 0x0008, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x03);
            return output;
        }else{
            byte[] bytes1 = FrameTwo.two(0x0500, 0x0000, 0x0003, 0x0008, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x05);
            return output;
        }

    }


    /** 发送更新数据
     * @param indexData
     * @param data
     * @return
     */
    public static byte[] sendUpdata(int indexData,byte[] data){
        byte[] bytes = FrameThree.send14(indexData, data);
        if(SerialSend.brush) {
            byte[] bytes1 = FrameTwo.two(0x0300, 0x0000, 0x00A0, 0x00A0, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x03);
            return output;
        }else{
            byte[] bytes1 = FrameTwo.two(0x0500, 0x0000, 0x00A0, 0x00A0, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x05);
            return output;
        }
    }


    /** 所有更新数据发送完毕指令
     * @param data
     * @return
     */
    public static byte[] sendUdEnd(byte[] data){
        byte[] bytes = FrameThree.send15(data);
        if(SerialSend.brush) {
            byte[] bytes1 = FrameTwo.two(0x0300, 0x0000, 0x00A0, 0x00A0, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x03);
            return output;
        }else{
            byte[] bytes1 = FrameTwo.two(0x0500, 0x0000, 0x00A0, 0x00A0, bytes,1);
            byte[] output = FrameOne.output(bytes1, 0x00, 0x05);
            return output;
        }
    }

}
