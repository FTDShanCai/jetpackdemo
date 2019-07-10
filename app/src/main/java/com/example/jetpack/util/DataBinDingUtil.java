package com.example.jetpack.util;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.jetpack.App;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description: DataBinDing 扩展类
 */
public abstract class DataBinDingUtil implements androidx.databinding.DataBindingComponent {
    private static final String PATH_HEAD = "file:///android_asset/";

    @BindingAdapter({"android:src"})
    public static void setImageResource(ImageView imageView, String path) {
        Glide.with(imageView.getContext()).load(path).into(imageView);
    }

    @BindingAdapter({"android:src"})
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    public static Bitmap getAssetsBitmap(String path) {
        AssetManager am = App.getInstance().getAssets();
        InputStream inputStream = null;
        try {
            inputStream = am.open(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }
}
