package com.example.jetpack.entity;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import java.math.BigDecimal;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class StockLiveData extends LiveData<BigDecimal> {

    LiveData<String> stringLiveData = new MediatorLiveData<>();
    LiveData<Double> doubleLiveData = new MediatorLiveData<>();

    MediatorLiveData<String> mediatorLiveData = new MediatorLiveData<>();

    @Override
    protected void onActive() {
        super.onActive();

        mediatorLiveData.addSource(stringLiveData, new Observer<String>() {
            @Override
            public void onChanged(String s) {
            }
        });
        mediatorLiveData.addSource(doubleLiveData, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {

            }
        });

    }
}
