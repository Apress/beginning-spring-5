package com.bsg5.chapter2;

import org.springframework.stereotype.Service;

import java.io.PrintStream;

@Service
public class WeatherGreeter implements Greeter {

    private PrintStream printStream = System.out;

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void greet() {
        printStream.print("Hello, the weather today is 68 and Cloudy");
    }
}
