package io.github.mortuzahossain.courseapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class VideoListResponse {

    @SerializedName("image")
    private String imageUrl;

    @SerializedName("course")
    private List<VideoItem> videoItems;

    public String getImageUrl() {
        return imageUrl;
    }

    public List<VideoItem> getVideoItems() {
        return videoItems;
    }
}