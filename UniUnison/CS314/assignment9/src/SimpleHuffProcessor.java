/*  Student information for assignment:
 *
 *  On MY honor, Pheanouk Hun,
 *  this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1: Pheanouk Hun
 *  UTEID: ph23434
 *  email address: ph23434@eid.utexas.edu
 *
 *  Grader name:
 *  Section number:
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class SimpleHuffProcessor implements IHuffProcessor {

    private IHuffViewer myViewer;
    private int[] freqs;
    private Map<Integer, String> huffCodes;
    private HuffManCodeTree huffTree;
    private int headerFormat;

    /**
     * Preprocess data so that compression is possible --- count characters/create tree/store
     * state so that a subsequent call to compress will work. The InputStream is not a
     * BitInputStream, so wrap it int one as needed. Note, to determine the number of bits saved,
     * the number of bits written includes ALL bits that will be written including the magic 
     * number, the header format number, the header to reproduce the tree, AND the actual data.
     *
     * @param in - is the stream which could be subsequently compressed
     * @param headerFormat - a constant from IHuffProcessor that determines what kind of
     *                       header to use, standard count format, standard tree format, or
     *                       possibly some format added in the future.
     * @return - number of bits saved by compression or some other measure
     * @throws IOException if an error occurs while reading from the input file.
     */
    public int preprocessCompress(InputStream in, int headerFormat) throws IOException {
        // Initialize all Necessary Variables
        this.headerFormat = headerFormat;
        freqs = new int[ALPH_SIZE];
        BitInputStream bitIn = new BitInputStream(in);

        // Create the Frequency Table
        int symbol = bitIn.read();
        int originalSize = 0;
        while (symbol != -1) {
            freqs[symbol]++;
            originalSize += BITS_PER_WORD;
            symbol = bitIn.read();
        }
        bitIn.close();
        freqs[PSEUDO_EOF] = 1;

        // Create the Huffman Code Tree and getting the Huffman Codes
        int compressedBits = 2 * BITS_PER_INT;

        huffTree = new HuffmanCodeTree(freqs);
        huffCodes = huffTree.getCodes();

        return compressedBits - originalSize;
    }

    /**
     * Compresses input to output, where the same InputStream has previously
     * been pre-processed via preprocessCompress storing state used by this call.
     * pre: preprocessCompress must be called before this method
     *
     * @param in - is the stream being compressed (NOT a BitInputStream)
     * @param out - is bound to a file/stream to which bits are written
     *              for the compressed file (not a BitOutputStream)
     * @param force - if this is true create the output file even if it is larger than the
     *                input file. If this is false do not create the output file if it is larger
     *                than the input file.
     * @return - the number of bits written.
     * @throws - IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
        throw new IOException("compress is not implemented");
        //return 0;
    }

    /**
     * Uncompress a previously compressed stream in, writing the
     * uncompressed bits/data to out.
     *
     * @param in - is the previously compressed data (not a BitInputStream)
     * @param out - is the uncompressed file/stream
     * @return - the number of bits written to the uncompressed file/stream
     * @throws IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int uncompress(InputStream in, OutputStream out) throws IOException {
        throw new IOException("uncompress not implemented");
        //return 0;
    }

    public void setViewer(IHuffViewer viewer) {
        myViewer = viewer;
    }

    private void showString(String s) {
        if (myViewer != null) {
            myViewer.update(s);
        }
    }

    /**
     * A Huffman code tree that stores the characters and their corresponding codes.
     */
    private class HuffManCodeTree {

        private TreeNode root;
        private int frequency;

        /**
         * Based on the frequency array, create a Huffman code tree.
         *
         * @param freqs - the Frequency Array of Each Character
         */
        public HuffManCodeTree(int[] freqs) {
            root = buildTree(freqs);
        }

        /**
         * Returns the root node of the Huffman code tree.
         *
         * @return - the root node of the tree
         */
        public TreeNode getRoot() {
            return root;
        }

        /**
         * Builds the Huffman code tree from the given array of frequencies.
         *
         * @param freqs - the Frequency Array of Each Character
         * @return - the root node of the tree
         */
        private TreeNode buildTree(int[] freqs) {
            // Create the Priority Queue and add all the nodes
            PriorityQueue queue = new PriorityQueue();
            for (int i = 0; i < freqs.length; i++) {
                queue.add(new TreeNode(i, freqs[i]));
            }

            // Build the Tree with the Queue
            while (queue.size() > 1) {
                TreeNode left = queue.pop();
                TreeNode right = queue.pop();
                TreeNode parent = new TreeNode(null, left.getFrequency() + right.getFrequency());
                parent.setLeft(left);
                parent.setRight(right);
                queue.add(parent);
            }

            // Return the root of the tree
            root = queue.pop();
            return root;
        }

        /**
         * Create and return a map of the characters codes based on the Huffman code tree.
         *
         * @return - a map of character codes
         */
        public Map<Integer, String> getCodes() {
            Map<Integer, String> codes = new HashMap<>();
            getCodeHelper(codes, root, "");
            return codes;
        }

        /**
         * (HELPER) method to that recurse through the character codes map.
         *
         * @param codes - the map that stores character codes
         * @param currNode - the current node being processed
         * @param currVal - the current code value being added
         */
        private void getCodeHelper(Map<Integer, String> codes, TreeNode currNode, String currVal) {
            // Recursive Case: Not a Leaf Node
            if (!currNode.isLeaf()) {
                getCodeHelper(codes, currNode.getLeft(), currVal + "0");
                getCodeHelper(codes, currNode.getRight(), currVal + "1");
            } else {
                // Base Case: Leaf Node
                codes.put(currNode.getValue(), currVal);
            }
        }
        
        /**
         * Returns the number of bits used to store the tree in the header.
         *
         * @return - number of bits used to store the tree in the header
         */
        public int getTreeBitSize() {
            return ;
        }
    }

    /**
     * A priority queue that stores elements of type E and orders them based on their
     * Priority value with lower being higher priority.
     */
    private class PriorityQueue<E extends Comparable<E>> {

        private ArrayList<E> queue;

        /**
         * Creates an empty priority queue.
         */
        public PriorityQueue() {
            queue = new ArrayList<>();
        }

        /**
         * Adds a node to the priority queue based on the Priority.
         *
         * @param node - the node to add
         */
        public void add(E node) {
            // Add to empty queue
            if (queue.isEmpty()) {
                queue.add(node);
            } else {
                // Add to Fair Priority Position
                int i = 0;
                while (i < queue.size() && node.compareTo(queue.get(i)) > 0) {
                    i++;
                }
                queue.add(i, node);
            }
        }

        /**
         * Removes and returns the node with the lowest number equals higher.
         *
         * @return - the node with the highest priority
         */
        public E pop() {
            E val = queue.get(0);
            queue.remove(0);
            return val;
        }

        /**
         * Returns the number of nodes in the queue.
         *
         * @return - the number of nodes in the queue
         */
        public int size() {
            return queue.size();
        }
    }
}
