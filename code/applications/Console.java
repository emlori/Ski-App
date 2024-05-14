package applications;

import java.util.Scanner;
import solveur.*;
import java.util.*;
import parseur.*;


public class Console {
	
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] argv) throws Exception{
	
		//charger la station à partir du fichier xml
		
		Station st = StationParser.parse(argv);
		
		System.out.println(st.points);
		//menu utilisateur
		//doit choisir point depart et point arrivee
		//doit choisir le mode de calcul (temp reel ou non
		
		
		//affiche le chemin le plus rapide
		//appel méthode affichage
	}
}
