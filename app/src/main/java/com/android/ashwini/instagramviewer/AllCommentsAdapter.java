package com.android.ashwini.instagramviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        TextView tvCommentUsername = (TextView) convertView.findViewById(R.id.tvCommentUserName);
        tvCommentUsername.setText(comment.getUsername());

        TextView tvComment = (TextView) convertView.findViewById(R.id.tvComment);
        tvComment.setText(comment.getComment());

        return convertView;
    }
}
