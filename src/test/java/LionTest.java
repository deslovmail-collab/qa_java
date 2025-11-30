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
public class LionTest {

    private final String sex;
    private final Boolean expectedHasMane;
    private final String animalType;
    private final String foodItem;
    private final Integer kittensFromFeline;
    private final Integer expectedKittens;
    private final String invalidSex;
    private final Boolean isExceptionTest;

    public LionTest(
            String sex, Boolean expectedHasMane,
            String animalType, String foodItem,
            Integer kittensFromFeline, Integer expectedKittens,
            String invalidSex, Boolean isExceptionTest
    ) {
        this.sex = sex;
        this.expectedHasMane = expectedHasMane;
        this.animalType = animalType;
        this.foodItem = foodItem;
        this.kittensFromFeline = kittensFromFeline;
        this.expectedKittens = expectedKittens;
        this.invalidSex = invalidSex;
        this.isExceptionTest = isExceptionTest;
    }

    @Parameterized.Parameters(name = "Тест: {7}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Самец", true,  null, null,  null, null, null, false},
                {"Самка", false, null, null,  null, null, null, false},

                {null, null, "Лев", "Газель",   null, null, null, false},
                {null, null, "Лев", "Зебра",    null, null, null, false},
                {null, null, "Кошка", "Мышь",   null, null, null, false},
                {null, null, "Кошка", "Птица",  null, null, null, false},

                {null, null, null, null, 1, 1, null, false},
                {null, null, null, null, 3, 3, null, false},
                {null, null, null, null, 5, 5, null, false},

                {null, null, null, null, null, null, "Гермафродит", true},
                {null, null, null, null, null, null, "Нейтральный", true},
                {null, null, null, null, null, null, "Муже-женский", true},
                {null, null, null, null, null, null, "", true},
                {null, null, null, null, null, null, "   ", true}
        });
    }

    @Test
    public void левИмеетГриву_вЗависимостиОтПола() throws Exception {
        if (sex != null && expectedHasMane != null) {
            Feline mockFeline = Mockito.mock(Feline.class);
            Lion lion = new Lion(sex, mockFeline);
            assertEquals(expectedHasMane, lion.doesHaveMane());
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

    @Test
    public void левПолучаетУказанноеКоличествоКотят() throws Exception {
        if (kittensFromFeline != null && expectedKittens != null) {
            Feline mockFeline = Mockito.mock(Feline.class);
            when(mockFeline.getKittens()).thenReturn(kittensFromFeline);

            Lion lion = new Lion("Самец", mockFeline);
            assertEquals((int) expectedKittens, lion.getKittens());
        }
    }

    @Test
    public void приНеверномПолеБросаетсяИсключение() {
        if (isExceptionTest && invalidSex != null) {
            Feline mockFeline = Mockito.mock(Feline.class);

            try {
                new Lion(invalidSex, mockFeline);
                fail("Ожидалось исключение");
            } catch (Exception e) {
                assertEquals("Используйте допустимые значения пола животного - самец или самка", e.getMessage());
            }
        }
    }
}
