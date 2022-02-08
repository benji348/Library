package com.benjicode.benjlibrary.books_activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.benjicode.benjlibrary.R;
import com.benjicode.benjlibrary.main.MainActivity;
import com.benjicode.benjlibrary.utils.Utils;
import com.benjicode.benjlibrary.adapter.BookRecViewAdapter;

public class allBooksActivity extends AppCompatActivity {
    private RecyclerView booksRecView;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        adapter = new BookRecViewAdapter(this,"allbooks");
        booksRecView = findViewById(R.id.recyleView);

        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBookDetails(Utils.getInstance(this).getAllbooks());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}