package com.example.pcb;

public class GPU {
    private int id_gpu;
    private String name;
    private String chip;
    private String memory;
    private int clock;
    private int price;

    public GPU (int id_gpu, String name, int clock, String chip, String memory, int price)
    {
        this.id_gpu=id_gpu;
        this.name=name;
        this.chip = chip;
        this.memory=memory;
        this.clock = clock;
        this.price = price;
    }

    public int getid_gpu()
    {
        return id_gpu;
    }

    public String getName() {
        return name;
    }

    public int getclock() {
        return clock;
    }

    public String getMemory() {
        return memory;
    }

    public String getchip() {
        return chip;
    }

    public int getPrice() {
        return price;
    }

    public void setid_gpu(int id_gpu) {
        this.id_gpu = id_gpu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setclock(int clock) {
        this.clock = clock;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public void setchip(String chip) {
        this.chip = chip;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString(){
        return this.getName();
    }
}