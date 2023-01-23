import java.util.Comparator;

public class EventComparator implements Comparator<Event>{
	public int compare(Event e1, Event e2) {
		Double difference = Math.abs(e1.getArrivingsec() - e2.getArrivingsec());
		if (difference < 0.0000000001){
				if (e1.getPlayerID() < e2.getPlayerID()) {
					return -1;
				}
				else if (e1.getPlayerID() > e2.getPlayerID()) {
					return 1;
				}
				else {
					if (e1.getEntrance() < e2.getEntrance()) {
						return -1;
					}
					else if (e1.getEntrance() > e2.getEntrance()) {
						return 1;
					}
					else {
						return 0;
					}
				}
		}
		else if (e1.getArrivingsec() < e2.getArrivingsec()) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
