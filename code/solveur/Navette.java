package solveur;

public class Navette extends Transition{
	String type;
	double duree_traj;
	

	public Navette() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Navette(int id, String nom, Point pointDep, Point pointArr, String type, double duree_traj) {
		super(id, nom, pointDep, pointArr);
		this.type = type;
		this.duree_traj = duree_traj;
	}


	public double getTpsTraj() {
		
		return this.duree_traj;
	}


	protected String getType() {
		return type;
	}


	protected void setType(String type) {
		this.type = type;
	}


	protected double getDuree_traj() {
		return duree_traj;
	}


	protected void setDuree_traj(double duree_traj) {
		this.duree_traj = duree_traj;
	}
	
	
	
	
	
	
	
	
	
	
	
}
