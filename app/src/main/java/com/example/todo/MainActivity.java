package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseReference reference;
    RecyclerView mydoing;
    ArrayList<Todo> list;
    TodoAdapter todoAdapter;
    Button buttonplus;
    ImageView delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonplus= findViewById(R.id.buttonplus);
        buttonplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Createtodo.class);
                startActivity(intent);
            }
        });
        mydoing= findViewById(R.id.mydoing);
        mydoing.setLayoutManager(new LinearLayoutManager(this));
        list= new ArrayList<Todo>();
        reference= FirebaseDatabase.getInstance().getReference().child("ToDo");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Todo p= dataSnapshot1.getValue(Todo.class);
                    list.add(p);
                }
                todoAdapter= new TodoAdapter(MainActivity.this,list);
                mydoing.setAdapter(todoAdapter);
                todoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_LONG).show();

            }
        });
    }
}