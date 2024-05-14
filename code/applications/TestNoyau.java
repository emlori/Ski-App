package applications;
import solveur.*;
import java.util.*;

public class TestNoyau {

	static Scanner in = new Scanner(System.in);
    static Station stat = new Station();
	
	 public static void main(String[] argv) {


		 // Essai 1
		 /*
		 stat.setPoints(1,new Point(1,"haut ts blainon",2150.2));
		 stat.setPoints(2,new Point(2,"bas ts blainon",1990.0));
		 stat.setPoints(3,new Point(3,"las donas",2300.0));
		 
		 Descente ds = new Descente(1,"Blainon", stat.getPoint(1),stat.getPoint(2),"bleu",5.0);
		 stat.addTransi(1,(ds));
		 
		 Descente ds2 = new Descente(2,"saispu", stat.getPoint(3),stat.getPoint(1),"bleu",8.0);
		 stat.addTransi(3,(ds2));
		 
		 stat.dijkstra(stat.getPoint(3),stat.getPoint(2));
		 
		 System.out.println(stat.mark);
		 System.out.println(stat.pi);
		 System.out.println(stat.pere);
		 
		 System.out.println("ouais");
		 
		*/
		 //erreur stationligne 30
		 
		 
		// Essai 2
		 
		stat.setPoints(1,new Point(1,"A",2150.2));
		stat.setPoints(2,new Point(2,"B",1990.0));
		stat.setPoints(3,new Point(3,"C",2500.0));
		stat.setPoints(4,new Point(4,"D",2225.0));
		stat.setPoints(5,new Point(5,"E",1553.0));
		stat.setPoints(6,new Point(6,"F",2900.7));
		stat.setPoints(7,new Point(7,"G",2300.0));
		stat.setPoints(8,new Point(8,"H",1748.0));
		stat.setPoints(9,new Point(9,"I",1876.0));
		stat.setPoints(10,new Point(10,"J",1945.0));
				 
		Descente ds = new Descente(1,"jbdovn", stat.getPoint(1),stat.getPoint(2),"bleu",5.0);
		stat.addTransi(1,(ds));
				 
		Descente ds2 = new Descente(2,"ihfc", stat.getPoint(1),stat.getPoint(3),"bleu",8.0);
		stat.addTransi(1,(ds2));
		
		Descente ds3 = new Descente(3,"efgb", stat.getPoint(2),stat.getPoint(3),"bleu",2.0);
		stat.addTransi(2,(ds3));
		
		Descente ds4 = new Descente(4,"zkef", stat.getPoint(2),stat.getPoint(4),"bleu",5.0);
		stat.addTransi(2,(ds4));
		
		Descente ds5 = new Descente(5,"zkef", stat.getPoint(3),stat.getPoint(5),"bleu",3.0);
		stat.addTransi(3,(ds5));
		
		Descente ds6 = new Descente(6,"elzfg", stat.getPoint(3),stat.getPoint(6),"bleu",1.0);
		stat.addTransi(3,(ds6));
		
		Descente ds7 = new Descente(7,"éiefn", stat.getPoint(4),stat.getPoint(7),"bleu",4.0);
		stat.addTransi(4,(ds7));
		
		Descente ds8 = new Descente(8,"éefhvb", stat.getPoint(4),stat.getPoint(8),"bleu",5.0);
		stat.addTransi(4,(ds8));
		
		Descente ds9 = new Descente(9,"nbaivc", stat.getPoint(5),stat.getPoint(7),"bleu",4.0);
		stat.addTransi(5,(ds9));
		
		Descente ds10 = new Descente(10,"cnabei", stat.getPoint(6),stat.getPoint(9),"bleu",4.0);
		stat.addTransi(6,(ds10));
		
		Descente ds11 = new Descente(11,"paoeijn", stat.getPoint(7),stat.getPoint(8),"bleu",3.5);
		stat.addTransi(7,(ds11));
		
		Descente ds12 = new Descente(12,"éieefhdv", stat.getPoint(7),stat.getPoint(10),"bleu",2.0);
		stat.addTransi(7,(ds12));
		
		Descente ds13 = new Descente(13,"nudhzb", stat.getPoint(8),stat.getPoint(10),"bleu",2.0);
		stat.addTransi(8,(ds13));
		
		Descente ds14 = new Descente(14,"rhzcbnd", stat.getPoint(9),stat.getPoint(10),"bleu",2.0);
		stat.addTransi(9,(ds14));
		
				 
		stat.dijkstra(stat.getPoint(1),stat.getPoint(8));
				 
		System.out.println(stat.mark);
		System.out.println(stat.pi);
		for (int i=0;i<stat.pere.size();i++) {
			try {
				System.out.println(stat.pere.get(i).toString());
			}catch(NullPointerException e) {}
		}
		
		//System.out.println(stat.points);
		
		 
	 }
	 /*
		Point pointDepart = stat.getPoint(1);
		Point pointArrivee = stat.getPoint(8);
		idPointDepart = pointDepart.getId();
		
		idPointArrivee = pointArrivee.getId();
		int idPCour;
		
		public void afficherChemin(int idPointDepart, int idPointArrivee) {
			System.out.println("Fastest path between"+points.get(idPointDepart).getNom()+points.get(idPointDepart).toString()+" and "+points.get(idPointArrivee).getNom()+points.get(idPointArrivee).toString()+" :");
			idPCour = idPointDepart;
			while(stat.getPere().get(idPCour).get) {
				System.out.println();
				idPCour = 
			}
			int tpsTotal = pi.get(idPointArrivee);
			System.out.println("Total time : "+tpsTotal);
			System.out.println("Cumulative altitude difference: "+points.get(idPointDepart).Deniv(pointArrivee)+"m");
		}
		
	 
	 */
}