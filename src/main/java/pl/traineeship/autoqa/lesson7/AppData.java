package pl.traineeship.autoqa.lesson7;

import static pl.traineeship.autoqa.lesson7.CsvUtil.DELIMITER;

public class AppData {

    private static final String NEW_LINE = "\n";
    private String[] header;
    private int[][] data;

    public String[] getHeader() {
        return header;
    }

    public void setHeader(final String[] newHeader) {
        header = newHeader;
    }

    public int[][] getData() {
        return data;
    }

    public void setData(final int[][] newData) {
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.join(DELIMITER, header));
        sb.append(NEW_LINE);
        for (int[] intArr : data) {
            for (int i = 0; i < intArr.length; ++i) {
                sb.append(intArr[i]);
                if (i == intArr.length - 1) {
                    sb.append(NEW_LINE);
                } else {
                    sb.append(DELIMITER);
                }
            }
        }
        return sb.toString().trim();
    }
}
