package com.berteodosio.actormovie.view;

import com.berteodosio.actormovie.model.Movie;

import java.util.List;

/**
 * Created by bernardo on 17/10/15.
 */
public interface MovieListView {
    void displayMovieList(List<Movie> movieList);
    void displayNoInternetAccess();
}
