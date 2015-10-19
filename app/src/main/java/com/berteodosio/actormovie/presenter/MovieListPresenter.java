package com.berteodosio.actormovie.presenter;

import com.berteodosio.actormovie.asynctask.LoadMoviesAsyncTask;
import com.berteodosio.actormovie.callback.LoadMoviesCallback;
import com.berteodosio.actormovie.model.Movie;
import com.berteodosio.actormovie.model.MovieActor;
import com.berteodosio.actormovie.view.MovieListView;

import java.util.ArrayList;
import java.util.List;

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

    public List<Movie> searchMoviesFromAllActors(List<List<Movie>> actorsMoviesList) {
        List<Movie> movieList = new ArrayList<>();

        for (List<Movie> list : actorsMoviesList) {
            for (Movie movie : list) {
                boolean allActorsInMovie = true;
                for (List<Movie> anotherList : actorsMoviesList) {
                    if (!anotherList.contains(movie)) {
                        allActorsInMovie = false;
                        break;
                    }
                }

                if (allActorsInMovie && !movieList.contains(movie))
                    movieList.add(movie);
            }
        }

        return movieList;
    }
}
