package arrayvisitors.adt;

import arrayvisitors.visitors.Visitor;
import arrayvisitors.visitors.Element;

/**
* MyArrayListI interface 
*
* @author Ketan Deshpande
*/
public interface MyArrayListI extends Element {
	public MyArrayI[] getMyArray();
	public void setArray(MyArrayI[] myArrayIn);
}
