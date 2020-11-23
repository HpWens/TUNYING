package com.tunyin.ui.fragment.mine;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.meis.base.mei.adapter.MeiBaseAdapter;
import com.meis.base.mei.base.BaseListFragment;
import com.meis.base.mei.entity.Result;
import com.tunyin.R;
import com.tunyin.entity.MsgEntity;
import com.tunyin.ui.adapter.mine.MsgListAdapter;
import com.tunyin.utils.HttpUtils;

import java.util.List;

import io.reactivex.Observable;

public class MsgListFragment extends BaseListFragment<MsgEntity> {

    private static final String TYPE = "type";
    private MsgListAdapter mAdapter;

    private int type = 1;

    public static MsgListFragment newInstance(int type) {
        Bundle args = new Bundle();
        MsgListFragment fragment = new MsgListFragment();
        args.putInt(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(TYPE)) {
            type = bundle.getInt(TYPE);
        }
        super.initView();

        setEmptyIconAndText(R.mipmap.mei_comm_ic_empty2, R.string.no_msg2);
    }

    @Override
    protected RecyclerView getRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    @Override
    protected MeiBaseAdapter<MsgEntity> getAdapter() {
        return mAdapter = new MsgListAdapter(type);
    }

    @Override
    protected Observable<Result<List<MsgEntity>>> getListObservable(int pageNo) {
        return getListByField(HttpUtils.getInstance().getMyMsgList(pageNo, type), "content", "list", MsgEntity.class);
    }

    @Override
    public boolean canLoadMore() {
        return true;
    }

    @Override
    public boolean canPullToRefresh() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comm_trans_recycler;
    }
}
