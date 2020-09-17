package io.github.mortuzahossain.courseapp.network.model;

import com.google.gson.annotations.SerializedName;


public class VideoItem {

    @SerializedName("ClassNo")
    private String classNo;

    @SerializedName("Topic")
    private String topic;

    @SerializedName("VideoURL")
    private String videoURL;

    public String getClassNo() {
        return classNo;
    }

    public String getTopic() {
        return topic;
    }

    public String getVideoURL() {
        return videoURL;
    }
}