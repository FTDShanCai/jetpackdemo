package com.example.jetpack.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public abstract class BaseAndroidViewModel<T> extends AndroidViewModel {
    protected T navigator;

    public BaseAndroidViewModel(@NonNull Application application) {
        super(application);
    }

    public void onViewCrate(T navigator) {
        this.navigator = navigator;
    }

    public void onViewDetory() {
        this.navigator = null;
    }

}
