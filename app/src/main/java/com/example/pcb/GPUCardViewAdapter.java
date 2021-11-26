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

public class GPUCardViewAdapter extends RecyclerView.Adapter<GPUCardViewAdapter.GPUCardViewViewHolder>{
    ArrayList<GPU> listGPU;
    public GPUCardViewAdapter(ArrayList<GPU> list) {
        this.listGPU = list;
    }
    public int[] gpuPhoto ={R.drawable.gpu_asusstrix1080ti, R.drawable.gpu_nvidia3080, R.drawable.gpu_evga1070, R.drawable.gpu_zotac1050ti, R.drawable.gpu_gigabyte1660super
            , R.drawable.gpu_asus1060dual, R.drawable.gpu_msirx570, R.drawable.gpu_asusrx580, R.drawable.gpu_msigt1030, R.drawable.asus_rtx2060};
    public String[] gpuLink = {"https://www.tokopedia.com/search?st=product&q=Asus%20Strix%201080%20Ti", "https://www.tokopedia.com/search?st=product&q=Nvidia%20RTX3080%20Founder%20Edition",
    "https://www.tokopedia.com/search?st=product&q=EVGA%20SC%20Gaming%20GTX%201070", "https://www.tokopedia.com/search?st=product&q=Zotac%201050Ti",
    "https://www.tokopedia.com/search?st=product&q=Gigabyte%20OC%201660%20super", "https://www.tokopedia.com/search?st=product&q=Asus%20dual%201060",
    "https://www.tokopedia.com/search?st=product&q=MSI%20Gaming%20X%20RX%20570", "https://www.tokopedia.com/search?st=product&q=Asus%20ROG%20Strix%20RX580",
    "https://www.tokopedia.com/search?st=product&q=MSI%20GT%201030%20LP%20OC", "https://www.tokopedia.com/search?st=product&q=Asus%20Dual%20Evo%20RTX%202060"};

    @NonNull
    @Override
    public GPUCardViewAdapter.GPUCardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_gpucomponent, viewGroup, false);
        return new GPUCardViewAdapter.GPUCardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GPUCardViewViewHolder holder, int position) {
//        List<CPU> listCPU = MainMenuActivity.CPUlist;

        GPU gpu = listGPU.get(position);
        Glide.with(holder.itemView.getContext())
                .load(gpuPhoto[position])
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
        holder.gpuName.setText(gpu.getName() + " " + gpu.getchip());
        holder.gpuMemory.setText(gpu.getMemory() + " MHz");
        holder.gpuPrice.setText("Rp. "+ gpu.getPrice());

        holder.linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(gpuLink[holder.getAdapterPosition()]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listGPU.size();
    }

    class GPUCardViewViewHolder extends RecyclerView.ViewHolder {
        CardView gpuCardView;
        ImageView imgPhoto;
        TextView gpuName, gpuMemory, gpuClock, gpuPrice;
        ImageButton linkButton;
        GPUCardViewViewHolder(View itemView) {
            super(itemView);
            gpuCardView = itemView.findViewById(R.id.gpu_cardview);
            imgPhoto = itemView.findViewById(R.id.img_gpu_recommendation);
            gpuName = itemView.findViewById(R.id.gpuName);
            gpuMemory = itemView.findViewById(R.id.gpuMemory);
            gpuClock = itemView.findViewById(R.id.gpuClock);
            gpuPrice = itemView.findViewById(R.id.gpuPrice);
            linkButton = itemView.findViewById(R.id.linkButton);
        }
    }

}

