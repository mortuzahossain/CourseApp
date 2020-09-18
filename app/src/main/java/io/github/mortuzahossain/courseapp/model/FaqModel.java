package io.github.mortuzahossain.courseapp.model;
/*
 * Created by mortuza on 18/9/20 | 4:43 PM for CourseApp
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import com.google.firebase.Timestamp;

public class FaqModel {
    String title;
    String description;
    Timestamp date;

    public FaqModel(String title, String description, Timestamp date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public FaqModel() {
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description.replace("\\n", "\n");
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
