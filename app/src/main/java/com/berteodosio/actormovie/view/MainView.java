package com.berteodosio.actormovie.view;

import com.berteodosio.actormovie.model.Actor;

/**
 * Created by bernardo on 17/10/15.
 */
public interface MainView {
    void displayActorInfo(Actor actor);
    void displayActorNotFound(String searchedActorName);
    void displayNoInternetAccess();
}
