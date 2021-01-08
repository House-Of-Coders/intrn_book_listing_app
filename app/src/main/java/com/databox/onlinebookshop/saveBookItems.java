package com.databox.onlinebookshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.WebChromeClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;


public class saveBookItems extends AppCompatActivity {

    private EditText bookName , bookCategory , bookISBN , bookPrice , bookQuanity;
    private ImageView bokThumbView;
    private Button saveBookList , bookChooseImage;
    FirebaseDatabase database;
    DatabaseReference bookDBReference;
    StorageReference bookStorageReference;
    int Image_Request_Code = 7;
    Uri FilePathUri;

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
        bookChooseImage = (Button) findViewById(R.id.buttonChooseFile);
        bokThumbView = (ImageView)findViewById(R.id.bookThumbView);

        bookStorageReference = FirebaseStorage.getInstance().getReference().child("Books");
        bookDBReference = FirebaseDatabase.getInstance().getReference().child("Books");

        saveBookList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
                saveBookListData();
            }
        });

        bookChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                bokThumbView.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;
    }


    private void saveBookListData() {
        int bokThumb = bokThumbView.getId();
        String bokName = bookName.getText().toString();
        String bokCategory =  bookCategory.getText().toString();
        String bokISBN = bookISBN.getText().toString();
        String bokPrice = bookPrice.getText().toString();

       Books books = new Books(bokName,bokCategory,bokISBN,bokPrice);

       bookDBReference.push().setValue(books);
       // bookDBReference.child("images/" +books);
       Toast.makeText(saveBookItems.this, "Save Books!",Toast.LENGTH_SHORT).show();
    }

    private void uploadImage(){
        if (FilePathUri != null){
            StorageReference fileReference = bookStorageReference.child(System.currentTimeMillis()+ "."+ GetFileExtension(FilePathUri)) ;

            fileReference.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           Toast.makeText(saveBookItems.this , "Image upload successfully!", Toast.LENGTH_SHORT).show();
                        }
                    }). addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(saveBookItems.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this,"No File Selected" , Toast.LENGTH_SHORT).show();
        }
    }

}