package pl.traineeship.autoqa.lesson2;

/*
1. Написать метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит
в пределах от 10 до 20 (включительно), если да – вернуть true, в противном случае – false.
 */
public final class Task1 {
    private final static int MIN_VALUE = 10;
    private final static int MAX_VALUE = 20;

    public static boolean checkSumRange(final int a, final int b) {
        int sum = a + b;
        return sum >= MIN_VALUE && sum <= MAX_VALUE;
    }
}
