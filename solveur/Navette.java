package solveur;

public class Navette extends Transition{
	String type; //type de navette
	double duree_traj; //durée du trajet de la navette
	
	/**
	 * Constructeur sans paramètre
	 */
	public Navette() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur avec tous les paramètres
	 * @param id
	 * @param nom
	 * @param pointDep
	 * @param pointArr
	 * @param type
	 * @param duree_traj
	 */
	public Navette(int id, String nom, Point pointDep, Point pointArr, String type, double duree_traj) {
		super(id, nom, pointDep, pointArr);
		this.type = type;
		this.duree_traj = duree_traj;
	}

	/**
	 * Redéfinition de la fonction abstraite déclarée Transition
	 * Fonction qui renvoie le temps de trajet pour la navette
	 * Selon si l'utilisateur demande le temps en temps réel ou absolu,
	 * on ajoute un temps d'attente que l'on définit de façon aléatoire entre 0 et MAX_WAITING=1800secondes
	 */
	public double getTpsTraj(boolean tempsReel) {
		double attente = 0;
		
		if(tempsReel == true) 
			attente = Math.random()*MAX_WAITING;
			
		return this.duree_traj+attente;
	}

	
	protected String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	protected double getDuree_traj() {
		return duree_traj;
	}


	public void setDuree_traj(double duree_traj) {
		this.duree_traj = duree_traj;
	}
	
	

	public String toString() {
		return "Transition number: " + id + " name: " + nom + " kind: " + type + " from: " + pointDep.getNom() +" to: " + pointArr.getNom() ;
	}
	
	
	
	
	
	
	
	
}
