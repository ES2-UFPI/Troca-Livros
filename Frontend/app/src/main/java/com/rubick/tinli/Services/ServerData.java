package com.rubick.tinli.Services;

import com.rubick.tinli.R;
import com.rubick.tinli.model.Book;

import java.util.ArrayList;
import java.util.List;

//Aqui receberiamos os dados do servidor
public class ServerData {

    public static List<Book> GetPostBooks(){
        List<Book> books = new ArrayList<>();
        books.add(new Book("O estrangeiro", "Albert Camus", R.drawable.the_stranger_bookcover));
        books.add(new Book("O idiota", "Fiodor Dostoievski", R.drawable.the_idiot_bookcover));
        books.add(new Book("1984", "George Orwell", R.drawable.ninefour_bookcover));
        return books;
    }
}
