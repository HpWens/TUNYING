package com.tunyin;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.tunyin.mvp.model.Event;
import com.tunyin.mvp.model.IsTryEntity;
import com.tunyin.mvp.model.SelfBean;
import com.tunyin.myservice.AudioFocusManager;
import com.tunyin.myservice.OnPlayerEventListener;
import com.tunyin.utils.EventBusUtil;

import java.io.IOException;

/**
 * create by wangrongchao
 * on 2019/12/15
 **/
public class MyAudioPlayer {

    private static final int STATE_IDLE = 0;
    private static final int STATE_PREPARING = 1;
    private static final int STATE_PLAYING = 2;
    private static final int STATE_PAUSE = 3;

    private static final long TIME_UPDATE = 300L;

    private Context context;
    private MediaPlayer mediaPlayer;

    private AudioFocusManager audioFocusManager;

    private Handler handler;
    private int state = STATE_IDLE;
    private String tempUrl = "";
    private boolean mIsTry = false;
    private String mImgUrl = "";
    private String mListeningTime = "";

    private OnPlayerEventListener myListeners = null;

    public static MyAudioPlayer get() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static MyAudioPlayer instance = new MyAudioPlayer();
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    private MyAudioPlayer() {
    }


    public void init(Context context) {
        this.context = context.getApplicationContext();
        audioFocusManager = new AudioFocusManager(context);
        mediaPlayer = new MediaPlayer();
        int i = mediaPlayer.getDuration();
        handler = new Handler(Looper.getMainLooper());

        mediaPlayer.setOnPreparedListener(mp -> {
            if (isPreparing()) {
//                startPlayer();
            }
        });
        mediaPlayer.setOnBufferingUpdateListener((mp, percent) -> {
            myListeners.onBufferingUpdate(percent);
        });

    }

    public void addOnPlayEventListener(OnPlayerEventListener listener) {
        myListeners = listener;
    }

    public void startPlayer() {
        if (!isPreparing() && !isPausing()) {
            return;
        }

        if (audioFocusManager.requestAudioFocus()) {
            mediaPlayer.start();
            state = STATE_PLAYING;
            handler.post(mPublishRunnable);
//            Notifier.get().showPlay(getPlayMusic());
//            MediaSessionManager.get().updatePlaybackState();
//            context.registerReceiver(noisyReceiver, noisyFilter);
//
//            for (OnPlayerEventListener listener : listeners) {
            myListeners.onPlayerStart();
            IsTryEntity isTryEntity = new IsTryEntity(mIsTry, isPlaying(), mImgUrl);
            Event event = new Event(1, isTryEntity);
            EventBusUtil.INSTANCE.sendEvent(event);
//            }
        }
    }

    public void forcePlayer() {
        state = STATE_PLAYING;
        mediaPlayer.start();
    }

    public void play(String url, boolean isTry, String imgUrl) {
        mIsTry = isTry;
        mImgUrl = imgUrl;
//        if (musicList.isEmpty()) {
//            return;
//        }
//
//        if (position < 0) {
//            position = musicList.size() - 1;
//        } else if (position >= musicList.size()) {
//            position = 0;
//        }

//        setPlayPosition(position);
//        Music music = getPlayMusic();
        if (!url.equals(tempUrl)) {


            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepareAsync();
                state = STATE_PREPARING;
//            for (OnPlayerEventListener listener : listeners) {
//                myListeners.onChange(music);
//            }
//            Notifier.get().showPlay(music);
//            MediaSessionManager.get().updateMetaData(music);
//            MediaSessionManager.get().updatePlaybackState();
            } catch (IOException e) {
                e.printStackTrace();
                //ToastUtils.INSTANCE.showSingleToast("当前歌曲无法播放");
            }
        }
        tempUrl = url;
    }

    public long getAudioPosition() {
        if (isPlaying() || isPausing()) {
            return mediaPlayer.getCurrentPosition();
        } else {
            return 0;
        }
    }

    public long getDuration() {
        if (mediaPlayer != null) {

            int it = mediaPlayer.getDuration();
//            LogUtils.INSTANCE.d("----mediaPlayer------" + it);
            return mediaPlayer.getDuration();
        }
        return 0;
    }


    /**
     * 跳转到指定的时间位置
     *
     * @param msec 时间
     */
    public void seekTo(int msec) {
        if (isPlaying() || isPausing()) {
            mediaPlayer.seekTo(msec);
//            MediaSessionManager.get().updatePlaybackState();
//            for (OnPlayerEventListener listener : listeners) {
            myListeners.onPublish(msec);
//            }
        }
    }

    private Runnable mPublishRunnable = new Runnable() {
        @Override
        public void run() {

            if (!TextUtils.isEmpty(mListeningTime)) {
                if (mListeningTime.equals(EventBusUtil.INSTANCE.formatSecond((int) getAudioPosition()))) {
                    pausePlayer();
                }
            }

            if (isPlaying()) {
                myListeners.onPublish(mediaPlayer.getCurrentPosition());
            }
            handler.postDelayed(this, TIME_UPDATE);
            IsTryEntity isTryEntity = new IsTryEntity(mIsTry, isPlaying(), mImgUrl);
            Event event = new Event(1, isTryEntity);
            EventBusUtil.INSTANCE.sendEvent(event);
        }
    };

    public boolean isPlaying() {
        return state == STATE_PLAYING;
    }

    public boolean isPausing() {
        return state == STATE_PAUSE;
    }

    public boolean isPreparing() {
        return state == STATE_PREPARING;
    }

    public boolean isIdle() {
        return state == STATE_IDLE;
    }

    public void playPause() {
        if (isPreparing()) {
            stopPlayer();
        } else if (isPlaying()) {
            pausePlayer();
        } else if (isPausing()) {
            startPlayer();
        } else {
            play(SelfBean.getInstance().getMusicUrl(), mIsTry, mImgUrl);
        }
    }

    public void playPauseForTry(String listeningTime) {
        mListeningTime = listeningTime;
        if (isPreparing()) {
            stopPlayer();
        } else if (isPlaying()) {
            pausePlayer();
        } else if (isPausing()) {
            startPlayer();
        } else {
            play(SelfBean.getInstance().getMusicUrl(), mIsTry, mImgUrl);
        }
    }

    public void stopPlayer() {
        if (isIdle()) {
            return;
        }

        pausePlayer();
        mediaPlayer.reset();
        state = STATE_IDLE;
    }

    public void pausePlayer() {
        pausePlayer(true);
    }

    public void pausePlayer(boolean abandonAudioFocus) {
        if (!isPlaying()) {
            return;
        }

        mediaPlayer.pause();
        state = STATE_PAUSE;
        handler.removeCallbacks(mPublishRunnable);
        IsTryEntity isTryEntity = new IsTryEntity(mIsTry, isPlaying(), mImgUrl);
        Event event = new Event(1, isTryEntity);
        EventBusUtil.INSTANCE.sendEvent(event);
//        Notifier.get().showPause(getPlayMusic());
//        MediaSessionManager.get().updatePlaybackState();
//        context.unregisterReceiver(noisyReceiver);
//        if (abandonAudioFocus) {
//            audioFocusManager.abandonAudioFocus();
//        }

//        for (OnPlayerEventListener listener : listeners) {
        myListeners.onPlayerPause();
//        }
    }

    public int getState() {
        return state;
    }
}
