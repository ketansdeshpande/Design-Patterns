package channelpopularity.util;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.operation.Operation;
import channelpopularity.context.ChannelContext;
import channelpopularity.exceptions.InvalidInputException;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.state.factory.SimpleStateFactoryI;


/**
* InputProcessor is a utility to be used to read in the contents
* 	of the input file and parse the input request.
*
* @author Ketan Deshpande
*/
public class InputProcessor {

	private String[] stringArraySplitLine, stringArraysplitOperation;
	Operation enumOperation;
	Vector vectorAllVideos = new Vector();	
	String stringLine;
	public static Map<String, Vector> map = new HashMap<>();
	FileProcessor objectFileProcessor = null;
	public static StringBuilder str = new StringBuilder();

	/**
	* Initializes objects to read the input from the file.
	*
	* @param String filePath File name of the input file.
	*/
	public InputProcessor(String filePath) throws IOException, InvalidInputException {
		objectFileProcessor = new FileProcessor(filePath);
		if(objectFileProcessor == null) throw new IOException("File not found.");
		stringLine = objectFileProcessor.poll();
		if(stringLine == null) throw new InvalidInputException("File is empty.");
	}

	/**
	* Accepts input from the file and forwards to the context class.
	*
	* @exception InvalidInputException On invalid input.
	* @exception IOException On any I/O errors while reading lines from input file.
	*/
	public void parseInput() throws IOException, InvalidInputException {
		StateName[] stateNamesArray = { 
			StateName.UNPOPULAR,
			StateName.MILDLY_POPULAR,
			StateName.HIGHLY_POPULAR,
			StateName.ULTRA_POPULAR
		};

		List<StateName> stateNames = Arrays.asList(stateNamesArray);
		SimpleStateFactoryI simpleStateFactoryI = new SimpleStateFactory();
		
		InputProcessor.map.put(StateI.CHANNEL_NAME, vectorAllVideos);

		ChannelContext channelContext = new ChannelContext(simpleStateFactoryI, stateNames);
		if(objectFileProcessor == null) throw new IOException("File not found.");
		Pattern patternOp = Pattern.compile("[^A-Z_]");
		Pattern patternData2 = Pattern.compile("([^-\\[\\]\\dA-Z=,])");
		do {
			stringArraySplitLine = stringLine.split("::");
			Commands objectCommands = null;
			if(stringArraySplitLine[0].contains("__")) {
				stringArraysplitOperation = stringArraySplitLine[0].split("__");

				Matcher matcherOp = patternOp.matcher(stringArraysplitOperation[0]);
				Matcher matcherData2 = patternData2.matcher(stringArraySplitLine[1]);
				if(matcherOp.find()) throw new InvalidInputException("Invalid input for operation.");
				if(matcherData2.find()) throw new InvalidInputException("Invalid input for metrics.");

				Operation operation = Operation.valueOf(stringArraysplitOperation[0]);
				objectCommands = new Commands(operation, stringArraysplitOperation[1], stringArraySplitLine[1]);
			} else {
				Matcher m = patternOp.matcher(stringArraySplitLine[0]);
				if(m.find()) throw new InvalidInputException("Invalid input.");
				Operation operation = Operation.valueOf(stringArraySplitLine[0]);
				objectCommands = new Commands(operation, stringArraySplitLine[1], "");
			}
			channelContext.process(objectCommands);
			str.append('\n');
		} while((stringLine = objectFileProcessor.poll()) != null);
	}
}
