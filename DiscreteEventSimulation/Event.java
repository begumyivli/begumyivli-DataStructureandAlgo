
public class Event {
	final String type;
	private int playerID;
	private double arrivingsec;
	private double takingsec;
	private int entrance;
	public Event(String type, int playerID, double arrivingsec, double takingsec, int entrance)  {
		this.type = type;
		this.playerID = playerID;
		this.arrivingsec = arrivingsec;
		this.takingsec = takingsec;
		this.entrance = entrance;
	}
	
	/**
	 * @return the arrivingsec
	 */
	public double getArrivingsec() {
		return arrivingsec;
	}

	/**
	 * @return the takingsec
	 */
	public double getTakingsec() {
		return takingsec;
	}

	/**
	 * @param arrivingsec the arrivingsec to set
	 */
	public void setArrivingsec(double arrivingsec) {
		this.arrivingsec = arrivingsec;
	}

	/**
	 * @param takingsec the takingsec to set
	 */
	public void setTakingsec(double takingsec) {
		this.takingsec = takingsec;
	}

	public int getEntrance() {
		return entrance;
	}

	public void setEntrance(int entrance) {
		this.entrance = entrance;
	}

	public int getPlayerID() {
		return playerID;
	}


}
