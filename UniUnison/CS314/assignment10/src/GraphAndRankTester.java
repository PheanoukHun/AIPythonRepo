/*
 * GraphAndRankTester.java - CS 314 Assignment 10
 *
 * By signing my name below, I affirm that this assignment is my own work. I
 * have neither given nor received unauthorized assistance on this assignment.
 *
 * Name 1: Pheanouk Hun
 * Email address 1: ph23434@eid.utexas.edu
 * UTEID 1: ph23434
 */

/*
 * Question.
 *
 * 1. The assignment presents three ways to rank teams using graphs.
 * The results, especially for the last two methods are reasonable.
 * However if all results from all college football teams are included
 * some unexpected results occur.
 *
 * Explain the unexpected results. You may
 * have to do some research on the various college football divisions to
 * make an informed answer. (What are the divisions within college
 * football? Who do teams play? How would this affect the
 * structure of the graph?)
 */

public class GraphAndRankTester {

    /**
     * Runs tests on Graph classes and FootballRanker class.
     * Experiments involve results from college football
     * teams. Central nodes in the graph are compared to
     * human rankings of the teams.
     *
     * @param args None expected.
     */
    public static void main(String[] args) {
        myTestCases();
    }

    /**
     * A HELPER METHOD created to print the test cases.
     *
     * @param testName - The Name of the Test cases
     * @param correct  - A boolean value that directs the method to whether the
     *                 tests passed or not.
     * @param descr    - A description of the method.
     * @param testNum  - The number of the test.
     */
    private static void printResults(String testName, boolean correct, String descr, int testNum) {

        System.out.println(testName + "Test Number " + testNum + ":");
        System.out.println(" * " + descr);

        if (correct) {
            System.out.println(" * TEST PASSED");
        } else {
            System.out.println(" * TEST FAILED");
        }

        System.out.println();
    }

    private static void myTestCases() {
        // Variable Setup
        String[][] edges = {
                { "A", "B", "2.0" },
                { "B", "C", "1.0" },
                { "A", "C", "5.0" },
                { "C", "D", "3.0" },
                { "D", "E", "1.0" },
                { "E", "A", "2.0" },
                { "B", "D", "4.0" },
                { "F", "G", "1.0" },
                { "G", "H", "1.0" },
                { "F", "H", "3.0" }
        };

        Graph g = getGraph(edges, true);
        int testNum = 1;

        // Dijkstra Algorithm Test:
        String testName = "Testing Dijkstra";
        System.out.println("\n" + testName + ":\n");

        // Test 1: Shortest weighted path from A to E
        g.dijkstra("A");
        String actual = g.findPath("E").toString();
        String expected = "[A, B, D, E]";
        printResults(testName, actual.equals(expected), "Dijkstra's Path from A to E", testNum);
        testNum++;

        // Test 2: Shortest weighted path from F to H
        g.dijkstra("F");
        actual = g.findPath("H").toString();
        expected = "[F, G, H]";
        printResults(testName, actual.equals(expected), "Dijkstra's Path from F to H", testNum);
        testNum++;

        // Unweighted Find All Paths
        testName = "Testing FindAllPaths (Unweighted)";
        System.out.println(testName + ":\n");
        g.findAllPaths(false);

        // Test 3: Unweighted Diameter
        int unweightedDiam = g.getDiameter();
        printResults(testName, unweightedDiam == 4, "Diameter Check of Graph", testNum);
        testNum++;

        // Test 4: Check to see info for F.
        boolean foundF = false;
        for (AllPathsInfo info : g.getAllPaths()) {
            if (info.getName().equals("F")) {
                actual = info.getNumPaths() + " paths, avg edge " + info.getAveCost();
                expected = "2 paths, avg edge 1.0";
            }
        }

        printResults(testName, !foundF && actual.equals(expected), "Info for Node F", testNum);
        testNum++;

        // Weighted Find All Paths
        testName = "Testing FindAllPaths (weighted)";
        System.out.println(testName + ":\n");
        g.findAllPaths(true);

        // Test 5: Weighted Cost of Longest Shortest Path
        double weightedMaxCost = g.costOfLongestShortestPath();
        printResults(testName, weightedMaxCost == 8.0,
                "Weighted Cost of Longest Shortest Path", testNum);
        testNum++;

        // Test 6: Weighted AllPathsInfo for Vertex A
        foundF = false;
        for (AllPathsInfo info : g.getAllPaths()) {
            if (info.getName().equals("F")) {
                actual = info.getNumPaths() + " paths, avg edge " + info.getAveCost();
                expected = "2 paths, avg edge 1.0";
            }
        }

        printResults(testName, !foundF && actual.equals(expected), "Info for Node F", testNum);
    }

    // return a Graph based on the given edges
    private static Graph getGraph(String[][] edges, boolean directed) {
        Graph result = new Graph();
        for (String[] edge : edges) {
            result.addEdge(edge[0], edge[1], Double.parseDouble(edge[2]));
            // If edges are for an undirected graph add edge in other direction too.
            if (!directed) {
                result.addEdge(edge[1], edge[0], Double.parseDouble(edge[2]));
            }
        }
        return result;
    }
}
