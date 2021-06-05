package studentskills.util;

import java.io.IOException;
import studentskills.exceptions.InvalidInputException;

/**
* InputProcessorI interface 
*
* @author Ketan Deshpande
*/
public interface InputProcessorI {
	
	public FileProcessor getFileProcessor();

	public void parseInput(String filePath) throws IOException, InvalidInputException;
	
	public void parseModify(String filePath, String[] outputFiles) throws IOException, InvalidInputException;
}