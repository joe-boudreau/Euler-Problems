package euler.problems;

import java.io.*;
import static java.lang.Math.*;
import java.util.*;
import java.math.*;
import euler.problems.Fraction;
import euler.problems.PokerHand;
public class EulerProblems {
    
    
    public static void main(String[] args) throws IOException {
    //Problem1();   
        //Problem2();
        //Problem3();
        //Problem4();   
        //Problem5();  
        //Problem6(); 
        //Problem7();
        //Problem8();
        //Problem9();
        //Problem10();
        //Problem11();
        //Problem12();
        //Problem13();
        //Problem14();    
        //Problem15();
        //Problem16();
        //Problem17();
        //Problem18();
        //Problem19();
        //Problem20();
        //Problem21();
        //Problem22();
        //Problem23();
        //Problem24();
        //Problem25();
        //Problem26();
        //Problem27();
        //Problem28();
        //Problem29();
        //Problem30();
        //Problem31();
        //Problem32();
        //Problem33();
        //Problem34();
        //Problem35();
        //Problem36();
        //Problem37();
        //Problem40();
        
        //Problem41();
        //Problem42();    
        //Problem43();
        //Problem44();
        //Problem45();
        //Problem46();
        //Problem47();
        //Problem48();
        //Problem49();
        //Problem50();
        //Problem51();
        //Problem52();
        //Problem53();
        //Problem54();
        //Problem55();
        //Problem56();
        //Problem57();
        //Problem58();
        //Problem59();
        Problem60();
        //Problem67();
        
        /*
        List<Integer[]> bp = new ArrayList<>();
        bp.add(new Integer[]{0,1});
        bp.add(new Integer[]{2,3});
        bp.add(new Integer[]{5,8});
        
        int[] low = {0,1,2,3,5};
        System.out.println(containsBadPair(bp,low));
        */
        }
    
    public static void Problem60(){
        int[] primes = generateNprimes(50002);
        int[] primesOpt = new int[50000];
        primesOpt[0] = 3; primesOpt[1] = 7;
        System.arraycopy(primes, 4, primesOpt, 2, 49998);

        
        
        int[] current5Set = {0, 0, 0, 0, 0};
        current5Set[0] = primesOpt[0];
        int pairsFound = 0, i = 0, j;
        long concat1, concat2;
        boolean playsNice;
        while(current5Set[4] == 0){
            i++;
            j = 0;
            playsNice = true;
            while(current5Set[j] != 0){
            concat1 = Long.parseLong(current5Set[j]+""+primesOpt[i]);
            concat2 = Long.parseLong(primesOpt[i]+""+current5Set[j]);
                if(!isPrime(primes,concat1) || !isPrime(primes,concat2)){
                    playsNice = false;
                    break;
                }
                j++;
            }
            if(playsNice){
                current5Set[j] = primesOpt[i];
            }
        }
        
        for(int c : current5Set){
            System.out.print(c + " ");
        }
        System.out.println("");
        
    }
    
    public static boolean containsBadPair(List<Integer[]> BadPairs, int[] set){
    int[][] allPairs = subSet(set,2);
    for(int[] pair : allPairs){
        for(int j = 0; j < BadPairs.size();j++){
            int[] badpair = {BadPairs.get(j)[0],BadPairs.get(j)[1]};
            if(Arrays.equals(pair,badpair))
            return true;
        }
    }
    return false;
    }
    public static int[][] subSet(int[] numbs, int setLen){
        if(setLen == 1){
            int[][] sets = new int[numbs.length][1];
            for(int i = 0; i < numbs.length; i++){
                sets[i][0] = numbs[i];
            }
            return sets;
        }
        else{
            int[][] sets = new int[(int)Choose(numbs.length,setLen)][setLen];
            
            int index = 0;
            for(int i = 0; i <= numbs.length-setLen; i++){
                int[] leftover = new int[numbs.length-(i+1)];
                System.arraycopy(numbs,(i+1),leftover,0,numbs.length-(i+1));
                
                for(int[] subsubs : subSet(leftover,setLen-1)){
                     sets[index][0] = numbs[i];
                     System.arraycopy(subsubs,0,sets[index],1,setLen-1);
                     index++;
                }
            }
            return sets;
        }
        }    
    
    
    public static int[][] badpairsinset(int[] primeList, int[] p){
        long concat1, concat2;
        List<Integer[]> badPairs = new ArrayList<>();
        for(int i = 0; i<4; i++){
            for(int j = i+1; j<5; j++){
                concat1 = Long.parseLong(p[i]+""+p[j]);
                concat2 = Long.parseLong(p[j]+""+p[i]);
                if(!isPrime(primeList,concat1) || !isPrime(primeList,concat2)){
                    badPairs.add(new Integer[]{p[i],p[j]});
                }
            }
        }
        int[][] bpArray = new int[badPairs.size()][2];
        for(int k = 0;k<badPairs.size(); k++){
            bpArray[k][0] = badPairs.get(k)[0];
            bpArray[k][1] = badPairs.get(k)[1];
        }
        return bpArray;
    }
    
    public static void Problem59() throws IOException{
    try (FileReader FileMsg = new FileReader("C:/Users/Joe B/Documents/p059_cipher.txt")) {
        BufferedReader Message = new BufferedReader(FileMsg);
        List<Integer> characters = new ArrayList<>();
        int d1, d2;
        while(Message.ready()){
        d1 = Message.read();
        d2 = Message.read();
        if(d2 == 44){ //if second character is a comma, its a 1 digit number
            characters.add(d1-48);
        }
        else{
            characters.add((d1-48)*10+(d2-48)); //its a two digit number
            Message.skip(1);
        }
        }
        Integer[] Chars = new Integer[characters.size()];
        characters.toArray(Chars);
        
        int[] key = {97, 97, 97};
        int totalChars = Chars.length;
        int[] decryptedChars = new int[totalChars];
        
        while(key[0] !=  122){
            for(int i = 0; i < totalChars; i++){
                decryptedChars[i] = Chars[i]^key[i%3];
            }
            //System.out.println(decryptedChars[0]);
            if(isDecrypted(decryptedChars)){
                break;
            }
            key[2]++;
            if(key[2] == 122){
                key[1]++;
                key[2] = 97;
            }
            if(key[1] == 122){
                key[0]++;
                key[1] = 97;
            }
        }
        
        String msg = "";
        int sum = 0;
        for(int x : decryptedChars){
        msg = msg + Character.toString((char)x);   
        sum+=x;
        }
        System.out.println(sum);
        
    }
    }
    
    public static boolean isDecrypted(int[] chars){
        int[] the = {32, 116, 104, 101, 32};
        int[] and = {32, 97, 110, 100, 32};
        int[] check5;
        int startingchar = 0;
        boolean foundThe = false, foundAnd = false;
        while(startingchar < chars.length-1){
        check5 = Arrays.copyOfRange(chars,startingchar,startingchar+5);   
        if(Arrays.equals(the,check5)){
            foundThe = true;
        }
        if(Arrays.equals(and,check5)){
            foundAnd = true;
        }
        if(foundThe && foundAnd){
            break;
        }
        startingchar++;
        }
        return (foundThe && foundAnd);
    }
    
    public static void Problem58(){
        int[] PA = generateNprimes(10000);
        int x = 1, jump = 2, primes = 0, spirals = 0;
        double ratio = 1.0;
        while (ratio>0.1){
            for(int i = 1; i<5; i++){
            if(isPrime(PA,x+i*jump)){
                primes++;
            }    
            }
            x+=4*jump;
            jump+=2;
            spirals++;
            System.out.println(primes);
            ratio = (double) primes/(1+spirals*4);
            System.out.println(ratio);
        }
        System.out.println(1+spirals*2);
    }
    
    public static void Problem57(){
    int expansion = 7, total = 0;
    BigFraction x;
    while(expansion<1000){
        x = continuedFraction("1","2",expansion);
        if(x.getNumerator().toString().length() > x.getDenominator().toString().length()){
            total++;
        }
        expansion++;
    }
    System.out.println(total);
      
    }
    
    public static BigFraction continuedFraction(String a0, String b0, int terms){
       if(terms == 0){
           return new BigFraction(a0,"1").add(new BigFraction("1",b0));
       }
       BigFraction x = new BigFraction("1","1").divide(continuedFraction("2","2",--terms));
       
       return new BigFraction(a0,"1").add(x);
    }
    
    
    
    public static void Problem56(){
    int biggestSum = 0, x;
    
    for(int a = 1; a < 100;a++){
        for(int b = 1;b < 100;b++){
            x = BigIntDigitSum(new BigInteger(String.valueOf(a)).pow(b));
            if(x > biggestSum){
                biggestSum = x;
            }
        }
    }
    System.out.println(biggestSum);
    }
    
    public static int BigIntDigitSum(BigInteger BI){
        int sum = 0;
        for (char c : BI.toString().toCharArray()){
            sum+=Character.getNumericValue(c);
        }
        return sum;
    }
    
    public static void Problem55(){
    int x = 1, Lychrels = 0;
    while(x<10000){
        if(isLychrel(new BigInteger(String.valueOf(x)),0)){
            Lychrels++;
        }
        x++;
    }
    System.out.println(Lychrels);
    }
    
    public static boolean isLychrel(BigInteger BI, int attempts){
        if(attempts > 0 && isBigIntPalindrome(BI)){
            return false;
        }
        else if(attempts == 50){
            return true;
        }
        else{
            return isLychrel(BI.add(new BigInteger(reverse(BI.toString()))),++attempts);
        }
    }
    
    public static boolean isBigIntPalindrome(BigInteger BI){
        return BI.equals(new BigInteger(reverse(BI.toString())));
    }
    
    public static String reverse(String s){
        String reverse = "";
        for (int i = s.length() - 1; i>=0; i--){
            reverse = reverse + s.charAt(i);
        }
        return reverse;
    }
    
    public static void Problem54()  throws IOException {
        try (FileReader pokerhands = new FileReader("C:/Users/Joe B/Documents/p054_poker.txt")) {
            BufferedReader hands = new BufferedReader(pokerhands);
        int totalhands = 0, P1Wins = 0;
        String CurrentTwoHands;
        PokerHand Player1Hand, Player2Hand;
        String[] P1Cards = new String[5], P2Cards = new String[5];
        int[] eachHand = {0,0,0,0,0,0,0,0,0};
        while(totalhands<1000){
            CurrentTwoHands = hands.readLine();
            for(int i = 0; i < 5; i++){
                P1Cards[i] = CurrentTwoHands.substring(i*3,i*3+2);
                P2Cards[i] = CurrentTwoHands.substring(i*3+15,i*3+17);
                
            }
            Player1Hand = new PokerHand(P1Cards[0], P1Cards[1], P1Cards[2], P1Cards[3], P1Cards[4]);
            Player2Hand = new PokerHand(P2Cards[0], P2Cards[1], P2Cards[2], P2Cards[3], P2Cards[4]);
            eachHand[Player1Hand.getHandType()[0]]++;
            
            if(Player1Hand.betterThan(Player2Hand)){
                P1Wins++;
            }
            totalhands++;
        }
        System.out.println(P1Wins);
        for(int g : eachHand){
            System.out.println(g);
        }    
            
            
    }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
   
    public static void Problem53(){
        long n = 2, GT1Mil = 0, k = 1;
        while(n<=100){
            while(Choose(n,k)<1000000){
                k++;
                if(k==n){
                    n++;
                    k = 1;
                }
            }
            if(n%2==0){
                GT1Mil+=((n)/2-k)*2+1;
            }
            else{
                GT1Mil+=((n+1)/2-k)*2;
            }
            n++;
            k = 1;
        }
        System.out.println(GT1Mil);
    }
    
    public static long Choose(long N, long k){
        if (k < 0 || k > N){
            return 0;
        }
        if (k == 1 || k == N){
            return 1;
        }
        long NCk = 1;
        k = k < N - k ? k : N - k;
        for (int i = 0; i < k; i++){
            NCk = NCk * (long)(N-i)/(i+1);
        }
        return NCk;
    }
    
    public static void Problem52(){
        long x = 1, x2, x3, x4, x5, x6;
        int[] xDig, x2Dig, x3Dig, x4Dig, x5Dig, x6Dig;
    while(true){
        x2 = x*2; x3 = x*3; x4 = x*4; x5 = x*5; x6 = x*6;
        xDig = numToDigits(x);
        Arrays.sort(xDig);
        x2Dig = numToDigits(x2);
        Arrays.sort(x2Dig);
        if(Arrays.equals(xDig, x2Dig)){
            x3Dig = numToDigits(x3);
            Arrays.sort(x3Dig);
            if(Arrays.equals(xDig, x3Dig)){
                x4Dig = numToDigits(x4);
                Arrays.sort(x4Dig);
                if(Arrays.equals(xDig, x4Dig)){
                    x5Dig = numToDigits(x5);
                    Arrays.sort(x5Dig);
                    if(Arrays.equals(xDig, x5Dig)){
                        x6Dig = numToDigits(x6);
                        Arrays.sort(x6Dig);
                        if(Arrays.equals(xDig, x6Dig)){
                            break;           
                        }
                    }
                }
            }
        }
        x++;
    }
    System.out.println(x);
    }
    
    public static void Problem51(){
    int[] primes = generateNprimes(10000);    
    long curInt = 56004;
    int[] curDigs, modDigs;
    boolean hasZero, hasOne, hasTwo;
    int place, notPrimeVariant;
    BREAKTHIS: while(true){
        if(isPrime(primes,curInt) && (curInt%10==3 || curInt%10==7 || curInt%10==9)){
            hasZero = false;
            hasOne = false;
            hasTwo = false;
            curDigs = numToDigits(curInt);
            for(int digit : curDigs){
                if(digit == 0){hasZero = true;}
                if(digit == 1){hasOne = true;}
                if(digit == 2){hasTwo = true;}
            }
            
            place = 1;
            notPrimeVariant = 0;
            if(hasZero){
                while(notPrimeVariant < 3){
                    if(place == 10){break BREAKTHIS;}
                    modDigs = curDigs.clone();
                    for(int i = 0; i < curDigs.length; i++){
                        if(curDigs[i] == 0){
                            modDigs[i] = place;
                        }
                    }
                    if(!isPrime(primes,digitsToNum(modDigs))){
                        notPrimeVariant++;
                    }
                place++;    
                }
            }
            
            place = 2;
            notPrimeVariant = 0;
            if(hasOne){
                while(notPrimeVariant < 2){
                    if(place == 10){break BREAKTHIS;}
                    modDigs = curDigs.clone();
                    for(int i = 0; i < curDigs.length; i++){
                        if(curDigs[i] == 1){
                            modDigs[i] = place;
                        }
                    }
                    if(!isPrime(primes,digitsToNum(modDigs))){
                        notPrimeVariant++;
                    }
                place++;    
                }                
            }
            
            place = 3;
            notPrimeVariant = 0;
            if(hasTwo){
                while(notPrimeVariant < 1){
                    if(place == 10){break BREAKTHIS;}
                    modDigs = curDigs.clone();
                    for(int i = 0; i < curDigs.length; i++){
                        if(curDigs[i] == 2){
                            modDigs[i] = place;
                        }
                    }
                    if(!isPrime(primes,digitsToNum(modDigs))){
                        notPrimeVariant++;
                    }
                place++;    
                }                
            }
            
        }
        curInt++;
    }
    System.out.println(notPrimeVariant);
    System.out.println(curInt);
    }
    
    public static void Problem50(){
    int[] primes = generateNprimes(79000);
    int consec = 0, startIndex = 0;
    long currentsum;
    while(consec <= 547){
        startIndex = 0;
        PRIMECHECK: while(startIndex+consec < 79000){
            currentsum = 0;
            for(int i = startIndex; i < startIndex+consec;i++){
                currentsum+=primes[i];
            }
            if(currentsum < 1000000 && isPrime(primes,currentsum)){
                System.out.println(consec);
                System.out.println(currentsum);
                break PRIMECHECK;
            }
            startIndex++;
        }
        consec++;
    }
    }
    
    
    public static void Problem49(){
        String[] allPerms;
        int[] aPI = new int[24], primes = {2};
        int place;
        EVERYTHING: for (int x = 1000; x < 10000; x++){
            if(isPrime(primes,x)){
                allPerms = perms(Integer.toString(x));
                Arrays.sort(allPerms);
                place = 0;
                for (int k = 0; k< 24; k++){
                    aPI[k] = Integer.valueOf(allPerms[k]);
                    if(aPI[k] == x){place = k;}
                }
                for (int i = place+1; i < 23; i++){
                    for(int j = i+1;j<24;j++){
                            if(isSequence(x,aPI[i],aPI[j]) && isPrime(primes,aPI[i]) && isPrime(primes,aPI[j])){
                                System.out.println(x);
                                System.out.println(aPI[i]);
                                System.out.println(aPI[j]);
                                //break EVERYTHING;
                            }
                        }
                    }
            }
        }
    
    }
    
    public static boolean isSequence(int first, int second, int third){
        return (third - second == second - first);
    }
    
    public static void Problem48(){
    BigInteger sum = new BigInteger("1");
    int i = 2;
    while(i<=1000){
        sum = sum.add(new BigInteger(Integer.toString(i)).pow(i));
        i++;
    }
    String hugefuckingnumber = sum.toString();
    int len = hugefuckingnumber.length();
    System.out.println(len);
    System.out.println(hugefuckingnumber.substring(len-11,len));
    }
    
    public static void Problem47(){
        int[] Primes = generateNprimes(20000);
        long first = 647;
        boolean found = false;
        while(!found){
            if(getDistinctPrimeFactors(Primes,first).length == 4){
                if(getDistinctPrimeFactors(Primes,first+1).length == 4){
                    if(getDistinctPrimeFactors(Primes,first+2).length == 4){
                        if(getDistinctPrimeFactors(Primes,first+3).length == 4){
                            found = true;
                        }
                        else{first+=4;}
                    }
                    else{first+=3;}
                }
                else{first+=2;}
            }
            else{first++;}

        }
        System.out.println(first);
    }
    
    
    public static long[] getDistinctPrimeFactors(int[] primes, long x){
    ArrayList<Long> PF = new ArrayList<>();
    long remaining = x, currentPrime;
    int index = 0;
    while(remaining != 1){
        currentPrime = (long) primes[index];
        if(remaining%currentPrime == 0){
            remaining = (long) remaining/currentPrime;
            if(!PF.contains(currentPrime)){
                PF.add(currentPrime);
            }
            index = 0;
        }
        else{
            index++;
        } 
    }
    long[] DPF = new long[PF.size()];
    for (int i = 0; i<DPF.length;i++){
        DPF[i] = PF.get(i);
    }
    return DPF;
    }
    
    
    public static void Problem46(){
    int[] Primes = generateNprimes(10000);    
    int guess = 35;
    int x, y, goldbach;
    boolean found = false;
    while(!found){
        if(!isPrime(Primes, guess)){
            x = 0;
            found = true;
            PRIMELOOP: while(Primes[x] < guess){
                y = 1;
                goldbach = Primes[x] + 2*y*y;
                while(goldbach <= guess){
                    if(goldbach == guess){
                        found = false;
                        break PRIMELOOP;
                    } 
                    y++;
                    goldbach = Primes[x] + 2*y*y;
                }
                x++;
            }
        }
        guess+=2;
    }
    System.out.println(guess-2);
    }
    public static int[] generateNprimes(int N){
        int[] primearray = {2};
        int x = 3;
        while(primearray.length != N){
            if(isPrime(primearray,x)){
                int[] NewPrimes = new int[primearray.length + 1];
                System.arraycopy(primearray, 0, NewPrimes, 0, primearray.length);
                NewPrimes[primearray.length] = x;
                primearray = new int[NewPrimes.length];
                System.arraycopy(NewPrimes, 0, primearray, 0, primearray.length);
            }
            x+=2;
        }
        return primearray;
    }
    
    public static void Problem45(){
    long Tindex = 286, Tri = 40755 + Tindex;
    boolean done = false;
    while(!done){
        if(isPerfectSquare(24*Tri+1) && isPerfectSquare(8*Tri+1)){
            if(((long)sqrt(24*Tri+1)+1)%6 == 0 && ((long)sqrt(8*Tri+1)+1)%4 == 0){
                done = true;
                System.out.println(Tri);
            }
        }
        Tindex++;
        Tri = Tri + Tindex;
    } 
    }
    
    
    
    public static boolean isPerfectSquare(long n)
    {
        if (n < 0){
            return false;
        }
        switch((int)(n & 0xF))
        {
        case 0: case 1: case 4: case 9:
            long tst = (long)Math.sqrt(n);
            return tst*tst == n;

        default:
            return false;
        }
    }
    public static void Problem44(){
    int[] pentagons = new int[10000];
    pentagons[0] = 1;
    int D, smallestD = Integer.MAX_VALUE;
    for (int i = 1;i<10000;i++){
        pentagons[i] = pentagons[i-1] + 3*i+1;
    }
    
    for (int i = 10;i<9900;i++){
        for (int j = i-1; j>=0; j--){
            D = pentagons[i]-pentagons[j];
            if(Arrays.binarySearch(pentagons, pentagons[i]+ pentagons[j]) >= 0 && Arrays.binarySearch(pentagons, D) >= 0){
                if (D < smallestD){ smallestD = D;}
                System.out.println(pentagons[i]);
                System.out.println(pentagons[j]);
                System.out.println(D);
            }
        }
    }
    
    System.out.println(smallestD);
    }
    
    public static int Pn(int n){
        return n*(3*n-1)/2;
    }
    
    public static void Problem43(){
    String digits = "0123456789";
    String[] AllPandigits;
    long sum = 0;
    AllPandigits = Permutations(digits);
    for (String s : AllPandigits){
        if(Integer.valueOf(s.substring(7,10))%17 == 0){
            if(Integer.valueOf(s.substring(6,9))%13 == 0){
                if(Integer.valueOf(s.substring(5,8))%11 == 0){
                    if(Integer.valueOf(s.substring(4,7))%7 == 0){
                        if(Integer.valueOf(s.substring(3,6))%5 == 0){
                            if(Integer.valueOf(s.substring(2,5))%3 == 0){
                                if(Integer.valueOf(s.substring(1,4))%2 == 0){
                                    sum+= Long.valueOf(s);
                                }
                            }
                        }
                    }
                }
            }   
        }
    }
    
    System.out.println(sum);
    }
    
    public static String[] Permutations(String str){
            ArrayList<String> TotesPerms = new ArrayList<>();
            Permutations(TotesPerms,"",str);
            String[] Perms = new String[factorial(str.length())];
            TotesPerms.toArray(Perms);
            return Perms;
    }
       
    private static void Permutations(ArrayList<String> tP,String inPlace, String leftOver){
        int len = leftOver.length();
        if (len == 0){ tP.add(inPlace);}
        for(int i = 0; i< len;i++){
            Permutations(tP, inPlace+leftOver.charAt(i),leftOver.substring(0,i)+leftOver.substring(i+1,leftOver.length()));
        }   
        }
    
    public static void Problem41(){
    int numb = 2341, biggest = 2143;
    int[] primearray = {2};
    while (numb <= 987654321){
        if(isPandigital(numb)  && isPrime(primearray,numb)){
            System.out.println("Found one!");
            System.out.println(numb);
            biggest = numb;
        }
        numb+=2;
    }
    System.out.println(biggest);
    }
    
    public static void Problem37(){
        int p = 12, a, solns, mostSolns = 0, bigP = 0;
        
        while (p <= 1000){
            solns = 0;
            a = 1;
            while(a < ceil(p/2)){
                if (((int)pow(p,2) - 2*p*a)%(2*(p-a)) == 0){
                    solns++;
                }
                a++;
            }
            if(solns > mostSolns){
                mostSolns = solns;
                bigP = p;
            }
            p++;
        }
        System.out.println(mostSolns);
        System.out.println(bigP);
    }
    
    public static void Problem40(){
        int index = 2, product = 1, Current = 2, Previous = 1, offset;
        int[] Digits;
        int[] N = {10,100,1000,10000,100000,1000000};
        for (int i = 0; i<N.length;i++){
            while(index < N[i]){
            Previous = Current;
            Current++;
            index+= numberDigits(Current);       
            }
            System.out.println(index);
            System.out.println(Current);
            offset = index - N[i];
            Digits = numToDigits(Current);
            product *= Digits[Digits.length - (offset + 1)];
        }
        System.out.println(product);
    }
    
    public static void Problem36(){
    int test = 9182, biggest = 0, testPan;
    int[] first, second, both;
    while(test<10000){
        first = numToDigits(test);
        second = numToDigits(test*2);
        both = concat(first, second);
        testPan = digitsToNum(both);
        if(isPandigital(testPan) && testPan > biggest){
            biggest = testPan;     
        }
        test++;
    }
    System.out.println(biggest);
    }
    
    public static int numberDigits(long num){
        return String.valueOf(num).length();
    }
    
    public static int[] numToDigits(long num){
        char[] digChar = Long.toString(num).toCharArray();
        int[] digits = new int[digChar.length];
        for(int i = 0; i<digits.length;i++){
            digits[i] = digChar[i]-48;
        }
        return digits;
    }
    
    public static int digitsToNum(int[] digits){
        String numStr = "";
        for (int i : digits){
            numStr+=Integer.toString(i);
        }
        return Integer.valueOf(numStr); 
    }
    
    public static int[] concat(int[] a, int[] b) {
        int aLen = a.length;
        int bLen = b.length;
        int[] c = new int[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }
    
    public static void Problem1() {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum = sum + i;
            }
        }
        System.out.println(sum);
    }

    public static void Problem2() {
        int sum = 0, fibo1 = 1, fibo2 = 1;
        int temp;
        while (fibo2 < 4e6) {
            temp = fibo2;
            fibo2 = fibo2 + fibo1;
            fibo1 = temp;
            if (fibo2 % 2 == 0) {
                sum = sum + fibo2;
            }

        }
        System.out.println(sum);
    }

    public static void Problem3() {
        int[] primearray = {2};
        long x = 600851475143L;
        long divider = 2;
        long largest = 0;
        while (divider <= sqrt(x)) {
            if (x % divider == 0) {
                if (isPrime(primearray, divider)) {
                    largest = divider > largest ? divider : largest;
                }
                if (isPrime(primearray, x / divider)) {
                    largest = x / divider > largest ? x / divider : largest;
                }
            }
            divider++;
        }
        System.out.println(largest);
    }

    public static boolean isPrime(int[] PrimeList, long numb) {
        int divisor = PrimeList[0], index = 0;
        boolean prime = true, bruteforce = false;
        numb = abs(numb);
        if (numb == 1){ return false;}
        
        while (divisor * divisor <= numb) {
            if (!bruteforce) {
                divisor = PrimeList[index];
            }
            if (numb % divisor == 0) {
                return prime = false;
            }
            if (index < (PrimeList.length - 1)) {
                index++;
            } else {
                divisor++;
                bruteforce = true;
            }

        }
        return prime;
    }

    public static void Problem4() {
        int x = 999 * 999;
        int[][] threedigproducts = new int[900][900];
        int first = 99, second;
        for (int[] i : threedigproducts) {
            first++;
            second = 100;
            for (int j : i) {
                i[second - 100] = first * second;
                second++;
            }
        }
        while (x > 1e4) {
            if (isPalindrome(x) && isThreeDigitProduct(x, threedigproducts)) {
                System.out.println(x);
                break;
            }
            x--;
        }
    }

    public static boolean isThreeDigitProduct(long numb, int[][] products) {
        boolean answer = false;
        for (int i = 0; i < 900; i++) {
            for (int k = 0; k < 900; k++) {
                if (numb == products[i][k]) {
                    return answer = true;
                }
            }
        }
        return answer;
    }

    public static boolean isPalindrome(long numb) {
        boolean answer = true;
        Long numbox = numb;
        String number = numbox.toString();
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) != number.charAt(number.length() - (i + 1))) {
                return answer = false;
            }
        }
        return answer;
    }

    public static void Problem5() {
        long x = 2520;
        BIGLOOP:
        while (true) {
            for (int i = 1; i <= 20; i++) {
                if (x % i != 0) {
                    break;
                }
                if (i == 20) {
                    System.out.println(x);
                    break BIGLOOP;
                }
            }
            x++;
        }
    }

    public static void Problem6() {
        long sumsquares = 0, sum = 0;
        for (int i = 0; i <= 100; i++) {
            sumsquares += pow(i, 2);
            sum += i;
        }
        System.out.println((long) pow(sum, 2) - sumsquares);
    }

    public static void Problem7() {
        int[] primearray = {2};
        int x = 2;
        int numprimes = 1;
        while (numprimes < 10001) {
            if (isPrime(primearray, ++x)) {
                numprimes++;
            }
        }
        System.out.println(x--);
    }

    public static void Problem8() throws IOException {
        try (FileReader numberfile = new FileReader("C:/Users/Joe B/Documents/bignumber.txt")) {
            char[] x = new char[1000];
            numberfile.read(x);
            long biggestproduct = 1, newproduct;
            for (int i = 0; i < (1000 - 13); i++) {
                newproduct = 1;
                for (int j = 0; j < 13; j++) {
                    newproduct *= Character.getNumericValue(x[i + j]);
                }
                if (newproduct > biggestproduct) {
                    biggestproduct = newproduct;
                }
            }
            System.out.println(biggestproduct);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void Problem9() {
        int a, b, c = 998;
        BIGLOO:
        while (c >= 335) {
            a = 1;
            b = 1000 - c - a;
            while (a <= b) {
                if ((int) (pow(a, 2) + pow(b, 2)) == (int) pow(c, 2)) {
                    System.out.println(a * b * c);
                    break BIGLOO;
                }
                a++;
                b--;
            }
            c--;
        }
    }

    public static void Problem10() {
        long sum = 2;
        int x = 3;
        int[] primearray = {2};
        while (x < 2000000) {

            if (isPrime(primearray, x)) {
                sum += x;
                int[] NewPrimes = new int[primearray.length + 1];
                System.arraycopy(primearray, 0, NewPrimes, 0, primearray.length);
                NewPrimes[primearray.length] = x;
                primearray = new int[NewPrimes.length];
                System.arraycopy(NewPrimes, 0, primearray, 0, primearray.length);
            }
            x += 2;
        }
        System.out.println(sum);
    }

    public static void Problem11() throws IOException {
        try (FileReader numberfile = new FileReader("C:/Users/Joe B/Documents/twodigitgrid.txt")) {
            char[] fileChars = new char[1300];
            numberfile.read(fileChars);
            int[][] TwoDigGrid = new int[20][20];
            int x = 0, y = 0;
            String digString;
            LOOP1:
            for (int i = 0; i < fileChars.length; i++) {
                if (Character.isDigit(fileChars[i]) && Character.isDigit(fileChars[i + 1])) {
                    digString = Character.toString(fileChars[i]) + Character.toString(fileChars[i + 1]);
                    TwoDigGrid[x][y] = Integer.parseInt(digString);
                    x++;
                    if (x == 20) {
                        x = 0;
                        y++;
                        if (y == 20) {
                            break LOOP1;
                        }
                    }

                }
            }
            int[] candidates = new int[4];
            int biggest = 0;
            for (int i = 0; i < 19; i++) {
                for (int j = 0; j <= (19 - 3); j++) {
                    candidates[0] = TwoDigGrid[j][i] * TwoDigGrid[j + 1][i] * TwoDigGrid[j + 2][i] * TwoDigGrid[j + 3][i];
                    candidates[1] = TwoDigGrid[i][j] * TwoDigGrid[i][j + 1] * TwoDigGrid[i][j + 2] * TwoDigGrid[i][j + 3];
                    if (i <= 16) {
                        candidates[2] = TwoDigGrid[j][i] * TwoDigGrid[j + 1][i + 1] * TwoDigGrid[j + 2][i + 2] * TwoDigGrid[j + 3][i + 3];
                        candidates[3] = TwoDigGrid[i][19 - j] * TwoDigGrid[i + 1][19 - (j + 1)] * TwoDigGrid[i + 2][19 - (j + 2)] * TwoDigGrid[i + 3][19 - (j + 3)];
                    }
                    Arrays.sort(candidates);
                    if (candidates[3] > biggest) {
                        biggest = candidates[3];
                    }
                }
            }
            System.out.println(biggest);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void Problem12() {
        int triNum = 1, adder = 2;

        while (numDivisors(triNum) < 500) {
            triNum += adder;
            adder++;
        }
        System.out.println(triNum);

    }

    public static int numDivisors(int numb) {
        int i = 1, x;
        boolean even = true, square = false;
        int[] divisors = new int[1000];
        if (numb == 1) {
            return 1;
        }
        divisors[0] = 1;
        if (numb % 2 == 0) {
            divisors[1] = 2;
            x = 3;
        } else {
            divisors[1] = 3;
            x = 5;
            even = false;
        }

        while (divisors[i] * divisors[i - 1] != numb) {
            if (divisors[i] * divisors[i] == numb) {
                square = true;
                break;
            }
            if (numb % x == 0) {
                divisors[++i] = x;
            }
            x = even ? x + 1 : x + 2;
        }

        return square ? 2 * i + 1 : 2 * i;

    }

    public static void Problem13() throws IOException {
        try (FileReader numberfile = new FileReader("C:/Users/Joe B/Documents/hundredbignumbers.txt")) {
            char[] bigNumberfile = new char[50 * 100 + 198];
            char[][] bigNumbers = new char[100][50];
            numberfile.read(bigNumberfile);
            int i = 0, j = 0;
            for (char a : bigNumberfile) {
                if (Character.isDigit(a)) {
                    bigNumbers[j][i] = a;
                    i++;
                    if (i == 50) {
                        i = 0;
                        j++;
                    }
                }
            }
            Long sum = 0L;
            for (int k = 0; k < 100; k++) {
                sum += Long.valueOf(String.valueOf(bigNumbers[k], 0, 12));
            }
            System.out.println(sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Problem14() {
        long x;
        int terms, biggest = 1, biggestNum = 3;
        for (int i = 3; i < 1e6; i++) {
            x = i;
            terms = 1;
            while (x != 1) {
                x = x % 2 == 0 ? x / 2 : 3 * x + 1;
                terms++;
            }
            if (terms > biggest) {
                biggest = terms;
                biggestNum = i;
            }

        }
        System.out.println(biggestNum);
    }

    public static void Problem15() {
        System.out.println(getPathNum(17, 17));
    }

    public static long getPathNum(int x, int y) {
        if (x == 0 || y == 0) {
            return 1;
        }
        return getPathNum(x - 1, y) + getPathNum(x, y - 1);
    }

    public static void Problem16() {
        byte[] first = {2}, second = {4};
        BigInteger Two = new BigInteger(first);
        BigInteger current = new BigInteger(second);
        int i = 2;
        while (i < 1000) {
            current = current.multiply(Two);
            i++;
        }
        String TotalNum = current.toString(10);
        System.out.println(TotalNum);
        char[] totalDigits = TotalNum.toCharArray();
        int sum = 0;
        for (char dig : totalDigits) {
            sum += Character.getNumericValue(dig);
        }
        System.out.println(sum);
    }
    
    public static void Problem17(){
        int sum = 0;
        for (int i = 1; i<=1000;i++){
            sum+=NumberofLetters(i);
        }
        System.out.println(sum);    
    }
    
    public static int NumberofLetters(int numb){
        int[] ones = {0,3,3,5,4,4,3,5,5,4,3,6,6,8,8,7,7,9,8,8};
        int[] tens = {0,0,"twenty".length(),"thirty".length(),"forty".length(),"fifty".length(),"sixty".length(),"seventy".length(),"eighty".length(),"ninety".length()};
        if (numb == 1000){return "onethousand".length();}
        if (numb <20){return ones[numb];}
        if (numb%100 == 0){return ones[(int)(numb/100)]+"hundred".length();}
        if (numb<100){
            return ones[numb%10]+tens[(int)((numb-numb%10)/10)];
        }
        return ones[(int)((numb-numb%100)/100)]+"hundred".length()+"and".length()+NumberofLetters(numb%100);
        
    }
    
    public static void Problem18() throws IOException {
        try (FileReader numberfile = new FileReader("C:/Users/Joe B/Documents/p018_triangle.txt")) {
        BufferedReader bufferednumbs = new BufferedReader(numberfile);
        String lineOfNumbs;
        int[][] triangle = new int[15][15];
        int[][] max_sum = new int[15][15];
        for (int i = 0; i<15;i++){
            lineOfNumbs = bufferednumbs.readLine();
            for(int j = 0; j<i+1;j++){
                triangle[i][j] = Integer.valueOf(lineOfNumbs.substring(j*3,j*3+2));
            }
        }
        bufferednumbs.close();
        max_sum[0][0] = triangle[0][0];
        for (int i = 1; i<15;i++){
            for(int j = 0; j<=i;j++){
                if(j == 0){max_sum[i][j] = triangle[i][j] + max_sum[i-1][j];}
                else if(j == i){max_sum[i][j] = triangle[i][j] + max_sum[i-1][j-1];}
                else{
                   max_sum[i][j] = triangle[i][j] + max(max_sum[i-1][j-1],max_sum[i-1][j]);
                }
            }
        }
        Arrays.sort(max_sum[14]);
        System.out.print(max_sum[14][14]);
        }
        
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void Problem19(){
    
    int[] date = {2,1,1,1900};
    int firstsundays = 0;
    
    while (date[3]<2001){
    if(date[0]==8){
        date[0]=1;
    }    
    if(date[1] > daysinMonth(date[2],date[3])){
        date[2]++;
        date[1]=1;
    }
    if(date[2]==13){
        date[2]=1;
        date[3]++;
    }
    if(date[3]>1900 && date[1] == 1 && date[0] == 1){
        firstsundays++;
    }
    date[0]++; date[1]++;
    
    }
    
    System.out.println(firstsundays);
    }
    
    public static boolean isLeapYear(int year){
        if(year%400==0){return true;}
        if(year%100==0){return false;}
        if(year%4==0){return true;}
        return false;
    }
    
    public static int daysinMonth(int month,int year){
        if( month==4 || month==6 || month==9 || month==11 ){
            return 30;
        }
        if( month==2 ){ return isLeapYear(year)? 29 : 28;}
        return 31;
    }
    
    public static void Problem20(){
    BigInteger hundredfact = new BigInteger("100");    
    int multiplier = 99;
    while(multiplier>0){
    hundredfact = hundredfact.multiply(new BigInteger(String.valueOf(multiplier--)));    
    }
    int sum = 0;
    for (char x : hundredfact.toString().toCharArray()){
    sum+=Character.getNumericValue(x);
    }
    System.out.println(sum);
    }
    
    public static void Problem21(){
    int sum = 0, dI;
    boolean[] amicable = new boolean[10000];
    for(int i = 4;i<10000;i++){
    if (!amicable[i]){    
        dI = d(i);    
        if(i != dI && i == d(dI)){
            sum+= i + dI;
            System.out.print(i);
            System.out.print("  ");
            System.out.println(dI);
            amicable[dI] = true;
        }
    }
    }
    System.out.print(sum);
    }
    
    /**
     * Returns the sum of the divisors of an integer
     * @param   numb an integer
     * @return  An integer representing the sum of the proper divisors of
     *         <code>numb</code> 
     * 
     * @author Joseph Boudreau
     */    
    public static int d(int numb) {

        int[] primearray = {2};
        int i = 1, x, sum = 0;
        boolean even = true, square = false;
        int[] divisors = new int[1000];
        if(isPrime(primearray,numb)) { return 1;}
        if (numb == 4){ return 3;}  
        if (numb == 9){ return 4;}
        divisors[0] = 1;
        sum+=1;
        if (numb % 2 == 0) {
            divisors[1] = 2;
            sum+= 2 + (int)numb/2;
            x = 3;
        }
        else if (numb % 3 == 0){
            divisors[1] = 3;
            sum+= 3 + (int)numb/3;
            x = 5;
            even = false;
        }
        else if (numb % 5 == 0){
            divisors[1] = 5;
            sum+= 5 + (int)numb/5;
            x = 7;
            even = false;
        }
        else{
            divisors[1] = 7;
            sum+= 7 + (int)numb/7;
            x = 9;
            even = false;
        }
        

        while (divisors[i] * divisors[i - 1] != numb) {
            if(divisors[i] * divisors[i] == numb){
                square = true;
                break;
            }
            if (numb % x == 0) {
                divisors[++i] = x;
                sum+= x + (int)numb/x;
            }
            x = even ? x + 1 : x + 2;
        }
           
        return square? sum - divisors[i] : sum - divisors[i] - divisors[i-1];

    }
    
    public static void Problem22(){
        try (FileReader namefile = new FileReader("C:/Users/Joe B/Documents/p022_names.txt")) {
        BufferedReader nameBuff = new BufferedReader(namefile);    
        int charlen = 1, index = 0, nameNo = 0;
        nameBuff.mark(46448);
        while(nameBuff.read() != -1){
            charlen++;
        }
        char[] nameChars = new char[charlen];
        List<String> Names = new ArrayList<>();
        nameBuff.reset();
        nameBuff.read(nameChars);
        String tempName;
        while(index<charlen){
            
            if(Character.isLetter(nameChars[index])){
                Names.add(Character.toString(nameChars[index]));
                
                index++;
            while(Character.isLetter(nameChars[index])){
               tempName = Names.get(nameNo).concat(Character.toString(nameChars[index]));
               Names.remove(nameNo);
               Names.add(nameNo,tempName);
               index++;
            }
            nameNo++;
            }
            index++;
        }
        
        String[] NameArray = new String[Names.size()];
        Names.toArray(NameArray);
        Arrays.sort(NameArray);
        
        long sum = 0;
        for (int i = 0;i<NameArray.length;i++){
            sum+=(i+1)*getNameScore(NameArray[i]);
        }
        System.out.print(sum);
        
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void Problem42() throws IOException {
    try (FileReader wordfile = new FileReader("C:/Users/Joe B/Documents/p042_words.txt")) {
        BufferedReader wordBuff = new BufferedReader(wordfile);    
        int charlen = 1, index = 0, wordNo = 0;
        wordBuff.mark(46448);
        while(wordBuff.read() != -1){
            charlen++;
        }
        char[] wordChars = new char[charlen];
        List<String> Words = new ArrayList<>();
        wordBuff.reset();
        wordBuff.read(wordChars);
        String tempWord;
        while(index<charlen){
            
            if(Character.isLetter(wordChars[index])){
                Words.add(Character.toString(wordChars[index]));
                
                index++;
            while(Character.isLetter(wordChars[index])){
               tempWord = Words.get(wordNo).concat(Character.toString(wordChars[index]));
               Words.remove(wordNo);
               Words.add(wordNo,tempWord);
               index++;
            }
            wordNo++;
            }
            index++;
        }
        
        String[] WordArray = new String[Words.size()];
        Words.toArray(WordArray);
        Arrays.sort(WordArray);
        
        int trianglewords = 0, wordScore;
        int[] trianglenumbs = new int[30];
        for (int j = 1; j < 31; j++){
            trianglenumbs[j-1] = (int)j*(j+1)/2;
        }
        
        for (int i = 0;i<WordArray.length;i++){
            wordScore = getNameScore(WordArray[i]);
            LOOP1: for (int t : trianglenumbs){
                if (wordScore == t){
                    trianglewords++;
                    break LOOP1;
                }
                if (wordScore < t){break LOOP1;}
            }
        }
        
        System.out.println(trianglewords);
        
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getNameScore(String x){
        int score = 0;
        char[] chars = new char[x.length()];
        chars = x.toCharArray();
        for(char c : chars){
            score+= (int)c-64;    
        }
        return score;
    }
    
    public static void Problem23(){
        List<Integer> abundantsList = new ArrayList<>();
        for(int i = 1;i<28123;i++){
            if (d(i)>i){
                abundantsList.add(i);
                //System.out.println(i);
            }
        }
        int[] abundants = new int[abundantsList.size()];
        int aLen = abundants.length;
        for (int i = 0;i<aLen;i++){
            abundants[i] = abundantsList.get(i);
        }
        
        List<Integer> sumAbuns = new ArrayList<>();
        int abundantsSum = 0, tSum;
        for (int i = 0;i<aLen;i++){
            //System.out.println(abundants[i]);
            for (int j = i;j<aLen;j++){
                tSum = abundants[i]+abundants[j];
                if(tSum<=28123 && !sumAbuns.contains(tSum)){
                    sumAbuns.add(tSum);
                    abundantsSum+=tSum;
                }
            }
        }
        //System.out.println(abundantsSum);
        int nonAbundantsSum = (int)(28123)*(28123+1)/2 - abundantsSum;
        System.out.println(nonAbundantsSum);
        
    }
    
    public static void Problem24(){
    String[] permys = perms("0123456789");    
    System.out.println(permys[999999]);
    }
    
    public static String[] perms(String digits){
        int len = digits.length();
        if (len == 2){
            return new String[] {digits , String.valueOf(new char[] {digits.charAt(1),digits.charAt(0)})};
        }
        String[] allPerms = new String[factorial(len)];
        String subdigits, firstdig;
        int index = 0;
        for (int i = 0 ; i<len;i++){
            subdigits = digits.substring(0,i)+digits.substring(i+1,len);
            firstdig = digits.substring(i,i+1);
            String[] subPerms = perms(subdigits);
            for(String s : subPerms){
                allPerms[index++] = firstdig+s;
            }
        }
        return allPerms;
        }
    
    public static int factorial(int numb){
        int j = numb;
        while (j>1){numb*=--j;}
        return numb;
    }
    
    public static void Problem25(){
    BigInteger fibo1 = new BigInteger("1");
    BigInteger fibo2 = new BigInteger("1");
    BigInteger fibotemp;
    int index = 2;
    while(fibo2.toString().length()<1000){
        fibotemp = fibo2;
        fibo2 = fibo2.add(fibo1);
        fibo1 = fibotemp;
        index++;
    }
    System.out.println(index);
    }
    
    public static void Problem26(){
        List<Integer> primes = new ArrayList<>();
        for (int i = 10; i < 1000; i++){
            if (isPrime(new int[] {2},i)){primes.add(i);}
        }
        int[] primesA = new int[primes.size()];
        for (int j = 0; j<primes.size();j++){
            primesA[j] = primes.get(j);
        }
        
        int biggestperiod = 0;
        int k;
        BigInteger tentothe;
        BigInteger ten = new BigInteger("10");
        BigInteger zero = new BigInteger("0");
        BigInteger primeB;
        BigInteger one = new BigInteger("1");
        int result;
        for (int pr : primesA){
            k = 0;
            primeB = new BigInteger(Integer.toString(pr));
            tentothe = new BigInteger("1");
            do{
            tentothe = tentothe.multiply(ten);
            k++;
            result = zero.compareTo(tentothe.subtract(one).mod(primeB));
            }
            while(result!=0);
            if (k>biggestperiod){
                biggestperiod = k;
                System.out.println(pr);
            }
        }
        
    }
    
    public static void Problem27() throws IOException{
    int[] primes = getPrimeArray();
    int n, biggestN = 0;
    for (int a = -999; a < 1000; a++){
        for (int b = -999; b < 1000; b++){
            if(isPrime(primes,b)){
                n = 0;
                while(isPrime(primes,quadratic(n,a,b))){
                    n++;
                }
                if(n>biggestN){
                    biggestN = n;
                    System.out.print(a);
                    System.out.print("  ");
                    System.out.println(b);
                    System.out.println(n);
                  
                }
            }
            
        }
    }
        
    }
    
    public static int quadratic(int n, int a, int b){
        return (int)pow(n,2)+ a*n +b;
    }
    
    
    public static int[] getPrimeArray() throws IOException {
        try (FileReader primefile = new FileReader("C:/Users/Joe B/Documents/primes.txt")) {
            BufferedReader bufferedPrime = new BufferedReader(primefile);
            int[] primes = new int[1000];
            int i = 0;
            while(bufferedPrime.ready()){
                primes[i] = Integer.valueOf(bufferedPrime.readLine());
                i++;
            }
            return primes;
        
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void Problem28(){
        int sum = 1, x = 1, jump = 2;
        while (x<1001*1001){
            sum+= 4*x+10*jump;
            x+=4*jump;
            jump+=2;
        }
        System.out.println(sum);
    }
    
    public static void Problem29(){
    List<BigInteger> distinct = new ArrayList<>();
    BigInteger a = new BigInteger("2"), poweratob;
    int b;
    BigInteger limit = new BigInteger("101");
    while(!a.equals(limit)){
        b = 2;
        while(b<101){
            poweratob = a.pow(b);
            if (!distinct.contains(poweratob)){
                distinct.add(poweratob);
            }
            b++;
        }
        a = a.add(new BigInteger("1"));
        System.out.println(a.intValue());
    }
    System.out.println(distinct.size());
    }
    
    public static void Problem30(){
        int sum = 0, i = 2;
        while (i < 300000){
            if( i == digitPowerSum(i,5) ){
                System.out.println(i);
                sum+=i;
            }
            i++;

        }
        System.out.println(sum);
    }
    
    public static int digitPowerSum(int num, int power){
        int sum = 0;
        char[] digits = Integer.toString(num).toCharArray();
        for (char dig : digits){
            sum+= pow(Character.getNumericValue(dig),power);
        }
        return sum;
    }
    
    public static void Problem31(){
    int combos = 0, p1, p2, p5, p10, p20, p50, p100, p200;
    int[] remaining = new int[6];
    
    for(p200 = 0; p200 <= 1; p200++){
        remaining[0] = 200 - 200*p200;
        if(remaining[0] == 0){combos++; break;}
        
        for(p100 = 0; p100 <= (int)floor(remaining[0]/100); p100++){
            remaining[1]= remaining[0] -  100*p100;
            if(remaining[1] == 0){combos++; break;}
            
            for(p50 = 0; p50 <= (int)floor(remaining[1]/50); p50++){
                remaining[2]= remaining[1] -  50*p50;
                if(remaining[2] == 0){combos++; break;}
            
                for(p20 = 0; p20 <= (int)floor(remaining[2]/20); p20++){
                    remaining[3]= remaining[2] -  20*p20;
                    if(remaining[3] == 0){combos++; break;}
            
                    for(p10 = 0; p10 <= (int)floor(remaining[3]/10); p10++){
                        remaining[4]= remaining[3] -  10*p10;
                        if(remaining[4] == 0){combos++; break;}
                        
                        for(p5 = 0; p5 <= (int)floor(remaining[4]/5); p5++){
                            remaining[5]= remaining[4] -  5*p5;
                            if(remaining[5] == 0){combos++; break;}
                            
                            combos+=(int)floor(remaining[5]/2)+1;
                        }
                    }
                }
            }    
        }
    }
    
    System.out.println(combos);
    }
    
    public static void Problem32(){
    int[] dividers; int sum = 0;
    
    for(int x = 1000;x<10000;x++){
        dividers = divisors(x);
        for (int d : dividers){
            if(isPandigital(d,(int)x/d,x)){
                sum+=x;
                System.out.println(x);
                break;
            }
        }    
    }
    
    System.out.println(sum);
    }
    
    public static boolean isPandigital(int first, int second, int product){
        char[] test = new char[] {'1','2','3','4','5','6','7','8','9'};

        String digits = String.valueOf(first)+String.valueOf(second)+String.valueOf(product);
        if(digits.length()!=9){return false;}

        char[] digChar = digits.toCharArray();
        Arrays.sort(digChar);

        return Arrays.equals(digChar, test);
    }
    
    public static boolean isPandigital(int a){
        char[] test = new char[] {'1','2','3','4','5','6','7','8','9'};
        String digits = String.valueOf(a);
        char[] SubTest = Arrays.copyOf(test, digits.length());
        char[] digChar = digits.toCharArray();
        Arrays.sort(digChar);
        return Arrays.equals(digChar, SubTest);
    }
    
    public static int[] divisors(long numb) {

        int[] primearray = {2};
        int i = 1, x, nextDiv;
        boolean even = true, square = false;
        int[] divisors = new int[1000];
        if(isPrime(primearray,numb)) { return new int[] {1};}
        divisors[0] = 1;
        if (numb % 2 == 0) {
            divisors[1] = 2;
            x = 3;
        }
        else{
            nextDiv = 3;
            while(numb % nextDiv != 0){
                nextDiv+=2;
            }
            divisors[1] = nextDiv;
            x = nextDiv+2;
            even = false;
        }
        
        while (divisors[i] * divisors[i - 1] != numb) {
            if(divisors[i] * divisors[i] == numb){
                square = true;
                break;
            }
            if (numb % x == 0) {
                divisors[++i] = x;
            }
            x = even ? x + 1 : x + 2;
            if (x > numb){
                System.out.println("not good");
            }
        }
        
        int[] realDivisors = new int[i+1];
        for(int j = 0; j<=i; j++){
            realDivisors[j] = divisors[j];
        }
        return realDivisors;

    }
    
    public static void Problem33(){
    Fraction CurrentF, CuriousF;
    Fraction[] CuriousOnes = new Fraction[4];
    int index = 0;
    for(int D = 10; D < 100; D++){
        for(int N = 10; N < D; N++){
            CurrentF = new Fraction(N,D);
            CuriousF = CurrentF.getCuriousReduced();
            if (CuriousF != null && CuriousF.equals(CurrentF)){
                CuriousOnes[index++] = CurrentF;
                CurrentF.printString();
            }
        }
    }
    Fraction base = new Fraction(1,1);
    for(Fraction f : CuriousOnes){
        base = base.multiply(f);
    }
    base.printString();
    base.reduceFraction();
    base.printString();
    }
    
    public static void Problem34(){
        int sum = 0;
        for (int i = 0; i < 1000000; i++){
            String iBool = Integer.toBinaryString(i);
            if (isPalindromic(i) && isPalindromic(iBool)){
                sum+=i;
            }
        }
        System.out.println(sum);
    }
    
    public static boolean isPalindromic(int x){
        char[] digits = Integer.toString(x).toCharArray();
        boolean palindrome = true;
        for(int i = 0; i < (int)floor(digits.length/2); i++){
            if (digits[i] != digits[digits.length - (i+1)]){ palindrome = false;}
        }
        return palindrome;
    }
    
    public static boolean isPalindromic(String x){
        char[] digits = x.toCharArray();
        boolean palindrome = true;
        for(int i = 0; i < (int)floor(digits.length/2); i++){
            if (digits[i] != digits[digits.length - (i+1)]){ palindrome = false;}
        }
        return palindrome;
    }
    
    
    public static void Problem35(){
        int test = 9, totalTruncatables = 0, sum = 0, testtemp;
        int[] primearray = {2};
        char[] notAllowed = {'0','4','6','8'};
        boolean searchable;
        boolean[] RandL = {true, false};
        BIGLOOP: while(totalTruncatables < 11){
            test++;
            searchable = true;
            char[] testDigs = Integer.toString(test).toCharArray();
            TEST1: for (char c : testDigs){
                if (Arrays.binarySearch(notAllowed,c) > 0){ searchable = false; break TEST1;}
            }
            TEST2: if(searchable && isPrime(primearray,test)){
                for(boolean B : RandL){
                    testtemp = truncate(test,B);
                    while(testtemp != 0){
                        if (isPrime(primearray,testtemp) == false){
                            break TEST2;
                        }
                        testtemp = truncate(testtemp,B);
                    }
                }
                System.out.println(test);
                totalTruncatables++;
                sum+=test;   
            }
        }
        System.out.println(sum);
    }
    
    public static int truncate(int numb, boolean Right){
        if (numb < 10){ return 0;}
        int numDigs = Integer.toString(numb).length();
        if (Right){
            return (int)(numb-numb%10)/10;
        }
        else{
            return numb%(int)(pow(10,numDigs-1));
        }
    }
    
    public static void Problem67(){
        try (FileReader numberfile = new FileReader("C:/Users/Joe B/Documents/p067_triangle.txt")) {
        BufferedReader bufferednumbs = new BufferedReader(numberfile);
        String lineOfNumbs;
        int[][] triangle = new int[100][100];
        int[][] max_sum = new int[100][100];
        for (int i = 0; i<100;i++){
            lineOfNumbs = bufferednumbs.readLine();
            for(int j = 0; j<i+1;j++){
                triangle[i][j] = Integer.valueOf(lineOfNumbs.substring(j*3,j*3+2));
            }
        }
        bufferednumbs.close();
        max_sum[0][0] = triangle[0][0];
        for (int i = 1; i<100;i++){
            for(int j = 0; j<=i;j++){
                if(j == 0){max_sum[i][j] = triangle[i][j] + max_sum[i-1][j];}
                else if(j == i){max_sum[i][j] = triangle[i][j] + max_sum[i-1][j-1];}
                else{
                   max_sum[i][j] = triangle[i][j] + max(max_sum[i-1][j-1],max_sum[i-1][j]);
                }
            }
        }
        Arrays.sort(max_sum[99]);
        System.out.print(max_sum[99][99]);
        }
        
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
}
 