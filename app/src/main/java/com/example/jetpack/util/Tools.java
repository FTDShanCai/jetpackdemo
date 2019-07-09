package com.example.jetpack.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.jetpack.App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class Tools {

    private Tools() {

    }


    public static List<String> getAssertPics() {
        AssetManager am = App.getInstance().getAssets();
        String[] path = null;
        try {
            path = am.list("");  // ""获取所有,填入目录获取该目录下所有资源
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> pciPaths = new ArrayList<>();
        if (path != null) {
            for (String s : path) {
                if (s.endsWith(".png") || s.endsWith(".jpg")) {  // 根据图片特征找出图片
                    pciPaths.add(s);
                }
            }
        }
        return pciPaths;
    }
}
