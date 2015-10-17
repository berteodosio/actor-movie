package com.berteodosio.actormovie.presenter;

import com.berteodosio.actormovie.asynctask.LoadActorsAsyncTask;
import com.berteodosio.actormovie.asynctask.LoadMoviesAsyncTask;
import com.berteodosio.actormovie.callback.LoadActorsCallback;
import com.berteodosio.actormovie.callback.LoadMoviesCallback;
import com.berteodosio.actormovie.model.ActorSearchResult;
import com.berteodosio.actormovie.model.MovieActor;
import com.berteodosio.actormovie.view.MainView;

public class MainPresenter implements LoadActorsCallback, LoadMoviesCallback {
    private MainView mView;
    
    public MainPresenter(MainView view) {
        this.mView = view;
    }

    public void loadActorMovies(String actorName) {
        new LoadActorsAsyncTask(this).execute(actorName);
    }

    @Override
    public void onActorsLoaded(ActorSearchResult actors) {
        int actorId = actors.getActorsList().get(0).id();
        new LoadMoviesAsyncTask(this).execute(actorId);
    }

    @Override
    public void onMoviesLoaded(MovieActor movies) {
        mView.displayMovieList(movies.getMovieList());
    }
}
