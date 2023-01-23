import java.util.Comparator;

public class MassageComparator implements Comparator<Player> {
	public int compare(Player p1, Player p2) {
        if (p1.skillLevel > p2.skillLevel) {
        	return -1;
        }
        else if (p1.skillLevel < p2.skillLevel) {
        	return 1;
        }
        else {
        	Double difference = Math.abs(p1.getMassageQentrance() - p2.getMassageQentrance());
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
			else if (p1.getMassageQentrance() < p2.getMassageQentrance()) {
		        return -1;
		    }
		    else {
		    	return 1;
		    }
        }
	}
}
