package arrayvisitors.visitors;

import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.Results;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.exceptions.InvalidInputException;

/**
* MissingIntsVisitor class to define visit method
*
* @author Ketan Deshpande
*/
public class MissingIntsVisitor implements Visitor {
	/**
	* visit Overriding visit method from Visitor
	*
	* @param MyArrayI myArray Object of MyArray
	* @exception InvalidInputException On invalid input.
	* @exception IOException On any I/O errors while reading lines from input file.
	*/
	@Override
	public void visit(MyArrayI myArray) throws IOException, InvalidInputException {
		int[] array = myArray.getArray();
		List<Integer> missingIntsArray = new ArrayList<Integer>();

		for(int i=0; i<100; i++) {
			missingIntsArray.add(i);
		}

		for(int j=0; j<array.length; j++) {
			if(missingIntsArray.contains(array[j])) {
				missingIntsArray.removeAll(Arrays.asList(array[j]));
			}
		}

		Results.missingInts.append("New Array");
		for(int n: missingIntsArray) {
			Results.missingInts.append("\n" + String.format("%02d", n));
		}

		Results.missingInts.append("\n");
		MyLogger.writeMessage("MissingIntsVisitor : Successfully found missing ints from two files.", MyLogger.DebugLevel.MISSING_INTS_VISITOR);
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
