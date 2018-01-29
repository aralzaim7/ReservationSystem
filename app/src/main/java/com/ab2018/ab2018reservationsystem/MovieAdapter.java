package com.ab2018.ab2018reservationsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

/**
 * Created by metuncc on 1/29/18.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    Context resContext;

    public MovieAdapter(Context context, int resource, @NonNull List<Movie> objects) {
        super(context, resource, objects);
        resContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Movie movie = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.movieName);
        ImageView imageMovie = (ImageView) convertView.findViewById(R.id.movieImage);

        name.setText(movie.getName());

        int res = resContext.getResources().getIdentifier(movie.getImageCode(), "drawable", resContext.getPackageName());
        imageMovie.setImageResource(res);

        return convertView;

    }
}
