package com.example.jetpack.adapter;

import com.example.jetpack.R;
import com.example.jetpack.databinding.ItemBottomChoicePicBinding;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */

public class BottomChoicePicAdapter extends BaseDataBindingAdapter<String, ItemBottomChoicePicBinding> {
    @Override
    protected void bindData(BaseViewHolder holder, int position, String s) {
        holder.mBinding.setMPath(s);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_bottom_choice_pic;
    }



}