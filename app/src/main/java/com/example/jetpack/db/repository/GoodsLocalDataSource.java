package com.example.jetpack.db.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.jetpack.db.dao.GoodsDao;
import com.example.jetpack.entity.GoodsEntity;
import com.example.jetpack.util.AppExecutors;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class GoodsLocalDataSource implements GoodsDataSource {

    private static volatile GoodsLocalDataSource instance;

    public static GoodsLocalDataSource getInstance(GoodsDao goodsDao) {
        if (instance == null) {
            instance = new GoodsLocalDataSource(goodsDao, new AppExecutors());
        }
        return instance;
    }

    AppExecutors appExecutors;

    GoodsDao goodsDao;

    private GoodsLocalDataSource(GoodsDao goodsDao, AppExecutors appExecutors) {
        this.goodsDao = goodsDao;
        this.appExecutors = appExecutors;
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
    public void queryGoods(long id, QueryGoodsCallBack callBack) {
        appExecutors.getDiskIO().execute(() -> {
            GoodsEntity goodsEntity = goodsDao.queryGoods(id);
            if (callBack != null) {
                appExecutors.getMainThread().execute(() -> callBack.onGoodsLoad(goodsEntity));
            }
        });
    }

    @SuppressLint("LogConditional")
    @Override
    public void insertOrUpdateGoods(OnCompleteCallBack callBack, GoodsEntity... entity) {
        appExecutors.getDiskIO().execute(() -> {
            goodsDao.insertOrUpdate(entity);
            if (callBack != null) {
                appExecutors.getMainThread().execute(callBack::onComplete);
            }
        });
    }

}
