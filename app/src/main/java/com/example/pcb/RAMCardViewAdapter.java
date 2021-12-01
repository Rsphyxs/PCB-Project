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

import java.text.BreakIterator;
import java.util.ArrayList;

public class RAMCardViewAdapter extends RecyclerView.Adapter<RAMCardViewAdapter.RAMCardViewViewHolder>{
    ArrayList<RAM> listRAM;
    public RAMCardViewAdapter(ArrayList<RAM> list) {
        this.listRAM = list;
    }
    public int[] ramPhoto ={R.drawable.ram_corsairvengeancelpx, R.drawable.ram_corsairvengeancergbpro, R.drawable.ram_gskilltridentz, R.drawable.ram_crucialballistix, R.drawable.ram_kingstonhyperxfury
            , R.drawable.ram_teameliteplus, R.drawable.ram_gskilltridentzneo, R.drawable.ram_vgentsunami, R.drawable.ram_teamgrouptcreateclassic, R.drawable.ram_gskillripjaws};
    public String[] ramLink =  {"https://www.tokopedia.com/search?st=product&q=corsair%20vengeance%20lpx&navsource=home", "https://www.tokopedia.com/search?st=product&q=corsair%20vengeance%20rgb%20pro&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=G.Skill%20Trident%20Z&navsource=home", "https://www.tokopedia.com/search?st=product&q=Crucial%20Ballistix%20RGB&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=Kingston%20HyperX%20Fury&navsource=home", "https://www.tokopedia.com/search?st=product&q=team%20elite%20plus&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=g.skill%20tridentz%20neo&navsource=home", "https://www.tokopedia.com/search?st=product&q=v-gen%20tsunami&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=teamgroup%20t-create&navsource=home", "https://www.tokopedia.com/search?st=product&q=gskill%20ripjaws&navsource=home"};

    @NonNull
    @Override
    public RAMCardViewAdapter.RAMCardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_ramcomponent, viewGroup, false);
        return new RAMCardViewAdapter.RAMCardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RAMCardViewAdapter.RAMCardViewViewHolder holder, int position) {
//        List<CPU> listCPU = MainMenuActivity.CPUlist;

        RAM ram = listRAM.get(position);
        Glide.with(holder.itemView.getContext())
                .load(ramPhoto[position])
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
        holder.ramName.setText(ram.getName());
        holder.ramSpeed.setText(ram.getSpeed() + "MHz");
        holder.ramModules.setText(ram.getModules());
        holder.ramPrice.setText("Rp. "+ ram.getPrice());

        holder.linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(ramLink[holder.getAdapterPosition()]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRAM.size();
    }

    class RAMCardViewViewHolder extends RecyclerView.ViewHolder {
        CardView ramCardView;
        ImageView imgPhoto;
        TextView ramName, ramSpeed, ramModules, ramPrice;
        ImageButton linkButton;
        RAMCardViewViewHolder(View itemView) {
            super(itemView);
            ramCardView = itemView.findViewById(R.id.ram_cardview);
            imgPhoto = itemView.findViewById(R.id.img_ram_recommendation);
            ramName = itemView.findViewById(R.id.ramName);
            ramSpeed = itemView.findViewById(R.id.ramSpeed);
            ramModules = itemView.findViewById(R.id.ramModules);
            ramPrice = itemView.findViewById(R.id.ramPrice);
            linkButton = itemView.findViewById(R.id.linkButton);
        }
    }
}
