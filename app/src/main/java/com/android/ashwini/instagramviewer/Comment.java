package com.android.ashwini.instagramviewer;

import android.os.Parcel;
import android.os.Parcelable;

public class Comment implements Parcelable {
    private String username;
    private String comment;

    public Comment(String username, String comment) {
        this.username = username;
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                this.username, this.comment
        });
    }
}
