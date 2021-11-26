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
    public int[] storagePhoto ={R.drawable.cpu_ryzen53600, R.drawable.cpu_ryzen73700x, R.drawable.cpu_ryzen93900x, R.drawable.cpu_corei710700k, R.drawable.cpu_ryzen93900xt
            , R.drawable.cpu_corei910850k, R.drawable.cpu_corei910900k, R.drawable.cpu_ryzen52600x, R.drawable.cpu_ryzen73800xt, R.drawable.cpu_corei911900k};
    public String[] storageLink = {"https://www.tokopedia.com/search?st=product&q=Ryzen%205%203600", "https://www.tokopedia.com/search?st=product&q=Ryzen%207%203700x",
            "https://www.tokopedia.com/search?st=product&q=Ryzen%209%203900x", "https://www.tokopedia.com/search?st=product&q=Intel%20i7%2010700k",
            "https://www.tokopedia.com/search?st=product&q=Ryzen%209%203900XT", "https://www.tokopedia.com/search?st=product&q=Intel%20i9%2010850k",
            "https://www.tokopedia.com/search?st=product&q=Intel%20i9%2010900k", "https://www.tokopedia.com/search?st=product&q=Ryzen%205%202600x",
            "https://www.tokopedia.com/search?st=product&q=Ryzen%207%203800xt", "https://www.tokopedia.com/search?st=product&q=Intel%20i9%2011900k"};

    @NonNull
    @Override
    public StorageCardViewAdapter.StorageCardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_storagecomponent, viewGroup, false);
        return new StorageCardViewAdapter.StorageCardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StorageCardViewAdapter.StorageCardViewViewHolder holder, int position) {
//        List<CPU> listCPU = MainMenuActivity.CPUlist;

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
