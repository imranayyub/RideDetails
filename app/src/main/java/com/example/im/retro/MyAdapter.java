package com.example.im.retro;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Im on 21-11-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    //this context we will use to inflate the layout
     Context mCtx;

    //we are storing all the products in a list
    private List<RideHistory> rideHistory;

    //getting the context and product list with constructor
    public MyAdapter(Context mCtx, List<RideHistory> rideHistory) {
        this.mCtx =  mCtx;
        this.rideHistory = rideHistory;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cardview, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        //getting the product of the specified position
        RideHistory product = rideHistory.get(position);

        //binding the data with the viewholder views
        holder.name.setText("Driver :"+product.getDriver_name());
        holder.bookingId.setText("Order Id:"+product.getBooking_id());

        Glide.with(mCtx.getApplicationContext()).load(product.getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return rideHistory.size();
    }


    public static class MyHolder extends RecyclerView.ViewHolder {

        TextView name, date, bookingId;
        ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            date = (TextView) itemView.findViewById(R.id.date);
            bookingId = (TextView) itemView.findViewById(R.id.bookingId);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }


    }
}
