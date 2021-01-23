package com.example.jetpack.ui.addGoods;

import android.content.Intent;

import com.example.jetpack.ui.BaseNavigator;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public interface AddGoodsNavigator extends BaseNavigator {

    void onSubmit();

    void showPicChoiceDialog();

    Intent getIntentData();
}
