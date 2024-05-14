package solveur;
import java.util.*;


public class Point {
	Integer id; //identifiant du point
	String nom; //nom du point
	Double altitude; //altitude du point
	ArrayList<Transition> pointTransitions = new ArrayList<Transition>(); //liste de toutes les transitions qui partent de ce point
	
	/**
	 * Constructeur sans paramètre
	 */
	public Point() {
		
	};
	/**
	 * Constructeur avec id comme paramètre
	 * @param id
	 */
	public Point(Integer id) {
		this.id = id;
		
	};
	/**
	 * Constructeur avec les paramètres
	 * @param id
	 * @param nom
	 * @param altitude
	 * @param pointTransitions
	 */
	public Point(Integer id, String nom, Double altitude, ArrayList<Transition> pointTransitions) {
		this(id,nom,altitude);
		this.pointTransitions = pointTransitions;
	}
	/**
	 * Constructeur avec les paramètres
	 * @param id
	 * @param nom
	 * @param altitude
	 */
	public Point(Integer id, String nom, Double altitude) {
		this(id);
		this.nom = nom;
		this.altitude = altitude;
	}


	public Double Deniv(Point p) {
		return Math.abs(p.getAltitude()-this.altitude);
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	/**
	 * Procédure qui ajoute une transition passé en paramètre à la liste de transitions parant de ce point
	 * @param tr
	 */
	public void addTransi(Transition tr) {
		this.pointTransitions.add(tr);
	}

	/**
	 * Méthode get qui permet d'accéder à la liste de transtions de ce point
	 * @return
	 */
	public ArrayList<Transition> getTransi(){
		return this.pointTransitions;
	}

	
	@Override
	public String toString() {
		return "";
	}
	
}
