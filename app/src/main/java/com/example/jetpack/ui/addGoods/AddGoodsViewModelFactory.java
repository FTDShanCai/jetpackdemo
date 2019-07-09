package com.example.jetpack.ui.addGoods;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.jetpack.App;
import com.example.jetpack.db.Injection;
import com.example.jetpack.ui.storeHouse.StoreHouseViewModel;

public class AddGoodsViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AddGoodsViewModel.class)) {
            return (T) new AddGoodsViewModel(App.getInstance(), Injection.provideGoodsRepositroy());
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
