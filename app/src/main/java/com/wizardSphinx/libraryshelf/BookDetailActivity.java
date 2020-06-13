package com.wizardSphinx.libraryshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookDetailActivity extends AppCompatActivity {

    private TextView bookName, bookAuthor, bookPages, bookLongDesc;
    private Button curReadBtn, alreadyReadBtn, wishlistBtn, favouriteBtn;
    private ImageView bookCover;

    public static final String BOOK_ID = "bookId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        initViews();

        Intent intent = getIntent();
        if(intent != null) {
            int bookId = intent.getIntExtra(BOOK_ID, -1);
            if(bookId != -1) {
                Book incomingBook = Utils.getBookById(bookId);
                if(incomingBook != null) {
                    setData(incomingBook);

                    handleAlreadyReadBook(incomingBook);
                    
                    handleCurrentlyReading(incomingBook);

                    handleAddToWishlist(incomingBook);

                    handleAddToFavourites(incomingBook);
                }
            }
        }

    }

    private void handleAddToFavourites(final Book incomingBook) {
        ArrayList<Book> favourites = Utils.getFavouriteBooks();

        Boolean isFavourite = false;

        for(Book b: favourites) {
            if(b.getId() == incomingBook.getId()){
                isFavourite = true;
            }
        }

        if(isFavourite) {
            favouriteBtn.setEnabled(false);
        } else {
            favouriteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.addToFavourites(incomingBook)) {
                        Toast.makeText(BookDetailActivity.this, "added to favourites!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookDetailActivity.this, FavouritesActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookDetailActivity.this, "Something went wrong. Try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleAddToWishlist(final Book incomingBook) {
        ArrayList<Book> wishlistBooks = Utils.getWishlistBooks();

        Boolean isWishlistItem = false;

        for(Book b: wishlistBooks) {
            if(b.getId() == incomingBook.getId()){
                isWishlistItem = true;
            }
        }

        if(isWishlistItem) {
            wishlistBtn.setEnabled(false);
        } else {
            wishlistBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.addToWishlist(incomingBook)) {
                        Toast.makeText(BookDetailActivity.this, "added to wishlist!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookDetailActivity.this, WishlistActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookDetailActivity.this, "Something went wrong. Try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReading(final Book incomingBook) {
        ArrayList<Book> currentlyReadingBooks = Utils.getCurrentlyReadingBooks();

        Boolean isCurrentlyReading = false;

        for(Book b: currentlyReadingBooks) {
            if(b.getId() == incomingBook.getId()){
                isCurrentlyReading = true;
            }
        }

        if(isCurrentlyReading) {
            curReadBtn.setEnabled(false);
        } else {
            curReadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.addToCurrentlyReading(incomingBook)) {
                        Toast.makeText(BookDetailActivity.this, "added to currently reading!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookDetailActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookDetailActivity.this, "Something went wrong. Try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleAlreadyReadBook(final Book incomingBook) {
        ArrayList<Book> alreadyReadBooks = Utils.getAlreadyReadBooks();

        Boolean isAlreadyRead = false;

        for(Book b: alreadyReadBooks) {
            if(b.getId() == incomingBook.getId()){
                isAlreadyRead = true;
            }
        }

        if(isAlreadyRead) {
            alreadyReadBtn.setEnabled(false);
        } else {
            alreadyReadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.addToAlreadyRead(incomingBook)) {
                        Toast.makeText(BookDetailActivity.this, "added to already read!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookDetailActivity.this, AlreadyReadBooksActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookDetailActivity.this, "Something went wrong. Try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book) {
        bookName.setText(book.getName());
        bookAuthor.setText(book.getAuthor());
        bookPages.setText(String.valueOf(book.getPages()));
        bookLongDesc.setText(book.getLongDescription());

        Glide.with(this)
                .asBitmap()
                .load(book.getImgUrl())
                .into(bookCover);
    }

    private void initViews() {
        bookName = findViewById(R.id.book_txt_name);
        bookAuthor = findViewById(R.id.book_txt_author);
        bookPages = findViewById(R.id.book_no_pages);
        bookLongDesc = findViewById(R.id.book_desc);
        curReadBtn = findViewById(R.id.book_cur_reading_btn);
        alreadyReadBtn = findViewById(R.id.book_already_read_btn);
        wishlistBtn = findViewById(R.id.book_wishlist_btn);
        favouriteBtn = findViewById(R.id.book_favourite_btn);
        bookCover = findViewById(R.id.book_img);
    }
}