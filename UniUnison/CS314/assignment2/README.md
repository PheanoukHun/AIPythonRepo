# Summary of “Programming Assignment 2: MathMatrix”
## Learning Goals
- Build a full Java class using OOP principles
- Work with 2D arrays
- Design strong test cases
- Analyze algorithm performance with timing experiments

## What You Must Build
You will implement a MathMatrix class that represents a mathematical matrix using a 2D int array as internal storage.
You must also write your own test cases in MathMatrixTester.java.

## Required Files
You will submit:
- MathMatrix.java (your implementation)
- MathMatrixTester.java (your tests + experiment results in comments)
Provided:
- Stopwatch.java (for timing experiments)
- Stopwatch.html (documentation)

## MathMatrix Class Requirements
Constructors
- MathMatrix(int numRows, int numCols, int initialVal)
- Creates a matrix filled with initialVal
- Preconditions: rows/cols > 0
- MathMatrix(int[][] mat)
- Deep copy of a rectangular 2D array
- Preconditions: non-null, >0 rows/cols, rectangular

## Basic Accessors
- getNumRows()
- getNumColumns()
- getVal(int row, int col)

## Matrix Operations to Implement
1. add(MathMatrix rhs)
- Element‑wise addition
- Preconditions: same dimensions
- Returns a new matrix
2. subtract(MathMatrix rhs)
- Element‑wise subtraction
- Preconditions: same dimensions
- Returns a new matrix
3. multiply(MathMatrix rhs)
- Standard matrix multiplication
- Preconditions: A.cols == B.rows
- Returns a new matrix
4. getScaledMatrix(int factor)
- Multiply every element by a scalar
- No preconditions
5. getTranspose()
- Swap rows and columns
- No preconditions

## equals(Object rhs)
- Return true if:
- rhs is a MathMatrix
- Same dimensions
- All values match

## toString() Requirements
You must produce a formatted matrix string:
- Each row wrapped in | ... |
- Each row ends with \n
- Right‑justify all values
- Column width = (length of longest value) + 1
- Must match formatting rules exactly

## isUpperTriangular()
- Matrix must be square
- All values below main diagonal must be zero

## Experiments (Using Stopwatch)
Experiment 1: add
- Time 1000 additions of two N×N matrices
- Adjust N until total time ≥ 1 second
- Double N twice more and repeat
- Record:
- Dimensions
- Total time
Experiment 2: multiply
- Same as above but:
- Only 100 repetitions
- Adjust N until total time ≥ 1 second

## Questions to Answer
You must answer (in comments at top of tester file):
- Expected time if dimensions doubled again (add)
- Big‑O of add + whether data supports it
- Expected time if dimensions doubled again (multiply)
- Big‑O of multiply + whether data supports it
- Largest matrix before OutOfMemoryError
- Estimate memory available + % of RAM used

## Testing Requirements
You must write 2 new test cases each for:
- getNumRows
- getNumColumns
- getVal
- add
- subtract
- multiply
- getScaledMatrix
- getTranspose
- equals
- toString
- isUpperTriangular
Delete the provided tests.

## Submission Checklist
You must ensure:
- All methods implemented
- Experiments completed + commented
- Preconditions checked with exceptions
- No compile/runtime errors
- Header comments filled
- Hygiene guidelines followed
- Hidden edge cases likely to pass
- Required test cases written
- Provided tests deleted

If you want, I can also:
- Turn this into a checklist you can follow while coding
- Help you design test cases
- Help you plan the class structure
- Walk through tricky methods like multiply or toString
Just tell me what you want to tackle next.