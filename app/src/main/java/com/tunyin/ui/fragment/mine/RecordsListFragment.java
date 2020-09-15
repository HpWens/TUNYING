package com.tunyin.ui.fragment.mine;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.meis.base.mei.adapter.MeiBaseAdapter;
import com.meis.base.mei.base.BaseListFragment;
import com.meis.base.mei.entity.Result;
import com.tunyin.R;
import com.tunyin.entity.RecordsEntity;
import com.tunyin.ui.adapter.mine.RecordsAdapter;
import com.tunyin.utils.HttpUtils;

import java.util.List;

import io.reactivex.Observable;

public class RecordsListFragment extends BaseListFragment<RecordsEntity> {

    private static final String TYPE = "type";

    // 是否消费记录
    private boolean isConsumed = true;

    private RecordsAdapter mAdapter;

    public static RecordsListFragment newInstance(int type) {
        Bundle args = new Bundle();
        RecordsListFragment fragment = new RecordsListFragment();
        args.putInt(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(TYPE)) {
            isConsumed = bundle.getInt(TYPE) == 0;
        }
        super.initView();

        setEmptyIconAndText(R.mipmap.no_order_record, R.string.no_order);
    }

    @Override
    protected RecyclerView getRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    @Override
    protected MeiBaseAdapter<RecordsEntity> getAdapter() {
        return mAdapter = new RecordsAdapter(isConsumed);
    }

    @Override
    protected Observable<Result<List<RecordsEntity>>> getListObservable(int pageNo) {
        return getListByField(HttpUtils.getInstance().getTransactionRecordList(pageNo, isConsumed ? 1 : 2), "content", "list", RecordsEntity.class);
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
