package com.example.jetpack.db.repository;

import com.example.jetpack.db.dao.GoodsDao;
import com.example.jetpack.util.AppExecutors;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class GoodsLocalDataSource implements GoodsDataSource {

    private static volatile GoodsLocalDataSource instance;

    public static GoodsLocalDataSource getInstance(AppExecutors appExecutors, GoodsDao goodsDao) {
        if (instance == null) {
            instance = new GoodsLocalDataSource(appExecutors, goodsDao);
        }
        return instance;
    }

    private AppExecutors appExecutors;
    private GoodsDao goodsDao;

    private GoodsLocalDataSource(AppExecutors appExecutors, GoodsDao goodsDao) {
        this.appExecutors = appExecutors;
        this.goodsDao = goodsDao;
    }

    @Override
    public void getAllGoods(GetGoodsCallBack callBack) {
        appExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                callBack.onGoodsLoad(goodsDao.getAllGoods());
            }
        });
    }
}
