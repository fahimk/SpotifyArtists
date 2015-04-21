package com.fahim.spotifyartists.interfaces;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

/**
 * Created by fahim on 4/21/15.
 */
public interface SearchListPresenter extends MvpPresenter<SearchListView> {

    public void searchForArtists(final String query);
}