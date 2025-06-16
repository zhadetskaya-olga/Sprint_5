package com.example;

import java.util.List;

public class Lion extends Feline {
    private final Feline feline;
    boolean hasMane;

    public Lion(String sex,   Feline feline) throws Exception {
        this.feline = feline;
        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного - самец или самка");
        }
    }
    public boolean hasMane(){
        return hasMane;
    }

    @Override
    public int getKittens(){
        return feline.getKittens(1);
    }
    @Override
    public int getKittens(int kittens){
        return feline.getKittens(kittens);
    }

    public List<String> getFood() throws Exception {
        return feline.getFood("Хищник");
    }

}