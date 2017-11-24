package com.xg.east.hibot_b_library.bean;

/**
 * Created by EastRiseWM on 2017/1/19.
 */

public class MotorBean {
    private String targetId;/*电机Id*/
    private int trouble;/*故障编号*/
    private int degree;/*绝对角度*/

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public int getTrouble() {
        return trouble;
    }

    public void setTrouble(int trouble) {
        this.trouble = trouble;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}
