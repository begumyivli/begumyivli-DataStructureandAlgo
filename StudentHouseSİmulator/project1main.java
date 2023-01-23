import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class project1main {

public static void main(String[] args) throws FileNotFoundException {

		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		/**
		 * here, i created a priority queue for students(pqS), a priority queue for houses(pqH),
		 * a priority queue for students who graduated without being able to settle in any house(pqNotNominated),
		 * a stack which is named stackS(when i poll a student from priority queue, i add her here), and
		 * a stack which is named stackH(when i poll a house for a student, i add it here.)
		 * 
		 */
		PriorityQueue<Student> pqS = new PriorityQueue<Student>(11, new StudentComparator());
		PriorityQueue<House> pqH = new PriorityQueue<House>(11, new HouseComparator());
		PriorityQueue<Student> pqNotNominated = new PriorityQueue<Student>(11, new StudentComparator());
		Stack<Student> stackS = new Stack<Student>();
		Stack<House> stackH = new Stack<House>();
		/**
		 * here i read the file and i created house or student object.
		 * i add the object to its priority queue.
		 */
		while(in.hasNextLine()) {
			String str = in.nextLine().trim();
			String[] arrOfStr = str.split(" ", 5);
			if(arrOfStr[0].equals("h")) {
				int ID = Integer.parseInt(arrOfStr[1]);
				int duration = Integer.parseInt(arrOfStr[2]);
				double rating = Double.parseDouble(arrOfStr[3]);
				House myHouse = new House(ID, duration, rating);
				pqH.add(myHouse);
			}
			if(arrOfStr[0].equals("s")) {
				int ID = Integer.parseInt(arrOfStr[1]);
				String name = arrOfStr[2];
				int duration = Integer.parseInt(arrOfStr[3]);
				double rating = Double.parseDouble(arrOfStr[4]);
				Student myStudent = new Student(ID, name, duration, rating);
				pqS.add(myStudent);
			}
		}
		/**
		 * here, i poll a student from queue, if her duration equals zero, i add her not nominated queue
		 * i created a flag boolean because if a student has 0 duration she should not settle in any house.
		 * for every student i poll a house from queue
		 * if student's duration > 0 and her rating is <= than the house which is polled from queue
		 * i add her to the student stack and set her duration to 0 because she should not be added to student queue again.
		 * and i set the house duration to student's initial duration because when she graduate, house will be empty
		 * if a student cannot settle any house i add her student stack and i pop every house in house stack
		 * and i add them into the house queue again for other student. when I look at every student on queue, 1 semester has passed.
		 *  
		 */
		while(!pqS.isEmpty()) {
			Student currentS = pqS.poll();
			boolean flag = true;
			if(currentS.getDuration() == 0) {
				pqNotNominated.add(currentS);
				flag = false;
			}
			if(flag) {
				while(!pqH.isEmpty()) {
				House currentH = pqH.poll();
				if (currentH.getDuration() == 0 && currentS.getRating() <= currentH.getRating()) {
					currentH.setDuration(currentS.getDuration());
					stackH.add(currentH);
					currentS.setDuration(0);
					break;
				}
				else {
					stackH.add(currentH);
				}
			}	
			}
			if(!pqS.isEmpty() && currentS.getDuration() != 0) {
				stackS.add(currentS);
			}
			if(!pqS.isEmpty()) {
				while(!stackH.isEmpty()) {
					House myH = stackH.pop();
					pqH.add(myH);
				}
			}
			if(pqS.isEmpty()) {
				if(currentS.getDuration() != 0) {
					stackS.add(currentS);
				}
				while(!pqH.isEmpty()) {
					stackH.add(pqH.poll());
				}
				while(!stackS.isEmpty()) {
					Student myS = stackS.pop();
					myS.setDuration(myS.getDuration()-1);
					if(myS.getDuration() == 0) {
						pqNotNominated.add(myS);
					}
					else {
						pqS.add(myS);
					}
				}
				while(!stackH.isEmpty()) {
					House myH = stackH.pop();
					myH.setDuration(myH.getDuration()-1);
					pqH.add(myH);
				}
			}
		}
		while(!pqNotNominated.isEmpty()) {
			out.println(pqNotNominated.poll().name);
		}
}
}


