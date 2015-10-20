package com.berteodosio.actormovie.asynctask;

import android.os.AsyncTask;

import com.berteodosio.actormovie.callback.LoadActorsCallback;
import com.berteodosio.actormovie.model.ActorSearchResult;
import com.berteodosio.actormovie.remote.client.LoadActorsRemoteClient;

/**
 * Created by bernardo on 17/10/15.
 */
public class LoadActorsAsyncTask extends AsyncTask<Void, Void, ActorSearchResult> {
    private LoadActorsCallback mCallback;
    private String mSearchedActorName;

    public LoadActorsAsyncTask(LoadActorsCallback callback, String searchedActorName) {
        mCallback = callback;
        mSearchedActorName = searchedActorName;
    }

    @Override
    protected ActorSearchResult doInBackground(Void... params) {
        return LoadActorsRemoteClient.loadActors(mSearchedActorName);
    }

    @Override
    protected void onPostExecute(ActorSearchResult actors) {
        mCallback.onActorsLoaded(actors, mSearchedActorName);
    }
}
