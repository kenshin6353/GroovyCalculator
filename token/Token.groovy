package token

interface Token {
    void evaluate(List<Double> stack) throws Exception
}

class Operand implements Token {
    double value

    Operand(double value) {
        this.value = value
    }

    @Override
    void evaluate(List<Double> stack) {
        stack.add(value)
    }
}

enum OperatorType {
    ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION
}

class Operator implements Token {
    
    OperatorType type

    Operator(OperatorType type) {
        this.type = type
    }

    Character getSymbol() {
        def symbol
        switch (this.type) {
            case OperatorType.ADDITION:
                symbol = '+'
                break
            case OperatorType.SUBTRACTION:
                symbol = '-'
                break
            case OperatorType.MULTIPLICATION:
                symbol = '*'
                break
            case OperatorType.DIVISION:
                symbol = '/'
                break
        }
        return symbol
    }

    @Override
    void evaluate(List<Double> stack) throws Exception {
        if (stack.size() < 2) {
            throw new Exception("Insufficient operands")
        }
        
        double right = stack.remove(stack.size() - 1)
        double left = stack.remove(stack.size() - 1)
        double result

        switch (type) {
            case OperatorType.ADDITION:
                result = left + right
                break
            case OperatorType.SUBTRACTION:
                result = left - right
                break
            case OperatorType.MULTIPLICATION:
                result = left * right
                break
            case OperatorType.DIVISION:
                if (right == 0) {
                    throw new Exception("Division by zero")
                }
                result = left / right
                break
        }
        stack.add(result)
    }
}