package com.example.pcb;

import java.util.ArrayList;
import java.util.List;

public class DummyData {
    private static String[] rekomendasiNames = {
            "Low Tier",
            "Mid Tier",
            "High Tier",
    };

    static List<Rekomendasi> getRekomendasiData(){
        ArrayList<Rekomendasi> list = new ArrayList<>();
        for (int i = 0; i < rekomendasiNames.length;i++){
            Rekomendasi rekomendasi = new Rekomendasi();
            rekomendasi.setName(rekomendasiNames[i]);
            rekomendasi.setPhoto(R.drawable.mid_tier);
            list.add(rekomendasi);
        }
        return list;
    }
}
