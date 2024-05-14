package solveur;

public class Descente extends Transition{
	String type; //type de Descente
	double tps_100m; //temps pour descendre un dénivelé de 100m
	
	/**
	 * Constructeur de Descente
	 * @param id
	 * @param nom
	 * @param pointDep
	 * @param pointArr
	 * @param type
	 * @param tps_100m
	 */
	public Descente(int id, String nom, Point pointDep, Point pointArr, String type, double tps_100m) {
		super(id, nom, pointDep, pointArr);
		this.type = type;
		this.tps_100m = tps_100m;
	}
	/**
	 * Constructeur sans paramètre
	 */
	public Descente() {
		super();
	}
	
	/**
	 * Redéfinition de la fonction abstraite déclarée Transition
	 * Fonction qui retourne le temps de descente de la piste en fonction du dénivelé
	 */
	public double getTpsTraj(boolean tempsReel) {
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



	public void setTps_100m(double tps_100m) {
		this.tps_100m = tps_100m;
	}
	
	

	public String toString() {
		return "Transition number: " + id + " name: " + nom + " kind: " + type + " from: " + pointDep.getNom() +" to: " + pointArr.getNom() ;
	}
	
	
	
	
	
	
	
	
}
