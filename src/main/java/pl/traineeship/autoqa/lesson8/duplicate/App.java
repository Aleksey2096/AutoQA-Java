package pl.traineeship.autoqa.lesson8.duplicate;

/*
Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
Посчитать, сколько раз встречается каждое слово.
 */
public class App {
    public static void main(String[] args) {

        String[] wordsArr = {"the", "be", "to", "of", "and", "the", "be",
                "to", "the", "the", "be", "to", "of", "be", "the"};

        WordUtil wordUtil = new WordUtil(wordsArr);

        System.out.println(wordUtil.getUniqueWords());  // [the, be, and, of, to]

        wordUtil.printWordsMap();
        /*
            Word: 'the', Number of occurrences = 5
            Word: 'be', Number of occurrences = 4
            Word: 'and', Number of occurrences = 1
            Word: 'of', Number of occurrences = 2
            Word: 'to', Number of occurrences = 3
         */
    }
}
