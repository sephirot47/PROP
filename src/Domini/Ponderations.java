package Domini;


/**
 * Contains a set of ponderations that can generate a solution.
 * The higher the number of an element the more it will be taken into account for the
 * final solution.
 *
 */
public class Ponderations 
{
	private int duration,
				year,
				style,
				userAge,
				nearbyReproductions,
				author; //De 0 a 10
	
	public Ponderations()
	{
		duration = year = style = userAge = nearbyReproductions = author = 5;
	}
	
	public float getThreshold()
	{
		return ((float)(duration + year + style + userAge + nearbyReproductions + author)) / 6;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public int getNearbyReproductions() {
		return nearbyReproductions;
	}

	public void setNearbyReproductions(int nearbyReproductions) {
		this.nearbyReproductions = nearbyReproductions;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

}
