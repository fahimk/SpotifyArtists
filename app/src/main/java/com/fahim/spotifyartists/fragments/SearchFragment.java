package com.fahim.spotifyartists.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.fahim.spotifyartists.R;
import com.fahim.spotifyartists.SpotifyArtistsApp;
import com.fahim.spotifyartists.adapters.SearchListAdapter;
import com.fahim.spotifyartists.api.SpotifyService;
import com.fahim.spotifyartists.api.model.Artist;
import com.fahim.spotifyartists.interfaces.SearchListView;
import com.fahim.spotifyartists.presenters.SimpleSearchListPresenter;
import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingFragmentLceViewState;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * Created by fahim on 4/20/15.
 */
public class SearchFragment extends MvpViewStateFragment<SimpleSearchListPresenter> implements SearchListView {
    public static final int VIEWFLIPPER_RESULTS = 0;
    public static final int VIEWFLIPPER_LOADING = 1;

    @Inject SpotifyService spotifyService;

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
        searchListAdapter = searchListAdapter == null ? new SearchListAdapter() : searchListAdapter;
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

    @Override
    protected SimpleSearchListPresenter createPresenter() {
        return new SimpleSearchListPresenter(spotifyService);
    }

    @Override
    public void showLoading(boolean b) {
        viewFlipper.setDisplayedChild(VIEWFLIPPER_LOADING);
    }

    @Override
    public void showContent() {
        viewFlipper.setDisplayedChild(VIEWFLIPPER_RESULTS);
    }

    @Override
    public void showError(Throwable throwable,
            boolean b) {
        viewFlipper.setDisplayedChild(VIEWFLIPPER_RESULTS);
    }

    @Override
    public void setData(List<Artist> artists) {
        searchListAdapter.setArtists(artists);
        searchListAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean b) {

    }

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        SpotifyArtistsApp app = (SpotifyArtistsApp) getActivity().getApplication();
        app.getObjectGraph().inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_search;
    }

    @Override
    public ViewState createViewState() {
        return new RetainingFragmentLceViewState<List<Artist>, SearchListView>(this);
    }


    @Override
    public void onNewViewStateInstance() {
        showContent();
    }
}
