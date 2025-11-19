package nl.zeebizon.application;


import nl.zeebizon.domain.WordFrequencyAnalyzer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnalysisServiceTest {

    @Mock
    WordFrequencyAnalyzer analyzer;
    @InjectMocks
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