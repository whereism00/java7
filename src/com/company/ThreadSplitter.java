package com.company;

import com.company.ArithmeticExpression.ArithmeticExpressionGenerator;
import com.company.ArithmeticExpression.ArithmeticExpression;

import java.text.ParseException;

/**
 * Класс - многопоточный разделитель
 */
public class ThreadSplitter extends Thread {

    ArithmeticExpressionGenerator generator;
    ArithmeticExpression arithmeticExpression;
    int count = 0;

    /**
     * Конструктор
     */
    public ThreadSplitter(String name, ArithmeticExpressionGenerator generator) {
        super(name);
        arithmeticExpression = new ArithmeticExpression();
        this.generator = generator;
    }

    @Override
    public void run() {
        System.out.println("Поток " + Thread.currentThread().getName() + " начал работу");
        try {
            String expression = generator.getNextExpression();
            while (!expression.equals("")) {
                System.out.println("Поток " + Thread.currentThread().getName() + " вернул результат = " +
                        arithmeticExpression.parse(expression) + ". Количество посчитанных потоком строк: " + ++count);
                expression = generator.getNextExpression();
            }
        } catch (ParseException e) {
            System.out.println("Не удалось считать строку!");
        }
        System.out.printf("Поток " + Thread.currentThread().getName() + " завершил работу");
        System.out.println();
    }
}

