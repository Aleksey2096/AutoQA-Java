package pl.traineeship.autoqa.lesson2;

/*
8. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
и с помощью цикла(-ов) заполнить его диагональные элементы единицами (можно только одну из
диагоналей, если обе сложно). Определить элементы одной из диагоналей можно по следующему
принципу: индексы таких элементов равны, то есть [0][0], [1][1], [2][2], …, [n][n];
 */
public final class Task8 {
    private static final int MATRIX_SIZE = 15;

    public static void fillMatrix() {
        int[][] matrix = new int[MATRIX_SIZE][MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; ++i) {
            matrix[i][i] = 1;
            matrix[i][MATRIX_SIZE - 1 - i] = 1;
        }
    }
}
