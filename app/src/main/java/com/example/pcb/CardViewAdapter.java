package com.example.pcb;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewViewHolder>{
    private ArrayList<Rekomendasi> listRekomendasi;

    public String[] Desc = {"This PC is capable of running multiple light application such as office, multimedia, and design application and also capable of running games in low to mid setting",
        "This PC is capable of running office application and also process design and editing application in high process rate. This PC also capable of running AAA games with mid to high setting",
        "This PC is capable of running anything that the previous one cannot in the speed that human cant comprehend, and also you dont have to worry about your FPS anymore"};

    public CardViewAdapter(ArrayList<Rekomendasi> list) {
        this.listRekomendasi = list;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_recommendation, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {

        List<String> cpuName = MainMenuActivity.cpuName;
        List<String> moboName = MainMenuActivity.moboName;
        List<String> gpuName = MainMenuActivity.gpuName;
        List<String> ramName = MainMenuActivity.ramName;
        List<String> storageName = MainMenuActivity.storageName;
        List<String> psuName = MainMenuActivity.psuName;
        List<String> caseName = MainMenuActivity.caseName;
        List<String> fanName = MainMenuActivity.fanName;
        Rekomendasi rekomendasi = listRekomendasi.get(position);
        long price = MainMenuActivity.CPUlist.get(rekomendasi.getId_cpu()).getPrice() + MainMenuActivity.Mobolist.get(rekomendasi.getId_mobo()).getPrice()
                + MainMenuActivity.GPUlist.get(rekomendasi.getId_gpu()).getPrice() + MainMenuActivity.Mobolist.get(rekomendasi.getId_mobo()).getPrice()
                + MainMenuActivity.RAMlist.get(rekomendasi.getId_ram()).getPrice() + MainMenuActivity.PSUlist.get(rekomendasi.getId_psu()).getPrice()
                + MainMenuActivity.Caselist.get(rekomendasi.getId_case()).getPrice() + MainMenuActivity.Fanlist.get(rekomendasi.getId_fan()).getPrice();
        Glide.with(holder.itemView.getContext())
                .load(rekomendasi.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
        holder.recommendationName.setText(rekomendasi.getNama());
        holder.description.setText(Desc[position]);
        holder.cpuName.setText(cpuName.get(rekomendasi.getId_cpu()));
        holder.gpuName.setText(gpuName.get(rekomendasi.getId_gpu()));
        holder.moboName.setText(moboName.get(rekomendasi.getId_mobo()));
        holder.ramName.setText(ramName.get(rekomendasi.getId_ram()));
        holder.storageName.setText(storageName.get(rekomendasi.getId_storage()));
        holder.psuName.setText(psuName.get(rekomendasi.getId_psu()));
        holder.caseName.setText(caseName.get(rekomendasi.getId_case()));
        holder.fanName.setText(fanName.get(rekomendasi.getId_fan()));
        holder.finalPrice.setText("Rp. " + price);

        holder.expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.expandableSpecification.getVisibility() == View.GONE){
                    holder.expandButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    TransitionManager.beginDelayedTransition(holder.recommendationCardView, new AutoTransition());
                    holder.expandableSpecification.setVisibility(View.VISIBLE);
                }
                else{
                    holder.expandButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    TransitionManager.beginDelayedTransition(holder.recommendationCardView, new AutoTransition());
                    holder.expandableSpecification.setVisibility(View.GONE);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listRekomendasi.size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder {
        CardView recommendationCardView;
        LinearLayout expandableSpecification;
        ImageView imgPhoto;
        TextView recommendationName, description, cpuName, gpuName, moboName, ramName, storageName, psuName, caseName, fanName, finalPrice;
        ImageButton expandButton;
        CardViewViewHolder(View itemView) {
            super(itemView);
            recommendationCardView = itemView.findViewById(R.id.recommendation_cardview);
            description = itemView.findViewById(R.id.description);
            expandableSpecification = itemView.findViewById(R.id.expand_specification);
            imgPhoto = itemView.findViewById(R.id.img_pc_recommendation);
            recommendationName = itemView.findViewById(R.id.recommendation_name);
            cpuName = itemView.findViewById(R.id.cpu_name);
            gpuName = itemView.findViewById(R.id.gpu_name);
            moboName = itemView.findViewById(R.id.mobo_name);
            ramName = itemView.findViewById(R.id.ram_name);
            storageName = itemView.findViewById(R.id.storage_name);
            psuName = itemView.findViewById(R.id.psu_name);
            caseName = itemView.findViewById(R.id.case_name);
            fanName = itemView.findViewById(R.id.fan_name);
            finalPrice = itemView.findViewById(R.id.finalPrice);
            expandButton = itemView.findViewById(R.id.expand_button);
        }
    }
}
