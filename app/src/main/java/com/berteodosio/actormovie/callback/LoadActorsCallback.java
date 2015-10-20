package com.berteodosio.actormovie.callback;

import com.berteodosio.actormovie.model.ActorSearchResult;

/**
 * Created by bernardo on 17/10/15.
 */
public interface LoadActorsCallback {
    void onActorsLoaded(ActorSearchResult actors, String searchedActorName);
}
