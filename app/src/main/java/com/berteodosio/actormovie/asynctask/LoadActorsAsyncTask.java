package com.berteodosio.actormovie.asynctask;

import android.os.AsyncTask;

import com.berteodosio.actormovie.callback.LoadActorsCallback;
import com.berteodosio.actormovie.model.ActorSearchResult;
import com.berteodosio.actormovie.remote.client.LoadActorsRemoteClient;

/**
 * Created by bernardo on 17/10/15.
 */
public class LoadActorsAsyncTask extends AsyncTask<String, Void, ActorSearchResult> {
    private LoadActorsCallback mCallback;

    public LoadActorsAsyncTask(LoadActorsCallback callback) {
        mCallback = callback;
    }

    @Override
    protected ActorSearchResult doInBackground(String... params) {
        return LoadActorsRemoteClient.loadActors(params[0]);
    }

    @Override
    protected void onPostExecute(ActorSearchResult actors) {
        mCallback.onActorsLoaded(actors);
    }
}
