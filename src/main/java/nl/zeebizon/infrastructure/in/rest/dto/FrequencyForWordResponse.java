package nl.zeebizon.infrastructure.in.rest.dto;

public record FrequencyForWordResponse(
        String word,
        int frequency
) {}
