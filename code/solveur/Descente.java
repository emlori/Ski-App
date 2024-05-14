package solveur;

public class Descente extends Transition{
	String type;
	double tps_100m;
	
	public Descente(int id, String nom, Point pointDep, Point pointArr, String type, double tps_100m) {
		super(id, nom, pointDep, pointArr);
		this.type = type;
		this.tps_100m = tps_100m;
	}

	public Descente() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	
	
	
	public double getTpsTraj() {
		double Alt1 = this.pointDep.getAltitude();
		double Alt2 = this.pointArr.getAltitude();
		double deniv= Math.abs(Alt1-Alt2);
		
		deniv=deniv*this.tps_100m/100;
		
		
		return deniv;
	}



	protected String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	protected double getTps_100m() {
		return tps_100m;
	}



	protected void setTps_100m(double tps_100m) {
		this.tps_100m = tps_100m;
	}
	
	
	
	
	
	
	
	
	
	
	
}
