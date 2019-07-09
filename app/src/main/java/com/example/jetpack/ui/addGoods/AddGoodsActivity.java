package com.example.jetpack.ui.addGoods;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.jetpack.R;
import com.example.jetpack.databinding.ActivityAddGoodsBinding;
import com.example.jetpack.ui.BaseDataBindingActivity;

public class AddGoodsActivity extends BaseDataBindingActivity<ActivityAddGoodsBinding, AddGoodsViewModel> implements AddGoodsNavigator {

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_goods;
    }

    @Override
    public AddGoodsViewModel getViewModel() {
        AddGoodsViewModel viewModel = ViewModelProviders.of(this).get(AddGoodsViewModel.class);
        viewModel.onViewCrate(this);
        return viewModel;
    }

    @Override
    public void initViewModel(@Nullable Bundle savedInstanceState) {
        mDataBinding.setModel(mViewModel);
    }
}
