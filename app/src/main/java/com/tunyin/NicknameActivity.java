package com.tunyin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tunyin.utils.eye.Eyes;

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
            if (!TextUtils.isEmpty(saveNick)) {
                Intent intent = new Intent();
                intent.putExtra(NICK, saveNick);
                setResult(1, intent);
                finish();
            }
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
