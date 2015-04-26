package prove;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CalcoloForma {

	private int giornata;
	public CalcoloForma(int giornata) {
		this.giornata=giornata;
	}

	public float getForma(int id){
		float fantamedia=0;
		int g=3;
		
		try {
			if(giornata < 3) {
				return 0;
			}
			else {
				for(int i=giornata;i>giornata-3;i--) {
					File fXmlFile = new File("../Voti/Voti_Fantagazzetta_(NAPOLI)_alla_"+String.valueOf(i-1)+"_Giornata.xml");
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(fXmlFile);
					XPathFactory xPathfactory = XPathFactory.newInstance();
					XPath xpath = xPathfactory.newXPath();
					XPathExpression expr = xpath.compile("//Row[@A=\""+String.valueOf(id)+"\"]");
					doc.getDocumentElement().normalize();
					NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
					
					if(nl.getLength()>0) {										//se trovo portiere che ha preso voto
						Element el = (Element) nl.item(0);	
						if(el.getAttribute("D").equals("6*")) {
							g--;
						}
						else 
						fantamedia += Float.parseFloat(el.getAttribute("D"));
					}
					else {
						g--;
					}
				}
			}
		}
		
		catch (Exception e) {
		e.printStackTrace();
				    }
			    
		return fantamedia/g;
	
		  }
	
}
