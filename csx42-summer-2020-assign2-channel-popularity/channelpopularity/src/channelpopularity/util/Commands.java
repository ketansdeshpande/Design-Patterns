package channelpopularity.util;

import channelpopularity.operation.Operation;
/**
* Commands is a data structure to store the commands from input file
*
* @author Ketan Deshpande
*/
public class Commands {

	public String data1, data2;
	public Operation operation;

	/**
	* Constructs a command that creates the structure of input value.
	*
	* @param stateFactoryIn Object of interface SimpleStateFactoryI.
	* @param stateNames List of StateName enum.
	*/
	public Commands(Operation inputOperation, String dataInOne, String dataInTwo) {
		operation = inputOperation;
		data1 = dataInOne;
		data2 = dataInTwo;
	}
}
