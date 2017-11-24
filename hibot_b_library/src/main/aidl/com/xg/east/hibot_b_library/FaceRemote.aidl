// FaceRemote.aidl
package com.xg.east.hibot_b_library;

// Declare any non-default types here with import statements
import com.xg.east.hibot_b_library.FaceCallBack;
import com.xg.east.hibot_b_library.FaceEntity;
interface FaceRemote {
    void openCameraAndFaceFollow();
    void restartFaceFollow();
    void stopFaceFollow();
    void closeFaceFollowAndCamera();
    void startFaceDiscern();
    void stopFaceDiscern();
    void runWithFaceFollow();
    void release();
    void registerCallBack(FaceCallBack cb);
    void unregisterCallBack(FaceCallBack cb);
}
