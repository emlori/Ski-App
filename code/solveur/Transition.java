package solveur;

public abstract class Transition {

	int id;
	String nom;
	Point pointDep;
	Point pointArr;
	

	public Transition(int id, String nom, Point pointDep, Point pointArr) {
		super();
		this.id = id;
		this.nom = nom;
		this.pointDep = pointDep;
		this.pointArr = pointArr;
	}
	
	public Transition() {
		super();
		this.id = -1;
		this.nom = "";
		this.pointDep = null;
		this.pointArr = null;
	}
	

	public abstract double getTpsTraj();
	
	
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




	protected Point getPointDep() {
		return pointDep;
	}




	public void setPointDep(Point pointDep) {
		this.pointDep = pointDep;
	}




	protected Point getPointArr() {
		return pointArr;
	}


	public void setPointArr(Point pointArr) {
		this.pointArr = pointArr;
	}

	@Override
	public String toString() {
		return "Transition [id=" + id + ", nom=" + nom + ", pointDep=" + pointDep.toString() + ", pointArr=" + pointArr.toString() + "]";
	}

	
	
	
	
	
	
	
	
	
}
