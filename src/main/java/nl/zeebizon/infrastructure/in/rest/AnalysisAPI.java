package nl.zeebizon.infrastructure.in.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import nl.zeebizon.application.AnalysisService;
import nl.zeebizon.domain.entities.WordFrequency;
import nl.zeebizon.infrastructure.in.rest.dto.FrequencyForWordRequest;
import nl.zeebizon.infrastructure.in.rest.dto.MostCommonWordFrequencyRequest;
import nl.zeebizon.infrastructure.in.rest.dto.MostCommonWordsRequest;

import java.util.List;

@RequiredArgsConstructor
@Path("/words")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnalysisAPI {

    private final AnalysisService service;

    @POST
    @Path("/most-common/frequency/")
    public Integer mostCommonWordFrequency(MostCommonWordFrequencyRequest request) {
        return service.getHighestFrequency(request.text());
    }

    @POST
    @Path("/{word}/frequency")
    public Integer frequencyForWordRequest(FrequencyForWordRequest request,
                                           @PathParam("word") String word) {
        return service.getFrequencyForWord(request.text(), word);
    }

    @POST
    @Path("/most-common/{n}")
    public List<WordFrequency> mostCommonWords(MostCommonWordsRequest request,
                                    @PathParam("n") Integer n) {
        return service.getMostFrequentWords(request.text(), n);
    }
}
