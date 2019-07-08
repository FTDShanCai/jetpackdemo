package com.example.jetpack.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public abstract class BaseDataBindingAdapter<T, D extends ViewDataBinding> extends RecyclerView.Adapter<BaseDataBindingAdapter.BaseViewHolder> {
    private ArrayList<T> mDatas;
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    private OnItemClickListener<T> onItemClickListener;

    public BaseDataBindingAdapter() {
        this.mDatas = new ArrayList<>();
    }

    public BaseDataBindingAdapter(ArrayList<T> mDatas) {
        if (mDatas == null) mDatas = new ArrayList<>();
        this.mDatas = mDatas;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mContext == null)
            mContext = parent.getContext();
        if (mLayoutInflater == null)
            mLayoutInflater = LayoutInflater.from(mContext);

        View view = mLayoutInflater.inflate(getLayoutId(), parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BaseDataBindingAdapter.BaseViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener == null)
                    return;
                onItemClickListener.onItemClick(holder.itemView, position, mDatas.get(position));
            }
        });

        bindData(holder, position, mDatas.get(position));
    }


    /**
     * 刷新数据
     *
     * @param datas
     */
    public void setNewData(ArrayList<T> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }


    /**
     * 添加数据
     *
     * @param datas
     */
    public void addData(ArrayList<T> datas) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        this.mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void addData(T data) {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        this.mDatas.add(data);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        this.mDatas.remove(position);
        notifyDataSetChanged();
    }


    /**
     * 绑定数据
     *
     * @param holder   具体的viewHolder
     * @param position 对应的索引
     */
    protected abstract void bindData(BaseViewHolder holder, int position, T t);

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }


    /**
     * 封装ViewHolder ,子类可以直接使用
     */
    public class BaseViewHolder extends RecyclerView.ViewHolder {
        protected D mBinding;

        private SparseArray<View> mViewArray;

        public BaseViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
            mViewArray = new SparseArray<>();
        }

        /**
         * 获取设置的view
         *
         * @param id
         * @return
         */
        public View getView(int id) {
            View view = mViewArray.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mViewArray.put(id, view);
            }
            return view;
        }
    }

    /**
     * 获取子item
     *
     * @return
     */
    public abstract int getLayoutId();


    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public ArrayList<T> getDatas() {
        return mDatas;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, int position, T t);
    }
}
