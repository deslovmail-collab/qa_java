package com.example;

import java.util.List;

public class Cat {

    private final Feline feline; // Было: Predator predator — исправлено

    public Cat(Feline feline) {
        this.feline = feline;
    }

    public String getSound() {
        return "Мяу";
    }

    public List<String> getFood() throws Exception {
        return feline.eatMeat();
    }

    // ✅ Добавлен метод — теперь тесты смогут его вызвать
    public int getKittens() {
        return feline.getKittens();
    }
}
