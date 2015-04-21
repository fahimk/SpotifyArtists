package com.fahim.spotifyartists.search;

import com.fahim.spotifyartists.api.model.Artist;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * Created by fahim on 4/20/15.
 */
public interface SearchListView extends MvpView {
    public void setData(List<Artist> artists);

    public void showLoading();

    public void showError(Throwable e);

    public void showSearchList();
}
