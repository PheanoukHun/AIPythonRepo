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

public class SimpleHuffProcessor implements IHuffProcessor {

    private IHuffViewer myViewer;
    private int[] freqs;
    private Map<Integer, String> huffCodes;
    private HuffManCodeTree huffTree;
    private int headerFormat;

    /**
     * Preprocess data so that compression is possible ---
     * count characters/create tree/store state so that
     * a subsequent call to compress will work. The InputStream
     * is <em>not</em> a BitInputStream, so wrap it int one as needed.
     * @param in is the stream which could be subsequently compressed
     * @param headerFormat a constant from IHuffProcessor that determines what kind of
     * header to use, standard count format, standard tree format, or
     * possibly some format added in the future.
     * @return number of bits saved by compression or some other measure
     * Note, to determine the number of
     * bits saved, the number of bits written includes
     * ALL bits that will be written including the
     * magic number, the header format number, the header to
     * reproduce the tree, AND the actual data.
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

        return originalSize;
    }

    /**
     * Compresses input to output, where the same InputStream has
     * previously been pre-processed via <code>preprocessCompress</code>
     * storing state used by this call.
     * <br> pre: <code>preprocessCompress</code> must be called before this method
     * @param in is the stream being compressed (NOT a BitInputStream)
     * @param out is bound to a file/stream to which bits are written
     * for the compressed file (not a BitOutputStream)
     * @param force if this is true create the output file even if it is larger than the input
     * file. If this is false do not create the output file if it is larger than the input file.
     * @return the number of bits written.
     * @throws IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
        throw new IOException("compress is not implemented");
        //return 0;
    }

    /**
     * Uncompress a previously compressed stream in, writing the
     * uncompressed bits/data to out.
     * @param in is the previously compressed data (not a BitInputStream)
     * @param out is the uncompressed file/stream
     * @return the number of bits written to the uncompressed file/stream
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

    private class HuffManCodeTree {

        private TreeNode root;

        public HuffManCodeTree(int[] freqs) {
            root = buildTree(freqs);
        }

        public TreeNode getRoot() {
            return root;
        }

        private TreeNode buildTree(int[] freqs) {
            CustomPriorityQueue queue = new CustomPriorityQueue();
            for (int i = 0; i < freqs.length; i++) {
                queue.add(new TreeNode(i, freqs[i]));
            }
        }

        private class CustomPriorityQueue<E> {}
    }
}
