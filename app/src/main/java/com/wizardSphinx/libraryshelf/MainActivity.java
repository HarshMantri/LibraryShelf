package com.wizardSphinx.libraryshelf;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button allBooksBtn, curReadingBtn, alreadyReadBtn, wishlistBtn, favouritesBtn, abtBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        Utils.getInstance();
    }

    private void initViews() {
        allBooksBtn = findViewById(R.id.main_all_books_btn);
        curReadingBtn = findViewById(R.id.main_cur_reading_btn);
        alreadyReadBtn = findViewById(R.id.main_already_read_btn);
        wishlistBtn = findViewById(R.id.main_wishlist_btn);
        favouritesBtn = findViewById(R.id.main_favourites_btn);
        abtBtn = findViewById(R.id.main_abt_btn);

        allBooksBtn.setOnClickListener(this);
        curReadingBtn.setOnClickListener(this);
        alreadyReadBtn.setOnClickListener(this);
        wishlistBtn.setOnClickListener(this);
        favouritesBtn.setOnClickListener(this);
        abtBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.main_all_books_btn:
                //Toast.makeText(this, "All Books Selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(intent);
                break;
            case R.id.main_cur_reading_btn:
                //Toast.makeText(this, "Currently Reading Books Selected", Toast.LENGTH_SHORT).show();
                Intent intent_cr = new Intent(MainActivity.this, CurrentlyReadingActivity.class);
                startActivity(intent_cr);
                break;
            case R.id.main_already_read_btn:
                //Toast.makeText(this, "Already Read Books Selected", Toast.LENGTH_SHORT).show();
                Intent intent_ar = new Intent(MainActivity.this, AlreadyReadBooksActivity.class);
                startActivity(intent_ar);
                break;
            case R.id.main_wishlist_btn:
                //Toast.makeText(this, "Wishlist Books Selected", Toast.LENGTH_SHORT).show();
                Intent intent_w = new Intent(MainActivity.this, WishlistActivity.class);
                startActivity(intent_w);
                break;
            case R.id.main_favourites_btn:
                //Toast.makeText(this, "Favourite Books Selected", Toast.LENGTH_SHORT).show();
                Intent intent_f = new Intent(MainActivity.this, FavouritesActivity.class);
                startActivity(intent_f);
                break;
            case R.id.main_abt_btn:
                //Toast.makeText(this, "About Button Selected", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("This is a basic app for all your book shelf organisational needs."+
                        " You can manage your books by adding to various lists as needed. \nThis app was made with love by Harsh Mantri");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
                break;
            default:
                break;
        }
    }
}