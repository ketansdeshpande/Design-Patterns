package channelpopularity.exceptions;

import java.util.*;

/**
* InvalidInputException is an exception to be thrown when
* 	an invalid input occurs while processing.
*
* @author Ketan Deshpande
*/
public class InvalidInputException extends Exception {
	public InvalidInputException(String s) 
	{ 
		// Call constructor of parent Exception 
		super(s);
	}
}