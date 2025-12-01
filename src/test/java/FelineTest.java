package com.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FelineTest {

    private final Feline feline = new Feline();

    @Test
    public void eatMeatShouldReturnCorrectFoodList() throws Exception {
        List<String> expected = Arrays.asList("Животные", "Птицы", "Рыба");
        List<String> actual = feline.eatMeat();

        assertEquals("Список еды должен совпадать с ожидаемым", expected, actual);
    }

    @Test
    public void getKittensShouldReturnOne() {
        int kittens = feline.getKittens();
        assertEquals("Количество котят должно быть 1", 1, kittens);
    }

    @Test
    public void getFamilyShouldReturnFelidae() {
        String family = feline.getFamily();
        assertEquals("Семейство должно быть 'Кошачьи'", "Кошачьи", family);
    }

    @Test
    public void getFoodWithInvalidAnimalTypeShouldThrowException() {
        Animal animal = new Animal();
        try {
            animal.getFood("Птица");
            fail("Ожидалось исключение при передаче некорректного вида животного");
        } catch (Exception e) {
            assertEquals("Сообщение об ошибке должно быть точным",
                    "Неизвестный вид животного, используйте значение Травоядное или Хищник",
                    e.getMessage());
        }
    }
}
