package com.example.jetpack.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.jetpack.R;
import com.example.jetpack.databinding.ItemStoreHouseBinding;
import com.example.jetpack.entity.GoodsEntity;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class StoreHouseAdapter extends BaseDataBindingAdapter<GoodsEntity, ItemStoreHouseBinding> {

    @Override
    public void bindData(BaseViewHolder holder, int position, GoodsEntity entity) {
        holder.mBinding.setBean(entity);
    }

    @BindingAdapter({"android:src"})
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_store_house;
    }
}
