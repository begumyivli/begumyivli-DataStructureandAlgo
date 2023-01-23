import java.util.Comparator;

public class TrainingComparator implements Comparator<Player> {
	public int compare(Player p1, Player p2) {
		Double difference = Math.abs(p1.getTrainingQentrance() - p2.getTrainingQentrance());
		if (difference < 0.0000000001) {
			if (p1.ID < p2.ID) {
        		return -1;
        	}
        	else if (p1.ID > p2.ID) {
        		return 1;
        	}
        	else {
        		return 0;
        	}
		}
		else if (p1.getTrainingQentrance() < p2.getTrainingQentrance()) {
        	return -1;
        }
        else {
        	return 1;
        }
    }
}
