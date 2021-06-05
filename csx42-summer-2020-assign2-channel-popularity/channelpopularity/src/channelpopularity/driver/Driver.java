package channelpopularity.driver;

import channelpopularity.util.InputProcessor;
import channelpopularity.util.Results;
/**
 * The driver class which calls the internal functionality
 * and generate the output.
 *
 * @author Ketan Deshpande
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	public static void main(String[] args) throws Exception {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}

		try {
			InputProcessor inputProcessor = new InputProcessor(args[0]);
			inputProcessor.parseInput();
			InputProcessor.str = InputProcessor.str.deleteCharAt(InputProcessor.str.length() - 1);
			Results results = new Results();
			results.displayOnConsole(InputProcessor.str.toString());
			results.printToFile(InputProcessor.str.toString(), args[1]);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		} finally {}
	}
}
