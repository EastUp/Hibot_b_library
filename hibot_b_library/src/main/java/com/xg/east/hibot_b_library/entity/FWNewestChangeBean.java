package com.xg.east.hibot_b_library.entity;

import java.util.List;

/**
 * 项目名称：Robot
 * 创建人：East
 * 创建时间：2017/4/17 18:23
 * 邮箱：EastRiseWM@163.com
 */

public class FWNewestChangeBean {

    /**
     * success : true
     * object : [{"size":"32.41KB","createTime":1482719299000,"name":"F407","id":28,"fileMD5":"41DF554FB9BFF8A6256BC0E05B8CE523","type":"4","version":"103","url":"http://7xspup.com1.z0.glb.clouddn.com/firmware/BHost_103.bin","fileType":"bin","lastUpdateTime":1492258388000}]
     * message :
     * statusCode : 200
     * remark :
     * returnTime : 2017-04-17 18:13:09
     * addition :
     */

    private boolean success;
    private String message;
    private String statusCode;
    private String remark;
    private String returnTime;
    private String addition;
    private List<ObjectBean> object;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public List<ObjectBean> getObject() {
        return object;
    }

    public void setObject(List<ObjectBean> object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * size : 32.41KB
         * createTime : 1482719299000
         * name : F407
         * id : 28
         * fileMD5 : 41DF554FB9BFF8A6256BC0E05B8CE523
         * type : 4
         * version : 103
         * url : http://7xspup.com1.z0.glb.clouddn.com/firmware/BHost_103.bin
         * fileType : bin
         * lastUpdateTime : 1492258388000
         */

        private String size;
        private long createTime;
        private String name;
        private int id;
        private String fileMD5;
        private String type;
        private String version;
        private String url;
        private String fileType;
        private long lastUpdateTime;

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFileMD5() {
            return fileMD5;
        }

        public void setFileMD5(String fileMD5) {
            this.fileMD5 = fileMD5;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

        public long getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(long lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }
    }
}
