import java.util.Comparator;

public class HouseComparator implements Comparator<House> {
	/**
	 * this class sorts houses according to their id and duration
	 * if a house has lower duration, it has priority
	 * if durations are equal, house which has lower id has priority
	 */
	public int compare(House h1, House h2) {
        if (h1.getDuration() < h2.getDuration()) {
        	return -1;
        }
        else if (h1.getDuration()  > h2.getDuration()) {
        	return 1;
        }
        else {
        	if (h1.ID < h2.ID) {
        		return -1;
        	}
        	else if (h1.ID > h2.ID) {
        		return 1;
        	}
        	else {
        		return 0;
        	}
        }
        }
}
