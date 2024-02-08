package pl.traineeship.autoqa.lesson5;

import java.util.Arrays;

/*
Написать метод, который меняет два элемента массива местами
(массив может быть любого ссылочного типа);
 */
public class ArrayUtil {

    public static void main(String[] args) {
        Integer[] intArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        swapElements(intArr, 2, 6);
        System.out.println(Arrays.toString(intArr));        // [0, 1, 6, 3, 4, 5, 2, 7, 8, 9]

        String[] stringArr = {"a", "ab", "abc"};
        swapElements(stringArr, 0, 2);
        System.out.println(Arrays.toString(stringArr));     // [abc, ab, a]
    }

    public static <T> void swapElements(T[] arr, int index1, int index2) {
        if (index1 < 0 || index1 >= arr.length || index2 < 0 || index2 >= arr.length) {
            System.out.println("Invalid indices. Unable to swap elements.");
            return;
        }

        T temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
