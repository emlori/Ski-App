package solveur;
import java.util.*;

public class Station {
	public Map<Integer,Point> points = new HashMap<Integer,Point>();
	
	public Map<Integer,Boolean> mark = new HashMap<Integer,Boolean>();
	public Map<Integer,Double> pi = new HashMap<Integer,Double>();
	public Map<Integer,Transition> pere = new HashMap<Integer,Transition>();
	
	
	public Station(Map<Integer, Point> points, Map<Integer, Boolean> mark, Map<Integer, Double> pi,
			Map<Integer, Transition> pere) {
		this.points = points;
		this.mark = mark;
		this.pi = pi;
		this.pere = pere;
	}
	
	public Station() {
		
	}

	public void dijkstra(Point ptDepart, Point ptArrivee) {
		
		
		int k,i,j;
		//this.addPi(ptDepart.getId(),0.0);
		this.addPe(ptDepart.getId(),new Navette()); //transition
		this.addM(ptDepart.getId(),false);                                                           // changé 0 par id du pt de depart
		this.addM(ptArrivee.getId(), false);
		Point x= null;
		Point xMin=null;

		int minweightID = -1;
		int idx = -1;
		
		int nbPoints = this.points.size();	
		
		for(j=1;j<=nbPoints;j++) {
			 setMark(j,false);
			 setPi(j,999999);
		}
		this.setPi(ptDepart.getId(),0.0);	
		
		
		while(mark.get(ptArrivee.getId()) == false){                                              // erreur corrigé normalement
			double minweight = 9999999;
			//Choisir le point x tel que le point soit non marqué et le poids minimal
			for(i=1;i<=nbPoints;i++) {
				x = points.get(i);
				idx = x.getId();                                                                   //erreur corrigé

				
				if(mark.get(idx) == false && pi.get(idx) < minweight) {                            //erreur
					minweight = pi.get(idx);
					//Enregistrer l'id du point pour connaitre le point courant sélectionné
					xMin=x;
					minweightID = idx;
				}
			}
			//Il y a un point sur lequel aller si (au minimum) un point n'est pas marqué !!
			if (minweightID >= 0) {
				this.setMark(minweightID,true);
				int nbTransition = xMin.getTransi().size();

				
				for(k=0;k<nbTransition;k++) {
					int y = transixTok(xMin,k).getPointArr().getId(); //y prend l'id du point d'arrivee de la transition
					//addM(y, false);
					
					//addPi(y, transixTok(x,k).getTpsTraj());
			
					double newweight = pi.get(minweightID)+xMin.getTransi().get(k).getTpsTraj();
					System.out.println("pi.get("+minweightID+") = "+pi.get(minweightID));
					if(newweight < pi.get(y)) {
						pi.replace(y,newweight);
						addPe(y, transixTok(xMin,k));
					}
				}
			}

		}
	}
		
	private Transition transixTok(Point p, int k) {
		return p.getTransi().get(k);
	}
	public void addPi(Integer cle, Double w) {
		pi.put(cle,w);
	}
	public void addPe(Integer cle, Transition t) {
		pere.put(cle,t);
	}
	public void addM(Integer cle, boolean b) {
		mark.put(cle,b);
	}

	
	
	
	
	
	protected void setMark(Integer id, boolean bo) {
		if(mark.containsKey(id)) {
			this.mark.replace(id, bo);
		}else {
			this.mark.put(id, bo);
		}
	}
	
	
	public void setPoints(Integer id, Point pt) {
		if(points.containsKey(id)) {
			this.points.replace(id, pt);
		}
		else {
			this.points.put(id, pt);
		}
	}
	
	public void setPi(Integer id, double d) {
		if(pi.containsKey(id)) {
			this.pi.replace(id, d);
		}
		else {
			this.pi.put(id, d);
		}
	}
	
	
	public void addTransi(Integer id,Transition tr) {
		this.points.get(id).addTransi(tr);
	}
	
	public Point getPoint(Integer id) {
		return this.points.get(id);
	}

	
	
	

	public Map<Integer, Point> getPoints() {
		return points;
	}



	
	

	
	
	
	
	
	
	
	
}
