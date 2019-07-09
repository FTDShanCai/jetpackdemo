package com.example.jetpack.adapter;

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

    @Override
    public int getLayoutId() {
        return R.layout.item_store_house;
    }
}
