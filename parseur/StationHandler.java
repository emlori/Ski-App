package parseur;
import solveur.*;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;


public class StationHandler implements ContentHandler {
  String typeCourant; // Type de l'element en cours de reconnaissance

  static Station mystat = new Station(); //une station vide, on va la remplir
  
  static Point pt = new Point();  //un point vide, pour stocker chaque point que l'on va rencontrer et l'ajouter à la station
  static Descente piste = new Descente(); //une Descente vide pour stocker chaque descente que l'on va rencontrer et l'ajouter au point associé à son point de départ dans la station
  static Remontee remontee = new Remontee();//une Remontée vide, pour stocker chaque remontee que l'on va rencontrer et l'ajouter au point associé à son point de départ dans la station
  static Navette navette = new Navette();//une Navette vide, pour stocker chaque navette que l'on va rencontrer et l'ajouter au point associé à son point de départ dans la station
  
  static int numero = -1; //besoin de stocker le numero (id) du point lu pour pouvoir ensuite l'ajouter à la station quand 
  static int xtmp=-1;
  
  //variable globale booléenne pour savoir si c'est un point ou une transition
  // 1 pour un point et 0 sinon
  static boolean isPoint;
  
  static int whichTransi; //Besoin de retenir si on a lu une piste (1), une remontee (2) ou 3 une navette pour remplir la bonne sous-classe de transition
  
  static int idPtDepTransi = -1; //Besoin de stocker l'id du point de départ de la transition pour ajouter la transition au point dont la transition démarre
  
  public void startDocument() throws SAXException {
    System.out.println("start document...");
  }

  public void endDocument() throws SAXException {
    System.out.println("\nDocument termine.");
  }

  // Balise ouvrante d'element : name dans localName
  public void startElement(String namespaceURI, String localName, String rawName, Attributes atts) throws SAXException {
    if (localName.equals("point")) {
    	isPoint = true;
      	//System.out.println("\nNouveau point... ");
    }
    else if (localName.equals("piste")) {
    	//System.out.println("\nNouvelle transition...");
    	whichTransi = 1;
    }else if(localName.equals("remontee")) {
    	//System.out.println("\nNouvelle transition...");
    	whichTransi = 2;
    }
    else if(localName.equals("navette")) {
    	//System.out.println("\nNouvelle transition...");
    	whichTransi = 3;
    }
    else // Autres elements eventuels...
        //System.out.println("startElement: "+localName);
  typeCourant=localName;
  }

  // Balise fermante : name dans localName
  public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
    if (localName.equals("point")) {
    	//System.out.println("endElement: "+localName+"\n");
    	mystat.setPoints(numero, pt);
    	pt = new Point();
    	numero = -1;
    	isPoint = false;
    }
    else if (localName.equals("piste")) {
    	//System.out.println("endElement");
    	mystat.addTransi(idPtDepTransi, piste);
    	piste = new Descente();
    	whichTransi = 0;
    	idPtDepTransi = -1;
    }
    else if (localName.equals("remontee")) {
    	//System.out.println("endElement");
    	mystat.addTransi(idPtDepTransi, remontee);
    	remontee = new Remontee();
    	whichTransi = 0;
    	idPtDepTransi = -1;
    }
    else if (localName.equals("navette")) {
    	//System.out.println("endElement");
    	mystat.addTransi(idPtDepTransi, (navette));
    	navette = new Navette();
    	whichTransi = 0;
    	idPtDepTransi = -1;
    }
   else // Autres elements eventuels...
        //System.out.println("endElement: "+localName);
  typeCourant=null;
  }

  
  // Contenu de l'element courant...
  public void characters(char[] ch, int start, int length) throws SAXException {
    if (typeCourant != null) {
      if (typeCourant.equals("docbase") || typeCourant.equals("point") || typeCourant.equals("piste") || typeCourant.equals("remontee")||typeCourant.equals("navette"))  {
	//pas de contenu particulier
    	  
    	  //System.out.println("contenu:");
      }
      else if(isPoint == true) { //si c'est un point
		  String contenu = new String (ch,start,length);
		  //selon le type courant, on remplit le bon champ
    	  switch(typeCourant) {
	    	  case "numero":
		  	      int num = Integer.parseInt(contenu);
		  	      numero = num;
		  	      pt.setId(num);
		  	      //System.out.println("num : "+num);
		  	      break;
	    	  case "nom":
	    	      String nom = contenu;
	    	      pt.setNom(nom);
	    	      //System.out.println("nom : "+nom);
	    	      break;
	    	  case "altitude":
	    		  double altitude = Double.parseDouble(contenu);
	    		  pt.setAltitude(altitude);
	    		  break;
	    		  
	    	  case "x":
	    		  int x = Integer.parseInt(contenu);
	    		  xtmp = x;
	    		  break;
	    	  case "y":
	    		  int y = Integer.parseInt(contenu);
	    		  pt = new PointCoord(pt, xtmp, y);
	    		  break;
	    	  default:
	    		  break;
	    		  
    	  }
    	 
      }
      else if(whichTransi == 1) { //si c'est une piste (descente)
		  String contenu = new String (ch,start,length);
		  //selon le typecourant lu, on remplit le bon champ
    	  switch(typeCourant) {
	    	  case "numero":
		  	      int num = Integer.parseInt(contenu);
		  	      numero = num;
		  	      piste.setId(num);
		  	      //System.out.println("num : "+num);
		  	      break;
	    	  case "nom":
	    	      String nom = contenu;
	    	      piste.setNom(nom);
	    	      //System.out.println("nom : "+nom);
	    	      break;
	    	  case "type":
	    	      String type = contenu;
	    	      piste.setType(type);
	    	      //System.out.println("type : "+type);
	    	      break;
	    	  case "depart":
	    	      int idptde = Integer.parseInt(contenu);
	    	      piste.setPointDep(mystat.getPoint(idptde));
	    	      idPtDepTransi = idptde; //on retient l'id du point de depart de la transition pour pouvoir associée la transition au point duquel elle part
	    	      //System.out.println("pt de dep : "+idptde);
	    	      break;
	    	  case "arrivee":
	    		  int idptar = Integer.parseInt(contenu);
	    	      piste.setPointArr(mystat.getPoint(idptar));
	    	      //System.out.println("pt ar : "+idptar);
	    	      break;
	    	  case "tpsDenivele":
	    		  double tpsdeniv = Double.parseDouble(contenu);
	    		  piste.setTps_100m(tpsdeniv);
	    		  //System.out.println("tps deniv : "+tpsdeniv);
	    		  break;
	    	   default: 
	    		   break;
    	  }
      }
      else if(whichTransi == 2) { //si c'est une remontee
		  String contenu = new String (ch,start,length);
		  //selon le type courant lu, on remplit le bon champ de remontee
    	  switch(typeCourant) {
	    	  case "numero":
		  	      int num = Integer.parseInt(contenu);
		  	      numero = num;
		  	      remontee.setId(num);
		  	      //System.out.println("num : "+num);
		  	      break;
	    	  case "nom":
	    	      String nom = contenu;
	    	      remontee.setNom(nom);
	    	      //System.out.println("nom : "+nom);
	    	      break;
	    	  case "type":
	    	      String type = contenu;
	    	      remontee.setType(type);
	    	      //System.out.println("type : "+type);
	    	      break;
	    	  case "depart":
	    	      int idptde = Integer.parseInt(contenu);
	    	      remontee.setPointDep(mystat.getPoint(idptde));
	    	      idPtDepTransi = idptde; //on retient l'id du point de depart de la transition pour pouvoir associée la transition au point duquel elle part
	    	      //System.out.println("pt de dep : "+idptde);
	    	      break;
	    	  case "arrivee":
	    		  int idptar = Integer.parseInt(contenu);
	    	      remontee.setPointArr(mystat.getPoint(idptar));
	    	      //System.out.println("pt ar : "+idptar);
	    	      break;
	    	  case "tpsFixe":
	    		  double tpsfixe = Double.parseDouble(contenu);
	    		  remontee.setDuree_fixe(tpsfixe);
	    		  break;
	    	  case "tpsDenivele":
	    		  double tpsdeniv = Double.parseDouble(contenu);
	    		  remontee.setTps_100m(tpsdeniv);
	    		  break;
	    	  default:
	    		  break;
    	  }
      }
      else if(whichTransi == 3) { //si c'est une navette
		  String contenu = new String (ch,start,length);
		  //selon le type courant lu, on remplit le bon champ
    	  switch(typeCourant) {
	    	  case "numero":
		  	      int num = Integer.parseInt(contenu);
		  	      numero = num;
		  	      navette.setId(num);
		  	      //System.out.println("num : "+num);
		  	      break;
	    	  case "nom":
	    	      String nom = contenu;
	    	      navette.setNom(nom);
	    	      //System.out.println("nom : "+nom);
	    	      break;
	    	  case "type":
	    	      String type = contenu;
	    	      navette.setType(type);
	    	      //System.out.println("type : "+type);
	    	      break;
	    	  case "depart":
	    	      int idptde = Integer.parseInt(contenu);
	    	      navette.setPointDep(mystat.getPoint(idptde));
	    	      idPtDepTransi = idptde; //on retient l'id du point de depart de la transition pour pouvoir associée la transition au point duquel elle part
	    	      //System.out.println("pt de dep : "+idptde);
	    	      break;
	    	  case "arrivee":
	    		  int idptar = Integer.parseInt(contenu);
	    	      navette.setPointArr(mystat.getPoint(idptar));
	    	      //System.out.println("pt ar : "+idptar);
	    	      break;
	    	  case "tpsTrajet":
	    		  double dureeTraj = Double.parseDouble(contenu);
	    		  navette.setDuree_traj(dureeTraj);
	    		  break;
	    	  default:
	    		  break;
    	  }
    	  
      }
      else { // Sinon element de description de point
		String contenu = new String (ch,start,length);
		//System.out.println("contenu: "+contenu);
      }
    }
  }

  
  // Sur le reste : NOP
  public void startPrefixMapping(String prefix, String uri) {}
  public void endPrefixMapping(String prefix) {}
  public void ignorableWhitespace(char [] ch, int start, int length) throws SAXException {}
  public void processingInstruction(String target, String data) throws SAXException {}
  public void skippedEntity(String name) throws SAXException {}
  public void setDocumentLocator(Locator locator) {}
}
