package io.github.mortuzahossain.courseapp.interfaces;
/*
 * Created by mortuza on 17/9/20 | 3:00 PM for CourseApp
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import java.util.List;

import io.github.mortuzahossain.courseapp.model.CourseListResponse;

public interface CourseInterfaces {
    interface view extends NetworkCommonInterface{
        void showSuccess(List<CourseListResponse> courseListResponses);
        void showFailed(String err);
    }
}
