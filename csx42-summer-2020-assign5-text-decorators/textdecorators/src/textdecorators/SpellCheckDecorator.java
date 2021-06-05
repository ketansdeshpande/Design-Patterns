package textdecorators;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import textdecorators.util.InputDetails;
import textdecorators.util.FileProcessor;
import textdecorators.exceptions.InvalidInputException;

/**
* SpellCheckDecorator class is a decorator which
* checks whether any of the words is a misspelled word.
*
* @author Ketan Deshpande
*/

public class SpellCheckDecorator extends AbstractTextDecorator {
	private AbstractTextDecorator atd;
	private InputDetails id;
	private static List<String> listOfMisspelledWords = new ArrayList<String>();

	private final String SPELL_CHECK_PREFIX = "SPELLCHECK_";
	private final String SPELL_CHECK_SUFFIX = "_SPELLCHECK";

	/**
	* Constructs a SpellCheckDecorator that stores
	* AbstractTextDecorator and InputDetails objects.
	*
	* @params AbstractTextDecorator atdIn abstract text decorator object
	* @params InputDetails idIn input details object
	*/
	public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
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
			for(String misspelledWord : listOfMisspelledWords) {
				match = "(?i)(\\b" + misspelledWord + "\\b)";
				sentences.set(i, sentences.get(i).replaceAll(match, (SPELL_CHECK_PREFIX + "$1" + SPELL_CHECK_SUFFIX)));
			}
		}

		// Forward to the next decorator, if any.
		if (null != atd) {
			atd.processInputDetails();
		}
	}

	/**
	* Static method to store the MispelledFile contents in a list
	*
	* @params String fileName MispelledFile file name
	*
	* @exception InvalidInputException On invalid input contents.
	* @exception FileNotFoundException On not finding the input file.
	* @exception IOException On any I/O errors while reading lines from input file.
	*/
	public static void parseMispelledFile(String fileName) throws FileNotFoundException, IOException, InvalidInputException {
		FileProcessor fileProcessor = new FileProcessor(fileName);
		if(fileProcessor == null) throw new IOException("File not found.");
		String line = fileProcessor.poll();
		if(line == null) throw new InvalidInputException("File is empty.");
		do {
			listOfMisspelledWords.add(line);
		} while((line = fileProcessor.poll()) != null);
	}
}
