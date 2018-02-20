
/*************************************************************
 * edX - Microsoft DEV277x Object Oriented Programming in Java
 * Module 2 Project - FRACTION CALCULATOR
 * Author: Juraj Sallai
 * 20/2/2018
 * Class FractionCalculator
 * User enters operand and two fractions on separate lines
 * Program returns result
 * Allowed operations are addition, subtraction, multiplication, division
 * and equals, which checks if fractions are equal.
 *************************************************************/

import java.util.*;

class FractionCalculator {

    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        introduction();

        while (true) {

            String operand = getOperation();
            Fraction fracA = getFraction();
            Fraction fracB = getFraction();

            System.out.print(fracA + " " + operand + " " + fracB + " is ");

            if (operand.equals("+")) {
                System.out.println(fracA.add(fracB).toString());
            } else if (operand.equals("-")) {
                System.out.println(fracA.subtract(fracB).toString());
            } else if (operand.equals("*")) {
                System.out.println(fracA.multiply(fracB).toString());
            } else if (operand.equals("/")) {
                System.out.println(fracA.divide(fracB).toString());
            } else if (operand.equals("=")) {
                System.out.println(fracA.equals(fracB));
            }

        }
    }
    // it prints introduction
    public static void introduction() {
        System.out.println("This program is fraction calculator");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit");
        System.out.println("-------------------------------------------------------------------------------");
    }

    // asks the user to enter valid mathematical operation.
    // if invalid, it re-prompts question until there is valid input.
    // "Q" terminates the program
    public static String getOperation() {
        System.out.print("Please enter a operation (+, -, /, *, = or Q to quit): ");
        String operand = input.next();

        while ( (!operand.equals("+")) && (!operand.equals("-")) && (!operand.equals("/")) &&
                (!operand.equals("*")) && (!operand.equals("=")) && (!operand.equals("Q")) ) {
            System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");
            operand = input.next();
        }
        if (operand.equals("Q")) {
            System.exit(0);
        }
        return operand;
    }

    // it prompts the user for a String that is fraction.
    // if user enters anything that is not a valid Fraction, it re-prompts until it is valid
    public static Fraction getFraction() {
        System.out.print("Please enter a fraction (a/b) or integer (a): ");
        String fracInput = input.next();
        while (!validFraction(fracInput)) {
            System.out.print("Invalid fraction. Please enter (a/b) or (a), "+
                    "where a and b are integers and b is no a zero: ");
            fracInput = input.next();
        }
        String[] fracStr = fracInput.split("/",2);
        if (fracStr.length == 1) {
            return new Fraction(Integer.parseInt(fracStr[0]));
        } else {
            return new Fraction(Integer.parseInt(fracStr[0]), Integer.parseInt(fracStr[1]));
        }
    }

    // returns true if the parameter is in the form "a/b"
    // where a and b is any int
    public static boolean validFraction(String fracInput) {
        boolean isValid = false;
        String[] fracStr = fracInput.split("/",2);

        for (int i = 0; i < fracStr.length; i++) {
            if (!fracStr[i].equals("")) {
                if (isNumber(fracStr[i])) {
                    isValid = true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return isValid;
    }

    // returns true is String contains only numbers
    // minus is allowed as first character
    public static boolean isNumber(String a) {
        boolean isNumber = true;
        // checks if String contains only digits 0-9. First character can be '-'
        if (!a.matches("-?\\d+")) {
            isNumber = false;
        }
        // return false if missing number
        if (a.charAt(0) == '-' && a.length() == 1) {
            isNumber = false;
        }
        return isNumber;
    }
}
