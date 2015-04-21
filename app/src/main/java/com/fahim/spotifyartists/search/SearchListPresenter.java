package com.fahim.spotifyartists.search;

import com.fahim.spotifyartists.api.SpotifyService;
import com.fahim.spotifyartists.api.model.ArtistSearchResponse;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by fahim on 4/20/15.
 */
public class SearchListPresenter extends MvpBasePresenter<SearchListView>  {
    SpotifyService spotifyService;

    public SearchListPresenter(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    public void searchForArtists(String query) {
        if (isViewAttached()) {
            getView().showLoading();
        }

        spotifyService.artistSearch(query)
                .delay(10, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArtistSearchResponse>() {
                    @Override
                    public void onCompleted() {
                        if (isViewAttached()) {
                            getView().showSearchList();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().showError(e);
                        }
                    }

                    @Override
                    public void onNext(ArtistSearchResponse artistSearchResponse) {
                        if (isViewAttached()) {
                            getView().setData(artistSearchResponse.artists.items);
                        }
                    }
                });
    }
}
