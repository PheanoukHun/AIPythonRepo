# AGENTS.md - CS314 Assignment 5 Codebase

## Project Overview

This is a Java project for CS314 (Data Structures) - Assignment 5. It implements a Set interface (`ISet<E>`) with two implementations:
- `UnsortedSet<E>` - elements in no particular order
- `SortedSet<E>` - elements maintained in ascending order (requires `Comparable`)

The project also includes:
- `AbstractSet<E>` - abstract base class implementing common functionality
- `SetTester.java` - manual test harness
- `Stopwatch.java` - timing utility

## Build & Run Commands

### Compile All Java Files

```bash
cd /home/procastoh/Git-Repos/JavaJourney/UniUnison/CS314/assignment5
javac -d bin src/*.java
```

### Run SetTester

```bash
cd /home/procastoh/Git-Repos/JavaJourney/UniUnison/CS314/assignment5
java -cp bin SetTester
```

### Compile & Run a Single Test Class

If you create a separate test file (e.g., `TestMySet.java`):

```bash
# Compile (place in src/ or separate test directory)
javac -d bin src/TestMySet.java

# Run specific test class
java -cp bin TestMySet
```

### Run a Single Test Method

Java doesn't have built-in single test execution like JUnit. To run a specific test:

1. Create a main method that calls only the desired test
2. Or use a testing framework like JUnit

```bash
# Example: Run only specific test logic
java -cp bin -Dtest.method=yourMethod SetTester
```

## Code Style Guidelines

### General Conventions

- **Language Level**: Java 8+ (generics, diamond operator `<>`)
- **Indentation**: 4 spaces (no tabs)
- **Line Length**: Max 100 characters (soft limit)
- **Braces**: K&R style (opening brace on same line)

### File Organization

1. License/header comment (for assignments)
2. Package statement (if any)
3. Import statements (grouped: java.*, javax.*, then custom)
4. Class/interface declaration
5. Class body (fields, constructors, methods)

### Import Conventions

```java
import java.util.Iterator;
import java.util.ArrayList;
// Blank line
import javax.swing.JFileChooser;
```

- Use explicit imports (no wildcard `.*` unless necessary)
- Group imports: standard library first, then third-party, then project-specific
- No blank lines within import group

### Naming Conventions

| Element | Convention | Example |
|---------|------------|---------|
| Classes/Interfaces | PascalCase | `ISet`, `AbstractSet`, `UnsortedSet` |
| Methods | camelCase | `add()`, `iterator()`, `containsAll()` |
| Variables | camelCase | `myCon`, `result`, `otherSet` |
| Constants | UPPER_SNAKE_CASE | `NANOS_PER_SEC` |
| Type Parameters | Single Uppercase | `E`, `T`, `K`, `V` |

### Type Guidelines

- Use generics (`ISet<E>`, `ArrayList<E>`) for type safety
- Prefer interfaces (`List<E>`, `Set<E>`) over implementations in declarations
- Example: `List<E> list = new ArrayList<>();`

### Method Signatures

- Use `@Override` annotation for all overridden methods
- Place `@Override` on its own line before the method
- Use full parameter names (not single letters unless trivial)

```java
@Override
public boolean add(E item) {
    // implementation
}
```

### Error Handling

- Use `UnsupportedOperationException` for unimplemented methods (as per assignment stubs):
  ```java
  throw new UnsupportedOperationException("Unimplemented method 'add'");
  ```
- Use `IllegalArgumentException` for invalid parameters
- Use `NullPointerException` with message for null arguments when appropriate
- Catch specific exceptions, not generic `Exception`

### Documentation

- Use Javadoc for all public API methods
- Follow assignment format for preconditions (`<br> pre: condition`)
- Document return values, parameters, and exceptions
- Include `@param` and `@return` tags

Example:
```java
/**
 * Add an item to this set.
 * <br> item != null
 * @param item the item to be added. May not equal null.
 * @return true if this set changed as a result, false otherwise.
 */
public boolean add(E item);
```

### Assignment-Specific Constraints

The following constraints are enforced by the assignment:

1. **NO instance variables** in `AbstractSet`
2. **NO direct references** to `UnsortedSet` or `SortedSet` in `AbstractSet`
3. **NO direct references** to Java Collections (`ArrayList`, etc.) in `AbstractSet`
4. **NO additional methods** beyond those in `ISet` and `Object`
5. Use `ArrayList` as internal storage in concrete implementations
6. `SortedSet` elements must implement `Comparable`

### Code Quality Rules

- Keep methods short and focused (single responsibility)
- Avoid code duplication - use helper methods in `AbstractSet`
- Use meaningful variable names
- Don't leave TODO comments in production code
- Remove debug print statements before finishing

### Testing Guidelines

- Test edge cases: empty set, single element, duplicates
- Verify `equals()` is properly overridden (symmetric, transitive)
- Test that collections are not mutated by operations
- Verify iterator behavior matches collection state
