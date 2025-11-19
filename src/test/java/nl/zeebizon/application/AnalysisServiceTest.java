package nl.zeebizon.application;


import nl.zeebizon.domain.WordFrequencyAnalyzer;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

@QuarkusTest
class AnalysisServiceTest {

    @InjectMock
    WordFrequencyAnalyzer analyzer;

    @Inject
    AnalysisService analysisService;

    @Test
    void getFrequencyForWord() {
        when(analyzer.calculateFrequencyForWord(anyString(), anyString())).thenReturn(0);
        analysisService.getFrequencyForWord("Some sample text", "sample");
        verify(analyzer, times(1)).calculateFrequencyForWord(anyString(), anyString());
    }

    @Test
    void getHighestFrequency() {
        when(analyzer.calculateHighestFrequency(anyString())).thenReturn(0);
        analysisService.getHighestFrequency("Some sample text");
        verify(analyzer, times(1)).calculateHighestFrequency(anyString());
    }

    @Test
    void getMostFrequentWords() {
        when(analyzer.calculateMostFrequentNWords(anyString(), anyInt())).thenReturn(List.of());
        analysisService.getMostFrequentWords("Some sample text", 1);
        verify(analyzer, times(1)).calculateMostFrequentNWords(anyString(), anyInt());
    }
}