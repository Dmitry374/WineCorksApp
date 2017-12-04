package com.example.dima.winecorksapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FullCorkActivity extends AppCompatActivity {

    ImageView fullImg;
    TextView tvFullBrand;
    TextView tvFullTitle;
    TextView tvFullPrice;
    Button btnBuy;
    ImageView imgArrow;
    LinearLayout linTitle;

    Boolean fullTitle = true;

    int imgRes;
    String title;
    String link;
    String price;
    String brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_cork);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();

        imgRes = intent.getIntExtra("img", 0);
        title = intent.getStringExtra("name");
        link = intent.getStringExtra("link");
        price = intent.getStringExtra("price");
        brand = intent.getStringExtra("brand");

        btnBuy = (Button) findViewById(R.id.btnBuy);
        imgArrow = (ImageView) findViewById(R.id.imgArrow);
        linTitle = (LinearLayout) findViewById(R.id.linTitle);

        fullImg = (ImageView) findViewById(R.id.fullImg);
        fullImg.setImageResource(imgRes);

        tvFullBrand = (TextView) findViewById(R.id.tvFullBrand);
        tvFullBrand.setText(brand);
        tvFullBrand.setTextColor(Color.RED);

        tvFullTitle = (TextView) findViewById(R.id.tvFullTitle);
        linTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fullTitle){
                    tvFullTitle.setMaxLines(10);
                    fullTitle = false;
                    imgArrow.setImageResource(android.R.drawable.arrow_up_float);
                } else {
                    tvFullTitle.setMaxLines(2);
                    fullTitle = true;
                    imgArrow.setImageResource(android.R.drawable.arrow_down_float);
                }
            }
        });
        tvFullTitle.setText(title);

        tvFullPrice = (TextView) findViewById(R.id.tvFullPrice);
        tvFullPrice.setText(price);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        });

//        Toast.makeText(this, price, Toast.LENGTH_SHORT).show();
    }
}
