package com.example.jetpack.util.widget.dialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;


import com.example.jetpack.R;


public abstract class BaseDialog<T extends ViewDataBinding> extends DialogFragment {

    protected T mDataBinDing;
    private int theme = -1;


    public BaseDialog() {
    }

    public BaseDialog(int theme) {
        this.theme = theme;
    }


    public abstract @LayoutRes
    int getLayoutId();

    public abstract void init();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (theme != -1) {
            setStyle(DialogFragment.STYLE_NO_TITLE, theme);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinDing = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return mDataBinDing.getRoot();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().setCanceledOnTouchOutside(true);

        // 设置宽度为屏宽、位置靠近屏幕底部
        Window window = getDialog().getWindow();
        window.setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        init();
    }


}
