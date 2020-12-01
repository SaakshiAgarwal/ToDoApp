package com.example.todo;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class TodoAdapter  extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {
    Context context;
    ArrayList<Todo> todo;
    public TodoAdapter(Context c, ArrayList<Todo> p)
    {
        context=c;
        todo= p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_todo,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.titletodo.setText(todo.get(i).getTitletodo());
        myViewHolder.desctodo.setText(todo.get(i).getDesctodo());
        myViewHolder.datetodo.setText(todo.get(i).getDatetodo());
        final  String getTitleTodo= todo.get(i).getTitletodo();
        final  String getDescTodo= todo.get(i).getDesctodo();
        final  String getDateTodo= todo.get(i).getDatetodo();
        final  String getKeys= todo.get(i).getKeys();
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, EditTask.class);
                intent.putExtra("titletodo",getTitleTodo);
                intent.putExtra("desctodo",getDescTodo);
                intent.putExtra("datetodo",getDateTodo);
                intent.putExtra("keys",getKeys);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todo.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView titletodo,desctodo,datetodo,keys;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titletodo=(TextView) itemView.findViewById(R.id.titletodo);
            desctodo=(TextView) itemView.findViewById(R.id.desctodo);
            datetodo=(TextView) itemView.findViewById(R.id.datetodo);
        }
    }
}
