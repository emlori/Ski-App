package parseur;


import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;

/**
 * Programme de test de {@code PointParser}.
 * 
 * Usage : java parseur.ImageParser map/station.xml}
 * 
 * @author Bernard.Carre -at- polytech-lille.fr
 */

public class PointParser {

	@SuppressWarnings("deprecation")
	public static void main(String argv[]) throws Exception {
		if (argv.length != 1)
			System.err.println("usage : java parseur.PointParser map/station.xml");
		else {
			System.out.println("analyse de " + argv[0] + "...");

			// Le parseur SAX
			XMLReader reader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");

			// Creation d'un flot XML sur le fichier d'entree
			InputSource input = new InputSource(new FileInputStream(argv[0]));

			// Connexion du ContentHandler
			reader.setContentHandler(new PointHandler());
			// Lancement du traitement...
			reader.parse(input);
		}
	}
}

