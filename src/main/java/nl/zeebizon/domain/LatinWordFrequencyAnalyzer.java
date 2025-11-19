package nl.zeebizon.domain;

import jakarta.enterprise.context.ApplicationScoped;
import nl.zeebizon.domain.entities.WordFrequency;
import nl.zeebizon.domain.entities.WordFrequencyRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.Comparator.comparingInt;

@ApplicationScoped
public class LatinWordFrequencyAnalyzer implements WordFrequencyAnalyzer {

    // Delimit everything that's not a latin letter (a-zA-Z)
    private static final Pattern DELIMITER = Pattern.compile("[^a-zA-Z]+");

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculateHighestFrequency(String text) {
        if (text == null || text.isBlank())
            return 0;
        return mapWordFrequencies(text)
                .values()
                .stream()
                .max(Integer::compareTo)
                .orElse(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculateFrequencyForWord(String text, String word) {
        if (text == null || text.isBlank() || word == null || word.isBlank())
            return 0;
        if (!text.contains(word))
            return 0;
        return mapWordFrequencies(text)
                .getOrDefault(word, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        if (text == null || text.isBlank() || n == 0)
            return List.of();
        if (n < 0)
            throw new IllegalArgumentException("N must be a positive integer. Provided: '%s'".formatted(n));

        var wordFrequencies = mapWordFrequencies(text).entrySet()
                .stream()
                .map(entry -> (WordFrequency) new WordFrequencyRecord(entry.getKey(), entry.getValue()))
                .toList();

        // If we have fewer unique words than n, skip sorting
        if (wordFrequencies.size() <= n)
            return wordFrequencies;

        return wordFrequencies.stream()
                .sorted(comparingInt(WordFrequency::getFrequency).reversed()
                        .thenComparing(WordFrequency::getWord))
                .limit(n)
                .toList();
    }

    private static Map<String, Integer> mapWordFrequencies(String text) {
        var wordCounts = new HashMap<String, Integer>();
        // Stream the words, filter empty words, and count them in wordCounts
        DELIMITER.splitAsStream(text)
                .map(String::toLowerCase)
                .filter(word -> !word.isBlank())
                .forEach(word -> wordCounts.merge(word, 1, Integer::sum));
        return wordCounts;
    }
}
