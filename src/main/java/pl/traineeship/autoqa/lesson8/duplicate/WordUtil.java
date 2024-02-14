package pl.traineeship.autoqa.lesson8.duplicate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordUtil {
    private Map<String, Integer> wordsMap = new HashMap<>();

    public WordUtil(String[] words) {
        fillMWordMap(words);
    }

    public Set<String> getUniqueWords() {
        return wordsMap.keySet();
    }

    public void printWordsMap() {
        for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
            System.out.println("Word: '"
                    + entry.getKey()
                    + "', Number of occurrences = "
                    + entry.getValue());
        }
    }

    private void fillMWordMap(String[] words) {
        for (String word : words) {
            if (wordsMap.containsKey(word)) {
                wordsMap.put(word, wordsMap.get(word) + 1);
            } else {
                wordsMap.put(word, 1);
            }
        }
    }
}
