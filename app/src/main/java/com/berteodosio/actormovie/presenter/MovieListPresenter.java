package com.berteodosio.actormovie.presenter;

import com.berteodosio.actormovie.asynctask.LoadMoviesAsyncTask;
import com.berteodosio.actormovie.callback.LoadMoviesCallback;
import com.berteodosio.actormovie.model.MovieActor;
import com.berteodosio.actormovie.view.MovieListView;

/**
 * Created by bernardo on 17/10/15.
 */
public class MovieListPresenter implements LoadMoviesCallback {
    private MovieListView mView;

    public MovieListPresenter(MovieListView view) {
        mView = view;
    }


    public void loadMovies(int actorId) {
        new LoadMoviesAsyncTask(this).execute(actorId);
    }

    @Override
    public void onMoviesLoaded(MovieActor movies) {
        mView.displayMovieList(movies.getMovieList());
    }
}
