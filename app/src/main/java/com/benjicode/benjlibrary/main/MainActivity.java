package com.benjicode.benjlibrary.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.benjicode.benjlibrary.R;
import com.benjicode.benjlibrary.utils.Utils;
import com.benjicode.benjlibrary.books_activities.allBooksActivity;
import com.benjicode.benjlibrary.books_activities.alreadyReadActivity;
import com.benjicode.benjlibrary.books_activities.currentlyReadingBooksActivity;
import com.benjicode.benjlibrary.books_activities.favoriteBooksActivity;
import com.benjicode.benjlibrary.books_activities.wantToReadBooksActivity;
import com.benjicode.benjlibrary.webview.webViewActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnAllBooks, btnAlreadyBooks, btnWantToRead, btnCurrentlyReading,btnFavoriteBooks,btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_from_pinterest_laucher);

        initViews();

        btnAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, allBooksActivity.class);
                startActivity(intent);
            }
        });

        btnAlreadyBooks.setOnClickListener((view)->{
            Intent intent = new Intent(MainActivity.this, alreadyReadActivity.class);
            startActivity(intent);
        });

        btnCurrentlyReading.setOnClickListener((view)->{
            Intent intent = new Intent(MainActivity.this, currentlyReadingBooksActivity.class);
            startActivity(intent);
        });

        btnWantToRead.setOnClickListener((view)->{
                Intent intent = new Intent(MainActivity.this, wantToReadBooksActivity.class);
                startActivity(intent);
        });

        btnFavoriteBooks.setOnClickListener((view)-> {
            Intent intent = new Intent(MainActivity.this, favoriteBooksActivity.class);
            startActivity(intent);});

        btnAbout.setOnClickListener((view)->{
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getString(R.string.app_name));
            builder.setMessage("Designed and Developed by benjicode at BenjiEnterprises \n" +
                    "check my github for more awesome android and desktop applications:");
            builder.setNegativeButton("Dismiss", ((dialogInterface, i) -> {}));
            builder.setPositiveButton("Visit", (dialogInterface, i) -> {
                Intent intent = new Intent(MainActivity.this, webViewActivity.class);
                startActivity(intent);
            });
            builder.setCancelable(false);
            builder.create().show();
        });

        Utils.getInstance(this);
    }


    private void initViews() {
        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnAlreadyBooks = findViewById(R.id.btnAlreadyRead);
        btnWantToRead = findViewById(R.id.btnWantToRead);
        btnCurrentlyReading = findViewById(R.id.btnCurrentLyReading);
        btnFavoriteBooks = findViewById(R.id.btnFavoriteBooks);
        btnAbout = findViewById(R.id.btnAbout);
    }


}