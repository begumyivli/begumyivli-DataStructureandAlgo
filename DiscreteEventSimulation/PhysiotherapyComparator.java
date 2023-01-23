import java.util.Comparator;

public class PhysiotherapyComparator implements Comparator<Player> {
	public int compare(Player p1, Player p2) {
		Double difference = Math.abs(p1.getTrainingtime() - p2.getTrainingtime());
		if (difference < 0.0000000001){
			Double difference2 = Math.abs(p1.getPhysQentrance() - p2.getPhysQentrance());
			if (difference2 < 0.0000000001) {
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
			else if (p1.getPhysQentrance() < p2.getPhysQentrance()) {
		        return -1;
		    }
		    else {
		    	return 1;
		    }
        }
		else if (p1.getTrainingtime() > p2.getTrainingtime()) {
        	return -1;
        }
        else {
        	return 1;
        }
	}
}
