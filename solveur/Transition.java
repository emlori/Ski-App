package solveur;

public abstract class Transition {
	final int MAX_WAITING = 1800; //Constante de temps d'attent maximale, 30min max soit 1800 secondes
	int id; //identifiant de la transition
	String nom; //nom de la transition
	Point pointDep; //point de départ de la transition
	Point pointArr; //point d'arrivée de la transition
	
	/**
	 * Constructeur de Transition avec les paramètres
	 * @param id
	 * @param nom
	 * @param pointDep
	 * @param pointArr
	 */
	public Transition(int id, String nom, Point pointDep, Point pointArr) {
		super();
		this.id = id;
		this.nom = nom;
		this.pointDep = pointDep;
		this.pointArr = pointArr;
	}
	/**
	 * Constructeur sans paramètres de Transition
	 */
	public Transition() {
		super();
		this.id = -1;
		this.nom = "";
		this.pointDep = null;
		this.pointArr = null;
	}
	
	/**
	 * Méthode abstraite avec en paramètre le booléen qui permet de savir si l'utilisateur souhaite son chemon en temps absolu ou en temps réel
	 * La méthode sera redéfinie dans les sous-classes Remontee, Navette et Descente
	 */
	public abstract double getTpsTraj(boolean tempsReel);
	
	
	protected int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	protected String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Point getPointArr() {
		return pointArr;
	}
	public Point getPointDep() {
		return pointDep;
	}

	public void setPointDep(Point pointDep) {
		this.pointDep = pointDep;
	}

	public void setPointArr(Point pointArr) {
		this.pointArr = pointArr;
	}

	@Override
	public abstract String toString();
	
	
	
	
	
	
	
	
	
}
