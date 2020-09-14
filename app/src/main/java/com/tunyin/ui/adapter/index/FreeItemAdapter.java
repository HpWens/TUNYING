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

public class FreeItemAdapter extends BaseAdapter<IndexEntity.FreeListBean.ListBeanX> {
    @Override
    protected int layoutId() {
        return R.layout.item_free;
    }

    @Override
    protected BaseViewHolder getViewHolder(View view) {
        return new BroadcastItemViewHolder(view);
    }

    class BroadcastItemViewHolder extends BaseViewHolder<IndexEntity.FreeListBean.ListBeanX> {

        TextView tvTitle, tvSubTitle;
        TextView tvNum;
        CustomRoundAngleImageView imageView;

        public BroadcastItemViewHolder(View itemView) {
            super(itemView);
            tvTitle = bindView(R.id.tv_title);
            tvNum = bindView(R.id.tv_num);
            tvSubTitle = bindView(R.id.tv_sub_title);
            imageView = bindView(R.id.image);
        }

        @Override
        protected void bindData(final IndexEntity.FreeListBean.ListBeanX listBean) {
            ImageUtil.load(listBean.getImage()).on(imageView);
//            tvNum.setText(listBean.getC);//todo
            tvTitle.setText(listBean.getTitle());
            tvSubTitle.setText(listBean.getContent());
            tvNum.setText(listBean.getNum().equals("-1") ? "0" : listBean.getNum());

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
