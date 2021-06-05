package wordPlay.driver;

import wordPlay.util.FileProcessor;
import wordPlay.util.Results;
import wordPlay.handler.WordRotator;
import wordPlay.metrics.MetricsCalculator;
import wordPlay.exceptions.InvalidInputException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ketan Deshpande
 *
 */
public class Driver {
	public static StringBuilder str = new StringBuilder();
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		String word;
		if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${output}")) || (args[2].equals("${metrics}"))) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			System.exit(0);
		}

		try {
			Pattern pattern = Pattern.compile("[^a-zA-Z0-9.]");
			FileProcessor fp = new FileProcessor(args[0]);
			WordRotator wr = new WordRotator();
			MetricsCalculator mcr = new MetricsCalculator();
			word = fp.poll();
			if(word == null) throw new Exception("File is empty.");
			do {
				Matcher match = pattern.matcher(word);
				if(match.find()) throw new InvalidInputException("Invalid input.");
				wr.rotateWord(word);
				mcr.calculateMetrics(word);
			} while((word = fp.poll()) != null );

			Results res = new Results();
			str = str.deleteCharAt(str.length() - 1);
			res.displayOnConsole(str.toString());
			res.printToFile(str.toString(), args[1]);
			String metricsString = "AVG_NUM_WORDS_PER_SENTENCE = " + MetricsCalculator.avgNumWordsPerSent;
			metricsString += "\nAVG_WORD_LENGTH = " + MetricsCalculator.avgWordLength;
			res.printToFile( metricsString, args[2]);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		} finally {}
	}

	@Override
	public String toString(){
		return "str: " + str.toString();
	}
}
