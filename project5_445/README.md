# The Game of Boggle

Solving the game of Boggle can be done elegantly with recursion and backtracking. Backtracking is a technique whereby an algorithm recognizes it is impossible or unnecessary to search any deeper into the search space from a given point. An example of this is finding your way through a maze. When you hit a dead end you retreat from that point and never take the same path to that point again. A fast and efficient solution to Boggle requires a heuristic that helps you recognize a dead end and save vast amounts of wasted words formed and wasted searches (and thus vast amounts of time).

# How to run
When running the game of Boggle, the "dictionary" is the text file which holds all of the possible words that can be found. The second word after "dictionary" is the board that is being chosen. This can be interchanged with any of the other boards.
```sh
$ javac Boggle.java
$ java Boggle dictionary 2x2
```
