package pl.traineeship.autoqa.lesson2;

/*
*** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным,
или отрицательным), при этом метод должен сместить все элементы массива на n позиций. Элементы
смещаются циклично. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ];
[ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
При каком n в какую сторону сдвиг можете выбирать сами.
 */
public final class TaskThreeStars {
    public static void rotateArray(final int[] arr, int n) {
        if (Math.abs(n) > arr.length) {
            n = n % arr.length;
        }
        if (n < 0) {
            n = arr.length + n;
        }
        int limit = Math.max(n, arr.length - n);
        for (int i = 0; i < limit; ++i) {
            int temp = arr[i];
            if (i - n < 0) {
                arr[i] = arr[arr.length - n + i];
                arr[arr.length - n + i] = temp;
            } else {
                arr[i] = arr[n + i];
                arr[n + i] = temp;
            }
        }
    }
}
