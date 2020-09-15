package com.tunyin.ui.adapter.mine;

import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.meis.base.mei.adapter.MeiBaseAdapter;
import com.tunyin.R;
import com.tunyin.entity.MsgEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class MsgListAdapter extends MeiBaseAdapter<MsgEntity> implements LoadMoreModule {

    public MsgListAdapter(int type) {
        super(R.layout.item_my_msg, new ArrayList<>());
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable MsgEntity msgEntity) {

    }
}
