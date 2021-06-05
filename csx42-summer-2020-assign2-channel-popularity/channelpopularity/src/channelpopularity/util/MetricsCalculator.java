package channelpopularity.util;

import java.util.Vector;
import channelpopularity.util.Video;
import channelpopularity.state.StateI;
import java.lang.ArithmeticException;
import channelpopularity.state.StateName;
import channelpopularity.context.ChannelContext;

/**
* MetricsCalculator is a class to be used to calculate the metrics
* 	based on the parsed input.
*
* @author Ketan Deshpande
*/
public class MetricsCalculator {
	
	/**
	* Calculates the metrics and provides the state based on it.
	*
	* @return StateName Returns the statename object
	*
	* @exception ArithmeticException Invalid calculation performed.
	*/
	public StateName getStateByMetrics() throws ArithmeticException {
		int totalPopularityScore = 0;
		Vector<Video> videos = InputProcessor.map.get(StateI.CHANNEL_NAME);
		StateName state = null;
		if(0 < videos.size()) {
			for(Video v : videos) {
				totalPopularityScore += v.getPopularityScore();
			}

			ChannelContext.avgPopularityScore = totalPopularityScore / videos.size();
			if(ChannelContext.avgPopularityScore >=0 && ChannelContext.avgPopularityScore <= 1000) {
				state = StateName.UNPOPULAR;
			} else if(ChannelContext.avgPopularityScore > 1000 && ChannelContext.avgPopularityScore <= 10000) {
				state = StateName.MILDLY_POPULAR;
			} else if(ChannelContext.avgPopularityScore > 10000 && ChannelContext.avgPopularityScore <= 100000) {
				state = StateName.HIGHLY_POPULAR;
			} else if(ChannelContext.avgPopularityScore > 100000 && ChannelContext.avgPopularityScore <= Integer.MAX_VALUE) {
				state = StateName.ULTRA_POPULAR;
			}
		}

		return state;
	}
}
