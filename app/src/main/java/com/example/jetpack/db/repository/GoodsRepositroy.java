package com.example.jetpack.db.repository;

import com.example.jetpack.entity.GoodsEntity;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class GoodsRepositroy implements GoodsDataSource {

    private GoodsDataSource localDataSource;

    @Override
    public void getAllGoods(GetGoodsCallBack callBack) {
        localDataSource.getAllGoods(callBack);
    }

    @Override
    public void insertGoods(GoodsEntity entity, OnCompleteCallBack callBack) {
        localDataSource.insertGoods(entity,callBack);
    }


    private static GoodsRepositroy instance;

    public static GoodsRepositroy getInstance(GoodsDataSource localDataSource) {
        if (instance == null) {
            instance = new GoodsRepositroy(localDataSource);
        }
        return instance;
    }

    private GoodsRepositroy(GoodsDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

}
