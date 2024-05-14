package solveur;

import java.util.ArrayList;

public class PointCoord extends Point {

	double x;
	double y;
	
	
	public PointCoord(Integer id, String nom, Double altitude,ArrayList<Transition> pointTransitions,double unx, double uny) {
		super(id, nom, altitude, pointTransitions);
		this.x= unx;
		this.y= uny;
		
	}
	
	//il se trouve que le x est toujours renseigné avant le y
	public PointCoord(Point pt, int unx) {
		this.id = pt.id;
		this.nom = pt.nom;
		this.altitude = pt.altitude;
		this.pointTransitions = pt.pointTransitions;
		this.x= unx;
	}
	
	public void PointCoordY(PointCoord pt, int uny) {
		this.id = pt.id;
		this.nom = pt.nom;
		this.altitude = pt.altitude;
		this.pointTransitions = pt.pointTransitions;
		this.x = pt.x;
		this.y= uny;
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


	public String toString() {
		return "<"+x+","+y+">";
	}
	
	
	
	
	
	
	

}
