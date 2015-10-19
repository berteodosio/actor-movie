package com.berteodosio.actormovie.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO: arrumar loading infinito quando busca, volta, busca
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list_activity);

        showLoading();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Filmes");

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

        mPresenter = new MovieListPresenter(this);
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
        }
    }
}
