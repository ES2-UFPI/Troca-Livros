package com.rubick.tinli.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rubick.tinli.R;
import com.rubick.tinli.Services.ServerData;
import com.rubick.tinli.Services.Validators.PostValidator;
import com.rubick.tinli.model.Book;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setItems();
    }

    private void ShowPopup(String bookTitle){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View popup = getLayoutInflater().inflate(R.layout.popup, null);

        Spinner booksSpinner = popup.findViewById(R.id.popup_book_list);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.user_books, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        booksSpinner.setAdapter(adapter);

        TextView bookNameTV= popup.findViewById(R.id.post_book);
        bookNameTV.setText(bookTitle);
        Button popupBt = popup.findViewById(R.id.pop_bt);
        builder.setView(popup);
        dialog = builder.create();
        dialog.show();


        popupBt.setOnClickListener(click ->{
            dialog.dismiss();
            Toast.makeText(getApplicationContext(), "Proposta realizada!", Toast.LENGTH_SHORT).show();
        });
    }

    private void setItems() {
        recyclerView = findViewById(R.id.recycle);
        SnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        snapHelper.attachToRecyclerView(recyclerView);
        BookAdapter bookAdapter = new BookAdapter(ValidBooks());
        recyclerView.setAdapter(bookAdapter);
    }

    private List<Book> ValidBooks(){
        List<Book> bookList = ServerData.GetPostBooks();
        List<Book> validBooksList = new ArrayList<>();
        for (Book book: bookList) {
            if(PostValidator.ValidateBook(book)) validBooksList.add(book);
        }
        return validBooksList;
    }

    private class BookAdapter extends RecyclerView.Adapter<BookViewHolder>{
        private List<Book> bookList;

        public BookAdapter(List<Book> bookList){
            this.bookList = bookList;
        }

        @NonNull
        @Override
        public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BookViewHolder(getLayoutInflater().inflate(R.layout.post, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
            Book currentBook = bookList.get(position);
            holder.bind(currentBook);
        }

        @Override
        public int getItemCount() {
            return bookList.size();
        }
    }


    private class BookViewHolder extends RecyclerView.ViewHolder{

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Book book){
            Button postBt = itemView.findViewById(R.id.postBt);
            TextView bookTitle = itemView.findViewById(R.id.book_title);
            TextView bookAuthor = itemView.findViewById(R.id.author_text);
            ImageView bookCover = itemView.findViewById(R.id.book_cover);

            bookTitle.setText(book.getTitle());
            bookAuthor.setText(book.getAuthor());
            bookCover.setImageResource(book.getCover());

            postBt.setOnClickListener(click ->{
                ShowPopup(book.getTitle());
            });
        }
    }

}