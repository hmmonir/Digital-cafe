package com.example.joy.cafe.model;

public class OrderModel {
    public String table_no,food_id1,food_id2,food_id3,time,status;

    public OrderModel(String table_no, String food_id1, String food_id2, String food_id3,String time,String status) {
        this.table_no = table_no;
        this.food_id1 = food_id1;
        this.food_id2 = food_id2;
        this.food_id3 = food_id3;
        this.time= time;
        this.status = status;
    }
}
