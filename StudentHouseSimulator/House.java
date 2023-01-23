

public class House{

	final int ID;
	private int duration;
	private double rating;
	/**
	 * here, i created a house object
	 * @param ID
	 * @param duration- after how many periods a house will be empty
	 * @param rating
	 */
	public House(int ID, int duration, double rating)  {
		this.ID = ID;
		this.duration = duration;
		this.rating = rating;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		if (duration < 0) this.duration = 0;
		else this.duration = duration;
	}

	public double getRating() {
		return rating;
	}


}
