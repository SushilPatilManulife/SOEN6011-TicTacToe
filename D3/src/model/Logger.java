package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.SwingUtilities;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;

import ui.Cards;

import org.w3c.dom.*;

public class Logger {
	
	private String xml = "score.xml";
	
	Map<String, List<String>> map = new TreeMap<String, List<String>>();
	
	private String role1 = "10";
	private String role2 = "20";
	private String role3 = "30";
	private String role4 = "40";
	private String role5 = "50";
	private ArrayList<Integer> rolev;

	public Logger() {
		
	}
	
	public String getStringFromDocument(Document doc)
	{
	    try
	    {
	       DOMSource domSource = new DOMSource(doc);
	       StringWriter writer = new StringWriter();
	       StreamResult result = new StreamResult(writer);
	       TransformerFactory tf = TransformerFactory.newInstance();
	       Transformer transformer = tf.newTransformer();
	       transformer.transform(domSource, result);
	       return writer.toString();
	    }
	    catch(TransformerException ex)
	    {
	       ex.printStackTrace();
	       return null;
	    }
	} 
	
	public void saveToXML() {
	    Document dom;
	    Element e = null;

	    // instance of a DocumentBuilderFactory
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    try {
	        // use factory to get an instance of document builder
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        // create instance of DOM
	        dom = db.newDocument();

	        // create the root element
	        Element rootEle = dom.createElement("roles");

	        // create data elements and place them under root
	        e = dom.createElement("role1");
	        e.appendChild(dom.createTextNode(rolev.get(0).toString()));
	        rootEle.appendChild(e);

	        e = dom.createElement("role2");
	        e.appendChild(dom.createTextNode(rolev.get(1).toString()));
	        rootEle.appendChild(e);

	        e = dom.createElement("role3");
	        e.appendChild(dom.createTextNode(rolev.get(2).toString()));
	        rootEle.appendChild(e);

	        e = dom.createElement("role4");
	        e.appendChild(dom.createTextNode(rolev.get(3).toString()));
	        rootEle.appendChild(e);
	        
	        e = dom.createElement("role5");
	        e.appendChild(dom.createTextNode(rolev.get(4).toString()));
	        rootEle.appendChild(e);

	        dom.appendChild(rootEle);
	        
	        System.out.println(getStringFromDocument(dom));
	        
	        
	        
	        try {
	        	
	            Transformer tr = TransformerFactory.newInstance().newTransformer();
	            tr.setOutputProperty(OutputKeys.INDENT, "yes");
	            tr.setOutputProperty(OutputKeys.METHOD, "xml");
	            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "score.dtd");
	            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	            // send DOM to file
	            FileOutputStream fl = new FileOutputStream(xml);
	            DOMSource dsr = new DOMSource(dom);
	            StreamResult sr = new StreamResult(fl);
	            
	            tr.transform(dsr, sr);

	        } catch (TransformerException te) {
	            System.out.println(te.getMessage());
	        } catch (IOException ioe) {
	            System.out.println(ioe.getMessage());
	        }
	    } catch (ParserConfigurationException pce) {
	        System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
	    }
	}
	
	public boolean readXML() {
        rolev = new ArrayList<Integer>();
        Document dom;
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the    
            // XML file
            dom = db.parse(xml);

            Element doc = dom.getDocumentElement();

            role1 = getTextValue(role1, doc, "role1");
            if (role1 != null) {
                if (!role1.isEmpty())
                    rolev.add(Integer.parseInt(role1));
            }
            role2 = getTextValue(role2, doc, "role2");
            if (role2 != null) {
                if (!role2.isEmpty())
                    rolev.add(Integer.parseInt(role2));
            }
            role3 = getTextValue(role3, doc, "role3");
            if (role3 != null) {
                if (!role3.isEmpty())
                    rolev.add(Integer.parseInt(role3));
            }
            role4 = getTextValue(role4, doc, "role4");
            if ( role4 != null) {
                if (!role4.isEmpty())
                    rolev.add(Integer.parseInt(role4));
            }
            role5 = getTextValue(role4, doc, "role5");
            if ( role5 != null) {
                if (!role5.isEmpty())
                    rolev.add(Integer.parseInt(role5));
            }
            System.out.print(rolev);
            return true;

        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        return false;
    }

	private String getTextValue(String def, Element doc, String tag) {
	    String value = def;
	    NodeList nl;
	    nl = doc.getElementsByTagName(tag);
	    if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
	        value = nl.item(0).getFirstChild().getNodeValue();
	    }
	    return value;
	}
	
	public boolean saveGame(String playerName, int score) {
		readXML();
		insert(score);
	    saveToXML();
	    System.out.print(rolev);
		return false;
	}
	
	private void insert(int x){
	    for (int i = 0; i < rolev.size(); i++) {
	        if (rolev.get(i) < x) continue;
	        if (rolev.get(i) == x) return;
	        rolev.add(i, x);
	        return;
	    }
	    rolev.add(x);
	}
	
//	public static void main(String[] args) {
//		Logger log = new Logger();
//		log.saveGame("new", 15);
//	}
}
