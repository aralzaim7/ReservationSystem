package com.ab2018.ab2018reservationsystem;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView movieLv;
    MovieAdapter mvAdaptor;
    ArrayList<Movie> movies;
    MovieDBAdapter mdbAdapter;
    Movie tmpMovie;
    Cursor c;

    int id;
    String name;
    String overView;
    String imageCode;
    String language;
    double vote;

    Intent intentDetails;
    Intent intentReservations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            String destPath = "/data/data/" + getPackageName()
                    + "/databases/FilmDB";
            File file = new File(destPath);
            File path = new File("/data/data/" + getPackageName()
                    + "/databases/");

            Log.d("Log", "Exists");

            if (!file.exists()) {
                Log.d("Log", "notExists");
                path.mkdirs();
                CopyDB(getBaseContext().getAssets().open("FilmDB"),
                        new FileOutputStream(path));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        movieLv = (ListView) findViewById(R.id.myList);
        movies = new ArrayList<>();

        mdbAdapter = new MovieDBAdapter(this);

        mdbAdapter.open();

        c = mdbAdapter.getAllMovies();

        //Log.d("Log", "Hata" + c.getString(1));

        //Cursorun içindeki ilk veriyi veriyor.
        //aşağıdaki kodla != null u da kontrol etmiş oluyoruz.
        if(c.moveToFirst())
        {
            Log.d("Log", "if" );
            do{
                //tmpMovie olarak da yapabiliriz setterlernan veya direk tektek id falan diye da alabiliriz
                id = c.getInt(0);
                name = c.getString(1);
                overView = c.getString(2);
                imageCode = c.getString(3);
                language = c.getString(4);
                vote = c.getDouble(5);

                //veriyi movie temp e kaydediyor
                tmpMovie = new Movie(id, name, overView, imageCode, language, vote);

                //tmpMovie yi listeye ekledimovieListesine ekleyecek
                movies.add(tmpMovie);
            }while(c.moveToNext());
            // sonraya geçebildiği sürece yap
        }
        else
            Toast.makeText(this,"Veritabanında film yok.",Toast.LENGTH_LONG).show();

        mdbAdapter.close();

        mvAdaptor = new MovieAdapter(this, R.layout.movie_item, movies);
        movieLv.setAdapter(mvAdaptor);



        movieLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                intentDetails = new Intent(MainActivity.this,MovieDetailsActivity.class);
                tmpMovie= movies.get(position);
                intentDetails.putExtra("movie", tmpMovie);
                startActivity(intentDetails);

            }
        });

        movieLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //login = new Intent(MainActivity.this)
                return true;
            }
        });

    }
    public void CopyDB(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        // Copy 1K bytes at a time

        Log.d("Log", "dbCopied");
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }
}
