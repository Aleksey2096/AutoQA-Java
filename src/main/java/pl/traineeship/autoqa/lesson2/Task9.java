package pl.traineeship.autoqa.lesson2;

/*
9.* Написать метод, принимающий на вход два аргумента: len и initialValue, и возвращающий
одномерный массив типа int длиной len, каждая ячейка которого равна initialValue;
 */
public final class Task9 {
    public static int[] createArray(final int len, final int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; ++i) {
            arr[i] = initialValue;
        }
        return arr;
    }
}
