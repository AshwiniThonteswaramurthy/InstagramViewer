package com.android.ashwini.instagramviewer;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class InstagramPhotoAdapter extends ArrayAdapter<Photo> {
    public InstagramPhotoAdapter(Context context, List<Photo> objects) {
        super(context, R.layout.instagram_complex_row, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Photo photo = getItem(position);

        //If the existing view is not being reused inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.instagram_complex_row, parent, false);
        }
        ImageView ivUserProfilePic = (ImageView) convertView.findViewById(R.id.ivUserProfilePic);
        Picasso.with(getContext())
                .load(photo.getUserProfileImageUrl().toString())
                .transform(new RoundedTransformationBuilder().cornerRadiusDp(50).build())
                .into(ivUserProfilePic);

        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        tvUserName.setText(photo.getUsername());

        ImageView ivPopularMedia = (ImageView) convertView.findViewById(R.id.ivPopularMedia);
        Picasso.with(getContext())
                .load(photo.getImageUrl().toString())
                .resize(photo.getImageHeight(),photo.getImageWidth())
                .into(ivPopularMedia);

        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        tvCaption.setText(photo.getCaption());

        String timestamp = (String) DateUtils.getRelativeTimeSpanString(Long.valueOf(photo.getTimestamp()) * 1000);
        TextView tvTimeStamp = (TextView) convertView.findViewById(R.id.tvTimeStamp);
        tvTimeStamp.setText(timestamp);

        TextView tvLikeCount = (TextView) convertView.findViewById(R.id.tvLikeCount);
        tvLikeCount.setText(String.valueOf(photo.getNumberOfLikes()) + " likes");

        TextView tvCommentCount = (TextView) convertView.findViewById(R.id.tvCommentCount);
        tvCommentCount.setText("view all " + photo.getNumberOfComments() + " comments");

        ArrayList<String> first2Comments = photo.getFirst2Comments();
        TextView tvFirstComment = (TextView) convertView.findViewById(R.id.tvFirstComment);
        TextView tvFirstCommentUserName = (TextView) convertView.findViewById(R.id.tvFirstCommentUserName);
        tvFirstCommentUserName.setText(first2Comments.get(0).split(":")[0]);
        tvFirstComment.setText(first2Comments.get(0).split(":")[1]);

        TextView tvSecondComment = (TextView) convertView.findViewById(R.id.tvSecondComment);
        TextView tvSecondCommentUserName = (TextView) convertView.findViewById(R.id.tvSecondCommentUserName);
        tvSecondCommentUserName.setText(first2Comments.get(1).split(":")[0]);
        tvSecondComment.setText(first2Comments.get(1).split(":")[1]);

        return convertView;
    }
}
