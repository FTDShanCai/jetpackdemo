package com.example.jetpack.util;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description: DataBinDing 扩展类
 */
public abstract class DataBinDingUtil implements androidx.databinding.DataBindingComponent {
    private static final String PATH_HEAD = "file:///android_asset/";

    @BindingAdapter({"android:src"})
    public static void setImageResource(ImageView imageView, String path) {
        Log.d("ftd", "setImageResource  path:" + path);
        Glide.with(imageView.getContext()).load(new File(PATH_HEAD + path)).into(imageView);
    }

    @BindingAdapter({"android:src"})
    public static void setImageResource(ImageView imageView, int resource) {
        Log.d("ftd", "setImageResource  resource:" + resource);
        imageView.setImageResource(resource);
    }

}
