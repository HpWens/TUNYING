package com.tunyin;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.tunyin.mvp.model.index.PlayerDirectoryEntity;
import com.tunyin.myservice.Actions;
import com.tunyin.myservice.AudioPlayer;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * create by wangrongchao
 * on 2019/12/15
 **/
public class MyPlayService extends Service {

    public static List<PlayerDirectoryEntity.ListBean> currentPlayList = new ArrayList<>();

    public class PlayBinder extends Binder {
        public MyPlayService getService() {
            return MyPlayService.this;
        }
    }

    public static String getPrice(String price) {
        if (price.equals("0") || price.equals("0.0") || price.equals("0.00")) {
            return "";
        }
        return price;
    }

    public static boolean isFree(String price) {
        if (price.equals("0") || price.equals("0.0") || price.equals("0.00")) {
            return true;
        }
        return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyAudioPlayer.get().init(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PlayBinder();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case Actions.ACTION_STOP:
                    stop();
                    break;
            }
        }
        return START_NOT_STICKY;
    }

    private void stop() {
        AudioPlayer.get().stopPlayer();
//        QuitTimer.get().stop();
//        Notifier.get().cancelAll();
    }
}
