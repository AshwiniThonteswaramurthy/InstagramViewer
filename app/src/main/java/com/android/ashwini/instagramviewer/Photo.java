package com.android.ashwini.instagramviewer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Photo implements Serializable {
    private String id;
    private String caption;
    private String username;
    private String timestamp;


    private int numberOfLikes;
    private int numberOfComments;
    private URL userProfileImageUrl;
    private URL imageUrl;
    private ArrayList<String> comments;

    public Photo(String id, String caption, String username, String timestamp, int numberOfLikes, int numberOfComments, URL userProfileImageUrl, URL imageUrl, ArrayList<String> comments) {
        this.id = id;
        this.caption = caption;
        this.username = username;
        this.timestamp = timestamp;
        this.numberOfLikes = numberOfLikes;
        this.numberOfComments = numberOfComments;
        this.userProfileImageUrl = userProfileImageUrl;
        this.imageUrl = imageUrl;
        this.comments = comments;
    }

    // This processes individual image json object
    public static Photo fromJson(JSONObject imageObject) {
        Photo photo = null;
        try {
            String id = imageObject.getString("id");
            String caption = "";
            if (imageObject.get("caption").getClass() == org.json.JSONObject.class) {
                caption = imageObject.getJSONObject("caption").getString("text");
            }
            String username = imageObject.getJSONObject("user").getString("username");
            String timestamp = imageObject.getString("created_time");
            int numberOfLikes = imageObject.getJSONObject("likes").getInt("count");
            int numberOfComments = imageObject.getJSONObject("comments").getInt("count");
            URL userProfileImageUrl = new URL(imageObject.getJSONObject("user").getString("profile_picture"));
            URL imageUrl = new URL(imageObject.getJSONObject("images").getJSONObject("standard_resolution").getString("url"));
            ArrayList<String> comments = new ArrayList<>();
            JSONArray commentsData = imageObject.getJSONObject("comments").getJSONArray("data");
            for (int i = 0; i < commentsData.length(); i++) {
                comments.add(commentsData.getJSONObject(i).getString("text"));
            }
            photo = new Photo(id, caption, username, timestamp, numberOfLikes, numberOfComments, userProfileImageUrl, imageUrl, comments);
        } catch (JSONException | MalformedURLException jsonException) {
            jsonException.printStackTrace();
        }
        return photo;
    }

    //Array of images processed
    public static ArrayList<Photo> processAllImages(JSONArray imagesArray) {
        ArrayList<Photo> images = new ArrayList<>();
        for (int i = 0; i < imagesArray.length(); i++) {
            try {
                images.add(Photo.fromJson(imagesArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return images;
    }

    public String getCaption() {
        return caption;
    }

    public String getUsername() {
        return username;
    }

    public String getTimestamp() {
        return timestamp;
    }


    public URL getUserProfileImageUrl() {
        return userProfileImageUrl;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public String getId() {
        return id;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }


}
