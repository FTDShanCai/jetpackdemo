package com.example.jetpack.ui.storeHouse;

import android.app.Application;

import androidx.annotation.NonNull;

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


    public void goAddGoods() {
        if (navigator != null) navigator.goAddGoods();
    }
}
