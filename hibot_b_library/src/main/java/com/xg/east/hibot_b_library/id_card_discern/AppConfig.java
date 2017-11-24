package com.xg.east.hibot_b_library.id_card_discern;

import android.os.Environment;

/**
 * 项目名称：Test
 * 创建人：East
 * 创建时间：2017/8/8 18:09
 * 邮箱：EastRiseWM@163.com
 */

public class AppConfig {
    /**
     * 数据文件的根路径 目前定在外部存储卡中
     */
    public static final String BasePath= Environment.getExternalStorageDirectory().getAbsolutePath();
    /**
     * 数据库文件夹名称
     */
    private static final String DBDirectoryName = "wltlib";
    /**
     * 临时证据文件夹
     */
    public static final String DBDirectoryNameL = "clog";

    /**
     * 总文件夹的路径
     */
    public static final String RootFile = BasePath+"/"+DBDirectoryName+"/";
    /**
     * 总文件夹的路径
     */
    public static final String RootFileL = BasePath+"/"+DBDirectoryNameL+"/";

    public static final String WITLIB = RootFile+"base.dat";

    public static final String LIC = RootFile+ "license.lic";
}
