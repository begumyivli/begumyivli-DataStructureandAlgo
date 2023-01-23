
public class Player {

	final int ID;
	final int skillLevel;
	private int massage;
	private double physQwaiting;
	private double massageQwaiting;
	
	private double trainingQentrance;
	private double physQentrance;
	private double massageQentrance;
	
	private double trainingenter;
	private double trainwanting;
	private double massagewanting;
	private double trainingtime;
	private boolean status = true;
	
	private Physiotherapist phys = null;
	
	public Player(int ID, int skillLevel)  {
		this.ID = ID;
		this.skillLevel = skillLevel;
	}

	
	public double getTrainingQentrance() {
		return trainingQentrance;
	}
	public void setTrainingQentrance(double trainingQentrance) {
		this.trainingQentrance = trainingQentrance;
	}
	public double getPhysQentrance() {
		return physQentrance;
	}
	public void setPhysQentrance(double physQentrance) {
		this.physQentrance = physQentrance;
	}
	public double getMassageQentrance() {
		return massageQentrance;
	}
	public void setMassageQentrance(double massageQentrance) {
		this.massageQentrance = massageQentrance;
	}
	public double getTrainingtime() {
		return trainingtime;
	}
	public void setTrainingtime(double trainingtime) {
		this.trainingtime = trainingtime;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Physiotherapist getPhys() {
		return phys;
	}
	public void setPhys(Physiotherapist phys) {
		this.phys = phys;
	}
	public int getMassage() {
		return massage;
	}
	public void setMassage(int massage) {
		this.massage = massage;
	}


	public double getTrainwanting() {
		return trainwanting;
	}


	public void setTrainwanting(double trainwanting) {
		this.trainwanting = trainwanting;
	}


	public double getMassagewanting() {
		return massagewanting;
	}


	public void setMassagewanting(double massagewanting) {
		this.massagewanting = massagewanting;
	}


	public double getMassageQwaiting() {
		return massageQwaiting;
	}


	public void setMassageQwaiting(double massageQwaiting) {
		this.massageQwaiting = massageQwaiting;
	}


	public double getPhysQwaiting() {
		return physQwaiting;
	}


	public void setPhysQwaiting(double physQwaiting) {
		this.physQwaiting = physQwaiting;
	}


	public double getTrainingenter() {
		return trainingenter;
	}


	public void setTrainingenter(double trainingenter) {
		this.trainingenter = trainingenter;
	}

}
