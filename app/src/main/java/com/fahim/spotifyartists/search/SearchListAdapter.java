package com.fahim.spotifyartists.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fahim.spotifyartists.R;
import com.fahim.spotifyartists.api.model.Artist;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by fahim on 4/20/15.
 */
public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {
    private List<Artist> artists;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_artist, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,
            int position) {
        holder.bindModel(artists.get(position));
    }

    @Override
    public int getItemCount() {
        return artists == null ? 0 : artists.size();
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.image) ImageView image;
        @InjectView(R.id.name) TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void bindModel(Artist artist) {
            name.setText(artist.name);
        }

    }
}
