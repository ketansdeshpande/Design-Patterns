package channelpopularity.state;

import java.util.Map;
import java.util.Vector;
import channelpopularity.util.Video;
import channelpopularity.util.InputProcessor;
import channelpopularity.util.MetricsCalculator;
import channelpopularity.state.StateName;
import channelpopularity.context.ContextI;
import channelpopularity.context.ChannelContext;

/**
* HighlyPopular is one of the states.
*
* @author Ketan Deshpande
*/
public class HighlyPopular implements StateI {

	/**
	* Performs the add video operation given on the video name.
	*
	* @param String name Name of the video to be added.
	* @param ContextI channelContext Instance of Context interface.
	*/
	@Override
	public void addVideo(String name, ContextI channelContext) {
		Vector<Video> videos = InputProcessor.map.get(StateI.CHANNEL_NAME);
		Video video = new Video(name);
		videos.add(video);
		InputProcessor.map.put(StateI.CHANNEL_NAME, videos);
		MetricsCalculator met = new MetricsCalculator();
		channelContext.setCurrentState(met.getStateByMetrics());
		InputProcessor.str.append("HIGHLY_POPULAR__VIDEO_ADDED::" + name);
	}

	/**
	* Performs the remove video operation given on the video name.
	*
	* @param Video video Instance of video to be removed.
	* @param ContextI channelContext Instance of Context interface.
	*/
	@Override
	public void removeVideo(Video video, ContextI channelContext) {
		Vector<Video> videos = InputProcessor.map.get(StateI.CHANNEL_NAME);
		if(0 < videos.size()) {
			videos.remove(videos.indexOf(video));
			InputProcessor.map.put(StateI.CHANNEL_NAME, videos);
			MetricsCalculator met = new MetricsCalculator();
			channelContext.setCurrentState(met.getStateByMetrics());
		}
		InputProcessor.str.append("HIGHLY_POPULAR__VIDEO_REMOVED::" + video.name);
	}

	/**
	* Performs the calculations based on the input provided from the file.
	*
	* @param ContextI channelContext Instance of Context interface.
	*/
	@Override
	public void calculateMetrics(ContextI channelContext) {
		MetricsCalculator met = new MetricsCalculator();
		channelContext.setCurrentState(met.getStateByMetrics());
		InputProcessor.str.append("HIGHLY_POPULAR__POPULARITY_SCORE_UPDATE::" + ChannelContext.avgPopularityScore);
	}		

	/**
	* Checks the ad request to be approved or not.
	*
	* @param int length Length of the video.
	*/
	@Override
	public void adRequest(int length) {
		if(length > 1 && length <= 30 )
			InputProcessor.str.append("HIGHLY_POPULAR__AD_REQUEST::APPROVED");
		else
			InputProcessor.str.append("HIGHLY_POPULAR__AD_REQUEST::REJECTED");
	}
}
