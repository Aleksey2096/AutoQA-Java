package pl.traineeship.autoqa.lesson6;

import pl.traineeship.autoqa.lesson6.exception.MyArrayDataException;
import pl.traineeship.autoqa.lesson6.exception.MyArraySizeException;

/*
1. Напишите метод, на вход которого подается двумерный строковый массив размером 4х4.
При подаче массива другого размера необходимо бросить исключение MyArraySizeException.

2. Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ
или текст вместо числа), должно быть брошено исключение MyArrayDataException с детализацией,
в какой именно ячейке лежат неверные данные.
 */
public class ArrayUtil {

    private static final int ARRAY_SIZE = 4;
    private static final String ARRAY_SIZE_EXCEPTION_MESSAGE
            = "Size of the two-dimensional array is not 4 by 4";

    public static int calculateElementsSum(String[][] stringArr)
            throws MyArraySizeException, MyArrayDataException {
        if (stringArr.length != ARRAY_SIZE) {
            throw new MyArraySizeException(ARRAY_SIZE_EXCEPTION_MESSAGE);
        } else {
            for (String[] innerArr : stringArr) {
                if (innerArr.length != ARRAY_SIZE) {
                    throw new MyArraySizeException(ARRAY_SIZE_EXCEPTION_MESSAGE);
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < stringArr.length; ++i) {
            for (int j = 0; j < stringArr[i].length; ++j) {
                try {
                    sum += Integer.parseInt(stringArr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Incorrect int string '"
                            + stringArr[i][j] + "' in the cell [" + i + "][" + j + "]");
                }
            }
        }

        return sum;
    }
}
