package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class CatTest {

    private final String expectedFood;
    private final int expectedKittens;
    private final String animalType;
    private final String foodItem;

    public CatTest(String expectedFood, int expectedKittens, String animalType, String foodItem) {
        this.expectedFood = expectedFood;
        this.expectedKittens = expectedKittens;
        this.animalType = animalType;
        this.foodItem = foodItem;
    }

    @Parameterized.Parameters(name = "Тест с едой: {0}, котятами: {1}, животным: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Мышь", 0, null, null},
                {"Птица", 0, null, null},
                {"Рыба", 0, null, null},

                {null, 1, null, null},
                {null, 2, null, null},
                {null, 5, null, null},

                {null, 0, "Лев", "Газель"},
                {null, 0, "Лев", "Зебра"},
                {null, 0, "Кошка", "Мышь"},
                {null, 0, "Кошка", "Птица"}
        });
    }

    @Test
    public void кошкаПолучаетУказаннуюЕду() throws Exception {
        if (expectedFood != null) {
            Feline mockFeline = Mockito.mock(Feline.class);
            when(mockFeline.eatMeat()).thenReturn(Arrays.asList("Мышь", "Птица", "Рыба"));

            Cat cat = new Cat(mockFeline);
            List<String> food = cat.getFood();

            assertTrue("Еда должна содержать: " + expectedFood, food.contains(expectedFood));
        }
    }

    @Test
    public void кошкаПолучаетУказанноеКоличествоКотят() throws Exception {
        if (expectedKittens != 0) {
            Feline mockFeline = Mockito.mock(Feline.class);
            when(mockFeline.getKittens()).thenReturn(expectedKittens);

            Cat cat = new Cat(mockFeline);
            assertEquals(expectedKittens, cat.getKittens());
        }
    }

    @Test
    public void животныеПолучаютПравильнуюЕду() throws Exception {
        if (animalType != null && foodItem != null) {
            Feline mockFeline = Mockito.mock(Feline.class);
            when(mockFeline.eatMeat()).thenReturn(Arrays.asList("Газель", "Зебра", "Мышь", "Птица"));

            if ("Лев".equals(animalType)) {
                Lion lion = new Lion("Самец", mockFeline);
                assertTrue(lion.eatMeat().contains(foodItem));
            } else if ("Кошка".equals(animalType)) {
                Cat cat = new Cat(mockFeline);
                assertTrue(cat.getFood().contains(foodItem));
            }
        }
    }
}
