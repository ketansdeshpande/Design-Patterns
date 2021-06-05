package textdecorators;

import java.util.List;
import textdecorators.util.InputDetails;
/**
* The SentenceDecorator class is responsible for retrieving the
* the sentences. This is the topmost decorator.
*
* @author Ketan Deshpande
*/

public class SentenceDecorator extends AbstractTextDecorator {
	private AbstractTextDecorator atd;
	private InputDetails id;
	private final String BEGIN_SENTENCE = "BEGIN_SENTENCE__";
	private final String END_SENTENCE = "__END_SENTENCE";

	/**
	* Constructs a SentenceDecorator that stores
	* AbstractTextDecorator and InputDetails objects.
	*
	* @params AbstractTextDecorator atdIn abstract text decorator object
	* @params InputDetails idIn input details object
	*/
	public SentenceDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
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
		List<String> listOfSentences = id.getSentences();
		final int size = listOfSentences.size();
		for(int i=0; i<size; i++){
			listOfSentences.set(i, (BEGIN_SENTENCE + listOfSentences.get(i) + END_SENTENCE));
		}

		// Forward to the next decorator, if any.
		if (null != atd) {
			atd.processInputDetails();
		}
	}
}
