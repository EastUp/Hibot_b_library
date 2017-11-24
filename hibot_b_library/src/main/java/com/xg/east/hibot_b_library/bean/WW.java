package com.xg.east.hibot_b_library.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class WW {

    /**
     * image_id : 2455a1930afc4002adc119380c79d2b1
     * width : 320
     * height : 240
     * faces : [{"rect":{"left":111,"top":94,"right":187,"bottom":170},"face_id":"3f40d8c2d78442f4a97d6d3788ddd33d","landmarks21":[{"x":121.339806,"y":101.581146},{"x":132.401413,"y":99.638664},{"x":144.010239,"y":101.947838},{"x":162.395493,"y":103.391678},{"x":174.188385,"y":103.202827},{"x":184.403076,"y":107.144356},{"x":124.874115,"y":113.27565},{"x":141.568817,"y":113.665695},{"x":163.125153,"y":115.488289},{"x":179.087555,"y":118.199989},{"x":139.726135,"y":132.197235},{"x":150.694702,"y":135.71228},{"x":161.788544,"y":134.161285},{"x":149.855881,"y":142.955322},{"x":149.259155,"y":149.400055},{"x":148.622894,"y":156.393494},{"x":133.447144,"y":112.775269},{"x":171.32576,"y":116.257187},{"x":151.311935,"y":127.470253},{"x":134.524734,"y":149.984299},{"x":162.88588,"y":152.600052}]}]
     * status : OK
     */

    private String image_id;
    private int width;
    private int height;
    private String status;
    /**
     * rect : {"left":111,"top":94,"right":187,"bottom":170}
     * face_id : 3f40d8c2d78442f4a97d6d3788ddd33d
     * landmarks21 : [{"x":121.339806,"y":101.581146},{"x":132.401413,"y":99.638664},{"x":144.010239,"y":101.947838},{"x":162.395493,"y":103.391678},{"x":174.188385,"y":103.202827},{"x":184.403076,"y":107.144356},{"x":124.874115,"y":113.27565},{"x":141.568817,"y":113.665695},{"x":163.125153,"y":115.488289},{"x":179.087555,"y":118.199989},{"x":139.726135,"y":132.197235},{"x":150.694702,"y":135.71228},{"x":161.788544,"y":134.161285},{"x":149.855881,"y":142.955322},{"x":149.259155,"y":149.400055},{"x":148.622894,"y":156.393494},{"x":133.447144,"y":112.775269},{"x":171.32576,"y":116.257187},{"x":151.311935,"y":127.470253},{"x":134.524734,"y":149.984299},{"x":162.88588,"y":152.600052}]
     */

    private List<FacesEntity> faces;

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

    public List<FacesEntity> getFaces() {
        return faces;
    }

    public void setFaces(List<FacesEntity> faces) {
        this.faces = faces;
    }

    public static class FacesEntity {
        /**
         * left : 111
         * top : 94
         * right : 187
         * bottom : 170
         */

        private RectEntity rect;
        private String face_id;
        /**
         * x : 121.339806
         * y : 101.581146
         */

        private List<Landmarks21Entity> landmarks21;

        public RectEntity getRect() {
            return rect;
        }

        public void setRect(RectEntity rect) {
            this.rect = rect;
        }

        public String getFace_id() {
            return face_id;
        }

        public void setFace_id(String face_id) {
            this.face_id = face_id;
        }

        public List<Landmarks21Entity> getLandmarks21() {
            return landmarks21;
        }

        public void setLandmarks21(List<Landmarks21Entity> landmarks21) {
            this.landmarks21 = landmarks21;
        }

        public static class RectEntity {
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

        public static class Landmarks21Entity {
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
