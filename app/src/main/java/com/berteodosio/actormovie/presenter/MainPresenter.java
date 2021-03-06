package com.berteodosio.actormovie.presenter;

import android.content.Context;

import com.berteodosio.actormovie.asynctask.LoadActorsAsyncTask;
import com.berteodosio.actormovie.callback.LoadActorsCallback;
import com.berteodosio.actormovie.info.InternetInfo;
import com.berteodosio.actormovie.model.Actor;
import com.berteodosio.actormovie.model.ActorSearchResult;
import com.berteodosio.actormovie.view.MainView;

import java.util.List;

public class MainPresenter implements LoadActorsCallback {
    private MainView mView;
    private Context mContext;
    
    public MainPresenter(MainView view, Context context) {
        mView = view;
        mContext = context;
    }

    public void getActorId(String actorName) {
        if (!InternetInfo.isConnectedToInternet(mContext)) {
            mView.displayNoInternetAccess();
            return;
        }
        new LoadActorsAsyncTask(this, actorName).execute();
    }

    @Override
    public void onActorsLoaded(ActorSearchResult actors, String searchedActorName) {
        List<Actor> actorList = actors.getActorsList();
        if (actorList.size() != 0)
            mView.displayActorInfo(actorList.get(0));
        else
            mView.displayActorNotFound(searchedActorName);
    }
}
