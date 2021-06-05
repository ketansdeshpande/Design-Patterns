# CS542: Assignment 4
**Name:** Ketan Deshpande

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [arrayvisitors/src](./ arrayvisitors/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile arrayvisitors/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile arrayvisitors/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile arrayvisitors/src/build.xml run -Dinput1="input1.txt" –Dinput2="input2.txt" -Dcommonintsout="commonintsout.txt" -Dmissingintsout="missingintsout.txt" -Ddebug="2"
```
Note: Arguments accept the absolute path of the files.

## Description:
Choice of data structure:
I have chosen ArrayList as a data structure to store the values to generate the appropriate output. The time complexity for the add operation is O(1) and time complexity of remove operation runs in linear O(n) time, where n is the number of elements in the list. We have to iterate the entire array to find the element qualifying for removal.

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken."

Date: [07/22/2020]
