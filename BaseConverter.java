/** This program converts a given number from one base to another, where:
    <ul>
        <li>args[0] represents the starting number;</li>
        <li>args[1] represents the starting base;</li>
        <li>args[2] represents the target base.</li>
    </ul>
<p>We require two or three arguments: the starting number will be specified using a special notation (below);
the starting and target bases must both be positive and will be given in decimal. The target base, if absent, defaults to decimal. 
Note that the "digits" in the starting number must be consistent with the starting base. All arithmetic is done using Java type <i>long</i>, whose 
largest value is <a href="http://docs.oracle.com/javase/7/docs/api/java/lang/Long.html#MAX_VALUE">java.lang.Long.MAX_VALUE</a>.</p>

<p>In order to represent numbers from all possible bases, we employ a special notation:
a string like [d1][d2]...[dk] represents a k-digit number whose high-order digit has
value d1 (decimal), whose next digit has value d2 (decimal), etc. Here are some examples:
    <ul>
        <li>The number 10110 would be represented [1][0][1][1][0].
        <li>The number 3204124 would be represented [3][2][0][4][1][2][4].
        <li>The base three number that means forty-seven would be represented [1][2][0][2].
        <li>The hexadecimal number for which we ordinarily write B3C would be represented [11][3][12].
    </ul></p>
 
<p>So, to convert from 314 base five to base three, we would run <i>java BaseConverter [3][1][4] 5 3</i>.
To convert BBA326CF from hexadecimal to base twenty, we would run <i>java BaseConverter [11][11][10][3][2][6][12][15] 16 20</i>. 
If we run <i>java BaseConverter [6][14] 26 14</i>, the output should be <i>[12][2]</i>.</p>

<p>The above-described special notation should also be used for outputting the target number.</p>
*/

import java.util.Arrays; 
import java.util.ArrayList;

public class BaseConverter {
    private static ArrayList<Long> startingNumber = new ArrayList<>();
    private static Long startingBase;
    private static Long targetBase;

    public static boolean validArgs (String[] args) {
      
       return (args.length == 2 || args.length == 3) && 
            args[0].matches("(\\[\\d+\\])+") && 
            args[1].matches("\\d+") 
            && (args.length != 3 || args[2].matches("\\d+")); //Toal's code but I understand perfecttly 
    }
        
    public static void readArguments(String[] args) {
        
        startingBase = Long.parseLong(args[1]);
        targetBase = args.length == 3 ? Long.parseLong(args[2]): 10;
        startingNumber = Arrays.stream(args[0].substring(1, args[0].length() - 1)
            .split("]\\["))
            .mapToLong(Long::parseLong)
            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll); //Toal put it on the board + Java API, but I understand 

    }
    
    public static  ArrayList<Long> convertStartingBase(long startingBase, long targetBase) {
        ArrayList<Long> firstResult = new ArrayList<>();
        long n = startingBase;
        if (n == 0) {
            firstResult.add((long) 0);
            return firstResult;
        }
        while (n > 0) {
            firstResult.add(0, n % targetBase);
            n = n / targetBase;
        }
        return firstResult;
    }

    public static ArrayList<Long> plus(ArrayList<Long> greaterNum, ArrayList<Long> lesserNum, long targetBase) {
        long carry = 0;
        ArrayList<Long> bigger = null;
        ArrayList<Long> smaller = null;
        long carryPlusSum = 0;
        ArrayList<Long> carryPlusSumCorrectBase = new ArrayList<>();
        ArrayList<Long> sum = new ArrayList<>();
        if (greaterNum.size() == lesserNum.size()) {
            for (int i = greaterNum.size() - 1; i >= 0; i--) {
                carryPlusSum = (long) (greaterNum.get(i) + lesserNum.get(i) + carry);
                carryPlusSumCorrectBase = convertStartingBase(carryPlusSum, targetBase);
                if (carryPlusSumCorrectBase.size() == 2) {
                    carry = carryPlusSumCorrectBase.get(0);
                    sum.add(0, carryPlusSumCorrectBase.get(1));
                } else {
                    carry = 0;
                    sum.add(0, carryPlusSumCorrectBase.get(0));
                }
            }
            sum.add(0, carry);
        } else {
            if (greaterNum.size() > lesserNum.size()) {
                bigger = greaterNum;
                smaller = lesserNum;
            } else if (greaterNum.size() < lesserNum.size()) {
                smaller = greaterNum;
                bigger = lesserNum;
            }
            int difference = bigger.size() - smaller.size();
            for (int i = smaller.size() - 1; i >= 0; i--) {
                carryPlusSum = (long) (bigger.get(difference + i) + smaller.get(i) + carry);
                carryPlusSumCorrectBase = convertStartingBase(carryPlusSum, targetBase);
                if (carryPlusSumCorrectBase.size() == 2) {
                    carry = carryPlusSumCorrectBase.get(0);
                    sum.add(0, carryPlusSumCorrectBase.get(1));
                } else {
                    carry = 0;
                    sum.add(0, carryPlusSumCorrectBase.get(0));
                }
            }
            for (int i = difference - 1; i >= 0; i--) {
                carryPlusSum = (long) (bigger.get(i) + carry);
                carryPlusSumCorrectBase = convertStartingBase(carryPlusSum, targetBase);
                if (carryPlusSumCorrectBase.size() == 2) {
                    carry = carryPlusSumCorrectBase.get(0);
                    sum.add(0, carryPlusSumCorrectBase.get(1));
                } else {
                    carry = 0;
                    sum.add(0, carryPlusSumCorrectBase.get(0));
                }
            }
            sum.add(0, carry);
        }
        return sum;
    }
       

    public static ArrayList<Long> multiply(ArrayList<Long> greaterNum, ArrayList<Long> lesserNum, long targetBase) {
        long product;
        ArrayList<Long> newProduct = new ArrayList<>();
        ArrayList<Long> finalProduct = new ArrayList<>();
        finalProduct.add((long) 0);
        long initialShift = 0;
        for (int i = lesserNum.size() - 1; i >= 0; i--) {
            long secondShift = 0;
            for (int j = greaterNum.size() - 1; j >= 0; j--) {
                product = lesserNum.get(i) * greaterNum.get(j);
                newProduct = convertStartingBase(product, targetBase);
                for (int k = 0; k < (secondShift + initialShift); k++) {
                    newProduct.add((long) 0);
                }
                finalProduct = plus(newProduct, finalProduct, targetBase);
                secondShift++;
            }
            secondShift = 0;
            initialShift++;
        }
        return finalProduct;
    }
    

    public static ArrayList<Long> convertNumber(ArrayList<Long> startingNumber, long startingBase, long targetBase) {
        ArrayList<Long> convertedInitialBase = convertStartingBase(startingBase, targetBase);
        ArrayList<Long> finalConvertedNumber = new ArrayList<>(0);
        ArrayList<Long> zero = new ArrayList<>();
        zero.add((long) 0);
        ArrayList<Long> firstDigit = new ArrayList<>();
        firstDigit.add(startingNumber.get(0));
        finalConvertedNumber = plus(firstDigit, zero, targetBase);
        for (int i = 1; i < startingNumber.size(); i++) {
            finalConvertedNumber = multiply(finalConvertedNumber, convertedInitialBase, targetBase);
            firstDigit = new ArrayList<>();
            firstDigit.add(startingNumber.get(i));
            finalConvertedNumber = plus(firstDigit, finalConvertedNumber, targetBase);
        }
        return finalConvertedNumber;

    }
    
    public static ArrayList<Long> takeOffZeros(ArrayList<Long> list) {
        ArrayList<Long> result = new ArrayList<>();
        boolean keepTrimming = true;
        for (int i = 0; i < list.size(); i++) {
            Long item = list.get(i);
            if (item != 0) keepTrimming = false; 
            if (!keepTrimming) {
                result.add(item);
            }
        }
        return result;
    }

    public static void trimmedNumber (ArrayList<Long> list) {
        for (int i = 0; i < list.size(); i++) {
            Long item = list.get(i);
            System.out.print("[" + item + "]");
        }
        System.out.println();
    }

    public static void main (String[] args) {
        if (!validArgs(args)) {
            throw new IllegalArgumentException();
        }
        else {
            readArguments(args);
            ArrayList<Long> finalResult;
            if (startingNumber.size() == 1 && startingNumber.get(0) == 0) {
                finalResult = startingNumber;
            } else {
                finalResult = convertNumber(startingNumber, startingBase, targetBase);
                finalResult = takeOffZeros(finalResult);
            }

            trimmedNumber(finalResult);
           
        }

    }

}
