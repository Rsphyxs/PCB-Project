package com.example.pcb;

public class Case {
    private int id_case;
    private String name;
    private String[] ff_tipe;
    private String brand;
    private int price;

    public Case (int id_case, String name, String[] ff_tipe, String brand, int price)
    {
        this.id_case=id_case;
        this.name=name;
        this.ff_tipe=ff_tipe;
        this.brand = brand;
        this.price = price;
    }

    public int getid_case()
    {
        return id_case;
    }

    public String getName() {
        return name;
    }

    public String[] getFf_tipe() {
        return ff_tipe;
    }

    public String getbrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }

    public void setid_case(int id_case) {
        this.id_case = id_case;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFf_tipe(String[] ff_tipe) {
        this.ff_tipe = ff_tipe;
    }

    public void setbrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString(){
        return this.getName();
    }
}
