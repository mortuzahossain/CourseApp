package io.github.mortuzahossain.courseapp.model;

import com.google.gson.annotations.SerializedName;

public class CourseListResponse{

	@SerializedName("isAvailable")
	private boolean isAvailable;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("courseListUrl")
	private String courseListUrl;

	@SerializedName("description")
	private String description;

	@SerializedName("title")
	private String title;

	public boolean isIsAvailable(){
		return isAvailable;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public String getCourseListUrl(){
		return courseListUrl;
	}

	public String getDescription(){
		return description;
	}

	public String getTitle(){
		return title;
	}
}