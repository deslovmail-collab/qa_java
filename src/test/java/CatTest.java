package com.example;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class CatTest {

    @RunWith(Parameterized.class)
    public static class CatFoodTest {
        private final String expectedFood;

        @Parameterized.Parameters(name = "Кошка ест: {0}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"Мышь"},
                    {"Птица"},
                    {"Рыба"}
            });
        }

        public CatFoodTest(String expectedFood) {
            this.expectedFood = expectedFood;
        }

        @Test
        public void catGetFoodShouldReturnAllMeat() throws Exception {
            Feline mockFeline = Mockito.mock(Feline.class);
            List<String> expectedFoodList = Arrays.asList("Мышь", "Птица", "Рыба");
            when(mockFeline.eatMeat()).thenReturn(expectedFoodList);

            Cat cat = new Cat(mockFeline);
            List<String> actualFood = cat.getFood();

            assertEquals("Список еды кошки не совпадает с ожидаемым", expectedFoodList, actualFood);
        }
    }

    @RunWith(Parameterized.class)
    public static class CatKittensTest {
        private final int expectedKittens;

        @Parameterized.Parameters(name = "Количество котят: {0}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {1},
                    {2},
                    {5}
            });
        }

        public CatKittensTest(int expectedKittens) {
            this.expectedKittens = expectedKittens;
        }

        @Test
        public void catGetKittensShouldReturnCorrectCount() throws Exception {
            Feline mockFeline = Mockito.mock(Feline.class);
            when(mockFeline.getKittens()).thenReturn(expectedKittens);

            Cat cat = new Cat(mockFeline);
            assertEquals("Количество котят должно быть " + expectedKittens,
                    expectedKittens, cat.getKittens());
        }
    }

    @RunWith(Parameterized.class)
    public static class AnimalDietComparisonTest {
        private final String animalType;
        private final String foodItem;

        @Parameterized.Parameters(name = "Животное: {0}, еда: {1}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"Лев", "Газель"},
                    {"Лев", "Зебра"},
                    {"Кошка", "Мышь"},
                    {"Кошка", "Птица"}
            });
        }

        public AnimalDietComparisonTest(String animalType, String foodItem) {
            this.animalType = animalType;
            this.foodItem = foodItem;
        }

        @Test
        public void animalEatMeatShouldContainCorrectFood() throws Exception {
            Feline mockFeline = Mockito.mock(Feline.class);
            List<String> expectedMeat = Arrays.asList("Газель", "Зебра", "Мышь", "Птица");
            when(mockFeline.eatMeat()).thenReturn(expectedMeat);

            if ("Лев".equals(animalType)) {
                Lion lion = new Lion("Самец", mockFeline);
                assertEquals("Лев должен есть всю указанную еду", expectedMeat, lion.eatMeat());
            } else if ("Кошка".equals(animalType)) {
                Cat cat = new Cat(mockFeline);
                assertEquals("Кошка должна есть всю указанную еду", expectedMeat, cat.getFood());
            }
        }
    }

    // Проверка звука кошки
    @Test
    public void catGetSoundShouldReturnMeow() {
        Cat cat = new Cat(null);
        String sound = cat.getSound();
        assertEquals("Кошка должна говорить 'Мяу'", "Мяу", sound);
    }
}
