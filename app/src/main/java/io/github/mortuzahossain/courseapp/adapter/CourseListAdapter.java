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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.mortuzahossain.courseapp.R;
import io.github.mortuzahossain.courseapp.activities.VideoPlaylistActivity;
import io.github.mortuzahossain.courseapp.database.AppConstants;
import io.github.mortuzahossain.courseapp.model.CourseListResponse;


public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseListViewHolder> {
    Context context;
    public List<CourseListResponse> courseListResponses;

    public CourseListAdapter(Context context, List<CourseListResponse> courseListResponses) {
        this.context = context;
        this.courseListResponses = courseListResponses;
    }

    @NonNull
    @Override
    public CourseListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new CourseListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseListViewHolder holder, int position) {
        CourseListResponse model = courseListResponses.get(position);
        holder.tvTitle.setText(model.getTitle());
        holder.tvDetails.setText(model.getDescription());

        Glide.with(holder.ivLogo.getContext())
                .load(model.getImageUrl())
                .thumbnail(Glide.with(holder.ivLogo.getContext())
                        .load(R.drawable.ezgifresize)
                )
                .into(holder.ivLogo);

        holder.itemView.setOnClickListener(view -> {
            if (model.isIsAvailable()) {
                Intent intent = new Intent(context, VideoPlaylistActivity.class);
                intent.putExtra(AppConstants.COURSE_TITLE, model.getTitle());
                intent.putExtra(AppConstants.COURSE_URL, model.getCourseListUrl());
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            } else {
                Toast.makeText(context, "Course Not Available", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseListResponses.size();
    }

    public class CourseListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle) TextView tvTitle;
        @BindView(R.id.tvDetails) TextView tvDetails;
        @BindView(R.id.ivLogo) ImageView ivLogo;

        public CourseListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
