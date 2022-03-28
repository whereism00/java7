package com.company.ArithmeticExpression;

import java.util.Random;

public class ArithmeticExpressionGenerator {

    private int length = 1000;       // Количество операндов в строке
    private int count = 10000;        // Количество строк с операндами
    private final Random rnd = new Random();
    private final String[] expressions;
    private int currentExpressionNumber = 0;

    /**
     * Конструктор
     */
    public ArithmeticExpressionGenerator() {
        this.expressions = new String[count];

        for (int i = 0; i < this.count; i++) {
            StringBuilder expression = new StringBuilder();
            for (int j = 0; j < this.length - 1; j++) {
                expression.append(rnd.nextInt(100));
                expression.append(rnd.nextBoolean() ? '+' : '-');
            }
            expression.append(rnd.nextInt(100));
            expressions[i] = expression.toString();
        }
    }

    /**
     * Получение следующей строки из массива строк
     */
    public synchronized String getNextExpression() {
        if (currentExpressionNumber < count) {
            return expressions[currentExpressionNumber++];
        } else {
            return "";
        }
    }
}

