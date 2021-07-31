package pl.glownia.pamela;

public interface PrintableProperties {

    void printProperties(long number);

    void printProperties(long beginNumber, long counter);

    void printProperties(long beginNumber, long counter, String userProperty);

    void printProperties(long beginNumber, long counter, String firstProperty, String secondProperty);

}
