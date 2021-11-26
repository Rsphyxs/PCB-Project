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

public class PSUCardViewAdapter extends RecyclerView.Adapter<PSUCardViewAdapter.PSUCardViewViewHolder> {
    ArrayList<PSU> listPSU;

    public PSUCardViewAdapter(ArrayList<PSU> list) {
        this.listPSU = list;
    }

    public int[] psuPhoto = {R.drawable.gpu_asusstrix1080ti, R.drawable.gpu_nvidia3080, R.drawable.gpu_evga1070, R.drawable.gpu_zotac1050ti, R.drawable.gpu_gigabyte1660super
            , R.drawable.gpu_asus1060dual, R.drawable.gpu_msirx570, R.drawable.gpu_asusrx580, R.drawable.gpu_msigt1030, R.drawable.asus_rtx2060};
    public String[] psuLink = {"https://www.tokopedia.com/search?st=product&q=Asus%20Strix%201080%20Ti", "https://www.tokopedia.com/search?st=product&q=Nvidia%20RTX3080%20Founder%20Edition",
            "https://www.tokopedia.com/search?st=product&q=EVGA%20SC%20Gaming%20GTX%201070", "https://www.tokopedia.com/search?st=product&q=Zotac%201050Ti",
            "https://www.tokopedia.com/search?st=product&q=Gigabyte%20OC%201660%20super", "https://www.tokopedia.com/search?st=product&q=Asus%20dual%201060",
            "https://www.tokopedia.com/search?st=product&q=MSI%20Gaming%20X%20RX%20570", "https://www.tokopedia.com/search?st=product&q=Asus%20ROG%20Strix%20RX580",
            "https://www.tokopedia.com/search?st=product&q=MSI%20GT%201030%20LP%20OC", "https://www.tokopedia.com/search?st=product&q=Asus%20Dual%20Evo%20RTX%202060"};

    @NonNull
    @Override
    public PSUCardViewAdapter.PSUCardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_psucomponent, viewGroup, false);
        return new PSUCardViewAdapter.PSUCardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PSUCardViewAdapter.PSUCardViewViewHolder holder, int position) {
//        List<CPU> listCPU = MainMenuActivity.CPUlist;

        PSU psu = listPSU.get(position);
        Glide.with(holder.itemView.getContext())
                .load(psuPhoto[position])
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
        holder.psuName.setText(psu.getName() + " " + psu.getwatt());
        holder.psuFormFactor.setText(psu.getForm_factor());
        holder.psuWatt.setText(psu.getwatt() + "Watt");
        holder.psuPrice.setText("Rp. " + psu.getPrice());

        holder.linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(psuLink[holder.getAdapterPosition()]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPSU.size();
    }

    class PSUCardViewViewHolder extends RecyclerView.ViewHolder {
        CardView psuCardView;
        ImageView imgPhoto;
        TextView psuName, psuFormFactor, psuWatt, psuPrice;
        ImageButton linkButton;

        PSUCardViewViewHolder(View itemView) {
            super(itemView);
            psuCardView = itemView.findViewById(R.id.psu_cardview);
            imgPhoto = itemView.findViewById(R.id.img_psu_recommendation);
            psuName = itemView.findViewById(R.id.psuName);
            psuFormFactor = itemView.findViewById(R.id.psuFormFactor);
            psuWatt = itemView.findViewById(R.id.psuWatt);
            psuPrice = itemView.findViewById(R.id.psuPrice);
            linkButton = itemView.findViewById(R.id.linkButton);
        }
    }
}
