package com.xg.east.hibot_b_library.service.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * 项目名称：Test
 * 创建人：East
 * 创建时间：2017/8/4 14:44
 * 邮箱：EastRiseWM@163.com
 */

public class ServiceUtils {
    /**
     * 校验某个服务是否还活着
     * serviceName :传进来的服务的名称
     */
    public static boolean isServiceRunning(Context context, String serviceName){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> infos = am.getRunningServices(100);
        for(ActivityManager.RunningServiceInfo info : infos){
            String name = info.service.getClassName();
            if(name.equals(serviceName)){
                return true;
            }
        }
        return false;
    }

    public static void killRunningService(Context context ,String serviceName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> infos = am.getRunningServices(100);
        for(ActivityManager.RunningServiceInfo info : infos){
            String name = info.service.getClassName();
            if(name.equals(serviceName)){
                String pkgName = info.service.getPackageName(); // 包名
                am.killBackgroundProcesses(pkgName);
                //mActivityManager.killBackgroundProcesses(packageName);
                break;
            }
        }
    }

}
