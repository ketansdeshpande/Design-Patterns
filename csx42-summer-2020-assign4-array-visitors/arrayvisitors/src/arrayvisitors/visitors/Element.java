package arrayvisitors.visitors;

import java.io.IOException;
import arrayvisitors.exceptions.InvalidInputException;

/**
* Element interface 
*
* @author Ketan Deshpande
*/
public interface Element {
	public void accept(Visitor visitor) throws IOException, InvalidInputException;
}
