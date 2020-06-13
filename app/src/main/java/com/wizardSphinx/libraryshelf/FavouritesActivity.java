package com.wizardSphinx.libraryshelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class FavouritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        RecyclerView favRecView = findViewById(R.id.fav_rec_view);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this, "favourites");

        favRecView.setAdapter(adapter);
        favRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance().getFavouriteBooks());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}