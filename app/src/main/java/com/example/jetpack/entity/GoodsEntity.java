package com.example.jetpack.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.jetpack.R;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
@Entity(tableName = "store_goods")
public class GoodsEntity {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String goodsName;
    private String goodsCount;
    private String goodsDesc;
    private String goodsImg;

    public GoodsEntity(String goodsName, String goodsCount, String goodsDesc, String goodsImg) {
        this.goodsName = goodsName;
        this.goodsCount = goodsCount;
        this.goodsDesc = goodsDesc;
        this.goodsImg = goodsImg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName == null ? "" : goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getGoodsDesc() {
        return goodsDesc == null ? "" : goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }
}
