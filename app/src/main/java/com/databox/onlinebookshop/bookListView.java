package com.databox.onlinebookshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class bookListView extends AppCompatActivity {

    ListView bookListView;
    FirebaseDatabase database;
    DatabaseReference bookDB;
    ArrayList<BookListAdapter> list;
    ArrayAdapter <BookListAdapter> adapter;
    BookListAdapter bookListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_view);

       bookListAdapter = new BookListAdapter();
        bookListView = findViewById(R.id.book_list);
        database = FirebaseDatabase.getInstance();
        bookDB = database.getReference("Books");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<BookListAdapter>(this , R.layout.book_layout, R.id.bokThumbView,list);
        bookDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot ds:snapshot.getChildren())
               {
                bookListAdapter = ds.getValue(BookListAdapter.class);
                list.add(bookListAdapter);
               }
               bookListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        BooksAdapter booksAdapter = new BooksAdapter(this, bokThumb ,bokName ,bokPrice);
//        bookListView.setAdapter(booksAdapter);
    }
}