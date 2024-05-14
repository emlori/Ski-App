package solveur;

import java.util.ArrayList;

public class PointCoord extends Point {

	double x; //coordonnée x
	double y; //coordonnée y
	
	/**
	 * Constructeur de PointCoord
	 * @param id
	 * @param nom
	 * @param altitude
	 * @param pointTransitions
	 * @param unx
	 * @param uny
	 */
	public PointCoord(Integer id, String nom, Double altitude,ArrayList<Transition> pointTransitions,double unx, double uny) {
		super(id, nom, altitude, pointTransitions);
		this.x= unx;
		this.y= uny;
		
	}
	/**
	 * Constructeur qui permet de transformer un Point en PoinCoord en prenant un point en paramètre
	 * @param pt
	 * @param unx
	 * @param uny
	 */
	public PointCoord(Point pt, double unx, double uny) {
		this.id = pt.id;
		this.nom = pt.nom;
		this.altitude = pt.altitude;
		this.pointTransitions = pt.pointTransitions;
		this.x= unx;
		this.y = uny;
	}
	
	
	public double getX() {
		return x;
	}


	public double getY() {
		return y;
	}

	

	
	public void setX(double x) {
		this.x = x;
	}


	public void setY(double y) {
		this.y = y;
	}


	@Override
	public String toString() {
		return super.toString() + "<"+x+","+y+">";
	}
	
	
	
	
	
	
	

}
