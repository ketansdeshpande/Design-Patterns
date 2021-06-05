package textdecorators;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import textdecorators.util.InputDetails;
import textdecorators.util.FileProcessor;
import textdecorators.exceptions.InvalidInputException;

/**
* KeywordDecorator class is a decorator which
* checks whether any of the words is a keyword.
*
* @author Ketan Deshpande
*/

public class KeywordDecorator extends AbstractTextDecorator {
	private AbstractTextDecorator atd;
	private InputDetails id;

	private static List<String> listOfKeywords = new ArrayList<String>();

	private final String KEYWORD_PREFIX = "KEYWORD_";
	private final String KEYWORD_SUFFIX = "_KEYWORD";

	/**
	* Constructs a KeywordDecorator that stores
	* AbstractTextDecorator and InputDetails objects.
	*
	* @params AbstractTextDecorator atdIn abstract text decorator object
	* @params InputDetails idIn input details object
	*/
	public KeywordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		atd = atdIn;
		id = idIn;
	}

	/**
	* Overrides processInputDetails method of AbstractTextDecorator
	*
	*/
	@Override
	public void processInputDetails() {
		// Decorate input details.
		String match = "";
		List<String> sentences = id.getSentences();

		final int size = sentences.size();
		for(int i=0; i<size; i++){
			for(String keyword : listOfKeywords) {
				match = "(?i)(\\b" + keyword + "\\b)";
				sentences.set(i, sentences.get(i).replaceAll(match, (KEYWORD_PREFIX + "$1" + KEYWORD_SUFFIX)));
			}
		}

		// Forward to the next decorator, if any.
		if (null != atd) {
			atd.processInputDetails();
		}
	}

	/**
	* Static method to store the KeywordsFile contents in a list
	*
	* @params String fileName KeywordsFile file name
	*
	* @exception InvalidInputException On invalid input contents.
	* @exception FileNotFoundException On not finding the input file.
	* @exception IOException On any I/O errors while reading lines from input file.
	*/
	public static void parseKeywordsFile(String fileName) throws FileNotFoundException, IOException, InvalidInputException {
		FileProcessor fileProcessor = new FileProcessor(fileName);
		if(fileProcessor == null) throw new IOException("File not found.");
		String line = fileProcessor.poll();
		if(line == null) throw new InvalidInputException("File is empty.");
		do {
			listOfKeywords.add(line);
		} while((line = fileProcessor.poll()) != null);
	}
}
