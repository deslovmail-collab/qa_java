package com.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AnimalTest {

    private final Animal animal = new Animal();

    // Травоядное — еда
    @Test
    public void getFoodForHerbivoreShouldReturnPlants() throws Exception {
        List<String> expected = Arrays.asList("Трава", "Различные растения");
        List<String> actual = animal.getFood("Травоядное");

        assertEquals("Список еды для травоядного не совпадает с ожидаемым", expected, actual);
    }

    // Хищник — еда
    @Test
    public void getFoodForPredatorShouldReturnAnimals() throws Exception {
        List<String> expected = Arrays.asList("Животные", "Птицы", "Рыба");
        List<String> actual = animal.getFood("Хищник");

        assertEquals("Список еды для хищника не совпадает с ожидаемым", expected, actual);
    }

    // Некорректный вид
    @Test
    public void getFoodWithInvalidAnimalTypeShouldThrowException() {
        try {
            animal.getFood("Птица");
            fail("Ожидалось исключение при передаче некорректного вида животного");
        } catch (Exception e) {
            assertEquals(
                    "Сообщение об ошибке должно быть точным",
                    "Неизвестный вид животного, используйте значение Травоядное или Хищник",
                    e.getMessage()
            );
        }
    }

    // getFamily
    @Test
    public void getFamilyShouldReturnCorrectDescription() {
        String expected = "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";
        String actual = animal.getFamily();

        assertEquals("Описание семейств не совпадает с ожидаемым", expected, actual);
    }
}
