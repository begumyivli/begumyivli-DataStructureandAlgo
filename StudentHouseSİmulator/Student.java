
public class Student{

	final int ID;
	final String name;
	private int duration;
	private double rating;
	/**
	 * here, i created a student object
	 * @param ID
	 * @param name
	 * @param duration- how many semesters are left for her to graduate
	 * @param rating- lowest rate for house she want
	 */
	
	public Student(int ID, String name, int duration, double rating) {
		this.ID = ID;
		this.name = name;
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
	

