package com.example.jetpack.ui.storeHouse;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.jetpack.db.repository.GoodsDataSource;
import com.example.jetpack.db.repository.GoodsRepositroy;
import com.example.jetpack.entity.GoodsEntity;
import com.example.jetpack.ui.BaseAndroidViewModel;

import java.util.List;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class StoreHouseViewModel extends BaseAndroidViewModel<StoreHouseNavigator> {

    private GoodsRepositroy repositroy;

    MutableLiveData<List<GoodsEntity>> goods = new MutableLiveData<>();

    public StoreHouseViewModel(@NonNull Application application, GoodsRepositroy repositroy) {
        super(application);
        this.repositroy = repositroy;
    }

    public MutableLiveData<List<GoodsEntity>> getGoods() {
        return goods;
    }

    public void goAddGoods() {
        if (navigator != null) navigator.goAddGoods();
    }

    public void refreshGoods() {
        repositroy.getAllGoods(new GoodsDataSource.GetGoodsCallBack() {
            @Override
            public void onGoodsLoad(List<GoodsEntity> list) {
                goods.setValue(list);
            }
        });
    }
}
