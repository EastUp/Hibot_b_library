package com.xg.east.hibot_b_library.service.usb;


import com.xg.east.hibot_b_library.service.utils.FormatChange;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


/**
 * 数据第三层
 */
public class FrameThree {

    /**
     * @param a 低位在前，高位在后
     * @return
     */
    private static int change(int a){
        int parseInt = Integer.parseInt(FormatChange.intToHexString(a, 2), 16);
        return ((parseInt>>8) | (parseInt<<8));
    }

    private static byte[] changeFourBytes(int a){
        byte[] get=new byte[4];
        get[0]= (byte) (a&0xff);
        get[1]= (byte) ((a>>8)&0xff);
        get[2]= (byte) ((a>>16)&0xff);
        get[3]= (byte) ((a>>24)&0xff);
        return  get;
    }

    private static long change(double px){
        long pxLong = (long)(px*10000000000l);
        byte[] pxbyte = new byte[8];

        pxbyte[0] = (byte) (pxLong&0x00000000000000ff);
        pxbyte[1] = (byte) ((pxLong>>8)&0x00000000000000ff);
        pxbyte[2] = (byte) ((pxLong>>16)&0x00000000000000ff);
        pxbyte[3] = (byte) ((pxLong>>24)&0x00000000000000ff);
        pxbyte[4] = (byte) ((pxLong>>32)&0x00000000000000ff);
        pxbyte[5] = (byte) ((pxLong>>40)&0x00000000000000ff);
        pxbyte[6] = (byte) ((pxLong>>48)&0x00000000000000ff);
        pxbyte[7] = (byte) ((pxLong>>56)&0x00000000000000ff);

        long pxLongBack = (((long)pxbyte[0]&0x00000000000000ff)
                + (((long)pxbyte[1]&0x000000000000ff)<<8)
                + (((long)pxbyte[2]&0x000000000000ff)<<16)
                + (((long)pxbyte[3]&0x000000000000ff)<<24)
                + (((long)pxbyte[4]&0x000000000000ff)<<32)
                + (((long)pxbyte[5]&0x000000000000ff)<<40)
                + (((long)pxbyte[6]&0x000000000000ff)<<48)
                + (((long)pxbyte[7]&0x000000000000ff)<<56)
        );
        return pxLongBack;
    }




    /**
     * @param rSpeed 旋转速度
     * @param rJourney 旋转行程
     * @param endMode 结束模式
     * @return
     */
    //设置步进电机根据设定参数运动
    public static byte[] send1(int  rSpeed,int rJourney,int endMode){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(5);

        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeShort(change(rSpeed));
            dataOutputStream.writeShort(change(rJourney));
            dataOutputStream.writeByte(endMode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * @param rSpeed 数组运行指针
     * @param rJourney 运行时间
     * @param endMode 结束模式
     * @return
     */
    //设置步进电机进行位置缓存运动
    public static byte[] send2(int  rSpeed,int rJourney,int endMode){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(5);

        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeShort(change(rSpeed));
            dataOutputStream.writeShort(change(rJourney));
            dataOutputStream.writeByte(endMode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * @param way 存储方式
     * @param ints  步进电机运行数据表
     * @return
     */
    //设置步进电机运行位置表发送
    public static byte[] send3(int  way,int[] ints){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(5);

        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(way);
            for(int i=0;i<ints.length;i++){
                dataOutputStream.writeShort(change(ints[i]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }


    /**
     * @param mode  运动方式（0：向左旋转，1：向右旋转）
     * @param degree  旋转角度
     * @return
     */
    public static byte[] send4(int  mode,int degree){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(3);

        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(mode);
            dataOutputStream.writeShort(change(degree));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
    //底盘控制模块  速度控制
    public static byte[] send5(int  left,int right,int time){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(6);

        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeShort(change(left));
            dataOutputStream.writeShort(change(right));
            dataOutputStream.writeShort(change(time));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    //眼睛灯光的效果控制
    public static byte[] send6(int controlMode,int r1,int g1,int b1,int r2,int g2,int b2,int r3,int g3,int b3,int r4,int g4,int b4,
                               int r5,int g5,int b5,int r6,int g6,int b6,int r7,int g7,int b7,int r8,int g8,int b8){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(25);

        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(controlMode);
            dataOutputStream.writeByte(r1);
            dataOutputStream.writeByte(g1);
            dataOutputStream.writeByte(b1);
            dataOutputStream.writeByte(r2);
            dataOutputStream.writeByte(g2);
            dataOutputStream.writeByte(b2);
            dataOutputStream.writeByte(r3);
            dataOutputStream.writeByte(g3);
            dataOutputStream.writeByte(b3);
            dataOutputStream.writeByte(r4);
            dataOutputStream.writeByte(g4);
            dataOutputStream.writeByte(b4);
            dataOutputStream.writeByte(r5);
            dataOutputStream.writeByte(g5);
            dataOutputStream.writeByte(b5);
            dataOutputStream.writeByte(r6);
            dataOutputStream.writeByte(g6);
            dataOutputStream.writeByte(b6);
            dataOutputStream.writeByte(r7);
            dataOutputStream.writeByte(g7);
            dataOutputStream.writeByte(b7);
            dataOutputStream.writeByte(r8);
            dataOutputStream.writeByte(g8);
            dataOutputStream.writeByte(b8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    //耳朵灯光控制效果
    public static byte[] send7(int controlMode,int r1,int g1,int b1){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4);

        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(controlMode);
            dataOutputStream.writeByte(r1);
            dataOutputStream.writeByte(g1);
            dataOutputStream.writeByte(b1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    //头顶灯光效果控制
    public static byte[] send8(int controlMode,int r1,int g1,int b1){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4);

        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(controlMode);
            dataOutputStream.writeByte(r1);
            dataOutputStream.writeByte(g1);
            dataOutputStream.writeByte(b1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**声源增强
     * @param number 波束编号
     * @return
     */
    public static byte[] send9(int number){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(number);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**电机传感器校准参数采集
     * @param
     * @return
     */
    public static byte[] send10(int way,int maiChong,int degree){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(5);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(way);
            dataOutputStream.writeShort(change(maiChong));
            dataOutputStream.writeShort(change(degree));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**配置电机控制参数
     *
     */
    public static byte[] send11(int maxDegree,int minDegee,int maiChong,double p1,double p2,double p3,int way){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(38);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);


        try {
            dataOutputStream.writeShort(change(maxDegree*10));
            dataOutputStream.writeShort(change(minDegee*10));
            dataOutputStream.writeShort(change(maiChong));
            sendLong(dataOutputStream,p1);
            sendLong(dataOutputStream,p2);
            sendLong(dataOutputStream,p3);
            dataOutputStream.writeByte(way);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }


    private static void sendLong(DataOutputStream dataOutputStream, double p1){
        long pxLong = (long)(p1*10000000000l);
        byte[] pxbyte = new byte[8];
        pxbyte[0] = (byte) (pxLong&0x00000000000000ff);
        pxbyte[1] = (byte) ((pxLong>>8)&0x00000000000000ff);
        pxbyte[2] = (byte) ((pxLong>>16)&0x00000000000000ff);
        pxbyte[3] = (byte) ((pxLong>>24)&0x00000000000000ff);
        pxbyte[4] = (byte) ((pxLong>>32)&0x00000000000000ff);
        pxbyte[5] = (byte) ((pxLong>>40)&0x00000000000000ff);
        pxbyte[6] = (byte) ((pxLong>>48)&0x00000000000000ff);
        pxbyte[7] = (byte) ((pxLong>>56)&0x00000000000000ff);
        try {
            dataOutputStream.writeByte(pxbyte[0]);
            dataOutputStream.writeByte(pxbyte[1]);
            dataOutputStream.writeByte(pxbyte[2]);
            dataOutputStream.writeByte(pxbyte[3]);
            dataOutputStream.writeByte(pxbyte[4]);
            dataOutputStream.writeByte(pxbyte[5]);
            dataOutputStream.writeByte(pxbyte[6]);
            dataOutputStream.writeByte(pxbyte[7]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**电机传感器校准参数采集
     * @param
     * @return
     */
    public static byte[] send12(int speed){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeShort(change(speed));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**自主避障
     * @param status
     * @return
     */
    public static byte[] send13(int status){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(status);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }


    /**
     *  发送更新数据
     */
    public static byte[] send14(int indexData,byte[] data){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(11+data.length);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte('s');
            dataOutputStream.writeByte('t');
            dataOutputStream.writeByte('a');
            dataOutputStream.writeByte('r');
            dataOutputStream.writeByte('t');
            dataOutputStream.writeShort(change(FrameOne.crc16(data,data.length)));
            dataOutputStream.writeShort(change(indexData));
            dataOutputStream.writeShort(change(data.length));
            dataOutputStream.write(data,0,data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }


    /**
     *  更新数据发送完毕需要发送
     */
    public static byte[] send15(byte[] data){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(10);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte('s');
            dataOutputStream.writeByte('t');
            dataOutputStream.writeByte('a');
            dataOutputStream.writeByte('r');
            dataOutputStream.writeByte('t');
            dataOutputStream.writeByte('e');
            dataOutputStream.writeByte('n');
            dataOutputStream.writeByte('d');
            dataOutputStream.writeShort(change(FrameOne.crc16(data,data.length)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * @param endLoc 旋转速度
     * @param time 旋转行程
     * @param endMode 结束模式
     * @return
     */
    //设置步进电机目标位置运动
    public static byte[] send16(int  endLoc,int time,int endMode){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(5);

        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeShort(change(endLoc));
            dataOutputStream.writeShort(change(time));
            dataOutputStream.writeByte(endMode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }


    /** 开关无线麦
     * @param on_off
     * @return
     */
    public static byte[] send17(int on_off){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1);

        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(on_off);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /** 自由运动-不充电(1:开启      0：关闭)
     * @param control
     * @return
     */
    public static byte[] send18(int control){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1);

        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(control);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /** 设置电流大小
     * @return
     */
    public static byte[] send19(int electricity){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(12);

        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeShort(electricity);
            dataOutputStream.write(new byte[]{0,0,0,0,0,0,0,0,0,0});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * @param acceleratedSpeed  加速度
     * @param acceleratedAcceleratedSpeed   加加速度
     * @return
     */
    public static byte[] send20(int acceleratedSpeed,int acceleratedAcceleratedSpeed){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8);

        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeInt(acceleratedSpeed);
            dataOutputStream.writeInt(acceleratedAcceleratedSpeed);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * @param currentX
     * @param currentY
     * @param expectX
     * @param expectY
     * @param deflection
     * @return
     */
    public static byte[] send21(int currentX,int currentY,int expectX,int expectY,int deflection){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(18);

        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            byte[] bytes = changeFourBytes(currentX);
            byte[] bytes1 = changeFourBytes(currentY);
            byte[] bytes2 = changeFourBytes(expectX);
            byte[] bytes3 = changeFourBytes(expectY);
            dataOutputStream.write(bytes,0,bytes.length);
            dataOutputStream.write(bytes1,0,bytes1.length);
            dataOutputStream.write(bytes2,0,bytes2.length);
            dataOutputStream.write(bytes3,0,bytes3.length);
            dataOutputStream.writeShort(change(deflection));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }



}
