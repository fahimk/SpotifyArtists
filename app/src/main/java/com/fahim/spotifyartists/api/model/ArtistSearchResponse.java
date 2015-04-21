package com.fahim.spotifyartists.api.model;

import java.util.List;

/**
 * Created by fahim on 4/19/15.
 */
public class ArtistSearchResponse {
    public Artists artists;

    public class Artists {
        public List<Artist> items;
    }
}
