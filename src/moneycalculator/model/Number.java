package moneycalculator.model;

public class Number {
    private long numerator;
    private long denominator;

    public Number (long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        reduce();
    }


    public Number (long number) {
        numerator = number;
        denominator = 1;
    }
    
    public Number (Number number){
        this.numerator = number.numerator;
        this.denominator = number.denominator;
    }
    
    public Number (double number){
        this(numberFromDouble(number));
    }

    public static Number numberFromDouble (double number) {
        long denominatorContainer = 1;
        while(number != (long) number){
            number *= 10;
            denominatorContainer *= 10;
        }
        return new Number((long) number, denominatorContainer);
    }

    private int[] getPrimeNumbers(){
        return new int[]{2,3,5,7,11,13,17,19,23};
    }
    
    private void reduce() {
        int[] primes = getPrimeNumbers();
        for (int divisor : primes) {
            if(numerator < divisor) break;
            if(denominator < divisor) break;
            divide(divisor);
        }
    }

    private void divide(int divisor) {
        while(numerator % divisor == 0 && denominator % divisor == 0){
            numerator /= divisor;
            denominator /= divisor;
        }
    }
    
    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }
    
    public static Number valueOf (String value) throws NumberFormatException{
        return new Number (Double.valueOf(value));
    }
    
    @Override
    public String toString() {
        if (denominator == 1) {
            return numerator + "";
        }
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        
        final Number other = (Number) obj;
        
        if (this.numerator != other.numerator) return false;
        if (this.denominator != other.denominator) return false;
        return true;
    }

    public Number multiplicate(double exchangeRate) {
        return multiplicate(new Number(exchangeRate));
    }
    
    public Number multiplicate(Number exchangeRate) {
        long num = this.numerator;
        long den = this.denominator;
        num *= exchangeRate.numerator;
        den *= exchangeRate.denominator;
        return new Number(num, den);
    }
    
    public Number divide(double exchangeRate) {
        return divide(new Number(exchangeRate));
    }
    
    public Number divide(Number exchangeRate) {
        this.numerator *= exchangeRate.denominator;
        this.denominator *= exchangeRate.numerator;
        reduce();
        return new Number(this.numerator, this.denominator);
    }
    
    
    
    
}