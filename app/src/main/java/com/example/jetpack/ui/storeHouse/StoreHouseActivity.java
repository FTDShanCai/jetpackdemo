package com.example.jetpack.ui.storeHouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetpack.Constants;
import com.example.jetpack.R;
import com.example.jetpack.adapter.BaseDataBindingAdapter;
import com.example.jetpack.adapter.StoreHouseAdapter;
import com.example.jetpack.databinding.ActivityStoreHouseBinding;
import com.example.jetpack.entity.GoodsEntity;
import com.example.jetpack.ui.BaseDataBindingActivity;
import com.example.jetpack.ui.addGoods.AddGoodsActivity;

import java.util.List;

import javax.inject.Inject;

public class StoreHouseActivity extends BaseDataBindingActivity<ActivityStoreHouseBinding, StoreHouseViewModel> implements StoreHouseNavigator {
    StoreHouseAdapter adapter;

    @Inject

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_house;
    }

    @Override
    public StoreHouseViewModel getViewModel() {
        StoreHouseViewModel viewModel = ViewModelProviders.of(this, new StoreHouseViewModelFactory()).get(StoreHouseViewModel.class);
        viewModel.onViewCrate(this);
        return viewModel;
    }

    @Override
    public void initViewModel(@Nullable Bundle savedInstanceState) {
        mDataBinding.setModel(mViewModel);
        adapter = new StoreHouseAdapter();
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mDataBinding.recyclerView.setAdapter(adapter);
        initDatas();
    }

    private void initDatas() {
        adapter.setOnItemClickListener(new BaseDataBindingAdapter.OnItemClickListener<GoodsEntity>() {
            @Override
            public void onItemClick(View view, int position, GoodsEntity entity) {
                goAddGoods();
            }
        });

        mViewModel.getGoods().observe(this, new Observer<List<GoodsEntity>>() {
            @Override
            public void onChanged(List<GoodsEntity> entities) {
                adapter.setNewData(entities);
            }
        });
        mViewModel.refreshGoods();
    }

    @Override
    public void goAddGoods() {
        startActivityForResult(new Intent(this, AddGoodsActivity.class), Constants.RequestCode);
    }

    @Override
    public void toastMessage(String message) {
        toast(message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RequestCode && resultCode == Constants.ResultRefresh) {
            mViewModel.refreshGoods();
        }
    }

}
