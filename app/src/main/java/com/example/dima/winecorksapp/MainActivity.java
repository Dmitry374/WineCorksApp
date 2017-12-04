package com.example.dima.winecorksapp;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pepperonas.materialdialog.MaterialDialog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    int[] ids;
    int[] names;
    int[] images;
    int[] links;
    int[] price;
    int[] brand;

    ArrayList<Cork> corkList;   //  Общая коллекция
    ArrayList<Cork> corkList2;  //  Коллекция для выборки

    GridView gvMain;
    GridAdapter gridAdapter;

    String[] arrayUniqueBrands;  // Массив уникальных названий бренда
    SharedPreferences sPrefSelection;
    int numSelection;  //  Выбор бренда из диалогового окна

    private RecyclerView mRecyclerView;

    SharedPreferences sPrefPreview;
    int numPreview;  //  Тип представления


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createActivity();

    }

    @Override
    protected void onResume() {
        super.onResume();

//        createActivity();
    }

    private void createActivity() {
        //        Извлечение Выбора представления
        sPrefPreview = getSharedPreferences("SharedPrefPreview", MODE_PRIVATE);
        numPreview = sPrefPreview.getInt("save_preview", 0);

        //        Извлечение Позиции
        sPrefSelection = getSharedPreferences("SharedPrefSelection", MODE_PRIVATE);
        numSelection = sPrefSelection.getInt("save_selection", 0);

        corkList2 = new ArrayList<>();

        ids = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21};
        names = new int[]{R.string.cork_1, R.string.cork_2, R.string.cork_3, R.string.cork_4, R.string.cork_5, R.string.cork_6, R.string.cork_7, R.string.cork_8, R.string.cork_9, R.string.cork_10, R.string.cork_11, R.string.cork_12, R.string.cork_13, R.string.cork_14, R.string.cork_15, R.string.cork_16, R.string.cork_17, R.string.cork_18, R.string.cork_19, R.string.cork_20, R.string.cork_21};
        images = new int[]{R.drawable.cork_1, R.drawable.cork_2, R.drawable.cork_3, R.drawable.cork_4, R.drawable.cork_5, R.drawable.cork_6, R.drawable.cork_7, R.drawable.cork_8, R.drawable.cork_9, R.drawable.cork_10, R.drawable.cork_11, R.drawable.cork_12, R.drawable.cork_13, R.drawable.cork_14, R.drawable.cork_15, R.drawable.cork_16, R.drawable.cork_17, R.drawable.cork_18, R.drawable.cork_19, R.drawable.cork_20, R.drawable.cork_21};
        links = new int[]{R.string.link_1, R.string.link_2, R.string.link_3, R.string.link_4, R.string.link_5, R.string.link_6, R.string.link_7, R.string.link_8, R.string.link_9, R.string.link_10, R.string.link_11, R.string.link_12, R.string.link_13, R.string.link_14, R.string.link_15, R.string.link_16, R.string.link_17, R.string.link_18, R.string.link_19, R.string.link_20, R.string.link_21};
        price = new int[]{R.string.price_1, R.string.price_2, R.string.price_3, R.string.price_4, R.string.price_5, R.string.price_6, R.string.price_7, R.string.price_8, R.string.price_9, R.string.price_10, R.string.price_11, R.string.price_12, R.string.price_13, R.string.price_14, R.string.price_15, R.string.price_16, R.string.price_17, R.string.price_18, R.string.price_19, R.string.price_20, R.string.price_21};
        brand = new int[]{R.string.brand_1, R.string.brand_2, R.string.brand_3, R.string.brand_4, R.string.brand_5, R.string.brand_6, R.string.brand_7, R.string.brand_8, R.string.brand_9, R.string.brand_10, R.string.brand_11, R.string.brand_12, R.string.brand_13, R.string.brand_14, R.string.brand_15, R.string.brand_16, R.string.brand_17, R.string.brand_18, R.string.brand_19, R.string.brand_20, R.string.brand_21};


        corkList = new ArrayList<>();

        for (int i = 0; i < ids.length; i++) {
            corkList.add(new Cork(ids[i], names[i], images[i], links[i], price[i], brand[i]));
        }

//        ----------- Создаем массив уникальных имен брендов -------
        HashSet<String> uniqueBrands = new HashSet<>();

        for (int i = 0; i < brand.length; i++){
//            uniqueBrands.add(String.valueOf(brand[i]));
            uniqueBrands.add((String) getResources().getText(corkList.get(i).getBrand()));
        }

        arrayUniqueBrands = new String[uniqueBrands.size()+1];
        arrayUniqueBrands[0] = "Все";

        Iterator<String> iter = uniqueBrands.iterator();
        while (iter.hasNext()) {
            for (int i = 0; i < uniqueBrands.size(); i++){
                arrayUniqueBrands[i+1] = iter.next();
            }
        }

//        arrayUniqueBrands = uniqueBrands.toArray(new String[uniqueBrands.size()]);
//        ---------------------------------------------------------






        if (numPreview == 0){   //  Если тип представления Grid   // 0 - grid   1 - recycler
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            gvMain = (GridView) findViewById(R.id.gridView);

            //        Возвращает ширину экраан
            Display display = getWindowManager().getDefaultDisplay();
            int displayWidth = display.getWidth();

            gvMain.setColumnWidth((displayWidth - 20)/2);

            if (numSelection == 0){
                gridAdapter = new GridAdapter(MainActivity.this, 1, corkList);
                gvMain.setAdapter(gridAdapter);

                gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parrent, View view, int position, long id) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String) getResources().getText(corkList.get(position).getLink())));
//                startActivity(intent);

                        Intent intent = new Intent(MainActivity.this, FullCorkActivity.class);
                        intent.putExtra("name", (String) getResources().getText(corkList.get(position).getName()));
                        intent.putExtra("img", corkList.get(position).getImage());
                        intent.putExtra("link", (String) getResources().getText(corkList.get(position).getLink()));
                        intent.putExtra("price", (String) getResources().getText(corkList.get(position).getPrice()));
                        intent.putExtra("brand", (String) getResources().getText(corkList.get(position).getBrand()));
                        startActivity(intent);

//                Log.d("myLogs", (String) getResources().getText(corkList.get(position).getLink()));
                    }
                });


            } else {
                corkList2.removeAll(corkList2);
                for (int i = 0; i < corkList.size(); i++){
                    if (arrayUniqueBrands[numSelection].equals((String) getResources().getText(corkList.get(i).getBrand()))){
                        corkList2.add(corkList.get(i));
                    }
                }
                gridAdapter = new GridAdapter(MainActivity.this, 1, corkList2);
                gvMain.setAdapter(gridAdapter);

                gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parrent, View view, int position, long id) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String) getResources().getText(corkList.get(position).getLink())));
//                startActivity(intent);

                        Intent intent = new Intent(MainActivity.this, FullCorkActivity.class);
                        intent.putExtra("name", (String) getResources().getText(corkList2.get(position).getName()));
                        intent.putExtra("img", corkList2.get(position).getImage());
                        intent.putExtra("link", (String) getResources().getText(corkList2.get(position).getLink()));
                        intent.putExtra("price", (String) getResources().getText(corkList2.get(position).getPrice()));
                        intent.putExtra("brand", (String) getResources().getText(corkList2.get(position).getBrand()));
                        startActivity(intent);

//                Log.d("myLogs", (String) getResources().getText(corkList.get(position).getLink()));
                    }
                });

            }

//            gridAdapter = new GridAdapter(this, 1, corkList);
//
//            gvMain.setAdapter(gridAdapter);

//            gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parrent, View view, int position, long id) {
////                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String) getResources().getText(corkList.get(position).getLink())));
////                startActivity(intent);
//
//                    Intent intent = new Intent(MainActivity.this, FullCorkActivity.class);
//                    intent.putExtra("name", (String) getResources().getText(corkList.get(position).getName()));
//                    intent.putExtra("img", corkList.get(position).getImage());
//                    intent.putExtra("link", (String) getResources().getText(corkList.get(position).getLink()));
//                    intent.putExtra("price", (String) getResources().getText(corkList.get(position).getPrice()));
//                    intent.putExtra("brand", (String) getResources().getText(corkList.get(position).getBrand()));
//                    startActivity(intent);
//
////                Log.d("myLogs", (String) getResources().getText(corkList.get(position).getLink()));
//                }
//            });

        } else {   //  Если тип представления Recycler
            setContentView(R.layout.activity_recycler);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            mRecyclerView = findViewById(R.id.recycler_view_main);

            LinearLayoutManager llm = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(llm);




            if (numSelection == 0){

                loadCorksData(corkList);

            } else {
                corkList2.removeAll(corkList2);
                for (int i = 0; i < corkList.size(); i++){
                    if (arrayUniqueBrands[numSelection].equals((String) getResources().getText(corkList.get(i).getBrand()))){
                        corkList2.add(corkList.get(i));
                    }
                }

                loadCorksData(corkList2);

            }





//            loadCorksData();


        }
    }

    private void loadCorksData(ArrayList<Cork> arrayList) {

            mRecyclerView.setAdapter(new RecyclerAdapter(this, arrayList, new RecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Cork item) {

                    Intent intent = new Intent(MainActivity.this, FullCorkActivity.class);
                    intent.putExtra("name", (String) getResources().getText(item.getName()));
                    intent.putExtra("img", item.getImage());
                    intent.putExtra("link", (String) getResources().getText(item.getLink()));
                    intent.putExtra("price", (String) getResources().getText(item.getPrice()));
                    intent.putExtra("brand", (String) getResources().getText(item.getBrand()));
                    startActivity(intent);
                }

            }));


        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
        }

        if (id == R.id.action_filter){
            showSingleChoice();
        }

        return super.onOptionsItemSelected(item);
    }

    public void showSingleChoice() {

        corkList2 = new ArrayList<>();

        sPrefSelection = getSharedPreferences("SharedPrefSelection", MODE_PRIVATE);
        numSelection = sPrefSelection.getInt("save_selection", 0);

        new MaterialDialog.Builder(this)
                .title("Бренды")
                .message(null)
                .positiveText("OK")
                .negativeText("CANCEL")
                .positiveColor(R.color.green_500)
                .negativeColor(R.color.pink_400)
                .listItemsSingleSelection(false, arrayUniqueBrands)
                .selection(numSelection)
                .itemClickListener(new MaterialDialog.ItemClickListener() {
                    @Override
                    public void onClick(View v, int position, long id) {
                        super.onClick(v, position, id);
//                        Toast.makeText(getActivity(), corks[position], Toast.LENGTH_SHORT).show();
//                        selection = arrayUniqueBrands[position];

                        sPrefSelection = getSharedPreferences("SharedPrefSelection", MODE_PRIVATE);
                        SharedPreferences.Editor edPrefSelection = sPrefSelection.edit();
                        edPrefSelection.putInt("save_selection", position);
                        edPrefSelection.commit();

                        if (numPreview == 0){  //  0 - grid    1 - recycler
                            if (position == 0){
                                gridAdapter = new GridAdapter(MainActivity.this, 1, corkList);
                                gvMain.setAdapter(gridAdapter);
                            } else {
                                corkList2.removeAll(corkList2);
                                for (int i = 0; i < corkList.size(); i++){
                                    if (arrayUniqueBrands[position].equals((String) getResources().getText(corkList.get(i).getBrand()))){
                                        corkList2.add(corkList.get(i));
                                    }
                                }
                                gridAdapter = new GridAdapter(MainActivity.this, 1, corkList2);
                                gvMain.setAdapter(gridAdapter);

                            }

                            createActivity();
                        } else {
                            if (position == 0){
                                mRecyclerView.setAdapter(new RecyclerAdapter(MainActivity.this, corkList, new RecyclerAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(Cork item) {

                                        Intent intent = new Intent(MainActivity.this, FullCorkActivity.class);
                                        intent.putExtra("name", (String) getResources().getText(item.getName()));
                                        intent.putExtra("img", item.getImage());
                                        intent.putExtra("link", (String) getResources().getText(item.getLink()));
                                        intent.putExtra("price", (String) getResources().getText(item.getPrice()));
                                        intent.putExtra("brand", (String) getResources().getText(item.getBrand()));
                                        startActivity(intent);
                                    }

                                }));
                            } else {
                                corkList2.removeAll(corkList2);
                                for (int i = 0; i < corkList.size(); i++){
                                    if (arrayUniqueBrands[position].equals((String) getResources().getText(corkList.get(i).getBrand()))){
                                        corkList2.add(corkList.get(i));
                                    }
                                }

                                mRecyclerView.setAdapter(new RecyclerAdapter(MainActivity.this, corkList2, new RecyclerAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(Cork item) {

                                        Intent intent = new Intent(MainActivity.this, FullCorkActivity.class);
                                        intent.putExtra("name", (String) getResources().getText(item.getName()));
                                        intent.putExtra("img", item.getImage());
                                        intent.putExtra("link", (String) getResources().getText(item.getLink()));
                                        intent.putExtra("price", (String) getResources().getText(item.getPrice()));
                                        intent.putExtra("brand", (String) getResources().getText(item.getBrand()));
                                        startActivity(intent);
                                    }

                                }));

                            }
                        }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        sPrefSelection = getSharedPreferences("SharedPrefSelection", MODE_PRIVATE);
        SharedPreferences.Editor edPrefSelection = sPrefSelection.edit();
        edPrefSelection.putInt("save_selection", 0);
        edPrefSelection.commit();
    }
}
