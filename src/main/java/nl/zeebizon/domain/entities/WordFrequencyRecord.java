package nl.zeebizon.domain.entities;

public record WordFrequencyRecord(String word, int frequency) implements WordFrequency {
    @Override
    public String getWord() {
        return word();
    }

    @Override
    public int getFrequency() {
        return frequency();
    }
}
