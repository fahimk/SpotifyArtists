package com.fahim.spotifyartists;

import android.app.Application;

import dagger.ObjectGraph;

/**
 * Created by fahim on 4/20/15.
 */
public class SpotifyArtistsApp extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(new SpotifyArtistsModule(this));
        objectGraph.inject(this);
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
