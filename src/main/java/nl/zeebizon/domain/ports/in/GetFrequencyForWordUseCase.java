package nl.zeebizon.domain.ports.in;

public interface GetFrequencyForWordUseCase {
    int getFrequencyForWord(String text, String word);
}
