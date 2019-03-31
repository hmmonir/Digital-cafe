package com.example.joy.cafe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.joy.cafe.R;
import com.example.joy.cafe.model.ReviewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hyogeun.github.com.colorratingbarlib.ColorRatingBar;

/**
 * Created by root on 12/18/17.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {
    Context context;
    ArrayList<ReviewModel> reviews=new ArrayList<>();
    float rating_f;

    public ReviewAdapter(Context context, ArrayList<ReviewModel> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_review,parent,false);
        ReviewAdapter.MyViewHolder viewHolder = new ReviewAdapter.MyViewHolder(view,reviews);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ReviewModel model=reviews.get(position);
        holder.userName.setText(model.getUserName());
        holder.rating.setText(model.getRating());
        holder.review.setText(model.getReview());
//        Picasso.with( context )
//                .load(model.getProfileImage())
//                .error( R.drawable.avatar )
//                .placeholder( R.drawable.progress_animation )
//                .into( holder.imageView);
        rating_f=Float.parseFloat(model.getRating());
        holder.colorRatingBar.setRatingProgressColor(R.color.colorPrimary);
        holder.colorRatingBar.setRatingEmptyColor(R.color.colorAccent);
        holder.colorRatingBar.setRating(rating_f);

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ArrayList<ReviewModel> arrayList=new ArrayList<>();
        TextView userName,review,rating;
        ColorRatingBar colorRatingBar;
        ImageView imageView;

        public MyViewHolder(View itemView, ArrayList<ReviewModel> arrayList) {
            super(itemView);
            this.arrayList=arrayList;
            imageView=itemView.findViewById(R.id.profile_image);
            userName=itemView.findViewById(R.id.user_name);
            review=itemView.findViewById(R.id.review);
            rating=itemView.findViewById(R.id.rating);
            colorRatingBar=itemView.findViewById(R.id.rating_1);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

        }
    }
}
