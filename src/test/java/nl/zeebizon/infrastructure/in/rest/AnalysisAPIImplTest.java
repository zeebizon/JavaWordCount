package nl.zeebizon.infrastructure.in.rest;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import nl.zeebizon.application.AnalysisService;
import nl.zeebizon.infrastructure.in.rest.dto.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@QuarkusTest
class AnalysisAPIImplTest {
    @InjectMock
    AnalysisService analysisService;

    @Inject
    AnalysisAPIImpl analysisAPIImpl;

    @Test
    void mostCommonWordFrequency() {
        when(analysisService.getHighestFrequency(anyString())).thenReturn(0);
        var response = analysisAPIImpl.mostCommonWordFrequency(new MostCommonWordFrequencyRequest("any string"));
        assertThat(response)
                .isInstanceOf(MostCommonWordFrequencyResponse.class)
                .extracting("frequency")
                .isEqualTo(0);
        verify(analysisService,  times(1)).getHighestFrequency(anyString());
    }

    @Test
    void frequencyForWordRequest() {
        when(analysisService.getFrequencyForWord(anyString(), anyString())).thenReturn(0);
        var response = analysisAPIImpl.frequencyForWordRequest(new FrequencyForWordRequest("Some sample text"), "sample");
        assertThat(response)
                .isInstanceOf(FrequencyForWordResponse.class)
                .extracting("frequency", "word")
                .containsExactly(0, "sample");
        verify(analysisService,  times(1)).getFrequencyForWord(anyString(), anyString());
    }

    @Test
    void mostCommonWords() {
        when(analysisService.getMostFrequentWords(anyString(), anyInt())).thenReturn(List.of());
        var response = analysisAPIImpl.mostCommonWords(new MostCommonWordsRequest("Some sample text"), 3);
        assertThat(response)
                .isInstanceOf(MostCommonWordsResponse.class)
                .extracting("wordFrequencies")
                .isEqualTo(List.of());
        verify(analysisService,  times(1)).getMostFrequentWords(anyString(), anyInt());
    }
}