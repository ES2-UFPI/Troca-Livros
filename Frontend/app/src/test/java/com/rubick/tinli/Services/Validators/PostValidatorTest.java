package com.rubick.tinli.Services.Validators;

import com.rubick.tinli.R;
import com.rubick.tinli.model.Book;

import org.junit.Assert;
import org.junit.Test;

public class PostValidatorTest {

    @Test
    public void CorrectBook() {
        Book book = new Book("O estrangeiro", "Albert Camus", R.drawable.the_stranger_bookcover);
        Assert.assertTrue(PostValidator.ValidateBook(book));
    }

    @Test
    public void NoTitle() {
        Book book = new Book("", "Albert Camus", R.drawable.the_stranger_bookcover);
        Assert.assertFalse(PostValidator.ValidateBook(book));
    }

    @Test
    public void NoAuthor() {
        Book book = new Book("O estrangeiro", "", R.drawable.the_stranger_bookcover);
        Assert.assertFalse(PostValidator.ValidateBook(book));
    }
}