package com.fahim.spotifyartists.presenters;

import com.fahim.spotifyartists.api.SpotifyService;
import com.fahim.spotifyartists.api.model.ArtistSearchResponse;
import com.fahim.spotifyartists.interfaces.SearchListPresenter;
import com.fahim.spotifyartists.interfaces.SearchListView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by fahim on 4/20/15.
 */
public class SimpleSearchListPresenter extends MvpBasePresenter<SearchListView> implements SearchListPresenter {
    SpotifyService spotifyService;

    public SimpleSearchListPresenter(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @Override
    public void searchForArtists(String query) {
        if (isViewAttached()) {
            getView().showLoading(false);
        }

        spotifyService.artistSearch(query)
                .observeOn(AndroidSchedulers.mainThread())
                .delay(10, TimeUnit.SECONDS)
                .subscribe(new Subscriber<ArtistSearchResponse>() {
                    @Override
                    public void onCompleted() {
                        if (isViewAttached()) {
                            getView().showContent();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().showContent();
                        }
                    }

                    @Override
                    public void onNext(ArtistSearchResponse artistSearchResponse) {
                        if (isViewAttached()) {
                            getView().setData(artistSearchResponse.artists.items);
                            getView().showContent();
                        }
                    }
                });
    }
}
