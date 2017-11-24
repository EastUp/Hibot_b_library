package com.xg.east.hibot_b_library.action;

import com.xg.east.hibot_b_library.SerialSend;
import com.xg.east.hibot_b_library.service.usbSendType.MotorPType;

/**
 * 项目名称：Test
 * 创建人：East
 * 创建时间：2017/4/13 12:00
 * 邮箱：EastRiseWM@163.com
 */

public class HeadControll {
    /**
     * 抬头
     */
    public static void riseHead(){
        byte[] bytes1 = MotorPType.stepMpParamsRunNotEndDegree(0x010A, 600, 600, 1);
        SerialSend.sendAllBytesToSerial(bytes1);
    }

    /**
     * 低头
     */
    public static void lowerHead(){
        byte[] bytes1 = MotorPType.stepMpParamsRunNotEndDegree(0x010A, -600, 600, 1);
        SerialSend.sendAllBytesToSerial(bytes1);
    }

    /**
     * 左转头
     */
    public static void headTurnLeft(){
        byte[] bytes1 = MotorPType.stepMpParamsRunNotEndDegree(0x010B, 600, 600, 1);
        SerialSend.sendAllBytesToSerial(bytes1);
    }

    /**
     * 右转头
     */
    public static void headTurnRight(){
        byte[] bytes1 = MotorPType.stepMpParamsRunNotEndDegree(0x010B, -600, 600, 1);
        SerialSend.sendAllBytesToSerial(bytes1);
    }

}
