package io.github.mortuzahossain.courseapp.network;


import java.util.List;

import io.github.mortuzahossain.courseapp.model.CourseListResponse;
import io.github.mortuzahossain.courseapp.model.VideoListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

    @GET("courses.json")
    Call<List<CourseListResponse>> getCourseLists();

    @GET("{path}")
    Call<VideoListResponse> getVideoLists(@Path("path") String path);
}
