package com.example;

import java.util.List;

public class Lion implements Predator {

    private final Feline feline;
    private final boolean hasMane;

    // Конструктор: зависимости передаются извне
    public Lion(String sex, Feline feline) throws Exception {
        this.feline = feline;// <-- зависимость внедрена
        if ("Самец".equals(sex)) {
            this.hasMane = true;
        } else if ("Самка".equals(sex)) {
            this.hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного - самец или самка");
        }

    }

    // Геттер для гривы
    public boolean doesHaveMane() {
        return hasMane;
    }

    // Переданный Feline
    @Override
    public List<String> eatMeat() throws Exception {
        return feline.eatMeat(); // делегируем
    }

    // Метод для котят — тоже через Feline
    public int getKittens() {
        return feline.getKittens();
    }
}
