package com.droidkothon.joy.admin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String food1Name="Chicken Biriyani";
    private int food1Price=270;
    private String food1Image="https://i0.wp.com/farm5.staticflickr.com/4206/34630446624_ec6133ed16_o_d.jpg?resize=750%2C629&ssl=1";

    private String food2Name="Oven Baked Pasta";
    private int food2Price=190;
    private String food2Image="https://s3.amazonaws.com/finecooking.s3.tauntonclud.com/app/uploads/2017/04/18171808/fc69ro068-01-main.jpg";

    private String food3Name="Pizza 10'";
    private int food3Price=650;
    private String food3Image="https://9by10.ams3.digitaloceanspaces.com/General/slider-3-2a6af817-61e4-49a9-aec2-aa1904f701f0-20180807-044633.jpg";


    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private MainAdapter adapter;
    private List<MainModel> list=new ArrayList<>();
    public  List<MainModel> orderedList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cafe-36e91.firebaseio.com/live_orders");

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
                list.clear();

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    MainModel model=new MainModel();

                    model.setOrderNo(String.valueOf(ds.getKey()));

                    model.setQ1(String.valueOf(ds.child("food_id1").getValue()));
                    model.setQ2(String.valueOf(ds.child("food_id2").getValue()));
                    model.setQ3(String.valueOf(ds.child("food_id3").getValue()));
                    model.setTime(String.valueOf(ds.child("time").getValue()));
                    model.setStatus(String.valueOf(ds.child("status").getValue()));

                    model.setTable(String.valueOf(ds.child("table_no").getValue()));

                    list.add(model);

                }

                Collections.reverse(list);

                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(),"Something went wrong ! try again",Toast.LENGTH_SHORT).show();

            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
