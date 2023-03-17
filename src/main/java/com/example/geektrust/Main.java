package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            InitService service = new InitService();
            service.init();
            service.initBuffer();
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
                String s = sc.nextLine();
                String string[] = s.split(" ");
                if(string[0].equalsIgnoreCase("vacancy")) {
                    service.vacancy(string);
                }
                if(string[0].equalsIgnoreCase("BOOK")) {
                    service.book(string);
                }

            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }

    }
}
/*
09:00 - 09:15,13:15 - 13:45 18:45 - 19:00
 */