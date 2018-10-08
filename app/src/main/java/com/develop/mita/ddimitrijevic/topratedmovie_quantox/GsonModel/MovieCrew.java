package com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel;

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.ViewHolerType;
import com.google.gson.annotations.SerializedName;

import static com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.ViewHolderType.MOVIE_CREW_CARD_RECYCLER_VIEW_HOLDER;

public class MovieCrew implements ViewHolerType {
    @SerializedName("credit_id")
    private String creditId;

    private String department;

    private int gender;

    private int id;

    private String job;

    private String name;

    @SerializedName("profile_path")
    private String profilePath;

    @Override
    public int getTypeViewHolder() {
        return MOVIE_CREW_CARD_RECYCLER_VIEW_HOLDER;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
