package arrayvisitors.visitors;

import java.io.IOException;
import java.util.Arrays;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.FileProcessor;
import arrayvisitors.exceptions.InvalidInputException;

/**
* PopulateMyArrayVisitor class to define visit method 
*
* @author Ketan Deshpande
*/
public class PopulateMyArrayVisitor implements Visitor {
	protected FileProcessor fileProcessor;
	String line;
	int[] arrInts;

	/**
	* Constructs a FileProcessor that can stream the contents of the provided input file
	* 	line by line.
	*/
	public PopulateMyArrayVisitor(FileProcessor fileProcessorIn) {
		this.fileProcessor = fileProcessorIn;
		MyLogger.writeMessage("PopulateMyArrayVisitor : Created file processor.", MyLogger.DebugLevel.CONSTRUCTOR);
	}

	/**
	* visit Overriding visit method from Visitor
	*
	* @param MyArrayI myArray Object of MyArray
	* @exception InvalidInputException On invalid input.
	* @exception IOException On any I/O errors while reading lines from input file.
	*/
	@Override
	public void visit(MyArrayI myArray) throws IOException, InvalidInputException {
		arrInts = new int[myArray.getCapacity()];
		Arrays.fill(arrInts, -1);

		int counter = 0;
		line = fileProcessor.poll();
		if(line == null) throw new InvalidInputException("File is empty.");
		do {
			if(counter >= myArray.getCapacity()) {
				increaseArraySize(myArray.getCapacity());
				myArray.setNewCapacityOfArray();
			}
			arrInts[counter] = Integer.parseInt(line);
			counter++;
		} while((line = fileProcessor.poll()) != null);

		myArray.setArray(arrInts);
		MyLogger.writeMessage("PopulateMyArrayVisitor : Successfully populated array.", MyLogger.DebugLevel.POPULATE_MY_ARRAY_VISITOR);
	}

	/**
	* increaseArraySize method used to increase size of array dynamically
	*
	* @param int oldSize Old size of the array
	*/
	public void increaseArraySize(int oldSize) {
		int newSize = oldSize + (oldSize/2);
		int[] newItems = new int[newSize];
		System.arraycopy(arrInts, 0, newItems, 0, oldSize);
		arrInts = newItems;
	}

	/**
	* visit Overriding visit method from Visitor
	*
	* @param MyArrayListI myArrayList Object of MyArrayList
	* @exception InvalidInputException On invalid input.
	* @exception IOException On any I/O errors while reading lines from input file.
	*/
	@Override
	public void visit(MyArrayListI myArrayList) throws IOException, InvalidInputException {}
}
