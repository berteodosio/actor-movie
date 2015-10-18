package com.berteodosio.actormovie.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.berteodosio.actormovie.R;
import com.berteodosio.actormovie.activity.base.BaseActivity;
import com.berteodosio.actormovie.adapter.MovieListAdapter;
import com.berteodosio.actormovie.model.Movie;
import com.berteodosio.actormovie.presenter.MovieListPresenter;
import com.berteodosio.actormovie.view.MovieListView;

import java.util.List;

/**
 * Created by bernardo on 17/10/15.
 */
public class MovieListActivity extends BaseActivity implements MovieListView {
    public static final String EXTRA_ACTOR_ID = "actor_id";
    public static final String EXTRA_ACTOR_NAME = "actor_name";

    private MovieListPresenter mPresenter;
    private MovieListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list_activity);

        showLoading();
        Bundle extras = getIntent().getExtras();
        getSupportActionBar().setTitle(extras.getString(EXTRA_ACTOR_NAME));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mAdapter = new MovieListAdapter();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movie_list_activity_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        mPresenter = new MovieListPresenter(this);
        mPresenter.loadMovies(extras.getInt(EXTRA_ACTOR_ID));
    }

    @Override
    public void displayMovieList(List<Movie> movieList) {
        mAdapter.updateMovies(movieList);
        hideLoading();
    }
}
