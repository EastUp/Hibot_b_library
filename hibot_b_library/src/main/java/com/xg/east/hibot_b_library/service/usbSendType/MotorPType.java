package com.xg.east.hibot_b_library.service.usbSendType;


import com.xg.east.hibot_b_library.service.usb.FrameOne;
import com.xg.east.hibot_b_library.service.usb.FrameThree;
import com.xg.east.hibot_b_library.service.usb.FrameTwo;

/**
 *  步进电机
 */
public class MotorPType {
    /** 步进电机强制断电
     * @return
     */
    //步进电机强制断电
    public static byte[] stepMpOutage(){
        byte[] bytes=new byte[0];
        byte[] bytes1 = FrameTwo.two(0x0100, 0x0000, 0x0001, 0x0000, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }

    /** 步进电机强制停止运动
     * @return
     */
    //步进电机强制停止运动
    public static byte[] stepMpStop(){
        byte[] bytes=new byte[0];
        byte[] bytes1 = FrameTwo.two(0x0100, 0x0000, 0x0001, 0x0001, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }

    /** 读取步进电机的状态
     * @param targetId
     * @return
     */
    //读取步进电机的状态
    public static byte[] stepMpStatus(int targetId){
        byte[] bytes=new byte[0];
        byte[] bytes1 = FrameTwo.two(targetId, 0x0000, 0x0001, 0x0002, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }

    /** 设置步进电机返回零点运动
     * @param targetId
     * @param speed
     * @return
     */
    //设置步进电机返回零点运动
    public static byte[] stepMpZero(int targetId,int speed){
        byte[] bytes= FrameThree.send12(speed);
        byte[] bytes1 = FrameTwo.two(targetId, 0x0000, 0x0001, 0x0003, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }

    /** 设置步进电机根据设定参数运动
     * @param targetId
     * @param rSpeed
     * @param rJourney
     * @param endMode
     * @return
     */
    //设置步进电机根据设定参数运动
    public static byte[] stepMpParamsRun(int targetId,int  rSpeed,int rJourney,int endMode){
        byte[] bytes= FrameThree.send1(rSpeed,rJourney,endMode);
        byte[] bytes1 = FrameTwo.two(targetId, 0x0000, 0x0001, 0x0004, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }

    /** 设置步进电机进行位置缓存运动
     * @param rSpeed
     * @param rJourney
     * @param endMode
     * @return
     */
    //设置步进电机进行位置缓存运动
    public static byte[] stepMpLocation(int  rSpeed,int rJourney,int endMode){
        byte[] bytes= FrameThree.send2(rSpeed,rJourney,endMode);
        byte[] bytes1 = FrameTwo.two(0x0100, 0x0000, 0x0001, 0x0005, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }

    /** 设置步进电机运行位置表发送
     * @param way
     * @param ints
     * @return
     */
    //设置步进电机运行位置表发送
    public static byte[] stepMpLoc(int  way,int[] ints){
        byte[] bytes= FrameThree.send3(way,ints);
        byte[] bytes1 = FrameTwo.two(0x0100, 0x0000, 0x0001, 0x0006, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }

    /** 设置步进电机 电机运行目标位置运动
     * @param targetId
     * @param endLoc
     * @param time
     * @param endMode
     * @return
     */
    //设置步进电机 电机运行目标位置运动
    public static byte[] stepMpToEndLoc(int targetId,int  endLoc,int time,int endMode){
        byte[] bytes= FrameThree.send16(endLoc,time,endMode);
        byte[] bytes1 = FrameTwo.two(targetId, 0x0000, 0x0001, 0x0007, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }

    /** 设置电机传感器校准参数采集
     * @param targetId
     * @param way
     * @param maiChong
     * @param degree
     * @return
     */
    //设置电机传感器校准参数采集
    public static byte[] stepMpJiaoZhun(int targetId,int way,int maiChong,int degree){
        byte[] bytes= FrameThree.send10(way,maiChong,degree);
        byte[] bytes1 = FrameTwo.two(targetId, 0x0000, 0x0001, 0x0008, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }

    /** 配置电机控制参数
     * @param targetId
     * @param maxDegree
     * @param minDegee
     * @param maiChong
     * @param p1
     * @param p2
     * @param p3
     * @param way
     * @return
     */
    //    //配置电机控制参数
    public static byte[] stepMpSetting(int targetId,int maxDegree,int minDegee,int maiChong,double p1,double p2,double p3,int way){
        byte[] bytes= FrameThree.send11(maxDegree,minDegee,maiChong,p1,p2,p3,way);
        byte[] bytes1 = FrameTwo.two(targetId, 0x0000, 0x0001, 0x0009, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }

    /** 获取电机控制固件版本号
     * @param targetId
     * @return
     */
    //获取电机控制固件版本号
    public static byte[] getFwVersion(int targetId){
        byte[] bytes=new byte[0];
        byte[] bytes1 = FrameTwo.two(targetId, 0x0000, 0x0001, 0x000A, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }

    /** 让电机控制进入 bootloader 模式
     * @param targetId
     * @return
     */
    //让电机控制进入 bootloader 模式
    public static byte[] intoBootloader(int targetId){
        byte[] bytes=new byte[0];
        byte[] bytes1 = FrameTwo.two(targetId, 0x0000, 0x0001, 0x000B, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }

    /**
     * @param targetId 目标ID
     * @param electricity   电流大小
     * @return
     */
    public static byte[] setElectricity(int targetId,int electricity){
        byte[] bytes=FrameThree.send19(electricity);
        byte[] bytes1 = FrameTwo.two(targetId, 0x0000, 0x0001, 0x000C, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }


    //发送更新数据
    public static byte[] sendUpdata(int targetId,int indexData,byte[] data){
        byte[] bytes = FrameThree.send14(indexData, data);
        byte[] bytes1 = FrameTwo.two(targetId, 0x0000, 0x00A0, 0x00A0, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }

    /** 所有更新数据发送完毕指令
     * @param targetId
     * @param data
     * @return
     */
    public static byte[] sendUdEnd(int targetId,byte[] data){
        byte[] bytes = FrameThree.send15(data);
        byte[] bytes1 = FrameTwo.two(targetId, 0x0000, 0x00A0, 0x00A0, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }


    /** 设置步进电机根据设定参数运动
     * @param targetId
     * @param rSpeed
     * @param rJourney
     * @param endMode
     * @return
     */
    public static byte[] stepMpParamsRunNotEndDegree(int targetId,int  rSpeed,int rJourney,int endMode){
        byte[] bytes= FrameThree.send1(rSpeed,rJourney,endMode);
        byte[] bytes1 = FrameTwo.two(targetId, 0x0000, 0x0001, 0x000D, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }

    /** 设置加速度
     * @return
     */
    public static byte[]  setAcceleratedSpeed(int targetId,int acceleratedSpeed,int acceleratedAcceleratedSpeed){
        byte[] bytes= FrameThree.send20(acceleratedSpeed,acceleratedAcceleratedSpeed);
        byte[] bytes1 = FrameTwo.two(targetId, 0x0000, 0x0001, 0x000E, bytes,1);
        byte[] output = FrameOne.output(bytes1, 0x00, 0x01);
        return output;
    }

}
