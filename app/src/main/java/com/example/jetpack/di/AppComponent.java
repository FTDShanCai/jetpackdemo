package com.example.jetpack.di;


import android.app.Application;

import com.example.jetpack.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}
