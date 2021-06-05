package channelpopularity.state;

import java.util.Map;
import java.util.Vector;
import channelpopularity.util.Video;
import channelpopularity.context.ContextI;
import channelpopularity.exceptions.InvalidInputException;

public interface StateI {
	
	String CHANNEL_NAME = "MyChannel";
	
	public void addVideo(String name, ContextI channelContext) throws InvalidInputException;
	
	public void removeVideo(Video video, ContextI channelContext);
	
	public void calculateMetrics(ContextI channelContext);
	
	public void adRequest(int length);
}
