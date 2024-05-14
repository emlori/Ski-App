package applications;

import solveur.*;
import java.util.*;
import parseur.*;


public class Console {
	
	static Scanner sc = new Scanner(System.in);
	static Station st = new Station(); //création d'une station vide
	public static boolean tempsReel = false; //variable globale booléenne pour gérer le temps réel ou non
	
	public static void main(String[] argv) throws Exception{
	
			//charger la station à partir du fichier xml
		
			st = StationParser.parse(argv);			
			
			//menu utilisateur
			//doit choisir point depart et point arrivee
			//doit choisir le mode de calcul (temp reel ou non)

			int choix = 0;
			
			
			do {
			
				choix = saisieMenu();

				switch (choix) {
					case 1 : Chemin();
						break;
					case 2: tempsReel = true; //on active le temps reel
							Chemin();
							tempsReel = false; //on remet par défaut le temps en absolu
						break;
					case 0 : 
						break;
				}
			
			}while(choix != 0);
			
			

	}
	
	

	public static void Chemin() throws Exception{
		int iddep=0, idarrivee=0;
		do {
			try {
				System.out.println("Donner l'identifiant de votre point de départ\n");
				iddep = sc.nextInt();
				System.out.println("Donner l'identifiant de votre point d'arrivée\n");
				idarrivee = sc.nextInt();
				if(iddep<1 || iddep > 37 || idarrivee < 1 || idarrivee > 37) {
					System.out.println("Ce point n'existe pas, les points sont numérotés de 1 à 37");
				}
			}catch(InputMismatchException e) { //on capture l'exception sur les types (pas un entier)
				System.out.println("Erreur de saisie, veuillez n'entrer que des entiers\n");
				sc.next();
			}
		}while(iddep<1 || iddep > 37 || idarrivee < 1 || idarrivee > 37);
		Point dep = st.getPoint(iddep);
		Point ar = st.getPoint(idarrivee);
		
		//Recherche du chemin optimal
		st.dijkstra(dep, ar,tempsReel);
		//affichage du chemin optimal
		afficherChemin(iddep, idarrivee);
		
	}
	/**
	 * Méthode qui demande à l'utilisateur son choix
	 * On capture l'exception ou il rentrerait autre chose qu'un entier et on affiche un message
	 * @return
	 */
	public static int saisieMenu() {
		int choix = -1;
			try {
				System.out.println("Choisir une operation\n\n1 - Chemin en temps absolu\n2 - Chemin en temps réel\n0 - Quitter ");
				choix = sc.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Erreur de saisie, veuillez n'entrer que des entiers parmi, 1, 2 ou 0 pour quitter.\n");
				sc.next();
			}
		return choix;
	}
	/**
	 * Méthode pour afficher le chemin
	 * @param idPointDepart
	 * @param idPointArrivee
	 */
	public static void afficherChemin(int idPointDepart, int idPointArrivee) {
		int idPCour;
		System.out.println("\n \nFastest path between: "+st.getPoints().get(idPointDepart).getNom()+" "+st.getPoints().get(idPointDepart).toString()+" and "+st.getPoints().get(idPointArrivee).getNom()+" "+st.getPoints().get(idPointArrivee).toString()+" :");
		idPCour = idPointArrivee;
		List<Transition> transiti = new ArrayList<Transition>();
		
		Transition transiCour = st.getPere().get(idPCour);
		int idPointDepartTransition = transiCour.getPointDep().getId();
		transiti.add(transiCour);
		
		double cumul_dif_alt = transiCour.getPointArr().Deniv(transiCour.getPointDep());
		
		//On remonte les transitions jusqu'au point de depart, pour les avoir dans l'ordre
		while(idPointDepartTransition != idPointDepart) {
			
			//Passage au point depart de la transition pour passer à la transi suivante
			//Le point de depart de la transition précédente devient le point d'arrivee de la transition qui suit
			idPCour = idPointDepartTransition;
			//on affecte la nouvelle transition courante
			transiCour = st.getPere().get(idPCour);
			//on stock l'id du point de depart de la transition courante
			idPointDepartTransition = transiCour.getPointDep().getId();
			
			transiti.add(transiCour);
			cumul_dif_alt += transiCour.getPointArr().Deniv(transiCour.getPointDep());
		}
		//on a dans transiti toues les transitions, mais l'ordre inverse
		//donc on inverse l'ordre des transition spour les afficher dans le bon ordre
		Collections.reverse(transiti);
		
		
		for(int i=0;i<transiti.size();i++)
			System.out.println("\t"+transiti.get(i));
		
		
		int tpsTotal = (int)Math.floor(st.getPi().get(idPointArrivee));
		int nbHeures = (int) Math.floor(tpsTotal/3600);
		int nbMin = (int) Math.floor((tpsTotal-nbHeures*3600)/60);
		int nbSec = (int) Math.floor(tpsTotal-nbHeures*3600-nbMin*60);
		System.out.println("Total time : "+tpsTotal+" seconds"+" ("+nbHeures+"h "+nbMin+"mn "+nbSec+"s)");
		System.out.println("Cumulative altitude difference: "+cumul_dif_alt+"m\n\n");
	
	}
	
	public boolean getTempsReel() {
		return tempsReel;
	}

}
