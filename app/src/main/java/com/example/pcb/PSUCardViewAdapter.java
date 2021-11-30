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

    public int[] psuPhoto = {R.drawable.psu_corsairrm, R.drawable.psu_evgabr, R.drawable.psu_corsairhxplatinum, R.drawable.psu_seasonicfocusgoldplus, R.drawable.psu_silverstonesfx
            , R.drawable.psu_corsairsf, R.drawable.psu_corsaircx, R.drawable.psu_seasonicfocus, R.drawable.psu_seasonicfocussgx, R.drawable.psu_evgap2};
    public String[] psuLink = {"https://www.tokopedia.com/search?st=product&q=Corsair%20RM&navsource=home", "https://www.tokopedia.com/search?st=product&q=EVGA%20BR&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=Corsair%20HX%20platinum&navsource=home", "https://www.tokopedia.com/search?st=product&q=Seasonic%20FOCUS%20Plus%20Gold&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=Silverstone%20SFX&navsource=home", "https://www.tokopedia.com/search?st=product&q=Corsair%20sf&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=Corsair%20cx&navsource=home", "https://www.tokopedia.com/search?st=product&q=Seasonic%20FOCUS&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=Seasonic%20FOCUS%20sgx&navsource=home", "https://www.tokopedia.com/search?st=product&q=EVGA%20p2&navsource=home"};

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
