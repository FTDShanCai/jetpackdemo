package com.example.jetpack.util.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class RectImageView extends AppCompatImageView {
    public RectImageView(Context context) {
        this(context, null);
    }

    public RectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RectImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
