package pl.traineeship.autoqa.lesson2;

/*
7. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
пройти по нему циклом, и числа меньшие 6 умножить на 2;
 */
public final class Task7 {
    private static final int LIMIT = 6;

    public static void multiplyArrayValues() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] < LIMIT) {
                arr[i] = arr[i] * 2;
            }
        }
    }
}
