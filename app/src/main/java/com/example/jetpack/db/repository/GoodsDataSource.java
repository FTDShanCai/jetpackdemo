package com.example.jetpack.db.repository;

import androidx.lifecycle.LiveData;

import com.example.jetpack.entity.GoodsEntity;

import java.util.List;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public interface GoodsDataSource {

    interface GetGoodsCallBack {
        void onGoodsLoad(LiveData<List<GoodsEntity>> goods);
    }

    void getAllGoods(GetGoodsCallBack callBack);
}