package com.tunyin.ui.adapter.index;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.tunyin.R;
import com.tunyin.base.BaseAdapter;
import com.tunyin.base.BaseViewHolder;
import com.tunyin.mvp.model.index.IndexEntity;
import com.tunyin.ui.activity.index.PlayerActivity;
import com.tunyin.utils.ImageUtil;
import com.tunyin.widget.CustomRoundAngleImageView;

public class GuessLikeItemAdapter extends BaseAdapter<IndexEntity.GuessLikeBean.ListBeanXX> {
    @Override
    protected int layoutId() {
        return R.layout.item_guess_like;
    }

    @Override
    protected BaseViewHolder getViewHolder(View view) {
        return new BroadcastItemViewHolder(view);
    }
    class BroadcastItemViewHolder extends BaseViewHolder<IndexEntity.GuessLikeBean.ListBeanXX>{

        TextView tvTitle;
        TextView tvNum;
        CustomRoundAngleImageView imageView;
        public BroadcastItemViewHolder(View itemView) {
            super(itemView);
            tvTitle=bindView(R.id.tv_title_guess_like);
            imageView=bindView(R.id.image);
            tvNum=bindView(R.id.tv_num);
        }

        @Override
        protected void bindData(final IndexEntity.GuessLikeBean.ListBeanXX listBean) {
            ImageUtil.load(listBean.getImage()).on(imageView);
            tvNum.setText(listBean.getCollectNum());
            tvTitle.setText(listBean.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=PlayerActivity.newInstance(itemView.getContext(),listBean.getId());
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }
}
