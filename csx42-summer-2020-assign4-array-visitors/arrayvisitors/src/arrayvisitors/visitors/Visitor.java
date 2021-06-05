package arrayvisitors.visitors;

import java.io.IOException;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.exceptions.InvalidInputException;

/**
* Visitor interface 
*
* @author Ketan Deshpande
*/
public interface Visitor {
	public void visit(MyArrayI myArray) throws IOException, InvalidInputException;
	
	public void visit(MyArrayListI myArrayList) throws IOException, InvalidInputException;
}
