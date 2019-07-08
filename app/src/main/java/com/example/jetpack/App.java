package com.example.jetpack;


import androidx.multidex.MultiDexApplication;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class App extends MultiDexApplication {

    private static App instance = null;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
