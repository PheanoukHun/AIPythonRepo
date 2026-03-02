# GEMINI.md - CS314 Assignment 5 (Sets)

## Project Overview

This project is a Java implementation of a Set data structure for CS314 (Data Structures). It focuses on implementing the `ISet<E>` interface using two different approaches:

- `UnsortedSet<E>`: Elements are stored in an `ArrayList` without any specific order.
- `SortedSet<E>`: Elements are maintained in ascending order, requiring `E` to implement `Comparable<? super E>`.

### Core Architecture

- `ISet<E>`: The interface defining set operations (add, remove, contains, union, intersection, difference, etc.).
- `AbstractSet<E>`: An abstract base class that implements common set logic using iterators to minimize code duplication in concrete classes.
- `UnsortedSet<E>`: Concrete implementation using an internal `ArrayList`.
- `SortedSet<E>`: Concrete implementation that maintains order for efficiency in certain operations (like `equals` or `contains` if implemented with binary search).
- `Stopwatch.java`: Utility for performance measurement.
- `SetTester.java`: Main test harness for verifying correctness and performance.

## Building and Running

### Prerequisites

- Java Development Kit (JDK) 8 Only
- (Optional) VS Code with Java Extension Pack.

### Compilation

To compile all source files into a `bin` directory:

```bash
javac -d bin src/*.java
```

### Running Tests

To run the provided test suite:

```bash
java -cp bin SetTester
```

## Development Conventions

### Assignment Constraints (CRITICAL)

- **Internal Storage**: Must use `java.util.ArrayList` as the underlying container for both `UnsortedSet` and `SortedSet`.
- **AbstractSet Limitations**:
  - NO instance variables allowed in `AbstractSet`.
  - NO direct references to `UnsortedSet` or `SortedSet` within `AbstractSet`.
  - NO direct references to Java Collections (like `ArrayList`) within `AbstractSet`.
- **Method Length**: Keep methods under 25 lines.
- **Magic Numbers**: Use constants for any literal values other than -1, 0, 1, and 2.
- **Java Features**: Avoid Java 8+ functional features (lambdas, streams, etc.) and `var` keyword.

### Coding Style

- **Indentation**: 4 spaces.
- **Naming**: PascalCase for classes, camelCase for methods and variables.
- **Documentation**: Use Javadoc for all public methods, including preconditions (`<br> pre: ...`).
- **Error Handling**: Use `IllegalArgumentException` for precondition violations and `UnsupportedOperationException` for stubs.

### Efficiency

- `SortedSet` operations involving another `SortedSet` should be optimized to run in $O(N)$ time when possible (e.g., using a merge-sort inspired approach for union/intersection/difference).

## Key Files

- `src/ISet.java`: The contract for all set implementations.
- `src/AbstractSet.java`: Where shared logic (like `toString`, `containsAll`, etc.) resides.
- `src/UnsortedSet.java`: Implementation of an unordered set.
- `src/SortedSet.java`: Implementation of an ordered set.
- `src/SetTester.java`: The primary entry point for testing.
