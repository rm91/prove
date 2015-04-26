package prove;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.io.File;

public class CalcoloImbattibilità {

	public int getValue(int id,int giornate){
		int count=0;
	    for(int i=1;i<giornate;i++) {
		try {
	 
		File fXmlFile = new File("../Voti/Voti_Fantagazzetta_(NAPOLI)_alla_"+String.valueOf(i)+"_Giornata.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile("//Row[@A=\""+String.valueOf(id)+"\"]");
		doc.getDocumentElement().normalize();
		NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		
		if(nl.getLength()>0)
		{										//se trovo portiere che ha preso voto
			Element el = (Element) nl.item(0);	
				if(Integer.parseInt(el.getAttribute("F"))==0)
						count++;
		}					
		
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    }
	    return count;

	  }
	 
	}

