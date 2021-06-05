package studentskills.util;

import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.reflect.Field;
import studentskills.util.MyLogger;
import studentskills.mytree.TreeHelper;
import studentskills.mytree.TreeHelperI;
import studentskills.mytree.StudentRecord;
import studentskills.exceptions.InvalidInputException;

/**
* InputProcessor is a utility to be used to read in the contents
* 	of the input file and parse the input request.
*
* @author Ketan Deshpande
*/
public class InputProcessor implements InputProcessorI{
	public static Map<Integer, Set<TreeHelperI>> replicaMap = new HashMap<>();

	String line;
	String[] splitLine, lineSplit;
	private FileProcessor fileProcessor = null;
	TreeHelperI treeHelperReplicaZero = null;
	TreeHelperI treeHelperReplicaOne = null;
	TreeHelperI treeHelperReplicaTwo = null;
	int i;
	public static StringBuilder str = new StringBuilder();

	public FileProcessor getFileProcessor(){
		return this.fileProcessor;
	}

	/**
	* Initializes objects to create trees of the StudentRecord nodes.
	*
	*/
	public InputProcessor() throws IOException, InvalidInputException {
		treeHelperReplicaZero = new TreeHelper(0);
		treeHelperReplicaOne = new TreeHelper(1);
		treeHelperReplicaTwo = new TreeHelper(2);
		
		Set<TreeHelperI> replica0 = new HashSet<TreeHelperI>(Arrays.asList(treeHelperReplicaOne, treeHelperReplicaTwo));
		Set<TreeHelperI> replica1 = new HashSet<TreeHelperI>(Arrays.asList(treeHelperReplicaZero, treeHelperReplicaTwo));
		Set<TreeHelperI> replica2 = new HashSet<TreeHelperI>(Arrays.asList(treeHelperReplicaZero, treeHelperReplicaOne));
		replicaMap.put(0, replica0);
		replicaMap.put(1, replica1);
		replicaMap.put(2, replica2);
		MyLogger.writeMessage("InputProcessor : Created tree replica.", MyLogger.DebugLevel.CONSTRUCTOR);
	}

	/**
	* Accepts input from the input file and forwards to the node class.
	*
	* @exception InvalidInputException On invalid input.
	* @exception IOException On any I/O errors while reading lines from input file.
	*/
	public void parseInput(String filePath) throws IOException, InvalidInputException {
		MyLogger.writeMessage("InputProcessor::parseInput() : Input file parsing started.", MyLogger.DebugLevel.INPUT_PROCESSOR);
		Pattern pattern = Pattern.compile("^[0-9]{4,4}$");
		fileProcessor = new FileProcessor(filePath);
		if(fileProcessor == null) throw new IOException("File not found.");
		line = fileProcessor.poll();
		if(line == null) throw new InvalidInputException("File is empty.");
		do {
			splitLine = line.split(":");
			if(line.trim().isEmpty()) {
				InputProcessor.str.append("Got an empty line in input file.");
				InputProcessor.str.append('\n');
				continue;
			}
			
			Matcher matcher = pattern.matcher(splitLine[0]);
			if(!matcher.find()) {
				InputProcessor.str.append("Got an invalid bNumber as " + splitLine[0]);
				InputProcessor.str.append('\n');
				continue;
			}
			
			Set<String> skills = new HashSet<String>();

			lineSplit = splitLine[1].split(",");
			for(i=4; i<lineSplit.length; i++) {
				if(lineSplit[i] != null) {
					skills.add(lineSplit[i]);
				}
			}

			StudentRecord studentRecord = new StudentRecord();
			studentRecord.createNode(Integer.parseInt(splitLine[0]), lineSplit[0], lineSplit[1], Double.parseDouble(lineSplit[2]), lineSplit[3], skills);
			studentRecord.register(treeHelperReplicaZero);
		} while((line = fileProcessor.poll()) != null);
		
		MyLogger.writeMessage("InputProcessor::parseInput() : Input file parsing end.", MyLogger.DebugLevel.INPUT_PROCESSOR);
	}

	/**
	* Accepts input from the modify file and forwards to the node class.
	*
	* @exception InvalidInputException On invalid input.
	* @exception IOException On any I/O errors while reading lines from input file.
	*/
	public void parseModify(String filePath, String[] outputFiles) throws IOException, InvalidInputException {
		MyLogger.writeMessage("InputProcessor::parseModify() : Modify file parsing started.", MyLogger.DebugLevel.INPUT_PROCESSOR);
		TreeHelperI treeHelper = null;
		Pattern pattern = Pattern.compile("^[0-9]{4,4}$");
		fileProcessor = new FileProcessor(filePath);
		if(fileProcessor == null) throw new IOException("File not found.");
		line = fileProcessor.poll();
		if(line == null) throw new InvalidInputException("File is empty.");
		do {
			splitLine = line.split(",");
			if(line.trim().isEmpty()) {
				InputProcessor.str.append("Got an empty line in modify file.");
				InputProcessor.str.append('\n');
				continue;
			}

			Matcher matcher = pattern.matcher(splitLine[1]);
			if(!matcher.find()) {
				InputProcessor.str.append("Got an invalid bNumber as " + splitLine[0]);
				InputProcessor.str.append('\n');
				continue;
			}

			lineSplit = splitLine[2].split(":");

			//TODO: write to error file
			if(lineSplit.length < 2) InputProcessor.str.append("Please provide new value to be updated for bNumber " + splitLine[1] + " and value " + lineSplit[0]);

			StudentRecord studentRecord = new StudentRecord();
			studentRecord.bNumber = Integer.parseInt(splitLine[1]);

			int treeId = Integer.parseInt(splitLine[0]);
			if(treeId == 0) {
				treeHelper = treeHelperReplicaZero;
			} else if(treeId == 1) {
				treeHelper = treeHelperReplicaOne;
			} else {
				treeHelper = treeHelperReplicaTwo;
			}
			
			studentRecord = treeHelper.searchStudentNode(studentRecord);
			try {
				if(studentRecord != null) {
					Field[] fields = studentRecord.getClass().getDeclaredFields();
					for (int i = 0; i < fields.length; i++) {						
						Object value = fields[i].get(studentRecord);
						fields[i].setAccessible(true);
						if(lineSplit[0].equals(value)) {
							fields[i].set(studentRecord, lineSplit[1]);
						} else if (value instanceof Collection<?>) {
							String[] splits = value.toString().replace("[","").replace("]","").split(", ");
							Set<String> arrayList = new HashSet<String>(Arrays.asList(splits));
							Set<String> newSkills = new HashSet<String>();
							for(String skill: arrayList) {
								if(lineSplit[0].equals(skill)) {
									newSkills.add(lineSplit[1]);
								} else {
									newSkills.add(skill);
								}
							}
							fields[i].set(studentRecord, newSkills);
						}
					}
					studentRecord.register(treeHelper);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} while((line = fileProcessor.poll()) != null);

		MyLogger.writeMessage("InputProcessor::parseModify() : Modify file parsing end.", MyLogger.DebugLevel.INPUT_PROCESSOR);

		Results results = new Results();
		treeHelperReplicaZero.printNodes(results, outputFiles[0]);
		treeHelperReplicaOne.printNodes(results, outputFiles[1]);
		treeHelperReplicaTwo.printNodes(results, outputFiles[2]);
	}
}
