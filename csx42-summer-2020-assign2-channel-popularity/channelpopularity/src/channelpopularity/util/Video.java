package channelpopularity.util;

/**
* Video is a data structure to store the video details
*
* @author Ketan Deshpande
*/
public class Video {

	public String name;
	public int likes, dislikes, views, popularityScore;

	/**
	* Initializes a Video object with default.
	*
	* @param String videoName Name of the video from input file.
	*/
	public Video(String videoName) {
		name = videoName;
		popularityScore = 0;
		likes = 0;
		dislikes = 0;
		views = 0;
	}

	/**
	* Called by the Context to calculate metrics.
	*
	* @param int views Views of the video.
	* @param int likes Likes of the video.
	* @param int dislikes Dislikes of the video.
	*/
	public void calculatePopularityScore(int views, int likes, int dislikes) {
		popularityScore += views + 2 * (likes - dislikes);
	}

	/**
	* Getter method to fetch the popularity score of the video.
	*
	* @return int Returns the popularity score of the video.
	*/
	public int getPopularityScore() {
		if(0 < this.popularityScore)
			return this.popularityScore;
		else
			return 0;
	}
}
