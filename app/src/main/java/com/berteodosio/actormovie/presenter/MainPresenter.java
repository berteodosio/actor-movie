package com.berteodosio.actormovie.presenter;

import com.berteodosio.actormovie.asynctask.LoadActorsAsyncTask;
import com.berteodosio.actormovie.callback.LoadActorsCallback;
import com.berteodosio.actormovie.model.Actor;
import com.berteodosio.actormovie.model.ActorSearchResult;
import com.berteodosio.actormovie.view.MainView;

import java.util.List;

public class MainPresenter implements LoadActorsCallback {
    private MainView mView;
    
    public MainPresenter(MainView view) {
        mView = view;
    }

    public void getActorId(String actorName) {
        new LoadActorsAsyncTask(this).execute(actorName);
    }

    @Override
    public void onActorsLoaded(ActorSearchResult actors) {
        List<Actor> actorList = actors.getActorsList();
        if (actorList.size() != 0)
            mView.displayActorInfo(actorList.get(0));
        else
            mView.displayNoActorsFound();
    }
}
