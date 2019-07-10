package com.example.jetpack.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.jetpack.entity.GoodsEntity;

import java.util.List;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */

@Dao
public interface GoodsDao {

    @Query("select * from store_goods")
    List<GoodsEntity> getAllGoods();

    @Query("select * from store_goods where goodsName like :Name")
    List<GoodsEntity> queryGoods(String Name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGoods(GoodsEntity entity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateGoods(GoodsEntity... entity);

//    @DELETE  //这样写就会报错
//    void delete(GoodsEntity entity);

    @Query("delete from store_goods")
    void deleteAllGoods();

}
