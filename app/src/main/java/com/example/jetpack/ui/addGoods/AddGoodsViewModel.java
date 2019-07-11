package com.example.jetpack.ui.addGoods;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.jetpack.db.repository.GoodsDataSource;
import com.example.jetpack.db.repository.GoodsRepositroy;
import com.example.jetpack.entity.GoodsEntity;
import com.example.jetpack.ui.BaseAndroidViewModel;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class AddGoodsViewModel extends BaseAndroidViewModel<AddGoodsNavigator> {

    private GoodsRepositroy repositroy;
    private MutableLiveData<Boolean> isVisible = new MutableLiveData<>();
    private MutableLiveData<String> name = new MutableLiveData<>();
    private MutableLiveData<String> count = new MutableLiveData<>();
    private MutableLiveData<String> desc = new MutableLiveData<>();
    public MutableLiveData<String> imgPath = new MutableLiveData<>();

    public AddGoodsViewModel(@NonNull Application application, GoodsRepositroy repositroy) {
        super(application);
        this.repositroy = repositroy;
        isVisible.postValue(false);
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getCount() {
        return count;
    }

    public MutableLiveData<String> getDesc() {
        return desc;
    }

    public MutableLiveData<Boolean> getIsVisible() {
        return isVisible;
    }

    //    public MutableLiveData<String> getImgPath() {
//        return imgPath;
//    }
//
//    public void setImgPath(String imgPath) {
//        this.imgPath.setValue(imgPath);
//    }

    public void showPicChoiceDialog() {
        if (navigator != null) navigator.showPicChoiceDialog();
    }

    public void onSubmit() {
        if (TextUtils.isEmpty(name.getValue())) {
            toastMessage("请输入商品名称");
            return;
        }
        if (TextUtils.isEmpty(count.getValue())) {
            toastMessage("请输入商品库存");
            return;
        }
        if (TextUtils.isEmpty(desc.getValue())) {
            toastMessage("请输入商品介绍");
            return;
        }
        if (TextUtils.isEmpty(imgPath.getValue())) {
            toastMessage("请选择商品图片");
            return;
        }

        GoodsEntity entity = new GoodsEntity(name.getValue(), count.getValue(), desc.getValue(), imgPath.getValue());
        repositroy.insertGoods(entity, new GoodsDataSource.OnCompleteCallBack() {
            @Override
            public void onComplete() {
                toastMessage("保存成功");
                navigator.onSubmit();
            }
        });
    }

}
