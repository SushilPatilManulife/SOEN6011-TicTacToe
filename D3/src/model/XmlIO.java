package model;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlIO {

	private String fileName = "score.xml";
	
	public XmlIO() {
		
	}
	
	public Boolean saveXML(Score score) {
		try {
			File file = new File(fileName);
			JAXBContext jaxbContext = JAXBContext.newInstance(score.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(score, file);
			jaxbMarshaller.marshal(score, System.out);

		      } catch (JAXBException e) {
			e.printStackTrace();
		      }
		return true;
	}
	
	public Score readXML() {
		Score score = new Score();
		try {

			File file = new File(fileName);
			JAXBContext jaxbContext = JAXBContext.newInstance(Score.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			score = (Score) jaxbUnmarshaller.unmarshal(file);
			System.out.println(score);
			return score;
		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		return score;
	}
	
	public static void main(String[] args) {
		XmlIO log = new XmlIO();
//		Score score = new Score();
//		score.setId(100);
//		score.setAge(29);
//		score.setName("Bob");
//		log.saveXML(score);
		Score score =  new Score();
		score = log.readXML();
		System.out.println(score.getAge());
	}
}
