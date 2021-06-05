package textdecorators;

import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.Arrays;
import java.util.HashMap;
import textdecorators.util.InputDetails;

/**
* MostFrequentWordDecorator class is a decorator which
* finds most frequent word.
*
* @author Ketan Deshpande
*/

public class MostFrequentWordDecorator extends AbstractTextDecorator {
	private AbstractTextDecorator atd;
	private InputDetails id;

	private final String MOST_FREQUENT_PREFIX = "MOST_FREQUENT_";
	private final String MOST_FREQUENT_SUFFIX = "_MOST_FREQUENT";

	/**
	* Constructs a MostFrequentWordDecorator that stores
	* AbstractTextDecorator and InputDetails objects.
	*
	* @params AbstractTextDecorator atdIn abstract text decorator object
	* @params InputDetails idIn input details object
	*/
	public MostFrequentWordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
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
		String match = "(?i)(\\b" + getMostFrequentWord() + "\\b)";
		List<String> sentences = id.getSentences();

		final int size = sentences.size();
		for(int i=0; i<size; i++){
			sentences.set(i, sentences.get(i).replaceAll(match, (MOST_FREQUENT_PREFIX + "$1" + MOST_FREQUENT_SUFFIX)));
		}

		// Forward to the next decorator, if any.
		if (null != atd) {
			atd.processInputDetails();
		}
	}

	/**
	* Method to get most repeated word from the sentence.
	*
	* @return String returns the word as a string
	*/
	public String getMostFrequentWord() {
		Vector<String> allWords = new Vector<String>(); 
		List<String> sentences = id.getSentences();
		HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();

		for(String sentence : sentences) {
			String[] words = sentence.split("\\s+");
			allWords.addAll(Arrays.asList(words));
		}

		for (int i = 0; i < allWords.size(); i++) {
            if(wordCountMap.containsKey(allWords.get(i).toLowerCase())) {
                wordCountMap.put(allWords.get(i).toLowerCase(), wordCountMap.get(allWords.get(i).toLowerCase()) + 1);
            } else {
                wordCountMap.put(allWords.get(i).toLowerCase(), 1);
            }
        }
		
		Set<Map.Entry<String, Integer> > setofWords = wordCountMap.entrySet(); 
        String key = ""; 
        int value = 0; 
  
        for(Map.Entry<String, Integer> me : setofWords) {
            if (me.getValue() > value) {
                value = me.getValue();
                key = me.getKey();
            }
        }

        return key;
	}
}
