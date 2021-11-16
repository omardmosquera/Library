package com.mosquera.biblioteca_mosquera;

public class Book {



    String title, description, author, id;

    public Book(){}

    public Book(String title, String description,String author,String id){
        this.title = title;
        this.description = description;
        this.author = author;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }
}
