package com.tunyin.ui.activity.index;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.tunyin.base.MeiBaseAdapter;
import com.tunyin.mvp.model.index.PayStaffBannerEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PayStuffAdapter extends MeiBaseAdapter<PayStaffBannerEntity.ListBean> {

    public PayStuffAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable PayStaffBannerEntity.ListBean listBean) {

    }
}
