package io.github.mortuzahossain.courseapp.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.mortuzahossain.courseapp.R;
import io.github.mortuzahossain.courseapp.adapter.VideoPlayListAdapter;
import io.github.mortuzahossain.courseapp.database.AppConstants;
import io.github.mortuzahossain.courseapp.interfaces.VideoPlaylistInterfaces;
import io.github.mortuzahossain.courseapp.model.VideoListResponse;
import io.github.mortuzahossain.courseapp.presenter.VideoPlaylistPresenter;
import io.github.mortuzahossain.courseapp.utils.DialogUtils;
import io.github.mortuzahossain.courseapp.utils.GridSpacingItemDecoration;

public class VideoPlaylistActivity extends AppCompatActivity implements VideoPlaylistInterfaces.view {

    @BindView(R.id.expandedImage) ImageView expandedImage;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar) AppBarLayout appBar;
    @BindView(R.id.error_image) ImageView errorImage;
    @BindView(R.id.error_text) TextView errorText;
    @BindView(R.id.llError) LinearLayout llError;
    @BindView(R.id.courseList) RecyclerView courseList;
    Dialog progressDialog;

    VideoPlaylistPresenter presenter;
    @BindView(R.id.btnRetry) MaterialButton btnRetry;

    String URL;
    @BindView(R.id.adView) AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_playlist);
        ButterKnife.bind(this);

        URL = getIntent().getStringExtra(AppConstants.COURSE_URL);
        toolbar.setTitle(getIntent().getStringExtra(AppConstants.COURSE_TITLE));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressDialog = DialogUtils.showLoadingDialog(this);
        GridSpacingItemDecoration spacingItemDecoration = new GridSpacingItemDecoration(1, 20, true);
        courseList.addItemDecoration(spacingItemDecoration);
        presenter = new VideoPlaylistPresenter(this);
        presenter.getCourseList(URL);

        // FOR ADMOB
        MobileAds.initialize(this, initializationStatus -> {
        });
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });
        adView.loadAd(adRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void showSuccess(VideoListResponse videoListResponse) {

        if (videoListResponse.getVideoItems().size() > 0) {

            llError.setVisibility(View.GONE);
            VideoPlayListAdapter adapter = new VideoPlayListAdapter(this, videoListResponse.getVideoItems());
            courseList.setAdapter(adapter);
            courseList.setLayoutManager(new LinearLayoutManager(this));
        } else {
            llError.setVisibility(View.VISIBLE);
            errorText.setText("No data available.");
            errorImage.setImageResource(R.drawable.warning);
        }

    }

    @Override
    public void showFailed(String err) {
        llError.setVisibility(View.VISIBLE);
        errorText.setText(err);
        errorImage.setImageResource(R.drawable.warning);
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void showInternetError() {
        llError.setVisibility(View.VISIBLE);
        errorText.setText("No Internet Connection");
        errorImage.setImageResource(R.drawable.no_wifi);
    }
    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        if (adView != null) {
            adView.pause();
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.pause();
        }
        super.onDestroy();
    }
    @OnClick(R.id.btnRetry)
    public void onViewClicked() {
        presenter.getCourseList(URL);
    }
}