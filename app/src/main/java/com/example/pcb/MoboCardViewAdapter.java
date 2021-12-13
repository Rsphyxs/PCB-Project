package com.example.pcb;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MoboCardViewAdapter extends RecyclerView.Adapter<MoboCardViewAdapter.MoboCardViewViewHolder>{
    ArrayList<Motherboard> listMobo;

    public MoboCardViewAdapter(ArrayList<Motherboard> list) {
        this.listMobo = list;
    }
    public int[] moboPhoto ={R.drawable.mobo_msib450tomahawkmax, R.drawable.mobo_asusrogstrixb550fgaming, R.drawable.mobo_gigabyteb460ds3h, R.drawable.mobo_asussabertoothz87, R.drawable.mobo_asusprimex570pro
            , R.drawable.mobo_asrockb450hdv, R.drawable.mobo_gigabytez490viciong, R.drawable.mobo_asusmaximusvigene, R.drawable.mobo_gigabytex570aoruselite, R.drawable.mobo_msib550apro};
    public String[] moboLink =  {"https://www.tokopedia.com/search?st=product&q=MSI%20B450%20Tomahawk%20MAX&navsource=home", "https://www.tokopedia.com/search?st=product&q=Asus%20ROG%20STRIX%20B550-F%20Gaming&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=Gigabyte%20B460M%20DS3H&navsource=home", "https://www.tokopedia.com/search?st=product&q=Asus%20Sabertooth%20Z87&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=Asus%20PRIME%20X570-PRO&navsource=home", "https://www.tokopedia.com/search?st=product&q=Asrock%20B450M-HDV&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=Gigabyte%20Z490%20Vision%20G&navsource=home", "https://www.tokopedia.com/search?st=product&q=Asus%20Maximus%20VI%20Gene&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=Gigabyte%20X570%20AORUS%20Elite&navsource=home", "https://www.tokopedia.com/search?st=product&q=MSI%20B550-A%20PRO&navsource=home"};

    @NonNull
    @Override
    public MoboCardViewAdapter.MoboCardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_view_mobocomponent, viewGroup, false);
        return new MoboCardViewAdapter.MoboCardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoboCardViewViewHolder holder, int position) {

        Motherboard motherboard = listMobo.get(position);
        Glide.with(holder.itemView.getContext())
                .load(moboPhoto[position])
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
        holder.moboName.setText(motherboard.getName());
        holder.moboChipset.setText(motherboard.getChipset());
        holder.moboFormFactor.setText(motherboard.getForm_factor());
        holder.moboPrice.setText("Rp. " + motherboard.getPrice());
        holder.linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(moboLink[holder.getAdapterPosition()]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listMobo.size();
    }

    class MoboCardViewViewHolder extends RecyclerView.ViewHolder {
        CardView moboCardView;
        ImageView imgPhoto;
        TextView moboName, moboChipset, moboFormFactor, moboPrice;
        ImageButton linkButton;
        MoboCardViewViewHolder(View itemView) {
            super(itemView);
            moboCardView = itemView.findViewById(R.id.mobo_cardview);
            imgPhoto = itemView.findViewById(R.id.img_mobo_recommendation);
            moboName = itemView.findViewById(R.id.moboName);
            moboChipset = itemView.findViewById(R.id.moboChipset);
            moboFormFactor = itemView.findViewById(R.id.moboFormFactor);
            moboPrice = itemView.findViewById(R.id.moboPrice);
            linkButton = itemView.findViewById(R.id.linkButton);
        }
    }

}

