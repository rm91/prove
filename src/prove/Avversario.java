package prove;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class Avversario {

	public  float getStrongness(String squadra,int giornata) {
		float index=-1;
		try {
			
		File fXmlFile = new File("../DatiSquadre/Giornata_"+(giornata-1)+".xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile("//Row[@squadra=\""+squadra+"\"]");
		doc.getDocumentElement().normalize();
		NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		
		Element el = (Element) nl.item(0);	
		
		index = Integer.parseInt(el.getAttribute("pos")); 
		
		
	}
		catch (Exception e) {
			e.printStackTrace();
		    }
		
		if(index==1)
			return (1/index)*10;
		if(index>=2 || index<=3)
			return (1/index)*9;
		if(index>=4 || index<=6)
			return (1/index)*8;
		if(index>=7 || index<=9)
			return (1/index)*6;
		if(index>=10 || index<=13)
			return (1/index)*4;
		if(index>=14 || index<=16)
			return (1/index)*3;
		else
			return (1/index)*1;
	}
	
	public Map<String, String> getAvversario(String squadra, int giornata) {
		
		String luogo, avversario="";
		Map<String,String> result=new HashMap<String,String>();
		try {
			
			File fXmlFile = new File("../Calendario/Calendario"+giornata+".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr1 = xpath.compile("//Partita[@casa=\""+squadra+"\"]");
			XPathExpression expr2 = xpath.compile("//Partita[@ospiti=\""+squadra+"\"]");

			NodeList nl = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);
			luogo ="casa";
			if(nl.getLength()==0) {
				 nl = (NodeList) expr2.evaluate(doc, XPathConstants.NODESET);
				 luogo = "ospiti";
				 }
			
			Element el = (Element) nl.item(0);
			
			if(luogo.equals("casa")) 
			{
				avversario = el.getAttribute("ospiti"); 
				result.put("luogo", String.valueOf(3));
			}
			else {
				avversario = el.getAttribute("casa");
				result.put("luogo", String.valueOf(1));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		    }
		
		result.put("avversario", avversario);
		return result;
	}

}
