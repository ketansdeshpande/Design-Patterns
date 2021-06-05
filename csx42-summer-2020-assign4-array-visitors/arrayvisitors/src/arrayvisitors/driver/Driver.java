package arrayvisitors.driver;

import java.lang.NumberFormatException;
import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayList;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.Results;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.FileProcessor;
import arrayvisitors.visitors.Element;
import arrayvisitors.visitors.Visitor;
import arrayvisitors.visitors.CommonIntsVisitor;
import arrayvisitors.visitors.MissingIntsVisitor;
import arrayvisitors.visitors.PopulateMyArrayVisitor;

/**
* The driver class which calls the internal functionality
* and generate the output.
*
* @author Ketan Deshpande
*/
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;
	protected static FileProcessor fileProcessor;

	public static void main(String[] args) throws Exception {

		/**
		* As the build.xml specifies the arguments as input,output or metrics, in case the
		* argument value is not given java takes the default value specified in
		* build.xml. To avoid that, below condition is used
		*/
		if ((args.length != 5) || (args[0].equals("${input1}")) || (args[1].equals("${input2}")) || (args[2].equals("${commonintsout}")) || (args[3].equals("${missingintsout}")) || (args[4].equals("${debug}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}

		MyLogger.setDebugValue(Integer.parseInt(args[4]));
		try {
			if(args[0].equals(args[1])) {
				System.err.println("Both input files cannot have same path or name.");
				System.exit(0);
			}

			if(args[2].equals(args[3])) {
				System.err.println("Both output files cannot have path or same name.");
				System.exit(0);
			}

			fileProcessor = new FileProcessor();
			fileProcessor.setFileProcessor(args[0]);
			MyArrayI myArray1 = new MyArray();
			Visitor populateMyArrayVisitor1 = new PopulateMyArrayVisitor(fileProcessor);
			myArray1.accept(populateMyArrayVisitor1);

			fileProcessor.setFileProcessor(args[1]);
			MyArrayI myArray2 = new MyArray();
			Visitor populateMyArrayVisitor2 = new PopulateMyArrayVisitor(fileProcessor);
			myArray2.accept(populateMyArrayVisitor2);

			MyArrayI[] myArrays = new MyArrayI[]{myArray1, myArray2};
			MyArrayListI myArrayList = new MyArrayList();
			myArrayList.setArray(myArrays);

			Visitor commonIntsVisitor = new CommonIntsVisitor();
			myArrayList.accept(commonIntsVisitor);

			Visitor missingIntsVisitor = new MissingIntsVisitor();
			myArray1.accept(missingIntsVisitor);
			myArray2.accept(missingIntsVisitor);

			Results results = new Results();
			Results.commonInts = Results.commonInts.deleteCharAt(Results.commonInts.length() - 1);
			Results.missingInts = Results.missingInts.deleteCharAt(Results.missingInts.length() - 1);
			results.printToFile(Results.commonInts.toString(), args[2]);
			results.printToFile(Results.missingInts.toString(), args[3]);
		} catch(NumberFormatException ne) {
			System.err.println("Found value other than integer in input file.");
			ne.printStackTrace();
			System.exit(0);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		} finally {
			if(fileProcessor != null)
				fileProcessor.close();
		}
	}
}
