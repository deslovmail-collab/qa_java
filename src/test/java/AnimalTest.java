package com.example;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AnimalTest {

    private final Animal animal = new Animal();

    @Test
    public void getFood_дляТравоядного_возвращаетРастения() throws Exception {
        List<String> food = animal.getFood("Травоядное");
        assertEquals(2, food.size());
        assertTrue(food.contains("Трава"));
        assertTrue(food.contains("Различные растения"));
    }

    @Test
    public void getFood_дляХищника_возвращаетЖивотных() throws Exception {
        List<String> food = animal.getFood("Хищник");
        assertEquals(3, food.size());
        assertTrue(food.contains("Животные"));
        assertTrue(food.contains("Птицы"));
        assertTrue(food.contains("Рыба"));
    }

    @Test
    public void getFood_сНекорректнымВидом_бросаетИсключение() {
        try {
            animal.getFood("Птица");
            fail("Ожидалось исключение");
        } catch (Exception e) {
            assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", e.getMessage());
        }
    }

    @Test
    public void getFamily_возвращаетТекстОСемействах() {
        String family = animal.getFamily();
        assertNotNull(family);
        assertTrue(family.contains("кошачьи"));
        assertTrue(family.contains("псовые"));
    }

    @Test
    public void getFamily_возвращаетОбщееОписаниеСемейств() {
        String family = animal.getFamily();
        assertNotNull("Семейство не должно быть null", family);
        assertTrue("Описание должно содержать 'кошачьи'", family.contains("кошачьи"));
        assertTrue("Описание должно содержать 'псовые'", family.contains("псовые"));
        assertEquals("Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи", family);
    }
}

