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

    public static List<String> getNetWorkUrl() {
        List<String> list = new ArrayList<>();
        list.add("http://img1.imgtn.bdimg.com/it/u=208532097,4227169484&fm=15&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=1869011853,3735550413&fm=15&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=2175665729,1727460511&fm=26&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=1359570704,731780530&fm=15&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=3839661167,4119209699&fm=26&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=169046109,2870070683&fm=15&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=1615032259,2399987390&fm=15&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=2036882323,1704468027&fm=26&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=2870129924,1040807016&fm=15&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=3671665015,4044788236&fm=26&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=2279842874,3654986650&fm=15&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=523379634,2370942957&fm=15&gp=0.jpg");
        return list;
    }
}
