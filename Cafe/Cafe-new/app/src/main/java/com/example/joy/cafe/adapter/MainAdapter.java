package com.example.joy.cafe.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joy.cafe.CkoutActivity;
import com.example.joy.cafe.FoodDeatils;
import com.example.joy.cafe.GlobalVeriableClass;
import com.example.joy.cafe.MainActivity;
import com.example.joy.cafe.R;
import com.example.joy.cafe.model.MainModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder>   {

    private List<MainModel> list;
    private Context context;
    int count_1=0,count_2=0,count_3=0;


    public MainAdapter(List<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.card_view,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final MainModel model=list.get(position);

        holder.name.setText(model.getName());
        holder.price.setText("৳ "+model.getPrice());
        Picasso.get().load(model.getImage()).into(holder.imageView);
        if (model.getCount()!=0){
            holder.count.setText(String.valueOf(model.getCount()));
            holder.count.setVisibility(View.VISIBLE);
            holder.imageMinus.setVisibility(View.VISIBLE);
        }else {
            holder.count.setVisibility(View.GONE);
            holder.imageMinus.setVisibility(View.GONE);
        }

        holder.imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainModel mainModel=new MainModel();

                    mainModel.setId(model.getId());
                    mainModel.setImage(model.getImage());
                    mainModel.setName(model.getName());
                    mainModel.setPrice(model.getPrice());
                    mainModel.setCount(model.getCount()+1);
                    list.set(position,mainModel);
                    notifyDataSetChanged();
                    GlobalVeriableClass.orderList=list;
                Toast.makeText(context,"Item added to cart",Toast.LENGTH_SHORT).show();

            }
        });
        holder.imageMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainModel mainModel=new MainModel();
                    mainModel.setId(model.getId());
                    mainModel.setImage(model.getImage());
                    mainModel.setName(model.getName());
                    mainModel.setPrice(model.getPrice());
                    mainModel.setCount(model.getCount()-1);
                    list.set(position,mainModel);
                    notifyDataSetChanged();
                    GlobalVeriableClass.orderList=list;


                Toast.makeText(context,"Item removed to cart",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView,imageViewAdd,imageMinus;
        TextView name,price,count;


        public MyViewHolder(View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.tv_name);
            price=itemView.findViewById(R.id.tv_price);
            imageView=itemView.findViewById(R.id.imageView);
            imageViewAdd=itemView.findViewById(R.id.img_add);
            imageMinus=itemView.findViewById(R.id.img_minus);
            count=itemView.findViewById(R.id.count);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            MainModel model=list.get(getAdapterPosition());
            Intent intent=new Intent(context,FoodDeatils.class);
            intent.putExtra("food_id",model.getId());
            intent.putExtra("image",model.getImage());
            intent.putExtra("name",model.getName());
            intent.putExtra("details",model.getDetsils());
            context.startActivity(intent);

        }
    }


}