package wordPlay.metrics;

import wordPlay.exceptions.InvalidInputException;
import java.io.*;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* MetricsCalculator is a utility to calculate metrices for the given word.
*
* @author Ketan Deshpande
*/
public class MetricsCalculator {
	public static float avgNumWordsPerSent;
	public static float avgWordLength;
	private float numOfSent, totalWords, totalWordLength;

	/**
	* Initializes all variables.
	*/
	public MetricsCalculator() {
		numOfSent = 0;
		totalWords = 0;
		avgWordLength = 0;
		totalWordLength = 0;
		avgNumWordsPerSent = 0;
	}

	/**
	* Calculate metrices for the given word.
	*
	* @param word from the input file
	* @exception InvalidInputException on getting a blank line
	*/
	public void calculateMetrics(String word) throws InvalidInputException {
		if(word.trim().isEmpty()) {
			throw new InvalidInputException("Invalid input.");
		}

		totalWords++;
		if(word.substring((word.length()-1)).equals(".")) {
			numOfSent++;
			avgNumWordsPerSent = totalWords/numOfSent;
			avgNumWordsPerSent = Float.parseFloat(String.format("%.2f", avgNumWordsPerSent));
			totalWordLength += word.length() - 1;
		} else {
			totalWordLength += word.length();
		}

		avgWordLength = totalWordLength/totalWords;
		avgWordLength = Float.parseFloat(String.format("%.2f", avgWordLength));
	}

	/**
	* Overrides toString method of Object.
	*
	* @return String returns properties of the class
	*/
	@Override
	public String toString(){
		return "numOfSent: " + numOfSent + " totalWords: " + " avgWordLength: " + avgWordLength + " totalWordLength: " + totalWordLength + " avgNumWordsPerSent: " + avgNumWordsPerSent;
	}
}