
import java.util.Collection;
public class NumberList implements Collection {
    private Long[] list;

    /** Constructs an empty number list. */
    public NumberList(){
        list = new Long[0];
    }


    /** Constructs a number list from an array of Longs. */
    public NumberList( Long[] l ){
        list = new Long[l.length];
        for (int i = 0; i < l.length; i++) {
            list[i] = l[i];
        }
    }
    
    /** Increases by one the number of instances of the given element in this collection. */
    public boolean add ( Object obj ) {
        if (obj instanceof Long) {
            Long[] additionalValue = new Long[list.length + 1];
            for (int i = 0; i < list.length; i++) {
                additionalValue[i] = list[i];
            }
            list = additionalValue;
            return true;
        }
        return false;
    }
    

    /** Adds all of the elements of the given number list to this one. */
    public boolean addAll ( java.util.Collection c  ) {
        if (c.size() > 0) {
            Long[] newList = new Long[list.length + c.size()];
            Object[] cArray = c.toArray();
            for (int i = 0; i < list.length; i++) {
                newList[i] = list[i];
            }
            for (int i = 0; i < c.size(); i++) {
                if (cArray[i] instanceof Long) {
                    newList[(list.length - 1) + i] = (long) cArray[i];
                } else {
                    return false;
                }
            }
            list = newList;
        }
        return true; 
    }
 

    /** Removes all of the elements from this collection. */
    public void clear () {
        list = new Long[0];
    }
 

    /** Returns true iff this number list contains at least one instance of the specified element. */
    public boolean contains ( Object obj ) {
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
    public boolean containsAll ( java.util.Collection c ) {
        for (int i = 0; i < c.size(); i++) {
            if (!this.contains(c.toArray()[i])) {
                return false;
            }
        }
        return true;
    }
 
 


    /** Compares the specified object with this collection for equality. */
    public boolean equals ( Object obj ) {
        //FINISH!!!!!!!!!!!
        if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
        }
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        throw new UnsupportedOperationException();
    }
 


//DOOOOO!!!!!!!!
    /** Returns the hashcode value for this collection. */
    public int hashCode () {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        throw new UnsupportedOperationException();
    }



    /** Returns true if this collection contains no elements. */
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
    public boolean remove ( Object obj ) {
        if (!(obj instanceof Long)) {
            return false;
        } else if (this.contains(obj)) {
            for (int i = 0; i < list.length; i++) {
                if (obj.equals(list[i])) {
                    list[i] = null;
                }
            }
        }
        return true;
    }



    /** Removes all of this collection's elements that are also contained 
        in the specified collection. */
    public boolean removeAll ( java.util.Collection c ) {
        if (this.containsAll(c)) {
            Object[] cArray = c.toArray();
            for (int i = 0; i < list.length; i++) {
                this.remove(cArray[i]);
            }
        }
        return false;
    }

	/** Retains only the elements in this collection that are contained in the specified collection. 
		 In other words, removes from this collection all of its elements that are not contained in the 
		 specified collection. */
	public boolean retainAll ( java.util.Collection c ) {
		if (c instanceof Collection) {
            Object[] cArray = c.toArray();
            for (int i = 0; i < c.size(); i++) {
                if (!this.contains(cArray[i])) {
                    remove(cArray[i]);
                }
            }
            return true;
        }
        return false;
	}


    /** Returns the number of elements in this number list, including duplicates. */
    public int sizeIncludingDuplicates () {
        if (list.length > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return list.length;
    }
    
    

    /** Returns a Long[] containing all of the elements in this collection, not including duplicates. */
    public Long[] toArray () {
        Long[] copyArray = list;
        for (int i = 0; i < copyArray.length; i++) {
            for (int j = 0; j < copyArray.length; i++) {
                if (copyArray[i] == copyArray[j]) {
                    remove(copyArray[i]);
                }
            }
        }
        return copyArray;
    }



    /** Not supported for this class. */
    public Object[] toArray ( Object[] obj ) {
        throw new UnsupportedOperationException();
    }




    /** Returns the number of elements in this number list, not including duplicates. */
    public int size () {
        // Object [] copiedArray = list.toArray();
        // Long[] secondArray = new Long[copiedArray.length];
        // return sizeIncludingDuplicates(secondArray);

        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        throw new UnsupportedOperationException();
    }




    /** Returns the number of instances of the given element in this number list. */
    public int count ( Object obj ) {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        throw new UnsupportedOperationException();
    }
    

    
    /** This returns a stringy version of this number list. */
    public String toString () { // overrides Object.toString()
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        throw new UnsupportedOperationException();
    }


    
    /** This so-called "static factory" returns a new number list comprised of the numbers in the specified array.
        Note that the given array is long[], not Long[]. */
    public static NumberList fromArray ( long[] l ) {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        throw new UnsupportedOperationException();
    }

    
    /** This main method is just a comprehensive test program for the class. */
    public static void main ( String[] args ) {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        throw new UnsupportedOperationException();
    }
    
}
