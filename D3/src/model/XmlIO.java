package model;

import java.io.File;
import java.util.*;
import java.util.Map.Entry;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

 
 
public class XmlIO
{
	private static final String fileName = "score.xml";
	
  private static void writeAsXml(Object o, Writer writer) throws Exception
  {
    JAXBContext jaxb = JAXBContext.newInstance(o.getClass());
 
    Marshaller xmlConverter = jaxb.createMarshaller();
    xmlConverter.setProperty("jaxb.formatted.output", true);
    xmlConverter.marshal(o, writer);
  }
 
  private static <T> T readObjectAsXmlFrom(Reader reader, Class<T> c) throws Exception
  {
    JAXBContext jaxb = JAXBContext.newInstance(c);
 
    XMLStreamReader xmlReader =
      XMLInputFactory.newInstance().createXMLStreamReader(reader);
 
    Unmarshaller xmlInterpreter = jaxb.createUnmarshaller();
 
    return xmlInterpreter.unmarshal(xmlReader, c).getValue();
  }
  // Allow to insert any score or player
  public static void addScore(String name, int score) throws Exception{
	  File xmlFile = new File(fileName);
	  Score root = new Score();
    root = readObjectAsXmlFrom(new FileReader(xmlFile.getAbsolutePath()), root.getClass());
    root.addResult(name,score);
    writeAsXml(root, new FileWriter(xmlFile));
  }
  // increment wining record if player exist or create new entry if not exist 
  public static void addWin(String name) throws Exception{
	  Integer newScore = 1;
	  Map<String, Integer> standings = new TreeMap<String, Integer>();
	  File xmlFile = new File(fileName);
	  Score root = new Score();
    root = readObjectAsXmlFrom(new FileReader(xmlFile.getAbsolutePath()), root.getClass());
    standings =  root.standings;
    if(standings.containsKey(name)) {
    	int current = standings.get(name);
    	newScore = current + 1;
    }
    root.addResult(name,newScore);
    writeAsXml(root, new FileWriter(xmlFile));
  }
  // top ten players on file
  public static ArrayList<String> getTopTen() throws Exception{
	
	int start, end;
	File xmlFile = new File(fileName);
	Score root = new Score();
	Map<String, Integer> standings = new TreeMap<String, Integer>();
    root = readObjectAsXmlFrom(new FileReader(xmlFile.getAbsolutePath()), root.getClass());
    standings = root.standings;
    Map<String, Integer> sortedStandings = sortByValue(standings);
    Set<Entry<String, Integer>> keys = sortedStandings.entrySet();
    Object[] sortedArr = keys.toArray();
    end = sortedArr.length;
    if(end<=10) start = 0;
    else start = end -10;
    ArrayList<String> result = new ArrayList<String>();
    for(int x = start; x<end;x++){
    	result.add(sortedArr[x].toString());
    }
	return result;
  }
  
  public static <K, V extends Comparable<? super V>> Map<K, V> 
  sortByValue( Map<K, V> map )
{
	  LinkedList<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>( map.entrySet() );
  Collections.sort( list, new Comparator<Map.Entry<K, V>>()
  {
      public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
      {
          return (o1.getValue()).compareTo( o2.getValue() );
      }
  } );

  Map<K, V> result = new LinkedHashMap<K, V>();
  for (Map.Entry<K, V> entry : list)
  {
      result.put( entry.getKey(), entry.getValue() );
  }
  return result;
}

// for testing and demo 
//  public static void main(String... args) throws Exception
//  {
////	  addScore("Mani", 350);
//	  addWin("Mani");
//	  System.out.println(getTopTen());
//  }
}