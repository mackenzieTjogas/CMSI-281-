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

11,11,23,656 [11,11,23,656]
*/

import java.util.Arrays; 
import java.util.ArrayList;

public class BaseConverter {

    /** This method attempts to validate the command-line arguments. If they're
        okay, it returns true; otherwise, it returns false. */
    
    public static boolean validArgs ( String[] args ) {
        //check and make sure the input arguments are valid
        
        long startingBase;
        long targetBase = 10;
        long convertedBase;
        long[] startingNumber;

        startingBase = Long.parseLong(args[1]);
        targetBase = Long.parseLong(args[2]);
        String[] startingNumberString = args[0].substring(1, args[0].length()-1).split("\\]\\[");
        
        System.out.println(Arrays.toString(startingNumberString));

        return true; //fix this later 
    }
    
    public static long ConvertStartingBase(long startingBase, long targetBase) {
        ArrayList<Long> result = new ArrayList<>();

        System.out.println(result.toString());

        long n = startingBase;
        while (n > 0) {
            result.add(n % targetBase);
            n = n / targetBase;

            System.out.println(result.toString());
        }

        return ArrayUtils.reverse(result);
    }
       
    
    /** This method calls validArgs() to check the command-line arguments and, if they're valid, 
        it takes care of the conversion and outputs the result. */
    
    public static void main ( String[] args ) {
        if ( ! validArgs ( args ) ) {
            throw new IllegalArgumentException();
        }
        else {
            
            long startingBase = Long.parseLong(args[1]);
            long targetBase = Long.parseLong(args[2]);
            long conversionFactor = ConvertStartingBase(startingBase, targetBase);
            System.out.println("ConversionFactor: " + conversionFactor); //take this away later 
           
        }
    }

}
