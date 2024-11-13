import java.util.Scanner
import calculator.Calculator

// Main program with user input options
void runCalculator() {
    Scanner scanner = new Scanner(System.in)
    println("Choose an option:")
    println("1: Enter first number, operator, and second number separately")
    println("2: Enter the entire expression (e.g., 5.5 + 2 * 4.1)")

    def option = scanner.nextLine()?.toInteger()
    Calculator calculator = new Calculator()

    try {
        double result
        switch (option) {
            case 1:
                println("Enter the first number:")
                double firstNumber = scanner.nextLine()?.toDouble()

                println("Enter an operator (+, -, *, /):")
                char opSymbol = scanner.nextLine().charAt(0)

                println("Enter the second number:")
                double secondNumber = scanner.nextLine()?.toDouble()

                result = calculator.calculate("$firstNumber $opSymbol $secondNumber")
                break

            case 2:
                println("Enter the expression (e.g., 5.5 + 2 * 4.1):")
                String expression = scanner.nextLine()

                result = calculator.calculate(expression)
                break

            default:
                println("Invalid choice.")
                return
        }
        
        println("Result: ${calculator.formatResult(result)}")

    } catch (Exception e) {
        println("Error during calculation: ${e.message}")
    }
}

// Run the calculator
runCalculator()
