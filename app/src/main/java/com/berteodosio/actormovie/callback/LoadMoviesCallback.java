package com.berteodosio.actormovie.callback;

import com.berteodosio.actormovie.model.MovieActor;

/**
 * Created by bernardo on 17/10/15.
 */
public interface LoadMoviesCallback {
    void onMoviesLoaded(MovieActor movies);
}
