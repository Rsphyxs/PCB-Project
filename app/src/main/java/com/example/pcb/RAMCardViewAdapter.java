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
    public int[] ramPhoto ={R.drawable.gpu_asusstrix1080ti, R.drawable.gpu_nvidia3080, R.drawable.gpu_evga1070, R.drawable.gpu_zotac1050ti, R.drawable.gpu_gigabyte1660super
            , R.drawable.gpu_asus1060dual, R.drawable.gpu_msirx570, R.drawable.gpu_asusrx580, R.drawable.gpu_msigt1030, R.drawable.asus_rtx2060};
    public String[] gpuLink = {"https://www.tokopedia.com/search?st=product&q=Asus%20Strix%201080%20Ti", "https://www.tokopedia.com/search?st=product&q=Nvidia%20RTX3080%20Founder%20Edition",
            "https://www.tokopedia.com/search?st=product&q=EVGA%20SC%20Gaming%20GTX%201070", "https://www.tokopedia.com/search?st=product&q=Zotac%201050Ti",
            "https://www.tokopedia.com/search?st=product&q=Gigabyte%20OC%201660%20super", "https://www.tokopedia.com/search?st=product&q=Asus%20dual%201060",
            "https://www.tokopedia.com/search?st=product&q=MSI%20Gaming%20X%20RX%20570", "https://www.tokopedia.com/search?st=product&q=Asus%20ROG%20Strix%20RX580",
            "https://www.tokopedia.com/search?st=product&q=MSI%20GT%201030%20LP%20OC", "https://www.tokopedia.com/search?st=product&q=Asus%20Dual%20Evo%20RTX%202060"};

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
                Uri uri = Uri.parse(gpuLink[holder.getAdapterPosition()]);
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
