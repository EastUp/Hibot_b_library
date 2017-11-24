package com.xg.east.hibot_b_library.action;

import com.xg.east.hibot_b_library.service.usbSendType.ClassicType;

import static com.xg.east.hibot_b_library.SerialSend.sendAllBytesToSerial;

/**
 * 项目名称：Test
 * 创建人：East
 * 创建时间：2017/4/13 11:56
 * 邮箱：EastRiseWM@163.com
 */

public class ClassicControll {

    /**
     * 前进
     */
    public static void advance(){
        byte[] bytes = ClassicType.runWithSpeed(40, 40, 8);
        sendAllBytesToSerial(bytes);
    }

    /**
     * 后退
     */
    public static void backUp(){
        byte[] bytes4 = ClassicType.runWithSpeed(-40, -40, 8);
        sendAllBytesToSerial(bytes4);
    }

    /**
     * 左转
     */
    public static void turnLeft(){
        byte[] bytes2 = ClassicType.runWithSpeed(-15, 15, 6);
        sendAllBytesToSerial(bytes2);
    }

    /**
     * 右转
     */
    public static void turnRight(){
        byte[] bytes3 = ClassicType.runWithSpeed(15, -15, 6);
        sendAllBytesToSerial(bytes3);
    }

    public static void stop(){
        byte[] bytes4 = ClassicType.runWithSpeed(0, 0, 6);
        sendAllBytesToSerial(bytes4);
    }


}
