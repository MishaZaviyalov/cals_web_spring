package com.example.prackone.classes;

import java.util.Stack;

public class MathMaker {
    public static double solveMathExpression(String expression) {
        // Удаление всех пробелов из выражения
        expression = expression.replaceAll("\\s+", "");

        Stack<Double> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                // Если символ - цифра, извлекаем всее число и помещаем его в стек операндов
                StringBuilder operand = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i) ) || expression.charAt(i) == '.')) {
                    operand.append(expression.charAt(i));
                    i++;
                }
                i--; // Возвращаем i назад, так как последний символ не является цифрой
                operands.push(Double.parseDouble(operand.toString()));
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                // Если символ - оператор, выполняем операции согласно их приоритету
                while (!operators.isEmpty() && hasPrecedence(operators.peek(), c)) {
                    double b = operands.pop();
                    double a = operands.pop();
                    char operator = operators.pop();
                    double result = performOperation(a, b, operator);
                    operands.push(result);
                }
                operators.push(c);
            }
        }

        // Выполнение оставшихся операций
        while (!operators.isEmpty()) {
            double b = operands.pop();
            double a = operands.pop();
            char operator = operators.pop();
            double result = performOperation(a, b, operator);
            operands.push(result);
        }

        // Результат будет на вершине стека операндов
        return operands.pop();
    }

    // Проверка приоритета операторов
    private static boolean hasPrecedence(char operator1, char operator2) {
        if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-')) {
            return true;
        }
        return false;
    }

    // Выполнение операции
    private static double performOperation(double a, double b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b != 0) {
                    return a / b;
                } else {
                    throw new ArithmeticException("Деление на ноль недопустимо");
                }
        }
        throw new IllegalArgumentException("Недопустимый оператор: " + operator);
    }
}
