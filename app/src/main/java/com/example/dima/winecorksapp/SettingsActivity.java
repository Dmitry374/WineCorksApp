package com.example.dima.winecorksapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pepperonas.materialdialog.MaterialDialog;

public class SettingsActivity extends AppCompatActivity {

    RelativeLayout typePreview;
//    TextView tvTypePreview;
    ImageView typeImage;
    SharedPreferences sPrefPreview;
    int numPreview;

    String[] arrayPreview;

    SharedPreferences sPrefSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        createSettingActivity();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void createSettingActivity() {

        sPrefPreview = getSharedPreferences("SharedPrefPreview", MODE_PRIVATE);
        numPreview = sPrefPreview.getInt("save_preview", 0);

        typePreview = (RelativeLayout) findViewById(R.id.typePreview);
        typeImage = (ImageView) findViewById(R.id.type_image);
//        tvTypePreview = (TextView) findViewById(R.id.tvTypePreview);

        arrayPreview = new String[]{"Сетка", "Список"};

//        tvTypePreview.setText(arrayPreview[numPreview]);

        if (numPreview == 0){
            typeImage.setImageResource(R.mipmap.ic_grid);
        } else {
            typeImage.setImageResource(R.mipmap.ic_recycler);
        }

        typePreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSingleChoice();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        createSettingActivity();
    }

    public void showSingleChoice() {

        new MaterialDialog.Builder(this)
                .title("Выбор представления")
                .message(null)
                .positiveText("OK")
                .negativeText("CANCEL")
                .positiveColor(R.color.green_500)
                .negativeColor(R.color.pink_400)
                .listItemsSingleSelection(false, arrayPreview)
                .selection(numPreview)
                .itemClickListener(new MaterialDialog.ItemClickListener() {
                    @Override
                    public void onClick(View v, int position, long id) {
                        super.onClick(v, position, id);
//                        Toast.makeText(getActivity(), corks[position], Toast.LENGTH_SHORT).show();
//                        selection = arrayUniqueBrands[position];

                        sPrefPreview = getSharedPreferences("SharedPrefPreview", MODE_PRIVATE);
                        SharedPreferences.Editor edPrefSelection = sPrefPreview.edit();
                        edPrefSelection.putInt("save_preview", position);
                        edPrefSelection.commit();

                        sPrefSelection = getSharedPreferences("SharedPrefSelection", MODE_PRIVATE);
                        SharedPreferences.Editor edPrefSelection2 = sPrefSelection.edit();
                        edPrefSelection2.putInt("save_selection", 0);
                        edPrefSelection2.commit();

                        if (numPreview == 0){
                            typeImage.setImageResource(R.mipmap.ic_grid);
                        } else {
                            typeImage.setImageResource(R.mipmap.ic_recycler);
                        }

                        createSettingActivity();

//                        tvTypePreview.setText(arrayPreview[position]);


                    }
                })
                .itemLongClickListener(new MaterialDialog.ItemLongClickListener() {
                    @Override
                    public void onLongClick(View view, int position, long id) {
                        super.onLongClick(view, position, id);
                    }
                })
                .showListener(new MaterialDialog.ShowListener() {
                    @Override
                    public void onShow(AlertDialog dialog) {
                        super.onShow(dialog);
                    }
                })
                .buttonCallback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                    }


                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                    }
                })
                .show();
    }
}
