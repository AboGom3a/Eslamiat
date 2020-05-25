package com.example.cardview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Query;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //ImageView myimage;
    Group group_item;
    TextView txt_couner;
    Button btn_clickcount,btn_reset;
    Context mcontext;
    DatabaseReference mreference;
    RecyclerView recyclerView;
    ArrayList<Images>imagesList;
    RecyclerAdaptor recyclerAdaptor;
    Intent intent;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        recyclerView=findViewById(R.id.recycler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // firebase connect
        database = FirebaseDatabase.getInstance();
        imagesList=new ArrayList<>();
        int id = getIntent().getIntExtra("id", 0);
        switch (id){
            case R.id.btn_Adyaa:{
                recyclerView.setVisibility(View.VISIBLE);
                group_item.setVisibility(View.INVISIBLE);
                myRef = database.getReference("users");
                connectToDB();
                break;
            }
            case R.id.btn_azkar:{
                recyclerView.setVisibility(View.VISIBLE);
                group_item.setVisibility(View.INVISIBLE);
                myRef = database.getReference("azkar");
                connectToDB();
                break;
            }
            case R.id.btn_tasabeeh: {
                recyclerView.setVisibility(View.INVISIBLE);
                group_item.setVisibility(View.VISIBLE);
                electrpraise();
                break;
            }
            default: Toast.makeText(MainActivity.this, "ERROR ", Toast.LENGTH_SHORT).show();

        }
        //if (id == R.id.main){
         //   myRef = database.getReference("users");
//            myRef.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    clearAll();
//                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
//                        Images images = snapshot.getValue(Images.class);
//                        imagesList.add(images);
//                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                    }
//                    recyclerAdaptor=new RecyclerAdaptor(mcontext,imagesList);
//                    recyclerView.setAdapter(recyclerAdaptor);
//                    recyclerAdaptor.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
        }
      /*  else {
            Toast.makeText(MainActivity.this, "ERROR ", Toast.LENGTH_SHORT).show();
        }*/
      private void setupView() {
          btn_clickcount=findViewById(R.id.btn_clickcount);
          btn_reset=findViewById(R.id.btn_reset);
          txt_couner=findViewById(R.id.txt_counter);
          group_item=findViewById(R.id.group_items);
      }
    public void connectToDB(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clearAll();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Images images = snapshot.getValue(Images.class);
                    imagesList.add(images);
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
                recyclerAdaptor=new RecyclerAdaptor(mcontext,imagesList);
                recyclerView.setAdapter(recyclerAdaptor);
                recyclerAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void clearAll(){
        if (imagesList!=null){
            imagesList.clear();
            if (recyclerAdaptor!=null){
                recyclerAdaptor.notifyDataSetChanged();
            }
        }
        imagesList=new ArrayList<>();

    }

    public void electrpraise(){
        txt_couner.setText("0");
        btn_clickcount.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                   String value = (String) txt_couner.getText();
                  int x= Integer.parseInt(value);
                   x=x+1;
                   String num= String.valueOf(x);
                  txt_couner.setText(num);
                  Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
              }
          });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_couner.setText("0");
                Toast.makeText(MainActivity.this, "counter reset ", Toast.LENGTH_SHORT).show();

            }
        });
            }
}
