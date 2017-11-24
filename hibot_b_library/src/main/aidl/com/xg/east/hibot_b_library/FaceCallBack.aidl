// FaceCallBack.aidl
package com.xg.east.hibot_b_library;

// Declare any non-default types here with import statements
import com.xg.east.hibot_b_library.FaceEntity;
interface FaceCallBack {
    void sendData(inout FaceEntity fe);
    void onPreviewFrame(inout FaceEntity fe);
    void onCameraOpen(boolean cameraOpen);
    void onFindFace(boolean findFace);
    void onFaceFllowWithMoving(int state);
    void dealDiscernFail(String error);
    void dealDiscernResult(String result, double score);
    void incognizant();
}
