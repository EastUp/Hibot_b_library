package com.xg.east.hibot_b_library.face_utils;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


/**
 * Created by Lerist on 2017/02/20 0020.
 */

public class FloatWindowUtils {
    private static ViewContainer rootView;
    private static WindowManager windowManager;
    private static WindowManager.LayoutParams layoutParams;

    private static void init(Context context) {
        if (windowManager == null)
            windowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
    }

    static float downX;
    static float downY;

    public static ViewContainer addView(Context context, View view, int gravity, boolean isCanDrag) {
        init(context);
        if (rootView == null) {
            rootView = new ViewContainer(context);
            int w = WindowManager.LayoutParams.WRAP_CONTENT;
            int h = WindowManager.LayoutParams.WRAP_CONTENT;
            int flags = 0;
            int type = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                type = WindowManager.LayoutParams.TYPE_TOAST;
            } else {
                type = WindowManager.LayoutParams.TYPE_PHONE;
            }
            flags |= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
//                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            layoutParams = new WindowManager.LayoutParams(w, h, type, flags, PixelFormat.TRANSPARENT);
            layoutParams.gravity = gravity;
            windowManager.addView(rootView, layoutParams);
        }
        rootView.addView(view,new ViewGroup.LayoutParams(1,1));
//        rootView.addView(view,new ViewGroup.LayoutParams(800,800));
        if (isCanDrag) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (windowManager == null || layoutParams == null || rootView == null)
                        return false;
                    int measuredWidth = v.getWidth();
                    int measuredHeight = v.getHeight();
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            downX = event.getX();
                            downY = event.getY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            layoutParams.x = (int) (event.getRawX() - downX);
                            layoutParams.y = (int) (event.getRawY() - downY);
//                            KLog.e(layoutParams.x + " , " + layoutParams.y);
                            windowManager.updateViewLayout(rootView, layoutParams);
                            break;
                        case MotionEvent.ACTION_UP:
//                            layoutParams.x = (int) (event.getRawX() - downX);
//                            layoutParams.y = (int) (event.getRawY() - downY);
//                            windowManager.updateViewLayout(rootView, layoutParams);
                            downX = downY = 0;
                            break;
                    }
                    return false;
                }
            });
        }
        return rootView;
    }

    public static void clear() {
        if (windowManager != null && rootView != null) {
            try {
                if (rootView.getParent() != null)
                    windowManager.removeView(rootView);
                rootView.removeAllViews();
                rootView = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
