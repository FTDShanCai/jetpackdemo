package com.example.jetpack.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.jetpack.R;
import com.example.jetpack.databinding.ActivityMainBinding;
import com.example.jetpack.ui.BaseDataBindingActivity;
import com.example.jetpack.ui.storeHouse.StoreHouseActivity;

public class MainActivity extends BaseDataBindingActivity<ActivityMainBinding, MainViewModel> implements MainNavigator {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.onViewCrate(this);
        return viewModel;
    }

    @Override
    public void initViewModel(@Nullable Bundle savedInstanceState) {
        mDataBinding.setModel(mViewModel);
    }

    @Override
    public void goStoreHouse() {
        startActivity(new Intent(this, StoreHouseActivity.class));
    }

    @Override
    public void goStoreMenu() {

    }
}
