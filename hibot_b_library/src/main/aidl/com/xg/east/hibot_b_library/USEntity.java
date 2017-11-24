package com.xg.east.hibot_b_library;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：SerialAndUsbTest
 * 创建人：East
 * 创建时间：2017/3/28 16:04
 * 邮箱：EastRiseWM@163.com
 */

public class USEntity implements Parcelable {
    private byte[] mBytes=new byte[0];
    private int length=0;


    public static final Creator<USEntity> CREATOR = new Creator<USEntity>() {
        @Override
        public USEntity createFromParcel(Parcel in) {
            return new USEntity(in);
        }

        @Override
        public USEntity[] newArray(int size) {
            return new USEntity[size];
        }
    };

    public byte[] getBytes() {
        return mBytes;
    }

    public void setBytes(byte[] bytes) {
        mBytes = bytes;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }



    public USEntity() {
    }

    public USEntity(byte[] bytes) {
        if(bytes==null){
            bytes=new byte[0];
        }else
            mBytes = bytes;
    }

    public USEntity(byte[] bytes, int length) {
        if(bytes==null){
            bytes=new byte[0];
        }else
            mBytes = bytes;
        this.length = length;
    }

    protected USEntity(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in){
        this.mBytes = in.createByteArray();
        length=in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(mBytes);
        dest.writeInt(length);
    }
}
