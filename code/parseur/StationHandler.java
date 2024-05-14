package parseur;
import solveur.*;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import java.util.ArrayList; //t
import java.util.List; //t


public class StationHandler implements ContentHandler {
  String typeCourant; // Type de l'element en cours de reconnaissance

  //une station
  static Station mystat = new Station();
  
  private List<Point> pointList = null; //t
  static Point pt = new Point(); //t
  static Descente piste = new Descente(); //t
  static Remontee remontee = new Remontee(); //t
  static Navette navette = new Navette(); //t
  
  static int numero = -1;
  
  //variable globale booléenne pour savoir si c'est un point ou une transition
  // 1 pour un point et 0 pour une transition
  boolean isPoint;
  int whichTransi; //0 si c'est une transition   1 si c'est une piste   2 si c'est une remontee et 3 si c'est une navette
  
  public void startDocument() throws SAXException {
    System.out.println("start document...");
  }

  public void endDocument() throws SAXException {
    System.out.println("\nDocument termine.");
  }

  // Balise ouvrante d'element : name dans localName
  public void startElement(String namespaceURI, String localName, String rawName, Attributes atts) throws SAXException {
    if (localName.equals("point")) {
      	System.out.println("\nNouveau point... ");
    	isPoint = true;
    }
    else if (localName.equals("piste") || localName.equals("remontee")||localName.equals("navette") ) {
    	System.out.println("\nNouvelle transition...");
    	isPoint = false;
    	whichTransi = 0;
    }
    else // Autres elements eventuels...
        System.out.println("startElement: "+localName);
  typeCourant=localName;
  }

  // Balise fermante : name dans localName
  public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
    if (localName.equals("point")) {
    	System.out.println("endElement: "+localName+"\n");
    	mystat.setPoints(numero, pt);
    	pt = new Point();
    	numero = -1;
    	isPoint = false;
    }
    else if (localName.equals("piste")) {
    	System.out.println("endElement");
    	mystat.addTransi(numero, piste);
    	Descente piste = new Descente();
    	numero = -1;
    	whichTransi = 1;
    }
    else if (localName.equals("remontee")) {
    	System.out.println("endElement");
    	mystat.addTransi(numero, remontee);
    	Remontee remontee = new Remontee();
    	numero = -1;
    	whichTransi = 2;
    }
    else if (localName.equals("navette")) {
    	System.out.println("endElement");
    	mystat.addTransi(numero, navette);
    	Navette navette = new Navette();
    	numero = -1;
    	whichTransi = 3;
    }
   else // Autres elements eventuels...
        System.out.println("endElement: "+localName);
  typeCourant=null;
  }

  
  // Contenu de l'element courant...
  public void characters(char[] ch, int start, int length) throws SAXException {
    if (typeCourant != null) {
      if (typeCourant.equals("docbase") || typeCourant.equals("point") || typeCourant.equals("piste") || typeCourant.equals("remontee")||typeCourant.equals("navette"))  {
	//pas de contenu particulier
    	  System.out.println("contenu:");
      }
      else if(isPoint == true) {
		  String contenu = new String (ch,start,length);

    	  switch(typeCourant) {
	    	  case "numero":
		  	      int num = Integer.parseInt(contenu);
		  	      numero = num;
		  	      pt.setId(num);
		  	      System.out.println("num : "+num);
		  	      break;
	    	  case "nom":
	    	      String nom = contenu;
	    	      pt.setNom(nom);
	    	      System.out.println("nom : "+nom);
	    	      break;
	    	  case "altitude":
	    		  double altitude = Double.parseDouble(contenu);
	    		  pt.setAltitude(altitude);
	    		  break;
	    		  
	    	  case "x":
	    		  int x = Integer.parseInt(contenu);
	    		  pt = new PointCoord(pt, x);
	    		  break;
	    	  case "y":
	    		  int y = Integer.parseInt(contenu);
	    		  pt = new PointCoord(pt, y);
	    		  break;
	    		  
    	  }
    	 
      }
      else if(whichTransi == 1) {
		  String contenu = new String (ch,start,length);

    	  switch(typeCourant) {
	    	  case "numero":
		  	      int num = Integer.parseInt(contenu);
		  	      numero = num;
		  	      piste.setId(num);
		  	      break;
	    	  case "nom":
	    	      String nom = contenu;
	    	      piste.setNom(nom);
	    	      break;
	    	  case "type":
	    	      String type = contenu;
	    	      piste.setType(type);
	    	      break;
	    	  case "depart":
	    	      int idptde = Integer.parseInt(contenu);
	    	      piste.setPointDep(mystat.getPoint(idptde));
	    	      break;
	    	  case "arrivee":
	    		  int idptar = Integer.parseInt(contenu);
	    	      piste.setPointArr(mystat.getPoint(idptar));
	    	      break;
    	  }
      }
      else { // Sinon element de description de point
		String contenu = new String (ch,start,length);
		System.out.println("contenu: "+contenu);
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
