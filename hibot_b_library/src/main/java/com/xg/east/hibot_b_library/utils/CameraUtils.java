package com.xg.east.hibot_b_library.utils;

import android.hardware.Camera;

/**
 * Created by EastRiseWM on 2016/10/13.
 */

public class CameraUtils {
    /* 测试当前摄像头能否被使用
    * @return
    */
    public static boolean isCameraCanUse() {
        boolean canUse = true;
        Camera mCamera = null;
        try {
            // TODO camera驱动挂掉,处理??
            mCamera = Camera.open();
        } catch (Exception e) {
            canUse = false;
        }
        if (canUse) {
            mCamera.release();
            mCamera = null;
        }

        return canUse;
    }
}
