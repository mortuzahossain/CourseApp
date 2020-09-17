package io.github.mortuzahossain.courseapp.network;

import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.concurrent.TimeUnit;

import io.github.mortuzahossain.courseapp.base.BaseApplication;
import io.github.mortuzahossain.courseapp.database.AppConstants;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class APIInstance {
    private static Retrofit retrofit;

    static final OkHttpClient okHttpClientRelease = new OkHttpClient.Builder()
            .readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(new ChuckInterceptor(BaseApplication.APP_CONTEXT))
            .build();

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .client(okHttpClientRelease)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static APIService getAPIService() {
        return APIInstance.getRetrofitInstance().create(APIService.class);
    }
}
