package com.example.dima.winecorksapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Dima on 25.11.2017.
 */

public class GridAdapter extends ArrayAdapter<Cork> {

    Context context;
    ArrayList<Cork> list;
    LayoutInflater layoutInflater;

    public GridAdapter(Context context, int resource, ArrayList<Cork> list) {
        super(context, resource);

        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Cork getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.item_grid, parent, false);
        }

        final CardView cardView = (CardView) convertView.findViewById(R.id.card_main);

//        ImageView imageView = (ImageView) convertView.findViewById(R.id.img_cork);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        final LinearLayout linLayout1 = (LinearLayout) convertView.findViewById(R.id.linLayout1);

//        imageView.setImageResource(list.get(position).getImage());
//        tvTitle.setText(list.get(position).getName());
        tvTitle.setText(list.get(position).getPrice());

        Picasso
                .with(context)
                .load(list.get(position).getImage())
//                .load(context.getResources().getInteger(list.get(position).getLink()))
//                .fit() // will explain later
                .into((ImageView) convertView.findViewById(R.id.img_cork));



        return convertView;
    }
}
