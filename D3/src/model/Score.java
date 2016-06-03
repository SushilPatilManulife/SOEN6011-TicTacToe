package model;

import java.util.HashMap;
import java.util.Map;
 
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * this class defines the standings element in XML document
 * @author shidokht
 *
 */
@XmlRootElement
public class Score
{ 
  @XmlJavaTypeAdapter(MapAdapter.class)
  public Map<String, Integer> standings;
 /**
  * constructor for Score class
  */
  public Score()
  {
    standings = new HashMap<String, Integer>();
  }
 
 /**
  * this method is used to add value to standings
  * @param name is the player name
  * @param score is the player score
  */
public void addResult(String name, Integer score)   { standings.put(name, score); }

}
 /**
  * this class maps java objects to xml elements and vice versa
  * @author shidokht
  *
  */
class MapAdapter extends XmlAdapter<MapElements[], Map<String, Integer>>
{
	/**
	 * this method maps java object to xml element
	 * @param arg0 is the java object
	 * @return created xml element
	 */
  public MapElements[] marshal(Map<String, Integer> arg0) throws Exception
  {
    MapElements[] mapElements = new MapElements[arg0.size()];
 
    Integer i = 0;
    for (Map.Entry<String, Integer> entry : arg0.entrySet())
      mapElements[i++] = new MapElements(entry.getKey(), entry.getValue());
 
    return mapElements;
  }
	/**
	 * this method maps xml element to java objects
	 * @param arg0 is the xml data
	 * @return created java object
	 */
  public Map<String, Integer> unmarshal(MapElements[] arg0) throws Exception
  {
    Map<String,Integer> r = new HashMap<String,Integer>();
    for(MapElements mapelement : arg0)
      r.put(mapelement.key, mapelement.value);
    return r;
  }
}
 /**
  * this class defines the local element in the XML Schema complex type
  * @author shidokht
  *
  */
class MapElements
{
  @XmlElement public String  key;
  @XmlElement public Integer value;
 
  private MapElements() {} //Required by JAXB
 /**
  * constructor method
  * @param key is the key for the HashMap entry
  * @param value is the value for the HashMap entry
  */
  public MapElements(String key, Integer value)
  {
    this.key   = key;
    this.value = value;
  }
}