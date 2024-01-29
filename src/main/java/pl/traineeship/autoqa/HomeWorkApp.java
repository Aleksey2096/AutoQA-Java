package pl.traineeship.autoqa;

import java.util.concurrent.ThreadLocalRandom;

/*
Задание 1
1. Создать удаленный репозиторий на GitHub, подключить его к IDE в качестве проекта,
добавить к нему модуль Maven. Создать новую ветку в репозитории в IntelliJ IDEA,
создать класс HomeWorkApp, и прописать в нем метод main().

2. Создайте метод printThreeWords(), который при вызове должен отпечатать
 в столбец три слова: Orange, Banana, Apple.

3. Создайте метод checkSumSign(), в теле которого объявите две int переменные a и b,
 и инициализируйте их любыми значениями, которыми захотите. Далее метод должен
 просуммировать эти переменные, и если их сумма больше или равна 0,
 то вывести в консоль сообщение “Сумма положительная”, в противном случае - “Сумма отрицательная”;

4. Создайте метод printColor() в теле которого задайте int переменную value
и инициализируйте ее любым значением. Если value меньше 0 (0 включительно),
то в консоль метод должен вывести сообщение “Красный”, если лежит в пределах от 0 (0 исключительно)
до 100 (100 включительно), то “Желтый”, если больше 100 (100 исключительно) - “Зеленый”;

5. Создайте метод compareNumbers(), в теле которого объявите две int переменные a и b,
и инициализируйте их любыми значениями, которыми захотите. Если a больше или равно b,
то необходимо вывести в консоль сообщение “a >= b”, в противном случае “a < b”;

6. Методы из пунктов 2-5 вызовите из метода main() и посмотрите, корректно ли они работают.
 */
public class HomeWorkApp {

    private static final String ORANGE = "Orange";
    private static final String BANANA = "Banana";
    private static final String APPLE = "Apple";
    private static final String FORMAT_STRING = "%s\n%s\n%s\n";
    private static final String POSITIVE_SUM = "Сумма положительная";
    private static final String NEGATIVE_SUM = "Сумма отрицательная";
    private static final String GREEN = "Зеленый";
    private static final String YELLOW = "Желтый";
    private static final String RED = "Красный";
    private static final String GREATER_OR_EQUAL = "a >= b";
    private static final String LESS = "a < b";

    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
    }

    private static void printThreeWords() {
        System.out.printf(FORMAT_STRING, ORANGE, BANANA, APPLE);
    }

    private static void checkSumSign() {
        int a = ThreadLocalRandom.current().nextInt(), b = ThreadLocalRandom.current().nextInt();
        if (a + b >= 0) {
            System.out.println(POSITIVE_SUM);
        } else {
            System.out.println(NEGATIVE_SUM);
        }
    }

    private static void printColor() {
        int value = ThreadLocalRandom.current().nextInt();
        if (value > 100) {
            System.out.println(GREEN);
        } else if (value > 0) {
            System.out.println(YELLOW);
        } else {
            System.out.println(RED);
        }
    }

    private static void compareNumbers() {
        int a = ThreadLocalRandom.current().nextInt(), b = ThreadLocalRandom.current().nextInt();
        if (a >= b) {
            System.out.println(GREATER_OR_EQUAL);
        } else {
            System.out.println(LESS);
        }
    }
}
