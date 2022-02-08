package com.benjicode.benjlibrary.books_activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.benjicode.benjlibrary.R;
import com.benjicode.benjlibrary.utils.Utils;
import com.benjicode.benjlibrary.adapter.BookRecViewAdapter;
import com.benjicode.benjlibrary.main.MainActivity;

public class favoriteBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_books);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_from_pinterest_laucher);

        RecyclerView recyclerView = findViewById(R.id.bookRecview);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this,"favoriteBooks");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBookDetails(Utils.getInstance(this).getFavoriteBookDetails());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(favoriteBooksActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}