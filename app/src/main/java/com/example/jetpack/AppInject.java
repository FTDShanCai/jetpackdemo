package com.example.jetpack;


import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.jetpack.di.DaggerAppComponent;
import com.example.jetpack.di.InjectAble;

import dagger.android.AndroidInjection;
import dagger.android.HasActivityInjector;
import dagger.android.support.AndroidSupportInjection;

import static androidx.fragment.app.FragmentManager.*;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class AppInject {

    public static void init(App app) {
        DaggerAppComponent.builder().application(app)
                .build().inject(app);

        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                handleActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }


    static void handleActivity(Activity activity) {
        if (activity instanceof HasActivityInjector) {
            AndroidInjection.inject(activity);
        }

        if (activity instanceof FragmentActivity) {
            FragmentActivity fragmentActivity = (FragmentActivity) activity;

            fragmentActivity.getSupportFragmentManager()
                    .registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks() {
                        @Override
                        public void onFragmentCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
                            super.onFragmentCreated(fm, f, savedInstanceState);
                            if (f instanceof InjectAble) {
                                AndroidSupportInjection.inject(f);
                            }
                        }
                    }, true);

        }

    }

}
