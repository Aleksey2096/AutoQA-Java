package pl.traineeship.autoqa.lesson7;

/*
1. Реализовать сохранение данных в csv файл;
2. Реализовать загрузку данных из csv файла. Файл читается целиком.

Структура csv файла:
Строка заголовок с набором столбцов
Набор строк с целочисленными значениями
* Разделитель между столбцами - символ точка с запятой (;)

Пример:

Value 1;Value 2;Value 3
100;200;123
300;400;500

Для хранения данных использовать класс вида:

public class AppData {
  private String[] header;
  private int[][] data;
 // ...
}

Если выполняется save(AppData data), то старые данные в файле полностью перезаписываются.
 */
public class App {
    public static void main(String[] args) {

        AppData appData = CsvUtil.readFile("files/input.csv");

        System.out.println(appData);
        /*
            Value 1;Value 2;Value 3
            100;200;123
            300;400;500
         */

        CsvUtil.writeFile(appData, "files/output.csv");
    }
}
