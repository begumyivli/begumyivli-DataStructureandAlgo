import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Scanner;

public class project2main {

	public static void main(String[] args) throws FileNotFoundException{
		Locale.setDefault(new Locale("en", "US"));
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		
		ArrayList<Player> players = new ArrayList<Player>();
		PriorityQueue<Event> events = new PriorityQueue<Event>(11, new EventComparator());
		PriorityQueue<Physiotherapist> phys = new PriorityQueue<Physiotherapist>(11, new PhysiotherapistComparator());
		
		int coach = 0;
		int masseur = 0;
		
		while (in.hasNextLine()) {
			int playerNum = Integer.parseInt(in.nextLine().trim());
			for (int i = 0; i < playerNum; i++) {
				String[] arrOfStr = in.nextLine().trim().split(" ", 2);
				Player myPlayer = new Player(i, Integer.parseInt(arrOfStr[1]));
				players.add(myPlayer);
			}
			int eventNum = Integer.parseInt(in.nextLine().trim());
			for (int j = 0; j < eventNum; j++) {
				String[] arrOfStr = in.nextLine().trim().split(" ", 4);
				Event myEvent = new Event(arrOfStr[0], Integer.parseInt(arrOfStr[1]), Double.parseDouble(arrOfStr[2]), Double.parseDouble(arrOfStr[3]), 1);
				events.add(myEvent);
			}
			int physnum = in.nextInt();
			for (int j = 0; j < physnum; j++) {
				Physiotherapist myphys = new Physiotherapist(j, in.nextDouble());
				phys.add(myphys);
			}
			coach = in.nextInt();
			masseur = in.nextInt();
			if(in.hasNextLine()) {
				break;
			}
		}
	PriorityQueue<Player> trainingPQ = new PriorityQueue<Player>(11, new TrainingComparator());
	PriorityQueue<Player> physPQ = new PriorityQueue<Player>(11, new PhysiotherapyComparator());
	PriorityQueue<Player> massagePQ = new PriorityQueue<Player>(11, new MassageComparator());
	
	int validTrainingAttempt = 0;
	int validPhysAttempt = 0;
	int validMassageAttempt = 0;
	int maxLenTraining = 0;
	int maxLenPhys = 0;
	int maxLenMassage = 0;
	double totalTraining = 0.0;
	double totalPhys = 0;
	double totalMassage = 0.0;
	double totalwaitingTraining = 0.0;
	double totalwaitingPhys = 0.0;
	double totalwaitingMassage = 0.0;
	double totalTurnAround = 0.0;
	int invalid = 0;
	int cancelled = 0;
	double second = 0;
	while(!events.isEmpty()) {
		Event myevent = events.poll();
		Player myplayer = players.get(myevent.getPlayerID());
		second = myevent.getArrivingsec();
		if(myevent.type.equals("t")) {
			if(myevent.getEntrance() == 1) { //training entrance event has created
				if(myplayer.isStatus()) {
					if(coach > 0) {  //if there is an available coach, player is going to training service
						coach--;
						myplayer.setTrainingtime(myevent.getTakingsec());
						myplayer.setTrainingenter(second);
						totalTraining += myevent.getTakingsec();
						myplayer.setStatus(false);
						validTrainingAttempt++;
						Event exitEvent = new Event("t", myplayer.ID, (second+myevent.getTakingsec()), 0, 0); //i created a training exit event for player
						events.add(exitEvent);
						continue;
					}
					else { // if there is not a coach, player is added to trainingPQ
						myplayer.setTrainingQentrance(second);
						myplayer.setTrainwanting(myevent.getTakingsec()); //i saved the desired training time of the player because when player is 
						myplayer.setTrainingenter(second);                //polled from PQ i created exit event and for exit event's arriving time this time will be necessary
						myplayer.setStatus(false);
						trainingPQ.add(myplayer);
						if (trainingPQ.size() > maxLenTraining) {
							maxLenTraining = trainingPQ.size();
						}
						continue;
					}
				}
				else {
					cancelled++; //if player's status is false, (s)he is in a PQ or a process so i increase cancelled attempt
					continue;
				}
			}
			else if(myevent.getEntrance() == 0) { //training exit event has created
				coach++;
				myplayer.setStatus(true);
				Event physenterEvent = new Event("f", myplayer.ID, second, 0, 1); //when a player exits from training service, physiotherapy event should created immediately
				events.add(physenterEvent);
				if(!trainingPQ.isEmpty()) { //when a player exits from training service, there will be an available coach, so i poll another player from PQ
					Player newplayer = trainingPQ.poll();
					newplayer.setTrainingtime(newplayer.getTrainwanting());
					totalTraining += newplayer.getTrainingtime();
					validTrainingAttempt++;
					totalwaitingTraining += second-newplayer.getTrainingQentrance();
					Event exitEvent = new Event("t", newplayer.ID, (second+newplayer.getTrainingtime()), 0, 0);
					events.add(exitEvent);
					coach--;
				}
				continue;
			}
		}

		else if(myevent.type.equals("f")) {
			if(myevent.getEntrance() == 1) { //physiotherapy entrance event has created
				if(myplayer.isStatus()) {
					if(!phys.isEmpty()) {  //if there is an available physiotherapist, player is going to the physiotherapy service
						Physiotherapist myphys = phys.poll();
						myplayer.setPhys(myphys); //i assign the physiotherapist to the player to add the physiotherapist back to the PQ when the player logs out.
						totalPhys += myphys.serviceTime;
						myplayer.setStatus(false);
						validPhysAttempt++;
						Event exitEvent = new Event("f", myplayer.ID, (second+myphys.serviceTime), 0, 0);
						events.add(exitEvent);
						continue;
					}
					else { // if there is not a physiotherapist, player will go to the physiotherapy PQ
						myplayer.setPhysQentrance(second);
						myplayer.setStatus(false);
						physPQ.add(myplayer);
						if (physPQ.size() > maxLenPhys) {
							maxLenPhys = physPQ.size();
						}
						continue;
					}
				}
				else {
					cancelled++;
					continue;
				}
			}
			else if(myevent.getEntrance() == 0) { //physiotherapy exit event has created
				phys.add(myplayer.getPhys());
				myplayer.setPhys(null);
				myplayer.setStatus(true);
				totalTurnAround += second - myplayer.getTrainingenter(); //it is equal to the time from training entrance to physiotherapy exit
				if(!physPQ.isEmpty()) {
					Physiotherapist newphys = phys.poll();
					Player newplayer = physPQ.poll();
					newplayer.setPhys(newphys);
					totalPhys += newphys.serviceTime;
					validPhysAttempt++;
					newplayer.setPhysQwaiting(newplayer.getPhysQwaiting()+(second-newplayer.getPhysQentrance()));
					totalwaitingPhys += second-newplayer.getPhysQentrance();
					Event exitEvent = new Event("f", newplayer.ID, (second+newphys.serviceTime), 0, 0);
					events.add(exitEvent);
				}
				continue;
			}
		}		
		else if(myevent.type.equals("m")) {
			if(myevent.getEntrance() == 1) { //massage entrance event has created
				if(myplayer.getMassage()==3) { // if a player has 3 massage already, (s)he cannot get another massage service
					invalid++;
					continue;
				}
				if(myplayer.isStatus()) {
					if(masseur > 0) {  //if there is an available masseur, player is going to massage service
						masseur--;
						myplayer.setMassage(myplayer.getMassage()+1);
						totalMassage += myevent.getTakingsec();
						myplayer.setStatus(false);
						validMassageAttempt++;
						Event exitEvent = new Event("m", myplayer.ID, (second+myevent.getTakingsec()), 0, 0);
						events.add(exitEvent);
						continue;
					}
					else { // if there is not an available masseur, player will go massage PQ
						myplayer.setMassageQentrance(second);
						myplayer.setMassagewanting(myevent.getTakingsec());
						myplayer.setMassage(myplayer.getMassage()+1);
						myplayer.setStatus(false);
						massagePQ.add(myplayer);
						if (massagePQ.size() > maxLenMassage) {
							maxLenMassage = massagePQ.size();
						}
						continue;
					}
				}
				else {
					cancelled++;
					continue;
				}
			}
			else if(myevent.getEntrance() == 0) { //massage exit event has created
				myplayer.setStatus(true);
				masseur++;
				if(!massagePQ.isEmpty()) {
					Player newplayer = massagePQ.poll();
					newplayer.setMassageQwaiting(newplayer.getMassageQwaiting()+(second-newplayer.getMassageQentrance()));
					totalMassage += newplayer.getMassagewanting();
					validMassageAttempt++;
					totalwaitingMassage += second-newplayer.getMassageQentrance();
					Event exitEvent = new Event("m", newplayer.ID, (second+newplayer.getMassagewanting()), 0, 0);
					events.add(exitEvent);
					masseur--;
				}
				continue;
			}
			}
		}
	//printing
	out.println(maxLenTraining);
	out.println(maxLenPhys);
	out.println(maxLenMassage);
	out.println(String.format("%.3f",totalwaitingTraining/validTrainingAttempt));
	out.println(String.format("%.3f",totalwaitingPhys/validPhysAttempt));
	out.println(String.format("%.3f",totalwaitingMassage/validMassageAttempt));
	out.println(String.format("%.3f",totalTraining/validTrainingAttempt));
	out.println(String.format("%.3f",totalPhys/validPhysAttempt));
	out.println(String.format("%.3f",totalMassage/validMassageAttempt));
	out.println(String.format("%.3f",totalTurnAround/validTrainingAttempt));
	
	double mostphysQ = 0;
	int mostphysid = 0;
	for (Player p : players) {
		if(p.getPhysQwaiting() > mostphysQ) {
			mostphysQ = p.getPhysQwaiting();
			mostphysid = p.ID;
		}
	}
	out.println(mostphysid+" "+String.format("%.3f",mostphysQ));
	
	double leastmassageQ = 0;
	int leastmassageid= 0;
	boolean flag = true;
	ArrayList<Player> playersForMassagePQ = new ArrayList<Player>();
	for (Player p : players) {
		if(p.getMassage() == 3) {
			flag = false;
			playersForMassagePQ.add(p);
		}
	}
	if(!flag) {
		 Collections.sort(playersForMassagePQ, new PlayerComparator());
		 leastmassageQ = playersForMassagePQ.get(0).getMassageQwaiting();
		 leastmassageid = playersForMassagePQ.get(0).ID;
		 out.println(leastmassageid+" "+String.format("%.3f",leastmassageQ));
	}
	if(flag) {
		out.println(-1+" "+-1);
	}

	out.println(invalid);
	out.println(cancelled);
	out.println(String.format("%.3f",second));

	}
}
