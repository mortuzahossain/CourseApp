package io.github.mortuzahossain.courseapp.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.mortuzahossain.courseapp.R;
import io.github.mortuzahossain.courseapp.utils.YoutubeConfig;

import static io.github.mortuzahossain.courseapp.database.AppConstants.VIDEO_ID;

public class VideoPlayerActivity extends AppCompatActivity {

    @BindView(R.id.youtubePlayer) YouTubePlayerView youtubePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_video_player);
        ButterKnife.bind(this);

        String videoId = getIntent().getStringExtra(VIDEO_ID);

        YouTubePlayer.OnInitializedListener onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoId);
                youTubePlayer.setFullscreen(true);
                youTubePlayer.setShowFullscreenButton(false);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(VideoPlayerActivity.this, "Try Again.", Toast.LENGTH_SHORT).show();
            }
        };

        youtubePlayer.initialize(YoutubeConfig.getApiKey(),onInitializedListener);

    }
}