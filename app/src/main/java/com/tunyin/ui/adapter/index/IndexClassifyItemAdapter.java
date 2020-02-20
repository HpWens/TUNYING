package com.tunyin.ui.adapter.index;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tunyin.R;
import com.tunyin.base.BaseAdapter;
import com.tunyin.base.BaseViewHolder;
import com.tunyin.mvp.model.index.IndexEntity;
import com.tunyin.ui.activity.index.PayStuffActivity;
import com.tunyin.ui.activity.index.RankingListActivity;
import com.tunyin.utils.ImageUtil;

public class IndexClassifyItemAdapter extends BaseAdapter<IndexEntity.ClassifyListBean> {
    @Override
    protected int layoutId() {
        return R.layout.item_index_head_type;
    }

    @Override
    protected BaseViewHolder getViewHolder(View view) {
        return new BroadcastItemViewHolder(view);
    }

    class BroadcastItemViewHolder extends BaseViewHolder<IndexEntity.ClassifyListBean> {

        TextView textView;
        ImageView ivType;

        public BroadcastItemViewHolder(View itemView) {
            super(itemView);
            textView = bindView(R.id.tv_head_type);
            ivType = bindView(R.id.iv_type);

        }

        @Override
        protected void bindData(final IndexEntity.ClassifyListBean listBean) {
            textView.setText(listBean.getName());
            ImageUtil.load(listBean.getIcon()).on(ivType);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ("1".equals(listBean.getType())) {
                        itemView.getContext().startActivity(PayStuffActivity.newInstance(itemView.getContext()));
                    } else if ("2".equals(listBean.getType())) {
                        itemView.getContext().startActivity(PayStuffActivity.newInstance(itemView.getContext(), PayStuffActivity.BROAD_CAST));
                    } else if ("3".equals(listBean.getType())) {
                        itemView.getContext().startActivity(PayStuffActivity.newInstance(itemView.getContext(), PayStuffActivity.STATION));
                    } else if ("4".equals(listBean.getType())) {
                        itemView.getContext().startActivity(RankingListActivity.newInstance(itemView.getContext()));
                    }
                }
            });

        }
    }
}
