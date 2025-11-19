package nl.zeebizon.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class LatinWordFrequencyAnalyzerTest {

    final WordFrequencyAnalyzer analyzer = new LatinWordFrequencyAnalyzer();

    /**
     * Tests for calculateHighestFrequency
     */

    @Test
    void calculateHighestFrequency_WithValidText() {
        var text = "The quick brown fox jumps over the lazy dog. The fox is quick. Extra .  space.";
        var result = analyzer.calculateHighestFrequency(text);
        assertThat(result).isEqualTo(3); // the
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { " .\t\n" })
    void calculateHighestFrequency_WithEmptyText(String text) {
        assertThat(analyzer.calculateHighestFrequency(text)).isEqualTo(0);
    }

    /**
     * Tests for calculateFrequencyForWord
     */

    @Test
    void calculateFrequencyForWord_WithValidTextAndWord() {
        String text = "The quick brown fox jumps over the lazy dog. The fox is quick.";
        String word = "quick";
        var result = analyzer.calculateFrequencyForWord(text, word);
        assertThat(result).isEqualTo(2);
    }

    @Test
    void calculateFrequencyForWord_WithWordNotInText() {
        String text = "The quick brown fox jumps over the lazy dog.";
        String word = "cat";
        int result = analyzer.calculateFrequencyForWord(text, word);
        assertThat(result).isEqualTo(0);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { " .\t\n" })
    void calculateFrequencyForWord_WithEmptyText(String text) {
        assertThat(analyzer.calculateFrequencyForWord(text, "quick")).isEqualTo(0);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { " .\t\n" })
    void calculateFrequencyForWord_WithEmptyWord(String text) {
        assertThat(analyzer.calculateFrequencyForWord("The quick fox", text)).isEqualTo(0);
    }

    /**
     * Tests for calculateMostFrequentNWords
     */

    @Test
    void calculateMostFrequentNWords_WithValidText() {
        String text = "The quick brown fox jumps over the lazy dog. The fox is quick.";
        var result = analyzer.calculateMostFrequentNWords(text, 3);
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getWord()).isEqualTo("the");
        assertThat(result.get(0).getFrequency()).isEqualTo(3);
        assertThat(result.get(1).getWord()).isEqualTo("fox");
        assertThat(result.get(1).getFrequency()).isEqualTo(2);
        assertThat(result.get(2).getWord()).isEqualTo("quick");
        assertThat(result.get(2).getFrequency()).isEqualTo(2);
    }

    @Test
    void calculateMostFrequentNWords_WithTiesInFrequency() {
        String text = "The sun shines over the lake";
        var result = analyzer.calculateMostFrequentNWords(text, 2);
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getWord()).isEqualTo("the");
        assertThat(result.get(1).getWord()).isEqualTo("lake");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { " .\t\n" })
    void calculateMostFrequentNWords_WithEmptyText(String text) {
        assertThat(analyzer.calculateMostFrequentNWords(text, 3)).isEmpty();
    }

    @Test
    void calculateMostFrequentNWords_WithNegativeN() {
        String text = "The quick brown fox jumps over the lazy dog.";
        assertThatThrownBy(() -> analyzer.calculateMostFrequentNWords(text, -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void calculateMostFrequentNWords_WithNGreaterThanUniqueWords() {
        String text = "The quick brown fox.";
        var result = analyzer.calculateMostFrequentNWords(text, 10);
        assertThat(result).hasSize(4);
    }

    @Test
    void calculateMostFrequentNWords_WithNIsZero() {
        String text = "The quick brown fox.";
        var result = analyzer.calculateMostFrequentNWords(text, 0);
        assertThat(result).isEmpty();
    }
}
