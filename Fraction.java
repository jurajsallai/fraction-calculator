/*************************************************************
 * edX - Microsoft DEV277x Object Oriented Programming in Java
 * Module 2 Project - FRACTION CALCULATOR
 * Author: Juraj Sallai
 * 15/2/2018
 * Class FRACTION
 *************************************************************/

import java.util.InputMismatchException;

class Fraction {

    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {

        if (denominator == 0) {
            throw new IllegalArgumentException("Division by zero");
        }

        if (denominator < 0) {
            this.numerator = numerator * -1;
            this.denominator = denominator * -1;
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    public Fraction(int numerator) {
        this(numerator,1);
    }

    public Fraction() {
        this(0,1);
    }

    // exposes the value of the numerator field to the user
    public int getNumerator() {
        return numerator;
    }

    // exposes the value of the denominator field to the user
    public int getDenominator() {
        return denominator;
    }

    // 	returns "numerator/denominator", a String representation of the Fraction
    public String toString() {
        return numerator + "/" + denominator;
    }

    // returns the result of numerator/denominator
    public double toDouble() {
        return (double) numerator/denominator;
    }

    // returns a new Fraction that is the sum of other and this fractions
    public Fraction add(Fraction other) {
        int gcd = gcd(this.denominator, other.denominator);
        int newDenominator = (this.denominator * other.denominator)/gcd;
        int newNumerator = (newDenominator/this.denominator)*this.numerator + (newDenominator/other.denominator)*other.numerator;
        Fraction f = new Fraction(newNumerator, newDenominator);
        return f;
    }

    // returns a new Fraction that is the difference between the other and this fraction
    public Fraction subtract(Fraction other) {
        int gcd = gcd(this.denominator, other.denominator);
        int newDenominator = (this.denominator * other.denominator)/gcd;
        int newNumerator = (newDenominator/this.denominator)*this.numerator - (newDenominator/other.denominator)*other.numerator;
        Fraction f = new Fraction(newNumerator, newDenominator);
        return f;
    }

    // returns a new Fraction that is the product of the other and this fraction
    public Fraction multiply(Fraction other) {
        int newDenominator = this.denominator * other.denominator;
        int newNumerator = this.numerator * other.numerator;
        Fraction f = new Fraction(newNumerator, newDenominator);
        return f;

    }

    // returns a new Fraction that is the division of the other and this fraction
    // throw an IllegalArgumentException() if the user tries to divide by 0
    public Fraction divide(Fraction other) {
        int newDenominator = this.denominator * other.numerator;
        if (newDenominator == 0) {
            throw new IllegalArgumentException("Division by zero");
        }
        int newNumerator = this.numerator * other.denominator;
        Fraction f = new Fraction(newNumerator, newDenominator);
        return f;
    }

    // checks if two fractions are equal
    //takes "Object" as parameter to properly override the Object class's equals method
    public boolean equals(Object other) {
        if (other instanceof Fraction) {
            Fraction otherF = (Fraction) other;
            otherF.toLowestTerms();
            this.toLowestTerms();

            if ( (this.numerator == otherF.numerator) && (this.denominator == otherF.denominator) ) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new InputMismatchException("Fraction expected");
        }
    }

    // converts the current fraction to the lowest terms
    public void toLowestTerms() {
        int gcd = gcd(this.numerator,this.denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;
    }

    // takes in two ints and determines the Greatest Common Divisor using Euclidean Algorithm
    // returns positive value of greatest common divisor
    private static int gcd(int numerator, int denominator) {
        if (denominator == 0) {
            if (numerator < 0) {
                return numerator*-1;
            } else {
                return numerator;
            }
        }
        return gcd(denominator, numerator % denominator);
    }
}
