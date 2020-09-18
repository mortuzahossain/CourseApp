package io.github.mortuzahossain.courseapp.presenter;
/*
 * Created by mortuza on 17/9/20 | 2:56 PM for CourseApp
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import java.util.List;

import io.github.mortuzahossain.courseapp.base.BaseApplication;
import io.github.mortuzahossain.courseapp.network.APIInstance;
import io.github.mortuzahossain.courseapp.interfaces.CourseInterfaces;
import io.github.mortuzahossain.courseapp.model.CourseListResponse;
import io.github.mortuzahossain.courseapp.utils.NetworkAvailability;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursePresenter {

    public CourseInterfaces.view view;

    public CoursePresenter(CourseInterfaces.view view) {
        this.view = view;
    }

    public void getCourse() {
        if (NetworkAvailability.isNetworkAvailable(BaseApplication.APP_CONTEXT) && NetworkAvailability.isOnline(BaseApplication.APP_CONTEXT)) {
            view.showLoading();
            APIInstance.getAPIService().getCourseLists().enqueue(new Callback<List<CourseListResponse>>() {
                @Override
                public void onResponse(Call<List<CourseListResponse>> call, Response<List<CourseListResponse>> response) {
                    view.hideLoading();
                    try {
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().size() > 0) {
                                view.showSuccess(response.body());
                            } else {
                                view.showFailed("No Data Found");
                            }
                        } else {
                            view.showFailed("No Data Found");
                        }

                    } catch (Exception e) {
                        view.showFailed(e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<List<CourseListResponse>> call, Throwable t) {
                    view.hideLoading();
                    view.showFailed(t.getLocalizedMessage());
                }
            });
        } else view.showInternetError();

    }


}
