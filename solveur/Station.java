package solveur;
import java.util.*;
import java.util.stream.Stream;

public class Station {
	public Map<Integer,Point> points = new HashMap<Integer,Point>(); //Collection de points, accessible par leur id
	
	public Map<Integer,Boolean> mark = new HashMap<Integer,Boolean>(); //Collection de booléen, permet de directement savoir si un point est marqué ou non par l'algorithme Dijkstra via son id
	public Map<Integer,Double> pi = new HashMap<Integer,Double>(); //Collection de Poids, ou on stocke le poids de chaque point pour l'algorithme de Dijkstra
	public Map<Integer,Transition> pere = new HashMap<Integer,Transition>(); //Collection de Transition, ou on stocke toutes les transitions ou l'algo de Dijkstra est passée
	
	
	/**
	 * Constructeur sans paramètre de Sation
	 */
	public Station() {
		
	}

	/**
	 * Méthode de Dijkstra stocke dans Pi le poids sur les points, dans pere les transitions dans lesquelles l'algo est passées
	 * et dans mark, il indique quels points ont été marqués par l'algo ou non
	 * @param ptDepart
	 * @param ptArrivee
	 * @param tempsReel
	 */
	public void dijkstra(Point ptDepart, Point ptArrivee,boolean tempsReel) {

		int k,i,j; //indices de boucle
		this.addPe(ptDepart.getId(),new Navette()); //transition
		this.addM(ptDepart.getId(),false);      // Point de départ marqué à Faux
		this.setMark(ptArrivee.getId(), false);	// Point d'arrivée marqué à Faux
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
		
		
		while(mark.get(ptArrivee.getId()) == false){                                           
			double minweight = 9999999;
			//Choisir le point x tel que le point soit non marqué et le poids minimal
			for(i=1;i<=nbPoints;i++) {
				x = points.get(i);
				idx = x.getId();                                                                   

				
				if(mark.get(idx) == false && pi.get(idx) < minweight) { 
					//on enregistre le nouveau poids minimal
					minweight = pi.get(idx);
					
					xMin=x;
					//Enregistrer l'id du point pour connaitre le point courant sélectionné
					minweightID = idx;
				}
			}
			//Il y a un point sur lequel aller si (au minimum) un point n'est pas marqué !!
			if (minweightID >= 0) {
				this.setMark(minweightID,true);
				int nbTransition = xMin.getTransi().size();

				//on regarde toutes les transitions qui partent de ce point
				for(k=0;k<nbTransition;k++) {
					int y = transixTok(xMin,k).getPointArr().getId(); //y prend l'id du point d'arrivee de la transition
					
					//poids propagé
					double newweight = pi.get(minweightID)+xMin.getTransi().get(k).getTpsTraj(tempsReel);
					if(newweight < pi.get(y)) {
						pi.replace(y,newweight); //on met à jour le poids du point concerné
						addPe(y, transixTok(xMin,k)); //ajout de la transition
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


	
	 public Map<Integer,Transition> getPere() {
		return pere;
	}
	
	 public Map<Integer, Double> getPi() {
			return pi;
	}


	public PointCoord rechercheNom(double unx, double uny) {

		ArrayList<PointCoord> pointscoord = new ArrayList<PointCoord>();
		
		for(int key : this.points.keySet()){
			if(this.points.get(key) instanceof PointCoord) {
				pointscoord.add((PointCoord) this.points.get(key));
			}
		}

		return pointscoord.stream()
			.filter(m->m.getX()==(unx) && m.getY()==(uny))
			.findAny()
			.orElse(null);

	}
	
	
	
	public Point recherchePoint(String nom) {
		
		
		Collection<Point> pts = new ArrayList <Point>();
		pts= points.values();
		
		
		return pts.stream()
			.filter(m->m.getNom().equals(nom))
			.findAny()
			.orElse(null);

	}
	
	
	
	
	
}
