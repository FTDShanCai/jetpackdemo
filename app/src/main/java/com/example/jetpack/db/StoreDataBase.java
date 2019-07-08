package com.example.jetpack.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.jetpack.App;
import com.example.jetpack.db.dao.GoodsDao;
import com.example.jetpack.entity.GoodsEntity;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
@Database(entities = {GoodsEntity.class}, version = 1, exportSchema = false)
public abstract class StoreDataBase extends RoomDatabase {
    private static final String DB_NAME = "db_jetpack";

    public abstract GoodsDao goodsDao();

    private static StoreDataBase dataBase;

    public static StoreDataBase getInstance() {
        if (dataBase == null) {
            dataBase = Room.databaseBuilder(App.getInstance(), StoreDataBase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return dataBase;
    }
}
