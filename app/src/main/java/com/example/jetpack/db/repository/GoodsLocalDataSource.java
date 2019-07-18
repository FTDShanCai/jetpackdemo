package com.example.jetpack.db.repository;

import com.example.jetpack.db.dao.GoodsDao;
import com.example.jetpack.entity.GoodsEntity;
import com.example.jetpack.util.AppExecutors;

import javax.inject.Inject;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class GoodsLocalDataSource implements GoodsDataSource {

    private static volatile GoodsLocalDataSource instance;

    public static GoodsLocalDataSource getInstance(GoodsDao goodsDao) {
        if (instance == null) {
            instance = new GoodsLocalDataSource(goodsDao);
        }
        return instance;
    }

    @Inject
    AppExecutors appExecutors;

    GoodsDao goodsDao;

    private GoodsLocalDataSource(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public void getAllGoods(GetGoodsCallBack callBack) {
        appExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    appExecutors.getMainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onGoodsLoad(goodsDao.getAllGoods());
                        }
                    });
                }
            }
        });
    }

    @Override
    public void insertGoods(GoodsEntity entity, OnCompleteCallBack callBack) {
        appExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                goodsDao.insertGoods(entity);
                if (callBack != null) {
                    appExecutors.getMainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onComplete();
                        }
                    });
                }
            }
        });
    }
}
