import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
	/**
	 * this class sorts students according to their id
	 * if a student has lower id, she has priority
	 */
	public int compare(Student s1, Student s2) {
        if (s1.ID < s2.ID) {
        	return -1;
        }
        else if (s1.ID > s2.ID) {
            return 1;
        }
        return 0;
        }
}
