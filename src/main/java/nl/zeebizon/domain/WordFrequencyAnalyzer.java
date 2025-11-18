package nl.zeebizon.domain;

import nl.zeebizon.domain.entities.WordFrequency;

import java.util.List;

public interface WordFrequencyAnalyzer {
    /**
     * Given {@code text}, calculate the highest frequency of any word in the text.
     * @param text Input text
     * @return The frequency for the most frequent word in {@code text}.
     */
    int calculateHighestFrequency(String text);

    /**
     * Given {@code text} and a {@code word}, calculate the frequency of the word in the text.
     * @param text Input text
     * @param word Word to count
     * @return The frequency of {@code word} in {@code text}.
     */
    int calculateFrequencyForWord (String text, String word);

    /**
     * Given {@code text} and a number {@code n}, calculate the most frequent n words in the text.
     *
     * @param text Input text
     * @param n    A Positive number of most frequent words to return
     * @return A list of the most frequent {@code n} words in {@code text}.
     */
    List<WordFrequency> calculateMostFrequentNWords (String text, int n);
}
