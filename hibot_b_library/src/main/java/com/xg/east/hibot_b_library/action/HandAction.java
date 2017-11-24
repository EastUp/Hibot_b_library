package com.xg.east.hibot_b_library.action;

import android.content.Context;

import com.xg.east.hibot_b_library.SerialSend;
import com.xg.east.hibot_b_library.service.usbSendType.ClassicType;
import com.xg.east.hibot_b_library.service.usbSendType.LightType;
import com.xg.east.hibot_b_library.service.usbSendType.MotorPType;

import static com.xg.east.hibot_b_library.SerialSend.mAMaxDegree;
import static com.xg.east.hibot_b_library.SerialSend.mARDgree;
import static com.xg.east.hibot_b_library.SerialSend.searchStatusInSleepTime;
import static com.xg.east.hibot_b_library.SerialSend.sendAllBytesToSerial;

/**
 * Created by EastRiseWM on 2017/1/18.                  手部动作
 */

public class HandAction {
    /*拜拜*/
    public synchronized static void handSeeYouNow() {

        synchronized (SerialSend.lock) {
            SerialSend.isActionFinish = false;
//        byte[] bytes = MotorPType.stepMpParamsRun(0x0105, 5000, 13000, 0);
//        byte[] bytes1 = MotorPType.stepMpParamsRun(0x0106, -5000, 2000, 0);
//        byte[] bytes2 = MotorPType.stepMpParamsRun(0x0108, -5000, 7000, 0);
//        byte[] bytes3 = MotorPType.stepMpParamsRun(0x0107, 5000, 1, 0);
            byte[] bytes = MotorPType.stepMpToEndLoc(0x0105, 18000, 2600, 0);
            byte[] bytes1 = MotorPType.stepMpToEndLoc(0x0106, -2000, 400, 0);
            byte[] bytes2 = MotorPType.stepMpToEndLoc(0x0108, -7000, 1400, 0);
            byte[] bytes3 = MotorPType.stepMpZero(0x0107, 5000);
            sendAllBytesToSerial(bytes, bytes1, bytes2, bytes3);
            searchStatusInSleepTime(2800);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            xuanzhuangNow();
            SerialSend.isActionFinish = true;
        }
    }

    public synchronized static void xuanzhuangNow() {
        synchronized (SerialSend.lock) {

//        byte[] bytes2 = MotorPType.stepMpParamsRun(0x0106, 5000, 4000, 0);
            byte[] bytes2 = MotorPType.stepMpToEndLoc(0x0106, 2000, 800, 0);
            sendAllBytesToSerial(bytes2);
            searchStatusInSleepTime(1500);
//        byte[] bytes3 = MotorPType.stepMpParamsRun(0x0106, -5000, 4000, 0);
            byte[] bytes3 = MotorPType.stepMpToEndLoc(0x0106, -2000, 800, 0);
            sendAllBytesToSerial(bytes3);
            searchStatusInSleepTime(1500);
//        byte[] bytes = MotorPType.stepMpParamsRun(0x0106, 5000, 4000, 0);
            byte[] bytes = MotorPType.stepMpToEndLoc(0x0106, 2000, 800, 0);
            sendAllBytesToSerial(bytes);
            searchStatusInSleepTime(1500);
//        byte[] bytes4 = MotorPType.stepMpParamsRun(0x0106, -5000, 4000, 1);
            byte[] bytes4 = MotorPType.stepMpToEndLoc(0x0106, -2000, 800, 1);
            sendAllBytesToSerial(bytes4);
            searchStatusInSleepTime(1500);
            byte[] bytes51 = MotorPType.stepMpZero(0x0106, 5000);
            byte[] bytes6 = MotorPType.stepMpZero(0x0108, 5000);
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytes5 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytes7 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);
            sendAllBytesToSerial(bytesL01, bytesL21, bytes5, bytes51, bytes6, bytes7);
            searchStatusInSleepTime(2800);
        }
    }

    /*拥抱*/
    public synchronized static void hugNow() {
        synchronized (SerialSend.lock) {
            SerialSend.isActionFinish = false;
            //左臂
            byte[] bytesl = MotorPType.stepMpToEndLoc(0x0100, -13000, 2600, 0);
            byte[] bytes2l = MotorPType.stepMpToEndLoc(0x0102, 2000, 400, 0);
            byte[] bytes22 = MotorPType.stepMpToEndLoc(0x0101, -5000, 1000, 0);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
            //右臂
            byte[] bytes = MotorPType.stepMpToEndLoc(0x0105, 13000, 2600, 0);
            byte[] bytes2 = MotorPType.stepMpToEndLoc(0x0107, -2000, 400, 0);
            byte[] bytes3 = MotorPType.stepMpToEndLoc(0x0106, -5000, 1000, 0);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
            sendAllBytesToSerial(bytesl, bytes2l, bytes22, bytesL31, bytes, bytes2, bytes3, bytesR81);
            searchStatusInSleepTime(3900);
//        try {
//            Thread.sleep(3900);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytes51 = MotorPType.stepMpParamsRun(0x0101, 5000, 3000, 1);
//        byte[] bytes32 = MotorPType.stepMpParamsRun(0x0106, 5000, 3000, 1);
            byte[] bytes51 = MotorPType.stepMpToEndLoc(0x0101, -2000, 600, 1);
            byte[] bytes32 = MotorPType.stepMpToEndLoc(0x0106, -2000, 600, 1);
            sendAllBytesToSerial(bytes51, bytes32);
            searchStatusInSleepTime(2800);
//        try {
//            Thread.sleep(2800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //左臂
//        byte[] byte3l = MotorPType.stepMpZero(0x0100, 5000);
//        byte[] bytes4l = MotorPType.stepMpZero(0x0102, 5000);
//        //右臂
//        byte[] byte3 = MotorPType.stepMpZero(0x0105, 5000);
//        byte[] bytes4 = MotorPType.stepMpZero(0x0107, 5000);

            byte[] byte3l = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytes4l = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] byte3 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytes4 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(byte3l, bytes4l, byte3, bytes4);
            searchStatusInSleepTime(2700);
//        try {
//            Thread.sleep(2700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //添加的
            allStepToZero();
            searchStatusInSleepTime(500);
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*握手*/
    public synchronized static void handShakeNow() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
            //右臂
//        byte[] bytes = MotorPType.stepMpParamsRun(0x0105, 5000, 8000, 0);
//        byte[] bytes3 = MotorPType.stepMpParamsRun(0x0106, -5000, 3000, 0);
//        byte[] bytes2 = MotorPType.stepMpParamsRun(0x0107, -5000, 3000, 0);
            byte[] bytes = MotorPType.stepMpToEndLoc(0x0105, 8000, 1600, 0);
            byte[] bytes3 = MotorPType.stepMpToEndLoc(0x0106, -3000, 600, 0);
            byte[] bytes2 = MotorPType.stepMpToEndLoc(0x0107, -3000, 600, 0);
            sendAllBytesToSerial(bytes, bytes3, bytes2);
            searchStatusInSleepTime(6000);
//        try {
//            Thread.sleep(6000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //添加的
            allStepToZero();
            searchStatusInSleepTime(1700);
//        try {
//            Thread.sleep(1700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*招手*/
    public synchronized static void zhaoshouNow() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
            //左臂
//        byte[] bytesl = MotorPType.stepMpParamsRun(0x0100, -5000, 7000, 0);
//        byte[] bytesl1 = MotorPType.stepMpParamsRun(0x0101, -5000, 2000, 0);
//        byte[] bytes2l = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
            byte[] bytesl = MotorPType.stepMpToEndLoc(0x0100, -7000, 1400, 0);
            byte[] bytesl1 = MotorPType.stepMpToEndLoc(0x0101, -2000, 400, 0);
            byte[] bytes2l = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            sendAllBytesToSerial(bytesl, bytesl1, bytes2l);
            searchStatusInSleepTime(1800);
//        try {
//            Thread.sleep(1900);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytes31 = MotorPType.stepMpParamsRun(0x0102, 8000, 8000, 0);
            byte[] bytes31 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1000, 0);
            sendAllBytesToSerial(bytes31);
            searchStatusInSleepTime(1100);
//        try {
//            Thread.sleep(1100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytes32 = MotorPType.stepMpParamsRun(0x0102, -8000, 8000, 0);
            byte[] bytes32 = MotorPType.stepMpToEndLoc(0x0102, 0, 1000, 0);
            sendAllBytesToSerial(bytes32);
            searchStatusInSleepTime(1100);
//        try {
//            Thread.sleep(1100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytes33 = MotorPType.stepMpParamsRun(0x0102, 8000, 8000, 1);
            byte[] bytes33 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1000, 1);
            sendAllBytesToSerial(bytes33);
            searchStatusInSleepTime(1100);
//        try {
//            Thread.sleep(1100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            byte[] bytes34 = MotorPType.stepMpZero(0x0102, 8000);
            sendAllBytesToSerial(bytes34);
            searchStatusInSleepTime(1100);
//        try {
//            Thread.sleep(1100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //添加的
            allStepToZero();
            searchStatusInSleepTime(1900);
            //右臂
//        try {
//            Thread.sleep(1900);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*抱拳*/
    public synchronized static void hipsNow() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
            //左臂
//        byte[] bytesl = MotorPType.stepMpParamsRun(0x0100, -5000, 9000, 0);
//        byte[] bytesl1 = MotorPType.stepMpParamsRun(0x0101, -5000, 4000, 0);
//        byte[] bytes2l = MotorPType.stepMpParamsRun(0x0102, 5000, 7500, 0);
//        byte[] bytes22 = MotorPType.stepMpParamsRun(0x0104, 5000, 6500, 0);
//        //右臂
//        byte[] bytes = MotorPType.stepMpParamsRun(0x0105, 5000, 9000, 0);
//        byte[] bytes3 = MotorPType.stepMpParamsRun(0x0106, -5000, 4000, 0);
//        byte[] bytes2 = MotorPType.stepMpParamsRun(0x0107, -4000, 6000, 0);
            byte[] bytesl = MotorPType.stepMpToEndLoc(0x0100, -9000, 1800, 0);
            byte[] bytesl1 = MotorPType.stepMpToEndLoc(0x0101, -4000, 800, 0);
            byte[] bytes2l = MotorPType.stepMpToEndLoc(0x0102, 7500, 1500, 0);
//        byte[] bytes22 = MotorPType.stepMpToEndLoc(0x0104, 6500, 1300, 0);
            //右臂
            byte[] bytes = MotorPType.stepMpToEndLoc(0x0105, 9000, 1800, 0);
            byte[] bytes3 = MotorPType.stepMpToEndLoc(0x0106, -4000, 800, 0);
            byte[] bytes2 = MotorPType.stepMpToEndLoc(0x0107, -6000, 1500, 0);
            sendAllBytesToSerial(bytesl, bytesl1, bytes2l/*,bytes22*/, bytes, bytes3, bytes2);
            searchStatusInSleepTime(3000);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] byte3l = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesl2 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytes4l = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytes52 = MotorPType.stepMpZero(0x0104, 5000);
//        byte[] byte3 = MotorPType.stepMpZero(0x0105, 5000);
            byte[] bytes31 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytes4 = MotorPType.stepMpZero(0x0107, 4000);
            //添加的
            byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);

            byte[] byte3l = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytes4l = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] byte3 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytes4 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(byte3l, bytesl2, bytes4l, bytes52, byte3, bytes31, bytes4, bytesHA1, bytesHB1, bytesL31, bytesR81);
            searchStatusInSleepTime(1900);
//        try {
//            Thread.sleep(1900);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*叉腰*/
    public synchronized static void akimbo() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 4000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 5000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 8000, 0);
//        byte[] bytesL4 = MotorPType.stepMpParamsRun(0x0104, 5000, 4000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 4000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 5000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 8000, 0);
//        byte[] bytesR9 = MotorPType.stepMpParamsRun(0x0109, 5000, 4000, 0);
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -5000, 1000, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1600, 0);
//        byte[] bytesL4 = MotorPType.stepMpToEndLoc(0x0104, 4000, 800, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -5000, 1000, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -8000, 1600, 0);
//        byte[] bytesR9 = MotorPType.stepMpToEndLoc(0x0109, 4000, 800, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2/*,bytesL4*/, bytesR5, bytesR6, bytesR7/*,bytesR9*/);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //左臂
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 3000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytesL41 = MotorPType.stepMpZero(0x0104, 5000);
            //右臂
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 3000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
            byte[] bytesR91 = MotorPType.stepMpZero(0x0109, 5000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1500, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 1500, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1500, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 1500, 0);


            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL41, bytesR51, bytesR61, bytesR71, bytesR91);
            searchStatusInSleepTime(1700);
//        try {
//            Thread.sleep(1700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*左手叉腰，右手向天，头向右上方望*/
    public synchronized static void leftAkimboRightSee() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 4000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 5000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 8000, 0);
//        byte[] bytesL4 = MotorPType.stepMpParamsRun(0x0104, 5000, 4000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 4000, 13000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 3000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, 5000, 1, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 3000, 0);
//        //头部
//        byte[] bytesHR1 = MotorPType.stepMpParamsRun(0x010B, -5000, 3000, 0);
//        byte[] bytesHR2 = MotorPType.stepMpParamsRun(0x010A, 5000, 4000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -5000, 1000, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1600, 0);
//        byte[] bytesL4 = MotorPType.stepMpToEndLoc(0x0104, 4000, 800, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 13000, 3250, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -3000, 600, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, 1, 1000, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 3000, 600, 0);
            //头部
            byte[] bytesHR1 = MotorPType.stepMpToEndLoc(0x010B, -3000, 600, 0);
            byte[] bytesHR2 = MotorPType.stepMpToEndLoc(0x010A, 4000, 800, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2/*,bytesL4*/, bytesR5, bytesR6, bytesR7, bytesR8, bytesHR1, bytesHR2);
            searchStatusInSleepTime(3300);
//        try {
//            Thread.sleep(3300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 3000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytesL41 = MotorPType.stepMpZero(0x0104, 5000);
            //右臂
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
            //头部
            byte[] bytesHR11 = MotorPType.stepMpZero(0x010B, 5000);
            byte[] bytesHR21 = MotorPType.stepMpZero(0x010A, 5000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL41, bytesR51, bytesR61, bytesR71, bytesR81, bytesHR11, bytesHR21);
            searchStatusInSleepTime(2700);
//        try {
//            Thread.sleep(2700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*右手叉腰，左手向天，头向左上方望*/
    public synchronized static void rightAkimboLeftsSee() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -4000, 13000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 3000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 1, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
//        //头部
//        byte[] bytesHR1 = MotorPType.stepMpParamsRun(0x010B, 5000, 3000, 0);
//        byte[] bytesHR2 = MotorPType.stepMpParamsRun(0x010A, 5000, 4000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 4000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 5000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 8000, 0);
//        byte[] bytesR9 = MotorPType.stepMpParamsRun(0x0109, 5000, 4000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -13000, 3250, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 1, 1000, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            //头部
            byte[] bytesHR1 = MotorPType.stepMpToEndLoc(0x010B, 3000, 600, 0);
            byte[] bytesHR2 = MotorPType.stepMpToEndLoc(0x010A, 4000, 800, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -5000, 1000, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -8000, 1600, 0);
//        byte[] bytesR9 = MotorPType.stepMpToEndLoc(0x0109, 4000, 800, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesHR1, bytesHR2, bytesR5, bytesR6, bytesR7/*,bytesR9*/);
            searchStatusInSleepTime(3300);
//        try {
//            Thread.sleep(3300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //左臂
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
            //头部
            byte[] bytesHR11 = MotorPType.stepMpZero(0x010B, 5000);
            byte[] bytesHR21 = MotorPType.stepMpZero(0x010A, 5000);
            //右臂
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 3000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
            byte[] bytesR91 = MotorPType.stepMpZero(0x0109, 5000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesHR11, bytesHR21, bytesR51, bytesR61, bytesR71, bytesR91);
            searchStatusInSleepTime(2700);
//        try {
//            Thread.sleep(2700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*左上右下*/
    public synchronized static void leftUpRightDown() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 11000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 8000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 9000, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
//        //头部
//        byte[] bytesHR1 = MotorPType.stepMpParamsRun(0x010B, 5000, 3000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 4000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 5000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 6000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -11000, 2200, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -8000, 1600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 9000, 1800, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            //头部
            byte[] bytesHR1 = MotorPType.stepMpToEndLoc(0x010B, 3000, 600, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -5000, 1000, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -6000, 1200, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesHR1, bytesR5, bytesR6, bytesR7);
            searchStatusInSleepTime(2300);
//        try {
//            Thread.sleep(2300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
            byte[] bytesHR11 = MotorPType.stepMpZero(0x010B, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 4000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
            //添加的
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
            byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);
            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesHR11, bytesR51, bytesR61, bytesR71, bytesR81, bytesHA1);
            searchStatusInSleepTime(2300);
//        try {
//            Thread.sleep(2300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*右上左下*/
    public synchronized static void rightUpLeftDown() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 4000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 5000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 6000, 0);
//        //头部
//        byte[] bytesHR1 = MotorPType.stepMpParamsRun(0x010B, -5000, 3000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 11000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 8000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 9000, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 5000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -5000, 1000, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 6000, 1200, 0);
            //头部
            byte[] bytesHR1 = MotorPType.stepMpToEndLoc(0x010B, -3000, 600, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 11000, 2200, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -8000, 1600, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -9000, 1800, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 5000, 1000, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesHR1, bytesR5, bytesR6, bytesR7, bytesR8);
            searchStatusInSleepTime(2300);
//        try {
//            Thread.sleep(2300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 4000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytesHR11 = MotorPType.stepMpZero(0x010B, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
            //添加的
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
            byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesHR11, bytesR51, bytesR61, bytesR71, bytesR81, bytesL31, bytesHA1);
            searchStatusInSleepTime(2300);
//        try {
//            Thread.sleep(2300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*一起上*/
    public synchronized static void leftRightUp() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 11000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 8000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 9000, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 11000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 8000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 9000, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 5000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -11000, 2200, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -8000, 1600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 9000, 1800, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 11000, 2200, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -8000, 1600, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -9000, 1800, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 5000, 1000, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesR5, bytesR6, bytesR7, bytesR8);
            searchStatusInSleepTime(2300);
//        try {
//            Thread.sleep(2300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
            //添加的
            byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81, bytesHA1, bytesHB1);
            searchStatusInSleepTime(2300);
//        try {
//            Thread.sleep(2300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*左前右后*/
    public synchronized static void zuoQianYouHou() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 5000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 3000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 7000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, -5000, 5000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 3000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -5000, 1000, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 7000, 1400, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, -5000, 1000, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -3000, 600, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesR5, bytesR6);
            searchStatusInSleepTime(1700);
//        try {
//            Thread.sleep(1700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 3000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 2000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 2000);
            //添加的
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
            byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1500, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 1500, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1500, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 1500, 0);

            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesR51, bytesR61, bytesL31, bytesR71, bytesR81, bytesHA1, bytesHB1);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*右前左后*/
    public synchronized static void youQianZouHou() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, 5000, 5000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 3000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 5000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 3000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 7000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, 5000, 1000, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 5000, 1000, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -3000, 600, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -7000, 1400, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesR5, bytesR6, bytesR7);
            searchStatusInSleepTime(1700);
//        try {
//            Thread.sleep(1700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 2000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 3000);
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 2000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 6000);
            //添加的
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
            byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1500, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 1500, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1500, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 1500, 0);

            sendAllBytesToSerial(bytesL01, bytesL11, bytesR51, bytesR61, bytesR71, bytesL21, bytesL31, bytesR81, bytesHA1, bytesHB1);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    public synchronized static void talkMotion2() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 2000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 2000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 9000, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 2000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 2000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 7000, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 3000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -2000, 400, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -2000, 400, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 9000, 1800, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 2000, 400, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -2000, 400, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -7000, 1400, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 3000, 600, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesR5, bytesR6, bytesR7, bytesR8);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //左臂
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 1000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 1000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5500);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
            //右臂
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 1000);
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 1000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5500);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
            //头部
            byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1500, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 1500, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1500, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 1500, 0);

            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81, bytesHA1, bytesHB1);
            searchStatusInSleepTime(2100);
//        try {
//            Thread.sleep(2100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }


    /*奥特曼姿势，右边起*/
    public synchronized static void atemanRightUp() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 7000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 3000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 9000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 8000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 3000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 10000, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 7000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -7000, 1400, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 9000, 1800, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 8000, 1600, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -3000, 600, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -10000, 2000, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 7000, 1400, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesR5, bytesR6, bytesR7, bytesR8);
            searchStatusInSleepTime(2500);
//        try {
//            Thread.sleep(2500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //左臂
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 3000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 3000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            //右臂
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 3000);
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 6000);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 6000);

            //添加
            byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1500, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 1500, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1500, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 1500, 0);

            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesR51, bytesR61, bytesR71, bytesR81, bytesHA1, bytesHB1, bytesL31);
            searchStatusInSleepTime(2700);
//        try {
//            Thread.sleep(2700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*奥特曼姿势，左边起*/
    public synchronized static void atemanLeftUp() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 8000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 3000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 10000, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 7000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 7000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 3000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 9000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -8000, 1600, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 10000, 2000, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -7000, 1400, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 7000, 1400, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -3000, 600, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -9000, 1800, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesR5, bytesR6, bytesR7);
            searchStatusInSleepTime(2500);
//        try {
//            Thread.sleep(2500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //左臂
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 3000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 6000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 6000);
            //右臂
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 3000);
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 3000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
            //添加的
            byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesHA1, bytesHB1, bytesR81);
            searchStatusInSleepTime(2700);
//        try {
//            Thread.sleep(2700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }


    /*左上*/
    public synchronized static void leftUp() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 11000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 8000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 9000, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
//        //头部
//        byte[] bytesHR1 = MotorPType.stepMpParamsRun(0x010B, 5000, 3000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -11000, 2200, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -8000, 1600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 9000, 1800, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            //头部
            byte[] bytesHR1 = MotorPType.stepMpToEndLoc(0x010B, 3000, 600, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesHR1);
            searchStatusInSleepTime(5000);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            allStepToZero();
            searchStatusInSleepTime(2300);
//        try {
//            Thread.sleep(2300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*两边一起下*/
    public synchronized static void allDown() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 4000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 5000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 6000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 4000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 5000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 6000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -5000, 1000, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 6000, 1200, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -5000, 1000, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -6000, 1200, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesR5, bytesR6, bytesR7);
            searchStatusInSleepTime(1300);
//        try {
//            Thread.sleep(1300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            allStepToZero();
            searchStatusInSleepTime(1300);
//        try {
//            Thread.sleep(1300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*下右摆*/
    public synchronized static void downBaiRight() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 4000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 5000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 9000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 4000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 5000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 3000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -5000, 1000, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 9000, 1800, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -5000, 1000, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -3000, 600, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesR5, bytesR6, bytesR7);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //左臂
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 2000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 2500);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            //右臂
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
            //添加
            byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesR51, bytesR61, bytesR71, bytesHA1, bytesHB1, bytesL31, bytesR81);
            searchStatusInSleepTime(2100);
//        try {
//            Thread.sleep(2100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*下左摆*/
    public synchronized static void downBaiLeft() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 4000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 5000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 3000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 4000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 5000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 9000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -5000, 1000, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 3000, 600, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -5000, 1000, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -9000, 1800, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesR5, bytesR6, bytesR7);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //左臂
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            //右臂
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 2000);
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 2500);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
            //添加
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
            byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesR51, bytesR61, bytesR71, bytesL31, bytesR81, bytesHA1, bytesHB1);
            searchStatusInSleepTime(2100);
//        try {
//            Thread.sleep(2100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }


    /*一起看的*/
    public synchronized static void allSee() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -4000, 13000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 3000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 1, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
//        //头部
//        byte[] bytesHR2 = MotorPType.stepMpParamsRun(0x010A, 5000, 4000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 4000, 13000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 3000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, 5000, 1, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 3000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -18000, 1000, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 1, 1000, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            //头部
            byte[] bytesHR2 = MotorPType.stepMpToEndLoc(0x010A, 4000, 800, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 18000, 1000, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -3000, 600, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, 1, 1000, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 3000, 600, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesHR2, bytesR5, bytesR6, bytesR7, bytesR8);
            searchStatusInSleepTime(3350);
//        try {
//            Thread.sleep(3350);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            allStepToZero();
            searchStatusInSleepTime(3350);
//        try {
//            Thread.sleep(3350);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }


    /*一起向上，右小臂左摆，头左看*/
    public synchronized static void allSeeHeadSeeLeft() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -4000, 13000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 3000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 1, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
//        //头部
//        byte[] bytesHR1 = MotorPType.stepMpParamsRun(0x010B, 5000, 3000, 0);
//        byte[] bytesHR2 = MotorPType.stepMpParamsRun(0x010A, 5000, 4000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 4000, 13000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 3000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 8000, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 3000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -13000, 3250, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 1, 1000, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            //头部
            byte[] bytesHR1 = MotorPType.stepMpToEndLoc(0x010B, 3000, 600, 0);
            byte[] bytesHR2 = MotorPType.stepMpToEndLoc(0x010A, 4000, 800, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 13000, 3250, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -3000, 600, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -8000, 1600, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 3000, 600, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesHR1, bytesHR2, bytesR5, bytesR6, bytesR7, bytesR8);
            searchStatusInSleepTime(3350);
//        try {
//            Thread.sleep(3350);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            allStepToZero();
            searchStatusInSleepTime(3350);
//        try {
//            Thread.sleep(3350);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*一起向上，左小臂右摆，头右看*/
    public synchronized static void allSeeHeadSeeRight() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -4000, 13000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 3000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 8000, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
//        //头部
//        byte[] bytesHR1 = MotorPType.stepMpParamsRun(0x010B, -5000, 3000, 0);
//        byte[] bytesHR2 = MotorPType.stepMpParamsRun(0x010A, 5000, 4000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 4000, 13000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 3000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, 5000, 1, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 3000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -13000, 3250, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1600, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            //头部
            byte[] bytesHR1 = MotorPType.stepMpToEndLoc(0x010B, -3000, 600, 0);
            byte[] bytesHR2 = MotorPType.stepMpToEndLoc(0x010A, 4000, 800, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 13000, 3250, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -3000, 600, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, 1, 1000, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 3000, 600, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesHR1, bytesHR2, bytesR5, bytesR6, bytesR7, bytesR8);
            searchStatusInSleepTime(3350);
//        try {
//            Thread.sleep(3350);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            allStepToZero();
            searchStatusInSleepTime(3350);
//        try {
//            Thread.sleep(3350);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*一起看的*/
    public synchronized static void groupPhoto() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -4000, 13000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 3000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 1, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
//        //头部
//        byte[] bytesHR2 = MotorPType.stepMpParamsRun(0x010A, 5000, 4000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 4000, 13000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 3000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, 5000, 1, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 3000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -13000, 3250, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 1, 1000, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            //头部
            byte[] bytesHR2 = MotorPType.stepMpToEndLoc(0x010A, 4000, 800, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 13000, 3250, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -3000, 600, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, 1, 1000, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 3000, 600, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesHR2, bytesR5, bytesR6, bytesR7, bytesR8);
            searchStatusInSleepTime(12000);
//        try {
//            Thread.sleep(12000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            allStepToZero();
            searchStatusInSleepTime(3350);
//        try {
//            Thread.sleep(3350);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 自我介绍的摊手+拍胸脯
     */
    public synchronized static void tanShou() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;

            //头部
            byte[] bytesA = new byte[0];
            if (SerialSend.mARDgree < SerialSend.mAMaxDegree) {
                bytesA = MotorPType.stepMpParamsRunNotEndDegree(0x010A, 1500, mAMaxDegree - mARDgree, 1);
            }

//        //左臂
//        byte[] bytesl = MotorPType.stepMpParamsRun(0x0100, -5000, 6000, 0);
//        byte[] bytes22 = MotorPType.stepMpParamsRun(0x0101, -5000, 4000, 0);
//        byte[] bytes2l = MotorPType.stepMpParamsRun(0x0102, 5000, 4000, 0);
//        byte[] bytes23 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
//        //右臂
//        byte[] bytes = MotorPType.stepMpParamsRun(0x0105, 5000, 6000, 0);
//        byte[] bytes3 = MotorPType.stepMpParamsRun(0x0106, -5000, 4000, 0);
//        byte[] bytes2 = MotorPType.stepMpParamsRun(0x0107, -5000, 4000, 0);
//        byte[] bytes4 = MotorPType.stepMpParamsRun(0x0108, 5000, 5000, 0);
            //左臂
            byte[] bytesl = MotorPType.stepMpToEndLoc(0x0100, -6000, 1200, 0);
            byte[] bytes22 = MotorPType.stepMpToEndLoc(0x0101, -4000, 800, 0);
            byte[] bytes2l = MotorPType.stepMpToEndLoc(0x0102, 4000, 800, 0);
            byte[] bytes23 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            //右臂
            byte[] bytes = MotorPType.stepMpToEndLoc(0x0105, 6000, 1200, 0);
            byte[] bytes3 = MotorPType.stepMpToEndLoc(0x0106, -4000, 800, 0);
            byte[] bytes2 = MotorPType.stepMpToEndLoc(0x0107, -4000, 800, 0);
            byte[] bytes4 = MotorPType.stepMpToEndLoc(0x0108, 5000, 1000, 0);
            sendAllBytesToSerial(bytesA, bytesl, bytes2l, bytes22, bytes23, bytes, bytes2, bytes3, bytes4);
            searchStatusInSleepTime(1400);
//        try {
//            Thread.sleep(1400);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //左臂
//        byte[] bytes61 = MotorPType.stepMpParamsRun(0x0100, 5000, 6000, 1);
//        byte[] bytes63 = MotorPType.stepMpParamsRun(0x0101, 5000, 4000, 1);
//        byte[] bytes62 = MotorPType.stepMpParamsRun(0x0102, -5000, 4000, 1);
//        byte[] bytes223 = MotorPType.stepMpParamsRun(0x0103, 5000, 5000, 1);
            byte[] bytes61 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytes63 = MotorPType.stepMpZero(0x0101, 5000);
            byte[] bytes62 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytes223 = MotorPType.stepMpZero(0x0103, 5000);
//        //右臂
//        byte[] bytes51 = MotorPType.stepMpParamsRun(0x0106, 5000, 2000, 0);
//        byte[] bytes52 = MotorPType.stepMpParamsRun(0x0107, -5000, 5000, 0);
//        byte[] bytes53 = MotorPType.stepMpParamsRun(0x0108, -5000, 4000, 0);
            //右臂
            byte[] bytes51 = MotorPType.stepMpToEndLoc(0x0106, -2000, 400, 0);
            byte[] bytes52 = MotorPType.stepMpToEndLoc(0x0107, -9000, 1000, 0);
            byte[] bytes53 = MotorPType.stepMpToEndLoc(0x0108, -1000, 800, 0);
            sendAllBytesToSerial(bytes61, bytes62, bytes63, bytes223, bytes51, bytes52, bytes53);
            searchStatusInSleepTime(2400);
//        try {
//            Thread.sleep(2400);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //头部
//        byte[] bytesA2 = MotorPType.stepMpParamsRun(0x010A, -2000, 2000, 1);
//        byte[] bytes71 = MotorPType.stepMpParamsRun(0x0105, -3000, 6000, 1);
//        byte[] bytes72 = MotorPType.stepMpParamsRun(0x0106, 2000, 2000, 1);
//        byte[] bytes73 = MotorPType.stepMpParamsRun(0x0107, 5000, 9000, 1);
//        byte[] bytes74 = MotorPType.stepMpParamsRun(0x0108, -5000, 1000, 1);//还原了
//        byte[] bytes71 = MotorPType.stepMpZero(0x0105, 3000);
            byte[] bytes72 = MotorPType.stepMpZero(0x0106, 2000);
//        byte[] bytes73 = MotorPType.stepMpZero(0x0107, 7000);
            byte[] bytes74 = MotorPType.stepMpZero(0x0108, 5000);

            //添加的
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytes71 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytes73 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(bytes71, bytes72, bytes73, bytes74, bytesHB1, bytesL01, bytesL11, bytesL21, bytesL31);
            searchStatusInSleepTime(2500);
//        try {
//            Thread.sleep(2500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * “托平板”：双手做“托平板”的动作；
     */
    public synchronized static void tuoPB() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
            //头部
            byte[] bytesA = new byte[0];
            if (SerialSend.mARDgree < SerialSend.mAMaxDegree) {
                bytesA = MotorPType.stepMpParamsRunNotEndDegree(0x010A, 1500, mAMaxDegree - mARDgree, 1);
            }
//        //左臂
//        byte[] bytesl = MotorPType.stepMpParamsRun(0x0100, -2000, 3000, 0);
//        byte[] bytes22 = MotorPType.stepMpParamsRun(0x0101, -2000, 3000, 0);
//        byte[] bytes2l = MotorPType.stepMpParamsRun(0x0102, 5000, 8000, 0);
//        byte[] bytes23 = MotorPType.stepMpParamsRun(0x0103, -2000, 2000, 0);
//        //右臂
//        byte[] bytes = MotorPType.stepMpParamsRun(0x0105, 2000, 3000, 0);
//        byte[] bytes3 = MotorPType.stepMpParamsRun(0x0106, -2000, 3000, 0);
//        byte[] bytes2 = MotorPType.stepMpParamsRun(0x0107, -5000, 8000, 0);
//        byte[] bytes4 = MotorPType.stepMpParamsRun(0x0108, 2000, 2000, 0);
            //左臂
            byte[] bytesl = MotorPType.stepMpToEndLoc(0x0100, -3000, 1500, 0);
            byte[] bytes22 = MotorPType.stepMpToEndLoc(0x0101, -3000, 1500, 0);
            byte[] bytes2l = MotorPType.stepMpToEndLoc(0x0102, 8000, 1600, 0);
            byte[] bytes23 = MotorPType.stepMpToEndLoc(0x0103, -2000, 1000, 0);
            //右臂
            byte[] bytes = MotorPType.stepMpToEndLoc(0x0105, 3000, 1500, 0);
            byte[] bytes3 = MotorPType.stepMpToEndLoc(0x0106, -3000, 1500, 0);
            byte[] bytes2 = MotorPType.stepMpToEndLoc(0x0107, -8000, 1600, 0);
            byte[] bytes4 = MotorPType.stepMpToEndLoc(0x0108, 2000, 1000, 0);
            sendAllBytesToSerial(bytesA, bytesl, bytes2l, bytes22, bytes23, bytes, bytes2, bytes3, bytes4);
            searchStatusInSleepTime(3900);
//        try {
//            Thread.sleep(3900);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            byte[] bytes62 = MotorPType.stepMpZero(0x0102, 6000);
            byte[] bytes65 = MotorPType.stepMpZero(0x0107, 6000);
            sendAllBytesToSerial(bytes62, bytes65);
            searchStatusInSleepTime(1500);
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        byte[] bytes61 = MotorPType.stepMpZero(0x0100, 2000);
            byte[] bytes63 = MotorPType.stepMpZero(0x0101, 2000);
            byte[] bytes223 = MotorPType.stepMpZero(0x0103, 5000);
            //右臂
//        byte[] bytes64 = MotorPType.stepMpZero(0x0105, 2000);
            byte[] bytes66 = MotorPType.stepMpZero(0x0106, 2000);
            byte[] bytes67 = MotorPType.stepMpZero(0x0108, 5000);
            //添加的
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);

            byte[] bytes61 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytes64 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(bytes61, bytes63, bytes223, bytes64, bytes66, bytes67, bytesL21, bytesR71);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 请
     */
    public synchronized static void please() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
            //头部
            byte[] bytesA = new byte[0];
            if (SerialSend.mARDgree < SerialSend.mAMaxDegree) {
                bytesA = MotorPType.stepMpParamsRunNotEndDegree(0x010A, 1500, mAMaxDegree - mARDgree, 1);
            }
//        byte[] bytes1 = MotorPType.stepMpParamsRun(0x0105, 5000, 7000, 0);
//        byte[] bytes2 = MotorPType.stepMpParamsRun(0x0106, -5000, 2000, 0);
//        byte[] bytes3 = MotorPType.stepMpParamsRun(0x0107, 5000, 1, 0);
//        byte[] bytes4 = MotorPType.stepMpParamsRun(0x0108, 5000, 5000, 0);
            byte[] bytes1 = MotorPType.stepMpToEndLoc(0x0105, 7000, 1400, 0);
            byte[] bytes2 = MotorPType.stepMpToEndLoc(0x0106, -2000, 400, 0);
            byte[] bytes3 = MotorPType.stepMpToEndLoc(0x0107, 1, 1000, 0);
            byte[] bytes4 = MotorPType.stepMpToEndLoc(0x0108, 5000, 1000, 0);
            sendAllBytesToSerial(bytesA, bytes1, bytes2, bytes3, bytes4);
            searchStatusInSleepTime(4000);
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //头部
            byte[] bytesA2 = MotorPType.stepMpZero(0x010A, 2000);
//        byte[] bytes11 = MotorPType.stepMpZero(0x0105, 5000);
            byte[] bytes21 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytes31 = MotorPType.stepMpZero(0x0107, 5000);
            byte[] bytes41 = MotorPType.stepMpZero(0x0108, 5000);

            //添加的
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1500, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 1500, 0);
            byte[] bytes11 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1500, 0);
            byte[] bytes31 = MotorPType.stepMpToEndLoc(0x0107, -3000, 1500, 0);


            sendAllBytesToSerial(bytes11, bytes21, bytes31, bytes41, bytesL01, bytesL11, bytesL21, bytesL31, bytesHB1);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 双手握拳举起：双手握拳，双臂抬至胸前并点头
     */
    public synchronized static void twoJuQi() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
            //头部
            byte[] bytesA = new byte[0];
            if (SerialSend.mARDgree < SerialSend.mAMaxDegree) {
                bytesA = MotorPType.stepMpParamsRunNotEndDegree(0x010A, 1500, mAMaxDegree - mARDgree, 1);
            }
//        //左臂
//        byte[] bytes0 = MotorPType.stepMpParamsRun(0x0100, -2000, 2000, 0);
//        byte[] bytes1 = MotorPType.stepMpParamsRun(0x0101, -2000, 2000, 0);
//        byte[] bytes2 = MotorPType.stepMpParamsRun(0x0102, 5000, 8000, 0);
//        byte[] bytes3 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
//        byte[] bytes4 = MotorPType.stepMpParamsRun(0x0104, 3000, 6000, 0);
//        //右臂
//        byte[] bytes5 = MotorPType.stepMpParamsRun(0x0105, 2000, 2000, 0);
//        byte[] bytes6 = MotorPType.stepMpParamsRun(0x0106, -2000, 2000, 0);
//        byte[] bytes7 = MotorPType.stepMpParamsRun(0x0107, -5000, 8000, 0);
//        byte[] bytes8 = MotorPType.stepMpParamsRun(0x0108, 5000, 5000, 0);
//        byte[] bytes9 = MotorPType.stepMpParamsRun(0x0109, 3000, 6000, 0);
            //左臂
            byte[] bytes0 = MotorPType.stepMpToEndLoc(0x0100, -2000, 1000, 0);
            byte[] bytes1 = MotorPType.stepMpToEndLoc(0x0101, -2000, 1000, 0);
            byte[] bytes2 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1600, 0);
            byte[] bytes3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
//        byte[] bytes4 = MotorPType.stepMpToEndLoc(0x0104, 6000, 2000, 0);
            //右臂
            byte[] bytes5 = MotorPType.stepMpToEndLoc(0x0105, 2000, 1000, 0);
            byte[] bytes6 = MotorPType.stepMpToEndLoc(0x0106, -2000, 1000, 0);
            byte[] bytes7 = MotorPType.stepMpToEndLoc(0x0107, -8000, 1600, 0);
            byte[] bytes8 = MotorPType.stepMpToEndLoc(0x0108, 5000, 1000, 0);
//        byte[] bytes9 = MotorPType.stepMpToEndLoc(0x0109, 6000, 2000, 0);
            sendAllBytesToSerial(bytesA, bytes0, bytes1, bytes2, bytes3/*,bytes4*/, bytes5, bytes6, bytes7, bytes8/*,bytes9*/);
            searchStatusInSleepTime(1000);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        twoJuQiDianTou();
//        byte[] bytes21 = MotorPType.stepMpParamsRun(0x0102, -5000, 8000, 1);
//        byte[] bytes71 = MotorPType.stepMpParamsRun(0x0107, 5000, 8000, 1);

            byte[] bytes21 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytes71 = MotorPType.stepMpZero(0x0107, 5000);
            sendAllBytesToSerial(bytes21, bytes71);
            searchStatusInSleepTime(2000);

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


//        //左臂
//        byte[] bytes01 = MotorPType.stepMpZero(0x0100, 1000);
            byte[] bytes11 = MotorPType.stepMpZero(0x0101, 1000);
            byte[] bytes31 = MotorPType.stepMpZero(0x0103, 5000);
            byte[] bytes41 = MotorPType.stepMpZero(0x0104, 4000);
            //右臂
//        byte[] bytes51 = MotorPType.stepMpZero(0x0105, 1000);
            byte[] bytes61 = MotorPType.stepMpZero(0x0106, 1000);
            byte[] bytes81 = MotorPType.stepMpZero(0x0108, 5000);
            byte[] bytes91 = MotorPType.stepMpZero(0x0109, 4000);

            //添加的
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);

            byte[] bytes01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytes51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(bytes01, bytes11, bytes31, bytes41, bytes51, bytes61, bytes81, bytes91, bytesL21, bytesR71);
            searchStatusInSleepTime(2200);
//        try {
//            Thread.sleep(2200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    public synchronized static void twoJuQiDianTou() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        byte[] bytes = MotorPType.stepMpParamsRun(0x010A, 2000, 1000, 1);
            byte[] bytes = MotorPType.stepMpToEndLoc(0x010A, 1000, 500, 1);
            sendAllBytesToSerial(bytes);
            searchStatusInSleepTime(600);
//        try {
//            Thread.sleep(600);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytes2 = MotorPType.stepMpParamsRun(0x010A, -2000, 2000, 1);
            byte[] bytes2 = MotorPType.stepMpToEndLoc(0x010A, -1000, 1000, 1);
            sendAllBytesToSerial(bytes2);
            searchStatusInSleepTime(1000);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytes3 = MotorPType.stepMpParamsRun(0x010A, 2000, 1000, 1);
            byte[] bytes3 = MotorPType.stepMpZero(0x010A, 2000);
            sendAllBytesToSerial(bytes3);
            searchStatusInSleepTime(1000);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 右手握拳，做“Yeah”的动作，并“抖”两下
     */
    public synchronized static void oYe() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
            //头部
            byte[] bytesA = new byte[0];
            if (SerialSend.mARDgree < SerialSend.mAMaxDegree) {
                bytesA = MotorPType.stepMpParamsRunNotEndDegree(0x010A, 1500, mAMaxDegree - mARDgree, 1);
            }
//        //右臂
//        byte[] bytes5 = MotorPType.stepMpParamsRun(0x0105, 5000, 4000, 0);
//        byte[] bytes6 = MotorPType.stepMpParamsRun(0x0106, -2000, 3000, 0);
//        byte[] bytes7 = MotorPType.stepMpParamsRun(0x0107, -5000, 10000, 0);
//        byte[] bytes8 = MotorPType.stepMpParamsRun(0x0108, 5000, 6000, 0);
//        byte[] bytes9 = MotorPType.stepMpParamsRun(0x0109, 5000, 6000, 0);
            //右臂
            byte[] bytes5 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
            byte[] bytes6 = MotorPType.stepMpToEndLoc(0x0106, -3000, 1500, 0);
            byte[] bytes7 = MotorPType.stepMpToEndLoc(0x0107, -10000, 2000, 0);
            byte[] bytes8 = MotorPType.stepMpToEndLoc(0x0108, 6000, 1200, 0);
//        byte[] bytes9 = MotorPType.stepMpToEndLoc(0x0109, 6000, 1200, 0);
            sendAllBytesToSerial(bytesA, bytes5, bytes6, bytes7, bytes8/*,bytes9*/);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            dou();
            byte[] bytes71 = MotorPType.stepMpZero(0x0107, 5000);
            sendAllBytesToSerial(bytes71);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //头部
            //右臂
//        byte[] bytes51 = MotorPType.stepMpZero(0x0105, 5000);
            byte[] bytes61 = MotorPType.stepMpZero(0x0106, 2000);
            byte[] bytes81 = MotorPType.stepMpZero(0x0108, 5000);
            byte[] bytes91 = MotorPType.stepMpZero(0x0109, 5000);

            //添加的
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1500, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 1500, 0);
            byte[] bytes51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1500, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 1500, 0);


            sendAllBytesToSerial(bytes51, bytes61, bytes81, bytes91, bytesHB1, bytesL01, bytesL11, bytesL21, bytesL31, bytesR71);
            searchStatusInSleepTime(1500);
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    public synchronized static void dou() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
            //右臂
//        byte[] bytes51 = MotorPType.stepMpParamsRun(0x0105, -5000, 4000, 0);
            byte[] bytes51 = MotorPType.stepMpToEndLoc(0x0105, 0, 800, 0);
            sendAllBytesToSerial(bytes51);
            searchStatusInSleepTime(800);
//        try {
//            Thread.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //右臂
//        byte[] bytes52 = MotorPType.stepMpParamsRun(0x0105, 5000, 4000, 0);
            byte[] bytes52 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
            sendAllBytesToSerial(bytes52);
            searchStatusInSleepTime(1800);
//        try {
//            Thread.sleep(1800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }


    /*说话时带的动作*/
    public synchronized static void talkAction() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 3000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 1000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 2000, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 3000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 1000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 4000, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 6000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -3000, 600, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -1000, 200, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 2000, 400, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 3000, 600, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -1000, 200, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -4000, 800, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 6000, 1200, 0);
//        //眼睛灯眨一下
//        byte[] eyeControll = LightType.eyeControll(2,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255);
            //头部
            byte[] bytesA = new byte[0];
//        if (SerialSend.mARDgree < SerialSend.mAMaxDegree) {
//            bytesA = MotorPType.stepMpParamsRunNotEndDegree(0x010A, 1500, mAMaxDegree - mARDgree, 1);
//        }
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesR5, bytesR6, bytesR7, bytesR8, bytesA/*,eyeControll*/);
            searchStatusInSleepTime(800);
//        try {
//            Thread.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //头部
//        byte[] bytesHR12 = MotorPType.stepMpParamsRun(0x010A, -5000, 1200, 1);
//        sendAllBytesToSerial(bytesHR12);
            searchStatusInSleepTime(1000);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            allStepToZeroExceptHead(2000, 2000, 7000, 7000, 2000, 2000, 7000, 7000);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    public synchronized static void talkAction2() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 3000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 1000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 2000, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -3000, 600, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -1000, 200, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 2000, 400, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            //头部
            byte[] bytesA = new byte[0];
//        if (SerialSend.mARDgree < SerialSend.mAMaxDegree) {
//            bytesA = MotorPType.stepMpParamsRunNotEndDegree(0x010A, 1500, mAMaxDegree - mARDgree, 1);
//        }
//        //眼睛灯眨一下
//        byte[] eyeControll = LightType.eyeControll(2,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesA/*,eyeControll*/);
            searchStatusInSleepTime(800);
//        try {
//            Thread.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //头部
//        byte[] bytesHR12 = MotorPType.stepMpParamsRun(0x010A, -5000, 1200, 1);
//        sendAllBytesToSerial(bytesHR12);
            searchStatusInSleepTime(1000);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            allStepToZeroExceptHead(2000, 2000, 7000, 7000, 2000, 2000, 7000, 7000);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    public synchronized static void talkAction3() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 3000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 1000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 4000, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 6000, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 3000, 600, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -1000, 200, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -4000, 800, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 6000, 1200, 0);

            //头部
            byte[] bytesA = new byte[0];
//        if (SerialSend.mARDgree < SerialSend.mAMaxDegree) {
//            bytesA = MotorPType.stepMpParamsRun(0x010A, 1500, mAMaxDegree - mARDgree, 1);
//        }
//        //眼睛灯眨一下
//        byte[] eyeControll = LightType.eyeControll(2,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255);
            sendAllBytesToSerial(bytesR5, bytesR6, bytesR7, bytesR8, bytesA/*,eyeControll*/);
            searchStatusInSleepTime(800);
//        try {
//            Thread.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //头部
//        byte[] bytesHR12 = MotorPType.stepMpParamsRun(0x010A, -5000, 1200, 1);
//        sendAllBytesToSerial(bytesHR12);
            searchStatusInSleepTime(1000);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            allStepToZeroExceptHead(2000, 2000, 7000, 7000, 2000, 2000, 7000, 7000);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    public synchronized static void talkAction4() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 3000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, 1000, 800, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 3000, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 2000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 3000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, 1000, 800, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000,3000, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 2000, 0);

            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -5000, 600, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, 800, 800, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 5000, 600, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -2000, 400, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 5000, 600, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, 800, 800, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -5000, 600, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 2000, 400, 0);

        /*//眼睛灯眨一下
        byte[] eyeControll = LightType.eyeControll(2,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);*/
            //头部
            byte[] bytesA = new byte[0];
//        if (SerialSend.mARDgree < SerialSend.mAMaxDegree) {
//            bytesA = MotorPType.stepMpParamsRun(0x010A, 1500, mAMaxDegree - mARDgree, 1);
//        }
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesR5, bytesR6, bytesR7, bytesR8, bytesA/*,eyeControll*/);
            searchStatusInSleepTime(800);
//        try {
//            Thread.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //头部
//        byte[] bytesHR12 = MotorPType.stepMpParamsRun(0x010A, -5000, 1200, 1);
//        sendAllBytesToSerial(bytesHR12);
            searchStatusInSleepTime(1000);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            allStepToZeroExceptHead(2000, 2000, 7000, 7000, 2000, 2000, 7000, 7000);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    public synchronized static void talkAction5() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 3000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, 1000, 800, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 3000, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 2000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -5000, 600, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, 800, 800, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 5000, 600, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -2000, 400, 0);
       /* //眼睛灯眨一下
        byte[] eyeControll = LightType.eyeControll(2,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);*/
            //头部
            byte[] bytesA = new byte[0];
//        if (SerialSend.mARDgree < SerialSend.mAMaxDegree) {
//            bytesA = MotorPType.stepMpParamsRun(0x010A, 1500, mAMaxDegree - mARDgree, 1);
//        }
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesA/*,eyeControll*/);
            searchStatusInSleepTime(800);
//        try {
//            Thread.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //头部
//        byte[] bytesHR12 = MotorPType.stepMpParamsRun(0x010A, -5000, 1200, 1);
//        sendAllBytesToSerial(bytesHR12);
            searchStatusInSleepTime(1000);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            allStepToZeroExceptHead(2000, 2000, 7000, 7000, 2000, 2000, 7000, 7000);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    public synchronized static void talkAction6() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 3000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, 1000, 800, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000,3000, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 2000, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 5000, 600, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, 800, 800, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -5000, 600, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 2000, 400, 0);
     /*   //眼睛灯眨一下
        byte[] eyeControll = LightType.eyeControll(2,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);*/
            //头部
            byte[] bytesA = new byte[0];
//        if (SerialSend.mARDgree < SerialSend.mAMaxDegree) {
//            bytesA = MotorPType.stepMpParamsRun(0x010A, 1500, mAMaxDegree - mARDgree, 1);
//        }
            sendAllBytesToSerial(bytesR5, bytesR6, bytesR7, bytesR8, bytesA/*,eyeControll*/);
            searchStatusInSleepTime(800);
//        try {
//            Thread.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //头部
//        byte[] bytesHR12 = MotorPType.stepMpParamsRun(0x010A, -5000, 1200, 1);
//        sendAllBytesToSerial(bytesHR12);
            searchStatusInSleepTime(1000);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            allStepToZeroExceptHead(2000, 2000, 7000, 7000, 2000, 2000, 7000, 7000);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    public synchronized static void talkAction7() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 3000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 1000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 8000, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 3000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 1000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000,8000, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 6000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -3000, 600, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -1000, 200, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1600, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 3000, 600, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -1000, 200, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -8000, 1600, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 6000, 1200, 0);
      /*  //眼睛灯眨一下
        byte[] eyeControll = LightType.eyeControll(2,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);*/
            //头部
            byte[] bytesA = new byte[0];
//        if (SerialSend.mARDgree < SerialSend.mAMaxDegree) {
//            bytesA = MotorPType.stepMpParamsRun(0x010A, 1500, mAMaxDegree - mARDgree, 1);
//        }
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesR5, bytesR6, bytesR7, bytesR8/*,eyeControll*/, bytesA);
            searchStatusInSleepTime(1000);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //头部
//        byte[] bytesHR12 = MotorPType.stepMpParamsRun(0x010A, -5000, 1200, 1);
//       sendAllBytesToSerial(bytesHR12);
            searchStatusInSleepTime(1000);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            allStepToZeroExceptHead(2000, 2000, 8000, 6000, 2000, 2000, 8000, 8000);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    public synchronized static void talkAction8() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 3000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, -5000, 3000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -3000, 1500, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 500, 0);
            byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 7000);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, -3000, 1500, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -3000, 500, 0);
            byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 7000);
//        //眼睛灯眨一下
//        byte[] eyeControll = LightType.eyeControll(2,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255,
//                0, 0, 255);
            //头部
            byte[] bytesA = new byte[0];
//        if (SerialSend.mARDgree < SerialSend.mAMaxDegree) {
//            bytesA = MotorPType.stepMpParamsRun(0x010A, 1500, mAMaxDegree - mARDgree, 1);
//        }
            sendAllBytesToSerial(bytesL0, bytesL1, bytesR5/*,eyeControll*/, bytesR6, bytesA, bytesL21, bytesR71);
            searchStatusInSleepTime(800);
//        try {
//            Thread.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //头部
//        byte[] bytesHR12 = MotorPType.stepMpParamsRun(0x010A, -5000, 1200, 1);
//        sendAllBytesToSerial(bytesHR12);
            searchStatusInSleepTime(1000);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            allStepToZeroExceptHead(2000, 2000, 7000, 7000, 2000, 2000, 7000, 7000);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    public synchronized static void talkAction9() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, 5000, 3000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 3000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, 3000, 1500, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 500, 0);
            byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 7000);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 3000, 1500, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -3000, 500, 0);
            byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 7000);
       /* //眼睛灯眨一下
        byte[] eyeControll = LightType.eyeControll(2,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);*/
            //头部
            byte[] bytesA = new byte[0];
//        if (SerialSend.mARDgree < SerialSend.mAMaxDegree) {
//            bytesA = MotorPType.stepMpParamsRun(0x010A, 1500, mAMaxDegree - mARDgree, 1);
//        }
            sendAllBytesToSerial(bytesL0, bytesL1, bytesR5/*,eyeControll*/, bytesR6, bytesA, bytesL21, bytesR71);
            searchStatusInSleepTime(800);
//        try {
//            Thread.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //头部
//        byte[] bytesHR12 = MotorPType.stepMpParamsRun(0x010A, -5000, 1200, 1);
//        sendAllBytesToSerial(bytesHR12);
            searchStatusInSleepTime(1000);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            allStepToZeroExceptHead(2000, 2000, 7000, 7000, 2000, 2000, 7000, 7000);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        }
    }

    /*我*/
    public synchronized static void me() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 6000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 3000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 9000, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 1000, 0);
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 6000, 1200, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -3000, 800, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -9000, 1800, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 1000, 200, 0);
            sendAllBytesToSerial(bytesR5, bytesR6, bytesR7, bytesR8);
            searchStatusInSleepTime(1900);
//        try {
//            Thread.sleep(1900);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 3000);
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 2000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 6000);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);//还原了

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(bytesL01, bytesL21, bytesR51, bytesR61, bytesR71, bytesR81);
            searchStatusInSleepTime(2100);
//        try {
//            Thread.sleep(2100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*你*/
    public synchronized static void you() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 11000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 3000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 2000, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 1000, 0);
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 11000, 2200, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -3000, 600, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -2000, 400, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 1000, 200, 0);
            sendAllBytesToSerial(bytesR5, bytesR6, bytesR7, bytesR8);
            searchStatusInSleepTime(2300);
//        try {
//            Thread.sleep(2300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);//还原了

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(bytesL01, bytesL21, bytesR51, bytesR61, bytesR71, bytesR81);
            searchStatusInSleepTime(2100);
//        try {
//            Thread.sleep(2100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*你好*/
    public synchronized static void hibotHello() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 9000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 3000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 6000, 0);
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -9000, 1800, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 6000, 1200, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2);
            searchStatusInSleepTime(4000);
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1500, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 1500, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1500, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 1500, 0);

            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21);
            searchStatusInSleepTime(1900);
//        try {
//            Thread.sleep(1900);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*欢迎*/
    public synchronized static void hibotWelCome() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
            //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 6000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 3000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 4000, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -6000, 1200, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 4000, 800, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3);
            searchStatusInSleepTime(1300);
//        try {
//            Thread.sleep(1300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 1000, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 1000, 0);

            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR71);
            searchStatusInSleepTime(1300);
//        try {
//            Thread.sleep(1300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*拜拜*/
    public synchronized static void hibotGoodBye() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, 5000, 2000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 13000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 4000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, 5000, 1, 0);
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, -5000, 7000, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 18000, 2600, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -2000, 800, 0);
            byte[] bytesR7 = MotorPType.stepMpZero(0x0107, 5000);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, -7000, 1400, 0);
            sendAllBytesToSerial(bytesR5, bytesR6, bytesR7, bytesR8);
            searchStatusInSleepTime(2700);
//        try {
//            Thread.sleep(2700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytesR61 = MotorPType.stepMpParamsRun(0x0106, 5000, 4000, 0);
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, 2000, 800, 0);
            sendAllBytesToSerial(bytesR61);
            searchStatusInSleepTime(1500);
//        try {
//            Thread.sleep(900);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytesR62 = MotorPType.stepMpParamsRun(0x0106, -5000, 4000, 0);
            byte[] bytesR62 = MotorPType.stepMpToEndLoc(0x0106, -2000, 800, 0);
            sendAllBytesToSerial(bytesR62);
            searchStatusInSleepTime(1500);
//        try {
//            Thread.sleep(900);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytesR63 = MotorPType.stepMpParamsRun(0x0106, 5000, 4000, 0);
            byte[] bytesR63 = MotorPType.stepMpToEndLoc(0x0106, 2000, 800, 0);
            sendAllBytesToSerial(bytesR63);
            searchStatusInSleepTime(1500);
//        try {
//            Thread.sleep(900);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytesR64 = MotorPType.stepMpParamsRun(0x0106, -5000, 4000, 0);
            byte[] bytesR64 = MotorPType.stepMpToEndLoc(0x0106, -2000, 800, 0);
            sendAllBytesToSerial(bytesR64);
            searchStatusInSleepTime(1500);
//        try {
//            Thread.sleep(900);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //左臂
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            //右臂
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
            byte[] bytesR65 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(bytesL01, bytesL21, bytesR51, bytesR65, bytesR71, bytesR81);
            searchStatusInSleepTime(2700);
//        try {
//            Thread.sleep(2700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*今天*/
    public synchronized static void hibotToday() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 6000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 3000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 6000, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 4000, 0);
//        //右臂
//        byte[] bytesR8 = MotorPType.stepMpParamsRun(0x0108, 5000, 5000, 0);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -6000, 1200, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 6000, 1200, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -4000, 800, 0);
            //右臂
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 5000, 1000, 0);
            sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesR8);
            searchStatusInSleepTime(1300);
//        try {
//            Thread.sleep(1300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //左臂
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
            //右臂
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 1000, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 1000, 0);

            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR81, bytesR51, bytesR71);
            searchStatusInSleepTime(1300);
//        try {
//            Thread.sleep(1300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*伤心*/
    public synchronized static void hibotSad() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        //头部
//        byte[] bytesHL = MotorPType.stepMpParamsRun(0x010B, 2000, 2000, 1);
//        byte[] bytesHR = MotorPType.stepMpParamsRun(0x010A, -2000, 1500, 1);
//        //左臂
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 11000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 3000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 11000, 0);
//        byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 2500, 0);
            //头部
            byte[] bytesHL = MotorPType.stepMpToEndLoc(0x010B, 2000, 1000, 1);
            byte[] bytesHR = MotorPType.stepMpToEndLoc(0x010A, -1500, 750, 1);
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -11000, 2200, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 11000, 2200, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -2500, 500, 0);
            sendAllBytesToSerial(bytesHL, bytesHR, bytesL0, bytesL1, bytesL2, bytesL3);
            searchStatusInSleepTime(2300);
//        try {
//            Thread.sleep(2300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //头部
            byte[] bytesHL1 = MotorPType.stepMpZero(0x010B, 2000);
            byte[] bytesHR1 = MotorPType.stepMpZero(0x010A, 2000);
            //左臂
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 2000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(bytesHL1, bytesHR1, bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR71);
            searchStatusInSleepTime(2300);
//        try {
//            Thread.sleep(2300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*高兴*/
    public synchronized static void happyNow() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        byte[] bytes3 = MotorPType.stepMpParamsRun(0x010B, -2000, 1500, 1);
//        byte[] bytes4 = MotorPType.stepMpParamsRun(0x010A, 2000, 1500, 1);
            byte[] bytes3 = MotorPType.stepMpToEndLoc(0x010B, -1500, 750, 1);
            byte[] bytes4 = MotorPType.stepMpToEndLoc(0x010A, 1500, 750, 1);
            sendAllBytesToSerial(bytes3, bytes4);
            searchStatusInSleepTime(2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            allStepToZero();
            searchStatusInSleepTime(850);
//        try {
//            Thread.sleep(850);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    public synchronized static void yaoTouNow() {
        synchronized (SerialSend.lock) {

            //2000-速度 1200-路程
//        byte[] bytes = MotorPType.stepMpParamsRun(0x010B, 3000, 1200, 1);
            byte[] bytes = MotorPType.stepMpToEndLoc(0x010B, 1200, 400, 1);
            sendAllBytesToSerial(bytes);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//        byte[] bytes31 = MotorPType.stepMpParamsRun(0x010B, -3000, 1200, 1);
            byte[] bytes31 = MotorPType.stepMpToEndLoc(0x010B, 0, 400, 1);
            sendAllBytesToSerial(bytes31);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//        byte[] bytes32 = MotorPType.stepMpParamsRun(0x010B, -3000, 1200, 1);
            byte[] bytes32 = MotorPType.stepMpToEndLoc(0x010B, -1200, 400, 1);
            sendAllBytesToSerial(bytes32);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//        byte[] bytes3 = MotorPType.stepMpParamsRun(0x010B, 3000, 1200, 1);
            byte[] bytes3 = MotorPType.stepMpZero(0x010B, 3000);
            sendAllBytesToSerial(bytes3);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /*点头*/
    public synchronized static void dianTouNow() {
        synchronized (SerialSend.lock) {

            SerialSend.isActionFinish = false;
//        byte[] bytes = MotorPType.stepMpParamsRun(0x010A, 3000, 1000, 1);
            byte[] bytes = MotorPType.stepMpToEndLoc(0x010A, 1000, 350, 1);
            sendAllBytesToSerial(bytes);
            searchStatusInSleepTime(350);
//        try {
//            Thread.sleep(350);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        byte[] bytes2 = MotorPType.stepMpParamsRun(0x010A, -3000, 2000, 1);
            byte[] bytes2 = MotorPType.stepMpToEndLoc(0x010A, -1000, 700, 1);
            sendAllBytesToSerial(bytes2);
            searchStatusInSleepTime(700);
//        try {
//            Thread.sleep(700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            byte[] bytes3 = MotorPType.stepMpZero(0x010A, 3000);
            sendAllBytesToSerial(bytes3);
            searchStatusInSleepTime(350);
//        try {
//            Thread.sleep(350);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            SerialSend.isActionFinish = true;
        }
    }

    /*伤心*/
    public synchronized static void sadNow() {
        synchronized (SerialSend.lock) {
            SerialSend.isActionFinish = false;
//            byte[] bytes = MotorPType.stepMpParamsRun(0x010B, 2000, 1500, 1);
//            byte[] bytes2 = MotorPType.stepMpParamsRun(0x010A, -2000, 1500, 1);
            byte[] bytes = MotorPType.stepMpToEndLoc(0x010B, 1500, 750, 1);
            byte[] bytes2 = MotorPType.stepMpToEndLoc(0x010A, -1500, 750, 1);
            sendAllBytesToSerial(bytes, bytes2);
            searchStatusInSleepTime(2000);
            //添加的
            allStepToZero();
            searchStatusInSleepTime(850);
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

    public static void allStepToZero(int speedA, int speedB, int spend0, int spend1, int spend2, int spend3, int spend5, int spend6, int spend7, int spend8) {
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

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

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

    public synchronized static void allStepToZeroExceptHead(int spend0, int spend1, int spend2, int spend3, int spend5, int spend6, int spend7, int spend8) {
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, spend0);
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, spend1);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, spend2);
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, spend3);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, spend5);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, spend6);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, spend7);
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, spend8);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1000, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1000, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81);
    }


    /**
     * 关闭右高左低
     */
    public synchronized static void closeRightUpLeftDown(Context context) {
        byte[] eyeControl = LightType.eyeControll(0,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);
        byte[] topHeadControl = LightType.topHeadControll(0, 0, 0, 255);
        byte[] earControll = LightType.earControll(0, 0, 0, 255);
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
//        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 4000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
//        byte[] bytesHR11 = MotorPType.stepMpZero(0x010B, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
//        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
//        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
//        //添加的
//        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
        //底盘
        byte[] bytes51 = ClassicType.runWithSpeed(15, -15, 4);
        sendAllBytesToSerial(eyeControl, topHeadControl, earControll,/* bytesL01, bytesL11, bytesL21, bytesHR11, bytesR51, bytesR61, bytesR71, bytesR81,*/ bytes51/*, bytesL31, bytesHA1*/);
        searchStatusInSleepTime(800);
    }

    /**
     * 关闭左高右低
     */
    public synchronized static void closeLeftUpRightDown(Context context) {
        byte[] eyeControl = LightType.eyeControll(0,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);
        byte[] topHeadControl = LightType.topHeadControll(0, 0, 0, 255);
        byte[] earControll = LightType.earControll(0, 0, 0, 255);
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
//        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
//        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytesHR11 = MotorPType.stepMpZero(0x010B, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
//        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 4000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
//        //添加的
//        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
//        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
        //底盘
        byte[] bytes52 = ClassicType.runWithSpeed(-15, 15, 4);
        sendAllBytesToSerial(eyeControl, topHeadControl, earControll,/* bytesL01, bytesL11, bytesL21, bytesL31, bytesHR11, bytesR51, bytesR61, bytesR71,*/ bytes52/*, bytesR81, bytesHA1*/);
        searchStatusInSleepTime(800);
    }

    /**
     * 关闭左右一起高
     */
    public synchronized static void closeLeftRightUp(Context context) {
        byte[] eyeControl = LightType.eyeControll(0,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);
        byte[] topHeadControl = LightType.topHeadControll(0, 0, 0, 255);
        byte[] earControll = LightType.earControll(0, 0, 0, 255);
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
//        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
//        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
//        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
//        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
//
//        //添加的
//        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
//        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

        //底盘
        byte[] bytes51 = ClassicType.runWithSpeed(15, -15, 4);

        sendAllBytesToSerial(eyeControl, topHeadControl, earControll,/* bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81,*/ bytes51/*, bytesHA1, bytesHB1*/);
        searchStatusInSleepTime(800);
    }

    /**
     * 关闭叉腰
     */
    public synchronized static void closeChayao(Context context) {
        byte[] eyeControl = LightType.eyeControll(0,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);
        byte[] topHeadControl = LightType.topHeadControll(0, 0, 0, 255);
        byte[] earControll = LightType.earControll(0, 0, 0, 255);
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
//        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 3000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
//        byte[] bytesL41 = MotorPType.stepMpZero(0x0104, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
//        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 3000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
//        byte[] bytesR91 = MotorPType.stepMpZero(0x0109, 5000);
//
//        //添加的
//        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
//        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
//        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

        //底盘
        byte[] bytes52 = ClassicType.runWithSpeed(-15, 15, 4);
        sendAllBytesToSerial(eyeControl, topHeadControl, earControll,/* bytesL01, bytesL11, bytesL21, bytesL41, bytesR51, bytesR61, bytesR71, bytesR91,*/ bytes52/*, bytesL31, bytesR81, bytesHA1, bytesHB1*/);
        searchStatusInSleepTime(800);
    }


    /**
     * 关闭左手叉腰右边看
     */
    public synchronized static void closeLeftChayaoRightSee(Context context) {
        byte[] eyeControl = LightType.eyeControll(0,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);
        byte[] topHeadControl = LightType.topHeadControll(0, 0, 0, 255);
        byte[] earControll = LightType.earControll(0, 0, 0, 255);
//
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
//        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 3000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
//        byte[] bytesL41 = MotorPType.stepMpZero(0x0104, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
//        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
//        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
//        byte[] bytesHR11 = MotorPType.stepMpZero(0x010B, 5000);
//        byte[] bytesHR21 = MotorPType.stepMpZero(0x010A, 5000);
//
//        //添加的
//        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);

        //底盘
        byte[] bytes51 = ClassicType.runWithSpeed(15, -15, 4);

        sendAllBytesToSerial(eyeControl, topHeadControl, earControll, /*bytesL01, bytesL11, bytesL21, bytesL41, bytesR51, bytesR61, bytesR71, bytesR81, bytesHR11, bytesHR21, */bytes51/*, bytesL31*/);
        searchStatusInSleepTime(800);
    }

    /**
     * 关闭右手叉腰左边看
     */
    public synchronized static void closeRightChayaoLeftSee(Context context) {
        byte[] eyeControl = LightType.eyeControll(0,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);
        byte[] topHeadControl = LightType.topHeadControll(0, 0, 0, 255);
        byte[] earControll = LightType.earControll(0, 0, 0, 255);
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
//        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
//        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
//        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 3000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
//        byte[] bytesR91 = MotorPType.stepMpZero(0x0109, 5000);
//        byte[] bytesHR11 = MotorPType.stepMpZero(0x010B, 5000);
//        byte[] bytesHR21 = MotorPType.stepMpZero(0x010A, 5000);

        //添加的
//        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);

        //底盘
        byte[] bytes52 = ClassicType.runWithSpeed(-15, 15, 4);

        sendAllBytesToSerial(eyeControl, topHeadControl, earControll,/*bytesL01, bytesL11, bytesL21, bytesL31, bytesHR11, bytesHR21, bytesR51, bytesR61, bytesR71, bytesR91,*/ bytes52/*, bytesR81*/);
        searchStatusInSleepTime(800);
    }

    /**
     * 关闭右前左后
     */
    public synchronized static void closeRightQianLeftHou(Context context) {
        byte[] eyeControl = LightType.eyeControll(0,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);
        byte[] topHeadControl = LightType.topHeadControll(0, 0, 0, 255);
        byte[] earControll = LightType.earControll(0, 0, 0, 255);
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
//        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 2000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 3000);
//        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 2000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 6000);
//
//        //添加的
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
//        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
//        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
//        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

        //底盘
        byte[] bytes51 = ClassicType.runWithSpeed(15, -15, 4);
        sendAllBytesToSerial(eyeControl, topHeadControl, earControll,/*bytesL01, bytesL11, bytesR51, bytesR61, bytesR71,*/ bytes51/*, bytesL21, bytesL31, bytesR81, bytesHA1, bytesHB1*/);
        searchStatusInSleepTime(800);
    }

    /**
     * 关闭左前右后
     */
    public synchronized static void closeLeftQianRightHou(Context context) {
        byte[] eyeControl = LightType.eyeControll(0,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);
        byte[] topHeadControl = LightType.topHeadControll(0, 0, 0, 255);
        byte[] earControll = LightType.earControll(0, 0, 0, 255);
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 3000);
//        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 2000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
//        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 2000);
//
//        //添加的
//        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
//        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
//        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
//        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

        //底盘
        byte[] bytes52 = ClassicType.runWithSpeed(-15, 15, 4);

        sendAllBytesToSerial(eyeControl, topHeadControl, earControll,/* bytesL01, bytesL11, bytesL21, bytesR51, bytesR61,*/ bytes52/*, bytesL31, bytesR71, bytesR81, bytesHA1, bytesHB1*/);
        searchStatusInSleepTime(800);
    }

    /**
     * 关闭奥特曼左边起
     */
    public synchronized static void closeAtemanLeftUp(Context context) {
        byte[] eyeControl = LightType.eyeControll(0,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);
        byte[] topHeadControl = LightType.topHeadControll(0, 0, 0, 255);
        byte[] earControll = LightType.earControll(0, 0, 0, 255);
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 3000);
//        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 6000);
//        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 6000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 3000);
//        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 3000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
//
//        //添加的
//        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
//        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
//        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

        //底盘
        byte[] bytes51 = ClassicType.runWithSpeed(15, -15, 4);
        sendAllBytesToSerial(eyeControl, topHeadControl, earControll,/* bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71,*/ bytes51/*, bytesR81, bytesHA1, bytesHB1*/);
        searchStatusInSleepTime(800);
    }

    /**
     * 关闭原舞蹈第一步
     */
    public synchronized static void closeBeforeDanceOne(Context context) {
        byte[] eyeControl = LightType.eyeControll(0,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);
        byte[] topHeadControl = LightType.topHeadControll(0, 0, 0, 255);
        byte[] earControll = LightType.earControll(0, 0, 0, 255);
//        byte[] bytes51 = MotorPType.stepMpZero(0x0100, 5000);
        byte[] bytes53 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytes52 = MotorPType.stepMpZero(0x0102, 5000);
        byte[] bytes57 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytes54 = MotorPType.stepMpZero(0x0105, 5000);
        byte[] bytes56 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytes55 = MotorPType.stepMpZero(0x0107, 5000);
        byte[] bytes58 = MotorPType.stepMpZero(0x0108, 5000);

        //添加的
        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

        byte[] bytes51 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
        byte[] bytes52 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
        byte[] bytes54 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
        byte[] bytes55 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

        //底盘
        byte[] bytes521 = ClassicType.runWithSpeed(-15, 15, 4);
        sendAllBytesToSerial(eyeControl, topHeadControl, earControll, bytes51, bytes52, bytes53, bytes57, bytes54, bytes55, bytes56, bytes58, bytes521, bytesHA1, bytesHB1);
        searchStatusInSleepTime(800);
    }

    /**
     * 关闭原舞蹈第二步
     */
    public synchronized static void closeBeforeDanceTwo(Context context) {
        byte[] eyeControl = LightType.eyeControll(0,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);
        byte[] topHeadControl = LightType.topHeadControll(0, 0, 0, 255);
        byte[] earControll = LightType.earControll(0, 0, 0, 255);
//        byte[] bytes51 = MotorPType.stepMpZero(0x0100, 5000);
        byte[] bytes53 = MotorPType.stepMpZero(0x0101, 5000);
        byte[] bytes57 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytes54 = MotorPType.stepMpZero(0x0105, 5000);
        byte[] bytes56 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytes55 = MotorPType.stepMpZero(0x0107, 5000);
        byte[] bytes58 = MotorPType.stepMpZero(0x0108, 5000);
        byte[] bytes41 = MotorPType.stepMpZero(0x010A, 3000);

        //添加的
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

        byte[] bytes51 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
        byte[] bytes54 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
        byte[] bytes55 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);


        //底盘
        byte[] bytes511 = ClassicType.runWithSpeed(15, -15, 4);
        sendAllBytesToSerial(eyeControl, topHeadControl, earControll, bytes51, bytes53, bytes57, bytes41, bytes54, bytes55, bytes56, bytes58, bytes511, bytesL21, bytesHB1);
        searchStatusInSleepTime(2700);
    }


    /**
     * 关闭原舞蹈第三步
     */
    public synchronized static void closeBeforeDanceThree(Context context) {
        byte[] eyeControl = LightType.eyeControll(0,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);
        byte[] topHeadControl = LightType.topHeadControll(0, 0, 0, 255);
        byte[] earControll = LightType.earControll(0, 0, 0, 255);
//
//        byte[] bytes51 = MotorPType.stepMpZero(0x0100, 5000);
//        byte[] bytes53 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytes22 = MotorPType.stepMpZero(0x0102, 5000);
//        byte[] bytes57 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytes54 = MotorPType.stepMpZero(0x0105, 5000);
//        byte[] bytes56 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytes58 = MotorPType.stepMpZero(0x0108, 5000);
//        byte[] bytes41 = MotorPType.stepMpZero(0x010A, 3000);
//
//        //添加的
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
//        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

        //底盘
        byte[] bytes521 = ClassicType.runWithSpeed(-15, 15, 4);
        sendAllBytesToSerial(eyeControl, topHeadControl, earControll,/* bytes51, bytes53, bytes57, bytes41, bytes22, bytes54, bytes56, bytes58, */bytes521/*, bytesR71, bytesHB1*/);
        searchStatusInSleepTime(800);
    }


    /**
     * 关闭右高左低
     */
    public synchronized static void closeRightUpLeftDown() {
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 4000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
        byte[] bytesHR11 = MotorPType.stepMpZero(0x010B, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
        //添加的
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesHR11, bytesR51, bytesR61, bytesR71, bytesR81, bytesL31, bytesHA1);
        searchStatusInSleepTime(2300);
//        try {
//            Thread.sleep(2300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 关闭左高右低
     */
    public synchronized static void closeLeftUpRightDown() {
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
        byte[] bytesHR11 = MotorPType.stepMpZero(0x010B, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 4000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
        //添加的
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesHR11, bytesR51, bytesR61, bytesR71, bytesR81, bytesHA1);
        searchStatusInSleepTime(2300);
//        try {
//            Thread.sleep(2300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 关闭左右一起高
     */
    public synchronized static void closeLeftRightUp() {
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);

        //添加的
        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81, bytesHA1, bytesHB1);
        searchStatusInSleepTime(2300);
//        try {
//            Thread.sleep(2300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 关闭叉腰
     */
    public synchronized static void closeChayao() {
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 3000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
        byte[] bytesL41 = MotorPType.stepMpZero(0x0104, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 3000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
        byte[] bytesR91 = MotorPType.stepMpZero(0x0109, 5000);

        //添加的
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1500, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 1500, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1500, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 1500, 0);

        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL41, bytesR51, bytesR61, bytesR71, bytesR91, bytesL31, bytesR81, bytesHA1, bytesHB1);
        searchStatusInSleepTime(1700);
//        try {
//            Thread.sleep(1700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


    /**
     * 关闭左手叉腰右边看
     */
    public synchronized static void closeLeftChayaoRightSee() {

//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 3000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
        byte[] bytesL41 = MotorPType.stepMpZero(0x0104, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
        byte[] bytesHR11 = MotorPType.stepMpZero(0x010B, 5000);
        byte[] bytesHR21 = MotorPType.stepMpZero(0x010A, 5000);
        //添加的
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL41, bytesR51, bytesR61, bytesR71, bytesR81, bytesHR11, bytesHR21, bytesL31);
        searchStatusInSleepTime(2700);
//        try {
//            Thread.sleep(2700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 关闭右手叉腰左边看
     */
    public synchronized static void closeRightChayaoLeftSee() {

//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 3000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
        byte[] bytesR91 = MotorPType.stepMpZero(0x0109, 5000);
        byte[] bytesHR11 = MotorPType.stepMpZero(0x010B, 5000);
        byte[] bytesHR21 = MotorPType.stepMpZero(0x010A, 5000);

        //添加的
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesHR11, bytesHR21, bytesR51, bytesR61, bytesR71, bytesR91, bytesR81);
        searchStatusInSleepTime(2700);
//        try {
//            Thread.sleep(2700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 关闭右前左后
     */
    public synchronized static void closeRightQianLeftHou() {
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 5000);
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 2000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 3000);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 2000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 6000);

        //添加的
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1500, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 1500, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1500, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 1500, 0);

        sendAllBytesToSerial(bytesL01, bytesL11, bytesR51, bytesR61, bytesR71, bytesL21, bytesL31, bytesR81, bytesHA1, bytesHB1);
        searchStatusInSleepTime(1700);
//        try {
//            Thread.sleep(1700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 关闭左前右后
     */
    public synchronized static void closeLeftQianRightHou() {

//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 3000);
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 2000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 5000);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 2000);

//        //添加的
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1500, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 1500, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1500, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 1500, 0);

        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesR51, bytesR61, bytesL31, bytesR71, bytesR81, bytesHA1, bytesHB1);
        searchStatusInSleepTime(1700);
//        try {
//            Thread.sleep(1700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 关闭奥特曼左边起
     */
    public synchronized static void closeAtemanLeftUp() {
//        byte[] bytesL01 = MotorPType.stepMpZero(0x0100, 3000);
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 6000);
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 6000);
//        byte[] bytesR51 = MotorPType.stepMpZero(0x0105, 3000);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 3000);
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 5000);

        //添加的
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);
        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);


        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81, bytesHA1, bytesHB1);
        searchStatusInSleepTime(2700);
//        try {
//            Thread.sleep(2700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 关闭原舞蹈第一步
     */
    public synchronized static void closeBeforeDanceOne() {

//        byte[] bytes51 = MotorPType.stepMpZero(0x0100, 5000);
        byte[] bytes53 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytes52 = MotorPType.stepMpZero(0x0102, 5000);
        byte[] bytes57 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytes54 = MotorPType.stepMpZero(0x0105, 5000);
        byte[] bytes56 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytes55 = MotorPType.stepMpZero(0x0107, 5000);
        byte[] bytes58 = MotorPType.stepMpZero(0x0108, 5000);

        //添加的
        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

        byte[] bytes51 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
        byte[] bytes52 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
        byte[] bytes54 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
        byte[] bytes55 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

        sendAllBytesToSerial(bytes51, bytes52, bytes53, bytes57, bytes54, bytes55, bytes56, bytes58, bytesHA1, bytesHB1);
        searchStatusInSleepTime(2700);
//        try {
//            Thread.sleep(2700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 关闭原舞蹈第二步
     */
    public synchronized static void closeBeforeDanceTwo() {
//        byte[] bytes51 = MotorPType.stepMpZero(0x0100, 5000);
        byte[] bytes53 = MotorPType.stepMpZero(0x0101, 5000);
        byte[] bytes57 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytes54 = MotorPType.stepMpZero(0x0105, 5000);
        byte[] bytes56 = MotorPType.stepMpZero(0x0106, 5000);
//        byte[] bytes55 = MotorPType.stepMpZero(0x0107, 5000);
        byte[] bytes58 = MotorPType.stepMpZero(0x0108, 5000);
        byte[] bytes41 = MotorPType.stepMpZero(0x010A, 3000);

        //添加的
//        byte[] bytesL21 = MotorPType.stepMpZero(0x0102, 5000);
        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

        byte[] bytes51 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
        byte[] bytes54 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
        byte[] bytes55 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

        sendAllBytesToSerial(bytes51, bytes53, bytes57, bytes41, bytes54, bytes55, bytes56, bytes58, bytesL21, bytesHB1);
        searchStatusInSleepTime(2700);
//        try {
//            Thread.sleep(2700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


    /**
     * 关闭原舞蹈第三步
     */
    public synchronized static void closeBeforeDanceThree() {
//        byte[] bytes51 = MotorPType.stepMpZero(0x0100, 5000);
        byte[] bytes53 = MotorPType.stepMpZero(0x0101, 5000);
//        byte[] bytes22 = MotorPType.stepMpZero(0x0102, 5000);
        byte[] bytes57 = MotorPType.stepMpZero(0x0103, 5000);
//        byte[] bytes54 = MotorPType.stepMpZero(0x0105, 5000);
        byte[] bytes56 = MotorPType.stepMpZero(0x0106, 5000);
        byte[] bytes58 = MotorPType.stepMpZero(0x0108, 5000);
        byte[] bytes41 = MotorPType.stepMpZero(0x010A, 3000);
        //添加的
//        byte[] bytesR71 = MotorPType.stepMpZero(0x0107, 7000);
        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);

        byte[] bytes51 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
        byte[] bytes22 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
        byte[] bytes54 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

        sendAllBytesToSerial(bytes51, bytes53, bytes57, bytes41, bytes22, bytes54, bytes56, bytes58, bytesR71, bytesHB1);
        searchStatusInSleepTime(2700);
//        try {
//            Thread.sleep(2700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public synchronized static void dance1() {
        //第一个动作叉腰
        byte[] eyeControll2 = LightType.eyeControll(1,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255,
                0, 0, 255);
        byte[] topHeadControll = LightType.topHeadControll(1, 0, 0, 255);
        byte[] earControll = LightType.earControll(1, 0, 0, 255);
//        byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 4000, 0);
//        byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 5000, 0);
//        byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 8000, 0);
//        byte[] bytesL4 = MotorPType.stepMpParamsRun(0x0104, 5000, 4000, 0);
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 4000, 0);
//        byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 5000, 0);
//        byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 8000, 0);
//        byte[] bytesR9 = MotorPType.stepMpParamsRun(0x0109, 5000, 4000, 0);
        byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
        byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -5000, 1000, 0);
        byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1600, 0);
//        byte[] bytesL4 = MotorPType.stepMpToEndLoc(0x0104, 4000, 800, 0);
        //右臂
        byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
        byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -5000, 1000, 0);
        byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -8000, 1600, 0);
//        byte[] bytesR9 = MotorPType.stepMpToEndLoc(0x0109, 4000, 800, 0);
        //底盘
        byte[] bytes51 = ClassicType.runWithSpeed(15, -15, 4);
        sendAllBytesToSerial(eyeControll2, topHeadControll, earControll, bytesL0, bytesL1, bytesL2/*, bytesL4*/, bytesR5, bytesR6, bytesR7/*, bytesR9*/, bytes51);
        searchStatusInSleepTime(1700);
    }

    public synchronized static void dance2() {
        //右高左低
//        //左臂
//        byte[] bytesL01 = MotorPType.stepMpParamsRun(0x0100, -5000, 4000, 0);
//        byte[] bytesL11 = MotorPType.stepMpParamsRun(0x0101, -5000, 5000, 0);
//        byte[] bytesL21 = MotorPType.stepMpParamsRun(0x0102, 5000, 6000, 0);
//        //头部
//        byte[] bytesHR1 = MotorPType.stepMpParamsRun(0x010B, -5000, 3000, 0);
//        //右臂
//        byte[] bytesR51 = MotorPType.stepMpParamsRun(0x0105, 5000, 11000, 0);
//        byte[] bytesR61 = MotorPType.stepMpParamsRun(0x0106, -5000, 8000, 0);
//        byte[] bytesR71 = MotorPType.stepMpParamsRun(0x0107, -5000, 9000, 0);
//        byte[] bytesR81 = MotorPType.stepMpParamsRun(0x0108, 5000, 5000, 0);
        //左臂
        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
        byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -5000, 1000, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 6000, 1200, 0);
        //头部
        byte[] bytesHR1 = MotorPType.stepMpToEndLoc(0x010B, -3000, 600, 0);
        //右臂
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 11000, 2200, 0);
        byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -8000, 1600, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -9000, 1800, 0);
        byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 5000, 1000, 0);
        //底盘
        byte[] bytes52 = ClassicType.runWithSpeed(-15, 15, 8);
        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesHR1, bytesR51, bytesR61, bytesR71, bytesR81, bytes52);
        searchStatusInSleepTime(2300);
    }

    public synchronized static void dance3() {
        //左高右低
//                    //左臂
//                    byte[] bytesL0 = MotorPType.stepMpParamsRun(0x0100, -5000, 11000, 0);
//                    byte[] bytesL1 = MotorPType.stepMpParamsRun(0x0101, -5000, 8000, 0);
//                    byte[] bytesL2 = MotorPType.stepMpParamsRun(0x0102, 5000, 9000, 0);
//                    byte[] bytesL3 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
//                    //头部
//                    byte[] bytesHR1 = MotorPType.stepMpParamsRun(0x010B, 5000, 3000, 0);
//                    //右臂
//                    byte[] bytesR5 = MotorPType.stepMpParamsRun(0x0105, 5000, 4000, 0);
//                    byte[] bytesR6 = MotorPType.stepMpParamsRun(0x0106, -5000, 5000, 0);
//                    byte[] bytesR7 = MotorPType.stepMpParamsRun(0x0107, -5000, 6000, 0);//左臂
        byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -11000, 2200, 0);
        byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -8000, 1600, 0);
        byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 9000, 1800, 0);
        byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
        //头部
        byte[] bytesHR1 = MotorPType.stepMpToEndLoc(0x010B, 3000, 600, 0);
        //右臂
        byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
        byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -5000, 1000, 0);
        byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -6000, 1200, 0);
        //底盘
        byte[] bytes53 = ClassicType.runWithSpeed(15, -15, 8);
        sendAllBytesToSerial(bytesL0, bytesL1, bytesL2, bytesL3, bytesHR1, bytesR5, bytesR6, bytesR7, bytes53);
        searchStatusInSleepTime(2300);
    }

    public synchronized static void dance4() {
//一起高
//                                //左臂
//                                byte[] bytesL01 = MotorPType.stepMpParamsRun(0x0100, -5000, 11000, 0);
//                                byte[] bytesL11 = MotorPType.stepMpParamsRun(0x0101, -5000, 8000, 0);
//                                byte[] bytesL21 = MotorPType.stepMpParamsRun(0x0102, 5000, 9000, 0);
//                                byte[] bytesL31 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
//                                //右臂
//                                byte[] bytesR51 = MotorPType.stepMpParamsRun(0x0105, 5000, 11000, 0);
//                                byte[] bytesR61 = MotorPType.stepMpParamsRun(0x0106, -5000, 8000, 0);
//                                byte[] bytesR71 = MotorPType.stepMpParamsRun(0x0107, -5000, 9000, 0);
//                                byte[] bytesR81 = MotorPType.stepMpParamsRun(0x0108, 5000, 5000, 0);
        //左臂
        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -11000, 2200, 0);
        byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -8000, 1600, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 9000, 1800, 0);
        byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
        //右臂
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 11000, 2200, 0);
        byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -8000, 1600, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -9000, 1800, 0);
        byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 5000, 1000, 0);
        //底盘
        byte[] bytes52 = ClassicType.runWithSpeed(-15, 15, 8);
        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81, bytes52);
        searchStatusInSleepTime(2300);
    }

    public synchronized static void dance5() {
//叉腰
//                                            byte[] bytesL02 = MotorPType.stepMpParamsRun(0x0100, -5000, 4000, 0);
//                                            byte[] bytesL12 = MotorPType.stepMpParamsRun(0x0101, -5000, 5000, 0);
//                                            byte[] bytesL22 = MotorPType.stepMpParamsRun(0x0102, 5000, 8000, 0);
//                                            byte[] bytesL42 = MotorPType.stepMpParamsRun(0x0104, 5000, 4000, 0);
//                                            //右臂
//                                            byte[] bytesR52 = MotorPType.stepMpParamsRun(0x0105, 5000, 4000, 0);
//                                            byte[] bytesR62 = MotorPType.stepMpParamsRun(0x0106, -5000, 5000, 0);
//                                            byte[] bytesR72 = MotorPType.stepMpParamsRun(0x0107, -5000, 8000, 0);
//                                            byte[] bytesR92 = MotorPType.stepMpParamsRun(0x0109, 5000, 4000, 0);
        byte[] bytesL02 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
        byte[] bytesL12 = MotorPType.stepMpToEndLoc(0x0101, -5000, 1000, 0);
        byte[] bytesL22 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1600, 0);
//                                            byte[] bytesL42 = MotorPType.stepMpToEndLoc(0x0104, 4000, 800, 0);
        //右臂
        byte[] bytesR52 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
        byte[] bytesR62 = MotorPType.stepMpToEndLoc(0x0106, -5000, 1000, 0);
        byte[] bytesR72 = MotorPType.stepMpToEndLoc(0x0107, -8000, 1600, 0);
//                                            byte[] bytesR92 = MotorPType.stepMpToEndLoc(0x0109, 4000, 800, 0);
        //底盘
        byte[] bytes531 = ClassicType.runWithSpeed(15, -15, 8);
        sendAllBytesToSerial(bytesL02, bytesL12, bytesL22/*, bytesL42*/, bytesR52, bytesR62, bytesR72/*, bytesR92*/, bytes531);
        searchStatusInSleepTime(2000);
    }

    public synchronized static void dance6() {
//左手叉腰右边看
//                                                        //左臂
//                                                        final byte[] bytesL03 = MotorPType.stepMpParamsRun(0x0100, -5000, 4000, 0);
//                                                        final byte[] bytesL13 = MotorPType.stepMpParamsRun(0x0101, -5000, 5000, 0);
//                                                        final byte[] bytesL23 = MotorPType.stepMpParamsRun(0x0102, 5000, 8000, 0);
//                                                        final byte[] bytesL43 = MotorPType.stepMpParamsRun(0x0104, 5000, 4000, 0);
//                                                        //右臂
//                                                        final byte[] bytesR53 = MotorPType.stepMpParamsRun(0x0105, 4000, 13000, 0);
//                                                        final byte[] bytesR63 = MotorPType.stepMpParamsRun(0x0106, -5000, 3000, 0);
//                                                        final byte[] bytesR73 = MotorPType.stepMpParamsRun(0x0107, 5000, 1, 0);
//                                                        final byte[] bytesR83 = MotorPType.stepMpParamsRun(0x0108, 5000, 3000, 0);
//                                                        //头部
//                                                        final byte[] bytesHR13 = MotorPType.stepMpParamsRun(0x010B, -5000, 3000, 0);
//                                                        final byte[] bytesHR23 = MotorPType.stepMpParamsRun(0x010A, 5000, 4000, 0);
        //左臂
        final byte[] bytesL03 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
        final byte[] bytesL13 = MotorPType.stepMpToEndLoc(0x0101, -5000, 1000, 0);
        final byte[] bytesL23 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1600, 0);
//                                                        final byte[] bytesL43 = MotorPType.stepMpToEndLoc(0x0104, 4000, 800, 0);
        //右臂
        final byte[] bytesR53 = MotorPType.stepMpToEndLoc(0x0105, 13000, 3250, 0);
        final byte[] bytesR63 = MotorPType.stepMpToEndLoc(0x0106, -3000, 600, 0);
        final byte[] bytesR73 = MotorPType.stepMpToEndLoc(0x0107, 1, 1000, 0);
        final byte[] bytesR83 = MotorPType.stepMpToEndLoc(0x0108, 3000, 600, 0);
        //头部
        final byte[] bytesHR13 = MotorPType.stepMpToEndLoc(0x010B, -3000, 600, 0);
        final byte[] bytesHR23 = MotorPType.stepMpToEndLoc(0x010A, 4000, 800, 0);
        //底盘
        byte[] bytes521 = ClassicType.runWithSpeed(-15, 15, 8);
        sendAllBytesToSerial(bytesL03, bytesL13, bytesL23/*, bytesL43*/, bytesR53, bytesR63, bytesR73, bytesR83, bytesHR13, bytesHR23, bytes521);
        searchStatusInSleepTime(3300);
    }

    public synchronized static void dance7() {
        //右手叉腰左边看
//                                                                    //左臂
//                                                                    final byte[] bytesL04 = MotorPType.stepMpParamsRun(0x0100, -4000, 13000, 0);
//                                                                    final byte[] bytesL14 = MotorPType.stepMpParamsRun(0x0101, -5000, 3000, 0);
//                                                                    final byte[] bytesL24 = MotorPType.stepMpParamsRun(0x0102, 5000, 1, 0);
//                                                                    final byte[] bytesL34 = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
//                                                                    //头部
//                                                                    final byte[] bytesHR14 = MotorPType.stepMpParamsRun(0x010B, 5000, 3000, 0);
//                                                                    final byte[] bytesHR24 = MotorPType.stepMpParamsRun(0x010A, 5000, 4000, 0);
//                                                                    //右臂
//                                                                    final byte[] bytesR54 = MotorPType.stepMpParamsRun(0x0105, 5000, 4000, 0);
//                                                                    final byte[] bytesR64 = MotorPType.stepMpParamsRun(0x0106, -5000, 5000, 0);
//                                                                    final byte[] bytesR74 = MotorPType.stepMpParamsRun(0x0107, -5000, 8000, 0);
//                                                                    final byte[] bytesR94 = MotorPType.stepMpParamsRun(0x0109, 5000, 4000, 0);
        //左臂
        final byte[] bytesL04 = MotorPType.stepMpToEndLoc(0x0100, -13000, 3250, 0);
        final byte[] bytesL14 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
        final byte[] bytesL24 = MotorPType.stepMpToEndLoc(0x0102, 1, 1000, 0);
        final byte[] bytesL34 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
        //头部
        final byte[] bytesHR14 = MotorPType.stepMpToEndLoc(0x010B, 3000, 600, 0);
        final byte[] bytesHR24 = MotorPType.stepMpToEndLoc(0x010A, 4000, 800, 0);
        //右臂
        final byte[] bytesR54 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
        final byte[] bytesR64 = MotorPType.stepMpToEndLoc(0x0106, -5000, 1000, 0);
        final byte[] bytesR74 = MotorPType.stepMpToEndLoc(0x0107, -8000, 1600, 0);
//                                                                    final byte[] bytesR94 = MotorPType.stepMpToEndLoc(0x0109, 4000, 800, 0);
        //底盘
        byte[] bytes532 = ClassicType.runWithSpeed(15, -15, 8);
        sendAllBytesToSerial(bytesL04, bytesL14, bytesL24, bytesL34, bytesHR14, bytesHR24, bytesR54, bytesR64, bytesR74/*, bytesR94*/, bytes532);
        searchStatusInSleepTime(3300);
    }

    public synchronized static void dance8() {
        //右手叉腰左边看
        //左臂
        //底盘
        //左臂
        final byte[] bytesL04 = MotorPType.stepMpToEndLoc(0x0100, -13000, 3250, 0);
        final byte[] bytesL14 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
        final byte[] bytesL24 = MotorPType.stepMpToEndLoc(0x0102, 1, 1000, 0);
        final byte[] bytesL34 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
        //头部
        final byte[] bytesHR14 = MotorPType.stepMpToEndLoc(0x010B, 3000, 600, 0);
        final byte[] bytesHR24 = MotorPType.stepMpToEndLoc(0x010A, 4000, 800, 0);
        //右臂
        final byte[] bytesR54 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
        final byte[] bytesR64 = MotorPType.stepMpToEndLoc(0x0106, -5000, 1000, 0);
        final byte[] bytesR74 = MotorPType.stepMpToEndLoc(0x0107, -8000, 1600, 0);
        byte[] bytes522 = ClassicType.runWithSpeed(-15, 15, 8);
        sendAllBytesToSerial(bytesL04, bytesL14, bytesL24, bytesL34, bytesHR14, bytesHR24, bytesR54, bytesR64, bytesR74, /*bytesR94,*/ bytes522);
        try {
            Thread.sleep(3300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void dance9() {
        //左臂
        final byte[] bytesL03 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
        final byte[] bytesL13 = MotorPType.stepMpToEndLoc(0x0101, -5000, 1000, 0);
        final byte[] bytesL23 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1600, 0);
//                                                        final byte[] bytesL43 = MotorPType.stepMpToEndLoc(0x0104, 4000, 800, 0);
        //右臂
        final byte[] bytesR53 = MotorPType.stepMpToEndLoc(0x0105, 13000, 3250, 0);
        final byte[] bytesR63 = MotorPType.stepMpToEndLoc(0x0106, -3000, 600, 0);
        final byte[] bytesR73 = MotorPType.stepMpToEndLoc(0x0107, 1, 1000, 0);
        final byte[] bytesR83 = MotorPType.stepMpToEndLoc(0x0108, 3000, 600, 0);
        //头部
        final byte[] bytesHR13 = MotorPType.stepMpToEndLoc(0x010B, -3000, 600, 0);
        final byte[] bytesHR23 = MotorPType.stepMpToEndLoc(0x010A, 4000, 800, 0);
        //左手叉腰右边看
        byte[] bytes533 = ClassicType.runWithSpeed(15, -15, 8);
        sendAllBytesToSerial(bytesL03, bytesL13, bytesL23,/* bytesL43,*/ bytesR53, bytesR63, bytesR73, bytesR83, bytesHR13, bytesHR23, bytes533);
        searchStatusInSleepTime(3300);
    }

    public synchronized static void dance10() {
/**
 * 奥特曼姿势，左边起
 */
//                                                                                                        //左臂
//                                                                                                        byte[] bytesL07 = MotorPType.stepMpParamsRun(0x0100, -5000, 8000, 0);
//                                                                                                        byte[] bytesL17 = MotorPType.stepMpParamsRun(0x0101, -5000, 3000, 0);
//                                                                                                        byte[] bytesL27 = MotorPType.stepMpParamsRun(0x0102, 5000, 10000, 0);
//                                                                                                        byte[] bytesL37 = MotorPType.stepMpParamsRun(0x0103, -5000, 7000, 0);
//                                                                                                        //右臂
//                                                                                                        byte[] bytesR57 = MotorPType.stepMpParamsRun(0x0105, 5000, 7000, 0);
//                                                                                                        byte[] bytesR67 = MotorPType.stepMpParamsRun(0x0106, -5000, 3000, 0);
//                                                                                                        byte[] bytesR77 = MotorPType.stepMpParamsRun(0x0107, -5000, 9000, 0);
        //左臂
        byte[] bytesL07 = MotorPType.stepMpToEndLoc(0x0100, -8000, 1600, 0);
        byte[] bytesL17 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
        byte[] bytesL27 = MotorPType.stepMpToEndLoc(0x0102, 10000, 2000, 0);
        byte[] bytesL37 = MotorPType.stepMpToEndLoc(0x0103, -7000, 1400, 0);
        //右臂
        byte[] bytesR57 = MotorPType.stepMpToEndLoc(0x0105, 7000, 1400, 0);
        byte[] bytesR67 = MotorPType.stepMpToEndLoc(0x0106, -3000, 600, 0);
        byte[] bytesR77 = MotorPType.stepMpToEndLoc(0x0107, -9000, 1800, 0);
        //底盘
        byte[] bytes523 = ClassicType.runWithSpeed(-15, 15, 8);
        sendAllBytesToSerial(bytesL07, bytesL17, bytesL27, bytesL37, bytesR57, bytesR67, bytesR77, bytes523);
        searchStatusInSleepTime(2500);
    }

    public synchronized static void dance11() {
//原舞蹈抬起手臂
//                                                                                                                    //左臂
//                                                                                                                    byte[] bytesL08 = MotorPType.stepMpParamsRun(0x0100, -5000, 13000, 0);
//                                                                                                                    byte[] bytesL28 = MotorPType.stepMpParamsRun(0x0102, 5000, 3000, 0);
//                                                                                                                    byte[] bytesL18 = MotorPType.stepMpParamsRun(0x0101, -5000, 5000, 0);
//                                                                                                                    byte[] bytesL38 = MotorPType.stepMpParamsRun(0x0103, 5000, 1, 0);
//                                                                                                                    //右臂
//                                                                                                                    byte[] bytesR58 = MotorPType.stepMpParamsRun(0x0105, 5000, 13000, 0);
//                                                                                                                    byte[] bytesR78 = MotorPType.stepMpParamsRun(0x0107, -5000, 3000, 0);
//                                                                                                                    byte[] bytesR68 = MotorPType.stepMpParamsRun(0x0106, -5000, 5000, 0);
//                                                                                                                    byte[] bytesR88 = MotorPType.stepMpParamsRun(0x0108, 5000, 1, 0);
        //左臂
        byte[] bytesL08 = MotorPType.stepMpToEndLoc(0x0100, -13000, 2600, 0);
        byte[] bytesL28 = MotorPType.stepMpToEndLoc(0x0102, 3000, 600, 0);
        byte[] bytesL18 = MotorPType.stepMpToEndLoc(0x0101, -5000, 1000, 0);
        byte[] bytesL38 = MotorPType.stepMpToEndLoc(0x0103, 1, 1000, 0);
        //右臂
        byte[] bytesR58 = MotorPType.stepMpToEndLoc(0x0105, 13000, 2600, 0);
        byte[] bytesR78 = MotorPType.stepMpToEndLoc(0x0107, -3000, 600, 0);
        byte[] bytesR68 = MotorPType.stepMpToEndLoc(0x0106, -5000, 1000, 0);
        byte[] bytesR88 = MotorPType.stepMpToEndLoc(0x0108, 1, 1000, 0);
        //底盘
        byte[] bytes534 = ClassicType.runWithSpeed(15, -15, 8);
        sendAllBytesToSerial(bytesL08, bytesL28, bytesL18, bytesL38, bytesR58, bytesR78, bytesR68, bytesR88, bytes534);
        searchStatusInSleepTime(3000);
    }

    public synchronized static void dance12() {
        //原舞蹈High第二步
//                                                                                                                                byte[] bytes21 = MotorPType.stepMpParamsRun(0x0102, -5000, 3000, 0);
//                                                                                                                                byte[] bytes31 = MotorPType.stepMpParamsRun(0x0107, -5000, 7000, 0);
//                                                                                                                                byte[] bytes41 = MotorPType.stepMpParamsRun(0x010A, 3000, 2000, 1);
        byte[] bytes21 = MotorPType.stepMpToEndLoc(0x0102, 0, 600, 0);
        byte[] bytes31 = MotorPType.stepMpToEndLoc(0x0107, -10000, 1400, 0);
        byte[] bytes41 = MotorPType.stepMpToEndLoc(0x010A, 2000, 700, 1);
        //底盘
        byte[] bytes524 = ClassicType.runWithSpeed(-15, 15, 8);
        sendAllBytesToSerial(bytes21, bytes31, bytes41, bytes524);
        searchStatusInSleepTime(1500);
    }

    public synchronized static void dance13() {
        //原舞蹈High第三步
//                                                                                                                                            byte[] bytes22 = MotorPType.stepMpParamsRun(0x0102, 5000, 10000, 0);
//                                                                                                                                            byte[] bytes32 = MotorPType.stepMpParamsRun(0x0107, 5000, 10000, 0);
//                                                                                                                                            byte[] bytes42 = MotorPType.stepMpParamsRun(0x010A, -3000, 4000, 1);
        byte[] bytes22 = MotorPType.stepMpToEndLoc(0x0102, 10000, 2000, 0);
        byte[] bytes32 = MotorPType.stepMpToEndLoc(0x0107, 0, 2000, 0);
        byte[] bytes42 = MotorPType.stepMpToEndLoc(0x010A, -2000, 1400, 1);
        //底盘
        byte[] bytes535 = ClassicType.runWithSpeed(15, -15, 8);
        sendAllBytesToSerial(bytes22, bytes32, bytes42, bytes535);
        searchStatusInSleepTime(2100);
//                                                                                                                                            try {
//                                                                                                                                                Thread.sleep(2100);
//                                                                                                                                            } catch (InterruptedException e) {
//                                                                                                                                                e.printStackTrace();
//                                                                                                                                            }
    }

    public synchronized static void dance14() {
//右高左低
//                                                                                                                                                        //左臂
//                                                                                                                                                        byte[] bytesL09 = MotorPType.stepMpParamsRun(0x0100, -5000, 4000, 0);
//                                                                                                                                                        byte[] bytesL19 = MotorPType.stepMpParamsRun(0x0101, -5000, 5000, 0);
//                                                                                                                                                        byte[] bytesL29 = MotorPType.stepMpParamsRun(0x0102, 5000, 6000, 0);
//                                                                                                                                                        //头部
//                                                                                                                                                        byte[] bytesHR19 = MotorPType.stepMpParamsRun(0x010B, -5000, 3000, 0);
//                                                                                                                                                        //右臂
//                                                                                                                                                        byte[] bytesR59 = MotorPType.stepMpParamsRun(0x0105, 5000, 11000, 0);
//                                                                                                                                                        byte[] bytesR69 = MotorPType.stepMpParamsRun(0x0106, -5000, 8000, 0);
//                                                                                                                                                        byte[] bytesR79 = MotorPType.stepMpParamsRun(0x0107, -5000, 9000, 0);
//                                                                                                                                                        byte[] bytesR89 = MotorPType.stepMpParamsRun(0x0108, 5000, 5000, 0);
        //左臂
        byte[] bytesL09 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
        byte[] bytesL19 = MotorPType.stepMpToEndLoc(0x0101, -5000, 1000, 0);
        byte[] bytesL29 = MotorPType.stepMpToEndLoc(0x0102, 6000, 1200, 0);
        //头部
        byte[] bytesHR19 = MotorPType.stepMpToEndLoc(0x010B, -3000, 600, 0);
        //右臂
        byte[] bytesR59 = MotorPType.stepMpToEndLoc(0x0105, 11000, 2200, 0);
        byte[] bytesR69 = MotorPType.stepMpToEndLoc(0x0106, -8000, 1600, 0);
        byte[] bytesR79 = MotorPType.stepMpToEndLoc(0x0107, -9000, 1800, 0);
        byte[] bytesR89 = MotorPType.stepMpToEndLoc(0x0108, 5000, 1000, 0);
        //底盘
        byte[] bytes525 = ClassicType.runWithSpeed(-15, 15, 8);
        sendAllBytesToSerial(bytesL09, bytesL19, bytesL29, bytesHR19, bytesR59, bytesR69, bytesR79, bytesR89, bytes525);
        searchStatusInSleepTime(2300);
    }


}
