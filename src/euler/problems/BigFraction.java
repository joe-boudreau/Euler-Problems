/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euler.problems;

import java.math.*;
/**
 *
 * @author Joe B
 */
public class BigFraction {

    private BigInteger num;
    private BigInteger den;
    private float value;
    
    public BigFraction(String initNum, String initDen){
        num = new BigInteger(initNum);
        den = new BigInteger(initDen);
        value = Float.valueOf(num.divide(den).toString());           
        
    }
    
    public BigInteger getNumerator(){
        return num;
    }
    
    public BigInteger getDenominator(){
        return den;
    }
    
    public float getValue(){
        return value;
    }
    
    public BigFraction multiply(BigFraction f){
        BigFraction product = new BigFraction(num.multiply(f.num).toString(),den.multiply(f.den).toString());
        product.reduceFraction();
        return product;
    }
    
    public BigFraction add(BigFraction f){
        BigFraction sum = new BigFraction(num.multiply(f.den).add(f.num.multiply(den)).toString(),den.multiply(f.den).toString());
        sum.reduceFraction();
        return sum;
    }
    
    public BigFraction divide(BigFraction f){
        BigFraction quotient = new BigFraction(num.multiply(f.den).toString(),den.multiply(f.num).toString());
        quotient.reduceFraction();
        return quotient;
    }
    
    public void reduceFraction(){
        if (num.mod(den).toString().equals("0")){
            num = num.divide(den);
            den = den.divide(den);
        }
        else{
            BigInteger BigGCD = num.gcd(den);
            num = num.divide(BigGCD);
            den = den.divide(BigGCD);
        }
    }
    
    public boolean equals(BigFraction f){
        if(this != null && f != null){
            return (num.multiply(f.den).subtract(den.multiply(f.num)).toString().equals("0"));
        }
        else{
            return false;
        }
    }
    
    public void printString(){
        System.out.println(num.toString()+" / "+den.toString());
    }
    
    
}
