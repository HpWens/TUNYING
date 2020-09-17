package com.tunyin.ui.adapter.mine;

import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.meis.base.mei.adapter.MeiBaseAdapter;
import com.tunyin.R;
import com.tunyin.entity.RecordsEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class RecordsAdapter extends MeiBaseAdapter<RecordsEntity> implements LoadMoreModule {

    private boolean isConsumed;

    public RecordsAdapter(boolean isConsumed) {
        super(R.layout.item_transaction_records, new ArrayList<>());
        this.isConsumed = isConsumed;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable RecordsEntity recordsEntity) {
        baseViewHolder.setText(R.id.tv_name, getNonEmpty(recordsEntity.title))
                .setText(R.id.tv_time, getNonEmpty(recordsEntity.createDate))
                .setText(R.id.tv_price, (isConsumed ? "-" : "+") + recordsEntity.money + "钻石");
    }
}
