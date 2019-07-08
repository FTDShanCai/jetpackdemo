package com.example.jetpack.ui.main;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.jetpack.ui.BaseAndroidViewModel;


/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class MainViewModel extends BaseAndroidViewModel<MainNavigator> {

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void goToStoreHouse() {
        if (navigator != null) navigator.goStoreHouse();
    }

    public void goToStoreMenu() {
        if (navigator != null) navigator.goStoreMenu();
    }
}
