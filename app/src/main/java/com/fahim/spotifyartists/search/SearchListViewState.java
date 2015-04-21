package com.fahim.spotifyartists.search;

import android.os.Bundle;

import com.hannesdorfmann.mosby.mvp.viewstate.RestoreableViewState;

/**
 * Created by fahim on 4/21/15.
 */
public class SearchListViewState implements RestoreableViewState<SearchListView> {
    private final String KEY_STATE = getClass().getName().toString() + "currentState";

    private final int STATE_SHOWING_SEARCH_LIST = 0;
    private final int STATE_SHOWING_LOADING = 1;

    private int currentState = 0;

    @Override
    public void saveInstanceState(Bundle out) {
        out.putInt(KEY_STATE, currentState);
    }

    @Override
    public boolean restoreInstanceState(Bundle in) {
        currentState = in.getInt(KEY_STATE);
        return false;
    }

    @Override
    public void apply(SearchListView searchListView,
            boolean retained) {
        if (currentState == STATE_SHOWING_SEARCH_LIST) {
            searchListView.showSearchList();
        }
        else {
            searchListView.showLoading();
        }
    }

    public void setStateShowSearchList() {
        currentState = STATE_SHOWING_SEARCH_LIST;
    }

    public void setStateShowLoading() {
        currentState = STATE_SHOWING_LOADING;
    }

}
