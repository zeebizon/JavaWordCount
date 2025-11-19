package nl.zeebizon.infrastructure.in.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import nl.zeebizon.application.AnalysisService;
import nl.zeebizon.infrastructure.in.rest.dto.*;

@RequiredArgsConstructor
@Path("/words")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnalysisAPI {

    private final AnalysisService service;

    @POST
    @Path("/most-common/frequency/")
    public MostCommonWordFrequencyResponse mostCommonWordFrequency(MostCommonWordFrequencyRequest request) {
        var frequency = service.getHighestFrequency(request.text());
        return new MostCommonWordFrequencyResponse(frequency);
    }

    @POST
    @Path("/{word}/frequency")
    public FrequencyForWordResponse frequencyForWordRequest(FrequencyForWordRequest   request,
                                           @PathParam("word") String word) {
        var frequency = service.getFrequencyForWord(request.text(), word);
        return new FrequencyForWordResponse(word, frequency);
    }

    @POST
    @Path("/most-common/{n}")
    public MostCommonWordsResponse mostCommonWords(MostCommonWordsRequest  request,
                                                   @PathParam("n") Integer n) {
        var wordFrequencies = service.getMostFrequentWords(request.text(), n);
        return new MostCommonWordsResponse(wordFrequencies);
    }
}
