package task3;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        String fileName = "words.txt";
        Map<String, Integer> wordFrequencyMap = countWordFrequency(fileName);

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordFrequencyMap.entrySet());

        Collections.sort(entryList, new WordFrequencyComparator());

        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static Map<String, Integer> countWordFrequency(String fileName) {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            scanner.useDelimiter("\\s+");

            while (scanner.hasNext()) {
                String word = scanner.next().trim().toLowerCase();
                if (!word.isEmpty()) {
                    if (wordFrequencyMap.containsKey(word)) {
                        wordFrequencyMap.put(word, wordFrequencyMap.get(word) + 1);
                    } else {
                        wordFrequencyMap.put(word, 1);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("File not found: " + fileName);
            e.printStackTrace();
        }

        return wordFrequencyMap;
    }

    static class WordFrequencyComparator implements java.util.Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
            return entry2.getValue().compareTo(entry1.getValue());
        }
    }
}
