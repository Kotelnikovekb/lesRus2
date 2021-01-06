package ru.lesruss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import ru.lesruss.Adapters.IntroViewPagerAdapter;
import ru.lesruss.Class.ScreenItem;

import static ru.lesruss.MainActivity.*;

public class IntroActivity extends AppCompatActivity {

    ViewPager viewPager;
    IntroViewPagerAdapter adapter;
    TabLayout tabLayout;
    Button next,start;
    int position=0;
    Animation btnAnim;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        sharedPreferences =getSharedPreferences(SETTINGS_S,MODE_PRIVATE);
        viewPager=findViewById(R.id.viewPager);
        tabLayout=findViewById(R.id.tabLayout);
        next=findViewById(R.id.button);
        start=findViewById(R.id.button2);

        if (!sharedPreferences.getBoolean(FIRS_START_S,true)){
            startActivity(new Intent(IntroActivity.this,MainActivity.class));
            finish();
        }

        btnAnim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        final List<ScreenItem> list=new ArrayList<>();
        list.add(new ScreenItem("Выберете","В линейку ассортимена входят более  1000 позиций товаров и более 15 пород дерева.",R.drawable.img1));
        list.add(new ScreenItem("Оплатите","Принимаем оплату разыными способами",R.drawable.img1));
        list.add(new ScreenItem("Ожидайте","Доставим ваш заказ всего за 500 рублей.",R.drawable.img1));


        adapter=new IntroViewPagerAdapter(IntroActivity.this,list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position=viewPager.getCurrentItem();
                if (position<list.size()){
                    position++;
                    viewPager.setCurrentItem(position);
                }
                if (position==list.size()-1){
                    loadLastScreen();
                }
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==list.size()-1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean(FIRS_START_S,false);
                editor.commit();
                startActivity(new Intent(IntroActivity.this,MainActivity.class));
                finish();
            }
        });

    }
    private void loadLastScreen() {
        tabLayout.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);
        start.setVisibility(View.VISIBLE);
        start.setAnimation(btnAnim);
    }
}