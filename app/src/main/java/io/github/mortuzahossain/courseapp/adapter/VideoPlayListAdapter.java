package io.github.mortuzahossain.courseapp.adapter;
/*
 * Created by mortuza on 17/9/20 | 2:21 PM for CourseApp
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import java.util.List;

import io.github.mortuzahossain.courseapp.network.model.VideoItem;

public class VideoPlayListAdapter {
    List<VideoItem> videoItems;

    public VideoPlayListAdapter(List<VideoItem> videoItems) {
        this.videoItems = videoItems;
    }
}
