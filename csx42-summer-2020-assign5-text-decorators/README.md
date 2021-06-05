# CS542: Assignment 5
**Name:** Ketan Deshpande

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [textdecorators/src](./textdecorators/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile textdecorators/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile textdecorators/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile textdecorators/src/build.xml run -Dinput="input.txt" –Dmisspelled="misspelled.txt" -Dkeywords="keywords.txt" -Doutput="output.txt" -Ddebug="2"
```
Note: Arguments accept the absolute path of the files.

## Note:
As per the discussion with professor, committing the code on Saturday, August 8, 2020.

## Description:
Choice of data structure: I have chosen ArrayList to store the sentences in InputDetails class. Since ArrayList is a dynamic array and we do not have to specify the size while creating it, the size of the array automatically increases when we dynamically add and remove items. Though the actual library implementation may be more complex, the following is a very basic idea explaining the working of the array when the array becomes full and if we try to add an item:

* Creates a bigger sized memory on heap memory (for example memory of double size).
* Copies the current memory elements to the new memory.
* New item is added now as there is bigger memory available now.
* Delete the old memory.
ArrayList has O(n) time complexity for arbitrary indices of add/remove, but O(1) for the operation at the end of the list.
Below are the detailed time complexities for each operation that can be done on ArrayList:
* add() – takes O(1) time
* add(index, element) – in average runs in O(n) time
* get() – is always a constant time O(1) operation
* remove() – runs in linear O(n) time. We have to iterate the entire array to find the element qualifying for removal
* indexOf() – also runs in linear time. It iterates through the internal array and checking each element one by one. So, the time complexity for this operation always requires O(n) time
* contains() – implementation is based on indexOf(). So, it will also run in O(n) time
## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken."

Date: [08/08/2020]
