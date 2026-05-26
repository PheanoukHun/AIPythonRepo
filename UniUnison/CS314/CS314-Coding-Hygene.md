# Code Hygiene Guide for CS314

## Introduction
This document serves as a guide to writing clean, understandable code for the CS314 course. It covers essential practices aimed at improving collaboration among programmers by ensuring code is easy to read and maintain.

## Motivation
The importance of well-structured and clean code cannot be overstated. Writing readable code not only enhances its quality but also facilitates easier debugging and maintenance. This guide aims to serve as a reference for adhering to best practices in CS314 programming assignments.

## Common Issues

### Magic Numbers
Constants should replace literal integers, except for `-1`, `0`, `1`, and `2`.

**Bad Example:**
```java
final int MIN_LENGTH = 5;
int count = 0;
for (String s : data) {
    if (s.length() >= MIN_LENGTH) {
        count++;
    }
}
```

### Limit Methods to 25 Lines of Code
Methods exceeding 25 lines likely encapsulate too much logic or contain redundant code. Refactor them for clarity.

**Bad Example:**
```java
public void methodWithManyLines(...) {...}
```

### Minimize Variable Scope
Declare variables as close to their usage point as possible to reduce cognitive load and improve readability.

## Source File Structure

### Package Statements
Avoid including package statements in your submitted code.

### Import Statements
Import classes separately, not using wildcards. Remove unnecessary imports via IDE features.

**Bad Example:**
```java
import java.util.*;
```

### Ordering of Class Components
Group constants above instance variables, followed by constructors and methods organized logically.

## Formatting

### Indentation and Spacing
Use 4 spaces for indentation and maintain consistent spacing around operators and after commas in declarations.

## Code Hygiene Guidelines

1. **Comment Judiciously**: Comment to explain the "why" behind complex logic rather than repeating what the code does.
2. **Avoid Implementation Details**: Comments should describe methods' behavior without delving into implementation specifics.
3. **Write for Clients**: Ensure clients can use your methods effectively by clearly commenting on parameters, return values, and exceptions.

## Formatting Rules

1. No line shall exceed 100 characters in length.
2. Enclose all blocks with curly braces for clarity and maintainability.
3. Remove unnecessary code to keep the submission pristine.

## Commenting Guidelines

Comments should be concise yet informative, describing what methods do without detailing their implementation. Use proper formatting for comments including method headers and parameter/return descriptions.

## Class Design
### Fields (Instance Variables)
- Private by default.
- Initialize in constructors rather than at declaration.

### Class Constants
Use `final` keywords for constants to indicate they shouldn't change throughout the class's execution.

## Naming Conventions

- Use camelCase for fields, local variables, and methods.
- Use UpperCamelCase for class names and constructors.
- Use SCREAMING_CASE for constants.

## Efficiency and Redundancy
- Avoid unnecessary object creation and recomputation of values.
- Factor out repeated or complex logic into helper methods to improve readability and maintainability.

## Disallowed Features

Avoid using `break`, `continue` excessively, returning from void methods, Java 8+ functional features, local variable type inference with `var`, try/catch for avoidable errors, and more. These practices are discouraged in CS314 for the sake of maintaining focus on core course learning objectives.

---

This markdown version is designed to be read by AI tools to assist in critiquing code based on the specified guidelines from the CS314 Programming Hygiene Guide. Remember, these rules aim to foster better coding habits and ensure your programs are clean, efficient, and easy to maintain. Happy coding!
