package solveur;

public class Remontee extends Transition {
	String type;
	double duree_fixe;
	double tps_100m;
	
	public Remontee(int id, String nom, Point pointDep, Point pointArr, String type, double duree_fixe, double tps_100m) {
		super(id, nom, pointDep, pointArr);
		this.type = type;
		this.duree_fixe = duree_fixe;
		this.tps_100m = tps_100m;
	}

	public Remontee() {
		super();
	};
	
	public double getTpsTraj() {
		double Alt1 = this.pointDep.getAltitude();
		double Alt2 = this.pointArr.getAltitude();
		double deniv= Math.abs(Alt1-Alt2);
		
		deniv=deniv*this.tps_100m/100;
		
		
		return deniv+this.duree_fixe;
	}
	
	protected String getType() {
		return type;
	}




	protected void setType(String type) {
		this.type = type;
	}




	protected double getDurée_fixe() {
		return duree_fixe;
	}




	protected void setDurée_fixe(double durée_fixe) {
		this.duree_fixe = durée_fixe;
	}




	protected double getTps_100m() {
		return tps_100m;
	}




	protected void setTps_100m(double tps_100m) {
		this.tps_100m = tps_100m;
	}
	
}
