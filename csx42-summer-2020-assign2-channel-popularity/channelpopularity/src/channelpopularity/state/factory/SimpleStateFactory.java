package channelpopularity.state.factory;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.Unpopular;
import channelpopularity.state.MildlyPopular;
import channelpopularity.state.HighlyPopular;
import channelpopularity.state.UltraPopular;

/**
* SimpleStateFactory is a simple factory class which
* 	takes the argument and generates object based on it.
*
* @author Ketan Deshpande
*/
public class SimpleStateFactory implements SimpleStateFactoryI {
	
	/**
	* Called by the context class to create instances of state classes.
	*
	* @param stateName Object of StateName enum.
	*/
	@Override
	public StateI createState(StateName stateName) {
		switch(stateName) {
				case UNPOPULAR:
					return new Unpopular();
				case MILDLY_POPULAR:
					return new MildlyPopular();
				case HIGHLY_POPULAR:
					return new HighlyPopular();
				case ULTRA_POPULAR:
					return new UltraPopular();
			}
		return new Unpopular();
	}
}
