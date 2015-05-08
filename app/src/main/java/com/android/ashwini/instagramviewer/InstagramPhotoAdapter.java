package com.android.ashwini.instagramviewer;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
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

    private FragmentManager manager;

    public InstagramPhotoAdapter(Context context, List<Photo> objects) {
        super(context, R.layout.instagram_complex_row, objects);
    }

    public InstagramPhotoAdapter(Context context, List<Photo> objects, FragmentManager manager) {
        super(context, R.layout.instagram_complex_row, objects);
        this.manager = manager;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Photo photo = getItem(position);

        //If the existing view is not being reused inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.instagram_complex_row, parent, false);
        }
        // Profile Pic
        ImageView ivUserProfilePic = (ImageView) convertView.findViewById(R.id.ivUserProfilePic);
        Picasso.with(getContext())
                .load(photo.getUserProfileImageUrl().toString())
                .transform(new RoundedTransformationBuilder().cornerRadiusDp(50).build())
                .into(ivUserProfilePic);

        //Username
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        tvUserName.setText(photo.getUsername());

        // Image
        ImageView ivPopularMedia = (ImageView) convertView.findViewById(R.id.ivPopularMedia);
        Picasso.with(getContext())
                .load(photo.getImageUrl().toString())
                .resize(photo.getImageHeight(), photo.getImageWidth())
                .into(ivPopularMedia);

        //Caption
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        tvCaption.setText(photo.getCaption());

        //Time stamp
        String timestamp = (String) DateUtils.getRelativeTimeSpanString(Long.valueOf(photo.getTimestamp()) * 1000);
        TextView tvTimeStamp = (TextView) convertView.findViewById(R.id.tvTimeStamp);
        tvTimeStamp.setText(timestamp);

        //Like Count
        TextView tvLikeCount = (TextView) convertView.findViewById(R.id.tvLikeCount);
        tvLikeCount.setText(String.valueOf(photo.getNumberOfLikes()) + " likes");

        //Comment Count
        TextView tvCommentCount = (TextView) convertView.findViewById(R.id.tvCommentCount);
        tvCommentCount.setText("view all " + photo.getNumberOfComments() + " comments");

        //Click on comment to view all the comments
        tvCommentCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data = new Bundle();
                data.putParcelableArrayList("comments", photo.getComments());
                DialogFragment dialog = new AllCommentsDialog();
                dialog.setArguments(data);
                dialog.show(manager, "dialog");
            }
        });

        //API returns the comments in sorted order the method puts those into a different arraylist.
        ArrayList<Comment> first2Comments = photo.getFirst2Comments();

        //First latest comment
        if (first2Comments.size() >= 1) {
            TextView tvFirstComment = (TextView) convertView.findViewById(R.id.tvFirstComment);
            TextView tvFirstCommentUserName = (TextView) convertView.findViewById(R.id.tvFirstCommentUserName);
            tvFirstCommentUserName.setText(first2Comments.get(0).getUsername());
            tvFirstComment.setText(first2Comments.get(0).getComment());
        }
        // Second latest comment
        if (first2Comments.size() == 2) {
            TextView tvSecondComment = (TextView) convertView.findViewById(R.id.tvSecondComment);
            TextView tvSecondCommentUserName = (TextView) convertView.findViewById(R.id.tvSecondCommentUserName);
            tvSecondCommentUserName.setText(first2Comments.get(1).getUsername());
            tvSecondComment.setText(first2Comments.get(1).getComment());
        }
        return convertView;
    }
}
