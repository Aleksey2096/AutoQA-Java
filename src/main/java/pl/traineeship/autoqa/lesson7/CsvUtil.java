package pl.traineeship.autoqa.lesson7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    public static final String DELIMITER = ";";

    public static AppData readFile(String fileName) {
        AppData appData = new AppData();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String str;
            if ((str = reader.readLine()) != null) {
                appData.setHeader(str.split(DELIMITER));
            }
            List<int[]> intArrList = new ArrayList<>();
            while ((str = reader.readLine()) != null) {
                String[] stringArr = str.split(DELIMITER);
                int[] intArr = new int[stringArr.length];
                for (int i = 0; i < intArr.length; ++i) {
                    intArr[i] = Integer.parseInt(stringArr[i]);
                }
                intArrList.add(intArr);
            }
            appData.setData(intArrList.toArray(new int[0][]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appData;
    }

    public static void writeFile(AppData appData, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(String.join(DELIMITER, appData.getHeader()));
            writer.newLine();
            for (int[] intArr : appData.getData()) {
                String[] stringArr = new String[intArr.length];
                for (int i = 0; i < stringArr.length; ++i) {
                    stringArr[i] = String.valueOf(intArr[i]);
                }
                writer.write(String.join(DELIMITER, stringArr));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
