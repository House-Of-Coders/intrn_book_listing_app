package com.databox.onlinebookshop;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BooksViewHolder {

    ImageView bookCoverImage;
    TextView bookHeaderName;
    TextView bookPrice;

    BooksViewHolder( View v){

        bookCoverImage = v.findViewById(R.id.bokThumbView);
        bookHeaderName = v.findViewById(R.id.bookName);
        bookPrice = v.findViewById(R.id.bookPrice);
    }

}




//    void setBlogPost(BlogPost blogPost) {
//        String imageThumb = blogPost.getImageThumb();
//        imageThumbtextView.setText(imageThumb);
//        String userId = blogPost.getUserId();
//        userIdTextView.setText(userId);
//        String imageUrl = blogPost.getImageUrl();
//        imageUrlTextView.setText(imageUrl);
//        String desc = blogPost.getDesc();
//        descTextView.setText(desc);
//    }