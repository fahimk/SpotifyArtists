package com.fahim.spotifyartists.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import com.fahim.spotifyartists.R;
import com.fahim.spotifyartists.SpotifyArtistsApp;
import com.hannesdorfmann.mosby.dagger1.Injector;

import javax.inject.Inject;

import dagger.ObjectGraph;


public class SearchActivity extends ActionBarActivity implements Injector {
    @Inject SpotifyArtistsApp spotifyArtistsApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        if (savedInstanceState == null) {
            Fragment f = new SearchFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, f).commit();
        }
    }

    @Override
    public ObjectGraph getObjectGraph() {
        SpotifyArtistsApp app = (SpotifyArtistsApp) getApplication();
        return app.getObjectGraph();
    }
}