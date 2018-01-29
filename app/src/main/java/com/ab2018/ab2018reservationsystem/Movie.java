package com.ab2018.ab2018reservationsystem;

import java.io.Serializable;

/**
 * Created by metuncc on 1/29/18.
 */

public class Movie  implements Serializable {

    int id;
    String name;
    String overView;
    String imageCode;
    String language;
    double vote;

    public Movie(int id, String name, String overView, String imageCode, String language, double vote) {
        this.id = id;
        this.name = name;
        this.overView = overView;
        this.imageCode = imageCode;
        this.language = language;
        this.vote = vote;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverView() {
        return overView;
    }

    public String getImageCode() {
        return imageCode;
    }

    public String getLanguage() {
        return language;
    }

    public double getVote() {
        return vote;
    }
}
