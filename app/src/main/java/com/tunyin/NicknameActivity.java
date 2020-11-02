package com.tunyin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tunyin.mvp.model.SelfBean;
import com.tunyin.utils.HttpUtils;
import com.tunyin.utils.eye.Eyes;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

public class NicknameActivity extends AppCompatActivity {

    EditText etNick;

    public static final String NICK = "nick";

    String saveNick = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Eyes.setStatusBarColor(this, Color.parseColor("#FFFFFF"), true);
        setContentView(R.layout.activity_nickname);

        String nick = getIntent().getStringExtra(NICK);
        etNick = findViewById(R.id.et_nick);

        if (!TextUtils.isEmpty(nick)) {
            etNick.setText(nick);
        }

        findViewById(R.id.tv_save).setOnClickListener(v -> {
            saveNick = etNick.getText().toString();

            String sex = SelfBean.getInstance().getSex();
            if (sex.equals("未填写")) {
                sex = "";
            } else {
                sex = sex.equals("男") ? "1" : "2";
            }

            String birthday = SelfBean.getInstance().getBirthday();
            if (birthday.equals("未填写")) {
                birthday = "";
            }

            HttpUtils.getInstance().commitUser(SelfBean.getInstance().getHeadUrl(),
                    saveNick, sex, birthday, SelfBean.getInstance().getMessageNotice(),
                    new SimpleCallBack<String>() {
                        @Override
                        public void onError(ApiException e) {
                            finish();
                        }

                        @Override
                        public void onSuccess(String s) {
                            if (!TextUtils.isEmpty(saveNick)) {
                                Intent intent = new Intent();
                                intent.putExtra(NICK, saveNick);
                                setResult(1, intent);
                                finish();
                            }
                        }
                    });
        });

        findViewById(R.id.iv_back).setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    public void finish() {
        super.finish();
    }
}
