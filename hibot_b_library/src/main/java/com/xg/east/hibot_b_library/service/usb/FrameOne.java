package com.xg.east.hibot_b_library.service.usb;

import com.xg.east.hibot_b_library.service.utils.FormatChange;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


/**
 * 数据第一层
 */
public class FrameOne {

    private static final int FRAME_BEGIN = 0xB78C;
    private static final int FRAME_END = 0xACBD;
    private static final int FRAME_HEAD_LENGTH = 4;
    private static final int FRAME_TAIL_LENGTH = 4;

    /**
     * @param data 帧数据
     * @param a 设备ID
     * @param b
     * @return
     */
    //注意a,和b是低位在前高位在后的结果
    public static byte[] output(byte[] data,int a,int b)
    {
        int size = FrameOne.FRAME_HEAD_LENGTH + FrameOne.FRAME_TAIL_LENGTH + data.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(size);

        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        try
        {
            dataOutputStream.writeShort(FrameOne.FRAME_BEGIN);
            dataOutputStream.writeShort(change(data.length));
            dataOutputStream.write(data, 0, data.length);
            dataOutputStream.writeShort(crc16(data,data.length));
            dataOutputStream.writeShort(FrameOne.FRAME_END);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
//        byte[] toByteArray = byteArrayOutputStream.toByteArray();
//        int length = toByteArray.length;
//        int sum = length / 62 + (length % 62 > 0 ? 1 : 0);
//        byte[] bytes = new byte[length + (length / 62) * 2 + (length % 62 > 0 ? 2 : 0)];
//        for (int j = 1; j <= sum; j++) {
//            for (int i = 0+(j-1)*64; i < bytes.length; i++) {
//                if(i<j*64){
//                    if(i==0+(j-1)*64){
//                        bytes[i]=(byte) a;
//                    }else if(i==1+(j-1)*64){
//                        bytes[i]=(byte) b;
//                    }else{
//                        bytes[i]=toByteArray[i-2*j];
//                    }
//                }else{
//                    break;
//                }
//            }
//        }
//        return bytes;
        if(dataOutputStream!=null){
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    ////CRC校验算法
    public static int crc16(byte[] buffer, int count)
    {
        int i, tmp = 0x0000FFFF;
        int j, flag;
        for (i = 0; i < count; i++)
        {
            tmp ^= (int)buffer[i] & 0x000000ff;
            for (j = 0; j < 8; j++)
            {
                flag = tmp & 0x00000001;
                tmp >>= 1;
                if (1 == flag)
                    tmp ^= 0x0000A001;
            }
        }

        return tmp;
    }

    public static int change(int a){
        int parseInt = Integer.parseInt(FormatChange.intToHexString(a, 2), 16);
        return ((parseInt>>8) | (parseInt<<8));
    }


}
