package com.fahim.spotifyartists.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fahim.spotifyartists.R;
import com.fahim.spotifyartists.api.model.Artist;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by fahim on 4/20/15.
 */
public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {
    Picasso picasso;
    private List<Artist> artists;

    public SearchListAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

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
        Artist artist = artists.get(position);

        if (artist.images.size() > 0) {
            String imageUrl = artist.images.get(0).url;
            SearchListAdapter.this.picasso.load(imageUrl).into(holder.image);
        }
        holder.name.setText(artist.name);
        holder.followers.setText(artist.followers.total + " followers");
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
        @InjectView(R.id.followers) TextView followers;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
