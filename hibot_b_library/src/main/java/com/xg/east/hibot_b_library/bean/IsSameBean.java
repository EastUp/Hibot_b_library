package com.xg.east.hibot_b_library.bean;

/**
 * Created by Administrator on 2016/5/16.
 */
public class IsSameBean {

    /**
     * same_person : true
     * confidence : 0.949857
     * face_id : 5b52b128c6904598b6af64186ec54e78
     * person_id : 2813b75dd8c94201942f001fd1bbad16
     * status : OK
     */

    private boolean same_person;
    private double confidence;
    private String face_id;
    private String person_id;
    private String status;

    public void setSame_person(boolean same_person) {
        this.same_person = same_person;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public void setFace_id(String face_id) {
        this.face_id = face_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSame_person() {
        return same_person;
    }

    public double getConfidence() {
        return confidence;
    }

    public String getFace_id() {
        return face_id;
    }

    public String getPerson_id() {
        return person_id;
    }

    public String getStatus() {
        return status;
    }
}
