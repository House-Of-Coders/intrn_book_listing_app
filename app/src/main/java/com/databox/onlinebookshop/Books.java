package com.databox.onlinebookshop;

public class Books {
    String bookImage;
    String bookName;
    String bookCategory;
    String bookISBN;
    String bookPrice;

    public Books(String bookImage , String bookName, String bookCategory, String bookISBN, String bookPrice) {
        this.bookImage = bookImage;
        this.bookName = bookName;
        this.bookCategory = bookCategory;
        this.bookISBN = bookISBN;
        this.bookPrice = bookPrice;
    }

    public String getBookImage(){ return  bookImage; }

    public String getBookName() {
        return bookName;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public String getBookPrice() {
        return bookPrice;
    }
}


