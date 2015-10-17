package com.berteodosio.actormovie.asynctask;

import android.os.AsyncTask;

import com.berteodosio.actormovie.callback.LoadMoviesCallback;
import com.berteodosio.actormovie.model.MovieActor;
import com.berteodosio.actormovie.remote.client.LoadActorsRemoteClient;

/**
 * Created by bernardo on 17/10/15.
 */
public class LoadMoviesAsyncTask extends AsyncTask<Integer, Void, MovieActor> {
    private LoadMoviesCallback mCallback;

    public LoadMoviesAsyncTask(LoadMoviesCallback callback) {
        mCallback = callback;
    }

    @Override
    protected MovieActor doInBackground(Integer... params) {
        return LoadActorsRemoteClient.loadMovies(params[0]);
    }

    @Override
    protected void onPostExecute(MovieActor movies) {
        mCallback.onMoviesLoaded(movies);
    }
}
