package com.example.jetpack.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.content.ContextCompat;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class PermissionUtil {
    private PermissionUtil() {
    }


    public static boolean hasPermission(Context context, String... permissions) {
        boolean hasPermission = true;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String permission : permissions) {
            int p = ContextCompat.checkSelfPermission(context, permission);
            MyLog.d(permission + "    |   " + p);
            if (p != PackageManager.PERMISSION_GRANTED) {
                hasPermission = false;
                break;
            }
        }
        return hasPermission;
    }

}
