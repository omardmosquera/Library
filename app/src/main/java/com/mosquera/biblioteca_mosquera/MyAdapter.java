package com.mosquera.biblioteca_mosquera;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Book> ebooks;

    public MyAdapter(Context context, ArrayList<Book>ebooks) {
        this.context = context;
        this.ebooks = ebooks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_book_reservate,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book = ebooks.get(position);
        holder.title.setText(book.getTitle());
        holder.description.setText(book.getDescription());
        holder.author.setText(book.getAuthor());
        holder.id.setText(book.getId());
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("reserved").push().setValue(book).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(view.getContext(), "Se ha reservado correctamente" + book.getTitle(), Toast.LENGTH_SHORT).show();

                        } else{
                            Toast.makeText(view.getContext(), "Error en el registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return ebooks.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, description, author, id;
        Button add;


        public MyViewHolder(@NonNull View itemView){
        super(itemView);
            title = itemView.findViewById(R.id.title_book);
            description = itemView.findViewById(R.id.description_book);
            author = itemView.findViewById(R.id.author_book);
            id= itemView.findViewById(R.id.id_book);
            add = itemView.findViewById(R.id.button_add);
    }
}
}
