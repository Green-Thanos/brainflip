package com.smallbrainflip;

import java.util.*;
public class BrainFlip {
    private Scanner sc = new Scanner(System.in);
    private final int len = 30000;
    private byte[] mem = new byte[len]; // Memory Tape
    private int ptr;

    public byte[] getMem() {
        return mem;
    }

    public void reset() {
        mem = new byte[len];
        ptr = 0;
    }

    public void printMem() {
        for(byte i : mem) { System.out.print(i + " "); }
    }

    public void interpret(String code) {
        int l = 0;
        for(int i = 0; i < code.length(); i++) {
            switch (code.charAt(i)) {
                case '>':
                    ptr = (ptr == len - 1) ? 0 : ptr + 1;
                    break;
                case '<':
                    ptr = (ptr == 0) ? len - 1 : ptr - 1;
                    break;
                case '+':
                    mem[ptr]++;
                    break;
                case '-':
                    mem[ptr]--;
                    break;
                case '.':
                    System.out.print((char) mem[ptr]);
                    break;
                case ',':
                    mem[ptr] = (byte) sc.next().charAt(0);
                    break;
                case '[':
                    if (mem[ptr] == 0) {
                        i++;
                        while (l > 0 || code.charAt(i) != ']') {
                            if (code.charAt(i) == '[') l++;
                            if (code.charAt(i) == ']') l--;
                            i++;
                        }
                    }
                    break;
                case ']':
                    if (mem[ptr] != 0) {
                        i--;
                        while (l > 0 || code.charAt(i) != '[') {
                            if (code.charAt(i) == ']') l++;
                            if (code.charAt(i) == '[') l--;
                            i--;
                        }
                        i--;
                    }
                    break;
                case '@':
                    mem[ptr] = (byte) (255 - mem[ptr]);
                    break;
                case '?':
                    mem[ptr] = (byte) (Math.random() * 256);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        BrainFlip inter = new BrainFlip();
        inter.interpret(args[0]);
        System.out.print("\n");
        inter.printMem();
    }
}
