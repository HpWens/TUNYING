package com.tunyin;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.tunyin.base.BaseInjectActivity;
import com.tunyin.mvp.contract.mine.FeedbackContract;
import com.tunyin.mvp.contract.mine.ResetPasswordContract;
import com.tunyin.mvp.model.SelfBean;
import com.tunyin.mvp.model.UploadFileEntity;
import com.tunyin.mvp.presenter.mine.FeedbackPresenter;
import com.tunyin.utils.DisplayUtil;
import com.tunyin.utils.ImagePickHelper;
import com.tunyin.utils.ImageUtil;
import com.tunyin.utils.UriUtil;
import com.tunyin.utils.eye.Eyes;
import com.zhihu.matisse.Matisse;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FeedbackActivity extends BaseInjectActivity<FeedbackPresenter> implements FeedbackContract.View {


    private EditText etContent;

    private TextView tvNum;

    private ImageView ivPic;

    private String picUrl = "";

    @Override
    public void initInject() {
        super.initInject();
        getActivityComponent().inject(this);
    }

    @Override
    public void initPresenter() {
        super.initPresenter();
        mPresenter.attachView(this);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_feed_back;
    }


    @Override
    public void uploadPicSus(@NotNull UploadFileEntity uploadFileEntity) {
        picUrl = uploadFileEntity.getUrl();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        Eyes.setStatusBarColor(this, Color.parseColor("#FFFFFF"), true);

        etContent = findViewById(R.id.ev_content);
        tvNum = findViewById(R.id.tv_num);
        ivPic = findViewById(R.id.iv_pic);

        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvNum.setText(s.toString().length() + "/200");
            }
        });

        ivPic.setOnClickListener(v -> {
            ImagePickHelper.with(this)
                    .pickMulPicture(1);
        });

        findViewById(R.id.tv_submit).setOnClickListener(v -> {
            String content = etContent.getText().toString();
            if (TextUtils.isEmpty(content)) {
                ToastUtils.INSTANCE.showToast("请输入反馈内容");
                return;
            }
            mPresenter.submitFeedback(content, picUrl);
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        List<Uri> pickPhotoList = Matisse.obtainResult(data);
        if (pickPhotoList == null || pickPhotoList.size() == 0) return;
        String path = UriUtil.getPathFromUri(pickPhotoList.get(0));

        RoundedCorners roundedCorners = new RoundedCorners(DisplayUtil.INSTANCE.dip2px(8));
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        Glide.with(this).load(path).apply(options).into(ivPic);

        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        mPresenter.uploadFile(filePart);
    }

    public void onBack(View view) {
        finish();
    }

    @Override
    public void feedbackSus(@NotNull String respose) {
        ToastUtils.INSTANCE.showToast("反馈成功");
        finish();
    }
}
