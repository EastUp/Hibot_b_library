package com.xg.east.hibot_b_library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class LOverrideActivity extends Activity {

    private static Callback mCallback;
    public static LOverrideActivity instance;

    public static void startActivity(Context context, final Callback resultCallback) {
        mCallback = resultCallback;
        Intent i = new Intent(context, LOverrideActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public interface Callback {
        void onCreate(Activity activity, Bundle savedInstanceState);

        void onRestart(Activity activity);

        void onStart(Activity activity);

        void onResume(Activity activity);

        void onPause(Activity activity);

        void onStop(Activity activity);

        void onDestroy(Activity activity);

        void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        mCallback.onCreate(this, savedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mCallback.onRestart(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCallback.onStart(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCallback.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCallback.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCallback.onStop(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCallback.onDestroy(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallback.onActivityResult(this, requestCode, resultCode, data);
    }
}
