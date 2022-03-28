package com.company.ArithmeticExpression;

import java.text.ParseException;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class ArithmeticExpression {

    private final String OPERATORS = "+-"; //Оператор
    private Stack<String> stackOperations = new Stack<>(); // Стек для операторов
    private Stack<String> stackRPN = new Stack<>();        // Стек для операндов
    private Stack<Double> stackAnswer = new Stack<>();     // Стек с ответом

    /**
     * Метод для парсинга строки в число
     */
    private boolean isNumber(String symbol) {
        try {
            Double.parseDouble(symbol);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Проверка является ли символ оператором
     */
    private boolean isOperator(String symbol) {
        return OPERATORS.contains(symbol);
    }

    /**
     * вычисление операции
     * */
    private double Operation(double right, double left, String action) {
        switch (action) {
            case "+": {
                return left + right;
            }
            case "-": {
                return left - right;
            }
        }
        return 0;
    }

    /**
     * парсер строки
     */
    public double parse(String expression) throws ParseException {
        stackOperations.clear();
        stackRPN.clear();
        stackAnswer.clear();

        StringTokenizer stringTokenizer = new StringTokenizer(expression, OPERATORS, true);

        while (stringTokenizer.hasMoreTokens()) {
            String symbol = stringTokenizer.nextToken();
            if (isNumber(symbol)) {
                stackRPN.push(symbol);
            } else {
                if (isOperator(symbol)) {
                    while (!stackOperations.empty()) {
                        stackRPN.push(stackOperations.pop());
                    }
                }
                stackOperations.push(symbol);
            }
        }

        while (!stackOperations.empty()) {
            stackRPN.push(stackOperations.pop());
        }

        Collections.reverse(stackRPN);

        while (!stackRPN.isEmpty()) {
            String symbol = stackRPN.pop();
            if (isNumber(symbol)) {
                stackAnswer.push(Double.parseDouble(symbol));
            } else {
                stackAnswer.push(Operation(stackAnswer.pop(), stackAnswer.pop(), symbol));
            }
        }

        return stackAnswer.pop();
    }
}