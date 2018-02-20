
/***********************************************************************
 * edX - Microsoft DEV277x Object Oriented Programming in Java
 * Module 2 Project - FRACTION CALCULATOR
 * Author: Juraj Sallai
 * 20/2/2018
 * Class FractionCalculatorAdvanced
 * User enters expression in single line in following format:
 * [FRACTION] [OPERATION] [FRACTION]. Program returns result.
 * EXAMPLE input 1/4 + 2/5, returns 13/20.
 * Allowed operations are addition, subtraction, multiplication, division
 * and equals, which checks if fractions are equal.
 ***********************************************************************/

import java.util.*;

class FractionCalculatorAdvanced {

    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        introduction();
        boolean cont = true;
        while (cont) {

            // asks to enter expression in format [FRAC] [OPERATION] [FRAC]
            String exp = input.nextLine();

            // splits expression string
            String[] expArr = exp.split("\\s+",3);

            // if user enters Q program ends
            if (isQuit(expArr)) {
                System.exit(0);
            }

            // checks if not too few expressions
            if (expArr.length == 3) {

                String firstFrac = expArr[0];
                String operand = expArr[1];
                String secondFrac = expArr[2];

                // checks if expressions are valid
                if (isValidFraction(firstFrac) && isValidOperand(operand) && isValidFraction(secondFrac)) {
                    Fraction fracA = insertFraction(firstFrac);
                    Fraction fracB = insertFraction(secondFrac);

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
                } else {
                    System.out.println("Invalid operation. Must be [FRAC] [OPERATION] [FRAC] :");
                }
            } else {
                System.out.println("Invalid operation. Must be [FRAC] [OPERATION] [FRAC] :");
            }
        }
    }

    // prints introduction
    public static void introduction() {
        System.out.println("This program is fraction calculator");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit");
        System.out.println("Valid operations are of the form \"[FRAC] [OPERATION] [FRAC]\"");
        System.out.println("[FRAC] can be either a single integer or two integers separately");
        System.out.println("[OPERATION] can be +, -, *, / or =");
        System.out.println("-------------------------------------------------------------------------------");
    }

    // returns true if user enters Q
    public static boolean isQuit(String[] expArr) {
        if (expArr.length == 1 && expArr[0].equals("Q")) {
            return true;
        }
        return false;
    }

    // checks if user entered valid operand
    public static boolean isValidOperand(String operand) {
        if ( (!operand.equals("+")) && (!operand.equals("-")) && (!operand.equals("/")) &&
                (!operand.equals("*")) && (!operand.equals("=")) ) {
            return false;
        }
        return true;
    }

    // checks if user entered valid fraction
    public static boolean isValidFraction(String fracInput) {
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

    // checks if fraction contains only digit
    // first char can be minus
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

    // converts string fraction to integer
    // returns as Fraction
    public static Fraction insertFraction(String fracInput) {

        String[] fracStr = fracInput.split("/",2);
        if (fracStr.length == 1) {
            return new Fraction(Integer.parseInt(fracStr[0]));
        } else {
            return new Fraction(Integer.parseInt(fracStr[0]), Integer.parseInt(fracStr[1]));
        }
    }
}
