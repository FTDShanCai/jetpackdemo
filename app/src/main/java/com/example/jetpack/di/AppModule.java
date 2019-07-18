package com.example.jetpack.di;

import com.example.jetpack.util.AppExecutors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    AppExecutors providerAppExecutors() {
        return new AppExecutors();
    }

}
