package com.example.joy.cafe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.joy.cafe.AddReview;
import com.example.joy.cafe.Constant;
import com.example.joy.cafe.R;
import com.example.joy.cafe.adapter.ReviewAdapter;
import com.example.joy.cafe.model.ReviewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.joy.cafe.FoodDeatils.food_id;

public class ReviewFragment extends Fragment {
    public static RecyclerView recyclerView;
    ReviewAdapter adapter;
    ArrayList<ReviewModel> reviews = new ArrayList<>();
    TextView writeRivew;
    double rating_int=0;
    int count5=0,count4=0,count3=0,count2=0,count1=0,total=0;
    TextView ratingTV,ratingCountTv,cusCountTv,count1Tv,count2Tv,count3Tv,count4Tv,count5Tv;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_fragment, container, false);

        recyclerView = view.findViewById(R.id.recycler_review);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ReviewAdapter(getContext(), reviews);
        recyclerView.setAdapter(adapter);

        writeRivew=view.findViewById(R.id.write_review);
        ratingTV=view.findViewById(R.id.rating_txt);
        ratingCountTv=view.findViewById(R.id.rat_count);
        cusCountTv=view.findViewById(R.id.cus_count);
        count1Tv=view.findViewById(R.id.count1);
        count2Tv=view.findViewById(R.id.count2);
        count3Tv=view.findViewById(R.id.count3);
        count4Tv=view.findViewById(R.id.count4);
        count5Tv=view.findViewById(R.id.count5);
        writeRivew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), AddReview.class);
//                intent.putExtra("cost",cost);
//                intent.putExtra("name",titel);
//                intent.putExtra("div",div);
//                intent.putExtra("distance",distence);
                intent.putExtra("food_id",food_id);
                startActivity(intent);
            }
        });
        getReviews();
        return  view;
    }
    private void getReviews(){
        //loadProgress();
        reviews.clear();
        recyclerView.removeAllViews();
        StringRequest request=new StringRequest(Request.Method.POST, new Constant().getReviewUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response",response);
                if (response.length() < 5) {

                }else {
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                            //calculation------
                            total++;
                            String rat=jsonObject.getString("rating");
                            rating_int=rating_int+Double.parseDouble(rat);
                            if(rat.equals("1")){
                                count1++;
                            }else  if(rat.equals("2.0")){
                                count2++;
                            }else  if(rat.equals("3.0")){
                                count3++;
                            }else  if(rat.equals("4.0")){
                                count4++;
                            }else  if(rat.equals("5.0")){
                                count5++;
                            }
                            //---end-----
                            ReviewModel reviewModel=new ReviewModel();
                            reviewModel.setProfileImage("p");
                            reviewModel.setUserName(jsonObject.getString("name"));
                            reviewModel.setRating(jsonObject.getString("rating"));
                            String descrip=jsonObject.getString("description");
                            if(descrip.equals("n/a")){
                                reviewModel.setReview(" ");
                            }else {
                                reviewModel.setReview(descrip);
                            }

                            reviews.add(reviewModel);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                //finishProgress();
                adapter = new ReviewAdapter(getContext(), reviews);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                ratingCountTv.setText("RATING( "+String.valueOf(total)+" )");
                ratingTV.setText(String.valueOf(rating_int/total)+" / 5");
                count1Tv.setText("("+String.valueOf(count1)+")");
                count2Tv.setText("("+String.valueOf(count2)+")");
                count3Tv.setText("("+String.valueOf(count3)+")");
                count4Tv.setText("("+String.valueOf(count4)+")");
                count5Tv.setText("("+String.valueOf(count5)+")");
                cusCountTv.setText("From "+String.valueOf(total)+" customers");




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                params.put("place_id",food_id);

                return params;
            }
        };

        request.setShouldCache(false);
        Volley.newRequestQueue(getActivity()).add(request);


    }

}
