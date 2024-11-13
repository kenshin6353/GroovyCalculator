package expressionParser

import token.Token
import token.Operand
import token.OperatorType
import token.Operator

class ExpressionParser {
    final Map<Character, Integer> precedence = [
        ('+' as Character): 1,
        ('-' as Character): 1,
        ('*' as Character): 2,
        ('/' as Character): 2
    ]

    List<Token> parse(String expression) {
        List<Token> tokens = []
        StringBuilder numberBuffer = new StringBuilder()
        expression.each { c ->
            if (Character.isWhitespace((char) c)) {
                return
            }
            
            if (Character.isDigit((char) c) || c == '.') {
                numberBuffer.append(c)
            } else {
                if (numberBuffer.length() > 0) {
                    tokens.add(new Operand(numberBuffer.toString().toDouble()))
                    numberBuffer.setLength(0)
                }

                try {
                    OperatorType opType

                    if (c == '+') {
                        opType = OperatorType.ADDITION
                    } else if (c == '-') {
                        opType = OperatorType.SUBTRACTION
                    } else if (c == '*') {
                        opType = OperatorType.MULTIPLICATION
                    } else if (c == '/') {
                        opType = OperatorType.DIVISION
                    }
                    tokens.add(new Operator(opType))
                } catch (IllegalArgumentException e) {
                    // Ignore invalid characters
                }
            }
        }

        if (numberBuffer.length() > 0) {
            tokens.add(new Operand(numberBuffer.toString().toDouble()))
        }

        tokens
    }

    double evaluate(List<Token> tokens) throws Exception {
        List<Double> operandStack = []
        List<Token> operatorStack = []

        tokens.each { token ->
            if (token instanceof Operand) {
                token.evaluate(operandStack)
            } else if (token instanceof Operator) {
                // Ensure only Operator tokens are managed here
                while (operatorStack && operatorStack[-1] instanceof Operator &&
                    precedence[((Operator) operatorStack[-1]).getSymbol()] >= precedence[((Operator) token).getSymbol()]) {
                    operatorStack.remove(operatorStack.size() - 1).evaluate(operandStack)
                }
                operatorStack.add(token)
            }
        }
        while (operatorStack) {
            operatorStack.remove(operatorStack.size() - 1).evaluate(operandStack)
        }

        return operandStack.last()
    }

}