package model;

import java.util.HashMap;
import java.util.Map;
 
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement
public class Score
{ 
  @XmlJavaTypeAdapter(MapAdapter.class)
  public Map<String, Integer> standings;
 
  public Score()
  {
    standings = new HashMap<String, Integer>();
  }
 
 
public void addResult(String name, Integer score)   { standings.put(name, score); }

}
 
class MapAdapter extends XmlAdapter<MapElements[], Map<String, Integer>>
{
  public MapElements[] marshal(Map<String, Integer> arg0) throws Exception
  {
    MapElements[] mapElements = new MapElements[arg0.size()];
 
    Integer i = 0;
    for (Map.Entry<String, Integer> entry : arg0.entrySet())
      mapElements[i++] = new MapElements(entry.getKey(), entry.getValue());
 
    return mapElements;
  }
 
  public Map<String, Integer> unmarshal(MapElements[] arg0) throws Exception
  {
    Map<String,Integer> r = new HashMap<String,Integer>();
    for(MapElements mapelement : arg0)
      r.put(mapelement.key, mapelement.value);
    return r;
  }
}
 
class MapElements
{
  @XmlElement public String  key;
  @XmlElement public Integer value;
 
  private MapElements() {} //Required by JAXB
 
  public MapElements(String key, Integer value)
  {
    this.key   = key;
    this.value = value;
  }
}