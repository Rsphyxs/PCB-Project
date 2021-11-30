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

public class StorageCardViewAdapter extends RecyclerView.Adapter<StorageCardViewAdapter.StorageCardViewViewHolder>{
    ArrayList<Storage> listStorage;
    public StorageCardViewAdapter(ArrayList<Storage> list) {
        this.listStorage = list;
    }
    public int[] storagePhoto ={R.drawable.storage_samsung_970_evo, R.drawable.storage_samsung_860_evo, R.drawable.storage_samsung_980_evo, R.drawable.storage_crucial_mx300
    , R.drawable.storage_seagate_barracuda, R.drawable.storage_corsair_force_series_gt240, R.drawable.storage_crucial_mx500, R.drawable.storage_toshiba_p300, R.drawable.storage_seagate_firecuda_520};
    public String[] storageLink = {"https://www.tokopedia.com/search?st=product&q=samsung%20970%20evo",
            "https://www.tokopedia.com/search?st=product&q=samsung%20860%20evo",
            "https://www.tokopedia.com/search?st=product&q=samsung%20evo%20980&navsource=home",
            "https://www.tokopedia.com/search?q=crucial%20mx300&source=universe&srp_component_id=02.07.01.01&st=product",
            "https://www.tokopedia.com/search?st=product&q=seagate%20barracuda",
            "https://www.tokopedia.com/search?st=product&q=corsair%20force%20series%20gt240",
            "https://www.tokopedia.com/search?q=crucial%20mx500&source=universe&srp_component_id=02.02.01.01&st=product",
            "https://www.tokopedia.com/search?st=product&q=Toshiba%20p300&navsource=home",
            "https://www.tokopedia.com/search?st=product&q=seagate%20firecuda%20520"};

    @NonNull
    @Override
    public StorageCardViewAdapter.StorageCardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_storagecomponent, viewGroup, false);
        return new StorageCardViewAdapter.StorageCardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StorageCardViewAdapter.StorageCardViewViewHolder holder, int position) {

        Storage storage = listStorage.get(position);
        Glide.with(holder.itemView.getContext())
                .load(storagePhoto[position])
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
        holder.storageName.setText(storage.getName());
        holder.storageSize.setText(storage.getSize() + " GB");
        holder.storageTipe.setText(storage.getTipe());
        holder.storagePrice.setText("Rp. "+ storage.getPrice());

        holder.linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(storageLink[holder.getAdapterPosition()]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listStorage.size();
    }

    class StorageCardViewViewHolder extends RecyclerView.ViewHolder {
        CardView storageCardView;
        ImageView imgPhoto;
        TextView storageName, storageSize, storageTipe, storagePrice;
        ImageButton linkButton;
        StorageCardViewViewHolder(View itemView) {
            super(itemView);
            storageCardView = itemView.findViewById(R.id.storage_cardview);
            imgPhoto = itemView.findViewById(R.id.img_storage_recommendation);
            storageName = itemView.findViewById(R.id.storageName);
            storageSize = itemView.findViewById(R.id.storageSize);
            storageTipe = itemView.findViewById(R.id.storageTipe);
            storagePrice = itemView.findViewById(R.id.storagePrice);
            linkButton = itemView.findViewById(R.id.linkButton);
        }
    }
}
