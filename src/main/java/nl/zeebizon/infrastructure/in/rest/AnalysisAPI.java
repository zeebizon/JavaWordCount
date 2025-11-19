package nl.zeebizon.infrastructure.in.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import nl.zeebizon.infrastructure.in.rest.dto.*;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

@Path("/words")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AnalysisAPI {

    @POST
    @Path("/most-common/frequency/")
    @Operation(summary = "Get the frequency of the most frequent word in the text")
    @APIResponse(
            responseCode = "200",
            description = "Frequency of the most frequent word",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = MostCommonWordFrequencyResponse.class))
    )
    MostCommonWordFrequencyResponse mostCommonWordFrequency(
            @RequestBody(
                    description = "Text to analyze",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MostCommonWordFrequencyRequest.class))
            )
            MostCommonWordFrequencyRequest request
    );

    @POST
    @Path("/{word}/frequency")
    @Operation(summary = "Get the frequency of a specific word in the text")
    @APIResponse(
            responseCode = "200",
            description = "Frequency for the given word",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FrequencyForWordResponse.class))
    )
    FrequencyForWordResponse frequencyForWordRequest(
            @RequestBody(
                    description = "Text to analyze",
                    required = true,
                    content = @Content(schema = @Schema(implementation = FrequencyForWordRequest.class))
            )
            FrequencyForWordRequest request,
            @PathParam("word") @Parameter(description = "Word to check frequency for", required = true) String word
    );

    @POST
    @Path("/most-common/{n}")
    @Operation(summary = "Get the N most common words in the text")
    @APIResponse(
            responseCode = "200",
            description = "List of the N most common words with their frequencies",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = MostCommonWordsResponse.class))
    )
    MostCommonWordsResponse mostCommonWords(
            @RequestBody(
                    description = "Text to analyze",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MostCommonWordsRequest.class))
            )
            MostCommonWordsRequest request,
            @PathParam("n") @Parameter(description = "Number of top words to return", required = true) Integer n
    );
}
