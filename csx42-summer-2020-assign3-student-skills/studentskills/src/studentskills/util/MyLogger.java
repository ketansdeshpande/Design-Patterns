package studentskills.util;

/**
* MyLogger is a utility to generate the logs while processing
*
*
* @author Ketan Deshpande
*/

public class MyLogger{

    public static enum DebugLevel { CONSTRUCTOR, FILE_PROCESSOR, INPUT_PROCESSOR, STUDENT_RECORD, TREE_HELPER, NONE };

    private static DebugLevel debugLevel;

    public static void setDebugValue (int levelIn) {
		switch (levelIn) {
			case 5: debugLevel = DebugLevel.TREE_HELPER; break;
			case 4: debugLevel = DebugLevel.STUDENT_RECORD; break;
			case 3: debugLevel = DebugLevel.INPUT_PROCESSOR; break;
			case 2: debugLevel = DebugLevel.CONSTRUCTOR; break;
			case 1: debugLevel = DebugLevel.FILE_PROCESSOR; break;
			default: debugLevel = DebugLevel.NONE; break;
		}
    }

    public static void setDebugValue (DebugLevel levelIn) {
		debugLevel = levelIn;
    }

    public static void writeMessage (String message, DebugLevel levelIn ) {
		if (levelIn == debugLevel)
			System.out.println(message);
    }

    public String toString() {
		return "The debug level has been set to the following " + debugLevel;
    }
}