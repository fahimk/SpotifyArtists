package com.fahim.spotifyartists;

import com.fahim.spotifyartists.api.NetworkModule;
import com.fahim.spotifyartists.search.SearchActivity;
import com.fahim.spotifyartists.search.SearchFragment;
import com.fahim.spotifyartists.search.SearchListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fahim on 4/20/15.
 */

@Module(
        includes = {
                NetworkModule.class
        },
        injects = {
                SearchActivity.class,
                SearchListPresenter.class,
                SearchFragment.class,
                SpotifyArtistsApp.class
        }
)
public class SpotifyArtistsModule {
    private final SpotifyArtistsApp app;
    public SpotifyArtistsModule(SpotifyArtistsApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    SpotifyArtistsApp provideApplication() {
        return app;
    }
}
