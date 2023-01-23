import java.util.Comparator;

public class PlayerComparator implements Comparator<Player>{

	@Override
	public int compare(Player p1, Player p2) {
		if (p1.getMassageQwaiting() < p2.getMassageQwaiting()) {
			return -1;
		}
		else if (p1.getMassageQwaiting() > p2.getMassageQwaiting()) {
			return 1;
		}
		else {
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

}
