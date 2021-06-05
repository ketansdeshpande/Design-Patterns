package wordPlay.handler;
import wordPlay.driver.Driver;
import wordPlay.exceptions.InvalidInputException;

/**
* WordRotator is a utility to rotate the given word according to its position.
*
* @author Ketan Deshpande
*/
public class WordRotator {
	protected static int wordCount;

	/**
	* Initializes all variables.
	*/
	public WordRotator() {
		wordCount = 0;
	}

	/**
	* Rotates the given word according to its position.
	*
	* @param word from the input file
	* @exception InvalidInputException on getting a blank line
	*/
	public void rotateWord(String word) throws InvalidInputException {
		if(word.trim().isEmpty()) {
			throw new InvalidInputException("Invalid input.");
		}
		wordCount++;
		if(word.substring((word.length()-1)).equals(".")) {
			word = word.substring(0, (word.length()-1));
			String tempStr = rotate(word, wordCount);
			Driver.str.append(tempStr + ".\n");
			wordCount = 0;
		} else {
			String tempStr = rotate(word, wordCount);
			Driver.str.append(tempStr + " ");
		}
	}

	/**
	* Rotates the given word according to its position.
	*
	* @param word from the input file
	* @param position of the word as offset
	* @return String rotates the string by concatenating the
	* 	splitted word by using substring method
	*/
	public static String rotate(String s, int offset) {
		int i = Math.abs(s.length() - offset);
		return s.substring(i) + s.substring(0, i);
	}

	/**
	* Overrides toString method of Object.
	*
	* @return String returns static properties of the class
	*/
	@Override
	public String toString(){
		return "wordCount: " + wordCount;
	}
}