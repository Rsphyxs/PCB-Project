package com.example.pcb;

public class Rekomendasi {
    private int id;
    private String nama;
    private int id_cpu;
    private int id_gpu;
    private int id_mobo;
    private int id_ram;
    private int id_storage;
    private int id_psu;
    private  int id_case;
    private int id_fan;
    private int photo;

    public Rekomendasi(int id, String nama, int id_cpu, int id_gpu, int id_mobo, int id_ram, int id_storage, int id_psu, int id_case, int id_fan){
        this.id = id;
        this.nama = nama;
        this.id_cpu = id_cpu;
        this.id_gpu = id_gpu;
        this.id_mobo = id_mobo;
        this.id_ram = id_ram;
        this.id_storage = id_storage;
        this.id_psu = id_psu;
        this.id_case = id_case;
        this.id_fan = id_fan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setName(String nama) {
        this.nama = nama;
    }

    public int getId_cpu() {
        return id_cpu;
    }

    public void setId_cpu(int id_cpu) {
        this.id_cpu = id_cpu;
    }

    public int getId_gpu() {
        return id_gpu;
    }

    public void setId_gpu(int id_gpu) {
        this.id_gpu = id_gpu;
    }

    public int getId_mobo() {
        return id_mobo;
    }

    public void setId_mobo(int id_mobo) {
        this.id_mobo = id_mobo;
    }

    public int getId_ram() {
        return id_ram;
    }

    public void setId_ram(int id_ram) {
        this.id_ram = id_ram;
    }

    public int getId_storage() {
        return id_storage;
    }

    public void setId_storage(int id_storage) {
        this.id_storage = id_storage;
    }

    public int getId_psu() {
        return id_psu;
    }

    public void setId_psu(int id_psu) {
        this.id_psu = id_psu;
    }

    public int getId_case() {
        return id_case;
    }

    public void setId_case(int id_case) {
        this.id_case = id_case;
    }

    public int getId_fan() {
        return id_fan;
    }

    public void setId_fan(int id_fan) {
        this.id_fan = id_fan;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
