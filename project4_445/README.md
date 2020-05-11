# The Josephus Permutation

Project #4 will simulate the Josephus permutation, a mathematical problem modeled after the famous story about a small band of zealots in the first century that withstood the Roman army for a long period of time but eventually realized they were going to lose. They made a suicide pact to die rather than surrender. One of the zealots was Josephus Flavius. Josephus himself did not actually prefer death to surrender so one of the zealots devised a suicide ritual that gave himself and his best friend a way to survive. Josephus suggested they all stand in circle and go around the circle killing every third (or n'th) man until everyone was gone. According to legend Josephus was a mathematician and made sure that himself and his friend stood in the two spots that would be the last to kill themselves. Then after everyone else had died, he and his friend surrendered to the Romans. Josephus went on to become a famous historian of that century writing "the Antiquities" and other famous works that have endured till today. The Josephus problem is the prediction of who will be the last Node remainimg given a circle of N nodes, starting at a given node, and deleting every k'th node in some direction CLOCKWISE or COUNTER_CLOCKWISE. Our program will not attempt an analytical solution. We will simulate the execution (no pun intended) of the algorithm until only one node remains.

# How to run
When running the Josephus Tester, the "names" is the text file which holds the different names of the people. The second word after "names" is the first person to be "killed". The number afterwords is the direction and number of nodes to move each time. If it is negative, it will go counter-clockwise.
```sh
$ javac CDLL_Josephus_Tester.java
$ java CDLL_Josephus_Tester names HoffmanT -3
```
