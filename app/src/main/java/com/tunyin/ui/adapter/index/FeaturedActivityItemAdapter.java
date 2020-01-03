package com.tunyin.ui.adapter.index;

import android.view.View;
import android.widget.TextView;

import com.tunyin.R;
import com.tunyin.base.BaseAdapter;
import com.tunyin.base.BaseViewHolder;
import com.tunyin.mvp.model.index.IndexEntity;
import com.tunyin.utils.ImageUtil;
import com.tunyin.widget.CustomRoundAngleImageView;

public class FeaturedActivityItemAdapter extends BaseAdapter<IndexEntity.FeaturedActivityBean.ListBeanXXX> {
    @Override
    protected int layoutId() {
        return R.layout.item_recommend_activity;
    }

    @Override
    protected BaseViewHolder getViewHolder(View view) {
        return new BroadcastItemViewHolder(view);
    }
    class BroadcastItemViewHolder extends BaseViewHolder<IndexEntity.FeaturedActivityBean.ListBeanXXX>{

        TextView tvTitle;
        TextView tvSubTiele;
        CustomRoundAngleImageView imageView;
        public BroadcastItemViewHolder(View itemView) {
            super(itemView);
            imageView=bindView(R.id.image);
            tvTitle=bindView(R.id.tv_title);
            tvSubTiele=bindView(R.id.tv_sub_title);
        }

        @Override
        protected void bindData(IndexEntity.FeaturedActivityBean.ListBeanXXX listBean) {
            ImageUtil.load(listBean.getImage()).on(imageView);
            tvTitle.setText(listBean.getTitle());
            tvSubTiele.setText(listBean.getContent());

        }
    }
}
