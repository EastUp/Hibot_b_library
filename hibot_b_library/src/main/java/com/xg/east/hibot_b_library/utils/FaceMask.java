package com.xg.east.hibot_b_library.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.faceplusplus.api.FaceDetecter.Face;

public class FaceMask extends View {
    Paint localPaint = new Paint();
    Face[] faceinfos = null;
    RectF rect = new RectF();

    public FaceMask(Context context, AttributeSet atti) {
        super(context, atti);
        rect = new RectF();
        localPaint = new Paint();
        localPaint.setColor(Color.GREEN);
        localPaint.setStrokeWidth(3);
        localPaint.setStyle(Paint.Style.STROKE);
    }

    public void setFaceInfo(Face[] faceinfos)
    {
        this.faceinfos = faceinfos;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (faceinfos == null)  return;
        Face face=new Face();
        for(int i=0;i<faceinfos.length-1;i++){
            for(int j=0;j<faceinfos.length-i-1;j++){
                if((faceinfos[j].right-faceinfos[j].left)>(faceinfos[j+1].right-faceinfos[j+1].left)){
                    face=faceinfos[j];
                    faceinfos[j]=faceinfos[j+1];
                    faceinfos[j+1]=face;
                }
            }
        }
        Face faceinfo = faceinfos[faceinfos.length - 1];
        rect.set(getWidth() * faceinfo.left, getHeight()
                        * faceinfo.top, getWidth() * faceinfo.right,
                getHeight()
                        * faceinfo.bottom);
        canvas.drawRect(rect, localPaint);
        ShowLog.d("w-h",getWidth()+"|||"+getHeight()+"\n"+
                getResources().getDisplayMetrics().widthPixels+"||||"+getResources().getDisplayMetrics().heightPixels);
//        for (Face localFaceInfo : faceinfos) {
//            rect.set(getWidth() * localFaceInfo.left, getHeight()
//                    * localFaceInfo.top, getWidth() * localFaceInfo.right,
//                    getHeight()
//                            * localFaceInfo.bottom);
//            canvas.drawRect(rect, localPaint);
//        }
    }

    public float getX(){
        return rect.centerX();
    }

    public float getY(){
        return rect.centerY();
    }

    public String getWH(){//获取矩形框的宽、高
        return rect.width()+"---"+rect.height();
    }

    public float getW(){
        return rect.width();
    }



}
