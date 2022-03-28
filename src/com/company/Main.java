package com.company;

import com.company.ArithmeticExpression.ArithmeticExpressionGenerator;

import java.util.Scanner;

public class Main {
        private static int threadNumber = 2; //Количество потоков

        private static int readNumber(String str){
            int min = 1;
            int max = Runtime.getRuntime().availableProcessors();
            int num;
            Scanner in = new Scanner(System.in);
            System.out.println(str);
            do {
                System.out.print("-->");
                num = in.nextInt();
                if (num < min || num > max) {
                    System.out.println("Некорректное значение, повторите ввод!");
                }
            } while (num < min || num > max);
            return num;
        }

        public static void main (String[]args){
            threadNumber = readNumber("Выберите количество потоков");
            ArithmeticExpressionGenerator generator = new ArithmeticExpressionGenerator();
            long startTime = System.nanoTime();
            ThreadSplitter[] threads = new ThreadSplitter[threadNumber];
            for (int i = 1; i <= threadNumber; i++) {
                threads[i - 1] = new ThreadSplitter("ThreadSplitter" + i, generator);
                threads[i - 1].start();
            }
            for (int i = 1; i <= threadNumber; i++) {
                try {
                    threads[i - 1].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
            System.out.println("Время работы программы: " + (System.nanoTime() - startTime));
        }
}
