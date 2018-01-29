package com.ab2018.ab2018reservationsystem;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ab2018.ab2018reservationsystem.databinding.ActivityMovieDetailsBinding;

public class MovieDetailsActivity extends AppCompatActivity {

    Intent movieDetailIntent;
    Movie movie;
    //layout ve data seklinde olması lazim xml tarafinin ki activitymoviedetailsbinding tanınsın
    //build.gradle içerisinde android içerisine  dataBinding enabled = true;} yazılması lazım
    // rebuild yapıldıkdan sonra tanınır sonra herşey sorunsuzsa aşağıdaki class otomatik oluşturulur
    ActivityMovieDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_movie_details);
        // yukarıdaki setContentView commentlenir ve neyi gösterecem sorusuna cevabi alt taraf verir.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        movieDetailIntent = getIntent();
        movie = (Movie) movieDetailIntent.getSerializableExtra("movie");
        binding.setMovie(movie);

    }
}
