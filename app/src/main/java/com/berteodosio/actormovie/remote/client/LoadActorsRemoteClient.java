package com.berteodosio.actormovie.remote.client;

import com.berteodosio.actormovie.constants.Constants;
import com.berteodosio.actormovie.model.ActorSearchResult;
import com.berteodosio.actormovie.model.MovieActor;
import com.berteodosio.actormovie.remote.service.LoadActorsRemoteService;

import retrofit.RestAdapter;

/**
 * Created by bernardo on 17/10/15.
 */
public class LoadActorsRemoteClient {
    public static ActorSearchResult loadActors(String name) {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(Constants.API_URL)
                .build();

        LoadActorsRemoteService service = adapter.create(LoadActorsRemoteService.class);
        return service.loadActors(name);
    }

    public static MovieActor loadMovies(int actorId) {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(Constants.API_URL)
                .build();

        LoadActorsRemoteService service = adapter.create(LoadActorsRemoteService.class);
        return service.loadMovies(actorId);
    }
}
