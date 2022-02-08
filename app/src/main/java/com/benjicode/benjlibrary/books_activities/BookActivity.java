package com.benjicode.benjlibrary.books_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.benjicode.benjlibrary.R;
import com.benjicode.benjlibrary.utils.Utils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    public static  final String BOOK_ID_KEY="bookId";
    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAlreadyRead, btnWantToRead, btnAddToCurrentlyReading,btnAddToFavorite;
    private ImageView bookImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);


        intitview();
//        String longDescription = "Adventures of Huckleberry Finn (or, in more recent editions, The Adventures of Huckleberry Finn) is a novel by Mark Twain, first published in the United Kingdom in December 1884 and in the United States in February 1885. Commonly named among the Great American Novels, the work is among the first in major American literature to be written throughout in vernacular English, characterized by local color regionalism. It is told in the first person by Huckleberry \"Huck\" Finn, the narrator of two other Twain novels (Tom Sawyer Abroad and Tom Sawyer, Detective) and a friend of Tom Sawyer. It is a direct sequel to The Adventures of Tom Sawyer.\n" +
//                "\n" +
//                "The book is noted for its colorful description of people and places along the Mississippi River. Set in a Southern antebellum society that had ceased to exist over 20 years before the work was published, Adventures of Huckleberry Finn is an often scathing satire on entrenched attitudes, particularly racism.\n" +
//                "\n" +
//                "Perennially popular with readers, Adventures of Huckleberry Finn has also been the continued object of study by literary critics since its publication. The book was widely criticized upon release because of its extensive use of coarse language. Throughout the 20th century, and despite arguments that the protagonist and the tenor of the book are anti-racist,[2][3] criticism of the book continued due to both its perceived use of racial stereotypes and its frequent use of the racial slur \"nigger\".";
//
//
//        Book book = new Book(1,"Huckleberry Finn","Mark Twain",698,"https://www-tc.pbs.org/wgbh/americanexperience/media/__sized__/canonical_images/feature/HuckFinn_NEWEST_Canoical-resize-1200x0-50.jpg",
//                "Often titled The Great American Novel,",
//                longDescription);

        Intent intent = getIntent();
            if(null != intent){
                int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);
                if(bookId != -1){
                BookDetails incomingBookDetails = Utils.getInstance(this).getBookById(bookId);
                if(null != incomingBookDetails){
                    setData(incomingBookDetails);
                    
                    handleAlreadyRead(incomingBookDetails);
                    handleWantToReadBooks(incomingBookDetails);
                    handleCurrentlyReadingBooks(incomingBookDetails);
                    handleFavoriteBooks(incomingBookDetails);
                    }
                }
            }
    }

    private void handleFavoriteBooks(final BookDetails bookDetails) {
        ArrayList<BookDetails> favoriteBookDetails =Utils.getInstance(this).getFavoriteBookDetails();
        boolean existInAlreadyReadBooks = false;
        for(BookDetails b: favoriteBookDetails){
            if(b.getId() == bookDetails.getId()){
                existInAlreadyReadBooks= true;
            }
        }
        if(existInAlreadyReadBooks){
            btnAddToFavorite.setEnabled(false);
        }else{
            btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToFavoriteBooks(bookDetails)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, favoriteBooksActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReadingBooks(final BookDetails bookDetails) {
        ArrayList<BookDetails> currentlyReandingBookDetails =Utils.getInstance(this).getCurrentlyReadingBookDetails();
        boolean existInAlreadyReadBooks = false;
        for(BookDetails b: currentlyReandingBookDetails){
            if(b.getId() == bookDetails.getId()){
                existInAlreadyReadBooks= true;
            }
        }
        if(existInAlreadyReadBooks){
            btnAddToCurrentlyReading.setEnabled(false);
        }else{
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToCurrentlyReadingBooks(bookDetails)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, currentlyReadingBooksActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void handleWantToReadBooks(final BookDetails bookDetails) {
        ArrayList<BookDetails> wantToReadBookDetails =Utils.getInstance(this).getWantToReadBookDetails();
        boolean existInAlreadyReadBooks = false;
        for(BookDetails b: wantToReadBookDetails){
            if(b.getId() == bookDetails.getId()){
                existInAlreadyReadBooks= true;
            }
        }
        if(existInAlreadyReadBooks){
            btnWantToRead.setEnabled(false);
        }else{
            btnWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToWantReadBooks(bookDetails)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, wantToReadBooksActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Enable and disable button
     * And the book to Already Read Book Arraylist
     * @param bookDetails
     */
    private void handleAlreadyRead(final BookDetails bookDetails) {
        ArrayList<BookDetails> alreadyReadBookDetails =Utils.getInstance(this).getAlreadyReadBookDetails();
        boolean existInAlreadyReadBooks = false;
        for(BookDetails b: alreadyReadBookDetails){
            if(b.getId() == bookDetails.getId()){
                existInAlreadyReadBooks= true;
            }
        }
        if(existInAlreadyReadBooks){
            btnAlreadyRead.setEnabled(false);
        }else{
            btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToAlreadyRead(bookDetails)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, alreadyReadActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(BookDetails bookDetails) {
        txtBookName.setText(bookDetails.getName());
        txtAuthor.setText(bookDetails.getAuthor());
        txtPages.setText(String.valueOf(bookDetails.getPages()));
        txtDescription.setText(bookDetails.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(bookDetails.getImageUrl())
                .into(bookImage);
    }

    private void intitview() {
        txtAuthor = findViewById(R.id.nameOfAuthor);
        txtBookName = findViewById(R.id.nameOfBook);
        txtPages = findViewById(R.id.amountOfPages);
        txtDescription = findViewById(R.id.longDesc);

        btnWantToRead = findViewById(R.id.wantToRead);
        btnAddToCurrentlyReading = findViewById(R.id.addCurrentReading);
        btnAddToFavorite = findViewById(R.id.addToFavorite);
        btnAlreadyRead = findViewById(R.id.alreadyRead);

        bookImage = findViewById(R.id.imgBook2);
    }
}