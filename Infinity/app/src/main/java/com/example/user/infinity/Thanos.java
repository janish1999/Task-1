package com.example.user.infinity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Thanos extends AppCompatActivity {
    int index;
    List<String> infinity;
    String stone, stoneorder;
    ArrayAdapter<String> adapter;
    List<String> gem;
    final StringBuilder builder = new StringBuilder();
    final StringBuilder build = new StringBuilder();
    Parcelable state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanos);
        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        final TextView textView = (TextView) findViewById(R.id.textView);
        gem = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gem);
        final ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        final SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        final SharedPreferences.Editor editor = getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit();
        stoneorder = preferences.getString("order", "");
        stone = preferences.getString("stone", "");
        infinity = new ArrayList<String>();
        infinity.add("You have got the Power Stone");
        infinity.add("You have got the Space Stone");
        infinity.add("You have got the Time Stone");
        infinity.add("You have got the Reality Stone");
        infinity.add("You have got the Soul Stone");
        infinity.add("You have got the Mind Stone");
        layout.setBackgroundColor(preferences.getInt("color", Color.WHITE));
        index = preferences.getInt("index", 0);
        if (preferences.contains("order")) {

            infinity = Arrays.asList(stoneorder.split(","));
            if (index != 0) {
                textView.setText(infinity.get(index - 1));

                Set<String> get = preferences.getStringSet("Set",null);
                gem.addAll(get);
        }}
                else{
            Collections.shuffle(infinity, new Random(System.nanoTime()));
                  preferences.edit().clear().apply();
                for(int i=0;i<6;i++){
        String a=infinity.get(i);
        build.append(a);
        build.append(",");
        }
        editor.putString("order",build.toString()).apply();
        }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setTextColor(Color.BLACK);
                    textView.setText(infinity.get(index));
                    if (infinity.get(index).equals("You have got the Power Stone")) {
                        gem.add("Power Stone");
                        adapter.notifyDataSetChanged();
                        layout.setBackgroundColor(Color.parseColor("#800080"));

                    }
                    if (infinity.get(index).equals("You have got the Space Stone")) {
                        gem.add("Space Stone");
                        adapter.notifyDataSetChanged();
                        layout.setBackgroundColor(Color.BLUE);

                    }
                    if (infinity.get(index).equals("You have got the Time Stone")) {
                        gem.add("Time Stone");
                        adapter.notifyDataSetChanged();
                        layout.setBackgroundColor(Color.GREEN);

                    }
                    if (infinity.get(index).equals("You have got the Reality Stone")) {
                        gem.add("Reality Stone");
                        adapter.notifyDataSetChanged();
                        layout.setBackgroundColor(Color.RED);

                    }
                    if (infinity.get(index).equals("You have got the Soul Stone")) {
                        gem.add("Soul Stone");
                        adapter.notifyDataSetChanged();
                        layout.setBackgroundColor(Color.parseColor("#FFA500"));


                    }
                    if (infinity.get(index).equals("You have got the Mind Stone")) {
                        gem.add("Mind Stone");
                        adapter.notifyDataSetChanged();
                        layout.setBackgroundColor(Color.YELLOW);


                    }
                    ColorDrawable viewColor = (ColorDrawable) layout.getBackground();
                    int colorId = viewColor.getColor();
                    SharedPreferences.Editor editor = getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit();
                    editor.putInt("color", colorId).apply();
                   Set<String> newset =new HashSet<String> (gem);
                   editor.putStringSet("Set",newset).apply();
                    index++;
                    editor.putInt("index", index).apply();
                    if (index >= 6) {

                        new CountDownTimer(500, 1000) {
                            public void onFinish() {
                                textView.setText("SNAP YOUR FINGER!!!!!!!!!! , MIGHTY THANOS");
                                textView.setTextColor(Color.WHITE);
                                layout.setBackgroundColor(Color.BLACK);
                                SharedPreferences.Editor editor = getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit();
                                editor.putInt("color", Color.WHITE);
                                editor.apply();
                                gem.clear();

                                adapter.notifyDataSetChanged();
                                preferences.edit().clear().apply();
                                Collections.shuffle(infinity, new Random(System.nanoTime()));
                                build.setLength(0);
                                for (int i = 0; i < 6; i++) {
                                    String a = infinity.get(i);
                                    build.append(a);
                                    build.append(",");
                                }
                                editor.putString("order", build.toString()).apply();
                                index = 0;
                                editor.putInt("index", index).apply();
                            }

                            @Override
                            public void onTick(long millisUntilFinished) {

                            }
                        }.start();
                    }
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText("");
                    layout.setBackgroundColor(Color.WHITE);
                    gem.clear();
                    adapter.notifyDataSetChanged();
                    preferences.edit().clear().apply();
                    Collections.shuffle(infinity, new Random(System.nanoTime()));
                    build.setLength(0);
                    for (int i = 0; i < 6; i++) {
                        String a = infinity.get(i);
                        build.append(a);
                        build.append(",");
                    }
                    editor.putString("order", build.toString()).apply();

                    index = 0;
                    editor.putInt("index", index).apply();

                }

            });

        }
    }


