package com.tunyin.listener;

/**
 * Created by yiang on 2018/4/6.
 */

public interface PlayerListener {

    void onPlayPrepared();

    void onPlayStart();

    void onPlayPause();

    void onPlayComplete();

    void onPlayError();


    void onPlayProgress(long progress, long totalProgress);

    void onPlaySeekTo(int progress);

}
