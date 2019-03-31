package com.example.joy.cafe.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joy.cafe.FoodDeatils;
import com.example.joy.cafe.R;
import com.squareup.picasso.Picasso;

public class OverViewFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.overview_fragment, container, false);
        return  view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView=view.findViewById(R.id.imageView);
        TextView tvDetails=view.findViewById(R.id.tv_details);
        TextView tvName=view.findViewById(R.id.tv_name);


        FoodDeatils activity = (FoodDeatils) getActivity();

        Picasso.get().load(activity.getImage()).into(imageView);
        tvDetails.setText(activity.getDetails());
        tvName.setText(activity.getName());



    }
}
