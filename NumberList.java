/**     

    <b>Note: Corrections have been made to the return types for both toArray() methods. (2015-10-13).</b>

    An object of this class represents a number list, i.e., an ordered collection
    of integers, each of Java class <a href="http://docs.oracle.com/javase/7/docs/api/java/lang/Long.html">Long</a>, 
    with duplicates permitted. Be sure to read the Java documentation on
    <a href="http://docs.oracle.com/javase/7/docs/api/java/util/Collection.html">interface java.util.Collection</a>.

*/
import java.util.Collection;

public class NumberList implements Collection {
    private Long[] list = new Long[16];
    private int numberSize = 0;
    private static int attempts = 0;
    private static int successes = 0;

    /** Constructs an empty number list. */
    //worst-case performance: θ(1)
    public NumberList(){
        list = new Long[16];
    }

    /** Constructs a number list from an array of Longs. */
    //worst-case performance: θ(n)
    public NumberList(Long[] l){
        list = new Long[l.length];
        for (int i = 0; i < l.length; i++) {
            list[i] = l[i];
            numberSize++;
        }
    }
    
    /** Increases by one the number of instances of the given element in this collection. */
    //worst-case performance: θ(n)
    public boolean add (Object obj) {
        if (!(obj instanceof Long)) {
            return false;
        }
        if (numberSize == list.length) {
            Long[] newArray = new Long[list.length * 2];
            System.arraycopy(list, 0, newArray, 0, list.length);
            list = newArray;
        }
        list[numberSize++] = (Long) obj;
        return true;
    }
    
    /** Adds all of the elements of the given number list to this one. */
    //worst-case performance: θ(n²)
    public boolean addAll (java.util.Collection c) {
        Object[] cArray = c.toArray();
        if (cArray.length > 0) {
            Long[] newList = new Long[list.length + cArray.length];
            for (int i = 0; i < list.length; i++) {
                newList[i] = list[i];
            }
            for (int i = 0; i < cArray.length; i++) {
                if (cArray[i] instanceof Long) {
                    newList[(list.length) + i] = (long) cArray[i];
                } else {
                    return false;
                }
            }
            list = newList;
        }
        return true; 
    }
 
    /** Removes all of the elements from this collection. */
    //worst-case performance: θ(1)
    public void clear () {
        list = new Long[16];
        numberSize = 0;
    }
 
    /** Returns true iff this number list contains at least one instance of the specified element. */
    //worst-case performance: θ(n)
    public boolean contains (Object obj) {
        if (obj instanceof Long) {
            for (int i = 0; i < list.length; i++) {
                if (obj.equals(list[i])) {
                    return true;
                }
            }
        }
        return false;
    }
 
    /** Returns true iff this number list contains at least one instance of each element 
        in the specified list. Multiple copies of some element in the argument do not
        require multiple copies in this number list. */
    //worst-case performance: θ(n³)
    public boolean containsAll (java.util.Collection c) {
        for (int i = 0; i < c.size(); i++) {
            if (!this.contains(c.toArray()[i])) {
                return false;
            }
        }
        return true;
    }
 
    /** Compares the specified object with this collection for equality. */
    //worst-case performance: θ(n)
    public boolean equals (Object obj) {
        if (!(obj instanceof NumberList)) {
            return false;
        }
        Long[] objArray = new Long[0];
        objArray = ((NumberList) obj).list;

        if (this.list.length != objArray.length) {
            return false;
        } else {
            for (int i = 0; i < list.length; i++) {
                if (this.list[i] != objArray[i]) {
                    return false;
                }
            }

        }
        return true;
    }
 
    /** Returns the hashcode value for this collection. */
    //worst-case performance: θ(1)
    public int hashCode () {
        return super.hashCode();
    }

    /** Returns true if this collection contains no elements. */
    //worst-case performance: θ(1)
    public boolean isEmpty () {
        if (list.length == 0) {
            return true;
        }
        return false;
    }

    /** Returns an iterator over the elements in this collection. Replicated elements should
        be "iterated over" just once. */
    public java.util.Iterator iterator () {
        //Told not to complete
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        throw new UnsupportedOperationException();
    }

    /** Removes a single instance of the specified element from 
        this collection, if it is present. */
    //worst-case performance: θ(n²)
    public boolean remove (Object obj) {
        if (!(obj instanceof Long)) {
            return false;
        } 
        for (int i = 0; i < this.list.length; i++) {
            if (this.list[i].equals(obj)) {
                System.arraycopy(list, i + 1, list, i, numberSize - 1 - i);
                numberSize--;
                return true;
            }
        }
        return false;
    }

    /** Removes all of this collection's elements that are also contained 
        in the specified collection. */
    //worst-case performance: θ(n³)
    public boolean removeAll ( java.util.Collection c ) {
        if (this.containsAll(c)) {
            Object[] cArray = c.toArray();
            for (int i = 0; i < cArray.length; i++) {
                this.remove(cArray[i]);
            }
            return true;
        }
        return false;
    }

	/** Retains only the elements in this collection that are contained in the specified collection. 
		 In other words, removes from this collection all of its elements that are not contained in the 
		 specified collection. */
    //worst-case performance: θ(n³)
	public boolean retainAll (java.util.Collection c) {
		if (c instanceof Collection) {
            for (int i = 0; i < numberSize; i++) {
                if (!(c.contains(list[i]))) {
                    remove(list[i]);
                    i--;
                }
    
            }
            return true;
        }
        return false;
	}

    /** Returns the number of elements in this number list, including duplicates. */
    //worst-case performance: θ(1)
    public int sizeIncludingDuplicates () {
        if (list.length > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return list.length;
    }
    
    /** Returns a Long[] containing all of the elements in this collection, not including duplicates. */
    //worst-case performance: θ(n²)
    public Long[] toArray () {
        Long[] copyArray = new Long[list.length];
        System.arraycopy(list, 0, copyArray, 0, list.length);
        for (int i = 0; i < copyArray.length; i++) {
            for (int j = i + 1; j < copyArray.length; j++) {
                if (copyArray[i] == copyArray[j]) {
                    copyArray[j] = null;
                }
            }
        }
        int elements = 0;
        for (int i = 0; i < copyArray.length; i++) {
            if (copyArray[i] != null) {
                elements++;
            }
        }
        Long[] finishedArray = new Long[elements];
        int finalPosition = 0;
        for (int i = 0; i < elements; i++) {
            if (copyArray[i] != null) {
                finishedArray[finalPosition] = copyArray[i];
                finalPosition++;
            }
        }
        return finishedArray;
    }

    /** Not supported for this class. */
    public Object[] toArray ( Object[] obj ) {
        throw new UnsupportedOperationException();
    }

    /** Returns the number of elements in this number list, not including duplicates. */
    //worst-case performance: θ(n²)
    public int size () {
        return this.toArray().length;
    }

    /** Returns the number of instances of the given element in this number list. */
    //worst-case performance: θ(n)
    public int count (Object obj) {
        int countDuplicates = 0;
        for (int i = 0; i < numberSize; i++) {
            if (this.list[i] == obj) {
                countDuplicates++;
            }
        }
        return countDuplicates;
    }
    
    /** This returns a stringy version of this number list. */
    //worst-case performance: θ(n)
    public String toString () { // overrides Object.toString()
        String finalString = "";
        for (int i= 0; i < numberSize; i++) {
            finalString += list[i] +  ", ";
        }
        return finalString;
    }

    /** This so-called "static factory" returns a new number list comprised of the numbers in the specified array.
        Note that the given array is long[], not Long[]. */
    //worst-case performance: θ(n)
    public static NumberList fromArray (long[] l) {
        int i = 0;
        Long[] converted = new Long[l.length];
        for(Long x : l){
            converted[i++] = x;
        }

        return new NumberList(converted);
    }

    /** This main method is just a comprehensive test program for the class. */
    public static void main (String[] args) {
        attempts = 0;
        successes = 0;

        test_constructors();
        test_add();
        test_addAll();
        test_contains();
        test_containsAll();
        test_equals();
        test_remove();
        test_removeAll();
        test_retainAll();
        test_toArray();
        test_count();
        test_giantList();
        test_emptyListCanBeConstructed();

        System.out.println(successes + "/" + attempts + " tests passed.");
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "successes" : "failure");
    }

    private static void test_constructors() {
        System.out.println("Testing constructors...");
        Long[] testArray = new Long[]{6L, 7L};
        NumberList input1 = new NumberList(testArray);

        try {
            displaySuccessIfTrue(input1.list[0] == 6L && input1.list[1] == 7L);
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

    }

    private static void test_add() {
        System.out.println("Testing add...");
        NumberList secondnL = new NumberList();
        secondnL.add(1L);
        Long[] testArray2 = new Long[]{6L, 7L};
        NumberList input2 = new NumberList(testArray2);
        input2.add(8L);

        try {
            displaySuccessIfTrue(secondnL.add(1L));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        } 

        try {
            displaySuccessIfTrue(input2.list[2] == 8L);
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        } 

        NumberList input3 = new NumberList(testArray2);
        input3.add(10L);
        try {
            displaySuccessIfTrue(input3.list[2] == 10L);
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        } 
        try {
            displaySuccessIfTrue(!input2.add("hello"));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

    }

     private static void test_addAll() {
        System.out.println("Testing addAll...");
        Long[] testArray3 = new Long[]{6L, 7L};
        NumberList input4 = new NumberList(testArray3);

        try {
            displaySuccessIfTrue(input4.addAll(input4));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }

    private static void test_contains() {
        System.out.println("Testing contains...");
        Long[] testArray4 = new Long[]{6L, 7L, 8L};
        NumberList input5 = new NumberList(testArray4);

        try {
            displaySuccessIfTrue(input5.contains(7L));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }

    private static void test_containsAll() {
        System.out.println("Testing containsAll...");
        Long[] testArray5 = new Long[]{6L, 7L, 8L};
        Long[] testArray6 = new Long[]{7L, 8L};
        NumberList input6 = new NumberList(testArray5);
        NumberList input7 = new NumberList(testArray6);

        try {
            displaySuccessIfTrue(input6.containsAll(input7));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }

    private static void test_equals() {
        System.out.println("Testing equals...");
        Long[] testArray11 = new Long[]{6L, 7L};
        Long[] testArray12 = new Long[]{6L, 7L};
        Long[] testArray13 = new Long[]{6L, 7L, 8L};
        NumberList input12 = new NumberList(testArray11);
        NumberList input13 = new NumberList(testArray12);
        NumberList input14 = new NumberList(testArray13);

        try {
            displaySuccessIfTrue(input12.equals(input13));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(!input12.equals(input14));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }

    private static void test_remove() {
        System.out.println("Tesing remove...");
        Long[] testArray7 = new Long[]{6L, 7L, 8L};
        NumberList input8 = new NumberList(testArray7);
        input8.remove(new Long(8L));

        try {
            displaySuccessIfTrue(input8.list[0] == 6L && input8.list[1] == 7L);
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

    }

    private static void test_removeAll() {
        //finish!!!!!
        System.out.println("Tesing removeAll...");
        Long[] testArray9 = new Long[]{6L, 7L, 8L, 9L, 10L};
        NumberList input10 = new NumberList(testArray9);
        input10.removeAll(new java.util.ArrayList<Long>(java.util.Arrays.asList(new Long[]{6L, 10L, 8L})));

        try {
            displaySuccessIfTrue(input10.list[0] == 7L && input10.list[1] == 9L);
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

    }

    private static void test_retainAll() {
        System.out.println("Tesing retainAll...");
        Long[] testArray10 = new Long[]{6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L};
        Long[] testArray11 = new Long[]{6L, 10L, 8L};
        NumberList input11 = new NumberList(testArray10);
        NumberList input12 = new NumberList(testArray11);
        input11.retainAll(new java.util.ArrayList<Long>(java.util.Arrays.asList(new Long[]{6L, 8L, 10L})));
        System.out.println(input11.toString());

        try {
            displaySuccessIfTrue(input11.containsAll(input12));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

    }
    
    private static void test_toArray() {
        System.out.println("Testing toArray...");
        Long[] testArray16 = new Long[]{8L, 10L, 13L, 25L, 10L};
        NumberList input16 = new NumberList(testArray16);
        Long[] testArray17 = new Long[]{8L, 10L, 13L, 25L};
        NumberList input17 = new NumberList(testArray17);

        NumberList booty = new NumberList(input16.toArray());
        System.out.println("toArray: " + booty);

        try {
            displaySuccessIfTrue(booty.equals(input17));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

    }

    private static void test_emptyListCanBeConstructed() {
        System.out.println("Testing empty list...");
        try {
            NumberList input = new NumberList();
            displaySuccessIfTrue(input.size() == 0);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }
    }
    private static void test_giantList() {
        System.out.println("Testing a really big list...");
        Long[] testArray = new Long[5000000];
        java.util.Arrays.fill(testArray, 1L);
        System.out.println("Constructing a big list...");
        NumberList list = new NumberList(testArray);


        try {
            displaySuccessIfTrue(list.count(1L) == 5000000);
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }
    
    private static void test_count() {
        System.out.println("Testing count...");
        Long[] testArray14 = new Long[]{5L, 5L, 6L, 7L, 5L, 5L, 6L};
        NumberList input15 = new NumberList(testArray14);

        try {
            displaySuccessIfTrue(input15.count(5L) == 4);
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(input15.count(6L) == 2);
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }

}
