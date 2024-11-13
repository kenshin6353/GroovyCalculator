package calculator

import expressionParser.ExpressionParser
import token.Token

class Calculator {
    private ExpressionParser parser = new ExpressionParser()

    double calculate(String expression) throws Exception {
        List<Token> tokens = parser.parse(expression)
        parser.evaluate(tokens)
    }

    String formatResult(double result) {
        result == result.intValue() ? result.intValue().toString() : result.toString()
    }
}