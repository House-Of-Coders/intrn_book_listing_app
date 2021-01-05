package com.databox.onlinebookshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BooksAdapter extends ArrayAdapter<String> {
    Context context;
    int[] booksThumbNail;
    String[] booksName;
    String[] booksPrice;


    public BooksAdapter( Context context, String[] booksName,String[] booksPrice, int[] booksThumbNail) {
        super(context, R.layout.book_layout,R.id.bookName, booksName);
        this.context = context;
        this.booksThumbNail = booksThumbNail;
        this.booksName = booksName;
        this.booksPrice = booksPrice;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        View singleBookListItem = convertView;
        BooksViewHolder holder = null;
        if (singleBookListItem == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleBookListItem = layoutInflater.inflate(R.layout.book_layout,parent,false);
            holder = new BooksViewHolder(singleBookListItem);
            singleBookListItem.setTag(holder);
        } else {
            holder = (BooksViewHolder) singleBookListItem.getTag();
        }
       // holder.bookCoverImage.setImageResource(images[position]);
       // holder.bookHeaderName.setText();

        return super.getView(position, convertView, parent);
    }
}
