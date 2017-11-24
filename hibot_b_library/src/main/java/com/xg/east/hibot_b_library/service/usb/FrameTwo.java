package com.xg.east.hibot_b_library.service.usb;

import com.xg.east.hibot_b_library.service.utils.FormatChange;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


/**
 * 数据第二层
 */
public class FrameTwo {
    private final static int FRAME_HEAD_LENGTH=10;
    public static int i=0;

    /**
     * @param targetId 帧目标设备ID
     * @param sendId 发送帧设备ID
     * @param type   帧类型
     * @param serial 帧编号
     * @param data  帧数据
     * @return
     */
    public static byte[] two(int targetId,int sendId,int type,int serial,byte[] data,int priority){
        i++;
        int size = FrameTwo.FRAME_HEAD_LENGTH +data.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(size);

        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeShort(change(targetId));
            dataOutputStream.writeShort(change(sendId));
            dataOutputStream.writeByte(priority);
            dataOutputStream.writeByte(i);
            dataOutputStream.writeShort(change(type));
            dataOutputStream.writeShort(change(serial));
            dataOutputStream.write(data,0,data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(dataOutputStream!=null){
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static int change(int a){
        int parseInt = Integer.parseInt(FormatChange.intToHexString(a, 2), 16);
        return ((parseInt>>8) | (parseInt<<8));
    }

}
