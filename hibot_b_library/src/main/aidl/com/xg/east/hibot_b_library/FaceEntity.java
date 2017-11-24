package com.xg.east.hibot_b_library;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：Test
 * 创建人：East
 * 创建时间：2017/8/18 14:44
 * 邮箱：EastRiseWM@163.com
 */

public class FaceEntity implements Parcelable {
    private  byte[] data;
    private int age;
    private int attractive;
    private int smile;
    private int gender;
    private int mask;
    private int eye_open;
    private int mouth_open;
    private int beard;
    private int eyeglass;
    private int sunglass;

    public FaceEntity() {
    }

    public FaceEntity(byte[] data) {
        if(data==null){
            data=new byte[0];
        }else
            this.data = data;

    }

    public FaceEntity(int age, int attractive, int smile, int gender, int mask, int eye_open, int mouth_open, int beard, int eyeglass, int sunglass) {
        this.age = age;
        this.attractive = attractive;
        this.smile = smile;
        this.gender = gender;
        this.mask = mask;
        this.eye_open = eye_open;
        this.mouth_open = mouth_open;
        this.beard = beard;
        this.eyeglass = eyeglass;
        this.sunglass = sunglass;
    }

    protected FaceEntity(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in){
        data = in.createByteArray();
        age = in.readInt();
        attractive = in.readInt();
        smile = in.readInt();
        gender = in.readInt();
        mask = in.readInt();
        eye_open = in.readInt();
        mouth_open = in.readInt();
        beard = in.readInt();
        eyeglass = in.readInt();
        sunglass = in.readInt();
    }

    public static final Creator<FaceEntity> CREATOR = new Creator<FaceEntity>() {
        @Override
        public FaceEntity createFromParcel(Parcel in) {
            return new FaceEntity(in);
        }

        @Override
        public FaceEntity[] newArray(int size) {
            return new FaceEntity[size];
        }
    };

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAttractive() {
        return attractive;
    }

    public void setAttractive(int attractive) {
        this.attractive = attractive;
    }

    public int getSmile() {
        return smile;
    }

    public void setSmile(int smile) {
        this.smile = smile;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getMask() {
        return mask;
    }

    public void setMask(int mask) {
        this.mask = mask;
    }

    public int getEye_open() {
        return eye_open;
    }

    public void setEye_open(int eye_open) {
        this.eye_open = eye_open;
    }

    public int getMouth_open() {
        return mouth_open;
    }

    public void setMouth_open(int mouth_open) {
        this.mouth_open = mouth_open;
    }

    public int getBeard() {
        return beard;
    }

    public void setBeard(int beard) {
        this.beard = beard;
    }

    public int getEyeglass() {
        return eyeglass;
    }

    public void setEyeglass(int eyeglass) {
        this.eyeglass = eyeglass;
    }

    public int getSunglass() {
        return sunglass;
    }

    public void setSunglass(int sunglass) {
        this.sunglass = sunglass;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(data);
        dest.writeInt(age);
        dest.writeInt(attractive);
        dest.writeInt(smile);
        dest.writeInt(gender);
        dest.writeInt(mask);
        dest.writeInt(eye_open);
        dest.writeInt(mouth_open);
        dest.writeInt(beard);
        dest.writeInt(eyeglass);
        dest.writeInt(sunglass);
    }
}
