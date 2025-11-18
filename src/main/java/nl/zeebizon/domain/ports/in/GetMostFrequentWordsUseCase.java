package nl.zeebizon.domain.ports.in;

import nl.zeebizon.domain.entities.WordFrequency;

import java.util.List;

public interface GetMostFrequentWordsUseCase {

    List<WordFrequency> getMostFrequentWords(String text, int n);
}
