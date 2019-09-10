package com.bsg5.chapter2;

import org.springframework.stereotype.Service;

import java.io.PrintStream;

@Service
public class HelloWorldGreeter implements Greeter {

    private PrintStream printStream = System.out;

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void greet() {
        printStream.print("Hello, World!");
    }

}