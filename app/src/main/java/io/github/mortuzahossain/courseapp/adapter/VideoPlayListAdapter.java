package io.github.mortuzahossain.courseapp.adapter;
/*
 * Created by mortuza on 17/9/20 | 2:21 PM for CourseApp
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.mortuzahossain.courseapp.R;
import io.github.mortuzahossain.courseapp.activities.VideoPlayerActivity;
import io.github.mortuzahossain.courseapp.database.AppConstants;
import io.github.mortuzahossain.courseapp.model.VideoItem;

public class VideoPlayListAdapter extends RecyclerView.Adapter<VideoPlayListAdapter.VideoPlayListViewHolder> {

    Context context;
    List<VideoItem> videoItems;

    public VideoPlayListAdapter(Context context, List<VideoItem> videoItems) {
        this.context = context;
        this.videoItems = videoItems;
    }

    @NonNull
    @Override
    public VideoPlayListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_list, parent, false);
        return new VideoPlayListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoPlayListViewHolder holder, int position) {
        VideoItem model = videoItems.get(position);
        holder.tvNumber.setText(model.getClassNo());
        holder.tvTitle.setText(model.getTopic());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, VideoPlayerActivity.class);
            intent.putExtra(AppConstants.VIDEO_ID, model.getVideoURL());
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
        });
    }

    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    public class VideoPlayListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvNumber) TextView tvNumber;
        @BindView(R.id.tvTitle) TextView tvTitle;

        public VideoPlayListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
