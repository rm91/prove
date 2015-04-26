package prove;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DatabaseRosa {
	//Su Android sarebbe meglio cambiare il path
final String path="RosaGiocatore\\Rosa.xml";

public DatabaseRosa() {
	
	//Controlo se il file Xml Esiste
	File f = new File(path);
	//se non esiste, lo creò
	if(!(f.exists())) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Rosa");
			doc.appendChild(rootElement);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));
			transformer.transform(source, result);
			 
			System.out.println("File saved!");
		      //Documento 
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public void insertGiocatore(int id,String nome,String ruolo,String squadra) {
	
	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	try {
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(path);	
		Node last =  (Node) doc.getElementsByTagName("Giocatore").item(0);
		if(last==null)
			last = doc.getElementsByTagName("Rosa").item(0);

		Element g = doc.createElement("Giocatore");
		g.setAttribute("id", String.valueOf(id));
		g.setAttribute("nome", nome);
		g.setAttribute("ruolo", ruolo);
		g.setAttribute("squadra", squadra);
		last.appendChild(g);
		
		TransformerFactory transFactory = TransformerFactory.newInstance();
	    Transformer transformer = transFactory.newTransformer();
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path));
		transformer.transform(source, result);
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (TransformerConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (TransformerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public ArrayList<GiocatoreRosa> getGiocatore(String ruolo)
{
	
	ArrayList<GiocatoreRosa> g = new ArrayList<GiocatoreRosa>();
	//Per sicurezza,Tronchiamo e portiamo tutto a maiuscolo
	ruolo.toUpperCase();
	ruolo=String.valueOf(ruolo.charAt(0));
	

	try {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(path);
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile("//Giocatore[@ruolo=\""+ruolo+"\"]");
		doc.getDocumentElement().normalize();
		NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for(int i=0;i<nl.getLength();i++)
		{
			Element el = (Element) nl.item(i);
			int id=Integer.parseInt(el.getAttribute("id"));
			String nome=el.getAttribute("nome");
			String squadra=el.getAttribute("squadra");
			g.add(new GiocatoreRosa(id,nome,ruolo,squadra));
		}
	} catch (SAXException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (XPathExpressionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return g;
}
}
