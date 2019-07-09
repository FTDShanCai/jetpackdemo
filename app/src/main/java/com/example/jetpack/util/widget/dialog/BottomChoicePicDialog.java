package com.example.jetpack.util.widget.dialog;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetpack.R;
import com.example.jetpack.adapter.BaseDataBindingAdapter;
import com.example.jetpack.adapter.BottomChoicePicAdapter;
import com.example.jetpack.databinding.DialogBottomChoicePicBinding;
import com.example.jetpack.databinding.ItemBottomChoicePicBinding;
import com.example.jetpack.util.Tools;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class BottomChoicePicDialog extends BaseDialog<DialogBottomChoicePicBinding> {
    private BottomChoicePicAdapter adapter = new BottomChoicePicAdapter();

    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public interface CallBack {
        void onChoice(String path);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_bottom_choice_pic;
    }

    @Override
    public void init() {
        mDataBinDing.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mDataBinDing.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseDataBindingAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, int position, String s) {
                if (callBack != null) callBack.onChoice(s);
            }
        });
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                emitter.onNext(Tools.getAssertPics());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        adapter.setNewData(strings);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }


}
