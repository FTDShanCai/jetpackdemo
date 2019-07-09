package com.example.jetpack.util.widget.popupwidow;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.LayoutRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;


import com.example.jetpack.R;


/**
 * Created by Administrator on 2018/11/19.
 */

public abstract class BasePopupWindow<T extends ViewDataBinding> extends PopupWindow {
    protected T mDataBinding;
    protected Context context;
    protected View root_view;
    protected View content_view;
    protected ViewStub view_stub;

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract void init();

    public BasePopupWindow(Context context) {
        super(context);
        this.context = context;
        initPopupWindow();
    }

    private void initPopupWindow() {
        WindowManager wm = (WindowManager) context.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        setWidth(width);
        setBgDrawable();
        setOutsideTouchable(true);
        root_view = LayoutInflater.from(context).inflate(R.layout.layout_base_popupwindow, null);
        setContentView(root_view);
        view_stub = root_view.findViewById(R.id.view_stub);
        view_stub.setLayoutResource(getLayoutId());
        content_view = view_stub.inflate();
        mDataBinding = DataBindingUtil.bind(content_view);
        root_view.findViewById(R.id.view_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowing()) {
                    dismiss();
                }
            }
        });
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setAnimationStyle(R.style.mypopwindow_anim_style);
        init();
    }

    protected void setBgDrawable() {
        setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.trans));
    }

    public void safeDismiss() {
        if (isShowing()) {
            dismiss();
        }
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        if (Build.VERSION.SDK_INT == 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor, xoff, yoff);
    }

}
