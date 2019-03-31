package com.example.joy.cafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joy.cafe.model.MainModel;
import com.example.joy.cafe.model.OrderModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CkoutActivity extends AppCompatActivity {
    @BindView(R.id.tv_name_1)
    TextView name_1;
    @BindView(R.id.tv_name2)
    TextView name_2;
    @BindView(R.id.tv_name3)
    TextView name_3;
    @BindView(R.id.tv_price_1)
    TextView price_1;
    @BindView(R.id.tv_price2)
    TextView price_2;
    @BindView(R.id.tv_price3)
    TextView price_3;
    @BindView(R.id.food_image_1)
    ImageView foodImage1;
    @BindView(R.id.food_image_2)
    ImageView foodImage2;
    @BindView(R.id.food_image3)
    ImageView foodImage3;
    @BindView(R.id.count1)
    TextView count1;
    @BindView(R.id.count2)
    TextView count2;
    @BindView(R.id.count3)
    TextView count3;
    @BindView(R.id.pay_amount)
    TextView pay_amount;
    @BindView(R.id.ck_out)
    Button ck_out_btn;
    List<MainModel> list;
    int price_int_1=0,price_int_2=0,price_int_3=0;
    int count_int_1=0,count_int_2=0,count_int_3=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ckout);
        // bind the view using butterknife
        ButterKnife.bind(this);
        list=GlobalVeriableClass.orderList;
        name_1.setText(list.get(0).getName());
        name_2.setText(list.get(1).getName());
        name_3.setText(list.get(2).getName());

        price_1.setText("৳ "+list.get(0).getPrice());
        price_2.setText("৳ "+list.get(1).getPrice());
        price_3.setText("৳ "+list.get(2).getPrice());

        Picasso.get().load(list.get(0).getImage()).into(foodImage1);
        Picasso.get().load(list.get(1).getImage()).into(foodImage2);
        Picasso.get().load(list.get(2).getImage()).into(foodImage3);

        count1.setText(String.valueOf(list.get(0).getCount()));
        count2.setText(String.valueOf(list.get(1).getCount()));
        count3.setText(String.valueOf(list.get(2).getCount()));

        set_pay_aount();






    }
    @OnClick(R.id.img_add1)
    public  void add1(){
        int i=list.get(0).getCount();
        i=i+1;
        MainModel mainModel=new MainModel();
        MainModel model=new MainModel();
        model=list.get(0);
        mainModel.setId(model.getId());
        mainModel.setImage(model.getImage());
        mainModel.setName(model.getName());
        mainModel.setPrice(model.getPrice());
        mainModel.setCount(model.getCount()+1);
        list.set(0,mainModel);
        count1.setText(String.valueOf(i));
        set_pay_aount();
    }
    @OnClick(R.id.img_add2)
    public  void add2(){
        int i=list.get(1).getCount();
        i=i+1;
        MainModel mainModel=new MainModel();
        MainModel model=new MainModel();
        model=list.get(1);
        mainModel.setId(model.getId());
        mainModel.setImage(model.getImage());
        mainModel.setName(model.getName());
        mainModel.setPrice(model.getPrice());
        mainModel.setCount(model.getCount()+1);
        list.set(1,mainModel);
        count2.setText(String.valueOf(i));
        set_pay_aount();

    }
    @OnClick(R.id.img_add3)
    public  void add3(){
        int i=list.get(2).getCount();
        i=i+1;
        MainModel mainModel=new MainModel();
        MainModel model=new MainModel();
        model=list.get(2);
        mainModel.setId(model.getId());
        mainModel.setImage(model.getImage());
        mainModel.setName(model.getName());
        mainModel.setPrice(model.getPrice());
        mainModel.setCount(model.getCount()+1);
        list.set(2,mainModel);
        count3.setText(String.valueOf(i));
        set_pay_aount();

    }
    @OnClick(R.id.img_minus1)
    public  void minus1(){
        if(count_int_1!=0){
            int i=list.get(0).getCount();
            i=i-1;
            MainModel mainModel=new MainModel();
            MainModel model=new MainModel();
            model=list.get(0);
            mainModel.setId(model.getId());
            mainModel.setImage(model.getImage());
            mainModel.setName(model.getName());
            mainModel.setPrice(model.getPrice());
            mainModel.setCount(model.getCount()-1);
            list.set(0,mainModel);
            count1.setText(String.valueOf(i));
            set_pay_aount();
        }


    }
    @OnClick(R.id.img_minus2)
    public  void minus2(){
        if(count_int_2!=0){
            int i=list.get(1).getCount();
            i=i-1;
            MainModel mainModel=new MainModel();
            MainModel model=new MainModel();
            model=list.get(1);
            mainModel.setId(model.getId());
            mainModel.setImage(model.getImage());
            mainModel.setName(model.getName());
            mainModel.setPrice(model.getPrice());
            mainModel.setCount(model.getCount()-1);
            list.set(1,mainModel);
            count2.setText(String.valueOf(i));
            set_pay_aount();
        }


    }
    @OnClick(R.id.img_minus3)
    public  void minus3(){
        if(count_int_3!=0){
            int i=list.get(2).getCount();
            i=i-1;
            MainModel mainModel=new MainModel();
            MainModel model=new MainModel();
            model=list.get(2);
            mainModel.setId(model.getId());
            mainModel.setImage(model.getImage());
            mainModel.setName(model.getName());
            mainModel.setPrice(model.getPrice());
            mainModel.setCount(model.getCount()-1);
            list.set(2,mainModel);
            count3.setText(String.valueOf(i));
            set_pay_aount();
        }


    }
    private void set_pay_aount(){
        price_int_1=Integer.parseInt(list.get(0).getPrice());
        price_int_2=Integer.parseInt(list.get(1).getPrice());
        price_int_3=Integer.parseInt(list.get(2).getPrice());
        count_int_1=list.get(0).getCount();
        count_int_2=list.get(1).getCount();
        count_int_3=list.get(2).getCount();
        pay_amount.setText(String.valueOf(price_int_1*count_int_1+price_int_2*count_int_2+price_int_3*count_int_3)+" Tk");
    }

    @OnClick(R.id.ck_out)
    public void submitChackOut(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("live_orders");
        String orderId = mDatabase.push().getKey();
        OrderModel orderModel = new OrderModel("1", String.valueOf(list.get(0).getCount()),String.valueOf(list.get(1).getCount()),String.valueOf(list.get(2).getCount()),"0","0");
        mDatabase.child(orderId).setValue(orderModel);
        startActivity(new Intent(CkoutActivity.this,WaitingActivity.class).putExtra("order_id",orderId));
        finish();
        //Toast.makeText(getApplicationContext(),"Your Order is Submitted",Toast.LENGTH_SHORT).show();
    }
}
