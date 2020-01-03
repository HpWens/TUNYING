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

public class BroadcastItemAdapter extends BaseAdapter<DiscoveryEntity.BroadcastBean.ListBean> {
    @Override
    protected int layoutId() {
        return R.layout.item_radio_serial;
    }

    @Override
    protected BaseViewHolder getViewHolder(View view) {
        return new BroadcastItemViewHolder(view);
    }

    class BroadcastItemViewHolder extends BaseViewHolder<DiscoveryEntity.BroadcastBean.ListBean> {

        TextView tvTitle;
        TextView tvNum;
        CustomRoundAngleImageView imageView;

        public BroadcastItemViewHolder(View itemView) {
            super(itemView);
            tvTitle = bindView(R.id.tv_title);
            tvNum = bindView(R.id.tv_num);
            imageView = bindView(R.id.image);
        }

        @Override
        protected void bindData(final DiscoveryEntity.BroadcastBean.ListBean listBean) {
            ImageUtil.load(listBean.getImage()).on(imageView);
            tvNum.setText(listBean.getCountView());
            tvTitle.setText(listBean.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = PlayerActivity.newInstance(itemView.getContext(), listBean.getId());
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }
}
