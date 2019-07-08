package com.example.jetpack.ui.storeHouse;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.jetpack.App;
import com.example.jetpack.db.Injection;
import com.example.jetpack.db.repository.GoodsRepositroy;
import com.example.jetpack.ui.BaseAndroidViewModel;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class StoreHouseViewModel extends BaseAndroidViewModel<StoreHouseNavigator> {

    private GoodsRepositroy repositroy;

    public StoreHouseViewModel(@NonNull Application application, GoodsRepositroy repositroy) {
        super(application);
        this.repositroy = repositroy;
    }

    public static class Factory implements ViewModelProvider.Factory {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass == StoreHouseViewModel.class) {
                return (T) new StoreHouseViewModel(App.getInstance(), Injection.provideGoodsRepositroy());
            }
            return null;
        }
    }
}