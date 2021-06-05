package arrayvisitors.adt;

import java.util.Arrays;
import java.io.IOException;
import arrayvisitors.visitors.Visitor;
import arrayvisitors.exceptions.InvalidInputException;

/**
* The MyArray class which is an ADT for storing ints.
*
* @author Ketan Deshpande
*/

public class MyArray implements MyArrayI, Cloneable {
	private int capacity = 10;
	private int[] array = new int[capacity];

	public MyArray() {}
	public MyArray(int[] array) {}

	// Getters
	/**
	* getArray Overriding getArray method from MyArrayI
	*
	* @return Returns integer array
	*/
	@Override
	public int[] getArray() {
		return this.array;
	}

	/**
	* getCapacity Overriding getCapacity method from MyArrayI
	*
	* @return Returns capacity
	*/
	@Override
	public int getCapacity() {
		return this.capacity;
	}

	// Setters
	/**
	* setArray Overriding setArray method from MyArrayI
	*
	* @param int[] arrayIn Integer array
	*/
	@Override
	public void setArray(int[] arrayIn) {
		this.array = arrayIn;
	}

	/**
	* setCapacity Overriding setCapacity method from MyArrayI
	*
	* @param int capacityIn Capacity value
	*/
	@Override
	public void setCapacity(int capacityIn) {
		this.capacity = capacityIn;
	}

	/**
	* setNewCapacityOfArray Overriding setNewCapacityOfArray method from MyArrayI
	*
	*/
	@Override
	public void setNewCapacityOfArray() {
		int newCapacity = this.capacity + (this.capacity/2);
		int[] newItems = new int[newCapacity];
		System.arraycopy(this.array, 0, newItems, 0, this.capacity);
		this.array = newItems;
		this.capacity = newCapacity;
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
		for(int arrays : array) {
			str += arrays;
		}
		return "array: " + str + " capacity: " + capacity;
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
	public MyArray clone() throws CloneNotSupportedException {
        MyArray myArray = (MyArray)super.clone();
        return myArray; 
    }
}
