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

public class FanCardViewAdapter extends RecyclerView.Adapter<FanCardViewAdapter.FanCardViewViewHolder> {
    ArrayList<Fan> listFan;

    public FanCardViewAdapter(ArrayList<Fan> list) {
        this.listFan = list;
    }
    public int[] fanPhoto ={R.drawable.fan_coolermasterhyper212evo, R.drawable.fan_thermaltakeux100, R.drawable.fan_scythefuma2, R.drawable.fan_arcticfreezer7x, R.drawable.fan_alphacooleisbaeraurora420
            , R.drawable.fan_aerocoolcyclon4, R.drawable.fan_msicorefrozrl, R.drawable.fan_phanteksph_tc12ls_bk, R.drawable.fan_coolermastrehypert4 , R.drawable.fan_rosewillpb120 };
    public String[] fanLink = {"https://www.amazon.com/s?k=Cooler+Master+Hyper+212+EVO&ref=nb_sb_noss_2", "https://www.amazon.com/s?k=Thermaltake+UX100&ref=nb_sb_noss_2",
            "https://www.amazon.com/s?k=Scythe+FUMA+2&ref=nb_sb_noss_2", "https://www.amazon.com/s?k=RCTIC+Freezer+7+X&ref=nb_sb_noss",
            "https://www.amazon.com/s?k=Alphacool+Eisbaer+Aurora+420&ref=nb_sb_noss_2", "https://www.amazon.com/s?k=Aerocool+Cyclon+4&ref=nb_sb_noss_2",
            "https://www.amazon.com/s?k=MSI+CORE+FROZR+L&ref=nb_sb_noss_2", "https://www.amazon.com/s?k=Phanteks+PH-TC12LS_BK&ref=nb_sb_noss_2",
            "https://www.amazon.com/s?k=Cooler+Master+Hyper+T4&ref=nb_sb_noss_2", "https://www.amazon.com/s?k=Rosewill+PB120&ref=nb_sb_noss_2"};

    @NonNull
    @Override
    public FanCardViewAdapter.FanCardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_fancomponent, viewGroup, false);
        return new FanCardViewAdapter.FanCardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FanCardViewAdapter.FanCardViewViewHolder holder, int position) {
//        List<CPU> listCPU = MainMenuActivity.CPUlist;

        Fan fan = listFan.get(position);
        Glide.with(holder.itemView.getContext())
                .load(fanPhoto[position])
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
        holder.fanName.setText(fan.getName());
        holder.fanRPM.setText(fan.getrpm() + "RPM");
        holder.fanPrice.setText("Rp. "+ fan.getPrice());

        holder.linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(fanLink[holder.getAdapterPosition()]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFan.size();
    }

    class FanCardViewViewHolder extends RecyclerView.ViewHolder {
        CardView fanCardView;
        ImageView imgPhoto;
        TextView fanName, fanRPM, fanPrice;
        ImageButton linkButton;
        FanCardViewViewHolder(View itemView) {
            super(itemView);
            fanCardView = itemView.findViewById(R.id.fan_cardview);
            imgPhoto = itemView.findViewById(R.id.img_fan_recommendation);
            fanName = itemView.findViewById(R.id.fanName);
            fanRPM = itemView.findViewById(R.id.fanRPM);
            fanPrice = itemView.findViewById(R.id.fanPrice);
            linkButton = itemView.findViewById(R.id.linkButton);
        }
    }
}
