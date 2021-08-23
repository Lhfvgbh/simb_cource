package simbirSoft;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class WordCounterTest {
    private String[] testText;
    private char[] testSplitters;
    private WordCounter wc;

    @Before
    public void initialWC() {
        testText = new String[]{"abc;def)gh.abc", "abc;def)gh.abc;", "abc;def)gh.abcd"};
        testSplitters = ";).".toCharArray();
    }

    @Test
    public void countAllWordsTest() {
        for (int i = 0; i < 3; i++) {
            wc = new WordCounter(testText[i], testSplitters, true);
            int expectedResult = (i != 2) ? 3 : 4;
            int actualResult = this.wc.getWordList().size();

            assertEquals(expectedResult, actualResult);
        }
    }

    @Test
    public void countEachWordTest() {
        this.wc = new WordCounter(testText[1], testSplitters, true);

        Map<String, Integer> expectedResult = new HashMap<>();
        expectedResult.put("abc", 2);
        expectedResult.put("def", 1);
        expectedResult.put("gh", 1);

        Map<String, Integer> actualResult = this.wc.getWordMap();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void sortMap() {
        this.wc = new WordCounter(testText[1], testSplitters, true);

        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("B", 55);
        unsortMap.put("A", 80);
        unsortMap.put("D", 20);
        unsortMap.put("C", 70);

        Map<String, Integer> expectedResult = new LinkedHashMap<>();
        expectedResult.put("A", 80);
        expectedResult.put("C", 70);
        expectedResult.put("B", 55);
        expectedResult.put("D", 20);

        Map<String, Integer> actualResult = this.wc.sortMapDESC(unsortMap);
        assertEquals(expectedResult, actualResult);
    }
}
