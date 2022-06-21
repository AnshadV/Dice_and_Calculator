package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.multiscreen.FileUpload.FbUploadActivity;
import com.example.multiscreen.Slide.EndlessRecyclerViewScrollListener;
import com.example.multiscreen.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn_dice, btn_calculator, btn_tts;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<SlideModel> slideModelsArrayList;
    ActivityMainBinding binding;
    RecyclerView recyclerView;
    String current_ip = "";
    boolean loading = false;
    CustomAdapter adapter;
    private EndlessRecyclerViewScrollListener scrollListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_dice = findViewById(R.id.btn_dice);
        btn_calculator = findViewById(R.id.btn_calculator);
        btn_tts = findViewById(R.id.btn_tts);
        recyclerView = findViewById(R.id.recyclerview);

        //slider
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        prepare_gallery_with_image();

        //enable infinite scroll for recycler view
        scrollListener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                prepare_gallery_with_image();
            }
        };
        recyclerView.addOnScrollListener(scrollListener);



        btn_dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to dice activity
                startActivity(new Intent(MainActivity.this, DiceActivity.class));

            }
        });

        btn_calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Clicked calculator", Toast.LENGTH_SHORT).show();
                //go to CalcActivity
                startActivity(new Intent(MainActivity.this, CalcActivity.class));
            }
        });

        //go to TtsActivity
        btn_tts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TtsActivity.class));
            }
        });

        //go to FileActivity
        Button btn_file = findViewById(R.id.btn_file);
        btn_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FbUploadActivity.class));
            }
        });


    }
    public void prepare_gallery_with_image() {
        slideModelsArrayList = new ArrayList<>();

        slideModelsArrayList.add(new SlideModel("Kids","https://firebasestorage.googleapis.com/v0/b/multiscreen-2c067.appspot.com/o/uploads%2Fstock-footage-kids-exploring-the-beaches-of-newquay-coast-around-cornwall-south-west-england-uk.webm?alt=media&token=a03123e7-d8f2-4331-a4f9-0f54b810f694","1","https://firebasestorage.googleapis.com/v0/b/multiscreen-2c067.appspot.com/o/uploads%2Fkidsimage.png?alt=media&token=41ab7969-7f45-4ea4-bfe3-eb5b27a8312c"));
        slideModelsArrayList.add(new SlideModel("Seagull","https://firebasestorage.googleapis.com/v0/b/multiscreen-2c067.appspot.com/o/uploads%2Fstock-footage-white-seagulls-flying-above-the-great-salt-lake-on-a-sunny-day-beautiful-scenery-view-from-a-car.webm?alt=media&token=9f637699-da65-4018-bbdc-39bb18224476","2","https://firebasestorage.googleapis.com/v0/b/multiscreen-2c067.appspot.com/o/uploads%2Fseagullimage.png?alt=media&token=62c5682e-6c48-4448-9fb6-da6b04801ba1"));
        slideModelsArrayList.add(new SlideModel("Image","","3","https://firebasestorage.googleapis.com/v0/b/multiscreen-2c067.appspot.com/o/uploads%2Fbanner_eg.png?alt=media&token=1069bdfb-6f5c-4ff2-86e8-0ee8e03179ac"));

        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, slideModelsArrayList);
        recyclerView.setAdapter(customAdapter);

    }


}