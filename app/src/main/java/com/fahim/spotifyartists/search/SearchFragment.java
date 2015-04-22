package com.fahim.spotifyartists.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.fahim.spotifyartists.R;
import com.fahim.spotifyartists.SpotifyArtistsApp;
import com.fahim.spotifyartists.api.SpotifyService;
import com.fahim.spotifyartists.api.model.Artist;
import com.hannesdorfmann.mosby.dagger1.viewstate.Dagger1MvpViewStateFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.RestoreableViewState;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * Created by fahim on 4/20/15.
 */
public class SearchFragment extends Dagger1MvpViewStateFragment<SearchListPresenter> implements SearchListView {
    public static final int VIEWFLIPPER_RESULTS = 0;
    public static final int VIEWFLIPPER_LOADING = 1;

    @Inject SpotifyService spotifyService;
    @Inject SpotifyArtistsApp app;
    @Inject Picasso picasso;

    @InjectView(R.id.search_box) EditText searchBox;
    @InjectView(R.id.recycler_view) RecyclerView recyclerView;
    @InjectView(R.id.view_flipper) ViewFlipper viewFlipper;

    private SearchListAdapter searchListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view,
            Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchListAdapter = searchListAdapter == null ? new SearchListAdapter(picasso) : searchListAdapter;
        recyclerView.setAdapter(searchListAdapter);

        searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v,
                    int actionId,
                    KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.searchForArtists(v.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    @Override protected void injectDependencies() {
        getObjectGraph().inject(this);
    }

    @Override
    protected SearchListPresenter createPresenter() {
        return new SearchListPresenter(spotifyService);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_search;
    }

    @Override
    public RestoreableViewState createViewState() {
        return new SearchListViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        showSearchList();
    }

    @Override
    public void setData(List<Artist> artists) {
        searchListAdapter.setArtists(artists);
        searchListAdapter.notifyDataSetChanged();
    }

    @Override
    public SearchListViewState getViewState() {
        return (SearchListViewState) super.getViewState();
    }

    @Override
    public void showSearchList() {
        getViewState().setStateShowSearchList();
        viewFlipper.setDisplayedChild(VIEWFLIPPER_RESULTS);
    }

    @Override
    public void showLoading() {
        getViewState().setStateShowLoading();
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchBox.getWindowToken(), 0);

        viewFlipper.setDisplayedChild(VIEWFLIPPER_LOADING);
    }

    @Override
    public void showError(Throwable e) {
        viewFlipper.setDisplayedChild(VIEWFLIPPER_RESULTS);
        Toast.makeText(app, "error: " + e.getMessage().toString(), Toast.LENGTH_LONG).show();
    }

}
