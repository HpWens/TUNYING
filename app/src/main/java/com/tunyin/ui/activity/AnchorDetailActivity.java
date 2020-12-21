package com.tunyin.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.meis.base.mei.base.BaseActivity;
import com.meis.base.mei.utils.ParseJsonUtils;
import com.tunyin.R;
import com.tunyin.entity.AnchorDetailEntity;
import com.tunyin.ui.adapter.AnchorDetailAdapter;
import com.tunyin.utils.eye.Eyes;
import com.vondear.rxtool.RxImageTool;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONObject;

public class AnchorDetailActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private AnchorDetailAdapter anchorDetailAdapter;

    private String anchorId;

    public static Intent start(Context context, String anchorId) {
        Intent intent = new Intent(context, AnchorDetailActivity.class);
        intent.putExtra("anchorId", anchorId);
        return intent;
    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this, true);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        recyclerView.setAdapter(anchorDetailAdapter = new AnchorDetailAdapter());

        anchorId = getIntent().getStringExtra("anchorId");

        EasyHttp.post("api/song/search")
                .params("offset", "0")
                .params("limit", "200")
                .params("anchorId", anchorId)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                    }

                    @Override
                    public void onSuccess(String s) {
                        if (TextUtils.isEmpty(s)) return;
                        try {
                            JSONObject json = new JSONObject(s);
                            if (json.has("content")) {
                                AnchorDetailEntity anchorDetailEntity = ParseJsonUtils.parseData(json.optString("content"), AnchorDetailEntity.class);
                                if (anchorDetailEntity.anchor != null) {
                                    View header = View.inflate(mContext, R.layout.header_anchor_layout, null);
                                    header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RxImageTool.dp2px(180)));
                                    anchorDetailAdapter.addHeaderView(header);

                                    ImageView ivSex = header.findViewById(R.id.iv_sex);
                                    ivSex.setImageResource(anchorDetailEntity.anchor.sex.equals("女") ? R.mipmap.woman_icon : R.mipmap.man_icon);

                                    ImageView ivHeader = header.findViewById(R.id.iv_header);
                                    Glide.with(mContext).load(anchorDetailEntity.anchor.headUrl).into(ivHeader);

                                    TextView tvName = header.findViewById(R.id.tv_name);
                                    tvName.setText(anchorDetailEntity.anchor.name);

                                    TextView tvCons = header.findViewById(R.id.tv_cons);
                                    tvCons.setText(anchorDetailEntity.anchor.constellation);

                                    TextView tvDesc = header.findViewById(R.id.tv_desc);
                                    tvDesc.setText(anchorDetailEntity.anchor.summary);

                                }

                                if (anchorDetailEntity.list != null) {
                                    anchorDetailAdapter.setNewData(anchorDetailEntity.list);
                                }
                            }
                        } catch (Exception e) {
                        }
                    }
                });

    }

    @Override
    protected void initData() {
        findViewById(R.id.iv_back).setOnClickListener(v -> finish());
    }

    @Override
    protected int layoutResId() {
        return R.layout.activity_anchor_detail;
    }


}
