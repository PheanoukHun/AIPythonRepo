# CS314 Assignment 7: Anagrams and Recursion

## Project Overview
This project is part of the CS314 Data Structures course. It consists of two primary components:
1.  **Anagram Solver:** A program that finds all possible combinations of words from a dictionary that form an anagram of a given phrase. This involves implementing a backtracking algorithm.
2.  **Recursive Problems:** A collection of independent recursive methods solving various problems such as phone mnemonics, Sierpinski carpets, water flow on a map, and team ability balancing.

### Main Technologies
- **Java:** The project is written in Java, adhering to pre-Java 8 features (as per CS314 standards).
- **Custom Graphics:** Utilizes `DrawingPanel.java` for visual problems like the Sierpinski Carpet.
- **Standard Library:** Heavy use of Java Collections Framework (`ArrayList`, `HashSet`, `List`, `Set`).

## Project Structure
- `src/`: Contains all Java source files.
  - `AnagramMain.java`: Entry point for the anagram solver.
  - `AnagramSolver.java`: Core logic for finding anagrams (to be implemented/refined).
  - `LetterInventory.java`: Helper class for counting and managing letters in words/phrases.
  - `Recursive.java`: Contains various recursive utility methods.
  - `RecursiveTester.java` & `AnagramFinderTester.java`: Test suites.
- `bin/`: Compiled `.class` files.
- `lib/`: Directory for external library dependencies.
- `d1.txt`, `d2.txt`, `d3.txt`: Dictionary files used by the Anagram Solver.
- `sample_output_*.txt`: Reference output files for validation.
- `testCaseAnagrams.txt`: Additional test cases for anagrams.

## Building and Running

### Prerequisites
- JDK 8 or higher (though Java 8+ features like streams are discouraged).

### Compilation
From the project root, you can compile all source files into the `bin` directory:
```bash
javac -d bin -cp "lib/*:src" src/*.java
```

### Running the Anagram Solver
To run the interactive anagram solver:
```bash
java -cp bin AnagramMain
```
When prompted for a dictionary, you can use `d1.txt`, `d2.txt`, or `d3.txt`.

### Running Tests
To run the provided test suites:
```bash
# For Recursive problems
java -cp bin RecursiveTester

# For Anagram Finder
java -cp bin AnagramFinderTester
```

## Development Conventions
This project follows strict **CS314 Programming Hygiene** rules:
- **Magic Numbers:** No literal integers except -1, 0, 1, and 2. Use named constants.
- **Method Length:** Methods should not exceed 25 lines.
- **Variable Scope:** Declare variables as close to their usage as possible.
- **Formatting:** 4-space indentation, no lines over 100 characters.
- **Class Design:** All fields must be `private` and initialized in constructors.
- **Disallowed:** No `break` or `continue` (outside `switch`), no early returns from `void` methods, and no Java 8+ features (streams, lambdas).
- **Imports:** Individual imports only; no wildcards (`import java.util.*;` is a violation).

## Key Components to Implement/Review
- `AnagramSolver.java`: Implementation of the backtracking algorithm for anagram generation.
- `LetterInventory.java`: Efficiency of letter counting and subtraction.
- `Recursive.java`: Correctness and efficiency of the 5 recursive problems.
