package com.rubick.tinli.Services.Validators;

import com.rubick.tinli.model.Book;

public class PostValidator {
    public static boolean ValidateBook(Book book){
        return !(book.getTitle().isEmpty() || book.getAuthor().isEmpty());

    }
}
