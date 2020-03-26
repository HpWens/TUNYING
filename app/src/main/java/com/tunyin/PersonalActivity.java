package com.tunyin;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.tunyin.base.BaseInjectActivity;
import com.tunyin.mvp.contract.mine.PersonContract;
import com.tunyin.mvp.model.SelfBean;
import com.tunyin.mvp.model.UploadFileEntity;
import com.tunyin.mvp.presenter.mine.PersonalPresenter;
import com.tunyin.ui.activity.mine.ResetPasswordActivity;
import com.tunyin.ui.dialog.SexDialog;
import com.tunyin.utils.ImagePickHelper;
import com.tunyin.utils.ImageUtil;
import com.tunyin.utils.UriUtil;
import com.tunyin.utils.eye.Eyes;
import com.zhihu.matisse.Matisse;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PersonalActivity extends BaseInjectActivity<PersonalPresenter> implements PersonContract.View {

    TextView tvDay;
    TextView tvSex;
    TextView tvNick;
    ImageView ivHeader;

    @Override
    public void initWidget() {
        super.initWidget();
        Eyes.setStatusBarColor(this, Color.parseColor("#FFFFFF"), true);
        tvDay = findViewById(R.id.tv_day);
        tvSex = findViewById(R.id.tv_sex);
        tvNick = findViewById(R.id.tv_nick);
        ivHeader = findViewById(R.id.iv_header);

        tvDay.setText(SelfBean.getInstance().getBirthday());
        ImageUtil.load(SelfBean.getInstance().getHeadUrl()).isCircle().on(ivHeader);
        tvNick.setText(SelfBean.getInstance().getNickName());
        tvSex.setText(SelfBean.getInstance().getSex());

        findViewById(R.id.iv_back).setOnClickListener(v -> {
            finish();
        });
    }

    private void showPicker() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        startDate.set(1949, 0, 1);
        endDate.set(year, month, 1);
        selectedDate.set(year, month, 1);

        TimePickerView pvTime = new TimePickerBuilder(this, (date, v) -> {
            String data_format = "yyyy-MM";
            SimpleDateFormat default_sdf = new SimpleDateFormat(data_format, Locale.getDefault());
            tvDay.setText(default_sdf.format(date));
            SelfBean.getInstance().setBirthday(tvDay.getText().toString());
        }).setType(new boolean[]{true, true, false, false, false, false})
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(14)//滚轮文字大小
                .setTitleSize(14)//标题文字大小
                .setTitleText("请选择日期")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(Color.parseColor("#0A0A0A"))//标题文字颜色
                .setSubmitColor(Color.parseColor("#00D066"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#666666"))//取消按钮文字颜色
                .setTitleBgColor(Color.parseColor("#ffffff"))//标题背景颜色 Night mode
                .setBgColor(Color.parseColor("#ffffff"))//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();

        pvTime.show();
    }

    public void onDay(View view) {
        showPicker();
    }

    public void onSex(View view) {
        SexDialog sexDialog = new SexDialog(view.getContext(), (view1, sex, dialog) -> {
            tvSex.setText(sex);
            SelfBean.getInstance().setSex(sex);
            dialog.dismiss();
        });
        sexDialog.show();
    }

    public void onPassword(View view) {
        startActivity(new Intent(this, ResetPasswordActivity.class));
    }

    public void onHeader(View view) {
        ImagePickHelper.with(this)
                .pickMulPicture(1);
    }

    public void onNick(View view) {
        Intent intent = new Intent(this, NicknameActivity.class);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (resultCode == 1) {
            tvNick.setText(data.getStringExtra(NicknameActivity.NICK));
            SelfBean.getInstance().setNickName(tvNick.getText().toString());
        } else {
            List<Uri> pickPhotoList = Matisse.obtainResult(data);
            if (pickPhotoList == null || pickPhotoList.size() == 0) return;
            String path = UriUtil.getPathFromUri(pickPhotoList.get(0));
            ImageUtil.load(path).isCircle().on(ivHeader);

            File file = new File(path);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            mPresenter.uploadFile(filePart);
        }
    }

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
        return R.layout.activity_personal;
    }

    @Override
    public void uploadFileSuc(@NotNull UploadFileEntity uploadFileEntity) {
        String headerUrl = uploadFileEntity.getUrl();
        SelfBean.getInstance().setHeadUrl(headerUrl);
    }
}
