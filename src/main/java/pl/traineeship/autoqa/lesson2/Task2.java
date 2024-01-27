package pl.traineeship.autoqa.lesson2;

/*
2. Написать метод, которому в качестве параметра передается целое число,
метод должен напечатать в консоль, положительное ли число передали или отрицательное.
Замечание: ноль считаем положительным числом.
 */
public final class Task2 {
    private static final String POSITIVE_NUMBER = "Positive number";
    private static final String NEGATIVE_NUMBER = "Negative number";

    public static void printPositiveOrNegative(final int x) {
        if (x >= 0) {
            System.out.println(POSITIVE_NUMBER);
        } else {
            System.out.println(NEGATIVE_NUMBER);
        }
    }
}
