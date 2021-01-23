package com.example.jetpack.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
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
public abstract class GoodsDao {

    @Query("select * from store_goods")
    public abstract List<GoodsEntity> getAllGoods();

    @Query("select * from store_goods where id == :id")
    public abstract GoodsEntity queryGoods(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertGoods(GoodsEntity entity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract int updateGoods(GoodsEntity... entity);

    public void insertOrUpdate(GoodsEntity... entities) {
        for (GoodsEntity entity : entities) {
            GoodsEntity goodsEntity = queryGoods(entity.getId());
            if (goodsEntity != null) {
                updateGoods(entity);
            } else {
                insertGoods(entity);
            }
        }
    }


    @Delete
    public abstract int delete(GoodsEntity... entity);

    @Query("delete from store_goods")
    public abstract void deleteAllGoods();

}
