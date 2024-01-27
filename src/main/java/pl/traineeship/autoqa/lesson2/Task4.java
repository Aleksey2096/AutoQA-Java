package pl.traineeship.autoqa.lesson2;

/*
4. Написать метод, которому в качестве аргументов передается строка и число,
метод должен отпечатать в консоль указанную строку, указанное количество раз;
 */
public final class Task4 {
    public static void printString(final String str, final int quantity) {
        for (int i = 0; i < quantity; ++i) {
            System.out.println(str);
        }
    }
}
