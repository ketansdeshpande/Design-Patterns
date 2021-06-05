package textdecorators.driver;

import java.lang.NumberFormatException;
import textdecorators.util.MyLogger;
import textdecorators.util.FileProcessor;
import textdecorators.util.InputDetails;
import textdecorators.AbstractTextDecorator;
import textdecorators.SentenceDecorator;
import textdecorators.SpellCheckDecorator;
import textdecorators.KeywordDecorator;
import textdecorators.MostFrequentWordDecorator;
import textdecorators.util.FileDisplayInterface;

/**
* The driver class which calls the internal functionality
* and generate the output.
*
* @author Ketan Deshpande
*/
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;
	protected static FileProcessor fileProcessor;

	public static void main(String[] args) throws Exception {

		/**
		* As the build.xml specifies the arguments as input,output or metrics, in case the
		* argument value is not given java takes the default value specified in
		* build.xml. To avoid that, below condition is used
		*/
		if ((args.length != 5) || (args[0].equals("${input}")) || (args[1].equals("${misspelled}")) || (args[2].equals("${keywords}")) || (args[3].equals("${output}")) || (args[4].equals("${debug}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}

		if(args[0].equals(args[1]) || args[0].equals(args[2])) {
			System.err.println("Input files cannot have same path or name.");
			System.exit(0);
		}

		MyLogger.setDebugValue(Integer.parseInt(args[4]));
		try {
			SpellCheckDecorator.parseMispelledFile(args[1]);
			KeywordDecorator.parseKeywordsFile(args[2]);
			InputDetails inputD = new InputDetails(args[0], args[3]);
			AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
			AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD);
			AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD);
			AbstractTextDecorator mostFrequentWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);
			mostFrequentWordDecorator.processInputDetails();

			((FileDisplayInterface) inputD).printToFile(inputD.toString(), args[3]);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		} finally {
		}
	}
}
