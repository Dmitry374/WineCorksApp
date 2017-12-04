package com.example.dima.winecorksapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dima on 22.10.2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RatesViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Cork item);
    }

    private final OnItemClickListener listener;
    List<Cork> corks;
    Context context;

    public RecyclerAdapter(Context context, List<Cork> rates, OnItemClickListener listener){
        this.context = context;
        this.corks = rates;
        this.listener = listener;
    }

    @Override
    public RatesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view, viewGroup, false);
        return new RatesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RatesViewHolder personViewHolder, int i) {

        personViewHolder.tvPrice.setText(corks.get(i).getPrice());
        personViewHolder.tvTitleRecycle.setText(corks.get(i).getName());

        Picasso
                .with(context)
                .load(corks.get(i).getImage())
//                .load(context.getResources().getInteger(list.get(position).getLink()))
//                .fit() // will explain later
                .into(personViewHolder.imgCork);

        personViewHolder.bind(corks.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return corks.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    static class RatesViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView tvTitleRecycle;
        ImageView imgCork;
        TextView tvPrice;
        RatesViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view_item_recycler_view);
            tvTitleRecycle = (TextView) itemView.findViewById(R.id.tvTitleRecycle);
            imgCork = (ImageView) itemView.findViewById(R.id.img_cork_recycle);
            tvPrice = (TextView)itemView.findViewById(R.id.tvPriceRecycle);
        }

        public void bind(final Cork corks, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(corks);
                }
            });
        }

    }

}
