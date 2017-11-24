package com.xg.east.hibot_b_library.action;

import com.xg.east.hibot_b_library.service.usb.FrameOne;
import com.xg.east.hibot_b_library.service.usb.FrameThree;
import com.xg.east.hibot_b_library.service.usb.FrameTwo;

/**
 * 项目名称：Test
 * 创建人：East
 * 创建时间：2017/4/12 21:24
 * 邮箱：EastRiseWM@163.com
 */

public class LightControll {
    /**
     * @param controlMode   0：当控制模式为 0 的时候眼睛灯板的 0-8 号灯直接显示后续各个灯的 rgb 值
                            1：连续色彩变换模式下眼睛灯光自动变色，当收到其他控制模式的时候退出该模式。
                            2：控制眼睛眨眼一次，只有在直接控制灯 rgb 模式下才会执行该指令。
                            3：等待模式下眼睛自动控制灯光旋转，当收到其他控制模式的时候退出该模式。
     * @param r1       1号灯的rgb色中的r
     * @param g1       1号灯的rgb色中的r
     * @param b1       1号灯的rgb色中的b
     * @param r2       ........
     * @param g2
     * @param b2
     * @param r3
     * @param g3
     * @param b3
     * @param r4
     * @param g4
     * @param b4
     * @param r5
     * @param g5
     * @param b5
     * @param r6
     * @param g6
     * @param b6
     * @param r7
     * @param g7
     * @param b7
     * @param r8
     * @param g8
     * @param b8
     * @return
     */
    //眼睛灯光的效果控制
    public static byte[] eyeControll(int controlMode,int r1,int g1,int b1,int r2,int g2,int b2,int r3,int g3,int b3,int r4,int g4,int b4,
                                     int r5,int g5,int b5,int r6,int g6,int b6,int r7,int g7,int b7,int r8,int g8,int b8){
        byte[] send6 = FrameThree.send6(controlMode, r1, g1, b1, r2, g2, b2, r3, g3, b3, r4, g4, b4, r5, g5, b5, r6, g6, b6, r7, g7, b7, r8, g8, b8);
        byte[] two = FrameTwo.two(0x0400, 0x0000, 0x0004, 0x0000, send6, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x05);
        return output;
    }

    /**
     * @param controlMode   0：当控制模式为 0 的时候眼睛灯板的 0-8 号灯直接显示后续各个灯的 rgb 值
     *                       1：连续色彩变换模式下眼睛灯光自动变色，当收到其他控制模式的时候退出该模式。
     *                       2：控制眼睛眨眼一次，只有在直接控制灯 rgb 模式下才会执行该指令。
     *                       3：等待模式下眼睛自动控制灯光旋转，当收到其他控制模式的时候退出该模式。
     * @param r1             rgb色中的r
     * @param g1            rgb色中的g
     * @param b1            rgb色中的b
     * @return
     */
    public static byte[] eyeCTSingleColor(int controlMode,int r1,int g1,int b1){
        byte[] send6 = FrameThree.send6(controlMode, r1, g1, b1, r1, g1, b1, r1, g1, b1, r1, g1, b1, r1, g1, b1, r1, g1, b1, r1, g1, b1, r1, g1, b1);
        byte[] two = FrameTwo.two(0x0400, 0x0000, 0x0004, 0x0000, send6, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x05);
        return output;
    }

    /**
     * @param controlMode   0：当控制模式为 0 的时候耳朵灯板的灯直接显示耳朵灯的 rgb 值。
                            1：连续色彩变换模式下灯光自动变色，当收到其他控制模式的时候退出该模式。
                            2：基于灯 rgb 值作为最大值运行呼吸灯效果，当收到其他控制模式的时候退出该模式。
     * @param r1    rgb色中的r
     * @param g1    rgb色中的g
     * @param b1    rgb色中的b
     * @return
     */
    //耳朵灯光控制效果
    public static byte[] earControll(int controlMode,int r1,int g1,int b1){
        byte[] send7 = FrameThree.send7(controlMode, r1, g1, b1);
        byte[] two = FrameTwo.two(0x0400, 0x0000, 0x0004, 0x0001, send7, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x05);
        return output;
    }

    /**
     * @param controlMode   0：当控制模式为 0 的时候头顶灯板的灯直接显示耳头顶灯的 rgb 值。
                            1：连续色彩变换模式下灯光自动变色，当收到其他控制模式的时候退出该模式。
                            2：基于灯 rgb 值作为最大值运行呼吸灯效果，当收到其他控制模式的时候退出该模式。
     * @param r1    rgb色中的r
     * @param g1    rgb色中的g
     * @param b1    rgb色中的b
     * @return
     */
    //头顶灯光效果控制
    public static byte[] topHeadControll(int controlMode,int r1,int g1,int b1){
        byte[] send8 = FrameThree.send8(controlMode, r1, g1, b1);
        byte[] two = FrameTwo.two(0x0400, 0x0000, 0x0004, 0x0002, send8, 1);
        byte[] output = FrameOne.output(two, 0x00, 0x05);
        return output;
    }
}
