package com.tunyin.ui.adapter;


import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.meis.base.mei.adapter.MeiBaseAdapter;
import com.tunyin.MyPlayService;
import com.tunyin.R;
import com.tunyin.entity.AnchorDetailItemEntity;
import com.tunyin.ui.activity.index.PlayerActivity;
import com.tunyin.utils.ImageUtil;
import com.tunyin.widget.RatioRoundImageView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AnchorDetailAdapter extends MeiBaseAdapter<AnchorDetailItemEntity> {

    public AnchorDetailAdapter() {
        super(R.layout.item_pay_stuff_ac);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable AnchorDetailItemEntity payStuffEntity) {

        RatioRoundImageView ivImage = baseViewHolder.getView(R.id.image);
        ImageUtil.load(payStuffEntity.image).on(ivImage);
        baseViewHolder.setText(R.id.tv_title, payStuffEntity.title);
        baseViewHolder.setText(R.id.tv_sub_title, payStuffEntity.content);
        baseViewHolder.setText(R.id.tv_diamond, MyPlayService.isFree(payStuffEntity.price + "") ? "免费" : "钻石");
        baseViewHolder.setText(R.id.tv_diamond_num, MyPlayService.getPrice(payStuffEntity.price + ""));
        baseViewHolder.itemView.setOnClickListener(v -> {
            v.getContext().startActivity(PlayerActivity.newInstance(v.getContext(), payStuffEntity.id + ""));
        });
    }
}
