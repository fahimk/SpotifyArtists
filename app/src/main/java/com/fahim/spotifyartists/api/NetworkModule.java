package com.fahim.spotifyartists.api;

import com.fahim.spotifyartists.SpotifyArtistsApp;
import com.fahim.spotifyartists.search.SearchFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by fahim on 4/19/15.
 */

@Module(
        injects = {
                SearchFragment.class
        },
        complete = false,
        library = true
)

public class NetworkModule {

    public static final String PRODUCTION_API_URL = "https://api.spotify.com/v1";

    @Provides
    @Singleton
    Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(PRODUCTION_API_URL);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .create();
    }

    @Provides
    @Singleton
    Picasso providesPicasso(SpotifyArtistsApp app,
            OkHttpClient client) {
        return new Picasso.Builder(app)
                .downloader(new OkHttpDownloader(client))
                .build();
    }

    @Provides
    @Singleton
    RestAdapter provideRestAdapter(Endpoint endpoint,
            OkHttpClient client,
            Gson gson) {
        return new RestAdapter.Builder()
                .setClient(new OkClient(client))
                .setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();
    }

    @Provides
    @Singleton
    SpotifyService proivdeSpotifyService(RestAdapter restAdapter) {
        return restAdapter.create(SpotifyService.class);
    }
}
