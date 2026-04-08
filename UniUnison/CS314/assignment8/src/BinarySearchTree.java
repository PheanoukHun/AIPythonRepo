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
 * @param <E> The data type of the elements of this BinarySearchTree. Must
 *            implement Comparable or inherit from a class that implements
 *            Comparable.
 *
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

    private BSTNode<E> root;
    private int size;

    /**
     * Add the specified item to this Binary Search Tree if it is not already
     * present.
     * pre: value != null
     * post: Add value to this tree if not already present. Return true if this tree
     * changed as a result of this method call, false otherwise.
     * 
     * @author - got Help from the Lectures
     * 
     * @param value the value to add to the tree
     * @return false if an item equivalent to value is already present in the tree,
     *         return true if value is added to the tree and size() = old size() + 1
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

    /**
     * A helper method that adds a value to its appropriate place if it is not
     * already found inside the BST yet.
     * 
     * @author - Got Help from the Lectures
     * 
     * @param currNode - The current Node the method is checking
     * @param val      - The value that will be added
     * @return - Returns the newly modified Node.
     */
    private BSTNode<E> addHelper(BSTNode<E> currNode, E val) {

        // Base Case
        if (currNode == null) {
            size++;
            return new BSTNode<>(val);
        }

        // Recursive Case
        int cmp = currNode.data.compareTo(val);

        if (cmp > 0) {
            currNode.left = addHelper(currNode.left, val);
        } else if (cmp < 0) {
            currNode.right = addHelper(currNode.right, val);
        }

        return currNode;
    }

    /**
     * Remove a specified item from this Binary Search Tree if it is present.
     * pre: value != null
     * post: Remove value from the tree if present, return true if this tree changed
     * as a result of this method call, false otherwise.
     * 
     * @author - Got Help from the Lectures
     * 
     * @param value the value to remove from the tree if present
     * @return false if value was not present
     *         returns true if value was present and size() = old size() - 1
     */
    public boolean remove(E value) {

        // Precondition
        if (value == null) {
            throw new IllegalArgumentException("Value Cannot be Null.");
        }

        // Removing Value
        int oldSize = size;
        root = removeHelper(root, value);
        return size != oldSize;
    }

    /**
     * A helper method that removes a value from the BST if it is found inside the
     * tree.
     * 
     * @author - Got Help from the Lectures
     * 
     * @param currNode - The Current Node the method is checking.
     * @param val      - The value that will be added
     * @return - Returns the newly modified node.
     */
    private BSTNode<E> removeHelper(BSTNode<E> currNode, E val) {

        // Base Case 1: Did Not Find Data Value
        if (currNode == null) {
            return null;
        }

        int cmp = currNode.data.compareTo(val);

        // Recursive Cases
        if (cmp > 0) {
            currNode.left = removeHelper(currNode.left, val);
        } else if (cmp < 0) {
            currNode.right = removeHelper(currNode.right, val);

            // Base Case
        } else {
            size--;

            // Removing a Leaf Node
            if (currNode.left == null && currNode.right == null) {
                return null;
            } else if (currNode.left == null) {
                // Removing a Parent Node with only a Right Chold Node
                return currNode.right;
            } else if (currNode.right == null) {
                // Removing a Parent Node with Only a Left Child Node
                return currNode.left;
            } else {
                // Replace the Value of the Node Value
                // and Delete the old Node of the same value.
                currNode.data = maxHelper(currNode.left);
                currNode.left = removeHelper(currNode.left, currNode.data);
            }
        }

        return currNode;
    }

    /**
     * Check to see if the specified element is in this Binary Search Tree.
     * pre: value != null
     * post: return true if value is present in tree, false otherwise
     * 
     * @param value the value to look for in the tree
     * @return true if value is present in this tree, false otherwise
     */
    public boolean isPresent(E value) {

        // Precondition
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be Empty.");
        }

        // Checking Containing Value
        return isPresentHelper(root, value);
    }

    /**
     * A helper method that helps check to see if the specified element is inside
     * the BST.
     * 
     * @param currNode - The Node it has to recurse through
     * @param val      - The val that needs to be found
     * @return - Returns a Boolean value that based on whether the value is found
     *         within the free or not.
     */
    private boolean isPresentHelper(BSTNode<E> currNode, E val) {

        // Base Case: Did Not Find Value
        if (currNode == null) {
            return false;
        }

        // Recursive Case
        int cmp = currNode.data.compareTo(val);

        if (cmp > 0) {
            // Recurse Left Side
            return isPresentHelper(currNode.left, val);
        } else if (cmp < 0) {
            // Recurse Right Side
            return isPresentHelper(currNode.right, val);
        } else {
            // Base Case: Found Value
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

        // Height of Zero
        if (size == 0) {
            return -1;
        }

        // Gets the Height of Tree
        return heightHelper(root);
    }

    /**
     * A Helper method to get the height of the BST
     * 
     * @param currNode -
     * @return - An Integer value repesenting the height of the tree.
     */
    private int heightHelper(BSTNode<E> currNode) {

        if (currNode == null) {
            return -1;
        }

        return 1 + Math.max(heightHelper(currNode.left), heightHelper(currNode.right));
    }

    /**
     * Return a list of all the elements in this Binary Search Tree.
     * pre: none
     * post: return a List object with all data from the tree in ascending order. If
     * the tree is empty return an empty List
     * 
     * @return a List object with all data from the tree in sorted order if the tree
     *         is empty return an empty List
     */
    public List<E> getAll() {
        List<E> result = new ArrayList<>();
        getAllHelper(root, result);
        return result;
    }

    /**
     * A Helper Method that Basically Turns the BST into a List.
     * 
     * @param currNode - The Current BSTNode the Method has to recursive search
     *                 through.
     * @param allNodes - A List of all the Data Values found inside the BST
     */
    private void getAllHelper(BSTNode<E> currNode, List<E> allNodes) {

        // Recursive Case
        if (currNode != null) {
            getAllHelper(currNode.left, allNodes);
            allNodes.add(currNode.data);
            getAllHelper(currNode.right, allNodes);
        }
    }

    /**
     * return the maximum value in this binary search tree.
     * pre: size() > 0
     * post: return the largest value in this Binary Search Tree
     * 
     * @return the maximum value in this tree
     */
    public E max() {

        // Precondition
        if (size == 0) {
            throw new IllegalStateException("The Size Must Be Greater than 0.");
        }

        // Retrieve the Value
        return maxHelper(root);
    }

    /**
     * A Recursive Helper Method to Get the Maximum Value in the Binary Search Tree
     * 
     * @param currNode - The Current BSTNode the Method has to recursive search
     *                 through.
     * @return - The value of the Largest element.
     */
    private E maxHelper(BSTNode<E> currNode) {

        // Base Case
        if (currNode.right == null) {
            return currNode.data;
        }

        // Recursive Case
        return maxHelper(currNode.right);
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

        // Precondition
        if (size == 0) {
            throw new IllegalStateException("The Size Must Be Greater than 0.");
        }

        // Retrieve Value
        return minHelper(root);
    }

    /**
     * A Recursive Helper Method to Get the Minimum Value in the Binary Search Tree
     * 
     * @param currNode - The Current BSTNode the Method has to recursive search
     *                 through.
     * @return - The value of the smallest element.
     */
    private E minHelper(BSTNode<E> currNode) {

        // Base Case
        if (currNode.left == null) {
            return currNode.data;
        }

        // Recursive Case
        return minHelper(currNode.left);
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

        // Preconditions
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be Null");
        }

        // Tree is Empty
        if (root == null) {
            root = new BSTNode<>(data);
            size++;
            return true;
        }

        BSTNode<E> node = root;
        
        while (node != null) {
            
            int cmp = node.data.compareTo(data);
            
            if (cmp > 0) {
                
                if (node.left == null) {
                    node.left = new BSTNode<>(data);
                    size++;
                    return true;
                }
                
                node = node.left;

            } else if (cmp < 0) {
                
                if (node.right == null) {
                    node.right = new BSTNode<>(data);
                    size++;
                    return true;
                }

                node = node.right;

            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * Return the "kth" element in this Binary Search Tree. If kth = 0 the smallest
     * value (minimum) is returned. If kth = 1 the second smallest value is
     * returned, and so forth.
     * 
     * pre: 0 <= kth < size()
     * 
     * @param kth indicates the rank of the element to get
     * @return the kth value in this Binary Search Tree
     */
    public E get(int kth) {

        // Preconditions
        if (kth < 0 || kth >= size) {
            throw new IllegalArgumentException("The kth Value must be within "
                    + "the size of the BST");
        }

        if (kth == 0) {
            return min();
        } else if (kth == size - 1) {
            return max();
        }

        return getHelper(root, new int[]{kth});
    }

    private E getHelper(BSTNode<E> currNode, int[] kth) {
        
        // Base Case: Went Passed Leaf Nodes
        if (currNode == null) {
            return null;
        }

        E left = getHelper(currNode.left, kth);
        if (left != null) {
            return left;
        }

        if (kth[0] == 0) {
            return currNode.data;
        }

        kth[0]--;

        return getHelper(currNode.right, kth);
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
        if (currNode != null && currNode.data.compareTo(val) >= 0) {
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

    /**
     * 
     * @param greaterVals
     * @param currNode
     * @param val
     */
    private void getAllGreaterThanHelper(List<E> greaterVals, BSTNode<E> currNode, E val) {
        if (currNode != null && currNode.data.compareTo(val) <= 0) {
            getAllGreaterThanHelper(greaterVals, currNode.right, val);
        } else if (currNode != null) {
            getAllGreaterThanHelper(greaterVals, currNode.left, val);
            greaterVals.add(currNode.data);
            getAllGreaterThanHelper(greaterVals, currNode.right, val);
        }
    }

    /**
     * Find the number of nodes in this tree at the specified depth.
     * pre: none
     * 
     * @param d The target depth.
     * @return The number of nodes in this tree at a depth equal to
     *         the parameter d.
     */
    public int numNodesAtDepth(int d) {

        if (d < 0) {
            throw new IllegalArgumentException("");
        }

        return nodesAtDepthHelper(root, d);
    }

    private int nodesAtDepthHelper(BSTNode<E> node, int d) {
        
        // Base Case
        
        // Found Leaf Nodes
        if (node == null) {
            return 0;
        }

        // Found Node at the Right Depth
        if (d == 0) {
            return 1;

        } 
        

        // Recursive Cases
        return nodesAtDepthHelper(node.left, d - 1) + nodesAtDepthHelper(node.right, d - 1);
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