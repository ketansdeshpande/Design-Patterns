package channelpopularity.context;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import channelpopularity.util.Video;
import channelpopularity.state.StateI;
import channelpopularity.util.Commands;
import channelpopularity.state.StateName;
import channelpopularity.state.Unpopular;
import channelpopularity.context.ContextI;
import channelpopularity.util.InputProcessor;
import channelpopularity.exceptions.InvalidInputException;
import channelpopularity.state.factory.SimpleStateFactoryI;

/**
* ChannelContext is a context class to be used to set the states
* 	based on the parsed input.
*
* @author Ketan Deshpande
*/
public class ChannelContext implements ContextI {
	private StateI currentState;
	private Map<StateName, StateI> availableStates = new HashMap<>();
	public static float avgPopularityScore;

	/**
	* Constructs a ChannelContext that creates the map of
	* 	available states.
	*
	* @param stateFactoryIn Object of interface SimpleStateFactoryI.
	* @param stateNames List of StateName enum.
	*/
	public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames) {
		// initialize states using factory instance and the provided state names.
		// initialize current state.
		for(StateName stateName : stateNames){
			availableStates.put(stateName, stateFactoryIn.createState(stateName));
		}
		setCurrentState(StateName.UNPOPULAR);
	}

	/**
	* Called by the States based on their logic of what
	* 	the popularity score of a video at any point.
	*
	* @param nextState Object of StateName enum.
	*/
	@Override
	public void setCurrentState(StateName nextState) {
		if (availableStates.containsKey(nextState)) { // for safety.
			currentState = availableStates.get(nextState);
		}
	}

	/**
	* Process the input and call the appropriate action.
	* 	on the current state.
	*
	* @param commands Object of Commands which contains operation
	* 		and data to be operated on.
	*/
	@Override
	public void process(Commands commands) throws InvalidInputException {
			Vector<Video> videos = InputProcessor.map.get(StateI.CHANNEL_NAME);
			boolean foundVideo = false;
			switch(commands.operation) {
				case ADD_VIDEO:
					for(Video v : videos) {
						if( v.name.equals(commands.data1) ) {
							throw new InvalidInputException("Video already exists.");
						}
					}
					currentState.addVideo(commands.data1, this);
					break;
					
				case REMOVE_VIDEO:
					for(Video v : videos) {
						if( v.name.equals(commands.data1) ) {
							currentState.removeVideo(v, this);
							foundVideo = true;
							break;
						}
					}
					if(!foundVideo) {
						throw new InvalidInputException("Video does not exist.");
					}
					break;
					
				case METRICS:
					for(Video v : videos) {
						if( v.name.equals(commands.data1) ) {
							
							String[] splittedArray = commands.data2.split(",");
							if(splittedArray.length == 3) {
								if(splittedArray[0].split("[^-\\d]{1,}").length > 0 && splittedArray[1].split("[^-\\d]{1,}").length > 0 && splittedArray[2].split("[^-\\d]{1,}").length > 0) {							
									v.views = Integer.parseInt(splittedArray[0].split("[^-\\d]{1,}")[1]);
									v.likes = Integer.parseInt(splittedArray[1].split("[^-\\d]{1,}")[1]);
									v.dislikes = Integer.parseInt(splittedArray[2].split("[^-\\d]{1,}")[1]);
								} else {
									throw new InvalidInputException("Invalid input.");
								}

								if(v.views < 0) {
									throw new InvalidInputException("Views cannot be negative.");
								}
								v.calculatePopularityScore(v.views, v.likes, v.dislikes);
								InputProcessor.map.put(StateI.CHANNEL_NAME, videos);
							}
						}
					}
					currentState.calculateMetrics(this);
					break;
					
				case AD_REQUEST:
					for(Video v : videos) {
						if( v.name.equals(commands.data1) ) {
							currentState.adRequest(Integer.parseInt(commands.data2.split("=")[1]));
							foundVideo = true;
							break;
						}
					}
					if(!foundVideo) {
						throw new InvalidInputException("Video does not exist.");
					}
					break;
			}
	}
}