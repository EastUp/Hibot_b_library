package com.xg.east.hibot_b_library.entity;

/**
 * Created by EastRiseWM on 2016/12/22.
 */

public class FirmWareBean {
    private int Type=0;//类型
    private String frimwareName="";//固件名称
    private int curentVersion=0;//当前版本
//    private FWNewestBean mFWNewestBean;//最新版本服务器请求到的Bean
    private FWNewestChangeBean mFWNewestBean;//最新版本服务器请求到的Bean
    private boolean canUpdate=false;//是否可以更新
    private boolean isChecked=false;//是否选择更新

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getFrimwareName() {
        return frimwareName;
    }

    public void setFrimwareName(String frimwareName) {
        this.frimwareName = frimwareName;
    }

    public int getCurentVersion() {
        return curentVersion;
    }

    public void setCurentVersion(int curentVersion) {
        this.curentVersion = curentVersion;
    }

    public FWNewestChangeBean getFWNewestChangeBean() {
        return mFWNewestBean;
    }

    public void setFWNewestBean(FWNewestChangeBean FWNewestBean) {
        mFWNewestBean = FWNewestBean;
    }

    public boolean isCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
