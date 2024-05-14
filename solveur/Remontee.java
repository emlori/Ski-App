package solveur;

public class Remontee extends Transition {
	String type; //type de remontée
	double duree_fixe; //duree fixe de la remontee
	double tps_100m; //duree pour 100m de dénivelé
	/**
	 * Constructeur de Remontee
	 * @param id
	 * @param nom
	 * @param pointDep
	 * @param pointArr
	 * @param type
	 * @param duree_fixe
	 * @param tps_100m
	 */
	public Remontee(int id, String nom, Point pointDep, Point pointArr, String type, double duree_fixe, double tps_100m) {
		super(id, nom, pointDep, pointArr);
		this.type = type;
		this.duree_fixe = duree_fixe;
		this.tps_100m = tps_100m;
	}
	/**
	 * Constructeur sans paramètre
	 */
	public Remontee() {
		super();
	};
	/**
	 * Redéfinition de la fonction abstraite déclarée Transition
	 * Fonction qui renvoie le temps de trajet pour la remontée en fonction du dénivelé
	 * Selon si l'utilisateur demande le temps en temps réel ou absolu,
	 * on ajoute un temps d'attente que l'on définit de façon aléatoire entre 0 et MAX_WAITING=1800secondes
	 */
	public double getTpsTraj(boolean tempsReel) {
		double Alt1 = this.pointDep.getAltitude();
		double Alt2 = this.pointArr.getAltitude();
		double deniv= Math.abs(Alt1-Alt2);
		
		double tps=deniv*this.tps_100m/100;
		
		double attente = 0;
		
		if(tempsReel == true)
			attente = Math.random()*MAX_WAITING;
		
		return tps+this.duree_fixe+attente;
	}
	
	

	protected String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	protected double getDuree_fixe() {
		return duree_fixe;
	}


	public void setDuree_fixe(double duree_fixe) {
		this.duree_fixe = duree_fixe;
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
