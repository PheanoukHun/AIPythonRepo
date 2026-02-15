**Summary of Programming Assignment 4: Evil Hangman**

**Objective:**  
Implement a game where the computer (AI) cheats by dynamically narrowing down possible secret words based on player guesses, using advanced data structures like maps and lists. The goal is to simulate an "evil" version of Hangman where the AI selects the hardest possible word family at each guess.

---

### **Key Components & Requirements**

1. **Data Structures:**
   - Use a `Map<String, ArrayList<String>>` to track patterns (e.g., `-e-`) and their associated words.
   - Store active words (potential secret words) based on guesses.

2. **Game Logic:**
   - **Word Families:** Split the current list of words into families based on guessed letters and patterns.
     - Example: If guessing `'e'`, words like `beget` or `beret` match `-e-`.
   - **Difficulty Levels:**
     - **Hardest:** Always pick the largest family (tiebreakers: most words, then fewest revealed characters, then lex smallest pattern).
     - **Medium:** Alternates between hardest and second-hardest families.
     - **Easy:** Alternates between hardest and second-hardest every other guess.

3. **Methods to Implement:**
   - `prepForRound(wordLen, numGuesses, diff)`: Resets the game with new parameters (word length, guesses, difficulty).
   - `makeGuess(char guess)`: Determines new patterns and selects the hardest family.
   - `getSecretWord()`: Randomly picks a word from the active list if multiple options remain.

4. **Debugging & Testing:**
   - Use `debug` flag to print useful outputs (e.g., pattern changes, word families).
   - Test with both small (`smallDictionary.txt`) and large dictionaries.

---

### **How It Works**

- The AI avoids picking a secret word until forced. Instead, it builds a list of possible words based on guesses.
- Each guess splits the active list into families (e.g., `-e--` for `beta`, `deal`).
- The AI selects the hardest family (largest in size, then lex smallest pattern) to continue the game.

---

### **Important Notes**

- **No Partner Work:** This is individual; code must be entirely your own.
- **Testing:** Use tools like `EvilHangmanAutoTester.java` and generate tests with `GenerateTests.java`.
- **Edge Cases:** Handle scenarios where no words remain (throw `IllegalStateException`).

---

### **Submission Checklist**

1. Implement all required methods (`prepForRound`, `makeGuess`, etc.).
2. Ensure output matches sample logs (e.g., debug vs. no-debug modes).
3. Include debugging statements for testing.
4. Add proper comments and follow code hygiene rules.

---

This assignment challenges you to combine advanced data structures, logic for dynamic word selection, and careful handling of difficulty levels. Focus on clarity, efficiency, and adherence to the specification. 🎮

### TO DO LIST:

1. Implement all the Methods in [[HangmanManager.java]]
  1. Constructors
  2. getNumWords()
  3. prepForRound()
  4. numWordsCurrent()
  5. getGuessesLeft()
  6. getGuessMade()
  7. alreadyGuessed()
  8. getPattern()
  9. makeGuess()
  10. getSecretWord();
