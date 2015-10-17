package com.berteodosio.actormovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bernardo on 17/10/15.
 */
public class Actor {
    private boolean adult;
    private int id;
    @SerializedName("known_for") private List<Actor.Movie> knownFor;
    private String name;
    private double popularity;
    @SerializedName("profile_path") private String profilePath;

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    private class Movie {
        private boolean adult;
        @SerializedName("backdrop_path") private String backdropPath;
        @SerializedName("genre_ids") private List<Integer> genreIds;
        private int id;
        @SerializedName("original_language") private String originalLanguage;
        @SerializedName("original_title") private String originalTitle;
        @SerializedName("overview") private String overview;
        @SerializedName("release_date") private String releaseDate;
        @SerializedName("poster_path") private String posterPath;
        private double popularity;
        private String title;
        private boolean video;
        @SerializedName("vote_average") private double voteAverage;
        @SerializedName("vote_count") private int voteCount;
        @SerializedName("media_type") private String mediaType;
    }

}
