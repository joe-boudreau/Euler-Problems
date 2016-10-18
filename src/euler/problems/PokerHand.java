/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euler.problems;

import java.util.*;

public class PokerHand {
    // Hearts = 0 , Diamonds = 1, Spades = 2, Clubs = 3
    private int[] card1, card2, card3, card4, card5;
    
    public PokerHand(String initC1, String initC2, String initC3, String initC4, String initC5){
        card1 = StrToIntArray(initC1);
        card2 = StrToIntArray(initC2);
        card3 = StrToIntArray(initC3);       
        card4 = StrToIntArray(initC4);
        card5 = StrToIntArray(initC5);       
    }
    
    public int[] getCard1(){
        return card1;
    }
    
    public int[] getCard2(){
        return card2;
    }
      
    public int[] getCard3(){
        return card3;
    }
    
    public int[] getCard4(){
        return card4;
    }
    
    public int[] getCard5(){
        return card5;
    }
    
    public int[] getHandType(){
        //Royal Flush = 9, Straight Flush = 8, Four of a Kind = 7, Full House = 6, Flush = 5, Straight = 4, Three of a kind = 3, Two Pair = 2, Pair = 1, High Card = 0
        boolean[] TypeExists = {true,false,false,false,false,false,false,false,false,false};
        int[] highest = {0,0,0,0,0,0,0,0,0,0};
        
        int[] values = {card1[0], card2[0], card3[0], card4[0], card5[0]};
        int[] suits = {card1[1], card2[1], card3[1], card4[1], card5[1]};
        
        Arrays.sort(values);
        Arrays.sort(suits);
        
        highest[0] = values[4]; //Assign highest card value in hand to the "high card" value 
        
        TypeExists[4] = values[4] - values[3] == 1 && values[3] - values[2] == 1 && values[2] - values[1] == 1 && values[1] - values[0] == 1; //straight
        highest[4] = values[4];
        TypeExists[5] = suits[4] - suits[0] == 0; //flush
        highest[5] = values[4];
        
        if(TypeExists[5] && TypeExists[4]){
            if(values[4] == 14){ //highest card in straght is Ace
                return new int[]{9,14}; //royal flush
            }
            return new int[]{8,values[4]};  //straight flush
        }
        
        //This for loop will check for pair, two pair, three of a kind, full house, and four of a kind
        for(int i = 1; i < 5; i++){
            if (values[i] == values[i-1]){
                
                if(TypeExists[1] && values[i] != highest[1]){ //check if there is already a pair found, raise two pair flag if true
                    TypeExists[2] = true;
                    highest[2] = values[i] > highest[1]? values[i] : highest[1];
                } 
                if(i<4 && values[i] != values[i+1]){ //make sure it is not actually three of a kind
                    highest[1] = values[i];
                    TypeExists[1] = true;
                }
                else if(i == 4){
                    highest[1] = values[i];
                    TypeExists[1] = true;
                }
                if(TypeExists[3] && values[i] != highest[3]){
                    TypeExists[6] = true;
                    highest[6] = highest[3];
                    
                }
            }
            
            if(i>1 && values[i] == values[i-2]){ 
                TypeExists[3] = true;
                highest[3] = values[i];
                if(TypeExists[1] && highest[1] != values[i]){
                    TypeExists[6] = true;
                    highest[6] = highest[3];
                }
            }
            if(i>2 && values[i] == values[i-3]){ 
                TypeExists[7] = true;
                highest[7] = values[i];   
        }
        }
        
        for (int j = 7;j>=0;j--){
            if(TypeExists[j]){
                return new int[]{j,highest[j]};
            }
        }
        return new int[]{0,2}; //so Java shuts up on me. will never happen
    }
    
    public boolean betterThan(PokerHand Hand2){
        int[] Hand1Type = this.getHandType();
        int[] Hand2Type = Hand2.getHandType();
        int i;
        if(Hand1Type[0]>Hand2Type[0]){return true;}
        else if (Hand2Type[0] > Hand1Type[0]){ return false;}
        else{
        int[] H1values = {card1[0], card2[0], card3[0], card4[0], card5[0]};
        int[] H2values = {Hand2.getCard1()[0], Hand2.getCard2()[0], Hand2.getCard3()[0], Hand2.getCard4()[0], Hand2.getCard5()[0]};
        Arrays.sort(H1values);
        Arrays.sort(H2values);
            switch (Hand1Type[0]){
                case 0: //high card
                    i = 4;
                    while(i>-1){
                        if(H1values[i]>H2values[i]){ return true;}
                        if(H1values[i]<H2values[i]){ return false;}
                        i--;
                    }
                
                case 1:  //one pair
                case 3: //three of a kind
                case 6: //full house
                case 7: //four of a kind
                    if(Hand1Type[1]>Hand2Type[1]){return true;}
                    else if (Hand2Type[1] > Hand1Type[1]){ return false;}
                    else{
                        i = 4;
                        while(i>-1){
                            if(H1values[i]>H2values[i]){ return true;}
                            if(H1values[i]<H2values[i]){ return false;}
                            i--;
                        }
                    }
                
                case 2: //two pair
                    if(Hand1Type[1]>Hand2Type[1]){return true;}
                    else if (Hand2Type[1] > Hand1Type[1]){ return false;}
                    else{
                        int H12ndpair = 0, H22ndpair = 0;
                        for(int j = 1; j < 5; j++){
                            if (H1values[j] == H1values[j-1] && H1values[j] != Hand1Type[1]){
                                H12ndpair = H1values[j];
                            }
                            if (H2values[j] == H2values[j-1] && H2values[j] != Hand1Type[1]){
                                H22ndpair = H2values[j];
                            }
                        }
                        if(H12ndpair>H22ndpair){return true;}
                        else if (H22ndpair > H12ndpair){ return false;}
                        else{
                            i = 4;
                            while(i>-1){
                                if(H1values[i]>H2values[i]){ return true;}
                                if(H1values[i]<H2values[i]){ return false;}
                                i--;
                            }
                        }
                    }

                case 4: //straight
                case 5: //flush
                case 8: //royal flush
                    if(Hand1Type[1]>Hand2Type[1]){return true;}
                    else if (Hand2Type[1] > Hand1Type[1]){ return false;}
                default:
                    return true;            
        } 
    }
}

    private int[] StrToIntArray(String card){
        int[] cardInt = new int[2];
        char[] split = card.toCharArray();
        if (split[0] < 58){
            cardInt[0] = split[0]-48;
        }
        else{
            switch (split[0]){
                case 'T':
                    cardInt[0] = 10;
                    break;
                case 'J':
                    cardInt[0] = 11;
                    break;
                case 'Q':
                    cardInt[0] = 12;
                    break;
                case 'K':
                    cardInt[0] = 13;
                    break;
                case 'A':
                    cardInt[0] = 14;
                    break;    
                default:
                    cardInt[0] = 14;
            }
        }
            switch (split[1]){
                case 'H':
                    cardInt[1] = 0;
                    break;
                case 'D':
                    cardInt[1] = 1;
                    break;    
                case 'S':
                    cardInt[1] = 2;
                    break;    
                case 'C':
                    cardInt[1] = 3;
                    break;       
            }
        return cardInt;
        }
    
   
    }

