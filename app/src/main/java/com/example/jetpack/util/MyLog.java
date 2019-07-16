package com.example.jetpack.util;

import android.text.TextUtils;
import android.util.Log;

import com.example.jetpack.BuildConfig;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class MyLog {
    private static final String TAG = "ftd";

    private MyLog() {

    }

    public static void d(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (BuildConfig.DEBUG) {
            Log.d(TAG, message);
        }
    }

}
