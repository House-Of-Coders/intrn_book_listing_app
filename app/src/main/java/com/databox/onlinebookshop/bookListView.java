package com.databox.onlinebookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class bookListView extends AppCompatActivity {

    ListView bookListView;

    //dummy data
    String[] bokName = {"beta" , "test", "omega" , "pulsar"};
    String[] bokPrice = {"rs100" ,"rs200" ,"rs300" ,"rs400"};
    int[] bokThumb = {R.drawable.common_full_open_on_phone ,R.drawable.common_google_signin_btn_icon_dark,
            R.drawable.common_google_signin_btn_icon_dark,R.drawable.common_full_open_on_phone};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_view);

        bookListView = findViewById(R.id.book_list);
        BooksAdapter booksAdapter = new BooksAdapter(this, bokThumb ,bokName ,bokPrice);
        bookListView.setAdapter(booksAdapter);
    }
}