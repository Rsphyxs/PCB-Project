package com.example.pcb;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.net.Uri;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class CPUCardViewAdapter extends RecyclerView.Adapter<CPUCardViewAdapter.CPUCardViewViewHolder>{
    ArrayList<CPU> listCPU;
    public CPUCardViewAdapter(ArrayList<CPU> list) {
        this.listCPU = list;
    }
    public int[] cpuPhoto ={R.drawable.cpu_ryzen53600, R.drawable.cpu_ryzen73700x, R.drawable.cpu_ryzen93900x, R.drawable.cpu_corei710700k, R.drawable.cpu_ryzen93900xt
    , R.drawable.cpu_corei910850k, R.drawable.cpu_corei910900k, R.drawable.cpu_ryzen52600x, R.drawable.cpu_ryzen73800xt, R.drawable.cpu_corei911900k};

    @NonNull
    @Override
    public CPUCardViewAdapter.CPUCardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_cpucomponent, viewGroup, false);
        return new CPUCardViewAdapter.CPUCardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CPUCardViewViewHolder holder, int position) {
//        List<CPU> listCPU = MainMenuActivity.CPUlist;

        CPU cpu = listCPU.get(position);
        Glide.with(holder.itemView.getContext())
                .load(cpuPhoto[position])
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
        holder.cpuName.setText(cpu.getName());
        holder.cpuCores.setText(cpu.getCore() + " Cores");
        holder.cpuClock.setText(cpu.getClock() + " GHz");
        holder.cpuPrices.setText("Rp. "+ cpu.getPrice());

        holder.linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.tokopedia.com/search?st=product&q=AMD%20Ryzen%209%203900x");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listCPU.size();
    }

    class CPUCardViewViewHolder extends RecyclerView.ViewHolder {
        CardView cpuCardView;
        ImageView imgPhoto;
        TextView cpuName, cpuCores, cpuClock, cpuPrices;
        ImageButton linkButton;
        CPUCardViewViewHolder(View itemView) {
            super(itemView);
            cpuCardView = itemView.findViewById(R.id.cpu_cardview);
            imgPhoto = itemView.findViewById(R.id.img_cpu_recommendation);
            cpuName = itemView.findViewById(R.id.cpuName);
            cpuCores = itemView.findViewById(R.id.cpuCores);
            cpuClock = itemView.findViewById(R.id.cpuClock);
            cpuPrices = itemView.findViewById(R.id.cpuPrice);
            linkButton = itemView.findViewById(R.id.linkButton);
        }
    }

}
