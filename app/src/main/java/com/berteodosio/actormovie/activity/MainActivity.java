package com.berteodosio.actormovie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.berteodosio.actormovie.R;
import com.berteodosio.actormovie.activity.base.BaseActivity;
import com.berteodosio.actormovie.adapter.MovieListAdapter;
import com.berteodosio.actormovie.animation.ViewAnimation;
import com.berteodosio.actormovie.model.Actor;
import com.berteodosio.actormovie.model.Movie;
import com.berteodosio.actormovie.presenter.MainPresenter;
import com.berteodosio.actormovie.validator.Validator;
import com.berteodosio.actormovie.validator.general.EditTextValidator;
import com.berteodosio.actormovie.view.MainView;

import java.util.List;

public class MainActivity extends BaseActivity implements MainView {
    private MainPresenter mPresenter;

    private EditText mName;
    private Button mShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mPresenter = new MainPresenter(this);
        initComponents();
    }

    private void initComponents() {
        mName = (EditText) findViewById(R.id.main_activity_actorName);
        mShow = (Button) findViewById(R.id.main_activity_showMovies);

        initListeners();
    }

    private void initListeners() {
        mShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowClick();
            }
        });

        mName.addTextChangedListener(new EditTextValidator(mName) {
            @Override
            public void validate(EditText editText) {
                Validator.validateName(editText);
            }
        });
    }

    private void onShowClick() {
        if (mName.getError() == null && !mName.getText().toString().equals("")) {
            showLoading();
            ViewAnimation.doContractHorizontalAnimation(mShow);
            mPresenter.getActorId(mName.getText().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayActorInfo(Actor actor) {
        hideLoading();
        Intent intent = new Intent(this, MovieListActivity.class);
        intent.putExtra(MovieListActivity.EXTRA_ACTOR_ID, actor.id());
        intent.putExtra(MovieListActivity.EXTRA_ACTOR_NAME, actor.name());
        startActivity(intent);
    }

    @Override
    public void displayNoActorsFound() {
        Snackbar.make(mName, "Nenhum ator encontrado", Snackbar.LENGTH_LONG)
                .show();
    }
}
