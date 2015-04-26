package prove;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CalcolaGoalSquadra {
	
	private int giornata;
	
	public CalcolaGoalSquadra(int giornata) {
		this.giornata=giornata-1;
	}
	
	public Map<String,Integer> getData(String squadra) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		
		Map<String,Integer> goals = new HashMap<String,Integer>();
		File fXmlFile = new File("../DatiSquadre/Giornata_"+String.valueOf(giornata)+".xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile("//Row[@squadra=\""+squadra+"\"]");
		doc.getDocumentElement().normalize();
		NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		
		Element el = (Element) nl.item(0);	
		int gf = Integer.parseInt(el.getAttribute("gf"));
		int gs = Integer.parseInt(el.getAttribute("gs"));
		
		goals.put("goalFatti", gf);
		goals.put("goalSubiti", gs);
		
		return goals;
	}
	
}

	
