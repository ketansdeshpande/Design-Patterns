package studentskills.driver;

import studentskills.util.MyLogger;
import studentskills.util.Results;
import studentskills.util.FileProcessor;
import studentskills.util.InputProcessor;
import studentskills.util.InputProcessorI;

/**
* The driver class which calls the internal functionality
* and generate the output.
*
* @author Ketan Deshpande
*/
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 7;

	public static void main(String[] args) throws Exception {

		/**
		* As the build.xml specifies the arguments as input,output or metrics, in case the
		* argument value is not given java takes the default value specified in
		* build.xml. To avoid that, below condition is used
		*/
		if ((args.length != 7) || (args[0].equals("${input}")) || (args[1].equals("${modify}")) || (args[2].equals("${out1}")) || (args[3].equals("${out2}")) || (args[4].equals("${out3}")) || (args[5].equals("${error}")) || (args[6].equals("${debug}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}

		MyLogger.setDebugValue(Integer.parseInt(args[6]));
		InputProcessorI inputProcessor = new InputProcessor();
		try {
			String[] outputFiles = new String[] {args[2],args[3],args[4]};
			inputProcessor.parseInput(args[0]);
			inputProcessor.parseModify(args[1], outputFiles);
			Results results = new Results();
			results.printToFile(InputProcessor.str.toString(), args[5]);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		} finally {
			FileProcessor fileProcessor = inputProcessor.getFileProcessor();
			if(fileProcessor != null)
				fileProcessor.close();
		}
	}
}
