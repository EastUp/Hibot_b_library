package com.xg.east.hibot_b_library.bean;

import java.util.List;

/**
 * Created by EastRiseWM on 2016/10/19.
 */

public class DetectWithAttribute {

    /**
     * image_id : dafa60a7f1444a6cb7b91ed7435ed26e
     * width : 640
     * height : 480
     * faces : [{"rect":{"left":274,"top":145,"right":459,"bottom":330},"face_id":"14d1428281604fffbd6cea342132eb03","landmarks21":[{"x":297.336426,"y":165.030319},{"x":324.76474,"y":162.026749},{"x":353.264709,"y":169.408463},{"x":397.671997,"y":173.506256},{"x":422.366089,"y":168.384308},{"x":443.755127,"y":173.169891},{"x":308.403717,"y":190.488861},{"x":345.486328,"y":194.487564},{"x":396.63092,"y":197.68428},{"x":430.664124,"y":197.424072},{"x":348.629089,"y":247.434143},{"x":375.038574,"y":258.498413},{"x":396.428711,"y":249.113037},{"x":372.103638,"y":275.540405},{"x":370.803558,"y":287.392944},{"x":369.922882,"y":302.412903},{"x":326.727081,"y":190.630753},{"x":412.923279,"y":195.809464},{"x":376.982178,"y":242.908173},{"x":335.98877,"y":286.155457},{"x":397.316132,"y":287.205139}],"emotions":{"angry":0,"calm":99,"confused":0,"disgust":0,"happy":0,"sad":0,"scared":0,"surprised":0,"squint":0,"screaming":0},"attributes":{"age":24,"attractive":80,"smile":0,"gender":100,"mask":0,"eye_open":99,"mouth_open":0,"beard":0,"eyeglass":11,"sunglass":2}}]
     * status : OK
     * request_id : 79CEE320-95FE-11E6-BA30-95BD2282253D
     */

    private String image_id;
    private int width;
    private int height;
    private String status;
    private String request_id;
    /**
     * rect : {"left":274,"top":145,"right":459,"bottom":330}
     * face_id : 14d1428281604fffbd6cea342132eb03
     * landmarks21 : [{"x":297.336426,"y":165.030319},{"x":324.76474,"y":162.026749},{"x":353.264709,"y":169.408463},{"x":397.671997,"y":173.506256},{"x":422.366089,"y":168.384308},{"x":443.755127,"y":173.169891},{"x":308.403717,"y":190.488861},{"x":345.486328,"y":194.487564},{"x":396.63092,"y":197.68428},{"x":430.664124,"y":197.424072},{"x":348.629089,"y":247.434143},{"x":375.038574,"y":258.498413},{"x":396.428711,"y":249.113037},{"x":372.103638,"y":275.540405},{"x":370.803558,"y":287.392944},{"x":369.922882,"y":302.412903},{"x":326.727081,"y":190.630753},{"x":412.923279,"y":195.809464},{"x":376.982178,"y":242.908173},{"x":335.98877,"y":286.155457},{"x":397.316132,"y":287.205139}]
     * emotions : {"angry":0,"calm":99,"confused":0,"disgust":0,"happy":0,"sad":0,"scared":0,"surprised":0,"squint":0,"screaming":0}
     * attributes : {"age":24,"attractive":80,"smile":0,"gender":100,"mask":0,"eye_open":99,"mouth_open":0,"beard":0,"eyeglass":11,"sunglass":2}
     */

    private List<FacesBean> faces;

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public List<FacesBean> getFaces() {
        return faces;
    }

    public void setFaces(List<FacesBean> faces) {
        this.faces = faces;
    }

    public static class FacesBean {
        /**
         * left : 274
         * top : 145
         * right : 459
         * bottom : 330
         */

        private RectBean rect;
        private String face_id;
        /**
         * angry : 0
         * calm : 99
         * confused : 0
         * disgust : 0
         * happy : 0
         * sad : 0
         * scared : 0
         * surprised : 0
         * squint : 0
         * screaming : 0
         */

        private EmotionsBean emotions;
        /**
         * age : 24
         * attractive : 80
         * smile : 0
         * gender : 100
         * mask : 0
         * eye_open : 99
         * mouth_open : 0
         * beard : 0
         * eyeglass : 11
         * sunglass : 2
         */

        private AttributesBean attributes;
        /**
         * x : 297.336426
         * y : 165.030319
         */

        private List<Landmarks21Bean> landmarks21;

        public RectBean getRect() {
            return rect;
        }

        public void setRect(RectBean rect) {
            this.rect = rect;
        }

        public String getFace_id() {
            return face_id;
        }

        public void setFace_id(String face_id) {
            this.face_id = face_id;
        }

        public EmotionsBean getEmotions() {
            return emotions;
        }

        public void setEmotions(EmotionsBean emotions) {
            this.emotions = emotions;
        }

        public AttributesBean getAttributes() {
            return attributes;
        }

        public void setAttributes(AttributesBean attributes) {
            this.attributes = attributes;
        }

        public List<Landmarks21Bean> getLandmarks21() {
            return landmarks21;
        }

        public void setLandmarks21(List<Landmarks21Bean> landmarks21) {
            this.landmarks21 = landmarks21;
        }

        public static class RectBean {
            private int left;
            private int top;
            private int right;
            private int bottom;

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public int getRight() {
                return right;
            }

            public void setRight(int right) {
                this.right = right;
            }

            public int getBottom() {
                return bottom;
            }

            public void setBottom(int bottom) {
                this.bottom = bottom;
            }
        }

        public static class EmotionsBean {
            private int angry;
            private int calm;
            private int confused;
            private int disgust;
            private int happy;
            private int sad;
            private int scared;
            private int surprised;
            private int squint;
            private int screaming;

            public int getAngry() {
                return angry;
            }

            public void setAngry(int angry) {
                this.angry = angry;
            }

            public int getCalm() {
                return calm;
            }

            public void setCalm(int calm) {
                this.calm = calm;
            }

            public int getConfused() {
                return confused;
            }

            public void setConfused(int confused) {
                this.confused = confused;
            }

            public int getDisgust() {
                return disgust;
            }

            public void setDisgust(int disgust) {
                this.disgust = disgust;
            }

            public int getHappy() {
                return happy;
            }

            public void setHappy(int happy) {
                this.happy = happy;
            }

            public int getSad() {
                return sad;
            }

            public void setSad(int sad) {
                this.sad = sad;
            }

            public int getScared() {
                return scared;
            }

            public void setScared(int scared) {
                this.scared = scared;
            }

            public int getSurprised() {
                return surprised;
            }

            public void setSurprised(int surprised) {
                this.surprised = surprised;
            }

            public int getSquint() {
                return squint;
            }

            public void setSquint(int squint) {
                this.squint = squint;
            }

            public int getScreaming() {
                return screaming;
            }

            public void setScreaming(int screaming) {
                this.screaming = screaming;
            }
        }

        public static class AttributesBean {
            private int age;
            private int attractive;
            private int smile;
            private int gender;
            private int mask;
            private int eye_open;
            private int mouth_open;
            private int beard;
            private int eyeglass;
            private int sunglass;

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public int getAttractive() {
                return attractive;
            }

            public void setAttractive(int attractive) {
                this.attractive = attractive;
            }

            public int getSmile() {
                return smile;
            }

            public void setSmile(int smile) {
                this.smile = smile;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getMask() {
                return mask;
            }

            public void setMask(int mask) {
                this.mask = mask;
            }

            public int getEye_open() {
                return eye_open;
            }

            public void setEye_open(int eye_open) {
                this.eye_open = eye_open;
            }

            public int getMouth_open() {
                return mouth_open;
            }

            public void setMouth_open(int mouth_open) {
                this.mouth_open = mouth_open;
            }

            public int getBeard() {
                return beard;
            }

            public void setBeard(int beard) {
                this.beard = beard;
            }

            public int getEyeglass() {
                return eyeglass;
            }

            public void setEyeglass(int eyeglass) {
                this.eyeglass = eyeglass;
            }

            public int getSunglass() {
                return sunglass;
            }

            public void setSunglass(int sunglass) {
                this.sunglass = sunglass;
            }
        }

        public static class Landmarks21Bean {
            private double x;
            private double y;

            public double getX() {
                return x;
            }

            public void setX(double x) {
                this.x = x;
            }

            public double getY() {
                return y;
            }

            public void setY(double y) {
                this.y = y;
            }
        }
    }
}
