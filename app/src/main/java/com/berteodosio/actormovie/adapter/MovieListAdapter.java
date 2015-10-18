package com.berteodosio.actormovie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.berteodosio.actormovie.R;
import com.berteodosio.actormovie.model.Movie;

import java.util.List;

/**
 * Created by bernardo on 17/10/15.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<Movie> mMovies;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_recycler_view_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie m = mMovies.get(position);
        holder.movieTitle().setText(m.title());
    }

    @Override
    public int getItemCount() {
        return mMovies != null? mMovies.size() : 0;
    }

    public void updateMovies(List<Movie> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View mRoot;
        private TextView mMovieTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mRoot = itemView;
            mMovieTitle = (TextView) mRoot.findViewById(R.id.movie_list_recyclerView_movieTitle);
        }

        public TextView movieTitle() {
            return mMovieTitle;
        }
    }
}
