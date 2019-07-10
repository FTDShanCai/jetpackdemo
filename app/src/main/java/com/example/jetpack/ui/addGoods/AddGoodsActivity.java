package com.example.jetpack.ui.addGoods;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.jetpack.Constants;
import com.example.jetpack.R;
import com.example.jetpack.databinding.ActivityAddGoodsBinding;
import com.example.jetpack.ui.BaseDataBindingActivity;
import com.example.jetpack.util.widget.dialog.BottomChoicePicDialog;

public class AddGoodsActivity extends BaseDataBindingActivity<ActivityAddGoodsBinding, AddGoodsViewModel> implements AddGoodsNavigator {

    private BottomChoicePicDialog dialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_goods;
    }

    @Override
    public AddGoodsViewModel getViewModel() {
        AddGoodsViewModel viewModel = ViewModelProviders.of(this, new AddGoodsViewModelFactory()).get(AddGoodsViewModel.class);
        viewModel.onViewCrate(this);
        return viewModel;
    }

    @Override
    public void initViewModel(@Nullable Bundle savedInstanceState) {
        mDataBinding.setModel(mViewModel);
    }

    @Override
    public void onSubmit() {
        setResult(Constants.ResultRefresh);
        finish();
    }

    @Override
    public void showPicChoiceDialog() {
        if (dialog == null) {
            dialog = new BottomChoicePicDialog();
            dialog.setCallBack(new BottomChoicePicDialog.CallBack() {
                @Override
                public void onChoice(String path) {
                    mViewModel.setImgPath(path);
                }
            });
        }
        dialog.show(getSupportFragmentManager(), "pic");
    }

    @Override
    public void toastMessage(String message) {
        toast(message);
    }
}
