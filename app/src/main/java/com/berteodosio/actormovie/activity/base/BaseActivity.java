package com.berteodosio.actormovie.activity.base;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.berteodosio.actormovie.R;

public class BaseActivity extends AppCompatActivity {
    private ViewGroup mProgressBarLayout;

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.base_activity, null);
        ViewGroup content = (ViewGroup) root.findViewById(R.id.base_content);
        inflater.inflate(layoutResID, content, true);

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressBarLayout = (ViewGroup) root.findViewById(R.id.base_progressBar_layout);
        super.setContentView(root);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showLoading() {
        mProgressBarLayout.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        mProgressBarLayout.setVisibility(View.GONE);
    }
}
