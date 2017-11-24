package com.xg.east.hibot_b_library.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/6.
 */
public class RobotResults {

    /**
     * moreResults : [{"rc":0,"answer":{"type":"T","text":"拼音[nǐ hǎo] 打招呼的敬语，作为一般对话的开场白。这也是个最基本的英语单词。"},"service":"baike","text":"你好","operation":"ANSWER"}]
     * rc : 0
     * operation : ANSWER
     * service : openQA
     * answer : {"type":"T","text":"你也好，见到你真开心！"}
     * text : 你好
     */

    private int rc;
    private String operation;
    private String service;
    /**
     * type : T
     * text : 你也好，见到你真开心！
     */

    private AnswerEntity answer;
    private String text;
    /**
     * rc : 0
     * answer : {"type":"T","text":"拼音[nǐ hǎo] 打招呼的敬语，作为一般对话的开场白。这也是个最基本的英语单词。"}
     * service : baike
     * text : 你好
     * operation : ANSWER
     */

    private List<MoreResultsEntity> moreResults;

    public void setRc(int rc) {
        this.rc = rc;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setAnswer(AnswerEntity answer) {
        this.answer = answer;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMoreResults(List<MoreResultsEntity> moreResults) {
        this.moreResults = moreResults;
    }

    public int getRc() {
        return rc;
    }

    public String getOperation() {
        return operation;
    }

    public String getService() {
        return service;
    }

    public AnswerEntity getAnswer() {
        return answer;
    }

    public String getText() {
        return text;
    }

    public List<MoreResultsEntity> getMoreResults() {
        return moreResults;
    }

    public static class AnswerEntity {
        private String type;
        private String text;

        public void setType(String type) {
            this.type = type;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public String getText() {
            return text;
        }
    }

    public static class MoreResultsEntity {
        private int rc;
        /**
         * type : T
         * text : 拼音[nǐ hǎo] 打招呼的敬语，作为一般对话的开场白。这也是个最基本的英语单词。
         */

        private AnswerEntity answer;
        private String service;
        private String text;
        private String operation;

        public void setRc(int rc) {
            this.rc = rc;
        }

        public void setAnswer(AnswerEntity answer) {
            this.answer = answer;
        }

        public void setService(String service) {
            this.service = service;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public int getRc() {
            return rc;
        }

        public AnswerEntity getAnswer() {
            return answer;
        }

        public String getService() {
            return service;
        }

        public String getText() {
            return text;
        }

        public String getOperation() {
            return operation;
        }

        public static class AnswerEntity {
            private String type;
            private String text;

            public void setType(String type) {
                this.type = type;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getType() {
                return type;
            }

            public String getText() {
                return text;
            }
        }
    }
}
