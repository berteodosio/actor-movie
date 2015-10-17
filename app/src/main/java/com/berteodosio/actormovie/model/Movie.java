package com.berteodosio.actormovie.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bernardo on 17/10/15.
 */
public class Movie {
    private boolean adult;
    private String character;
    @SerializedName("credit_id") private String creditId;
    private int id;
    @SerializedName("original_title") private String originalTitle;
    @SerializedName("poster_path") private String posterPath;
    @SerializedName("release_date") private String releaseDate;
    @SerializedName("title") private String title;

    public String title() {
        return title;
    }
}
