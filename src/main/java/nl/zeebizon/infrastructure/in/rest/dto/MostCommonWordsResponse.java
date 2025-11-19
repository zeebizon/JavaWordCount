package nl.zeebizon.infrastructure.in.rest.dto;

import nl.zeebizon.domain.entities.WordFrequency;

import java.util.List;

public record MostCommonWordsResponse (
        List<WordFrequency> wordFrequencies
) {}
