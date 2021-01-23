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
    public void queryGoods(long id, QueryGoodsCallBack callBack) {
        localDataSource.queryGoods(id, callBack);
    }

    @Override
    public void insertOrUpdateGoods(OnCompleteCallBack callBack, GoodsEntity... entity) {
        localDataSource.insertOrUpdateGoods(callBack, entity);
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
