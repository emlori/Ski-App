package solveur;
import java.util.*;


public class Point {
	Integer id;
	String nom;
	Double altitude;
	ArrayList<Transition> pointTransitions = new ArrayList<Transition>();
	
	public Point() {};
	public Point(int id) {
		this.id = id;
	};
	
	public Point(Integer id, String nom, Double altitude, ArrayList<Transition> pointTransitions) {
		this.id = id;
		this.nom = nom;
		this.altitude = altitude;
		this.pointTransitions = pointTransitions;
	}
	
	public Point(Integer id, String nom, Double altitude) {
		this.id = id;
		this.nom = nom;
		this.altitude = altitude;
	}
		
	protected ArrayList<Transition> getPointTransitions() {
		return pointTransitions;
	}


	protected void setPointTransitions(ArrayList<Transition> pointTransitions) {
		this.pointTransitions = pointTransitions;
	}


	public Double Deniv(Point p) {
		return this.altitude-p.getAltitude();
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

	
	public void addTransi(Transition tr) {
		this.pointTransitions.add(tr);
	}


	public ArrayList<Transition> getTransi(){
		return this.pointTransitions;
	}

	@Override
	public String toString() {
		return "Point [id=" + id + ", nom=" + nom + ", altitude=" + altitude + "]";
	}
	
	
}
