package com.databox.onlinebookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class saveBookItems extends AppCompatActivity {

    private EditText bookName , bookCategory , bookISBN , bookPrice , bookQuanity;
    private Button saveBookList;
    FirebaseDatabase database;
    DatabaseReference bookDBReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_book_items);

        bookName = (EditText) findViewById(R.id.txtEnterName);
        bookCategory = (EditText) findViewById(R.id.txtBookCategory);
        bookISBN = (EditText) findViewById(R.id.txtISBN);
        bookPrice = (EditText) findViewById(R.id.txtPrice);
        bookQuanity = (EditText) findViewById(R.id.txtQuantity);
        saveBookList = (Button) findViewById(R.id.buttonSaveBookList);


        bookDBReference = FirebaseDatabase.getInstance().getReference().child("Books");

        saveBookList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBookListData();
            }
        });

    }

    private void saveBookListData() {

        String bokName = bookName.getText().toString();
        String bokCategory =  bookCategory.getText().toString();
        String bokISBN = bookISBN.getText().toString();
        String bokPrice = bookPrice.getText().toString();

       Books books = new Books(bokName,bokCategory,bokISBN,bokPrice);

       bookDBReference.push().setValue(books);
        Toast.makeText(saveBookItems.this, "Save Books!",Toast.LENGTH_SHORT).show();
    }

}