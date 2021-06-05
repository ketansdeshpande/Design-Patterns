package wordPlay.util;

import wordPlay.metrics.MetricsCalculator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
* Results is a utility to print the output to the standard output and file
*
* @author Ketan Deshpande
*/
public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	/**
	* Overrides displayOnConsole method of StdoutDisplayInterface
	*
	* @param String str the string which needs to be shown on
	* 	standard output i.e. console.
	*/
	@Override
	public void displayOnConsole(String str) {
		System.out.println(str);
		System.out.println(MetricsCalculator.avgNumWordsPerSent);
		System.out.println(MetricsCalculator.avgWordLength);
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
}
