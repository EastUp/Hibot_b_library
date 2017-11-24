package com.xg.east.hibot_b_library.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 */
public class DetectBena {

    /**
     * image_id : f25811d207304c83a27aadb171824c0f
     * width : 1944
     * height : 2592
     * faces : [{"face_id":"cc0fc099a43942e29f5de68effabf974","rect":{"left":639,"top":830,"right":1330,"bottom":1521},"eye_dist":327,"landmarks21":[[716.464355,919.254883],[809.45752,898.232605],[907.851807,919.926147],[1069.638794,919.34436],[1167.594238,896.286865],[1258.426025,912.794678],[756.637329,1014.56189],[894.350952,1020.570801],[1085.629639,1019.806885],[1221.184082,1011.121094],[902.26062,1205.994507],[992.415344,1237.706055],[1078.27417,1205.830811],[992.712891,1309.091553],[993.942444,1353.783325],[995.394409,1407.250732],[824.596069,1009.450439],[1152.534302,1007.308838],[989.411743,1174.66687],[874.825195,1355.09668],[1104.639404,1352.526978]]}]
     * status : OK
     */

    private String image_id;
    private int width;
    private int height;
    private String status;
    /**
     * face_id : cc0fc099a43942e29f5de68effabf974
     * rect : {"left":639,"top":830,"right":1330,"bottom":1521}
     * eye_dist : 327
     * landmarks21 : [[716.464355,919.254883],[809.45752,898.232605],[907.851807,919.926147],[1069.638794,919.34436],[1167.594238,896.286865],[1258.426025,912.794678],[756.637329,1014.56189],[894.350952,1020.570801],[1085.629639,1019.806885],[1221.184082,1011.121094],[902.26062,1205.994507],[992.415344,1237.706055],[1078.27417,1205.830811],[992.712891,1309.091553],[993.942444,1353.783325],[995.394409,1407.250732],[824.596069,1009.450439],[1152.534302,1007.308838],[989.411743,1174.66687],[874.825195,1355.09668],[1104.639404,1352.526978]]
     */

    private List<FacesEntity> faces;

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFaces(List<FacesEntity> faces) {
        this.faces = faces;
    }

    public String getImage_id() {
        return image_id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getStatus() {
        return status;
    }

    public List<FacesEntity> getFaces() {
        return faces;
    }

    public static class FacesEntity {
        private String face_id;
        /**
         * left : 639
         * top : 830
         * right : 1330
         * bottom : 1521
         */

        private RectEntity rect;
        private int eye_dist;
        private List<List<Double>> landmarks21;

        public void setFace_id(String face_id) {
            this.face_id = face_id;
        }

        public void setRect(RectEntity rect) {
            this.rect = rect;
        }

        public void setEye_dist(int eye_dist) {
            this.eye_dist = eye_dist;
        }

        public void setLandmarks21(List<List<Double>> landmarks21) {
            this.landmarks21 = landmarks21;
        }

        public String getFace_id() {
            return face_id;
        }

        public RectEntity getRect() {
            return rect;
        }

        public int getEye_dist() {
            return eye_dist;
        }

        public List<List<Double>> getLandmarks21() {
            return landmarks21;
        }

        public static class RectEntity {
            private int left;
            private int top;
            private int right;
            private int bottom;

            public void setLeft(int left) {
                this.left = left;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public void setRight(int right) {
                this.right = right;
            }

            public void setBottom(int bottom) {
                this.bottom = bottom;
            }

            public int getLeft() {
                return left;
            }

            public int getTop() {
                return top;
            }

            public int getRight() {
                return right;
            }

            public int getBottom() {
                return bottom;
            }
        }
    }
}
