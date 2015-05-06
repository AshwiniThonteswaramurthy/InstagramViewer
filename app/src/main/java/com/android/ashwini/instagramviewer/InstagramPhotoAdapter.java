package com.android.ashwini.instagramviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
        Picasso.with(getContext()).load(photo.getUserProfileImageUrl().toString()).into(ivUserProfilePic);

        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        tvUserName.setText(photo.getUsername());
        return convertView;
    }
}
