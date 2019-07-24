package com.example.jetpack.db.repository;

import com.example.jetpack.entity.GoodsEntity;

import java.util.List;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public interface GoodsDataSource {

    interface GetGoodsCallBack {
        void onGoodsLoad(List<GoodsEntity> goods);
    }

    interface OnCompleteCallBack {
        void onComplete();
    }

    void getAllGoods(GetGoodsCallBack callBack);

    void insertGoods(GoodsEntity entity, OnCompleteCallBack callBack);
}
