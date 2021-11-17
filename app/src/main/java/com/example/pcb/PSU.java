package com.example.pcb;

public class PSU {
    private int id_psu;
    private String name;
    private int watt;

    private String form_factor;
    private int price;

    public PSU (int id_psu, String name, int watt, String formFactor, int price)
    {
        this.id_psu=id_psu;
        this.name=name;
        this.watt = watt;
        this.form_factor = formFactor;
        this.price = price;
    }

    public int getid_psu()
    {
        return id_psu;
    }

    public String getName() {
        return name;
    }

    public int getwatt() {
        return watt;
    }

    public int getPrice() {
        return price;
    }

    public String getForm_factor() {
        return form_factor;
    }

    public void setForm_factor(String form_factor) {
        this.form_factor = form_factor;
    }

    public void setid_psu(int id_psu) {
        this.id_psu = id_psu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setwatt(int watt) {
        this.watt = watt;
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public String toString(){
        return this.getName();
    }
}
