package nl.zeebizon.infrastructure.in.rest;

import lombok.RequiredArgsConstructor;
import nl.zeebizon.application.AnalysisService;
import nl.zeebizon.infrastructure.in.rest.dto.*;

@RequiredArgsConstructor
public class AnalysisAPIImpl implements AnalysisAPI{

    private final AnalysisService service;

    @Override
    public MostCommonWordFrequencyResponse mostCommonWordFrequency(MostCommonWordFrequencyRequest request) {
        var frequency = service.getHighestFrequency(request.text());
        return new MostCommonWordFrequencyResponse(frequency);
    }

    @Override
    public FrequencyForWordResponse frequencyForWordRequest(FrequencyForWordRequest request,
                                                            String word) {
        var frequency = service.getFrequencyForWord(request.text(), word);
        return new FrequencyForWordResponse(word, frequency);
    }

    @Override
    public MostCommonWordsResponse mostCommonWords(MostCommonWordsRequest  request,
                                                   Integer n) {
        var wordFrequencies = service.getMostFrequentWords(request.text(), n);
        return new MostCommonWordsResponse(wordFrequencies);
    }
}
