package com.example.jetpack.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.jetpack.entity.GoodsEntity;

import java.util.List;

import retrofit2.http.DELETE;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */

@Dao
public interface GoodsDao {

    @Query("select * from store_goods")
    LiveData<List<GoodsEntity>> getAllGoods();

    @Query("select * from store_goods where goodsName like :Name")
    LiveData<List<GoodsEntity>> queryGoods(String Name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGoods(GoodsEntity entity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateGoods(GoodsEntity... entity);

    @DELETE
    void deleteAllGoods();
}
