package ihm;

import solveur.*;
import java.util.*;
import parseur.*;


import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;



@SuppressWarnings("serial")
public class EasySki extends JFrame {
	//emplacement de l'image de la station
	/*********************************************************************************/
	static String img="/home/e/l/eloric/S7/Java/ressources/station/station.jpg";
	/*********************************************************************************/
	
	static Scanner sc = new Scanner(System.in);
	Point dep=null; //déclaration d'un point de départ, on doit retrouver le point de départ demandé par l'utilisateur sur l'interface graphique
	Point arr=null; //idem pour le point d'arrivée

	static Station st = new Station();
	public boolean tempsReel = false;

	/**
	 * Precision de la grille de reperage.
	 * 
	 */
	protected static final int DELTA = 20;
	/**
	 * Taille du plan.
	 */
	protected int hauteurPlan, largeurPlan;

	/**
	 * ImageCanvas scrollable
	 * NE PAS MODIFIER
	 * Plan "cliquable" = image + grille.	 * 
	 */
	protected ImageCanvas canvas = new ImageCanvas();
	/**
	 * Vue "scrollable" du plan.
	 */
	protected ScrollPane planView = new ScrollPane();
	
	/**
	 * InteractionPanel
	 * Panel d'interaction avec l'utilisateur
	 */	
	protected InteractionPanel interact = new InteractionPanel();

	/**
	 * Charge une image de plan et construit l'interface graphique.
	 * 
	 * @param fichierImage
	 *            Nom du fichier image de plan
	 * @throws java.io.IOException
	 *             Erreur d'acces au fichier
	 */
	public EasySki(String fichierImage) throws java.io.IOException {

		// Chargement de l'image
		Image im = new ImageIcon(fichierImage).getImage();
		hauteurPlan = im.getHeight(this);
		largeurPlan = im.getWidth(this);

		// Preparation de la vue scrollable de l'image
		canvas.setImage(im); // image a afficher dans le Canvas
		canvas.addMouseListener(interact.getSelectionPanel()); // notification de clic sur la grille
		planView.setSize(hauteurPlan + DELTA, largeurPlan + DELTA);
		planView.add(canvas); // apposition de la vue scrollable sur l'ImageCanvas

		// Construction de l'ensemble
		setLayout(new BorderLayout());
		add(planView, BorderLayout.CENTER);
		add(interact, BorderLayout.SOUTH);

		// Evenement de fermeture de la fenetre : quitter l'application
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
	}

	/**
	 * 
	 * Classe utilitaire interne (sous-classe de java.awt.Canvas = plan (jpg) + grille de coordonnees cliquable
	 * NE PAS MODIFIER
	 */
	class ImageCanvas extends Canvas {
		Image image;

		void setImage(Image img) {
			image = img;
			setSize(largeurPlan, hauteurPlan);
			repaint();
		}

		/**
		 * Affiche l'image + la grille.
		 * 
		 */
		public void paint(Graphics g) {
			if (image != null)
				g.drawImage(image, DELTA, DELTA, this);

			// Grille de repérage apposée
			int lignes = hauteurPlan / DELTA;
			int colonnes = largeurPlan / DELTA;
			g.setColor(Color.gray);
			for (int i = 1; i <= lignes; i++) {
				g.drawString("" + i, 0, (i + 1) * DELTA);
				g.drawLine(DELTA, i * DELTA, DELTA + largeurPlan, i * DELTA);
			}
			g.drawLine(DELTA, (lignes + 1) * DELTA, DELTA + largeurPlan, (lignes + 1) * DELTA);
			for (int i = 1; i <= colonnes; i++) {
				g.drawString("" + i, i * DELTA, DELTA / 2);
				g.drawLine(i * DELTA, DELTA, i * DELTA, DELTA + hauteurPlan);
			}
			g.drawLine((colonnes + 1) * DELTA, DELTA, (colonnes + 1) * DELTA, DELTA + hauteurPlan);
		}
	}

	/**
	 * InteractionPanel
	 * Panel d'interaction avec l'utilisateur composee :
	 * - d'un panel de selection utilisateur "SelectionSubPanel"
	 * - une zone de texte "resultTextArea" pour afficher les resultats 
	 * 
	 */
	class InteractionPanel extends JPanel{
 		SelectionSubPanel selectionPanel = new SelectionSubPanel();
		JTextArea resultTextArea = new JTextArea(5, 30);

		InteractionPanel() {
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			add(selectionPanel);

			resultTextArea.setEditable(false); // non editable (produit par les resultats de l'application)
			add(resultTextArea);
		}

		/**
		 * SelectionSubPanel
		 * Sous-panel de selection utilisateur :
		 * - a l'ecoute (implements MouseListener) de clics utilisateur sur le plan:
		 * enregistre les coordonnees (x,y) et les affiche dans les label "X=...", "Y=..."
		 * - permet de saisir un nom de lieu dans le JTextField "lieu"
		 * - bouton : "GO!" : reporte les saisies dans la zone "resultTextArea"		 * 
		 */
		class SelectionSubPanel extends JPanel implements MouseListener
		{
			/**
			 * Valeurs de x, y cliquees
			 */
			int x, y;
			/**
			 * Affichage de X,Y cliquees
			 */
			JLabel xLabel = new JLabel("X");
			/**
			 * Affichage de X,Y cliquees
			 */
			JLabel yLabel = new JLabel("Y");
			/**
			 * Champ de saisie d'un nom de lieu
			 */
			JTextField lieu = new JTextField(20);
			JTextField lieuDep = new JTextField(20);
			JTextField lieuArr = new JTextField(20);
			
			JTextField coordx= new JTextField(3);
			JTextField coordy = new JTextField(3);
			/**
			 * Reporte les saisies dans la zone "resultTextArea"
			 */
			JButton go = new JButton("GO!"); // reporte les saisies dans la zone "resultTextArea"
			JButton setdep = new JButton("SET DEPART");
			JButton setarr = new JButton("SET ARRIVEE");
			
			JRadioButton tpsreel = new JRadioButton("TEMPS REEL");
			JButton effacer= new JButton("EFFACER DATA");
			
			// Construction et ecouteur du bouton "GO!"
			SelectionSubPanel(){
				
				
				setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // organisation verticale
				add(xLabel); //label X
				add(coordx); //TextField pour le champ coordonnée x
				add(yLabel); //label Y
				add(coordy); //TextField pour le champ coordonnée y
				add(new JLabel("NOM LIEU")); //label NOM LIEU
				add(lieu); // champ pour que l'utilisateur rentre direcement le nom d'un lieu
				
				add(setdep); //Bouton pour valider le point de départ
				add(lieuDep); //affiche dans ce champ le nom du point de départ validé
			
				add(setarr); //Bouton pour valider le point d'arrivée
				add(lieuArr); //affiche dans ce champ le nom du point d'arrivée validé
				
				add(tpsreel); //Bouton radio pour avoir le temps réel ou non
				
				
				add(go); //Bouton pour lancer la recherche du chemin
				add(effacer); //Bouton pour effacer tous les champs
				
				//On initialise tous les champs à la chaine vide
				lieu.setText("");
				lieuDep.setText("");
				lieuArr.setText("");
				coordx.setText("");
				coordy.setText("");
				
				//lorsque l'on clique sur le bouton EFFACER DATA, tous les champs sont réinitialiser
				effacer.addActionListener(evt->{
					lieu.setText("");
					lieuDep.setText("");
					lieuArr.setText("");
					coordx.setText("");
					coordy.setText("");
					resultTextArea.setText("");
					dep=null;
					arr=null;
				});
				
				
				//lorsque l'on clique sur le bouton SET DEPART, on recherche le point depart dans la liste de point de la station
				//on affiche le nom du lieu dans le champ lieuDep si celui-ci existe bien
				//sinon on affiche un message dans la zone résultat
				setdep.addActionListener(evt -> {
					try {
						if(lieu.getText().equals("")) {
							dep = st.rechercheNom(x,y);
							lieuDep.setText(""+dep.getNom());
						}else {
							dep = st.recherchePoint(lieu.getText());
	
							lieuDep.setText(""+dep.getNom());
						}
						lieu.setText("");
					}catch(NullPointerException e) {
						resultTextArea.setText("Désolé, ce lieu n'existe pas ici");
					}
				});
				
				
				
				//lorsque l'on clique sur le bouton SET ARRIVEE, on recherche le point depart dans la liste de point de la station
				//on affiche le nom du lieu dans le champ lieuArr si celui-ci existe bien
				//sinon on affiche un message dans la zone résultat
				setarr.addActionListener(evt -> {
					try {
						if(lieu.getText().equals("")) {
							arr = st.rechercheNom(x,y);
							lieuArr.setText(""+arr.getNom());
						}else {
							arr = st.recherchePoint(lieu.getText());
	
							lieuArr.setText(""+arr.getNom());
						}
						lieu.setText("");
					}catch(NullPointerException e) {
						resultTextArea.setText("Désolé, ce lieu n'existe pas ici");
					}
				});
				
				//Lorsque l'utilisateur clique sur le bouton GO pour lancer la recherche
				//on regarde si il souhaite le temps Reel ou non
				//puis on lance la recherche et on affiche le résultat
				go.addActionListener(evt -> {
					try {
						if(tpsreel.isSelected())
							tempsReel = true;
						else
							tempsReel = false;
						
						st.dijkstra(dep, arr,tempsReel);
						
						resultTextArea.setText(afficherChemin(dep.getId(), arr.getId()));
					}catch(NullPointerException e) {
						resultTextArea.setText("Désolé mais vous n'avez pas validé un lieu de départ et/ou d'arrivée");
					}
				});
			}

			/**
			 * 
			 * Reactions au clic utilisateur sur la grille
			 * - methode "void mouseReleased(MouseEvent e)" : selection de coordonnees
			 * - autres méthodes sans effet
			 * 
			 */
			public void mouseReleased(MouseEvent e) {
				x = e.getX() / DELTA;
				y = e.getY() / DELTA;
				coordx.setText(""+x);
				coordy.setText(""+y);

			}

			/**
			 * NOP
			 */
			public void mousePressed(MouseEvent e) {
			}

			/**
			 * NOP
			 */
			public void mouseClicked(MouseEvent e) {
			}

			/**
			 * NOP
			 */
			public void mouseEntered(MouseEvent e) {
			}

			/**
			 * NOP
			 */
			public void mouseExited(MouseEvent e) {
			}
		}

		SelectionSubPanel getSelectionPanel() {
			return selectionPanel;
		}

	}

	/**
	 * 
	 * Usage : java ihm.EasySkiProto <plan jpg>
	 * 
	 * @param argv[0]
	 *            plan jpg
	 * @throws java.io.IOException
	 *             Erreur d'acces au fichier
	 */
	public static void main(String[] argv) throws java.io.IOException,Exception {
		
		st = StationParser.parse(argv);
			

		if (argv.length != 1)
			System.err.println("usage : java ihm.EasySki <plan jpg>");
		else {
			EasySki window = new EasySki(img);
			window.setTitle("EasySki");
			window.setSize(1920, 1080);
			window.setVisible(true);
		}
	}
	
	/**
	 * Méthode pour afficher le chemin
	 * @param idPointDepart
	 * @param idPointArrivee
	 * @return
	 */
	public static String afficherChemin(int idPointDepart, int idPointArrivee) {
		int idPCour;
		String aff="";
		aff+="\nFastest path between: "+st.getPoints().get(idPointDepart).getNom()+" "+st.getPoints().get(idPointDepart).toString()+" and "+st.getPoints().get(idPointArrivee).getNom()+" "+st.getPoints().get(idPointArrivee).toString()+" :";
		idPCour = idPointArrivee;
		List<Transition> transiti = new ArrayList<Transition>();
		Transition transiCour = st.getPere().get(idPCour);
		int idPointDepartTransition;
		
		try {
			idPointDepartTransition = transiCour.getPointDep().getId();
		}catch(NullPointerException e) {
			return "\n\n\tPas de chemin possible, merci de votre compréhension. La direction";
		}
		
		
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
			
			//on ajoute la transition courante dans la liste de transiti pour pouvoir l'afficher plus tard
			transiti.add(transiCour);
			//On additionne le cumul des dénivelés
			cumul_dif_alt += transiCour.getPointArr().Deniv(transiCour.getPointDep());
		}
		//on a dans transiti toues les transitions, mais l'ordre inverse
		//donc on inverse l'ordre des transition spour les afficher dans le bon ordre
		Collections.reverse(transiti);
		
		//On affiche désormais les transitions dans le bon ordre
		for(int i=0;i<transiti.size();i++)
			aff+="\n\t"+transiti.get(i);
		
		
		int tpsTotal = (int)Math.floor(st.getPi().get(idPointArrivee));
		int nbHeures = (int) Math.floor(tpsTotal/3600);
		int nbMin = (int) Math.floor((tpsTotal-nbHeures*3600)/60);
		int nbSec = (int) Math.floor(tpsTotal-nbHeures*3600-nbMin*60);
		aff+="\nTotal time : "+tpsTotal+" seconds"+" ("+nbHeures+"h "+nbMin+"mn "+nbSec+"s)";
		aff+="\nCumulative altitude difference: "+cumul_dif_alt+"m\n\n";
		
		return aff; //on retourne une chaine de caractère
	}
}