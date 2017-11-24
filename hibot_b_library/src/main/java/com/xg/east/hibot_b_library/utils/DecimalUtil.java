package com.xg.east.hibot_b_library.utils;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 保留小数
 */
public class DecimalUtil {
    public static String getDecimal(double value){//保留1位小数
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(value);
    }

    /**
     * @param list  把所有命令合并成一包发
     * @return
     */
    public static byte[] getTogher(List<byte[]> list) {
        int size=0;
        for(int i=0;i<list.size();i++){
            size+=list.get(i).length;
        }
        byte[] sorce=new byte[size];
        for(int i=0;i<list.size();i++){
            int index=0;
            for(int k=0;k<i;k++){
                index+=list.get(k).length;
            }
            byte[] bytes = list.get(i);
            for (int j = index; j < bytes.length + index; j++) {
                sorce[j] = bytes[j - index];
            }
        }
        return sorce;
    }

}
