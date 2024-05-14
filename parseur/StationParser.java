package parseur;


import org.xml.sax.*;
import org.xml.sax.helpers.*;

import solveur.Station;

import java.io.*;



public class StationParser {

	@SuppressWarnings("deprecation")
	public static Station parse(String[] argv) throws Exception {
		if (argv.length != 1)
			System.err.println("usage : java parseur.PointParser map/station.xml");
		else {
			System.out.println("analyse de " + argv[0] + "...");

			// Le parseur SAX
			XMLReader reader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");

			// Creation d'un flot XML sur le fichier d'entree
			InputSource input = new InputSource(new FileInputStream(argv[0]));

			// Connexion du ContentHandler
			reader.setContentHandler(new StationHandler());
			// Lancement du traitement...
			reader.parse(input);
		}
		return StationHandler.mystat; //on retourne la station construite
	}
}

