package com.example.joy.cafe;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.ColorSpace;
import android.view.Display;

import com.example.joy.cafe.model.MainModel;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SharePreferenceClass {

    private List<MainModel> oderList;
    String My_PREFS_NAME="oder";
    Context context;
    public SharePreferenceClass(Context context) {
        this.context=context;
    }

    void save_in_sharePreference(int count,String food_id){
        SharedPreferences.Editor editor = context.getSharedPreferences(My_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(food_id, count);
        editor.apply();
    }
    List getOrderList(){
        SharedPreferences prefs = context.getSharedPreferences(My_PREFS_NAME, MODE_PRIVATE);

        for(int i=1;i<4;i++){
           int cout=prefs.getInt(String.valueOf(i),0);
            MainModel model=new MainModel();
            model.setCount(cout);
            model.setId(String.valueOf(i));
            oderList.add(model);
        }
        return oderList;
    }





}
