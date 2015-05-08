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

import java.util.List;

public class AllCommentsAdapter extends ArrayAdapter<Comment> {

    public AllCommentsAdapter(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Comment comment = getItem(position);

        //Comment view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment_row, parent, false);
        }
        //Comment User Profile pic
        ImageView ivUserProfilePic = (ImageView) convertView.findViewById(R.id.ivCommentUserProfilePic);
        Picasso.with(getContext())
                .load(comment.getProfilePicUrl())
                .transform(new RoundedTransformationBuilder().cornerRadiusDp(50).build())
                .into(ivUserProfilePic);

        TextView tvCommentUsername = (TextView) convertView.findViewById(R.id.tvCommentUserName);
        tvCommentUsername.setText(comment.getUsername());

        TextView tvComment = (TextView) convertView.findViewById(R.id.tvComment);
        tvComment.setText(comment.getComment());

        String timestamp = (String) DateUtils.getRelativeTimeSpanString(Long.valueOf(comment.getCommentedTime()) * 1000);
        TextView tvTimeStamp = (TextView) convertView.findViewById(R.id.tvCommentedTimeStamp);
        tvTimeStamp.setText(timestamp);

        return convertView;
    }
}
