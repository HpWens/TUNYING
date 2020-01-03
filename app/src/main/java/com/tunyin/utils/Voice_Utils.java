package com.tunyin.utils;

import android.media.MediaPlayer;

/**
 * create by wangrongchao
 * on 2019/11/10
 **/
public class Voice_Utils {
    private Voice_Utils() {
    }

    private static Voice_Utils lei = new Voice_Utils();

    //单例模式
    public synchronized static Voice_Utils getInstance() {
        return lei;
    }

    //播放方法
    public void play(MediaPlayer mediaplayer) {
        mediaplayer.start();

    }


    //暂停
    public void pause(MediaPlayer mediaplayer) {
        mediaplayer.pause();
    }


    //判断是否正在播放中
    public boolean isplay(MediaPlayer mediaplayer) {
        return mediaplayer.isPlaying();
    }

    //获取播放时长
    public long getduring(MediaPlayer mediaplayer) {
        // TODO Auto-generated method stub
        return mediaplayer.getDuration();
    }

    //获取当前的播放进度
    public long getcurrentduring(MediaPlayer mediaplayer) {
        // TODO Auto-generated method stub
        return mediaplayer.getCurrentPosition();
    }

    //获取位置
    public int position(int current) {
        // TODO Auto-generated method stub
        return current;
    }

    //更上进度，设置进度..
    public void curento(int position, MediaPlayer mediaplayer) {
        mediaplayer.seekTo(position);
    }


    /**
     * 关闭播放器
     */
    public void closeMedia(MediaPlayer mediaplayer) {
        if (mediaplayer != null) {
            if (mediaplayer.isPlaying()) {
                mediaplayer.stop();
            }
            mediaplayer.release();
        }

    }
}
