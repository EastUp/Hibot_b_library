package com.xg.east.hibot_b_library.bean;

/**
 * Created by Lerist on 2017/03/05 0005.
 */

public class SceneEntity {
    private String id;/*场景类型*/
    private String name;
    private String state;
    private String isNull;
    private String extra;
    public SceneEntity() {

    }

    public SceneEntity(String id, String name, String state, String isNull,String extra) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.isNull = isNull;
        this.extra =extra;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
