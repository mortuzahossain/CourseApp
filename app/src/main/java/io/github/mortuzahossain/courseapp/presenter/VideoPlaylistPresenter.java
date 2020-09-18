package io.github.mortuzahossain.courseapp.presenter;
/*
 * Created by mortuza on 17/9/20 | 2:56 PM for CourseApp
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import io.github.mortuzahossain.courseapp.base.BaseApplication;
import io.github.mortuzahossain.courseapp.network.APIInstance;
import io.github.mortuzahossain.courseapp.interfaces.VideoPlaylistInterfaces;
import io.github.mortuzahossain.courseapp.model.VideoListResponse;
import io.github.mortuzahossain.courseapp.utils.NetworkAvailability;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoPlaylistPresenter {

    VideoPlaylistInterfaces.view view;

    public VideoPlaylistPresenter(VideoPlaylistInterfaces.view view) {
        this.view = view;
    }

    public void getCourseList(String path) {
        if (NetworkAvailability.isNetworkAvailable(BaseApplication.APP_CONTEXT) && NetworkAvailability.isOnline(BaseApplication.APP_CONTEXT)) {
            view.showLoading();
            APIInstance.getAPIService().getVideoLists(path).enqueue(new Callback<VideoListResponse>() {
                @Override
                public void onResponse(Call<VideoListResponse> call, Response<VideoListResponse> response) {
                    view.hideLoading();
                    try {
                        if (response.isSuccessful() && response.body() != null) {
                            view.showSuccess(response.body());
                        } else {
                            view.showFailed("No Data Found");
                        }

                    } catch (Exception e) {
                        view.showFailed(e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<VideoListResponse> call, Throwable t) {
                    view.hideLoading();
                    view.showFailed(t.getLocalizedMessage());
                }
            });
        } else view.showInternetError();

    }


}
