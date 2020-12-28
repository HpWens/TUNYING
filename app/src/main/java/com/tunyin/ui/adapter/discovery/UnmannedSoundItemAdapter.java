package com.tunyin.ui.adapter.discovery;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.tunyin.R;
import com.tunyin.base.BaseAdapter;
import com.tunyin.base.BaseViewHolder;
import com.tunyin.mvp.model.discovery.DiscoveryEntity;
import com.tunyin.ui.activity.index.PlayerActivity;
import com.tunyin.utils.ImageUtil;
import com.tunyin.widget.CustomRoundAngleImageView;

public class UnmannedSoundItemAdapter extends BaseAdapter<DiscoveryEntity.UnmannedSoundBean.ListBeanX> {
    @Override
    protected int layoutId() {
        return R.layout.item_radio_serial_pro;
    }

    @Override
    protected BaseViewHolder getViewHolder(View view) {
        return new BroadcastItemViewHolder(view);
    }

    class BroadcastItemViewHolder extends BaseViewHolder<DiscoveryEntity.UnmannedSoundBean.ListBeanX> {

        TextView tvTitle;
        TextView tvNum;
        CustomRoundAngleImageView imageView;

        public BroadcastItemViewHolder(View itemView) {
            super(itemView);
            tvTitle = bindView(R.id.tv_title);
            tvNum = bindView(R.id.tv_num);
            imageView = bindView(R.id.iv_bg);
        }

        @Override
        protected void bindData(final DiscoveryEntity.UnmannedSoundBean.ListBeanX listBean) {
            ImageUtil.load(listBean.getImage()).on(imageView);
            tvNum.setText(listBean.getCountView());
            tvTitle.setText(listBean.getTitle());
        }
    }
}
