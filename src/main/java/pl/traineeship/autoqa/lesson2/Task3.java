package pl.traineeship.autoqa.lesson2;

/*
3. Написать метод, которому в качестве параметра передается целое число.
Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.
Замечание: ноль считаем положительным числом.
 */
public final class Task3 {
    public static boolean isNegative(final int x) {
        return x < 0;
    }
}
