package com.example.jetpack.util;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.jetpack.App;

import java.io.File;
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

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        MyLog.d("loadImage:" + url);
        if (!TextUtils.isEmpty(url) && url.contains(Environment.getExternalStorageDirectory().getPath())) {
            Glide.with(view.getContext()).load(new File(url)).error(error).into(view);
        } else {
            Glide.with(view.getContext()).load(url).error(error).into(view);
        }
    }

    @BindingAdapter({"android:src"})
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter("goneUnless")
    public static void goneUnless(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
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
