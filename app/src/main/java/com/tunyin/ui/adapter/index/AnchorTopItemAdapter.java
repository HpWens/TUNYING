package com.tunyin.ui.adapter.index;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tunyin.R;
import com.tunyin.base.BaseAdapter;
import com.tunyin.base.BaseViewHolder;
import com.tunyin.mvp.model.discovery.DiscoveryEntity;
import com.tunyin.mvp.model.index.IndexEntity;
import com.tunyin.utils.DimensionUtil;
import com.tunyin.utils.ImageUtil;
import com.tunyin.widget.CustomRoundAngleImageView;

public class AnchorTopItemAdapter extends BaseAdapter<IndexEntity.AnchorListBean> {
    @Override
    protected int layoutId() {
        return R.layout.item_hot_host;
    }

    @Override
    protected BaseViewHolder getViewHolder(View view) {
        return new BroadcastItemViewHolder(view);
    }
    class BroadcastItemViewHolder extends BaseViewHolder<IndexEntity.AnchorListBean>{

        TextView tvName;
        ImageView imageView;
        LinearLayout lyContain;
        public BroadcastItemViewHolder(View itemView) {
            super(itemView);
            imageView=bindView(R.id.iv_avatar);
            tvName=bindView(R.id.tv_name);
            lyContain=bindView(R.id.ly_contain);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) lyContain.getLayoutParams();
            int width= DimensionUtil.getScreenWith(itemView.getContext())/4-params.getMarginStart()-params.getMarginEnd()-30;
//            params.height = width;
            params.width = width;
            lyContain.setLayoutParams(params);


            ViewGroup.LayoutParams params2 = imageView.getLayoutParams();
            int width2= DimensionUtil.getScreenWith(itemView.getContext())/4-params.getMarginStart()-params.getMarginEnd()-30;
            params2.height = width2;
            params2.width = width2;
            imageView.setLayoutParams(params2);

        }

        @Override
        protected void bindData(IndexEntity.AnchorListBean listBean) {
            ImageUtil.load(listBean.getHeadUrl()).isCircle().on(imageView);
            tvName.setText(listBean.getName());

        }
    }
}
