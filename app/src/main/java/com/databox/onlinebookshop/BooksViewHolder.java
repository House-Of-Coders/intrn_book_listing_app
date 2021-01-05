package com.databox.onlinebookshop;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BooksViewHolder {

    ImageView bookCoverImage;
    TextView bookHeaderName;
    TextView bookPrice;

    BooksViewHolder( View v){

        bookCoverImage = v.findViewById(R.id.bookimageView);
        bookHeaderName = v.findViewById(R.id.bookName);
        bookPrice = v.findViewById(R.id.bookPrice);
    }
}
