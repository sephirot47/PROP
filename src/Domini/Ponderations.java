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
	
	public float GetThreshold()
	{
		return ((float)(duration + year + style + userAge + nearbyReproductions + author)) / 6;
	}

	public int GetDuration() {
		return duration;
	}

	public void SetDuration(int duration) {
		this.duration = duration;
	}

	public int GetYear() {
		return year;
	}

	public void SetYear(int year) {
		this.year = year;
	}

	public int GetStyle() {
		return style;
	}

	public void SetStyle(int style) {
		this.style = style;
	}

	public int GetUserAge() {
		return userAge;
	}

	public void SetUserAge(int userAge) {
		this.userAge = userAge;
	}

	public int GetNearbyReproductions() {
		return nearbyReproductions;
	}

	public void SetNearbyReproductions(int nearbyReproductions) {
		this.nearbyReproductions = nearbyReproductions;
	}

	public int GetAuthor() {
		return author;
	}

	public void SetAuthor(int author) {
		this.author = author;
	}

}
