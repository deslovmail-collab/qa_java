package com.example;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FelineTest {

    private final Feline feline = new Feline();

    @Test
    public void eatMeat_возвращает_еду_для_хищника() throws Exception {
        List<String> food = feline.eatMeat();

        assertNotNull("Список еды не должен быть null", food);
        assertFalse("Список еды не должен быть пустым", food.isEmpty());
        assertTrue("Еда должна содержать 'Животные'", food.contains("Животные"));
        assertTrue("Еда должна содержать 'Птицы'", food.contains("Птицы"));
        assertTrue("Еда должна содержать 'Рыба'", food.contains("Рыба"));
    }

    @Test
    public void getKittens_всегда_возвращает_1() {
        assertEquals(1, feline.getKittens());
    }

    @Test
    public void getFamily_возвращает_кошачьи() {
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void getFood_сНекорректнымВидом_бросаетИсключение() {
        Animal animal = new Animal();
        try {
            animal.getFood("Птица");
            fail("Ожидалось исключение");
        } catch (Exception e) {
            assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", e.getMessage());
        }
    }
}  // ← ЭТОТ } ЗАКРЫВАЕТ ВЕСЬ КЛАСС — ОН ОБЯЗАТЕЛЕН!
