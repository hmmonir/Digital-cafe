package com.example.joy.cafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import butterknife.BindView;

public class WaitingActivity extends AppCompatActivity {

    String order_id;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        textView=findViewById(R.id.text);
        order_id=getIntent().getStringExtra("order_id");
        Log.e("time",String.valueOf(order_id));
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cafe-36e91.firebaseio.com/live_orders");
        dbRef.addValueEventListener(changeListener);



    }

    ValueEventListener changeListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {


            for(DataSnapshot ds : dataSnapshot.getChildren()) {

                if(order_id.equals(String.valueOf(ds.getKey()))){
                    String time=String.valueOf(ds.child("time").getValue());
                    String status=String.valueOf(ds.child("status").getValue());
                    Log.e("time",String.valueOf(time));

                    Calendar now = Calendar.getInstance();
                    //add minutes to current date using Calendar.add method
                    now.add(Calendar.MINUTE,Integer.parseInt(time));

                    if (time.equals("0") && status.equals("0")){
                        textView.setText("Your order is submitted."+"\n"+"We will review soon"+"\n"+"Stay this page to show your order status.");
                    } else if (status.equals("0")) {
                        textView.setText("Food will arrive within  " + now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
                    } else {
                        textView.setText("Your food is delivered");
                    }
                }


            }


        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
