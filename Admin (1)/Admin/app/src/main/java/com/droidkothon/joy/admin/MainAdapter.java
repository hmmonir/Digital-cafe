package com.droidkothon.joy.admin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder>   {


    private String food1Name="Chicken Biriyani";
    private int food1Price=270;
    private String food1Image="https://i0.wp.com/farm5.staticflickr.com/4206/34630446624_ec6133ed16_o_d.jpg?resize=750%2C629&ssl=1";

    private String food2Name="Oven Baked Pasta";
    private int food2Price=190;
    private String food2Image="https://s3.amazonaws.com/finecooking.s3.tauntonclud.com/app/uploads/2017/04/18171808/fc69ro068-01-main.jpg";

    private String food3Name="Pizza 10'";
    private int food3Price=650;
    private String food3Image="https://9by10.ams3.digitaloceanspaces.com/General/slider-3-2a6af817-61e4-49a9-aec2-aa1904f701f0-20180807-044633.jpg";


    private List<MainModel> list;
    private Context context;
    private int total=0;

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
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final MainModel model=list.get(position);


        holder.orderNo.setText("Order No: "+model.getOrderNo());

        total=0;

        if (model.getQ1().equals("0")){
            holder.ll1.setVisibility(View.GONE);

        }else {
            holder.name1.setText(food1Name+" x"+String.valueOf(model.q1));

            int sum = Integer.parseInt(model.getQ1())*food1Price;
            total=total+sum;

            holder.price1.setText(String.valueOf(sum));
        }
        if (model.getQ2().equals("0")){
            holder.ll2.setVisibility(View.GONE);
        }else {
            holder.name2.setText(food2Name+" x"+String.valueOf(model.q2));
            int sum = Integer.parseInt(model.getQ2())*food2Price;
            total=total+sum;
            holder.price2.setText(String.valueOf(sum));
        }
        if (model.getQ3().equals("0")){
            holder.ll3.setVisibility(View.GONE);
        }else {
            holder.name3.setText(food3Name+" x"+String.valueOf(model.q3));
            int sum = Integer.parseInt(model.getQ3())*food3Price;
            total=total+sum;
            holder.price3.setText(String.valueOf(sum));
        }
        if(model.getStatus().equals("0")){
            if(model.getTime().equals("0")){
                holder.time_liner.setVisibility(View.VISIBLE);
                holder.delivered_btn.setVisibility(View.GONE);
            }else {
                holder.time_liner.setVisibility(View.GONE);
                holder.delivered_btn.setVisibility(View.VISIBLE);
            }
        }else {
            holder.time_liner.setVisibility(View.GONE);
            holder.delivered_btn.setVisibility(View.GONE);
        }


        holder.totalBill.setText(String.valueOf(total));

        ///handle time
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("live_orders").child(model.getOrderNo()).child("time");
        holder.time_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mDatabase.setValue("10");
            }
        });
        holder.time_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.setValue("20");

            }
        });
        holder.time_30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.setValue("30");

            }
        });
        holder.delivered_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("live_orders").child(model.getOrderNo()).child("status");
                mDatabase.setValue("1");
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        LinearLayout ll1,ll2,ll3,time_liner;
        TextView name1,name2,name3,price1,price2,price3,orderNo,totalBill,time_10,time_20,time_30,delivered_btn;


        public MyViewHolder(View itemView) {
            super(itemView);

            name1=itemView.findViewById(R.id.tv_food1name);
            name2=itemView.findViewById(R.id.tv_food2name);
            name3=itemView.findViewById(R.id.tv_food3name);
            price1=itemView.findViewById(R.id.price1);
            price2=itemView.findViewById(R.id.price2);
            price3=itemView.findViewById(R.id.price3);

            ll1=itemView.findViewById(R.id.ll1);
            ll2=itemView.findViewById(R.id.ll2);
            ll3=itemView.findViewById(R.id.ll3);

            orderNo=itemView.findViewById(R.id.order_no);
            totalBill=itemView.findViewById(R.id.tv_total);
            time_10=itemView.findViewById(R.id.time_10);
            time_20=itemView.findViewById(R.id.time_20);
            time_30=itemView.findViewById(R.id.time_30);
            time_liner=itemView.findViewById(R.id.time_liner);
            delivered_btn=itemView.findViewById(R.id.delivered_btn);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            //Toast.makeText(context,"CLICKED !!",Toast.LENGTH_SHORT).show();

//            MobileModel mobileModel=list.get(getAdapterPosition());
//            Intent intent=new Intent(context,DetailsActivity.class);
//
//            intent.putExtra("name",mobileModel.name);
//            intent.putExtra("des",mobileModel.description);
//            intent.putExtra("logo",mobileModel.logo);
//
//            context.startActivity(intent);

        }
    }



}