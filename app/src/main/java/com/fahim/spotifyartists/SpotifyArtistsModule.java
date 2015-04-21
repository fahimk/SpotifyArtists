package com.fahim.spotifyartists;

import com.fahim.spotifyartists.api.ApiModule;

import dagger.Module;

/**
 * Created by fahim on 4/20/15.
 */

@Module(
        includes = {
                ApiModule.class
        },
        injects = {
                SpotifyArtistsApp.class
        }
)
public class SpotifyArtistsModule {
    private final SpotifyArtistsApp app;
    public SpotifyArtistsModule(SpotifyArtistsApp app) {
        this.app = app;
    }
}
