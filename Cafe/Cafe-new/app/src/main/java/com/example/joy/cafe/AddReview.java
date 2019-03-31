package com.example.joy.cafe;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class AddReview extends AppCompatActivity {
    @BindView(R.id.titel_name)
    EditText name;
    @BindView(R.id.titel_email)
    EditText email;
    @BindView(R.id.des_rev)
    EditText des_rev;
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;
    ProgressDialog progressDialog;
    String food_id,str_name,str_email,str_des,str_rating;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        progressDialog=new ProgressDialog(this);
        food_id=getIntent().getStringExtra("food_id");

    }
    @OnClick(R.id.send_btn)
    public void sendReview(){
        str_name=name.getText().toString();
        str_email=email.getText().toString();
        str_des=des_rev.getText().toString();
        str_rating=String.valueOf(ratingBar.getRating());
        if(ratingBar.getRating()==0){
            Toast.makeText(AddReview.this,"Add rating first",Toast.LENGTH_SHORT).show();
        }else {
            addReview(str_rating,str_name,str_des,str_email,food_id);
        }

    }


    private void addReview(final String rating, final String titel, final String description,final  String email,final String food_id){
       progressDialog.show();

        StringRequest request=new StringRequest(Request.Method.POST, new Constant().addReviewUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.cancel();
                Toast.makeText(AddReview.this,"Success",Toast.LENGTH_SHORT).show();
                finish();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                params.put("email",email);
                params.put("rating",rating);
                params.put("description",description);
                params.put("place_id",food_id);
                params.put("name",titel);

                return params;
            }
        };

        request.setShouldCache(false);
        Volley.newRequestQueue(this).add(request);


    }
}
