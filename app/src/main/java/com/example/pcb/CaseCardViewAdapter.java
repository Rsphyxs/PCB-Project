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

public class CaseCardViewAdapter extends RecyclerView.Adapter<CaseCardViewAdapter.CaseCardViewViewHolder>{
    ArrayList<Case> listCase;

    public CaseCardViewAdapter(ArrayList<Case> list) {
        this.listCase = list;
    }
    public int[] casePhoto ={R.drawable.casing_corsair4000d, R.drawable.casing_lianlilancool205, R.drawable.casing_corsair4000x, R.drawable.casing_corsair465x,
            R.drawable.casing_nzxth510, R.drawable.casing_metallicgearneo, R.drawable.casing_cubegamingkellva, R.drawable.casing_corsaircarbide275r,
            R.drawable.casing_msimpgsekira100r, R.drawable.casing_primex};
    public String[] caseLink = {"https://www.tokopedia.com/search?st=product&q=corsair%204000d&navsource=home", "https://www.tokopedia.com/search?st=product&q=lian%20li%20lancool%20205&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=corsair%204000x&navsource=home", "https://www.tokopedia.com/search?st=product&q=corsair%20465x&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=nzxt%20h510&navsource=home", "https://www.tokopedia.com/search?st=product&q=metallic%20gear%20neo&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=cube%20gaming%20kellva&navsource=home", "https://www.tokopedia.com/search?st=product&q=corsair%20carbide%20275r&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=msi%20mpg%20sekira%20100r&navsource=home", "https://www.tokopedia.com/search?st=product&q=prime%20x%20case&navsource=home"};

    @NonNull
    @Override
    public CaseCardViewAdapter.CaseCardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_casecomponent, viewGroup, false);
        return new CaseCardViewAdapter.CaseCardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaseCardViewViewHolder holder, int position) {

        Case aCase = listCase.get(position);
        Glide.with(holder.itemView.getContext())
                .load(casePhoto[position])
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
        holder.caseName.setText(aCase.getName());
        String aCaseArray[] = aCase.getFf_tipe();
        String caseFftipe = "";
        for(int i = 0; i < aCaseArray.length; i++){
            caseFftipe += (aCaseArray[i] + " ");
        }
        holder.caseFormFactor.setText(caseFftipe);
        holder.casePrice.setText("Rp. " + aCase.getPrice());
        holder.linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(caseLink[holder.getAdapterPosition()]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listCase.size();
    }

    class CaseCardViewViewHolder extends RecyclerView.ViewHolder {
        CardView caseCardView;
        ImageView imgPhoto;
        TextView caseName, caseFormFactor, casePrice;
        ImageButton linkButton;
        CaseCardViewViewHolder(View itemView) {
            super(itemView);
            caseCardView = itemView.findViewById(R.id.case_cardview);
            imgPhoto = itemView.findViewById(R.id.img_case_recommendation);
            caseName = itemView.findViewById(R.id.caseName);
            caseFormFactor = itemView.findViewById(R.id.caseFormFactor);
            casePrice = itemView.findViewById(R.id.casePrice);
            linkButton = itemView.findViewById(R.id.linkButton);
        }
    }

}

