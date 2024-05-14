package parseur;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * Exemple de {@code ContentHandler SAX}.
 * <br><br>
 * Ne fait que scruter et afficher les points d'une station en XML.
 * 
 * Voir le programme de test {@code PointParser}.
 * 
 * @author Bernard.Carre -at- polytech-lille.fr
 */
public class PointHandler implements ContentHandler {
  String typeCourant; // Type de l'element en cours de reconnaissance

  public void startDocument() throws SAXException {
    System.out.println("start document...");
  }

  public void endDocument() throws SAXException {
    System.out.println("\nDocument termine.");
  }

  // Balise ouvrante d'element : name dans localName
  public void startElement(String namespaceURI, String localName, String rawName, Attributes atts) throws SAXException {
    if (localName.equals("point"))
      	System.out.println("\nNouveau point... ");
    else // Autres elements eventuels...
        System.out.println("startElement: "+localName);
  typeCourant=localName;
  }

  // Balise fermante : name dans localName
  public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
    if (localName.equals("point"))
    	System.out.println("endElement: "+localName+"\n");
   else // Autres elements eventuels...
        System.out.println("endElement: "+localName);
  typeCourant=null;
  }

  // Contenu de l'element courant...
  public void characters(char[] ch, int start, int length) throws SAXException {
    if (typeCourant != null) {
      if (typeCourant.equals("docbase") || typeCourant.equals("point"))  {
	//pas de contenu particulier
	System.out.println("contenu:");
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
