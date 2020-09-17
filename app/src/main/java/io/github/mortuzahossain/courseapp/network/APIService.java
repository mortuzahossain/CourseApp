package io.github.mortuzahossain.courseapp.network;


import java.util.List;

import io.github.mortuzahossain.courseapp.network.model.CourseListResponse;
import io.github.mortuzahossain.courseapp.network.model.VideoListResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("courses.json")
    Call<List<CourseListResponse>> getCourseLists();

    @GET("")
    Call<VideoListResponse> getVideoLists();
}
