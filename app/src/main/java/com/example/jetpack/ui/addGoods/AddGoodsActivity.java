package com.example.jetpack.ui.addGoods;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import com.example.jetpack.Constants;
import com.example.jetpack.R;
import com.example.jetpack.databinding.ActivityAddGoodsBinding;
import com.example.jetpack.ui.BaseDataBindingActivity;
import com.example.jetpack.ui.camerax.CarmeraXActivity;
import com.example.jetpack.util.MyLog;
import com.example.jetpack.util.PermissionUtil;
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
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setItems(new String[]{"网络资源", "本地图库", "相机"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            openNetWorkImg();
                        } else if (which == 1) {
                            openGallery();
                        } else if (which == 2) {
                            openCameraX();
                        }
                    }
                }).create();
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
        }
        alertDialog.show();

    }

    @Override
    public Intent getIntentData() {
        return getIntent();
    }

    void openNetWorkImg() {
        if (dialog == null) {
            dialog = new BottomChoicePicDialog();
            dialog.setCallBack(new BottomChoicePicDialog.CallBack() {
                @Override
                public void onChoice(String path) {
                    mViewModel.imgPath.setValue(path);
                }
            });
        }
        dialog.show(getSupportFragmentManager(), "pic");
    }

    void openGallery() {
        if (PermissionUtil.hasPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, Constants.RequestCode);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constants.RequestCode);
        }
    }

    void openCameraX() {
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (PermissionUtil.hasPermission(this, permissions)) {
            startActivityForResult(new Intent(this, CarmeraXActivity.class), Constants.RequestCamerax);
        } else {
            ActivityCompat.requestPermissions(this, permissions, Constants.RequestCode);
        }
    }

    @Override
    public void toastMessage(String message) {
        toast(message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RequestCode && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            if (selectedImage == null) return;
            String[] filePathColums = {MediaStore.Images.Media.DATA};
            Cursor cursor = null;
            try {
                cursor = getContentResolver().query(selectedImage, filePathColums, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    String imagePath = cursor.getString(cursor.getColumnIndex(filePathColums[0]));
                    MyLog.d(imagePath);
                    mViewModel.imgPath.setValue(imagePath);
                }
            } catch (Exception e) {

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        } else if (requestCode == Constants.RequestCamerax && resultCode == Constants.ResultRefresh && data != null) {
            String path = data.getStringExtra(Constants.Key.PATH);
            mViewModel.imgPath.setValue(path);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == Constants.RequestCode) {
            boolean isHasPermission = true;
            for (int a : grantResults) {
                if (a == PackageManager.PERMISSION_DENIED) {
                    isHasPermission = false;
                }
            }

            if (!isHasPermission) {
                toast("请开启相关权限");
            }
        }

    }
}
