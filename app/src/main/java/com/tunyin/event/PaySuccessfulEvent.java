package com.tunyin.event;

public class PaySuccessfulEvent {
    public String musicId;

    public PaySuccessfulEvent(String musicId) {
        this.musicId = musicId;
    }
}
