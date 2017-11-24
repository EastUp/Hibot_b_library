package com.xg.east.hibot_b_library.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/18.
 */
public class IdentificationBean {

    /**
     * candidates : [{"person_id":"c93d09d186204aefa18e139a39072e9d","name":"西瓜","confidence":0.945339}]
     * face_id : 3c64e3b63344475dbd93d0fcc2eb5573
     * group_id : 744900ef63704de7b7b98efce839e9e2
     * status : OK
     */

    private String face_id;
    private String group_id;
    private String status;
    /**
     * person_id : c93d09d186204aefa18e139a39072e9d
     * name : 西瓜
     * confidence : 0.945339
     */

    private List<CandidatesEntity> candidates;

    public void setFace_id(String face_id) {
        this.face_id = face_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCandidates(List<CandidatesEntity> candidates) {
        this.candidates = candidates;
    }

    public String getFace_id() {
        return face_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public String getStatus() {
        return status;
    }

    public List<CandidatesEntity> getCandidates() {
        return candidates;
    }

    public static class CandidatesEntity {
        private String person_id;
        private String name;
        private double confidence;

        public void setPerson_id(String person_id) {
            this.person_id = person_id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setConfidence(double confidence) {
            this.confidence = confidence;
        }

        public String getPerson_id() {
            return person_id;
        }

        public String getName() {
            return name;
        }

        public double getConfidence() {
            return confidence;
        }
    }
}
