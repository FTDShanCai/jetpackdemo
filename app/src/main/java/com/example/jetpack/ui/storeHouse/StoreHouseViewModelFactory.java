package com.example.jetpack.ui.storeHouse;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.jetpack.App;
import com.example.jetpack.db.Injection;

public class StoreHouseViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StoreHouseViewModel.class)) {
            return (T) new StoreHouseViewModel(App.getInstance(), Injection.provideGoodsRepositroy());
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
