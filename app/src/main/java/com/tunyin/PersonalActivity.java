package com.tunyin;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showPicker();
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
            String data_format = "yyyy-MM-dd";
            SimpleDateFormat default_sdf = new SimpleDateFormat(data_format, Locale.getDefault());
            default_sdf.format(date);

        }).setType(new boolean[]{true, true, true, false, false, false})
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
}
