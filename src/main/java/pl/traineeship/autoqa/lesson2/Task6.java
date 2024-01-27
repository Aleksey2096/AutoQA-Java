package pl.traineeship.autoqa.lesson2;

/*
6. Задать пустой целочисленный массив длиной 100.
С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100;
 */
public final class Task6 {
    private static final int ARRAY_SIZE = 100;

    public static void fillArray() {
        int[] arr = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; ++i) {
            arr[i] = i + 1;
        }
    }
}
