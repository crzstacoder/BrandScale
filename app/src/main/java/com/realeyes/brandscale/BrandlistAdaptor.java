package com.realeyes.brandscale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BrandlistAdaptor extends RecyclerView.Adapter<BrandlistAdaptor.Brandlistviewholder> {

    private ArrayList<Brandinfo> arrayList;
    private Context context;

    public BrandlistAdaptor(ArrayList<Brandinfo> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Brandlistviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_brandlist, parent, false);
        Brandlistviewholder holder = new Brandlistviewholder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Brandlistviewholder holder, int position) {

        Glide.with(holder.itemView)
                .load(arrayList.get(position).getBrandlogo())
                .into(holder.Brandlogo);
        holder.Brandname.setText(arrayList.get(position).getBrandname());

    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);     //if문과 비슷 '삼항연산자' 자바 공부하기
    }

    public class Brandlistviewholder extends RecyclerView.ViewHolder {

        ImageView Brandlogo;
        TextView Brandname;


        public Brandlistviewholder(@NonNull View itemView) {
            super(itemView);
            this.Brandlogo = itemView.findViewById(R.id.Brandlogo);
            this.Brandname = itemView.findViewById(R.id.Brnadname);
        }
    }
}
