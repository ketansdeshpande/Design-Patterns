package textdecorators.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import textdecorators.exceptions.InvalidInputException;

/**
* InputDetails class is a class that has
* datastructure(s) to store, retrieve and update sentences.
*
* @author Ketan Deshpande
*/

public class InputDetails implements FileDisplayInterface {
	private List<String> sentences = new ArrayList<String>();
	protected static FileProcessor fileProcessor;
	protected String line, wholeFile = "";
	protected String[] splitLine;

	/**
	* Constructs a InputDetails that can store, retrieve and update sentences.
	*
	* @params String inputFile input file path
	* @params String outputFile output file path
	*
	* @exception InvalidInputException On invalid input contents.
	* @exception FileNotFoundException On not finding the input file.
	* @exception IOException On any I/O errors while reading lines from input file.
	*/
	public InputDetails(String inputFile, String outputFile) throws IOException, InvalidInputException, FileNotFoundException {
		fileProcessor = new FileProcessor(inputFile);
		if(fileProcessor == null) throw new IOException("File not found.");
		line = fileProcessor.poll();
		if(line == null) throw new InvalidInputException("File is empty.");
		do {
			Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\.,\\s]");
			Matcher matcher = pattern.matcher(line);
			if(matcher.find()) throw new InvalidInputException("The content of the input file is not valid.");
			wholeFile += line;
		} while((line = fileProcessor.poll()) != null);

		Matcher m = Pattern.compile("[a-zA-Z0-9,\\s]+\\.").matcher(wholeFile);
		while(m.find()) {
			sentences.add(m.group().toString().replaceAll(".$", ""));
		}
	}

	/**
	* Returns the senteces list.
	*
	* @return list of sentences.
	*/
	public List<String> getSentences() {
		return this.sentences;
	}

	/**
	* Overrides printToFile method of FileDisplayInterface
	*
	* @param String str the string which needs to be written.
	* @param String fileName the file name of the file to which
	* 	the output will be written.
	*/
	@Override
	public void printToFile(String str, String fileName) {
		BufferedWriter bw = null;
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(str);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	* Overrides toString method
	*
	* @return String returns contents of list senteces.
	*/
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		for (Object sentence : sentences) {
		  out.append(sentence.toString());
		  out.append(".");
		}

		return out.toString();
	}
}
