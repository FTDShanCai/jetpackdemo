package com.example.jetpack.db;

import com.example.jetpack.db.dao.GoodsDao;
import com.example.jetpack.db.repository.GoodsLocalDataSource;
import com.example.jetpack.db.repository.GoodsRepositroy;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class Injection {

    public static GoodsRepositroy provideGoodsRepositroy() {
        GoodsDao goodsDao = StoreDataBase.getInstance().goodsDao();
        return GoodsRepositroy.getInstance(GoodsLocalDataSource.getInstance(goodsDao));
    }
}
