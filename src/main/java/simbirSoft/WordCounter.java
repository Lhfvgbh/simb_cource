package simbirSoft;

import java.util.*;
import java.util.logging.Logger;

public class WordCounter {

    Logger logger = Logger.getLogger(WordCounter.class.getName());
    private Map<String, Integer> wordMap;
    private List<String> wordList;

    public WordCounter(String text, char[] splitters, boolean ignoreRegisterFlag) {
        countAllWords(text, splitters, ignoreRegisterFlag);
        countEachWord(text);
    }

    public Map<String, Integer> getWordMap() {
        return wordMap;
    }

    public List<String> getWordList() {
        return wordList;
    }

    protected void countAllWords(String text, char[] splitters, boolean ignoreRegisterFlag) {
        this.wordList = new ArrayList<>();
        String splittersList = new String(splitters);
        char[] bodyChars = text.toCharArray();
        int firstChar = 0;

        for (int i = 0; i < bodyChars.length; i++) {
            char c = bodyChars[i];
            if (splittersList.contains(Character.toString(c))) {
                String w = ignoreRegisterFlag ? text.substring(firstChar, i).toLowerCase(Locale.ROOT) : text.substring(firstChar, i);
                this.wordList.add(w);
                firstChar = i + 1;
            }
        }

        if (firstChar < bodyChars.length) {
            String lastWord = ignoreRegisterFlag ? text.substring(firstChar, bodyChars.length).toLowerCase(Locale.ROOT) : text.substring(firstChar, bodyChars.length);
            this.wordList.add(lastWord);
        }

        Set<String> uniqueWords = new HashSet<>(wordList);
        wordList = new ArrayList<>(uniqueWords);
        logger.info(wordList.size() + " unique words was retrieved from the text");
    }

    public void countEachWord(String text) {
        this.wordMap = new HashMap<>();
        for (String word : wordList) {
            this.wordMap.put(word, text.split(word).length);
        }
    }

    protected Map<String, Integer> sortMapDESC(Map<String, Integer> unsortMap) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public void showResults() {
        Map<String, Integer> sortedMap = sortMapDESC(wordMap);
        for (Map.Entry entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
