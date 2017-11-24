package com.xg.east.hibot_b_library.action;

import com.xg.east.hibot_b_library.SerialSend;
import com.xg.east.hibot_b_library.service.usbSendType.MotorPType;

import static com.xg.east.hibot_b_library.SerialSend.searchStatusInSleepTime;
import static com.xg.east.hibot_b_library.SerialSend.sendAllBytesToSerial;

/**
 * 项目名称：TestHibot_b_Jar
 * 创建人：East
 * 创建时间：2017/4/18 10:28
 * 邮箱：EastRiseWM@163.com
 */

public class HandNewAction {
    /**
     * 害羞
     */
    public synchronized static void shy() {
        synchronized (SerialSend.lock){
            SerialSend.isActionFinish=false;
            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -12000, 2000, 0);
            byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -500, 1000, 0);
            byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -2000, 1600, 0);
            //右臂
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 12000, 2000, 0);
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -500, 1000, 0);
            byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
            byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 4000, 1600, 0);
            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81);
            searchStatusInSleepTime(1000);

            //左臂
            byte[] bytesL22 = MotorPType.stepMpToEndLoc(0x0102, 11000, 1600, 0);
            //右臂
            byte[] bytesR72 = MotorPType.stepMpToEndLoc(0x0107, -11000, 1600, 0);
            //头部
//        byte[] bytesHA = MotorPType.stepMpParamsRunNotEndDegree(0x010A, -2000, 1000, 1);
            byte[] bytesHA = MotorPType.stepMpToEndLoc(0x010A, -18000/8, 1000, 1);
            sendAllBytesToSerial(bytesL22, bytesR72, bytesHA);
            searchStatusInSleepTime(4000);

            //摇头
//        byte[] bytesHB = MotorPType.stepMpParamsRunNotEndDegree(0x010B, -8000, 20000/8, 1);
//        sendAllBytesToSerial(bytesHB);
//        searchStatusInSleepTime(500);
//        byte[] bytesHB2 = MotorPType.stepMpParamsRunNotEndDegree(0x010B, 8000, 40000/8, 1);
//        sendAllBytesToSerial(bytesHB2);
//        searchStatusInSleepTime(800);
//        byte[] bytesHB3 = MotorPType.stepMpParamsRunNotEndDegree(0x010B, -8000, 40000/8, 1);
//        sendAllBytesToSerial(bytesHB3);
//        searchStatusInSleepTime(800);
//        byte[] bytesHB4 = MotorPType.stepMpParamsRunNotEndDegree(0x010B, 8000, 40000/8, 1);
//        sendAllBytesToSerial(bytesHB4);
//        searchStatusInSleepTime(800);

//        sendAllBytesToSerial(MotorPType.stepMpZero(0x010A, 5000));
            allStepToZero();
            searchStatusInSleepTime(2000);
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 鼓掌
     */
    public synchronized static void handclap() {
        synchronized (SerialSend.lock){
            SerialSend.isActionFinish = false;
            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -122000/8, 2000, 0);
            byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -2400/8, 1000, 0);
            byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, 20000/8, 1000, 0);
            //右臂
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 88000/8, 2000, 0);
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, 11500/8, 2000, 0);
            byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
            byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 35000/8, 1600, 0);
            sendAllBytesToSerial(bytesL01,bytesL11, bytesL21, bytesL31, bytesR51,bytesR61, bytesR71, bytesR81);
            searchStatusInSleepTime(1000);
            //左臂
            byte[] bytesL22 = MotorPType.stepMpToEndLoc(0x0102, 50500/8, 1000, 0);
            //右臂
            byte[] bytesR72 = MotorPType.stepMpToEndLoc(0x0107, -46000/8, 1000, 0);
            sendAllBytesToSerial(bytesL22, bytesR72);

            searchStatusInSleepTime(2000);
            handclapStep();
            handclapStep();
            handclapStep();
            SerialSend.isActionFinish = true;
        }
    }

    private static void handclapStep() {
        synchronized (SerialSend.lock){

            //左臂
            byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, 2000/8, 1000, 0);
            //右臂
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, 16000/8, 1000, 0);
            sendAllBytesToSerial(bytesL11, bytesR61);
            searchStatusInSleepTime(1200);
            //左臂
            byte[] bytesL12 = MotorPType.stepMpToEndLoc(0x0101, -2400/8, 1000, 0);
            //右臂
            byte[] bytesR62 = MotorPType.stepMpToEndLoc(0x0106, 11000/8, 1000, 0);
            sendAllBytesToSerial(bytesL12, bytesR62);
            searchStatusInSleepTime(1200);
        }
    }


    /**
     * 敬礼
     */
    public synchronized static void salute() {
        synchronized (SerialSend.lock){
            SerialSend.isActionFinish = false;
            //左臂
            byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 3000);
            byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 7000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
            //右臂
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 153000/8, 1500, 0);
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -40000/8, 1500, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -95000/8, 1500, 0);
            byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 13000/8, 1500, 0);
            sendAllBytesToSerial(bytesL01,bytesL11,bytesL21,bytesL31,bytesR51,bytesR61,bytesR71,bytesR81);
            searchStatusInSleepTime(5000);
            allStepToZero();
            searchStatusInSleepTime(1700);
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 超人
     */
    public synchronized static void superman(){
        synchronized (SerialSend.lock){
            SerialSend.isActionFinish = false;
            byte[] bytes = MotorPType.setElectricity(0x0105, 150);
            sendAllBytesToSerial(bytes);
            searchStatusInSleepTime(200);
            byte[] bytesL110 = MotorPType.stepMpToEndLoc(0x0101, -8000/8, 500, 0);
            sendAllBytesToSerial(bytesL110);
            searchStatusInSleepTime(700);
            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, 45000/8, 1500, 0);
            byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -3000/8, 1000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000/8, 1500, 0);
            byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, 66000/8, 1000, 0);
            //右臂
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 153000/8, 1500, 0);
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -3000/8, 1500, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000/8, 1500, 0);
            byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, -60000/8, 1500, 0);
            sendAllBytesToSerial(bytesL01,bytesL11,bytesL21,bytesL31,bytesR51,bytesR61,bytesR71,bytesR81);
            searchStatusInSleepTime(3000);
            byte[] bytes2 = MotorPType.setElectricity(0x0105, 80);
            byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);
//                byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL12 = MotorPType.stepMpToEndLoc(0x0101, -8000/8, 700, 0);
//                byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 7000);
            byte[] bytesL32 = MotorPType.stepMpZero(0x0103, 5000);
//                byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
            byte[] bytesR62 = MotorPType.stepMpZero(0x0106, 3000);
//                byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 7000);
            byte[] bytesR82 = MotorPType.stepMpZero(0x0108, 5000);

            byte[] bytesL02 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL22 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytesR52 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR72 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(bytes2,bytesHA1, bytesHB1, bytesL02, bytesL12, bytesL22, bytesL32, bytesR52, bytesR62, bytesR72, bytesR82);
            searchStatusInSleepTime(1700);
            byte[] bytesL13 = MotorPType.stepMpZero(0x0101, 3000);
            sendAllBytesToSerial(bytesL13);
            searchStatusInSleepTime(800);
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 咏春
     */
    public synchronized static void springChant(){
        synchronized (SerialSend.lock){
            SerialSend.isActionFinish = false;
            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -65000/8, 1500, 0);
            byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -6000/8, 1000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 52000/8, 1500, 0);
            byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -3000/8, 1000, 0);
            //右臂
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 85000/8, 1500, 0);
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -3000/8, 1500, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -6000/8, 1500, 0);
            byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 14000/8, 1500, 0);
            sendAllBytesToSerial(bytesL01,bytesL11,bytesL21,bytesL31,bytesR51,bytesR61,bytesR71,bytesR81);
            searchStatusInSleepTime(4000);
            allStepToZero();
            searchStatusInSleepTime(1700);
            SerialSend.isActionFinish = true;
        }
    }


    /**
     * 胜利（抄袭pepper）
     */
    public synchronized static void victory(){
        synchronized (SerialSend.lock){
            SerialSend.isActionFinish = false;
            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -46000/8, 800, 0);
            byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -6810/8, 800, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 90270/8, 800, 0);
            byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -61560/8, 800, 0);
            //右臂
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 55000/8, 800, 0);
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -1000/8, 800, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -85800/8, 800, 0);
            byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 67610/8, 800, 0);
            sendAllBytesToSerial(bytesL01,bytesL11,bytesL21,bytesL31,bytesR51,bytesR61,bytesR71,bytesR81);
            searchStatusInSleepTime(1200);


            //头部
            byte[] bytesHA1 = MotorPType.stepMpToEndLoc(0x010A, -18000/8, 800, 1);
            //左臂
            byte[] bytesL02 = MotorPType.stepMpToEndLoc(0x0100, -9910/8, 500, 0);
            byte[] bytesL12 = MotorPType.stepMpToEndLoc(0x0101, -5820/8, 800, 0);
            byte[] bytesL22 = MotorPType.stepMpToEndLoc(0x0102, 90800/8, 800, 0);
            byte[] bytesL32 = MotorPType.stepMpToEndLoc(0x0103, -59810/8, 800, 0);
            //右臂
            byte[] bytesR52 = MotorPType.stepMpToEndLoc(0x0105, 23410/8, 800, 0);
            byte[] bytesR62 = MotorPType.stepMpToEndLoc(0x0106, 3530/8, 800, 0);
            byte[] bytesR72 = MotorPType.stepMpToEndLoc(0x0107, -85800/8, 800, 0);
            byte[] bytesR82 = MotorPType.stepMpToEndLoc(0x0108, 67610/8, 800, 0);
            sendAllBytesToSerial(bytesHA1,bytesL02,bytesL12,bytesL22,bytesL32,bytesR52,bytesR62,bytesR72,bytesR82);
            searchStatusInSleepTime(1000);
            SerialSend.isActionFinish = true;
        }

    }

    /**
     * 朝左边讲PPT
     */
    public synchronized static void toLeftSpeakPPT(){
        synchronized (SerialSend.lock){
            SerialSend.isActionFinish = false;
            //头部
            byte[] bytesHA1 = MotorPType.stepMpToEndLoc(0x010A,10560/8,800,0);
            byte[] bytesHB1 = MotorPType.stepMpToEndLoc(0x010B, 34790/8, 800, 0);
            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -141450/8, 1600, 0);
            byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -46980/8, 800, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 23430/8, 800, 0);
            byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -2920/8, 800, 0);
            //右臂
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 3000);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
            sendAllBytesToSerial(bytesHA1,bytesHB1,bytesL01,bytesL11,bytesL21,bytesL31,bytesR51,bytesR61,bytesR71,bytesR81);
            searchStatusInSleepTime(4800);
            allStepToZero();
            SerialSend.isActionFinish = true;
        }
    }


    /**
     * 朝右边讲PPT
     */
    public synchronized static void toRightSpeakPPT(){
        synchronized (SerialSend.lock){
            SerialSend.isActionFinish = false;
            //头部
            byte[] bytesHA1 = MotorPType.stepMpToEndLoc(0x010A,10560/8,800,0);
            byte[] bytesHB1 = MotorPType.stepMpToEndLoc(0x010B, -34790/8, 800, 0);
            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 3000);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
            //右臂
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 141450/8, 1600, 0);
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -46980/8, 800, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -23430/8, 800, 0);
            byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 2920/8, 800, 0);
            sendAllBytesToSerial(bytesHA1,bytesHB1,bytesL01,bytesL11,bytesL21,bytesL31,bytesR51,bytesR61,bytesR71,bytesR81);
            searchStatusInSleepTime(2800);
            allStepToZero();
            SerialSend.isActionFinish = true;
        }
    }
    
    


    /**
     * 所有动作归零运动
     */
    public synchronized static void allStepToZero() {
        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);
//                byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 3000);
//                byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 7000);
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
//                byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 3000);
//                byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 7000);
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

        sendAllBytesToSerial(bytesHA1, bytesHB1, bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81);
    }

    public static void allStepToZero(int speedA, int speedB, int time0, int spend1, int time2, int spend3, int time5, int spend6, int time7, int spend8) {
        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, speedA);
        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, speedB);
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, spend0);
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, spend1);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, spend2);
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, spend3);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, spend5);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, spend6);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, spend7);
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, spend8);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, time0, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, time2, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, time5, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, time7, 0);

        sendAllBytesToSerial(bytesHA1, bytesHB1, bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81);
    }

    public synchronized static void allStepToZeroExceptHead() {
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81);
    }

    public synchronized static void allStepToZeroExceptHead(int time0, int spend1, int time2, int spend3, int time5, int spend6, int time7, int spend8) {
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, spend0);
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, spend1);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, spend2);
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, spend3);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, spend5);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, spend6);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, spend7);
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, spend8);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, time0, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, time2, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, time5, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, time7, 0);

        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81);
    }


}
