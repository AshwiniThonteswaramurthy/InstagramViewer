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
    private int imageHeight;
    private int imageWidth;
    private ArrayList<Comment> comments;
    private ArrayList<Comment> first2Comments;

    public Photo(String id, String caption, String username, String timestamp,
                 int numberOfLikes, int numberOfComments, URL userProfileImageUrl,
                 URL imageUrl, ArrayList<Comment> comments, ArrayList<Comment> first2Comments,
                 int imageHeight, int imageWidth) {
        this.id = id;
        this.caption = caption;
        this.username = username;
        this.timestamp = timestamp;
        this.numberOfLikes = numberOfLikes;
        this.numberOfComments = numberOfComments;
        this.userProfileImageUrl = userProfileImageUrl;
        this.imageUrl = imageUrl;
        this.comments = comments;
        this.first2Comments = first2Comments;
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
    }

    // This processes individual image json object
    public static Photo fromJson(JSONObject imageObject) {
        Photo photo = null;
        try {
            String id = imageObject.optString("id");
            String caption = "";
            if (imageObject.get("caption").getClass() == org.json.JSONObject.class) {
                caption = imageObject.optJSONObject("caption").optString("text");
            }
            String username = imageObject.optJSONObject("user").optString("username");
            String timestamp = imageObject.optString("created_time");
            int numberOfLikes = imageObject.optJSONObject("likes").optInt("count");
            int numberOfComments = imageObject.optJSONObject("comments").optInt("count");
            URL userProfileImageUrl = new URL(imageObject.optJSONObject("user").optString("profile_picture"));
            URL imageUrl = new URL(imageObject.optJSONObject("images").optJSONObject("standard_resolution").optString("url"));
            int imageHeight = imageObject.optJSONObject("images").optJSONObject("standard_resolution").optInt("height");
            int imageWidth = imageObject.optJSONObject("images").optJSONObject("standard_resolution").optInt("width");
            ArrayList<Comment> comments = new ArrayList<>();
            JSONArray commentsData = imageObject.optJSONObject("comments").optJSONArray("data");
            ArrayList<Comment> first2comments = new ArrayList<>();
            for (int i = 0; i < commentsData.length(); i++) {
                if (first2comments.size() < 2) {
                    first2comments.add(commentPattern(commentsData.optJSONObject(i)));
                }
                comments.add(commentPattern(commentsData.optJSONObject(i)));
            }
            photo = new Photo(id, caption, username, timestamp, numberOfLikes, numberOfComments,
                    userProfileImageUrl, imageUrl, comments, first2comments, imageHeight, imageWidth);

        } catch (JSONException | MalformedURLException jsonException) {
            jsonException.printStackTrace();
        }
        return photo;
    }

    //Array of images processed
    public static ArrayList<Photo> processAllImages(JSONArray imagesArray) {
        ArrayList<Photo> images = new ArrayList<>();
        for (int i = 0; i < imagesArray.length(); i++) {
            images.add(Photo.fromJson(imagesArray.optJSONObject(i)));
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

    public ArrayList<Comment> getComments() {
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

    public ArrayList<Comment> getFirst2Comments() {
        return first2Comments;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    private static Comment commentPattern(JSONObject commentObject) {
        String username = commentObject.optJSONObject("from").optString("username");
        String commentText = commentObject.optString("text");
        String commentUserProfilePicUrl = commentObject.optJSONObject("from").optString("profile_picture");
        String commentedTime = commentObject.optString("created_time");
        return new Comment(username, commentText, commentUserProfilePicUrl, commentedTime);
    }


}
