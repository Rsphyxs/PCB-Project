package com.example.pcb;

public class Storage {
    private int id_storage;
    private String name;
    private int size;
    private String tipe;
    private int price;

    public Storage (int id_storage, String name, int size, String tipe, int price)
    {
        this.id_storage=id_storage;
        this.name=name;
        this.size = size;
        this.tipe = tipe;
        this.price = price;
    }

    public void setid_storage(int id_storage) {
        this.id_storage = id_storage;
    }

    public int getid_storage()
    {
        return id_storage;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getTipe() {
        return tipe;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString(){
        return this.getName();
    }
}
