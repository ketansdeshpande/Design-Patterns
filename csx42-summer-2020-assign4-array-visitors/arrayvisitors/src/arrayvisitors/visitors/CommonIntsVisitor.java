package arrayvisitors.visitors;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import arrayvisitors.util.Results;
import arrayvisitors.util.MyLogger;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.exceptions.InvalidInputException;

/**
* CommonIntsVisitor class to define visit method
*
* @author Ketan Deshpande
*/
public class CommonIntsVisitor implements Visitor {
	/**
	* visit Overriding visit method from Visitor
	*
	* @param MyArrayI myArray Object of MyArray
	* @exception InvalidInputException On invalid input.
	* @exception IOException On any I/O errors while reading lines from input file.
	*/
	@Override
	public void visit(MyArrayI myArray) throws IOException, InvalidInputException {}

	/**
	* visit Overriding visit method from Visitor
	*
	* @param MyArrayListI myArrayList Object of MyArrayList
	* @exception InvalidInputException On invalid input.
	* @exception IOException On any I/O errors while reading lines from input file.
	*/
	@Override
	public void visit(MyArrayListI myArrayList) throws IOException, InvalidInputException {
		MyArrayI[] myArrays;
		int[] a, b;
		myArrays = myArrayList.getMyArray();

		List<Integer> commonElements = new ArrayList<Integer>();
		a = myArrays[0].getArray();
		b = myArrays[1].getArray();
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < b.length; j++) {
				if(a[i] == b[j]) {
					if(!commonElements.contains(a[i]) && a[i] != -1) {
						commonElements.add(a[i]);
					}
				}
            }
        }

		for(int n: commonElements) {
			Results.commonInts.append(String.format("%02d", n) + "\n");
		}

		MyLogger.writeMessage("CommonIntsVisitor : Successfully found common ints from two files.", MyLogger.DebugLevel.COMMON_INTS_VISITOR);
	}
}
