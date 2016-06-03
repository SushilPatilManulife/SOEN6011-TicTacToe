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

 
 /**
  * this class handles the I/O for the xml file containing the player name and score
  * 
  */
public class XmlIO
{
	private static final String fileName = "score.xml";
	/**
	 * this method adds value t the xml file
	 * @param o object to be added
	 * @param writer FileWriter used for adding the value
	 * @throws Exception
	 */
  private static void writeAsXml(Object o, Writer writer) throws Exception
  {
    JAXBContext jaxb = JAXBContext.newInstance(o.getClass());
 
    Marshaller xmlConverter = jaxb.createMarshaller();
    xmlConverter.setProperty("jaxb.formatted.output", true);
    xmlConverter.marshal(o, writer);
  }
 /**
  * this method reads from the xml file
  * @param reader File Reader for accessing xml file
  * @param c class of the object to be read
  * @return xml data in form of the object class
  * @throws Exception
  */
  private static <T> T readObjectAsXmlFrom(Reader reader, Class<T> c) throws Exception
  {
    JAXBContext jaxb = JAXBContext.newInstance(c);
 
    XMLStreamReader xmlReader =
      XMLInputFactory.newInstance().createXMLStreamReader(reader);
 
    Unmarshaller xmlInterpreter = jaxb.createUnmarshaller();
 
    return xmlInterpreter.unmarshal(xmlReader, c).getValue();
  }
  /**
   * this method is used to insert any score or player
   * @param name is the name of the player
   * @param score is the score of the player
   * @throws Exception
   */
  public static void addScore(String name, int score) throws Exception{
	  File xmlFile = new File(fileName);
	  Score root = new Score();
    root = readObjectAsXmlFrom(new FileReader(xmlFile.getAbsolutePath()), root.getClass());
    root.addResult(name,score);
    writeAsXml(root, new FileWriter(xmlFile));
  }
  /**
   * this method increments wining record if player exist or create new entry if not exist 
   * @param name is the player name
   * @throws Exception
   */
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
  /**
   * this method selects and returns the top ten players on file
   * @return Array of top 10 playe name and score
   * @throws Exception
   */
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
  /**
   * this method is used for sorting a map
   * @param map the unsorted map
   * @return the sorted map
   */
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


}