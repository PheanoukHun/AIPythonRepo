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
        studentTests();
    }

    /**
     *
     * @param testName
     * @param correct
     * @param descr
     * @param testNum
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

    private static void studentTests() {
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
                { "F", "H", "3.0" },
                { "E", "F", "2.0" },
        };

        Graph g = getGraph(edges, true);
        int testNum = 1;

        // Dijkstra Test 1:
        System.out.println("\nTesting Dijkstra:");

        // Test 1: Shortest weighted path from A to E
        g.dijkstra("A");
        String actual = g.findPath("E").toString();
        String expected = "[A, B, D, E]";
        printResults("Dijkstra Test", actual.equals(expected),
                "Dijkstra's Path from A to E", testNum);
        testNum++;

        // Test 2: Shortest weighted path from F to H
        g.dijkstra("F");
        actual = g.findPath("H").toString();
        expected = "[F, G, H]";
        printResults("Dijkstra Test", actual.equals(expected),
                "Dijkstra's Path from F to H", testNum);
        testNum++;

        // --- FIND ALL PATHS UNWEIGHTED (2 cases) ---
        System.out.println("Testing findAllPaths (not WEIGHTED):");
        g.findAllPaths(false);
        
        int unweightedDiam = g.getDiameter();

        // Test 4: Unweighted AllPathsInfo for Vertex A
        // A is connected to B and C. Both are 1 edge away. Total edges = 2. Avg = 1.0.
        boolean foundA = false;
        for (AllPathsInfo info : g.getAllPaths()) {
            if (info.getName().equals("A")) {
                if (info.getAveCost() == 1.0 && info.getNumPaths() == 2) {
                    System.out.println(
                            "Test 4 Passed: Vertex A unweighted stats are correct.");
                    foundA = true;
                }
            }
        }
        if (!foundA)
            System.out.println(
                    "Test 4 FAILED: Vertex A stats incorrect or not found.");

        // --- FIND ALL PATHS WEIGHTED (2 cases) ---
        System.out.println("\nTesting findAllPaths (weighted = true):");
        g.findAllPaths(true);

        // Test 5: Weighted Cost of Longest Shortest Path
        // A->B (2), B->C (1), A->C (3). Max cost is A to C (3.0).
        double weightedMaxCost = g.costOfLongestShortestPath();
        if (weightedMaxCost == 3.0) {
            System.out.println("Test 5 Passed: Weighted max shortest path cost is 3.0.");
        } else {
            System.out.println(
                    "Test 5 FAILED: Weighted max cost. Expected 3.0, got " + weightedMaxCost);
        }

        // Test 6: Weighted AllPathsInfo for Vertex A
        // A to B (2.0), A to C (3.0). Total cost = 5.0. Num paths = 2. Avg = 2.5.
        boolean foundAWeighted = false;
        for (AllPathsInfo info : g.getAllPaths()) {
            if (info.getName().equals("A")) {
                if (info.getAveCost() == 2.5 && info.getTotalCost() == 5.0) {
                    System.out.println("Test 6 Passed: Vertex A weighted stats are correct.");
                    foundAWeighted = true;
                }
            }
        }
        if (!foundAWeighted)
            System.out.println(
                    "Test 6 FAILED: Vertex A weighted stats incorrect.");
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
