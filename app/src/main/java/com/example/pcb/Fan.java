package com.example.pcb;

public class Fan {
    private int id_fan;
    private String name;
    private int rpm;
    private int price;

    public Fan (int id_fan, String name, int rpm,  int price)
    {
        this.id_fan=id_fan;
        this.name=name;
        this.rpm = rpm;
        this.price = price;
    }

    public int getid_fan()
    {
        return id_fan;
    }

    public String getName() {
        return name;
    }

    public int getrpm() {
        return rpm;
    }

    public int getPrice() {
        return price;
    }

    public void setid_fan(int id_fan) {
        this.id_fan = id_fan;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setrpm(int rpm) {
        this.rpm = rpm;
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public String toString(){
        return this.getName();
    }
}
