package com.berteodosio.actormovie.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.berteodosio.actormovie.R;
import com.berteodosio.actormovie.activity.base.BaseActivity;
import com.berteodosio.actormovie.adapter.MovieListAdapter;
import com.berteodosio.actormovie.model.Movie;
import com.berteodosio.actormovie.presenter.MovieListPresenter;
import com.berteodosio.actormovie.view.MovieListView;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends BaseActivity implements MovieListView {
    public static final String EXTRA_ACTOR_ID_LIST = "actor_id";
    public static final String EXTRA_ACTOR_NAME_LIST = "actor_name";
    public static final int REQUEST_CODE = 1;

    private MovieListPresenter mPresenter;
    private MovieListAdapter mAdapter;

    private List<Integer> mActorIds;
    private List<List<Movie>> mActorsMoviesList = new ArrayList<>();

    private View mContent;  // para evitar vários findViewById na snackbar

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list_activity);

        showLoading();

        mContent = findViewById(android.R.id.content);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(R.string.movie_list_activity_toolbarTitle);

        mAdapter = new MovieListAdapter();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movie_list_activity_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        Bundle extras = getIntent().getExtras();

        mActorIds = (List) extras.get(EXTRA_ACTOR_ID_LIST);
        List<String> actorNames = (List) extras.get(EXTRA_ACTOR_NAME_LIST);

        TextView textView = (TextView) findViewById(R.id.movie_list_activity_actorName);

        String text = "";
        for (String s : actorNames)
            text += s + "\n";

        textView.setText(text);

        if (actorNames.size() == 1)
            ((TextView) findViewById(R.id.movie_list_activity_searchedActors))
                    .setText(R.string.movie_list_activity_serachedActors_single);

        mPresenter = new MovieListPresenter(this, this);
        for (Integer i : mActorIds)
            mPresenter.loadMovies(i);
    }

    @Override
    public void displayMovieList(List<Movie> movieList) {
        mActorsMoviesList.add(movieList);
        if (mActorsMoviesList.size() == mActorIds.size()) {
            hideLoading();
            List<Movie> moviesFromAllActors = mPresenter.searchMoviesFromAllActors(mActorsMoviesList);
            mAdapter.updateMovies(moviesFromAllActors);

            if (moviesFromAllActors.size() == 1)
                ((TextView) findViewById(R.id.movie_list_activity_foundedMovies))
                        .setText(R.string.movie_list_activity_foundedMovies_single);
        }
    }

    @Override
    public void displayNoInternetAccess() {
        hideLoading();
        Snackbar.make(mContent, "Sem acesso à internet", Snackbar.LENGTH_LONG);
    }
}
