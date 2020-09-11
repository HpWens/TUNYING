package com.meis.base.mei.widget.tag;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wenshi on 2019/3/13.
 * Description
 */
public class TagModel implements Parcelable {

    public TagModel() {
    }

    // 相对于父控件的x坐标百分比
    public float x;
    public float y;

    // 标签内容
    public String text;

    // left = true   right = false
    public boolean direction;

    protected TagModel(Parcel in) {
        x = in.readFloat();
        y = in.readFloat();
        text = in.readString();
        direction = in.readByte() != 0;
    }

    public static final Creator<TagModel> CREATOR = new Creator<TagModel>() {
        @Override
        public TagModel createFromParcel(Parcel in) {
            return new TagModel(in);
        }

        @Override
        public TagModel[] newArray(int size) {
            return new TagModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(x);
        dest.writeFloat(y);
        dest.writeString(text);
        dest.writeByte((byte) (direction ? 1 : 0));
    }
}
