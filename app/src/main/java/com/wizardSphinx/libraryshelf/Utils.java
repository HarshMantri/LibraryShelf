package com.wizardSphinx.libraryshelf;

import android.util.Log;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> wishlistBooks;
    private static ArrayList<Book> favouriteBooks;

    private Utils() {
        if (allBooks == null) {
            allBooks = new ArrayList<>();
            initData();
        }

        if (alreadyReadBooks == null) {
            alreadyReadBooks = new ArrayList<>();
        }

        if (currentlyReadingBooks == null) {
            currentlyReadingBooks = new ArrayList<>();
        }

        if (wishlistBooks == null) {
            wishlistBooks = new ArrayList<>();
        }

        if (favouriteBooks == null) {
            favouriteBooks = new ArrayList<>();
        }
    }

    private void initData() {
        //TODO remove the hardcoded books and give user option to add books to their library
        allBooks.add(new Book(1, "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 223, "https://upload.wikimedia.org/wikipedia/en/thumb/6/6b/Harry_Potter_and_the_Philosopher%27s_Stone_Book_Cover.jpg/220px-Harry_Potter_and_the_Philosopher%27s_Stone_Book_Cover.jpg", "A masterclass of a wizarding world", "Long Description"));
        allBooks.add(new Book(2, "Harry Potter and the Chamber of Secrets", "J.K. Rowling", 251, "https://upload.wikimedia.org/wikipedia/en/5/5c/Harry_Potter_and_the_Chamber_of_Secrets.jpg", "Wow!", ""));
    }

    public static Utils getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getWishlistBooks() {
        return wishlistBooks;
    }

    public static ArrayList<Book> getFavouriteBooks() {
        return favouriteBooks;
    }

    public static Book getBookById(int id) {
        for (Book b : allBooks) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public static boolean addToAlreadyRead(Book book) {
        return alreadyReadBooks.add(book);
    }

    public static boolean addToCurrentlyReading(Book book) {
        return currentlyReadingBooks.add(book);
    }

    public static boolean addToWishlist(Book book) {
        return wishlistBooks.add(book);
    }

    public static boolean addToFavourites(Book book) {
        return favouriteBooks.add(book);
    }

    public static boolean removeFromAlreadyRead(Book book) {
        Log.d("hehe", "removeFromAlreadyRead: hmm");
        return alreadyReadBooks.remove(book);
    }

    public static boolean removeFromCurrentlyReading(Book book) {
        return currentlyReadingBooks.remove(book);
    }

    public static boolean removeFromWishlist(Book book) {
        return wishlistBooks.remove(book);
    }

    public static boolean removeFromFavourites(Book book) {
        return favouriteBooks.remove(book);
    }
}
