package com.tunyin;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.tunyin.myservice.Actions;
import com.tunyin.myservice.AudioPlayer;

/**
 * create by wangrongchao
 * on 2019/12/15
 **/
public class MyPlayService extends Service {



    public class PlayBinder extends Binder {
        public MyPlayService getService() {
            return MyPlayService.this;
        }
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
