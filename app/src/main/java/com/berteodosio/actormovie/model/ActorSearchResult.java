package com.berteodosio.actormovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bernardo on 17/10/15.
 */
public class ActorSearchResult {
    private int page;
    private List<Actor> results;
    @SerializedName("total_pages") private int totalPages;
    @SerializedName("total_results") private int totalResults;

    public List<Actor> getActorsList() {
        return results;
    }
}
