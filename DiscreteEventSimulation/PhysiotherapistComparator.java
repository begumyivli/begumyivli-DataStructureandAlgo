import java.util.Comparator;

public class PhysiotherapistComparator implements Comparator<Physiotherapist>{
	public int compare(Physiotherapist p1, Physiotherapist p2) {
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
}
