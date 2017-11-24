package com.xg.east.hibot_b_library.face_utils;

import android.graphics.RectF;
import android.os.RemoteException;
import android.util.DisplayMetrics;

import com.faceplusplus.api.FaceDetecter;
import com.xg.east.hibot_b_library.SerialSend;
import com.xg.east.hibot_b_library.service.FaceService;
import com.xg.east.hibot_b_library.service.usbSendType.ClassicType;
import com.xg.east.hibot_b_library.service.usbSendType.LightType;
import com.xg.east.hibot_b_library.service.usbSendType.MotorPType;
import com.xg.east.hibot_b_library.utils.ShowLog;


/**
 * Created by EastRiseWM on 2017/1/12.
 */

public class MainPresenter2 {
//    private Context mContext;
    private RectF rect;/*人脸检测的矩形框*/
    private FaceDetecter.Face[] faceinfo;
    private int time = 0;/*待机状态，Action2头部运动的次数*/
    public boolean findFace = false;/*是否找到人脸*/
    private int faceDetectSpeedH = 3000;/*横向*/
    private int faceDetectSpeedV = 3000;/*纵向*/
    private int HorizontalDegree = 500;/*超过头部5度就动底盘*/
    public boolean IsfaceFollowWithBody = false;/*是否接收到了你过来的指令*/
    public int noFaceCount = 31;/*第一次假装新来了一个人*/
    private boolean isActionFinish = true;/*动作是否完成*/
    private boolean quiz = false;/*是否已经提过问了*/
    private int faces = 0;/*总共发现多少次人脸*/
    private int bili = 1;/*运行路程的比例*/
    private FaceService mFaceService;
    public MainPresenter2(FaceService faceService) {
        mFaceService= faceService;
        rect = new RectF();
    }

    public void setFaceInfo(FaceDetecter.Face[] faceinfos) {
        faceinfo = faceinfos;
        if (faceinfos == null) return;
        FaceDetecter.Face face = new FaceDetecter.Face();
        for (int i = 0; i < faceinfos.length - 1; i++) {
            for (int j = 0; j < faceinfos.length - i - 1; j++) {
                if ((faceinfos[j].right - faceinfos[j].left) > (faceinfos[j + 1].right - faceinfos[j + 1].left)) {
                    face = faceinfos[j];
                    faceinfos[j] = faceinfos[j + 1];
                    faceinfos[j + 1] = face;
                }
            }
        }
        FaceDetecter.Face faceinfo = faceinfos[faceinfos.length - 1];
        DisplayMetrics displayMetrics = mFaceService.getResources().getDisplayMetrics();
        rect.set(displayMetrics.widthPixels * faceinfo.left, displayMetrics.heightPixels
                        * faceinfo.top, displayMetrics.widthPixels * faceinfo.right,
                displayMetrics.heightPixels
                        * faceinfo.bottom);
    }

    public float getX() {
        return rect.centerX();
    }

    public float getY() {
        return rect.centerY();
    }

    public float getW() {
        return rect.width();
    }

    public float getH() {
        return rect.height();
    }

    /**
     * 人脸跟踪
     */
    public void startFaceDetect(int left, int right, int top, int bottom, int widthDistance, int heightDistance) {
        ShowLog.d("Timer1FaceWidth", getW() + "\n" + getH());
        /*是否检测到人脸，并且不是在场景模式下工作*/
        if (faceinfo != null && getW() > 0) {
            if (isThereNewPerson()) {
                byte[] bytes2 = LightType.headVoice(3);
                mFaceService.sendData(bytes2);
//                SerialSend.sendAllBytesToSerial(bytes2);
            }
            faces++;
            noFaceCount = 0;
            findFace = true;
            float centerX = ((float) (right - left)) / 2;
            float centerY = ((float) (bottom - top)) / 2;
            final float distanceX = getX() - centerX;
            float distanceY = getY() - centerY;
            changeHeadSpeed(distanceX, distanceY, widthDistance, heightDistance);
            //ShowLog.v("Timer12JceXXY", distanceX + "--" + distanceY);
            if (distanceX > widthDistance) {
                float degreexFloat = (distanceX - widthDistance) / 118 * 300;
                int degreex = (int) degreexFloat;//右
                byte[] bytes1 = new byte[0];
                byte[] bytes3 = new byte[0];
                if (SerialSend.mBRDgree > -HorizontalDegree) {/*1111111111111*/
                    if (SerialSend.mBRDgree - degreex < -HorizontalDegree) {
                        bytes1 = MotorPType.stepMpParamsRunNotEndDegree(0x010B, -faceDetectSpeedH, (HorizontalDegree + SerialSend.mBRDgree) / bili, 1);//右
                    } else {
                        bytes1 = MotorPType.stepMpParamsRunNotEndDegree(0x010B, -faceDetectSpeedH, degreex / bili, 1);//右
                    }
                    bytes3 = ClassicType.runWithSpeed(0, 0, 1);
                } else {
                    int speedDegree = degreex / 100;/*角速度角速度角速度角速度角速度*/
                    int v = (int) (speedDegree * 19.5 * 3.14 / 180 * 0.001 * 8200);
                    bytes3 = ClassicType.runWithSpeed(v, -v, 100);
                }
                if (distanceY > heightDistance) {
                    float degreeyFloat = (distanceY - heightDistance) / 60 * 300;
                    int degreey = (int) degreeyFloat;
                    byte[] bytes2 = new byte[0];
                    if (SerialSend.mARDgree > -1500) {
                        if (SerialSend.mARDgree - degreey < -1500) {
                            bytes2 = MotorPType.stepMpParamsRunNotEndDegree(0x010A, -faceDetectSpeedV, (SerialSend.mARDgree + 1500) / bili, 1);//下
                        } else {
                            bytes2 = MotorPType.stepMpParamsRunNotEndDegree(0x010A, -faceDetectSpeedV, degreey / bili, 1);//下
                        }
                    }
//                    if (SerialSend.isReceive) {
                    mFaceService.sendData(bytes1,bytes2,bytes3);
//                    SerialSend.sendAllBytesToSerial(bytes1, bytes2, bytes3);
//                    }
                } else if (distanceY < -heightDistance) {
                    float degreeyFloat = (-heightDistance - distanceY) / 60 * 300;
                    int degreey = (int) degreeyFloat;
                    byte[] bytes2 = new byte[0];
                    if (SerialSend.mARDgree < 2000) {
                        if (SerialSend.mARDgree + degreey > 2000) {
                            bytes2 = MotorPType.stepMpParamsRunNotEndDegree(0x010A, faceDetectSpeedV, (2000 - SerialSend.mARDgree) / bili, 1);
                        } else {
                            bytes2 = MotorPType.stepMpParamsRunNotEndDegree(0x010A, faceDetectSpeedV, degreey / bili, 1);//上
                        }
                    }
//                    if (SerialSend.isReceive) {
                    mFaceService.sendData(bytes1,bytes2,bytes3);
//                    SerialSend.sendAllBytesToSerial(bytes1, bytes2, bytes3);
//                    }
                } else {
//                    if (SerialSend.isReceive) {
                    mFaceService.sendData(bytes1,bytes3);
//                    SerialSend.sendAllBytesToSerial(bytes1, bytes3);
//                    }
                }
            } else if (distanceX < -widthDistance) {
                float degreexFloat = (-widthDistance - distanceX) / 118 * 300;
                int degreex = (int) degreexFloat;//左
                //ShowLog.d("DDDD", SerialSend.mBRDgree + "||||" + degreex + "\n" +
//                        (SerialSend.mBRDgree - degreex));
                byte[] bytes1 = new byte[0];
                byte[] bytes3 = new byte[0];
                if (SerialSend.mBRDgree < HorizontalDegree) {/*222222222222222222*/
                    if (SerialSend.mBRDgree + degreex > HorizontalDegree) {
                        bytes1 = MotorPType.stepMpParamsRunNotEndDegree(0x010B, faceDetectSpeedH, (HorizontalDegree - SerialSend.mBRDgree) / bili, 1);//左
                    } else {
                        bytes1 = MotorPType.stepMpParamsRunNotEndDegree(0x010B, faceDetectSpeedH, degreex / bili, 1);//左
                    }
                    bytes3 = ClassicType.runWithSpeed(0, 0, 1);
                } else {
                    int speedDegree = degreex / 100;/*角速度角速度角速度角速度角速度角速度*/
                    int v = (int) (speedDegree * 19.5 * 3.14 / 180 * 0.001 * 8200);
                    bytes3 = ClassicType.runWithSpeed(-v, v, 100);
                    //ShowLog.d("DDDDTTTTTT", SerialSend.mBRDgree + "||||" + degreex + "\n" +
//                            (SerialSend.mBRDgree - degreex));
                }
                if (distanceY > heightDistance) {
                    float degreeyFloat = (distanceY - heightDistance) / 60 * 300;
                    int degreey = (int) degreeyFloat;
                    byte[] bytes2 = new byte[0];
                    if (SerialSend.mARDgree > -1500) {
                        if (SerialSend.mARDgree - degreey < -1500) {
                            bytes2 = MotorPType.stepMpParamsRunNotEndDegree(0x010A, -faceDetectSpeedV, (SerialSend.mARDgree + 1500) / bili, 1);//下
                        } else {
                            bytes2 = MotorPType.stepMpParamsRunNotEndDegree(0x010A, -faceDetectSpeedV, degreey / bili, 1);//下
                        }
                    }
//                    if (SerialSend.isReceive) {
                    mFaceService.sendData(bytes1,bytes2,bytes3);
//                    SerialSend.sendAllBytesToSerial(bytes1, bytes2, bytes3);
//                    }
                } else if (distanceY < -heightDistance) {
                    float degreeyFloat = (-heightDistance - distanceY) / 60 * 300;
                    int degreey = (int) degreeyFloat;
                    byte[] bytes2 = new byte[0];
                    if (SerialSend.mARDgree < 2000) {
                        if (SerialSend.mARDgree + degreey > 2000) {
                            bytes2 = MotorPType.stepMpParamsRunNotEndDegree(0x010A, faceDetectSpeedV, (2000 - SerialSend.mARDgree) / bili, 1);
                        } else {
                            bytes2 = MotorPType.stepMpParamsRunNotEndDegree(0x010A, faceDetectSpeedV, degreey / bili, 1);//上
                        }
                    }
                    mFaceService.sendData(bytes1,bytes2,bytes3);
//                    SerialSend.sendAllBytesToSerial(bytes1, bytes2, bytes3);
                } else {
                    mFaceService.sendData(bytes1,bytes3);
//                    SerialSend.sendAllBytesToSerial(bytes1, bytes3);
                }
            } else {
                if (distanceY > heightDistance) {
                    float degreeyFloat = (distanceY - heightDistance) / 60 * 300;
                    int degreey = (int) degreeyFloat;
                    byte[] bytes2 = new byte[0];
                    if (SerialSend.mARDgree > -1500) {
                        if (SerialSend.mARDgree - degreey < -1500) {
                            bytes2 = MotorPType.stepMpParamsRunNotEndDegree(0x010A, -faceDetectSpeedV, (SerialSend.mARDgree + 1500) / bili, 1);//下
                        } else {
                            bytes2 = MotorPType.stepMpParamsRunNotEndDegree(0x010A, -faceDetectSpeedV, degreey / bili, 1);//下
                        }
                    }
                    mFaceService.sendData(bytes2);
//                    SerialSend.sendAllBytesToSerial(bytes2);
                } else if (distanceY < -heightDistance) {
                    float degreeyFloat = (-heightDistance - distanceY) / 60 * 300;
                    int degreey = (int) degreeyFloat;
                    byte[] bytes2 = new byte[0];
                    if (SerialSend.mARDgree < 2000) {
                        if (SerialSend.mARDgree + degreey > 2000) {
                            bytes2 = MotorPType.stepMpParamsRunNotEndDegree(0x010A, faceDetectSpeedV, (2000 - SerialSend.mARDgree) / bili, 1);
                        } else {
                            bytes2 = MotorPType.stepMpParamsRunNotEndDegree(0x010A, faceDetectSpeedV, degreey / bili, 1);//上
                        }
                    }
                    mFaceService.sendData(bytes2);
//                    SerialSend.sendAllBytesToSerial(bytes2);
                }
                /*是否要机器人过去*/
                if (IsfaceFollowWithBody) {
                    if (getW() < 340) {
                        if (!SerialSend.lockClassic) {
                            byte[] bytes3 = ClassicType.runWithSpeed(15, 15, 100);
                            mFaceService.sendData(bytes3);
//                            SerialSend.sendAllBytesToSerial(bytes3);
                        }
                    }else if(SerialSend.getInstance().mInfrared2<=40){
                        byte[] bytes3 = ClassicType.runWithSpeed(0, 0, 1);
                        mFaceService.sendData(bytes3,bytes3,bytes3);
//                        SerialSend.sendAllBytesToSerial(bytes3,bytes3,bytes3);

//                        FaceUtils.getInstance().mOnFaceListener.onFaceFllowWithMoving(1);
                        if (mFaceService.mFaceCallBack != null) {
                            try {
                                mFaceService.mFaceCallBack.onFaceFllowWithMoving(1);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                        IsfaceFollowWithBody = false;
                    }else if(SerialSend.getInstance().forwardLeftCrash && SerialSend.getInstance().forwardRightCrash && SerialSend.getInstance().backCrash){
                        byte[] bytes3 = ClassicType.runWithSpeed(0, 0, 10);
                        mFaceService.sendData(bytes3,bytes3,bytes3);
//                        SerialSend.sendAllBytesToSerial(bytes3,bytes3,bytes3);
                        if (mFaceService.mFaceCallBack != null) {
                            try {
                                mFaceService.mFaceCallBack.onFaceFllowWithMoving(2);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                        IsfaceFollowWithBody = false;
                    } else {
                        byte[] bytes3 = ClassicType.runWithSpeed(0, 0, 10);
                        mFaceService.sendData(bytes3,bytes3,bytes3);
//                        SerialSend.sendAllBytesToSerial(bytes3,bytes3,bytes3);
                        if (mFaceService.mFaceCallBack != null) {
                            try {
                                mFaceService.mFaceCallBack.onFaceFllowWithMoving(0);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                        IsfaceFollowWithBody = false;
                    }
                }
                /*当发现头和身体的角度很大后，自主转动底盘找寻人脸*/
                if (SerialSend.mBRDgree > 2000) {/*头在身体的左边*/
                    mFaceService.sendData(ClassicType.runWithSpeed(5, -5, 10));
//                    SerialSend.sendAllBytesToSerial(ClassicType.runWithSpeed(5, -5, 10));/*身体像右转*/
                } else if (SerialSend.mBRDgree < -2000) {
                    mFaceService.sendData(ClassicType.runWithSpeed(-5, 5, 10));
//                    SerialSend.sendAllBytesToSerial(ClassicType.runWithSpeed(-5, 5, 10));
                }
            }
            //ShowLog.v("Timer13", getW() + "");
        } else {
            noFaceCount++;
            if (noFaceCount == 4) {
                if (IsfaceFollowWithBody) {/*是否有人在叫机器人过去*/
                    if( mFaceService.mFaceCallBack!=null){
                        try {
                            mFaceService.mFaceCallBack.onFaceFllowWithMoving(0);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    IsfaceFollowWithBody = false;
                }
            }
            if (findFace && noFaceCount == 15) {
                byte[] bytes3 = ClassicType.runWithSpeed(0, 0, 10);
                mFaceService.sendData(bytes3);
//                SerialSend.sendAllBytesToSerial(/*bytes1,*/bytes3);
                findFace = false;
                //ShowLog.d("Timer14findFace", findFace + "");
            }
        }
    }

    /*这里是否刚过来一个人*/
    private boolean isThereNewPerson() {
        return noFaceCount > 30;
    }




    /*改变头部运动速度*/
    private void changeHeadSpeed(float distanceX, float distanceY, int widthDistance, int heightDistance) {
        int degreex = 0;
        int degreey = 0;
        if (distanceX > widthDistance) {
            float degreexFloat = (distanceX - widthDistance) / 118 * 300;
            degreex = (int) degreexFloat;//右
        } else if (distanceX < -widthDistance) {
            float degreexFloat = (-widthDistance - distanceX) / 118 * 300;
            degreex = (int) degreexFloat;//左
        }
        if (distanceY > heightDistance) {
            float degreeyFloat = (distanceY - heightDistance) / 60 * 300;
            degreey = (int) degreeyFloat;
        } else if (distanceY < -heightDistance) {
            float degreeyFloat = (-heightDistance - distanceY) / 60 * 300;
            degreey = (int) degreeyFloat;
        }
        if (degreex != 0 || degreey != 0) {
            faceDetectSpeedH = degreex * 3;
            faceDetectSpeedV = degreey * 3;
//            if (degreex >= 2000) {
//                faceDetectSpeedH = 2000;
//            } else {
//                faceDetectSpeedH = 1000;
//            }
//            if (degreey>=1500) {
//                faceDetectSpeedV = 1000;
//            } else {
//                faceDetectSpeedV = 500;
//            }
        }
    }




}
