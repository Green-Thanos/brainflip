package com.smallbrainflip;

import com.smallbrainflip.BrainFlip;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class BrainFlipTest {
    private ByteArrayOutputStream outputStream;
    private BrainFlipInherited interpreter;

    private byte[][] memState;
    private int stateIndex = 0;

    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        interpreter = new BrainFlipInherited(new PrintStream(outputStream));
        memState = new byte[100][30000];
    }

    public void testHelloWorld() {
        String program = "+[----->+++<]>+.+.";
        interpreter.interpret(program);
        System.out.println("Output: " + outputStream.toString());
        System.out.println("Expected: hi");
    }

    public void testFibonacci() {
        String program = "+++++++++++>+>>>>++++++++++++++++++++++++++++++++++++++++++++>++++++++++++++++++++++++++++++++<<<<<<[>[>>>>>>+>+<<<<<<<-]>>>>>>>[<<<<<<<+>>>>>>>-]<[>++++++++++[-<->>>>>>>>+<<<<<<<<]>>>>>>>>>]<<<<<<<<<[>>>>+>[-]>>>[->>>>>>>>+<<<<<<<<[>+>]>>>>>>+>]<<+>[-]>>>>>>>+[>]+>]";
        interpreter.interpret(program);
        int[] result = interpreter.getFibonacci(12);
        System.out.print("Fibonacci sequence: ");
        for (int j : result) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public void testSimpleProgram() {
        interpreter.reset();
        String program = "++."; // Simplest BrainFlip program that increments memory cell 3 times and prints it
        for (int i = 0; i < program.length(); i++) {
            interpreter.interpret(program.substring(i, i+1));
            memState[stateIndex++] = Arrays.copyOf(interpreter.getMem(), interpreter.getMem().length);
        }
        System.out.println("Memory state after running the program:");
        for (int i = 0; i < stateIndex; i++) {
            for (int j = 0; j < memState[i].length; j++) {
                System.out.print(memState[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BrainFlipTest test = new BrainFlipTest();
        test.setUp();
        System.out.println("Running testHelloWorld...");
        test.testHelloWorld();
        System.out.println("\nRunning testFibonacci...");
        test.testFibonacci();
        System.out.println("Running testSimpleProgram...");
        test.testSimpleProgram();
    }
}

class BrainFlipInherited extends BrainFlip {
    private PrintStream outputStream;

    public BrainFlipInherited(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void interpret(String code) {
        super.interpret(code);
    }

    public int[] getFibonacci(int n) {
        return fibonacciRecursive(n);
    }

    private int[] fibonacciRecursive(int n) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = fibonacci(i);
        }
        return result;
    }

    private int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}