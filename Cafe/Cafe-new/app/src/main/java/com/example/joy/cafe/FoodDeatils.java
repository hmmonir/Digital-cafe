package com.example.joy.cafe;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FoodDeatils extends AppCompatActivity {
    public static String food_id;
    private String name,image,details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_deatils);

        food_id=getIntent().getStringExtra("food_id");
        name=getIntent().getStringExtra("name");
        image=getIntent().getStringExtra("image");
        details=getIntent().getStringExtra("details");
        //code --for viewpager---
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Food Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Reviews"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter=new com.example.joy.cafe.adapter.PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //----end code of viewpager----
    }
    public String getName() {
        return name;
    }
    public String getImage() {
        return image;
    }
    public String getDetails() {
        return details;
    }
}
