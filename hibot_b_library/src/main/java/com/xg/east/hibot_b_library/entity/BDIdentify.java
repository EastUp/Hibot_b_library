package com.xg.east.hibot_b_library.entity;

import java.util.List;

/**
 * 项目名称：FaceSearchPro
 * 创建人：East
 * 创建时间：2017/9/19 13:18
 * 邮箱：EastRiseWM@163.com
 */

public class BDIdentify {

    /**
     * log_id : 73473737
     * result_num : 1
     * result : [{"group_id":"test1","uid":"u333333","user_info":"Test User","scores":[99.3,83.4]}]
     */

    private long log_id;
    private int result_num;
    private List<ResultBean> result;

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public int getResult_num() {
        return result_num;
    }

    public void setResult_num(int result_num) {
        this.result_num = result_num;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * group_id : test1
         * uid : u333333
         * user_info : Test User
         * scores : [99.3,83.4]
         */

        private String group_id;
        private String uid;
        private String user_info;
        private List<Double> scores;

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUser_info() {
            return user_info;
        }

        public void setUser_info(String user_info) {
            this.user_info = user_info;
        }

        public List<Double> getScores() {
            return scores;
        }

        public void setScores(List<Double> scores) {
            this.scores = scores;
        }
    }
}
