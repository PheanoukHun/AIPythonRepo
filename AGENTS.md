# AGENTS.md - Guidance for OpenCode Sessions

## Project Overview
This is a CS314 Huffman Coding assignment from UT Austin. The goal is to implement Huffman compression and decompression algorithms.

## Key Files
- `src/Huff.java` - Main entry point (launch GUI)
- `src/SimpleHuffProcessor.java` - Where you implement Huffman logic (preprocessCompress, compress, uncompress)
- `TestingHarness/TestingHarness/A10_Huffman_Test_Student_Version.java` - Test harness using BevoTest framework
- `src/IHuffProcessor.java` - Interface defining required methods
- `src/IHuffConstants.java` - Constants including MAGIC_NUMBER, STORE_COUNTS, STORE_TREE, STORE_CUSTOM

## Development Workflow

### Compilation
```bash
javac -cp .:TestingHarness:TestingHarness/bevotest.jar src/*.java TestingHarness/TestingHarness/*.java
```

### Running the Application
```bash
java -cp .:TestingHarness Huff
```

### Running Tests
```bash
java -cp .:TestingHarness:TestingHarness/bevotest.jar edu.utexas.cs.bevotest.BevoTest TestingHarness.TestingHarness.A10_Huffman_Test_Student_Version
```

## Implementation Details
1. **Header Formats** (from IHuffConstants.java):
   - `STORE_COUNTS` (MAGIC_NUMBER \| 1) - Store array of counts for each ASCII character
   - `STORE_TREE` (MAGIC_NUMBER \| 2) - Store the actual Huffman tree structure
   - `STORE_CUSTOM` (MAGIC_NUMBER \| 4) - For custom formats (not required for basic implementation)

2. **Return Values**:
   - `preprocessCompress`: Returns bit savings or other measure (negative means expansion)
   - `compress`: Returns total bits written (including headers and magic number)
   - `uncompress`: Returns bits written to output

3. **Critical Behavior**:
   - Methods should ONLY throw IOException for actual I/O errors
   - `force=false` in compress: Don't create output if compressed size >= original size
   - `force=true` in compress: Always create output file regardless of size

## Implementation Notes
1. Focus on implementing the three methods in `SimpleHuffProcessor.java`:
   - `preprocessCompress` - Analyze input and build Huffman tree
   - `compress` - Actually compress data using the tree
   - `uncompress` - Decompress data using the tree

2. The test harness expects specific behavior:
   - Methods should throw IOException only for actual I/O errors
   - Return values represent bit counts (refer to Javadoc for specifics)
   - `force` parameter in compress controls behavior when output would be larger than input

3. Test data is available in:
   - `AdditionalTests/AdditionalTests/files_to_test/original_files/` - Original test files
   - `TestingHarness/TestingHarness/FilesForStudentTest/` - Pre-made test files

## Important Conventions
- All paths are relative to the assignment9 directory
- The BevoTest framework is used for automated testing
- GUI launches by default; for text interface, modify Huff.java line 24-25
- Implementation must follow the interface defined in IHuffProcessor and IHuffViewer
- Need to handle PSEUDO_EOF constant (value 256) for end-of-stream marker
- Bit writing/reading requires using ExplicitBitOutputWriter/ExplicitBitInputReader classes