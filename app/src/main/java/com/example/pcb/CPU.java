package com.example.pcb;

public class CPU {

    private int id_cpu;
    private String name;
    private  int core;
    private  float clock;
    private String integrated;
    private long price;

    public CPU(int id_cpu, String name, int core, float clock, String integrated, long price){
        this.id_cpu =  id_cpu;
        this.name = name;
        this.core = core;
        this.clock = clock;
        this.integrated = integrated;
        this.price = price;
    }

    public int getId_cpu() {
        return id_cpu;
    }

    public void setId_cpu(int id_cpu) {
        this.id_cpu = id_cpu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCore() {
        return core;
    }

    public void setCore(int core) {
        this.core = core;
    }

    public float getClock() {
        return clock;
    }

    public void setClock(float clock) {
        this.clock = clock;
    }

    public String getIntegrated() {
        return integrated;
    }

    public void setIntegrated(String integrated) {
        this.integrated = integrated;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String toString(){
        return this.getName();
    }

}
