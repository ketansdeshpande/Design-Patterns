package channelpopularity.context;

import channelpopularity.state.StateName;
import channelpopularity.util.Commands;
import channelpopularity.exceptions.InvalidInputException;

public interface ContextI {
	public void setCurrentState(StateName nextState);
	
	public void process(Commands commands) throws InvalidInputException;
}
