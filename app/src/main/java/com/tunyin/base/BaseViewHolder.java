package com.tunyin.base;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by WYiang on 2017/10/25.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void bindData(T t);

    public <T extends View> T bindView(@IdRes int id) {
        return itemView.findViewById(id);
    }

    public <T extends View> T bindView(@IdRes int id, View.OnClickListener listener) {
        T view = itemView.findViewById(id);
        view.setOnClickListener(listener);
        return view;
    }
}
