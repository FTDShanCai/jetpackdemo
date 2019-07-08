package com.example.jetpack.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public abstract class BaseDataBindingActivity<T extends ViewDataBinding, M extends BaseAndroidViewModel> extends AppCompatActivity {

    protected T mDataBinding;

    protected M mViewModel;

    public abstract int getLayoutId();

    public abstract M getViewModel();

    public abstract void initViewModel(@Nullable Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mDataBinding.setLifecycleOwner(this);
        mViewModel = getViewModel();
        initViewModel(savedInstanceState);
    }
}
