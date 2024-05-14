package applications;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import solveur.*;

public class TestNoyau {
	
    static Station stat = new Station();
	public static boolean tempsReel = false;

	
	 public static void main(String[] argv) {


		 // Essai 1
		 /*
		 stat.setPoints(1,new Point(1,"haut ts blainon",2150.2));
		 stat.setPoints(2,new Point(2,"bas ts blainon",1990.0));
		 stat.setPoints(3,new Point(3,"las donas",2300.0));
		 
		 Navette ds = new Navette(1,"Blainon", stat.getPoint(1),stat.getPoint(2),"bleu",5.0);
		 stat.addTransi(1,(ds));
		 
		 Navette ds2 = new Navette(2,"saispu", stat.getPoint(3),stat.getPoint(1),"bleu",8.0);
		 stat.addTransi(3,(ds2));
		 
		 stat.dijkstra(stat.getPoint(3),stat.getPoint(2));
		 
		 System.out.println(stat.mark);
		 System.out.println(stat.pi);
		 System.out.println(stat.pere);
		 
	
		 
		*/
		 
		 
		// Essai 2
		 
		stat.setPoints(1,new Point(1,"A",4000.1));
		stat.setPoints(2,new PointCoord(new Point(2,"B",4000.1),102.1,80.5));
		stat.setPoints(3,new Point(3,"C",4000.1));
		stat.setPoints(4,new Point(4,"D",4000.1));
		stat.setPoints(5,new Point(5,"E",4000.1));
		stat.setPoints(6,new Point(6,"F",4000.1));
		stat.setPoints(7,new Point(7,"G",4000.1));
		stat.setPoints(8,new Point(8,"H",4000.1));
		stat.setPoints(9,new Point(9,"I",4000.1));
		stat.setPoints(10,new Point(10,"J",4000.1));
				 
		Navette ds = new Navette(1,"jbdovn", stat.getPoint(1),stat.getPoint(2),"bleu",5.0);
		stat.addTransi(1,ds);
				 
		Navette ds2 = new Navette(2,"ihfc", stat.getPoint(1),stat.getPoint(3),"bleu",8.0);
		stat.addTransi(1,(ds2));
		
		Navette ds3 = new Navette(3,"efgb", stat.getPoint(2),stat.getPoint(3),"bleu",2.0);
		stat.addTransi(2,(ds3));
		
		Navette ds4 = new Navette(4,"zkef", stat.getPoint(2),stat.getPoint(4),"bleu",5.0);
		stat.addTransi(2,(ds4));
		
		Navette ds5 = new Navette(5,"zkef", stat.getPoint(3),stat.getPoint(5),"bleu",3.0);
		stat.addTransi(3,(ds5));
		
		Navette ds6 = new Navette(6,"elzfg", stat.getPoint(3),stat.getPoint(6),"bleu",1.0);
		stat.addTransi(3,(ds6));
		
		Navette ds7 = new Navette(7,"éiefn", stat.getPoint(4),stat.getPoint(7),"bleu",4.0);
		stat.addTransi(4,(ds7));
		
		Navette ds8 = new Navette(8,"éefhvb", stat.getPoint(4),stat.getPoint(8),"bleu",5.0);
		stat.addTransi(4,(ds8));
		
		Navette ds9 = new Navette(9,"nbaivc", stat.getPoint(5),stat.getPoint(7),"bleu",4.0);
		stat.addTransi(5,(ds9));
		
		Navette ds10 = new Navette(10,"cnabei", stat.getPoint(6),stat.getPoint(9),"bleu",4.0);
		stat.addTransi(6,ds10);
		
		Navette ds11 = new Navette(11,"paoeijn", stat.getPoint(7),stat.getPoint(8),"bleu",3.5);
		stat.addTransi(7,ds11);
		
		Navette ds12 = new Navette(12,"éieefhdv", stat.getPoint(7),stat.getPoint(10),"bleu",40);
		stat.addTransi(7,ds12);
		
		Navette ds13 = new Navette(13,"nudhzb", stat.getPoint(8),stat.getPoint(10),"bleu",2);
		stat.addTransi(8,ds13);
		
		Navette ds14 = new Navette(14,"rhzcbnd", stat.getPoint(9),stat.getPoint(10),"bleu",2.0);
		stat.addTransi(9,ds14);
		
				 
		stat.dijkstra(stat.getPoint(1),stat.getPoint(10), tempsReel);
				 
		System.out.println(stat.mark);
		System.out.println(stat.pi);
		for (int i=0;i<stat.pere.size();i++) {
			try {
				System.out.println(stat.pere.get(i).toString());
			}catch(NullPointerException e) {}
		}
	
		afficherChemin(1, 10);
		
		 
	 }
	 /**
	  * Méthode d'affichage du chemin
	  * @param idPointDepart
	  * @param idPointArrivee
	  */
	 public static void afficherChemin(int idPointDepart, int idPointArrivee) {
			int idPCour;
			System.out.println("\n \nFastest path between"+stat.getPoints().get(idPointDepart).getNom()+stat.getPoints().get(idPointDepart).toString()+" and "+stat.getPoints().get(idPointArrivee).getNom()+stat.getPoints().get(idPointArrivee).toString()+" :");
			idPCour = idPointArrivee;
			List<Transition> transiti = new ArrayList<Transition>();
			
			int idPointDepartTransition = stat.getPere().get(idPCour).getPointDep().getId();
			transiti.add(stat.getPere().get(idPCour));
			
			//On remonte les transitions jusqu'au point de depart, pour les avoir dans l'ordre
			while(idPointDepartTransition != idPointDepart) {
				
				//Passage au point depart de la transition pour passer à la transi suivante
				idPCour = idPointDepartTransition;
				idPointDepartTransition = stat.getPere().get(idPCour).getPointDep().getId();
				
				transiti.add(stat.getPere().get(idPCour));
			}
			//les transitionns dans la liste transiti sont dans l'ordre inverse
			Collections.reverse(transiti);
			
			//On affiche les transition dans le bon ordre
			for(int i=0;i<transiti.size();i++)
				System.out.println("\n"+transiti.get(i));
			
			
			Double tpsTotal = stat.getPi().get(idPointArrivee);
			System.out.println("Total time : "+tpsTotal);
			Point ptdep=stat.getPoints().get(idPointDepart);
			Point ptar=stat.getPoints().get(idPointArrivee);
			System.out.println("Cumulative altitude difference: "+ptdep.Deniv(ptar)+"m");
		
		}
}