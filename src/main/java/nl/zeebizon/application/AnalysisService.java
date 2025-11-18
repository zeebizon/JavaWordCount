package nl.zeebizon.application;

import nl.zeebizon.domain.WordFrequencyAnalyzer;
import nl.zeebizon.domain.entities.WordFrequency;
import nl.zeebizon.domain.ports.in.GetFrequencyForWordUseCase;
import nl.zeebizon.domain.ports.in.GetHighestFrequencyUseCase;
import nl.zeebizon.domain.ports.in.GetMostFrequentWordsUseCase;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class AnalysisService implements GetFrequencyForWordUseCase,
                                        GetHighestFrequencyUseCase,
                                        GetMostFrequentWordsUseCase {

    private final WordFrequencyAnalyzer wordFrequencyAnalyzer;

    @Override
    public int getFrequencyForWord(String text, String word) {
        return wordFrequencyAnalyzer.calculateFrequencyForWord(text, word);
    }

    @Override
    public int getHighestFrequency(String text) {
        return wordFrequencyAnalyzer.calculateHighestFrequency(text);
    }

    @Override
    public List<WordFrequency> getMostFrequentWords(String text, int n) {
        return wordFrequencyAnalyzer.calculateMostFrequentNWords(text, n);
    }
}
