# CSX42: Assignment 3
**Name:** Ketan Deshpande

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [studentskills/src](./ studentskills/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile studentskills/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile studentskills/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile studentskills/src/build.xml run -Dinput="input.txt" -Dmodify="modify.txt" -Dout1="out1.txt" -Dout2="out2.txt" -Dout3="out3.txt" -Derror="error.txt" -Ddebug="2"
```
Note: Arguments accept the absolute path of the files.


## Description:
Implemented binary search tree to create student record nodes.
Ref- https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/

Choice of Tree:
I have chosen Binary search tree to implement the tree structure for the student record as nodes. The properties of Binary Search Tree provide an ordering among keys so that the operations like search, minimum and maximum can be done fast. If there is no ordering, then we may have to compare every key to search a given key.
In binary search we start with ‘n’ elements in search space and then if mid element is not the element that we are looking for, we reduce the search space to ‘n/2’ and we go on reducing the search space till we either find the record that we are looking for or we get to only one element in search space and be done with this whole reduction.


Note: Used 3 slack days for this assignment.
## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [07/13/2020]
