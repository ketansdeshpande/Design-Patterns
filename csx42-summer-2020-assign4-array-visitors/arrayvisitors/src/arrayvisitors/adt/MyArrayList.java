package arrayvisitors.adt;

import java.util.Arrays;
import java.io.IOException;
import arrayvisitors.visitors.Visitor;
import arrayvisitors.exceptions.InvalidInputException;

/**
* The MyArrayList class which is an ADT for storing MyArray.
*
* @author Ketan Deshpande
*/
public class MyArrayList implements MyArrayListI, Cloneable {
	private MyArrayI[] myArray;

	public MyArrayList() {}
	public MyArrayList(MyArrayI[] myArray) {}

	// Getters
	/**
	* getMyArray Overriding getMyArray method from MyArrayListI
	*
	* @return Returns array of MyArray objects
	*/
	@Override
	public MyArrayI[] getMyArray() {
		return this.myArray;
	}

	// Setters
	/**
	* setArray Overriding setArray method from MyArrayListI
	*
	* @param MyArrayI[] myArrayIn Accepts array of MyArray objects
	*/
	@Override
	public void setArray(MyArrayI[] myArrayIn) {
		this.myArray = myArrayIn;
	}

	/**
	* accept Overriding accept method from Element
	*
	* @param Visitor visitor Object visitor as an argument
	*/
	@Override
	public void accept(Visitor visitor) throws IOException, InvalidInputException {
		visitor.visit(this);
	}

	/**
	* String toString Overriding toString method
	*
	* @return String
	*/
	@Override
	public String toString() {
		String str = "";
		for(MyArrayI myArrays : myArray) {
			str += Arrays.toString(myArrays.getArray());
		}
		return "myArray: " + str;
	}

	/**
	* finalize Overriding finalize method
	*/
	@Override
	public void finalize() {}

	/**
	* clone Overriding clone method from Cloneable
	*
	* @return MyArray
	*/
	@Override
	public MyArrayList clone() throws CloneNotSupportedException {
        MyArrayList myArrayList = (MyArrayList)super.clone();
        return myArrayList; 
    }
}
