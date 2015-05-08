package com.android.ashwini.instagramviewer;

import android.os.Parcel;
import android.os.Parcelable;

public class Comment implements Parcelable {
    private String username;
    private String comment;
    private String profilePicUrl;
    private String commentedTime;

    public Comment(String username, String comment, String profilePicUrl, String commentedTime) {
        this.username = username;
        this.comment = comment;
        this.profilePicUrl = profilePicUrl;
        this.commentedTime = commentedTime;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public String getCommentedTime() {
        return commentedTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                this.username, this.comment, this.profilePicUrl, this.commentedTime

        });
    }
}
