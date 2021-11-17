package com.example.pcb;

public class RAM {
    private int id_ram;
    private String name;
    private int speed;
    private String modules;
    private int price;

    public RAM (int id_ram, String name, int speed, String modules, int price)
    {
        this.id_ram=id_ram;
        this.name=name;
        this.speed = speed;
        this.modules = modules;
        this.price = price;
    }

    public int getId_ram()
    {
        return id_ram;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public String getModules() {
        return modules;
    }

    public int getPrice() {
        return price;
    }

    public void setId_ram(int id_ram) {
        this.id_ram = id_ram;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString(){
        return this.getName();
    }
}
