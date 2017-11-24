package com.xg.east.hibot_b_library.action;


import com.xg.east.hibot_b_library.SerialSend;
import com.xg.east.hibot_b_library.service.usbSendType.MotorPType;

import static com.xg.east.hibot_b_library.SerialSend.searchStatusInSleepTime;
import static com.xg.east.hibot_b_library.SerialSend.sendAllBytesToSerial;

/**
 * 项目名称：TestHibot_b_Jar
 * 创建人：East
 * 创建时间：2017/4/27 15:25
 * 邮箱：EastRiseWM@163.com
 */

public class SpeakAction {
    /**
     * 演讲的第一个动作
     */
    public synchronized static void speachActionOne() {
        synchronized (SerialSend.lock){
            SerialSend.isActionFinish = false;
            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1500, 0);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0100, 5000);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 1500, 0);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0102, 5000);

            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
            byte[] bytesR6 = MotorPType.stepMpZero(0x0106, 5000);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -8000, 1600, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 8000, 1600, 0);
            sendAllBytesToSerial(bytesL01, bytesL11,bytesL21,bytesL31, bytesR5,bytesR6,bytesR7, bytesR8);
            searchStatusInSleepTime(2000);
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 演讲的时候基本动作
     */
    public  static void stepToSpeachBaseAction(){
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
            byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);
            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 8000, 800, 0);
            byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -8000, 800, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
            byte[] bytesR6 = MotorPType.stepMpZero(0x0106, 5000);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -8000, 500, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 8000, 800, 0);
            sendAllBytesToSerial(bytesHA1,bytesHB1,bytesL01, bytesL11,bytesL21,bytesL31, bytesR5,bytesR6,bytesR7, bytesR8);
            SerialSend.isActionFinish = true;
        }
    }


    /**
     * 演讲的第二个动作
     */
    public synchronized static void speachActionTwo() {
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1600, 0);
            byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -8000, 1600, 0);

            //右臂
//        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1500, 0);
//        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 1500, 0);
            sendAllBytesToSerial(bytesL01,bytesL11, bytesL21, bytesL31/*, bytesR51, bytesR71*/);
            searchStatusInSleepTime(2000);
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 演讲的第三个动作
     */
    public synchronized static void speachActionThree() {
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1600, 0);
            byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -8000, 1600, 0);

            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
            byte[] bytesR6 = MotorPType.stepMpZero(0x0106, 5000);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -8000, 1600, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 8000, 1600, 0);
            sendAllBytesToSerial(bytesL01,bytesL11, bytesL21, bytesL31, bytesR5, bytesR6,bytesR7, bytesR8);
            searchStatusInSleepTime(2000);
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 起式TWO
     */
    public synchronized static void speakActionStartingForm(){
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
            //左臂
//        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -5240/8, 800, 0);
//        byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -35400/8,800,0);
//        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 85000/8, 1500, 0);
//        byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -2320/8, 800, 0);
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -10560/8, 800, 0);
            byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -35400/8,800,0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 80640/8, 1500, 0);
            byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -2320/8, 800, 0);
            //右臂
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 10560/8, 800, 0);
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -35400/8, 800, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -80640/8, 1500, 0);
            byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 2320/8, 800, 0);
            sendAllBytesToSerial(bytesL01,bytesL11,bytesL21,bytesL31,bytesR51,bytesR61,bytesR71,bytesR81);
            searchStatusInSleepTime(1700);

            //右臂
            byte[] bytesR52 = MotorPType.stepMpToEndLoc(0x0105, 82410/8, 800, 0);
            byte[] bytesR62 = MotorPType.stepMpToEndLoc(0x0106, -18040/8, 800, 0);
            byte[] bytesR72 = MotorPType.stepMpToEndLoc(0x0107, -87500/8, 800, 0);
            byte[] bytesR82 = MotorPType.stepMpToEndLoc(0x0108, 63220/8, 800, 0);
            sendAllBytesToSerial(bytesR52,bytesR62,bytesR72,bytesR82);
            searchStatusInSleepTime(1000);

            //右臂
            byte[] bytesR53 = MotorPType.stepMpToEndLoc(0x0105, 84410/8, 800, 0);
            byte[] bytesR63 = MotorPType.stepMpToEndLoc(0x0106, -16260/8, 800, 0);
            byte[] bytesR73 = MotorPType.stepMpToEndLoc(0x0107, 0, 800, 0);
            byte[] bytesR83 = MotorPType.stepMpToEndLoc(0x0108, 63310/8, 800, 0);
            sendAllBytesToSerial(bytesR53,bytesR63,bytesR73,bytesR83);
            searchStatusInSleepTime(1000);

            stepToSpeachBaseAction();
            searchStatusInSleepTime(1000);
            SerialSend.isActionFinish = true;
        }
    }


    /**
     * 抄袭pepper动作二
     */
    public synchronized static void speakActionFromPepper() {
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
            //左臂
        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -10560 / 8, 800, 0);
        byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -35400 / 8, 800, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 80640 / 8, 1500, 0);
        byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -2320 / 8, 800, 0);
        //右臂
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 10560 / 8, 800, 0);
        byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -35400 / 8, 800, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -80640 / 8, 1500, 0);
        byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 2320 / 8, 800, 0);
        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81);
        searchStatusInSleepTime(1700);

        //左臂
        byte[] bytesL02 = MotorPType.stepMpToEndLoc(0x0100, -77760 / 8, 800, 0);
        byte[] bytesL12 = MotorPType.stepMpToEndLoc(0x0101, -41300 / 8, 800, 0);
        byte[] bytesL22 = MotorPType.stepMpToEndLoc(0x0102, 40470 / 8, 800, 0);
        byte[] bytesL32 = MotorPType.stepMpToEndLoc(0x0103, -42440 / 8, 800, 0);
        //右臂
        byte[] bytesR52 = MotorPType.stepMpToEndLoc(0x0105, 77760 / 8, 800, 0);
        byte[] bytesR62 = MotorPType.stepMpToEndLoc(0x0106, -41300 / 8, 800, 0);
        byte[] bytesR72 = MotorPType.stepMpToEndLoc(0x0107, -40470 / 8, 800, 0);
        byte[] bytesR82 = MotorPType.stepMpToEndLoc(0x0108, 42440 / 8, 800, 0);
        sendAllBytesToSerial(bytesL02, bytesL12, bytesL22, bytesL32, bytesR52, bytesR62, bytesR72, bytesR82);
        searchStatusInSleepTime(1500);

        //左臂
        byte[] bytesL03 = MotorPType.stepMpToEndLoc(0x0100, -140980 / 8, 800, 0);
        byte[] bytesL13 = MotorPType.stepMpToEndLoc(0x0101, -31430 / 8, 800, 0);
        byte[] bytesL23 = MotorPType.stepMpToEndLoc(0x0102, 67100 / 8, 800, 0);
        byte[] bytesL33 = MotorPType.stepMpToEndLoc(0x0103, -25510 / 8, 800, 0);
        //右臂
        byte[] bytesR53 = MotorPType.stepMpToEndLoc(0x0105, 140980 / 8, 800, 0);
        byte[] bytesR63 = MotorPType.stepMpToEndLoc(0x0106, -31430 / 8, 800, 0);
        byte[] bytesR73 = MotorPType.stepMpToEndLoc(0x0107, -67100 / 8, 800, 0);
        byte[] bytesR83 = MotorPType.stepMpToEndLoc(0x0108, 25510 / 8, 800, 0);
        sendAllBytesToSerial(bytesL03, bytesL13, bytesL23, bytesL33, bytesR53, bytesR63, bytesR73, bytesR83);
        searchStatusInSleepTime(1500);

        //头部
        byte[] bytesHA = MotorPType.stepMpToEndLoc(0x010A, 12000/8, 800, 1);
        //左臂
        byte[] bytesL04 = MotorPType.stepMpToEndLoc(0x0100, -144500 / 8, 500, 0);
        byte[] bytesL14 = MotorPType.stepMpToEndLoc(0x0101, -19330 / 8, 500, 0);
        byte[] bytesL24 = MotorPType.stepMpToEndLoc(0x0102, 45860 / 8, 500, 0);
        byte[] bytesL34 = MotorPType.stepMpToEndLoc(0x0103, -25350 / 8, 500, 0);
        //右臂
        byte[] bytesR54 = MotorPType.stepMpToEndLoc(0x0105, 148870 / 8, 500, 0);
        byte[] bytesR64 = MotorPType.stepMpToEndLoc(0x0106, -19330 / 8, 500, 0);
        byte[] bytesR74 = MotorPType.stepMpToEndLoc(0x0107, -45860 / 8, 500, 0);
        byte[] bytesR84 = MotorPType.stepMpToEndLoc(0x0108, 25350 / 8, 500, 0);
        sendAllBytesToSerial(bytesHA,bytesL04, bytesL14, bytesL24, bytesL34, bytesR54, bytesR64, bytesR74, bytesR84);
        searchStatusInSleepTime(700);

        //头部
        byte[] bytesHA2 = MotorPType.stepMpToEndLoc(0x010A, 0, 400, 1);
        //左臂
        byte[] bytesL05 = MotorPType.stepMpToEndLoc(0x0100, -130980 / 8, 400, 0);
        byte[] bytesL15 = MotorPType.stepMpToEndLoc(0x0101, -31430/ 8, 400, 0);
        byte[] bytesL25 = MotorPType.stepMpToEndLoc(0x0102, 67100/ 8, 400, 0);
        byte[] bytesL35 = MotorPType.stepMpToEndLoc(0x0103, -25510/ 8, 400, 0);
        //右臂
        byte[] bytesR55 = MotorPType.stepMpToEndLoc(0x0105, 130700/ 8, 400, 0);
        byte[] bytesR65 = MotorPType.stepMpToEndLoc(0x0106, -31430/ 8, 400, 0);
        byte[] bytesR75 = MotorPType.stepMpToEndLoc(0x0107, -67100/ 8, 400, 0);
        byte[] bytesR85 = MotorPType.stepMpToEndLoc(0x0108, 25510/ 8, 400, 0);
        sendAllBytesToSerial(bytesHA2,bytesL05, bytesL15, bytesL25, bytesL35, bytesR55, bytesR65, bytesR75, bytesR85);
        searchStatusInSleepTime(1200);

        //头部
        byte[] bytesHA3 = MotorPType.stepMpToEndLoc(0x010B, 3500, 600, 1);
        //左臂
        byte[] bytesL06 = MotorPType.stepMpToEndLoc(0x0100, -144600 / 8, 800, 0);
        byte[] bytesL16 = MotorPType.stepMpToEndLoc(0x0101, -61660/ 8, 800, 0);
        byte[] bytesL26 = MotorPType.stepMpToEndLoc(0x0102, 88420/ 8, 800, 0);
        byte[] bytesL36 = MotorPType.stepMpToEndLoc(0x0103, -10800/ 8, 800, 0);
        //右臂
        byte[] bytesR56 = MotorPType.stepMpToEndLoc(0x0105, 148840/ 8, 800, 0);
        byte[] bytesR66 = MotorPType.stepMpToEndLoc(0x0106, -61660/ 8, 800, 0);
        byte[] bytesR76 = MotorPType.stepMpToEndLoc(0x0107, -88420/ 8, 800, 0);
        byte[] bytesR86 = MotorPType.stepMpToEndLoc(0x0108, 10800/ 8, 800, 0);
        sendAllBytesToSerial(bytesHA3,bytesL06, bytesL16, bytesL26, bytesL36, bytesR56, bytesR66, bytesR76, bytesR86);
        searchStatusInSleepTime(1000);

        //头部
        byte[] bytesHA4 = MotorPType.stepMpToEndLoc(0x010B, -3500, 700, 1);
        sendAllBytesToSerial(bytesHA4);
        searchStatusInSleepTime(1000);

        //头部
        byte[] bytesHA5 = MotorPType.stepMpZero(0x010B, 5000);
        sendAllBytesToSerial(bytesHA5);
        searchStatusInSleepTime(1000);

            byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);
            //左臂
            byte[] bytesL07 = MotorPType.stepMpToEndLoc(0x0100, -4000, 1000, 0);
            byte[] bytesL17 = MotorPType.stepMpZero(0x0101, 5000);
            byte[] bytesL27 = MotorPType.stepMpToEndLoc(0x0102, 8000, 800, 0);
            byte[] bytesL37 = MotorPType.stepMpToEndLoc(0x0103, -8000, 800, 0);
            //右臂
            byte[] bytesR57 = MotorPType.stepMpToEndLoc(0x0105, 4000, 1000, 0);
            byte[] bytesR67 = MotorPType.stepMpZero(0x0106, 5000);
            byte[] bytesR77 = MotorPType.stepMpToEndLoc(0x0107, -8000, 500, 0);
            byte[] bytesR87 = MotorPType.stepMpToEndLoc(0x0108, 8000, 800, 0);
            sendAllBytesToSerial(bytesHA1,bytesHB1,bytesL07, bytesL17,bytesL27,bytesL37, bytesR57,bytesR67,bytesR77, bytesR87);
            searchStatusInSleepTime(1200);
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 抄袭pepper三
     */
    public synchronized static void speakActionFromPepperThree(){
        synchronized (SerialSend.lock){
            SerialSend.isActionFinish = false;
          //左臂
        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -10560 / 8, 800, 0);
        byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -29450 / 8, 800, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 80640 / 8, 1500, 0);
        byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -10670 / 8, 800, 0);
        //右臂
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 10560 / 8, 800, 0);
        byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -29450 / 8, 800, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -80640 / 8, 1500, 0);
        byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 10670 / 8, 800, 0);
        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81);
        searchStatusInSleepTime(1700);

        //左臂
        byte[] bytesL02 = MotorPType.stepMpToEndLoc(0x0100, -91690 / 8, 800, 0);
        byte[] bytesL12 = MotorPType.stepMpToEndLoc(0x0101, -10030 / 8, 800, 0);
        byte[] bytesL22 = MotorPType.stepMpToEndLoc(0x0102, 64730 / 8, 800, 0);
        byte[] bytesL32 = MotorPType.stepMpToEndLoc(0x0103, -59420 / 8, 800, 0);
        //右臂
        byte[] bytesR52 = MotorPType.stepMpToEndLoc(0x0105, 91690 / 8, 800, 0);
        byte[] bytesR62 = MotorPType.stepMpToEndLoc(0x0106, -10030 / 8, 800, 0);
        byte[] bytesR72 = MotorPType.stepMpToEndLoc(0x0107, -64730 / 8, 800, 0);
        byte[] bytesR82 = MotorPType.stepMpToEndLoc(0x0108, 59420 / 8, 800, 0);
        sendAllBytesToSerial(bytesL02, bytesL12, bytesL22, bytesL32, bytesR52, bytesR62, bytesR72, bytesR82);
        searchStatusInSleepTime(1500);

        //头部
        byte[] bytesHA = MotorPType.stepMpToEndLoc(0x010A, -18000/8, 400, 1);
        //左臂
        byte[] bytesL03 = MotorPType.stepMpToEndLoc(0x0100, -79090 / 8, 400, 0);
        byte[] bytesL13 = MotorPType.stepMpToEndLoc(0x0101, -10030 / 8, 400, 0);
        byte[] bytesL23 = MotorPType.stepMpToEndLoc(0x0102, 31140 / 8, 400, 0);
        byte[] bytesL33 = MotorPType.stepMpToEndLoc(0x0103, -66400 / 8, 400, 0);
        //右臂
        byte[] bytesR53 = MotorPType.stepMpToEndLoc(0x0105, 79090 / 8, 400, 0);
        byte[] bytesR63 = MotorPType.stepMpToEndLoc(0x0106, -10030 / 8, 400, 0);
        byte[] bytesR73 = MotorPType.stepMpToEndLoc(0x0107, -31140 / 8, 400, 0);
        byte[] bytesR83 = MotorPType.stepMpToEndLoc(0x0108, 66400 / 8, 400, 0);
        sendAllBytesToSerial(bytesHA,bytesL03, bytesL13, bytesL23, bytesL33, bytesR53, bytesR63, bytesR73, bytesR83);
        searchStatusInSleepTime(800);

        //头部
        byte[] bytesHA2 = MotorPType.stepMpToEndLoc(0x010A, 3000/8, 800, 1);
        //左臂
        byte[] bytesL04 = MotorPType.stepMpToEndLoc(0x0100, -91690 / 8, 800, 0);
        byte[] bytesL14 = MotorPType.stepMpToEndLoc(0x0101, -10030 / 8, 800, 0);
        byte[] bytesL24 = MotorPType.stepMpToEndLoc(0x0102, 64730 / 8, 800, 0);
        byte[] bytesL34 = MotorPType.stepMpToEndLoc(0x0103, -59420 / 8, 800, 0);
        //右臂
        byte[] bytesR54 = MotorPType.stepMpToEndLoc(0x0105, 91690 / 8, 800, 0);
        byte[] bytesR64 = MotorPType.stepMpToEndLoc(0x0106, -10030 / 8, 800, 0);
        byte[] bytesR74 = MotorPType.stepMpToEndLoc(0x0107, -64730 / 8, 800, 0);
        byte[] bytesR84 = MotorPType.stepMpToEndLoc(0x0108, 59420 / 8, 800, 0);
        sendAllBytesToSerial(bytesHA2,bytesL04, bytesL14, bytesL24, bytesL34, bytesR54, bytesR64, bytesR74, bytesR84);
        searchStatusInSleepTime(1000);

        //左臂
        byte[] bytesL05 = MotorPType.stepMpToEndLoc(0x0100, -86840 / 8, 400, 0);
        byte[] bytesL15 = MotorPType.stepMpToEndLoc(0x0101, -39090/ 8, 400, 0);
        byte[] bytesL25 = MotorPType.stepMpToEndLoc(0x0102, 71490/ 8, 400, 0);
        byte[] bytesL35 = MotorPType.stepMpToEndLoc(0x0103, -54180/ 8, 400, 0);
        //右臂
        byte[] bytesR55 = MotorPType.stepMpToEndLoc(0x0105, 86840/ 8, 400, 0);
        byte[] bytesR65 = MotorPType.stepMpToEndLoc(0x0106, -39090/ 8, 400, 0);
        byte[] bytesR75 = MotorPType.stepMpToEndLoc(0x0107, -66860/ 8, 400, 0);
        byte[] bytesR85 = MotorPType.stepMpToEndLoc(0x0108, 61300/ 8, 400, 0);
        sendAllBytesToSerial(bytesL05, bytesL15, bytesL25, bytesL35, bytesR55, bytesR65, bytesR75, bytesR85);
        searchStatusInSleepTime(600);

        //左臂
        byte[] bytesL06 = MotorPType.stepMpToEndLoc(0x0100, -68530 / 8, 800, 0);
        byte[] bytesL16 = MotorPType.stepMpToEndLoc(0x0101, -29180/ 8, 800, 0);
        byte[] bytesL26 = MotorPType.stepMpToEndLoc(0x0102, 92800/ 8, 800, 0);
        byte[] bytesL36 = MotorPType.stepMpToEndLoc(0x0103, -9580/ 8, 800, 0);
        //右臂
        byte[] bytesR56 = MotorPType.stepMpToEndLoc(0x0105, 68530/ 8, 800, 0);
        byte[] bytesR66 = MotorPType.stepMpToEndLoc(0x0106, -29180/ 8, 800, 0);
        byte[] bytesR76 = MotorPType.stepMpToEndLoc(0x0107, -92800/ 8, 800, 0);
        byte[] bytesR86 = MotorPType.stepMpToEndLoc(0x0108, 9580/ 8, 800, 0);
        sendAllBytesToSerial(bytesL06, bytesL16, bytesL26, bytesL36, bytesR56, bytesR66, bytesR76, bytesR86);
        searchStatusInSleepTime(1100);
        stepToSpeachBaseAction();
        searchStatusInSleepTime(1000);
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 抄袭pepper三
     */
    public synchronized static void speakActionFromPepperFour(){
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -10560 / 8, 800, 0);
            byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -35400 / 8, 800, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 80640 / 8, 1500, 0);
            byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -2320 / 8, 800, 0);
            //右臂
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 10560 / 8, 800, 0);
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -29450 / 8, 800, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -80640 / 8, 1500, 0);
            byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 10670 / 8, 800, 0);
            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81);
            searchStatusInSleepTime(1700);

            //左臂
            //右臂
            byte[] bytesR52 = MotorPType.stepMpToEndLoc(0x0105, 68350 / 8, 800, 0);
            byte[] bytesR62 = MotorPType.stepMpToEndLoc(0x0106, -3140 / 8, 800, 0);
            byte[] bytesR72 = MotorPType.stepMpToEndLoc(0x0107, -85670 / 8, 800, 0);
            byte[] bytesR82 = MotorPType.stepMpToEndLoc(0x0108, 68090 / 8, 800, 0);
            sendAllBytesToSerial(bytesR52, bytesR62, bytesR72, bytesR82);
            searchStatusInSleepTime(1500);


            //头部
            byte[] bytesHA1 = MotorPType.stepMpToEndLoc(0x010A, -18000/8, 800, 1);
            byte[] bytes = MotorPType.setElectricity(0x0105, 150);
            //左臂
            //右臂
            byte[] bytesR53 = MotorPType.stepMpToEndLoc(0x0105, 23410 / 8, 800, 0);
            byte[] bytesR63 = MotorPType.stepMpToEndLoc(0x0106, -3140 / 8, 800, 0);
            byte[] bytesR73 = MotorPType.stepMpToEndLoc(0x0107, -85670 / 8, 800, 0);
            byte[] bytesR83 = MotorPType.stepMpToEndLoc(0x0108, 68090 / 8, 800, 0);
            sendAllBytesToSerial(bytesHA1,bytesR53, bytesR63, bytesR73, bytesR83,bytes);
            searchStatusInSleepTime(1200);


            //头部
            byte[] bytesHA2 = MotorPType.stepMpToEndLoc(0x010A, 18000/8, 1000, 1);
            //左臂
            //右臂
            byte[] bytesR54 = MotorPType.stepMpToEndLoc(0x0105, 151840 / 8, 1200, 0);
            byte[] bytesR64 = MotorPType.stepMpToEndLoc(0x0106, -11710 / 8, 800, 0);
            byte[] bytesR74 = MotorPType.stepMpToEndLoc(0x0107, -5870 / 8, 800, 0);
            byte[] bytesR84 = MotorPType.stepMpToEndLoc(0x0108, 10990 / 8, 800, 0);
            sendAllBytesToSerial(bytesHA2, bytesR54, bytesR64, bytesR74, bytesR84);
            searchStatusInSleepTime(2300);

            byte[] bytesHA3 = MotorPType.stepMpZero(0x010A, 4000);
            byte[] bytesHB3 = MotorPType.stepMpZero(0x010B, 4000);
            byte[] bytes2 = MotorPType.setElectricity(0x0105, 80);
            //左臂
            byte[] bytesL07 = MotorPType.stepMpToEndLoc(0x0100, -4000, 1000, 0);
            byte[] bytesL17 = MotorPType.stepMpZero(0x0101, 5000);
            byte[] bytesL27 = MotorPType.stepMpToEndLoc(0x0102, 8000, 800, 0);
            byte[] bytesL37 = MotorPType.stepMpToEndLoc(0x0103, -8000, 800, 0);
            //右臂
            byte[] bytesR57 = MotorPType.stepMpToEndLoc(0x0105, 4000, 1000, 0);
            byte[] bytesR67 = MotorPType.stepMpZero(0x0106, 5000);
            byte[] bytesR77 = MotorPType.stepMpToEndLoc(0x0107, -8000, 500, 0);
            byte[] bytesR87 = MotorPType.stepMpToEndLoc(0x0108, 8000, 800, 0);
            sendAllBytesToSerial(bytesHA3,bytesHB3,bytes2,bytesL07, bytesL17,bytesL27,bytesL37, bytesR57,bytesR67,bytesR77, bytesR87);
            searchStatusInSleepTime(1200);
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 抄袭pepper四
     */
    public synchronized static void speakActionFromPepperFive(){
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -10560 / 8, 800, 0);
            byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -35400 / 8, 800, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 80640 / 8, 1500, 0);
            byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -2320 / 8, 800, 0);
            //右臂
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 10560 / 8, 800, 0);
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -29450 / 8, 800, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -80640 / 8, 1500, 0);
            byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 10670 / 8, 800, 0);
            sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81);
            searchStatusInSleepTime(1700);

            //头部
            byte[] bytesHA1 = MotorPType.stepMpToEndLoc(0x010B, 18270/8, 800, 1);
            //右臂
            byte[] bytesR52 = MotorPType.stepMpToEndLoc(0x0105, 71040 / 8, 800, 0);
            byte[] bytesR62 = MotorPType.stepMpToEndLoc(0x0106, 13200 / 8, 800, 0);
            byte[] bytesR72 = MotorPType.stepMpToEndLoc(0x0107, -75520 / 8, 800, 0);
            byte[] bytesR82 = MotorPType.stepMpToEndLoc(0x0108, 34740 / 8, 800, 0);
            sendAllBytesToSerial(bytesHA1,bytesR52, bytesR62, bytesR72, bytesR82);
            searchStatusInSleepTime(1500);

            //头部
            byte[] bytesHA2 = MotorPType.stepMpToEndLoc(0x010B, -25220/8, 800, 1);
            //右臂
            byte[] bytesR53 = MotorPType.stepMpToEndLoc(0x0105, 70050 / 8, 800, 0);
            byte[] bytesR63 = MotorPType.stepMpToEndLoc(0x0106, -31450 / 8, 800, 0);
            byte[] bytesR73 = MotorPType.stepMpToEndLoc(0x0107, -71220 / 8, 800, 0);
            byte[] bytesR83 = MotorPType.stepMpToEndLoc(0x0108, 68260 / 8, 800, 0);
            sendAllBytesToSerial(bytesHA2,bytesR53, bytesR63, bytesR73, bytesR83);
            searchStatusInSleepTime(1500);

            //头部
            byte[] bytesHA3 = MotorPType.stepMpToEndLoc(0x010B, 18270/8, 800, 1);
            //右臂
            byte[] bytesR54 = MotorPType.stepMpToEndLoc(0x0105, 71040 / 8, 800, 0);
            byte[] bytesR64 = MotorPType.stepMpToEndLoc(0x0106, 13200 / 8, 800, 0);
            byte[] bytesR74 = MotorPType.stepMpToEndLoc(0x0107, -75520 / 8, 800, 0);
            byte[] bytesR84 = MotorPType.stepMpToEndLoc(0x0108, 34740 / 8, 800, 0);
            sendAllBytesToSerial(bytesHA3,bytesR54, bytesR64, bytesR74, bytesR84);
            searchStatusInSleepTime(1500);
            stepToSpeachBaseAction();
            searchStatusInSleepTime(1000);
            SerialSend.isActionFinish = true;
        }
    }


    /**
     * 演讲的第四个动作
     */
    public synchronized static void speachActionFour(int waitMillisecond) {
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
            //左臂
//        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1500, 0);
//        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 1500, 0);
//
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
////        byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -5000, 1000, 0);
//        byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -8000, 1600, 0);
//        byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 8000, 1600, 0);
//        sendAllBytesToSerial(bytesL01, bytesL21, bytesR5,/*bytesR6,*/bytesR7, bytesR8);
//        searchStatusInSleepTime(waitMillisecond);

            byte[] bytesHA = MotorPType.stepMpParamsRunNotEndDegree(0x010A, -3000, 2000, 1);

            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 7000, 1000, 0);
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -3000, 1000, 0);
            sendAllBytesToSerial(bytesHA,bytesR51, bytesR61);

            searchStatusInSleepTime(1000);

            byte[] bytesR52 = MotorPType.stepMpToEndLoc(0x0105, 2000, 1000, 0);
            byte[] bytesR62 = MotorPType.stepMpToEndLoc(0x0106, 0, 1000, 0);
            sendAllBytesToSerial(bytesR52, bytesR62);

            searchStatusInSleepTime(1200);

            byte[] bytesR53 = MotorPType.stepMpToEndLoc(0x0105, 7000, 1000, 0);
            byte[] bytesR63 = MotorPType.stepMpToEndLoc(0x0106, -3000, 1000, 0);
            sendAllBytesToSerial(bytesR53, bytesR63);

            searchStatusInSleepTime(1500);

            byte[] bytesR54 = MotorPType.stepMpToEndLoc(0x0105, 4000, 1000, 0);
            byte[] bytesR64 = MotorPType.stepMpToEndLoc(0x0106, 0, 1000, 0);
            byte[] bytesHA2 = MotorPType.stepMpParamsRunNotEndDegree(0x010A, 3000, 2000, 1);
            sendAllBytesToSerial(bytesHA2,bytesR54, bytesR64);


            searchStatusInSleepTime(1500);
//        searchStatusInSleepTime(5000);
//
//        allStepToZeroExceptHead(1500, 3000, 1500, 5000, 1500, 3000, 1500, 5000);
//
//        searchStatusInSleepTime(1700);
            SerialSend.isActionFinish = true;
        }
    }


    /**
     * 演讲的第五个动作
     */
    public synchronized static void speachActionFive(int waitMillisecond) {
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
//        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1600, 0);
//        byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -8000, 1600, 0);
//
//        //右臂
//        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1500, 0);
//        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 1500, 0);
//        sendAllBytesToSerial(bytesL01, bytesL21, bytesL31, bytesR51, bytesR71);
//        searchStatusInSleepTime(waitMillisecond);

            byte[] bytesHA = MotorPType.stepMpParamsRunNotEndDegree(0x010A, 3000, 2000, 1);

            byte[] bytesL02 = MotorPType.stepMpToEndLoc(0x0100, -7000, 1000, 0);
            byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -3000, 1000, 0);
            sendAllBytesToSerial(bytesHA,bytesL02, bytesL11);

            searchStatusInSleepTime(1000);

            byte[] bytesL03 = MotorPType.stepMpToEndLoc(0x0100, -2000, 1000, 0);
            byte[] bytesL12 = MotorPType.stepMpToEndLoc(0x0101, 0, 1000, 0);
            sendAllBytesToSerial(bytesL03, bytesL12);

            searchStatusInSleepTime(1000);

            byte[] bytesL04 = MotorPType.stepMpToEndLoc(0x0100, -7000, 1000, 0);
            byte[] bytesL13 = MotorPType.stepMpToEndLoc(0x0101, -3000, 1000, 0);
            sendAllBytesToSerial(bytesL04, bytesL13);

            searchStatusInSleepTime(1500);

            byte[] bytesL05 = MotorPType.stepMpToEndLoc(0x0100, -4000, 1000, 0);
            byte[] bytesL14 = MotorPType.stepMpToEndLoc(0x0101, 0, 1000, 0);
            byte[] bytesHA2 = MotorPType.stepMpParamsRunNotEndDegree(0x010A, -3000, 2000, 1);
            sendAllBytesToSerial(bytesHA2,bytesL05, bytesL14);

            searchStatusInSleepTime(1500);

//        searchStatusInSleepTime(5000);

//        allStepToZeroExceptHead(1500, 3000, 1500, 5000, 1500, 3000, 1500, 5000);
//
//        searchStatusInSleepTime(1700);
            SerialSend.isActionFinish = true;
        }
    }


    /**
     * 演讲的第六个动作
     */
    public synchronized static void speachActionSix(int waitMillisecond) {
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
//        //左臂
//        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -4000, 800, 0);
//        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1600, 0);
//        byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -8000, 1600, 0);
//
//        //右臂
//        byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 4000, 800, 0);
////        byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -5000, 1000, 0);
//        byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -8000, 1600, 0);
//        byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 8000, 1600, 0);
//        sendAllBytesToSerial(bytesL01, bytesL21, bytesL31, bytesR5, bytesR7, bytesR8);
//        searchStatusInSleepTime(waitMillisecond);

            byte[] bytesL02 = MotorPType.stepMpToEndLoc(0x0100, -7000, 1000, 0);
            byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -3000, 1000, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 7000, 1000, 0);
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -3000, 1000, 0);
            sendAllBytesToSerial(bytesL02, bytesL11, bytesR51, bytesR61);

            searchStatusInSleepTime(1000);

            byte[] bytesL03 = MotorPType.stepMpToEndLoc(0x0100, -2000, 1000, 0);
            byte[] bytesL12 = MotorPType.stepMpToEndLoc(0x0101, 0, 1000, 0);
            byte[] bytesR52 = MotorPType.stepMpToEndLoc(0x0105, 2000, 1000, 0);
            byte[] bytesR62 = MotorPType.stepMpToEndLoc(0x0106, 0, 1000, 0);
            sendAllBytesToSerial(bytesL03, bytesL12, bytesR52, bytesR62);

            searchStatusInSleepTime(1500);

            byte[] bytesL04 = MotorPType.stepMpToEndLoc(0x0100, -7000, 1000, 0);
            byte[] bytesL13 = MotorPType.stepMpToEndLoc(0x0101, -3000, 1000, 0);
            byte[] bytesR53 = MotorPType.stepMpToEndLoc(0x0105, 7000, 1000, 0);
            byte[] bytesR63 = MotorPType.stepMpToEndLoc(0x0106, -3000, 1000, 0);
            sendAllBytesToSerial(bytesL04, bytesL13, bytesR53, bytesR63);

            searchStatusInSleepTime(1500);

            byte[] bytesL05 = MotorPType.stepMpToEndLoc(0x0100, -4000, 1000, 0);
            byte[] bytesL14 = MotorPType.stepMpToEndLoc(0x0101, 0, 1000, 0);
            byte[] bytesR54 = MotorPType.stepMpToEndLoc(0x0105, 4000, 1000, 0);
            byte[] bytesR64 = MotorPType.stepMpToEndLoc(0x0106, 0, 1000, 0);
            sendAllBytesToSerial(bytesL05, bytesL14, bytesR54, bytesR64);

            searchStatusInSleepTime(1500);
//        searchStatusInSleepTime(5000);
//
//        allStepToZeroExceptHead(1500, 3000, 1500, 5000, 1500, 3000, 1500, 5000);
//
//        searchStatusInSleepTime(1700);
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 演讲的第七个动作
     */
    public synchronized static void speachActionSeven() {
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
            //头部
//        byte[] bytesHA = MotorPType.stepMpParamsRunNotEndDegree(0x010A, -2000, 1000, 1);
            byte[] bytesHA = MotorPType.stepMpToEndLoc(0x010A, -10000/8, 800, 1);

            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 500, 0);
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 500, 0);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 34000/8, 500, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, 6000/8, 500, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -63500/8, 500, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 39200/8, 500, 0);
            sendAllBytesToSerial(bytesHA,bytesL01,bytesL11, bytesL21,bytesL31, bytesR5, bytesR6, bytesR7, bytesR8);
            searchStatusInSleepTime(800);
            //右臂
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 35200/8, 800, 0);
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -32400/8, 800, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -63500/8, 800, 0);
            byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 62500/8, 800, 0);

            byte[] bytesHA2 = MotorPType.stepMpToEndLoc(0x010A, 20000/8, 800, 1);

            sendAllBytesToSerial(bytesHA2,bytesR51,bytesR61, bytesR71, bytesR81);
            searchStatusInSleepTime(1000);
            byte[] bytesHA3 = MotorPType.stepMpToEndLoc(0x010A, -10000/8, 800, 1);
            sendAllBytesToSerial(bytesHA3);
            stepToSpeachBaseAction();
            searchStatusInSleepTime(1000);
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 演讲的第八个动作
     */
    public synchronized static void speachActionEight() {
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
            byte[] bytesHA = MotorPType.stepMpToEndLoc(0x010A, -10000/8, 800, 1);

            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -34000/8, 500, 0);
            byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, 6000/8, 500, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 63500/8, 500, 0);
            byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -39200/8, 500, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 2500, 600, 0);
            byte[] bytesR6 = MotorPType.stepMpZero(0x0106, 5000);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -3000, 600, 0);
            byte[] bytesR8 = MotorPType.stepMpZero(0x0108, 5000);
            sendAllBytesToSerial(bytesHA,bytesL01,bytesL11, bytesL21,bytesL31, bytesR5, bytesR6, bytesR7, bytesR8);
            searchStatusInSleepTime(800);
            //左臂
            byte[] bytesL02 = MotorPType.stepMpToEndLoc(0x0100, -35200/8, 800, 0);
            byte[] bytesL12 = MotorPType.stepMpToEndLoc(0x0101, -32400/8, 800, 0);
            byte[] bytesL22 = MotorPType.stepMpToEndLoc(0x0102, 63500/8, 800, 0);
            byte[] bytesL32 = MotorPType.stepMpToEndLoc(0x0103, -62500/8, 800, 0);

            byte[] bytesHA2 = MotorPType.stepMpToEndLoc(0x010A, 20000/8, 800, 1);

            sendAllBytesToSerial(bytesHA2,bytesL02,bytesL12, bytesL22, bytesL32);
            searchStatusInSleepTime(1000);
            byte[] bytesHA3 = MotorPType.stepMpToEndLoc(0x010A, -10000/8, 800, 1);
            sendAllBytesToSerial(bytesHA3);
            stepToSpeachBaseAction();
            searchStatusInSleepTime(1000);
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 演讲的第九个动作
     */
    public synchronized static void speachActionNine() {
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -34000/8, 500, 0);
            byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, 6000/8, 500, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 63500/8, 500, 0);
            byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -39200/8, 500, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 34000/8, 500, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, 6000/8, 500, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -63500/8, 500, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 39200/8, 500, 0);
            sendAllBytesToSerial(bytesL01,bytesL11, bytesL21,bytesL31, bytesR5, bytesR6, bytesR7, bytesR8);
            searchStatusInSleepTime(800);
            //左臂
            byte[] bytesL02 = MotorPType.stepMpToEndLoc(0x0100, -35200/8, 800, 0);
            byte[] bytesL12 = MotorPType.stepMpToEndLoc(0x0101, -32400/8, 800, 0);
            byte[] bytesL22 = MotorPType.stepMpToEndLoc(0x0102, 63500/8, 800, 0);
            byte[] bytesL32 = MotorPType.stepMpToEndLoc(0x0103, -62500/8, 800, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 35200/8, 800, 0);
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -32400/8, 800, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -63500/8, 800, 0);
            byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 62500/8, 800, 0);
            sendAllBytesToSerial(bytesL02,bytesL12, bytesL22, bytesL32,bytesR51,bytesR61,bytesR71,bytesR81);
            searchStatusInSleepTime(1000);
            stepToSpeachBaseAction();
            searchStatusInSleepTime(1000);
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 演讲的第十个动作
     */
    public synchronized static void speachActionTen() {
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
            byte[] bytesHA = MotorPType.stepMpParamsRunNotEndDegree(0x010B, -3000, 2000, 1);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 69000/8, 800, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -54800/8, 800, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -90000/8, 800, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 67000/8, 800, 0);
            sendAllBytesToSerial(bytesHA,/*bytesL01,bytesL11, bytesL21,bytesL31,*/ bytesR5, bytesR6, bytesR7, bytesR8);
            searchStatusInSleepTime(1000);
            //左臂
//        byte[] bytesL02 = MotorPType.stepMpToEndLoc(0x0100, -35200/8, 800, 0);
//        byte[] bytesL12 = MotorPType.stepMpToEndLoc(0x0101, -32400/8, 800, 0);
//        byte[] bytesL22 = MotorPType.stepMpToEndLoc(0x0102, 63500/8, 800, 0);
//        byte[] bytesL32 = MotorPType.stepMpToEndLoc(0x0103, -62500/8, 800, 0);
            //右臂
//        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 64100/8, 800, 0);
//        byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -52800/8, 800, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, 0, 800, 0);
            byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 67700/8, 800, 0);
            sendAllBytesToSerial(/*bytesL02,bytesL12, bytesL22, bytesL32,bytesR51,bytesR61,*/bytesR71,bytesR81);
            searchStatusInSleepTime(1200);
            stepToSpeachBaseAction();
            searchStatusInSleepTime(1000);
            SerialSend.isActionFinish = true;
        }
    }

    /**
     * 演讲的第十二个动作
     */
    public synchronized static void speachActionTwelve() {
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
            byte[] bytesHA = MotorPType.stepMpParamsRunNotEndDegree(0x010B, 3000, 2000, 1);
            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -69000/8, 800, 0);
            byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -54800/8, 800, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 90000/8, 800, 0);
            byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, -67000/8, 800, 0);
            //右臂
//        byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 69000/8, 800, 0);
//        byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -54800/8, 800, 0);
//        byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -90000/8, 800, 0);
//        byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 67000/8, 800, 0);
            sendAllBytesToSerial(bytesHA,bytesL01,bytesL11, bytesL21,bytesL31);
            searchStatusInSleepTime(1000);
            //左臂
//        byte[] bytesL02 = MotorPType.stepMpToEndLoc(0x0100, -35200/8, 800, 0);
//        byte[] bytesL12 = MotorPType.stepMpToEndLoc(0x0101, -32400/8, 800, 0);
            byte[] bytesL22 = MotorPType.stepMpToEndLoc(0x0102, 0, 800, 0);
            byte[] bytesL32 = MotorPType.stepMpToEndLoc(0x0103, -67700/8, 800, 0);

            //右臂
//        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 64100/8, 800, 0);
//        byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, -52800/8, 800, 0);
//        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, 0, 800, 0);
//        byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 67700/8, 800, 0);
            sendAllBytesToSerial(/*bytesL02,bytesL12, bytesL22, bytesL32,bytesR51,bytesR61,*/bytesL22,bytesL32);
            searchStatusInSleepTime(1200);
            stepToSpeachBaseAction();
            searchStatusInSleepTime(1000);
            SerialSend.isActionFinish = true;
        }
    }


    /**
     * 演讲的第十一个动作
     */
    public synchronized static void speachActionEleven() {
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
            //左臂
            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -65000/8, 800, 0);
            byte[] bytesL11 = MotorPType.stepMpToEndLoc(0x0101, -2500/8, 800, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 72200/8, 800, 0);
            byte[] bytesL31 = MotorPType.stepMpToEndLoc(0x0103, 5000/8, 800, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 93700/8, 800, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, 4600/8, 800, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -57000/8, 800, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 9600/8, 800, 0);
            sendAllBytesToSerial(bytesL01,bytesL11, bytesL21,bytesL31, bytesR5, bytesR6, bytesR7, bytesR8);
            searchStatusInSleepTime(1000);
            //左臂
            byte[] bytesL02 = MotorPType.stepMpToEndLoc(0x0100, -72150/8, 500, 0);
            byte[] bytesL12 = MotorPType.stepMpToEndLoc(0x0101, -5380/8, 500, 0);
            byte[] bytesL22 = MotorPType.stepMpToEndLoc(0x0102, 84760/8, 500, 0);
            byte[] bytesL32 = MotorPType.stepMpToEndLoc(0x0103, -1000/8, 500, 0);
            //右臂
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 76800/8, 500, 0);
            byte[] bytesR61 = MotorPType.stepMpToEndLoc(0x0106, 3700/8, 500, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -55000/8, 500, 0);
            byte[] bytesR81 = MotorPType.stepMpToEndLoc(0x0108, 4400/8, 500, 0);
            sendAllBytesToSerial(bytesL02,bytesL12, bytesL22, bytesL32,bytesR51,bytesR61,bytesR71,bytesR81);
            searchStatusInSleepTime(700);

            //左臂
            byte[] bytesL03 = MotorPType.stepMpToEndLoc(0x0100, -72100/8, 500, 0);
            byte[] bytesL13 = MotorPType.stepMpToEndLoc(0x0101, -7500/8, 500, 0);
            byte[] bytesL23 = MotorPType.stepMpToEndLoc(0x0102, 68260/8, 500, 0);
            byte[] bytesL33 = MotorPType.stepMpToEndLoc(0x0103, 0, 500, 0);
            //右臂
            byte[] bytesR52 = MotorPType.stepMpToEndLoc(0x0105, 71780/8, 500, 0);
            byte[] bytesR62 = MotorPType.stepMpToEndLoc(0x0106, 2940/8, 500, 0);
            byte[] bytesR72 = MotorPType.stepMpToEndLoc(0x0107, -73650, 500, 0);
            byte[] bytesR82 = MotorPType.stepMpToEndLoc(0x0108, 4400/8, 500, 0);
            sendAllBytesToSerial(bytesL03,bytesL13, bytesL23, bytesL33,bytesR52,bytesR62,bytesR72,bytesR82);
            searchStatusInSleepTime(700);

            //左臂
//        byte[] bytesL04 = MotorPType.stepMpToEndLoc(0x0100, -72190/8, 800, 0);
            byte[] bytesL04 = MotorPType.stepMpToEndLoc(0x0100, -65000/8, 500, 0);
            byte[] bytesL14 = MotorPType.stepMpToEndLoc(0x0101, -6300/8, 500, 0);
            byte[] bytesL24 = MotorPType.stepMpToEndLoc(0x0102, 68050/8, 500, 0);
            byte[] bytesL34 = MotorPType.stepMpToEndLoc(0x0103, 2650/8, 500, 0);
            //右臂
//        byte[] bytesR54 = MotorPType.stepMpToEndLoc(0x0105, 86420/8, 800, 0);
            byte[] bytesR54 = MotorPType.stepMpToEndLoc(0x0105, 93700/8, 500, 0);
            byte[] bytesR64 = MotorPType.stepMpToEndLoc(0x0106, 2500/8, 500, 0);
            byte[] bytesR74 = MotorPType.stepMpToEndLoc(0x0107, -80530/8, 500, 0);
            byte[] bytesR84 = MotorPType.stepMpToEndLoc(0x0108, 7120/8, 500, 0);
            sendAllBytesToSerial(bytesL04,bytesL14, bytesL24, bytesL34,bytesR54,bytesR64,bytesR74,bytesR84);
            searchStatusInSleepTime(700);
            SerialSend.isActionFinish = true;
        }
    }


    /**
     * 演讲时候的抱拳
     */
    public synchronized static void speakActionHips() {
        synchronized (SerialSend.lock){
            SerialSend.isActionFinish = false;
            //头部
//        byte[] bytesHA = MotorPType.stepMpParamsRunNotEndDegree(0x010A, -2000, 1000, 1);
            byte[] bytesHA = MotorPType.stepMpToEndLoc(0x010A, -10000/8, 800, 1);
            //左臂
            byte[] bytesl = MotorPType.stepMpToEndLoc(0x0100, -9000, 1800, 0);
            byte[] bytesl1 = MotorPType.stepMpToEndLoc(0x0101, -4000, 800, 0);
            byte[] bytes2l = MotorPType.stepMpToEndLoc(0x0102, 7500, 1500, 0);
            byte[] bytes3l = MotorPType.stepMpZero(0x0103, 5000);
            //右臂
            byte[] bytes = MotorPType.stepMpToEndLoc(0x0105, 9000, 1800, 0);
            byte[] bytes3 = MotorPType.stepMpToEndLoc(0x0106, -4000, 800, 0);
            byte[] bytes2 = MotorPType.stepMpToEndLoc(0x0107, -6000, 1500, 0);
            byte[] bytes8 = MotorPType.stepMpZero(0x0108, 5000);
            sendAllBytesToSerial(bytesHA,bytesl,bytesl1,bytes2l,bytes3l/*,bytes22*/,bytes,bytes3,bytes2,bytes8);
            searchStatusInSleepTime(1000);

            byte[] bytesHA2 = MotorPType.stepMpToEndLoc(0x010A, 20000/8, 800, 1);
            sendAllBytesToSerial(bytesHA2);
            searchStatusInSleepTime(2000);
            byte[] bytesHA3 = MotorPType.stepMpToEndLoc(0x010A, -10000/8, 800, 1);
            sendAllBytesToSerial(bytesHA3);
            stepToSpeachBaseAction();
            searchStatusInSleepTime(1900);
            SerialSend.isActionFinish = true;
        }
    }


    /**
     * 演讲的左上右下
     */
    public synchronized static void speakActionLeftUpRightDown() {
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
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
            sendAllBytesToSerial(bytesL0,bytesL1,bytesL2,bytesL3,bytesHR1,bytesR5,bytesR6,bytesR7);
            searchStatusInSleepTime(2300);
            stepToSpeachBaseAction();
            searchStatusInSleepTime(2300);
            SerialSend.isActionFinish = true;
        }
    }


    /**
     * 演讲时候的右上左下
     */
    public synchronized static void speakActionRightUpLeftDown() {
        synchronized (SerialSend.lock){

            SerialSend.isActionFinish = false;
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
            sendAllBytesToSerial(bytesL0,bytesL1,bytesL2,bytesHR1,bytesR5,bytesR6,bytesR7,bytesR8);
            searchStatusInSleepTime(2300);
            stepToSpeachBaseAction();
            searchStatusInSleepTime(2300);
            SerialSend.isActionFinish = true;
        }
    }


    /**
     * 演讲的左上
     */
    public synchronized static void speakActionLeftUp(){
        synchronized (SerialSend.lock){
            SerialSend.isActionFinish = false;
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -11000, 2200, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -8000, 1600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 9000, 1800, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            //头部
            byte[] bytesHR1 = MotorPType.stepMpToEndLoc(0x010B, 3000, 600, 0);
            sendAllBytesToSerial(bytesL0,bytesL1,bytesL2,bytesL3,bytesHR1);
            searchStatusInSleepTime(2300);
            stepToSpeachBaseAction();
            searchStatusInSleepTime(2300);
            SerialSend.isActionFinish = true;
        }

    }


    /**
     * 演讲动作右上
     */
    public synchronized static void speakActionRightUp() {
        synchronized (SerialSend.lock){
            SerialSend.isActionFinish = false;
            //头部
            byte[] bytesHR1 = MotorPType.stepMpToEndLoc(0x010B, -3000, 600, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 11000, 2200, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -8000, 1600, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -9000, 1800, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 5000, 1000, 0);
            sendAllBytesToSerial(bytesHR1,bytesR5,bytesR6,bytesR7,bytesR8);
            searchStatusInSleepTime(2300);
            stepToSpeachBaseAction();
            searchStatusInSleepTime(2300);
            SerialSend.isActionFinish = true;
        }
    }



    /**
     * 演讲动作
     */
    public synchronized static void speakActionTwoJuQi() {
        synchronized (SerialSend.lock){
            SerialSend.isActionFinish = false;
            //左臂
            byte[] bytes0 = MotorPType.stepMpToEndLoc(0x0100, -2000, 1000, 0);
            byte[] bytes1 = MotorPType.stepMpToEndLoc(0x0101, -2000, 1000, 0);
            byte[] bytes2 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1600, 0);
            byte[] bytes3 = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            //右臂
            byte[] bytes5 = MotorPType.stepMpToEndLoc(0x0105, 2000, 1000, 0);
            byte[] bytes6 = MotorPType.stepMpToEndLoc(0x0106, -2000, 1000, 0);
            byte[] bytes7 = MotorPType.stepMpToEndLoc(0x0107, -8000, 1000, 0);
            byte[] bytes8 = MotorPType.stepMpToEndLoc(0x0108, 5000, 1000, 0);
            sendAllBytesToSerial(bytes0,bytes1,bytes2,bytes3,bytes5,bytes6,bytes7,bytes8);
            searchStatusInSleepTime(1200);
            stepToSpeachBaseAction();
            searchStatusInSleepTime(1800);
            SerialSend.isActionFinish = true;
        }
    }


    /**
     * 招手
     */
    public synchronized static void speakActionZhaoshouNow() {
        synchronized (SerialSend.lock) {
            SerialSend.isActionFinish = false;
            //左臂
//        byte[] bytesl = MotorPType.stepMpParamsRun(0x0100, -5000, 7000, 0);
//        byte[] bytesl1 = MotorPType.stepMpParamsRun(0x0101, -5000, 2000, 0);
//        byte[] bytes2l = MotorPType.stepMpParamsRun(0x0103, -5000, 5000, 0);
            byte[] bytesl = MotorPType.stepMpToEndLoc(0x0100, -7000, 1000, 0);
            byte[] bytesl1 = MotorPType.stepMpToEndLoc(0x0101, -2000, 400, 0);
            byte[] bytes2l = MotorPType.stepMpToEndLoc(0x0103, -5000, 1000, 0);
            sendAllBytesToSerial(bytesl,bytesl1,bytes2l);
            searchStatusInSleepTime(1200);
            byte[] bytes31 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1000, 0);
            sendAllBytesToSerial(bytes31);
            searchStatusInSleepTime(1300);
            byte[] bytes32 = MotorPType.stepMpToEndLoc(0x0102, 0, 1000, 0);
            sendAllBytesToSerial(bytes32);
            searchStatusInSleepTime(1300);
            byte[] bytes33 = MotorPType.stepMpToEndLoc(0x0102, 8000, 1000, 1);
            sendAllBytesToSerial(bytes33);
            searchStatusInSleepTime(1300);
            byte[] bytes34 = MotorPType.stepMpZero(0x0102, 8000);
            sendAllBytesToSerial(bytes34);
            searchStatusInSleepTime(1100);
            //添加的
            allStepToZero();
            searchStatusInSleepTime(1900);
            SerialSend.isActionFinish = true;
        }

    }


    /**
     * 叉腰
     */
    public synchronized static void speakActionChayao() {
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
            sendAllBytesToSerial(bytesL0,bytesL1,bytesL2/*,bytesL4*/,bytesR5,bytesR6,bytesR7/*,bytesR9*/);
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


    /**
     * 奥特曼姿势，右边起
     */
    public synchronized static void speakActionAtemanRightUp() {
        synchronized (SerialSend.lock) {
            SerialSend.isActionFinish = false;
            //左臂
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -7000, 1400, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 9000, 1800, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 8000, 1600, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -3000, 600, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -10000, 2000, 0);
            byte[] bytesR8 = MotorPType.stepMpToEndLoc(0x0108, 7000, 1400, 0);
            sendAllBytesToSerial(bytesL0,bytesL1,bytesL2,bytesR5,bytesR6,bytesR7,bytesR8);
            searchStatusInSleepTime(2500);
            //左臂
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 3000);
            //右臂
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 5000);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 6000);

            //添加
            byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 1500, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 1500, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 1500, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 1500, 0);

            sendAllBytesToSerial(bytesL01,bytesL11,bytesL21,bytesR51,bytesR61,bytesR71,bytesR81,bytesHA1,bytesHB1,bytesL31);
            searchStatusInSleepTime(2700);
            SerialSend.isActionFinish = true;
        }

    }


    /**
     * 奥特曼姿势，左边起
     */
    public synchronized static void speakActionAtemanLeftUp() {
        synchronized (SerialSend.lock) {
            SerialSend.isActionFinish = false;
            byte[] bytesL0 = MotorPType.stepMpToEndLoc(0x0100, -8000, 1600, 0);
            byte[] bytesL1 = MotorPType.stepMpToEndLoc(0x0101, -3000, 600, 0);
            byte[] bytesL2 = MotorPType.stepMpToEndLoc(0x0102, 10000, 2000, 0);
            byte[] bytesL3 = MotorPType.stepMpToEndLoc(0x0103, -7000, 1400, 0);
            //右臂
            byte[] bytesR5 = MotorPType.stepMpToEndLoc(0x0105, 7000, 1400, 0);
            byte[] bytesR6 = MotorPType.stepMpToEndLoc(0x0106, -3000, 600, 0);
            byte[] bytesR7 = MotorPType.stepMpToEndLoc(0x0107, -9000, 1800, 0);
            sendAllBytesToSerial(bytesL0,bytesL1,bytesL2,bytesL3,bytesR5,bytesR6,bytesR7);
            searchStatusInSleepTime(2500);
            //左臂
            byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
            byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 6000);
            //右臂
            byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 3000);
            //添加的
            byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
            byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);
            byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);

            byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
            byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
            byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
            byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

            sendAllBytesToSerial(bytesL01,bytesL11,bytesL21,bytesL31,bytesR51,bytesR61,bytesR71,bytesHA1,bytesHB1,bytesR81);
            searchStatusInSleepTime(2700);
            SerialSend.isActionFinish = true;
        }
        //左臂

    }


    /**
     * 所有动作归零运动
     */
    public synchronized static void allStepToZero() {
        byte[] bytesHA1 = MotorPType.stepMpZero(0x010A, 4000);
        byte[] bytesHB1 = MotorPType.stepMpZero(0x010B, 4000);
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 3000);
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 3000);
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
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, spend1);
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, spend3);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, spend6);
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, spend8);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, time0, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, time2, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, time5, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, time7, 0);

        sendAllBytesToSerial(bytesHA1, bytesHB1, bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81);
    }

    public synchronized static void allStepToZeroExceptHead() {
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, 5000);
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, 5000);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, 5000);
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, 5000);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, 2000, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, 2000, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, 2000, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, 2000, 0);

        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81);
    }

    public synchronized static void allStepToZeroExceptHead(int time0, int spend1, int time2, int spend3, int time5, int spend6, int time7, int spend8) {
        byte[] bytesL11 = MotorPType.stepMpZero(0x0101, spend1);
        byte[] bytesL31 = MotorPType.stepMpZero(0x0103, spend3);
        byte[] bytesR61 = MotorPType.stepMpZero(0x0106, spend6);
        byte[] bytesR81 = MotorPType.stepMpZero(0x0108, spend8);

        byte[] bytesL01 = MotorPType.stepMpToEndLoc(0x0100, -2500, time0, 0);
        byte[] bytesL21 = MotorPType.stepMpToEndLoc(0x0102, 3000, time2, 0);
        byte[] bytesR51 = MotorPType.stepMpToEndLoc(0x0105, 2500, time5, 0);
        byte[] bytesR71 = MotorPType.stepMpToEndLoc(0x0107, -3000, time7, 0);

        sendAllBytesToSerial(bytesL01, bytesL11, bytesL21, bytesL31, bytesR51, bytesR61, bytesR71, bytesR81);
    }

}
