package ru.diasoft.service;

import java.util.Scanner;

public class IOServiceImpl implements IOService {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public String read() {
        return sc.nextLine();
    }

    @Override
    public void write(String text) {
        System.out.println(text);
    }

}