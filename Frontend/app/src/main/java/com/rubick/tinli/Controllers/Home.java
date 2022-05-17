package com.rubick.tinli.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rubick.tinli.R;
import com.rubick.tinli.model.Book;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        books.add(new Book("O estrangeiro", "Albert Camus", R.drawable.the_stranger_bookcover));
        books.add(new Book("O idiota", "Fiodor Dostoievski", R.drawable.the_idiot_bookcover));
        books.add(new Book("1984", "George Orwell", R.drawable.ninefour_bookcover));
        setItems();
    }

    private void setItems() {
        recyclerView = findViewById(R.id.recycle);
        SnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        snapHelper.attachToRecyclerView(recyclerView);
        BookAdapter bookAdapter = new BookAdapter(books);
        recyclerView.setAdapter(bookAdapter);
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
                Toast.makeText(getApplicationContext(), "Livro: " + book.getTitle(), Toast.LENGTH_SHORT).show();
            });
        }
    }

}