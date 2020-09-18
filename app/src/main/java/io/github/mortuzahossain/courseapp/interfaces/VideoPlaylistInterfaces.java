package io.github.mortuzahossain.courseapp.interfaces;
/*
 * Created by mortuza on 17/9/20 | 3:00 PM for CourseApp
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */

import io.github.mortuzahossain.courseapp.model.VideoListResponse;

public interface VideoPlaylistInterfaces {
    interface view extends NetworkCommonInterface{
        void showSuccess(VideoListResponse videoListResponse);
        void showFailed(String err);
    }
}
