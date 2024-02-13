package pl.traineeship.autoqa.lesson6;

import pl.traineeship.autoqa.lesson6.exception.MyArrayDataException;
import pl.traineeship.autoqa.lesson6.exception.MyArraySizeException;

/*
3. В методе main() вызвать полученный метод, обработать возможные исключения
MyArraySizeException и MyArrayDataException и вывести результат расчета.
 */
public class App {
    public static void main(String[] args) {

        String[][] wrongSizeArr = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3"},
                {"1", "2", "3", "4"}
        };

        try {
            int sum = ArrayUtil.calculateElementsSum(wrongSizeArr);
            System.out.println(sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage()); // Size of the two-dimensional array is not 4 by 4
        }


        String[][] wrongFormatArr = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "?"},
                {"1", "2", "3", "4"}
        };

        try {
            int sum = ArrayUtil.calculateElementsSum(wrongFormatArr);
            System.out.println(sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage()); // Incorrect int string '?' in the cell [2][3]
        }


        String[][] arr = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"}
        };

        try {
            int sum = ArrayUtil.calculateElementsSum(arr);
            System.out.println(sum);                                 // 40
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }
}
