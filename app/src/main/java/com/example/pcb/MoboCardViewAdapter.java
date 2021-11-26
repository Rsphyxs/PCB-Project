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
    public int[] moboPhoto ={R.drawable.gpu_asusstrix1080ti, R.drawable.gpu_nvidia3080, R.drawable.gpu_evga1070, R.drawable.gpu_zotac1050ti, R.drawable.gpu_gigabyte1660super
            , R.drawable.gpu_asus1060dual, R.drawable.gpu_msirx570, R.drawable.gpu_asusrx580, R.drawable.gpu_msigt1030, R.drawable.asus_rtx2060};
    public String[] moboLink = {"https://www.tokopedia.com/search?st=product&q=Asus%20Strix%201080%20Ti", "https://www.tokopedia.com/search?st=product&q=Nvidia%20RTX3080%20Founder%20Edition",
            "https://www.tokopedia.com/search?st=product&q=EVGA%20SC%20Gaming%20GTX%201070", "https://www.tokopedia.com/search?st=product&q=Zotac%201050Ti",
            "https://www.tokopedia.com/search?st=product&q=Gigabyte%20OC%201660%20super", "https://www.tokopedia.com/search?st=product&q=Asus%20dual%201060",
            "https://www.tokopedia.com/search?st=product&q=MSI%20Gaming%20X%20RX%20570", "https://www.tokopedia.com/search?st=product&q=Asus%20ROG%20Strix%20RX580",
            "https://www.tokopedia.com/search?st=product&q=MSI%20GT%201030%20LP%20OC", "https://www.tokopedia.com/search?st=product&q=Asus%20Dual%20Evo%20RTX%202060"};

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

