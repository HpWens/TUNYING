package sj.keyboard.emoji;


import com.keyboard.view.R;

public class DefEmoticons {

    public static final EmojiBean[] sEmojiArray;

    public DefEmoticons() {
    }

    static {
        sEmojiArray = new EmojiBean[]{new EmojiBean(R.mipmap.emoji_0x1f604, EmojiParse.fromCodePoint(128516))};
    }
}
