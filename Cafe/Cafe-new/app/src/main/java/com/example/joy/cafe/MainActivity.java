package com.example.joy.cafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.joy.cafe.adapter.MainAdapter;
import com.example.joy.cafe.model.MainModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private List<MainModel> list=new ArrayList<>();

    public  List<MainModel> orderedList=new ArrayList<>();

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cafe-36e91.firebaseio.com/items");

        recyclerView=findViewById(R.id.recyclerView);
        adapter=new MainAdapter(list,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getItems();

    }



    private void getItems(){


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d("TAG",ds.child("name").getValue().toString());

                    MainModel model=new MainModel();
                    model.setName(String.valueOf(ds.child("name").getValue()));
                    model.setPrice(String.valueOf(ds.child("price").getValue()));
                    model.setImage(String.valueOf(ds.child("image").getValue()));
                    model.setId(String.valueOf(ds.getKey()));
                    model.setDetsils(String.valueOf(ds.child("details").getValue()));
                    model.setCount(0);

                    list.add(model);

                    adapter.notifyDataSetChanged();

                }
                GlobalVeriableClass.orderList=list;


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_cart:
                startActivity(new Intent(MainActivity.this,CkoutActivity.class));


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
