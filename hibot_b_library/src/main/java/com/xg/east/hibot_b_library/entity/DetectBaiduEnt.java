package com.xg.east.hibot_b_library.entity;

import java.util.List;

/**
 * 项目名称：FaceSearchPro
 * 创建人：East
 * 创建时间：2017/9/20 15:01
 * 邮箱：EastRiseWM@163.com
 */

public class DetectBaiduEnt {

    /**
     * result_num : 1
     * result : [{"rotation_angle":0,"yaw":-8.4896440505981,"roll":-1.8870124816895,"face_probability":0.96473443508148,"pitch":1.8859195709229,"location":{"left":147,"height":238,"width":254,"top":267}}]
     * log_id : 3230152520
     */

    private int result_num;
    private long log_id;
    private List<ResultBean> result;

    public int getResult_num() {
        return result_num;
    }

    public void setResult_num(int result_num) {
        this.result_num = result_num;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * rotation_angle : 0
         * yaw : -8.4896440505981
         * roll : -1.8870124816895
         * face_probability : 0.96473443508148
         * pitch : 1.8859195709229
         * location : {"left":147,"height":238,"width":254,"top":267}
         */

        private int rotation_angle;
        private double yaw;
        private double roll;
        private double face_probability;
        private double pitch;
        private LocationBean location;

        public int getRotation_angle() {
            return rotation_angle;
        }

        public void setRotation_angle(int rotation_angle) {
            this.rotation_angle = rotation_angle;
        }

        public double getYaw() {
            return yaw;
        }

        public void setYaw(double yaw) {
            this.yaw = yaw;
        }

        public double getRoll() {
            return roll;
        }

        public void setRoll(double roll) {
            this.roll = roll;
        }

        public double getFace_probability() {
            return face_probability;
        }

        public void setFace_probability(double face_probability) {
            this.face_probability = face_probability;
        }

        public double getPitch() {
            return pitch;
        }

        public void setPitch(double pitch) {
            this.pitch = pitch;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public static class LocationBean {
            /**
             * left : 147
             * height : 238
             * width : 254
             * top : 267
             */

            private int left;
            private int height;
            private int width;
            private int top;

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }
        }
    }
}
