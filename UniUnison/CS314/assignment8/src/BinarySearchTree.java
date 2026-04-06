/*
 * BinarySearchTree.java - CS 314 Assignment 8
 *
 * By signing my name below, I affirm that this assignment is my own work. I
 * have neither given nor received unauthorized assistance on this assignment.
 *
 * Name: Pheanouk Hun
 * Email address: ph23434@eid.utexas.edu
 * UTEID: ph23434
 */

import java.util.List;
import java.util.ArrayList;

/**
 * Shell for a binary search tree class.
 * 
 * @author scottm
 * @param <E> The data type of the elements of this BinarySearchTree.
 *            Must implement Comparable or inherit from a class that implements
 *            Comparable.
 *
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

    private BSTNode<E> root;
    private int size;

    /**
     * Add the specified item to this Binary Search Tree if it is not already
     * present. pre: value != null post: Add value to this tree if not already
     * present. Return true if this tree changed as a result of this method call,
     * false otherwise.
     * 
     * @param value the value to add to the tree
     * @return false if an item equivalent to value is already present
     *         in the tree, return true if value is added to the tree and size() =
     *         old size() + 1
     */
    public boolean add(E value) {
        
        // Precondition
        if (value == null) {
            throw new IllegalArgumentException("Value Cannot be Null.");
        }
        
        // Add Values
        int oldSize = size;
        root = addHelper(root, value);
        return oldSize != size;
    }

    private BSTNode<E> addHelper(BSTNode<E> currNode, E val) {

        if (currNode == null) {
            size++;
            return new BSTNode<E>(val);
        }

        int cmp = currNode.data.compareTo(val);
        if (cmp > 1) {
            currNode.left = addHelper(currNode.left, val);
        } else if (cmp < 1) {
            currNode.right = addHelper(currNode.right, val);
        }

        return currNode;
    }

    /**
     * Remove a specified item from this Binary Search Tree if it is present. pre:
     * value != null post: Remove value from the tree if present, return true if
     * this tree changed as a result of this method call, false otherwise.
     * 
     * @param value the value to remove from the tree if present
     * @return false if value was not present
     *         returns true if value was present and size() = old size() - 1
     */
    public boolean remove(E value) {
        return true;
    }

    /**
     * Check to see if the specified element is in this Binary Search Tree. pre:
     * value != null post: return true if value is present in tree, false otherwise
     * 
     * @param value the value to look for in the tree
     * @return true if value is present in this tree, false otherwise
     */
    public boolean isPresent(E value) {
        return isPresentHelper(root, value);
    }

    private boolean isPresentHelper(BSTNode<E> currNode, E val) {

        if (currNode == null) {
            return false;
        }

        int cmp = currNode.data.compareTo(val);

        if (cmp > 1) {
            return isPresentHelper(currNode.left, val);
        } else if (cmp < 1) {
            return isPresentHelper(currNode.right, val);
        } else {
            return true;
        }
    }

    /**
     * Return how many elements are in this Binary Search Tree.
     * pre: none
     * post: return the number of items in this tree
     * 
     * @return the number of items in this Binary Search Tree
     */
    public int size() {
        return size;
    }

    /**
     * return the height of this Binary Search Tree. If the tree is empty return -1,
     * otherwise return the height of the tree
     * pre: none
     * post: return the height of this tree.
     * 
     * @return the height of this tree or -1 if the tree is empty
     */
    public int height() {

        if (size == 0) {
            return -1;
        }

        return -2;
    }

    /**
     * Return a list of all the elements in this Binary Search Tree.
     * <br>
     * pre: none<br>
     * post: return a List object with all data from the tree in ascending order.
     * If the tree is empty return an empty List
     * 
     * @return a List object with all data from the tree in sorted order
     *         if the tree is empty return an empty List
     */
    public List<E> getAll() {
        
        
        return null;
    }

    /**
     * return the maximum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the largest value in this Binary Search Tree
     * 
     * @return the maximum value in this tree
     */
    public E max() {

        if (size == 0) {
            throw new IllegalStateException("The Size Must Be Greater than 0.");
        }

        return maxHelper(root);
    }

    private E maxHelper(BSTNode<E> node) {
        if (node.right == null) {
            return node.data;
        }
        return maxHelper(node.right);
    }

    /**
     * return the minimum value in this binary search tree.
     * 
     * pre: size() > 0
     * post: return the smallest value in this Binary Search Tree
     * 
     * @return the minimum value in this tree
     */
    public E min() {

        if (size == 0) {
            throw new IllegalStateException("The Size Must Be Greater than 0.");
        }

        return minHelper(root);
    }

    private E minHelper(BSTNode<E> node) {
        if (node.left == null) {
            return node.data;
        }
        return minHelper(node.left);
    }

    /**
     * An add method that implements the add algorithm iteratively instead of
     * recursively.
     * pre: data != null
     * post: if data is not present add it to the tree, otherwise do nothing.
     * 
     * @param data the item to be added to this tree
     * @return true if data was not present before this call to add,
     *         false otherwise.
     */
    public boolean iterativeAdd(E data) {

        BSTNode<E> trail = root;
        BSTNode<E> search = root.data.compareTo(data) > 1 ? root.left : root.right;

        while (search != null && search != null) {

            int cmp = search.data.compareTo(data);
            trail = search;

            if (cmp > 1) {
                search = search.left;
            } else if (cmp < 1) {
                search = search.right;
            } else {
                return false;
            }
        }

        if (trail.data.compareTo(data) > 1) {
            trail.left = new BSTNode<>(data);
        } else {
            trail.right = new BSTNode<>(data);
        }

        return true;
    }

    /**
     * Return the "kth" element in this Binary Search Tree. If kth = 0 the
     * smallest value (minimum) is returned.
     * If kth = 1 the second smallest value is returned, and so forth.
     * <br>
     * pre: 0 <= kth < size()
     * 
     * @param kth indicates the rank of the element to get
     * @return the kth value in this Binary Search Tree
     */
    public E get(int kth) {
        
        if (kth == 0) {
            return min();
        } else if (kth == size - 1) {
            return max();
        }
        
        return null; 
    }

    /**
     * Return a List with all values in this Binary Search Tree
     * that are less than the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * 
     * @param value the cutoff value
     * @return a List with all values in this tree that are less than
     *         the parameter value. If there are no values in this tree less
     *         than value return an empty list. The elements of the list are
     *         in ascending order.
     */
    public List<E> getAllLessThan(E value) {
        List<E> results = new ArrayList<>();
        getAllLessThanHelper(results, root, value);
        return results;
    }

    private void getAllLessThanHelper(List<E> lessVals, BSTNode<E> currNode, E val) {
        if (currNode != null && currNode.data.compareTo(val) > 1) {
            getAllLessThanHelper(lessVals, currNode.left, val);
        } else if (currNode != null) {
            getAllLessThanHelper(lessVals, currNode.left, val);
            lessVals.add(currNode.data);
            getAllLessThanHelper(lessVals, currNode.right, val);
        }
    }

    /**
     * Return a List with all values in this Binary Search Tree
     * that are greater than the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * 
     * @param value the cutoff value
     * @return a List with all values in this tree that are greater than the
     *         parameter value. If there are no values in this tree greater than
     *         value return an empty list. The elements of the list are in ascending
     *         order.
     */
    public List<E> getAllGreaterThan(E value) {
        List<E> results = new ArrayList<>();
        getAllGreaterThanHelper(results, root, value);
        return results;
    }

    private void getAllGreaterThanHelper(List<E> lessVals, BSTNode<E> currNode, E val) {
        if (currNode != null && currNode.data.compareTo(val) < 1) {
            getAllLessThanHelper(lessVals, currNode.right, val);
        } else if (currNode != null) {
            getAllLessThanHelper(lessVals, currNode.left, val);
            lessVals.add(currNode.data);
            getAllLessThanHelper(lessVals, currNode.right, val);
        }
    }

    /**
     * Find the number of nodes in this tree at the specified depth.
     * <br>
     * pre: none
     * 
     * @param d The target depth.
     * @return The number of nodes in this tree at a depth equal to
     *         the parameter d.
     */
    public int numNodesAtDepth(int d) {
        return -1;
    }

    /**
     * Prints a vertical representation of this tree.
     * The tree has been rotated counter clockwise 90
     * degrees. The root is on the left. Each node is printed
     * out on its own row. A node's children will not necessarily
     * be at the rows directly above and below a row. They will
     * be indented three spaces from the parent. Nodes indented the
     * same amount are at the same depth.
     * <br>
     * pre: none
     */
    public void printTree() {
        printTree(root, "");
    }

    private void printTree(BSTNode<E> n, String spaces) {
        if (n != null) {
            printTree(n.getRight(), spaces + "  ");
            System.out.println(spaces + n.getData());
            printTree(n.getLeft(), spaces + "  ");
        }
    }

    private static class BSTNode<E extends Comparable<? super E>> {
        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        public BSTNode() {
            this(null);
        }

        public BSTNode(E initValue) {
            this(null, initValue, null);
        }

        public BSTNode(BSTNode<E> initLeft,
                E initValue,
                BSTNode<E> initRight) {
            data = initValue;
            left = initLeft;
            right = initRight;
        }

        public E getData() {
            return data;
        }

        public BSTNode<E> getLeft() {
            return left;
        }

        public BSTNode<E> getRight() {
            return right;
        }

        public void setData(E theNewValue) {
            data = theNewValue;
        }

        public void setLeft(BSTNode<E> theNewLeft) {
            left = theNewLeft;
        }

        public void setRight(BSTNode<E> theNewRight) {
            right = theNewRight;
        }
    }
}