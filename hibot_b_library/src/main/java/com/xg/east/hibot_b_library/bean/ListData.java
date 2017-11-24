package com.xg.east.hibot_b_library.bean;

/**
 * Created by Administrator on 2016/5/6.
 */
public class ListData {
    public static final int RECEIVE=1;
    public static final int SEND=2;
    private String time;
    private int flag;
    private String content;

    public ListData(String time, int flag, String content) {
        this.time = time;
        this.flag = flag;
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
