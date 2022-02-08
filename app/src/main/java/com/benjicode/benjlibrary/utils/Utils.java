package com.benjicode.benjlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.benjicode.benjlibrary.books_activities.BookDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String All_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVORITE_BOOKS = "favorite_books";

    private static Utils instance;
    private  SharedPreferences sharedPreferences;
//    private static ArrayList<BookDetails> allbooks;
//    private static ArrayList<BookDetails> alreadyReadBookDetails;
//    private static ArrayList<BookDetails> wantToReadBookDetails;
//    private static ArrayList<BookDetails> currentlyReadingBookDetails;
//    private static ArrayList<BookDetails> favoriteBookDetails;

    private Utils(Context context){
        sharedPreferences = context.getSharedPreferences("alternate_db",Context.MODE_PRIVATE);

        if(null == getAllbooks()){
            initData();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if(null == getAlreadyReadBookDetails()){
           editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<BookDetails>()));
           editor.commit();
        }

      if(null == getWantToReadBookDetails()){
          editor.putString(WANT_TO_READ_BOOKS , gson.toJson(new ArrayList<BookDetails>()));
          editor.commit();
            }

      if(null == getCurrentlyReadingBookDetails()){
          editor.putString(CURRENTLY_READING_BOOKS , gson.toJson(new ArrayList<BookDetails>()));
          editor.commit();
            }

      if(null == getFavoriteBookDetails()){
          editor.putString(FAVORITE_BOOKS , gson.toJson(new ArrayList<BookDetails>()));
          editor.commit();
            }

        }

    private void initData() {
        //TODO: add initial data
        ArrayList<BookDetails> bookDetails = new ArrayList<>();

        bookDetails.add(new BookDetails(1,"Huckleberry Finn","Mark Twain",698,"https://www-tc.pbs.org/wgbh/americanexperience/media/__sized__/canonical_images/feature/HuckFinn_NEWEST_Canoical-resize-1200x0-50.jpg",
                "Often titled The Great American Novel,",
                "Long Description"));

        bookDetails.add(new BookDetails(2,"Frankenstein","Mary Shelley",698,"https://m.media-amazon.com/images/I/5127OtHzHuL._SL210_.jpg",
                "A combination of gothic thriller, cautionary tale and romance novel, Frankenstein is a story like no other",
                "Long Description"));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(All_BOOKS_KEY,gson.toJson(bookDetails));
        editor.commit();

       }

    public  static Utils getInstance(Context context){
        if (null == instance) {
            instance = new Utils(context);
        }
        return  instance;

    }

    public  ArrayList<BookDetails> getAllbooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<BookDetails>>(){}.getType();
        ArrayList<BookDetails> bookDetails = gson.fromJson(sharedPreferences.getString(All_BOOKS_KEY,null),type);
        return bookDetails;
    }

    public  ArrayList<BookDetails> getAlreadyReadBookDetails() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<BookDetails>>(){}.getType();
        ArrayList<BookDetails> bookDetails = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS,null),type);
        return bookDetails;
    }

    public  ArrayList<BookDetails> getWantToReadBookDetails() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<BookDetails>>(){}.getType();
        ArrayList<BookDetails> bookDetails = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS,null),type);
        return bookDetails;
    }

    public  ArrayList<BookDetails> getCurrentlyReadingBookDetails() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<BookDetails>>(){}.getType();
        ArrayList<BookDetails> bookDetails = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS,null),type);
        return bookDetails;
    }

    public  ArrayList<BookDetails> getFavoriteBookDetails() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<BookDetails>>(){}.getType();
        ArrayList<BookDetails> bookDetails = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS,null),type);
        return bookDetails;
    }

    public BookDetails getBookById(int id){
        ArrayList<BookDetails> bookDetails = getAllbooks();
        if(null!=bookDetails)
        for(BookDetails b: bookDetails){
            if(b.getId()== id){
                return b;
            }
        }
        return  null;
    }

    public boolean addToAlreadyRead(BookDetails bookDetails){
        ArrayList<BookDetails> bookDetails1 =getAlreadyReadBookDetails();
        if(null != bookDetails1){
            if(bookDetails1.add(bookDetails)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS,gson.toJson(bookDetails1));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWantReadBooks(BookDetails bookDetails) {
        ArrayList<BookDetails> bookDetails1 = getWantToReadBookDetails();
        if (null != bookDetails1) {
            if (bookDetails1.add(bookDetails)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(bookDetails1));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addToCurrentlyReadingBooks(BookDetails bookDetails){
        ArrayList<BookDetails> bookDetails1 = getCurrentlyReadingBookDetails();
        if(null != bookDetails1) {
            if (bookDetails1.add(bookDetails)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(bookDetails1));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addToFavoriteBooks(BookDetails bookDetails) {
        ArrayList<BookDetails> bookDetails1 = getFavoriteBookDetails();
        if (null != bookDetails1) {
            if (bookDetails1.add(bookDetails)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS);
                editor.putString(FAVORITE_BOOKS, gson.toJson(bookDetails1));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadReadBooks(BookDetails bookDetails){
        ArrayList<BookDetails> bookDetails1 = getAlreadyReadBookDetails();
        if(null != bookDetails1){
            for(BookDetails b: bookDetails1){
                if(b.getId() == bookDetails.getId()){
                    if(bookDetails1.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS, gson.toJson(bookDetails1));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromWantToReadBooks(BookDetails bookDetails){
        ArrayList<BookDetails> bookDetails1 = getWantToReadBookDetails();
        if(null != bookDetails1){
            for(BookDetails b: bookDetails1){
                if(b.getId() == bookDetails.getId()){
                    if(bookDetails1.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(bookDetails1));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromCurrentlyReadBooks(BookDetails bookDetails){
        ArrayList<BookDetails> bookDetails1 = getCurrentlyReadingBookDetails();
        if(null != bookDetails1){
            for(BookDetails b: bookDetails1){
                if(b.getId() == bookDetails.getId()){
                    if(bookDetails1.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(bookDetails1));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromFavoriteBooks(BookDetails bookDetails){
        ArrayList<BookDetails> bookDetails1 = getFavoriteBookDetails();
        if(null != bookDetails1){
            for(BookDetails b: bookDetails1){
                if(b.getId() == bookDetails.getId()){
                    if(bookDetails1.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS);
                        editor.putString(FAVORITE_BOOKS, gson.toJson(bookDetails1));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
