/*
 * SortedSet.java - CS 314 Assignment 7
 *
 * By signing my/our name(s) below, I/we affirm that this assignment is my/our
 * own work. I/we have neither given nor received unauthorized assistance on
 * this assignment.
 *
 * Name 1: Pheanouk Hun
 * Email address 1: ph23434@eid.utexas.edu
 * UTEID 1: ph23434
 */

import java.util.Iterator;
import java.util.ArrayList;

/**
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 *
 * The data type for E must be a type that implements Comparable.
 *
 * Implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must
 * be used as the internal storage container. For methods involving two set,
 * if that method can be done more efficiently if the other set is also a
 * SortedSet, then do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    /**
     * create an empty SortedSet
     * 
     * Big O Notation: O(1)
     */
    public SortedSet() {
        this.myCon = new ArrayList<>();
    }

    /**
     * create a SortedSet out of an unsorted set.
     * 
     * Big O Notation: O(N) if other is a SortedSet<E>; N * log(N) if other isn't a
     * SortedSet<E>.
     * 
     * @param other != null
     */
    public SortedSet(ISet<E> other) {

        // Precondition
        if (other == null) {
            throw new IllegalArgumentException("The Parameter Cannot Equal Null");
        }

        this.myCon = new ArrayList<>();

        // Copies Values
        if (other instanceof SortedSet<?>) {
            SortedSet<E> otherSet = (SortedSet<E>) other;
            for (E item : otherSet.myCon) {
                this.myCon.add(item);
            }
        } else {
            for (E item : other) {
                this.add(item);
            }
        }
    }

    /**
     * Return the smallest element in this SortedSet.
     * 
     * pre: size() != 0
     * 
     * Big O Notation: O(1)
     * 
     * @return the smallest element in this SortedSet.
     */
    public E min() {
        // Precondition
        if (this.size() == 0) {
            throw new IllegalArgumentException("Theres is no elements in this Set.");
        }

        return this.myCon.get(0);
    }

    /**
     * Return the largest element in this SortedSet.
     * 
     * pre: size() != 0
     * 
     * Big O Notation: O(1)
     * 
     * @return the largest element in this SortedSet.
     */
    public E max() {

        // Precondition
        if (this.size() == 0) {
            throw new IllegalArgumentException("Theres is no elements in this Set.");
        }

        return this.myCon.get(this.size() - 1);
    }

    /**
     * Add an item to this set.
     * item != null
     * 
     * Big O Notation: O(N)
     * 
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation,
     *         false otherwise.
     */
    public boolean add(E item) {

        // Precondition
        if (item == null) {
            throw new IllegalArgumentException("The Item Parameter cannot be null.");
        }

        int left = 0;
        int right = this.size() - 1;

        // Finding the Approximately Correct Index
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            int comparedVal = item.compareTo(this.myCon.get(mid));
            if (comparedVal == 0) {
                return false;
            } else if (comparedVal < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        this.myCon.add(left, item);
        return true;
    }

    /**
     * A union operation. Add all items of otherSet that
     * are not already present in this set to this set.
     * 
     * Big O Notation: O(N) if otherSet is a SortedSet<E>; O(N * log(N)) if otherSet
     * is not a SortedSet<E>.
     * 
     * @param otherSet != null
     * @return true if this set changed as a result of this operation,
     *         false otherwise.
     */
    public boolean addAll(ISet<E> otherSet) {

        // Precondition
        if (otherSet == null) {
            throw new IllegalArgumentException("The Parameter Other Set cannot be Null.");
        }

        // Converts the otherSet to a Sorted Set
        SortedSet<E> sortedOther = getSortedSetFromUnsorted(otherSet);

        // Add all values
        Iterator<E> otherIt = sortedOther.iterator();
        Iterator<E> thisIt = this.iterator();

        int oldSize = this.size();
        final boolean IS_UNION = true;
        this.myCon = mergeArrays(thisIt, otherIt, IS_UNION);

        return oldSize != this.size();
    }

    /**
     * Determine if item is in this set. Using Binary Search
     * 
     * Big O Notation: O(log(N))
     * 
     * @param item element whose presence is being tested. item != null
     *             Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    public boolean contains(E item) {

        // Precondition
        if (item == null) {
            throw new IllegalArgumentException("The Parameter cannot be Null.");
        }

        return binarySearch(item) != -1;
    }

    /**
     * Determine if all of the elements of otherSet are in this set.
     * 
     * Big O Notation: O(N) if otherSet is a sortedSet<E>; O(N * log(n)) if other is
     * not a SortedSet<E>
     * 
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet,
     *         false otherwise.
     */
    public boolean containsAll(ISet<E> otherSet) {

        // Precondition
        if (otherSet == null) {
            throw new IllegalArgumentException("The Parameter cannot be Null.");
        }

        if (this.size() < otherSet.size()) {
            return false;
        }

        // Converts the otherSet to a Sorted Set
        SortedSet<E> sortedOther = getSortedSetFromUnsorted(otherSet);

        // Going in and checking the Values for containment
        Iterator<E> thisIt = this.iterator();
        Iterator<E> otherIt = sortedOther.iterator();

        E thisVal = getSafeIteratorNext(thisIt);
        E otherVal = getSafeIteratorNext(otherIt);

        while (thisVal != null && otherVal != null) {

            int comparedVal = thisVal.compareTo(otherVal);

            if (comparedVal == 0) {
                thisVal = getSafeIteratorNext(thisIt);
                otherVal = getSafeIteratorNext(otherIt);
            } else if (comparedVal < 0) {
                thisVal = getSafeIteratorNext(thisIt);
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * Make this set empty.
     * 
     * Big O Notation: O(1)
     */
    public void clear() {
        this.myCon = new ArrayList<>();
    }

    /**
     * Create a new set that is the difference of this set and otherSet. Return an
     * ISet of elements that are in this Set but not in otherSet. Also called the
     * relative complement. Example: If ISet A contains [X, Y, Z] and ISet B
     * contains [W, Z]then A.difference(B) would return an ISet with elements [X, Y]
     * while B.difference(A) would return an ISet with elements [W]. Neither this
     * set or otherSet are altered as a result of this operation.
     * 
     * Big O Notation: O(N) if otherSet is a SortedSet<E>; else O(N * log(N))
     * 
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
    public ISet<E> difference(ISet<E> otherSet) {

        // Precondition
        if (otherSet == null) {
            throw new IllegalArgumentException("The Parameter Cannot Equal Null");
        }

        SortedSet<E> diffSet = new SortedSet<>();

        SortedSet<E> otherSorted = getSortedSetFromUnsorted(otherSet);

        Iterator<E> thisIt = this.iterator();
        Iterator<E> otherIt = otherSorted.iterator();

        E thisCurrVal = getSafeIteratorNext(thisIt);
        E otherCurrVal = getSafeIteratorNext(otherIt);

        while (thisCurrVal != null && otherCurrVal != null) {
            int comparedVal = thisCurrVal.compareTo(otherCurrVal);
            if (comparedVal < 0) {
                diffSet.add(thisCurrVal);
                thisCurrVal = getSafeIteratorNext(thisIt);
            } else if (comparedVal > 0) {
                otherCurrVal = getSafeIteratorNext(otherIt);
            } else {
                thisCurrVal = getSafeIteratorNext(thisIt);
                otherCurrVal = getSafeIteratorNext(otherIt);
            }
        }

        while (thisCurrVal != null) {
            diffSet.add(thisCurrVal);
            thisCurrVal = getSafeIteratorNext(thisIt);
        }

        return diffSet;
    }

    /**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * 
     * Big O Notation: O(N) if other is a SortedSet<E>, else O(N^2)
     * 
     * @param other the object to compare to this set
     * @return true if other is a Set and has the same elements as this set
     */
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }

        if (!(other instanceof ISet<?>)) {
            return false;
        }

        ISet<?> otherSet = (ISet<?>) other;

        if (otherSet.size() != this.size()) {
            return false;
        }

        if (otherSet instanceof SortedSet<?>) {
            Iterator<E> thisIt = this.iterator();
            Iterator<?> otherIt = otherSet.iterator();

            while (thisIt.hasNext()) {
                if (!thisIt.next().equals(otherIt.next())) {
                    return false;
                }
            }

            return true;
        }

        return super.equals(other);
    }

    /**
     * create a new set that is the intersection of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * 
     * Big O Notation: O(N) if otherSet is SortedSet<E>; else O(N * log(N))
     * 
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     */
    public ISet<E> intersection(ISet<E> otherSet) {

        // Precondition
        if (otherSet == null) {
            throw new IllegalArgumentException("The Item Parameter cannot be null.");
        }

        SortedSet<E> result = new SortedSet<>();
        SortedSet<E> otherSortedSet = getSortedSetFromUnsorted(otherSet);

        Iterator<E> thisIt = this.iterator();
        Iterator<E> otherIt = otherSortedSet.iterator();

        final boolean IS_UNION = false;

        result.myCon = mergeArrays(thisIt, otherIt, IS_UNION);
        return result;
    }

    /**
     * Return an Iterator object for the elements of this set.
     * 
     * Big O Notation: O(1)
     * 
     * @return an Iterator object for the elements of this set
     */
    public Iterator<E> iterator() {
        return this.myCon.iterator();
    }

    /**
     * Remove the specified item from this set if it is present.
     * 
     * Big O Notation: O(N)
     * 
     * @param item the item to remove from the set. item may not equal null.
     * @return true if this set changed as a result of this operation,
     *         false otherwise
     */
    public boolean remove(E item) {
        // Precondition
        if (item == null) {
            throw new IllegalArgumentException("The Item Parameter cannot be null.");
        }

        int valIndex = this.binarySearch(item);
        if (valIndex != -1) {
            myCon.remove(valIndex);
            return true;
        }

        return false;
    }

    /**
     * Return the number of elements of this set.
     * 
     * @return the number of items in this set
     */
    public int size() {
        return this.myCon.size();
    }

    /**
     * Create a new set that is the union of this set and otherSet. Neither this set
     * or otherSet are altered as a result of this operation.
     * 
     * @param otherSet != null
     * @return - returns a set that is the union of this set and otherSet.
     */
    public ISet<E> union(ISet<E> otherSet) {

        // Precondition
        if (otherSet == null) {
            throw new IllegalArgumentException("You cannot use null as the parameter otherSet");
        }

        SortedSet<E> result = new SortedSet<E>();

        result.addAll(this);
        result.addAll(otherSet);

        return result;
    }

    /**
     * Merges two sorted iterators into an ArrayList based on either the Union or
     * Intersection operator based on the isUnion parameter.
     * 
     * @param thisIt  - an iterator over this sorted set's elements.
     * @param otherIt - an iterator over the other sorted set's elements.
     * @param isUnion - if true, uses makes the method add all elements from both
     *                iterator, else makes sure they have to appear in both
     *                iteators.
     * @return - An ArrayList Object that contains either the Union or Intersection
     *         of the two iterators.
     */
    private ArrayList<E> mergeArrays(Iterator<E> thisIt, Iterator<E> otherIt, boolean isUnion) {

        ArrayList<E> results = new ArrayList<>();
        E thisCurrVal = getSafeIteratorNext(thisIt);
        E otherCurrVal = getSafeIteratorNext(otherIt);

        // Add based on the comparison of the current values
        while (thisCurrVal != null && otherCurrVal != null) {

            int comparedVal = thisCurrVal.compareTo(otherCurrVal);

            // Check for Equality, Greater than, or Less Than
            if (comparedVal == 0) {
                results.add(thisCurrVal);
                thisCurrVal = getSafeIteratorNext(thisIt);
                otherCurrVal = getSafeIteratorNext(otherIt);
            } else if (comparedVal > 0) {
                if (isUnion) {
                    results.add(otherCurrVal);
                }
                otherCurrVal = getSafeIteratorNext(otherIt);
            } else {
                if (isUnion) {
                    results.add(thisCurrVal);
                }
                thisCurrVal = getSafeIteratorNext(thisIt);
            }
        }

        // Add the Rest of the Value based on whether it is a union call or not.
        while (thisCurrVal != null && isUnion) {
            results.add(thisCurrVal);
            thisCurrVal = getSafeIteratorNext(thisIt);
        }

        while (otherCurrVal != null && isUnion) {
            results.add(otherCurrVal);
            otherCurrVal = getSafeIteratorNext(otherIt);
        }

        return results;
    }

    /**
     * Searches the sorted set for the value using the Binary Search Algorithm
     * 
     * @param val - the value to be searched for in the search.
     * @return - The index value of the val index or -1 if it is not found.
     */
    private int binarySearch(E val) {

        if (this.size() == 0) {
            return -1;
        }

        // Search through the Array using Binary Search
        int start = 0;
        int end = this.size() - 1;

        while (start <= end) {
            int mid = start + ((end - start) / 2);

            if (this.myCon.get(mid).equals(val)) {
                return mid;
            } else if (this.myCon.get(mid).compareTo(val) < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Safely get the next element from an iterator without throwing an exception.
     * 
     * @param it - an iterator over a sorted set's elements.
     * @return - The next value of iterator or null if it is null or iterator does
     *         not have a next value.
     */
    private E getSafeIteratorNext(Iterator<E> it) {

        if (it == null || !it.hasNext()) {
            return null;
        }

        return it.next();
    }

    /**
     * Creates a SortedSet<E> Representation of an ISet<E> Object.
     * 
     * @param otherSet - The set needed to be converted
     * @return - The SortedSet<E> Representation of the the otherSet Object.
     */
    private SortedSet<E> getSortedSetFromUnsorted(ISet<E> otherSet) {
        
        SortedSet<E> otherSortedSet;

        if (!(otherSet instanceof SortedSet<?>)) {
            otherSortedSet = new SortedSet<>(otherSet);
        } else {
            otherSortedSet = (SortedSet<E>) otherSet;
        }

        return otherSortedSet;
    }

}