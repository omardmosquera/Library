package com.mosquera.biblioteca_mosquera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterSee extends RecyclerView.Adapter<MyAdapterSee.MyViewHolder> {


    Context context;
    ArrayList<Book> ebooks;

    public  MyAdapterSee(Context context, ArrayList<Book> ebooks){
        this.context=context;
        this.ebooks=ebooks;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_book_see,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book = ebooks.get(position);
        holder.title.setText(book.getTitle());
        holder.description.setText(book.getDescription());
        holder.author.setText(book.getAuthor());
        holder.id.setText(book.getId());
    }

    @Override
    public int getItemCount() {
        return ebooks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, description, author, id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_book_see);
            description = itemView.findViewById(R.id.description_book_see);
            author = itemView.findViewById(R.id.author_book_see);
            id= itemView.findViewById(R.id.id_book_see);
        }
    }
}
