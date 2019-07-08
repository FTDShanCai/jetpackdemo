package com.example.jetpack.ui.storeHouse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.ArrayMap;

import com.example.jetpack.R;
import com.example.jetpack.adapter.StoreHouseAdapter;
import com.example.jetpack.databinding.ActivityStoreHouseBinding;
import com.example.jetpack.entity.GoodsEntity;
import com.example.jetpack.ui.BaseDataBindingActivity;

import java.util.ArrayList;

public class StoreHouseActivity extends BaseDataBindingActivity<ActivityStoreHouseBinding, StoreHouseViewModel> implements StoreHouseNavigator {
    private StoreHouseAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_house;
    }

    @Override
    public StoreHouseViewModel getViewModel() {
        StoreHouseViewModel viewModel = ViewModelProviders.of(this, new StoreHouseViewModel.Factory()).get(StoreHouseViewModel.class);
        viewModel.onViewCrate(this);
        return viewModel;
    }

    @Override
    public void initViewModel(@Nullable Bundle savedInstanceState) {
        mDataBinding.setModel(mViewModel);
        adapter = new StoreHouseAdapter();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        initDatas();
    }

    private void initDatas() {
        ArrayList<GoodsEntity> entities = new ArrayList<>();
        entities.add(new GoodsEntity("鸡肉堡", 19, "鸡腿肉，生菜，番茄酱，沙拉酱等材料", R.mipmap.goods1));
        entities.add(new GoodsEntity("鸡肉堡1", 10, "鸡腿肉，生菜，番茄酱，沙拉酱等材料", R.mipmap.goods1));
        entities.add(new GoodsEntity("鸡肉堡2", 1, "鸡腿肉，生菜，番茄酱，沙拉酱等材料", R.mipmap.goods1));
        entities.add(new GoodsEntity("鸡肉堡3", 119, "鸡腿肉，生菜，番茄酱，沙拉酱等材料", R.mipmap.goods1));
        entities.add(new GoodsEntity("鸡肉堡4", 139, "鸡腿肉，生菜，番茄酱，沙拉酱等材料", R.mipmap.goods1));
        entities.add(new GoodsEntity("鸡肉堡5", 23, "鸡腿肉，生菜，番茄酱，沙拉酱等材料", R.mipmap.goods1));
        adapter.setNewData(entities);
    }
}
