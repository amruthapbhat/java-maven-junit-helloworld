package com.example.javamavenjunithelloworld;
import java.io.PrintStream;

public class Hello {
    static final String HELLO = "Hello!";
    public static final int MAXIMUM_AMOUNT_OF_TIMES = 20;
    private short times = 1;

    public void setTimes(int times) {
        if (times < 0 || times > MAXIMUM_AMOUNT_OF_TIMES) {
            throw new IllegalArgumentException("Parameter should be a positive integer no larger than " + MAXIMUM_AMOUNT_OF_TIMES + ".");
        }
        this.times = (short) times;
    }

    public void sayHello(PrintStream printer) {
        for (short i = 0; i < times; i++) {
            printer.println(HELLO);
        }
    }
}
