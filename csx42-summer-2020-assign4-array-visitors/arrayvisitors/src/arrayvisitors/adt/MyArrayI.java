package arrayvisitors.adt;

import arrayvisitors.visitors.Visitor;
import arrayvisitors.visitors.Element;

/**
* MyArrayI interface 
*
* @author Ketan Deshpande
*/
public interface MyArrayI extends Element {
	public int[] getArray();
	public int getCapacity();
	public void setArray(int[] arrayIn);
	public void setNewCapacityOfArray();
	public void setCapacity(int capacityIn);
}
