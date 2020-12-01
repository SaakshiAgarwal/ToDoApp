package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTask extends AppCompatActivity {

    Button savebtn;
    Button delete;
    EditText titleTodo;
    EditText descTodo;
    EditText dateTodo;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        savebtn= findViewById(R.id.savebtn);
        titleTodo=findViewById(R.id.titletodo);
        descTodo=findViewById(R.id.desctodo);
        dateTodo=findViewById(R.id.datetodo);
        delete= findViewById(R.id.delete);
        titleTodo.setText(getIntent().getStringExtra("titletodo"));
       descTodo.setText(getIntent().getStringExtra("desctodo"));
        dateTodo.setText(getIntent().getStringExtra("datetodo"));
       final String key= getIntent().getStringExtra("keytodo");
        reference = FirebaseDatabase.getInstance().getReference().child("ToDo").child("Do" +key);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent j= new Intent(EditTask.this,MainActivity.class);
                            startActivity(j);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titletodo").setValue(titleTodo.getText().toString());
                        dataSnapshot.getRef().child("desctodo").setValue(descTodo.getText().toString());
                        dataSnapshot.getRef().child("datetodo").setValue(dateTodo.getText().toString());
                        dataSnapshot.getRef().child("keys").setValue(key);
                        Intent intent= new Intent(EditTask.this,MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}