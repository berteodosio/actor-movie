package com.berteodosio.actormovie.remote.service;

import com.berteodosio.actormovie.constants.ApiConfiguration;
import com.berteodosio.actormovie.model.ActorSearchResult;
import com.berteodosio.actormovie.model.MovieActor;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by bernardo on 17/10/15.
 */
public interface LoadActorsRemoteService {
    @GET("/3/search/person?api_key=" + ApiConfiguration.API_KEY)
    ActorSearchResult loadActors(
            @Query("query") String name
    );

    @GET("/3/person/{id}/credits?api_key=" + ApiConfiguration.API_KEY)
    MovieActor loadMovies(
            @Path("id") int actorId
    );
}
