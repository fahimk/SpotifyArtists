package com.fahim.spotifyartists.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import com.fahim.spotifyartists.R;
import com.fahim.spotifyartists.api.SpotifyService;
import com.fahim.spotifyartists.fragments.SearchFragment;

import javax.inject.Inject;


public class SearchActivity extends ActionBarActivity {
    @Inject SpotifyService spotifyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        if (savedInstanceState == null) {
            Fragment f = new SearchFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, f).commit();
        }
    }
}