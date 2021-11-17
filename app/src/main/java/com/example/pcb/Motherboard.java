package com.example.pcb;

public class Motherboard {

    private int id_mobo;
    private String name;
    private String chipset;
    private String form_factor;
    private int ram_slot;
    private String brand;
    private long price;

    public Motherboard(int id_mobo, String name, String chipset, String form_factor, int ram_slot, String brand, long price){
        this.id_mobo = id_mobo;
        this.name = name;
        this.chipset = chipset;
        this.form_factor = form_factor;
        this.ram_slot = ram_slot;
        this.brand = brand;
        this.price = price;
    }

    public int getId_mobo() {
        return id_mobo;
    }

    public void setId_mobo(int id_mobo) {
        this.id_mobo = id_mobo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getForm_factor() {
        return form_factor;
    }

    public void setForm_factor(String form_factor) {
        this.form_factor = form_factor;
    }

    public int getRam_slot() {
        return ram_slot;
    }

    public void setRam_slot(int ram_slot) {
        this.ram_slot = ram_slot;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
