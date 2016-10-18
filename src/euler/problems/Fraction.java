/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euler.problems;

/**
 *
 * @author Joe B
 */
public class Fraction {

    private long num;
    private long den;
    private float value;
    
    public Fraction(long initNum, long initDen){
        num = initNum;
        den = initDen;
        value = (float) initNum/initDen;
        
    }
    
    public long getNumerator(){
        return num;
    }
    
    public long getDenominator(){
        return den;
    }
    
    public float getValue(){
        return value;
    }
    
    public boolean isCurious(){
        if(num==den){
            return false;
        }
        if (num >= 10 && num < 100 && den >= 10 && den < 100 && !(num%10 == 0 && den%10==0)){
            int num1stDig = (int) (num - num%10)/10;
            int den1stDig = (int) (den - den%10)/10;
            return (num%10 == den%10 || num1stDig == den%10 || num%10 == den1stDig || num1stDig == den1stDig);
        }
        return false;
    }
    
    public Fraction getCuriousReduced(){
        if (this.isCurious()){
            long num1stDig = (long) (num - num%10)/10;
            long den1stDig = (long) (den - den%10)/10;
            long num2ndDig = num%10;
            long den2ndDig = den%10;
            
            if(num1stDig == den1stDig){
                return new Fraction(num2ndDig,den2ndDig);
            }
            if(num1stDig == den2ndDig){
                return new Fraction(num2ndDig,den1stDig);
            }
            if(num2ndDig == den1stDig){
                return new Fraction(num1stDig,den2ndDig);
            }
            else{
                return new Fraction(num1stDig,den1stDig);
            }    
        }
        else{
            return null;
        }
    }
    
    public Fraction multiply(Fraction f){
        Fraction product = new Fraction(num*f.num,den*f.den);
        product.reduceFraction();
        return product;
    }
    
    public Fraction add(Fraction f){
        //f.reduceFraction();
        Fraction sum = new Fraction(num*f.den+f.num*den,den*f.den);
        sum.reduceFraction();
        return sum;
    }
    
    public Fraction divide(Fraction f){
        Fraction quotient = new Fraction(num*f.den,den*f.num);
        quotient.reduceFraction();
        return quotient;
    }
    
    public void reduceFraction(){
        if (num%den==0){
            num /= den;
            den = 1;
        }
        else{
            int[] numDivisors = EulerProblems.divisors(num);
            int[] denDivisors = EulerProblems.divisors(den);
            int gcd = 1;
            for(int n : numDivisors){
                for(int d : denDivisors){
                    gcd = (n == d)&&(n > gcd) ? n : gcd;
                }
            }
            num /= gcd;
            den /= gcd;
        }
    }
    
    public boolean equals(Fraction f){
        if(this != null && f != null){
            return (this.num*f.den - this.den*f.num == 0);
        }
        else{
            return false;
        }
    }
    
    public void printString(){
        System.out.println(String.valueOf(num)+" / "+String.valueOf(den));
    }
    
    
}
