package pl.traineeship.autoqa.lesson2;

/*
Написать метод, который определяет, является ли год високосным, и возвращает boolean
(високосный - true, не високосный - false). Каждый 4-й год является високосным,
кроме каждого 100-го, при этом каждый 400-й – високосный.
 */
public final class TaskStar {
    public static boolean isLeapYear(final int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }
}
