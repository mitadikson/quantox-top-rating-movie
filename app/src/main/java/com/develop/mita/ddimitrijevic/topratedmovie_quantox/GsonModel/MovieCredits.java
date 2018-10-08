package com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel;

import java.util.List;

public class MovieCredits {

    private int id;

    private List<MovieCast> cast;

    private List<MovieCrew> crew;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MovieCast> getCast() {
        return cast;
    }

    public void setCast(List<MovieCast> cast) {
        this.cast = cast;
    }

    public List<MovieCrew> getCrew() {
        return crew;
    }

    public void setCrew(List<MovieCrew> crew) {
        this.crew = crew;
    }
}
