package com.xg.east.hibot_b_library.bean;

import java.util.List;

/**
 * Created by EastRiseWM on 2017/1/16.                              根据灵聚语义返回的动作
 */

public class MotionBean {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * motion : 65537
         * index : 13
         * level : 1
         */

        private int motion;
        private int index;
        private String level;

        public int getMotion() {
            return motion;
        }

        public void setMotion(int motion) {
            this.motion = motion;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }
}
