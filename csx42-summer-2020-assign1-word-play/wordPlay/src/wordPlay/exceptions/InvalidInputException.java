package wordPlay.exceptions;

import java.util.*;

public class InvalidInputException extends Exception {
	public InvalidInputException(String s) 
	{ 
		// Call constructor of parent Exception 
		super(s);
	}
}