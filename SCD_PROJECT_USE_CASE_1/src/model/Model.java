package model;

import dataBaseLayer.*;
import tranferObjects.TransferObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author maste
 *
 */
public class Model {
	/**
	 * 
	 * @param path
	 */
	private ArrayList<WordFrequency> wf = new ArrayList<WordFrequency>();
	private DataBaseLayer d = new DataBaseLayer();
	private ArrayList<Int> in = new ArrayList<Int>();
	private ArrayList<String> wordListLocal = new ArrayList<String>();
	private int parentId = 0;
	private int wordId = 0;

	/**
	 * 
	 * @param path
	 */
	public void openDirectory(String path) {

     	d.sqlConnection();
//		File directoryPath = new File(path);
//		File filesList[] = directoryPath.listFiles();
//		//int i = 0;
//		for (File file : filesList) {
//			readXmlFile(file.getAbsolutePath().toString());
//			System.out.println("Accomplished file : " + file.getName());
////			i++;
////			if (i == 5)
////				break;
//		}
//		maintainTableIdWithFile();
		//d.getWordForViewingInTable();// sending the the stored object of to the Transfer object to Controller
		// and then from controller send it to View
     	getListOfWords();
		System.out.println("compeleted");
	}
	public ArrayList<TransferObject> getListOfWords()
	{
		return d.getWordForViewingInTable();
	}


	/**
	 * 
	 * @param file
	 * @return
	 */
	public boolean readXmlFile(String file) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			org.w3c.dom.Document doc = db.parse(new File(file));
			doc.getDocumentElement().normalize();
			System.out.println("Rooot Element :" + doc.getDocumentElement().getNodeName());
			System.out.println("***************");
			String title, author, para;
			try {
				NodeList list1 = doc.getElementsByTagName("meta");
				Node node = list1.item(0);
				Element element1 = (Element) node;
				title = element1.getElementsByTagName("title").item(0).getTextContent().toString();
				title = title.replaceAll("(?U)[\\W_]+", " ");
				NodeList list2 = doc.getElementsByTagName("author");

				Node node2 = list2.item(0);
				Element element = (Element) node2;

				author = element.getElementsByTagName("name").item(0).getTextContent().toString();

				author = author.replaceAll("(?U)[\\W_]+", " ");

			} catch (NullPointerException e) {
				title = "نہیں معلوم";
				author = "نہیں جانتے";
				System.out.println("Unable to insert with out title and Author name");

			}

			NodeList list3 = doc.getElementsByTagName("section");
			String enP = "";
			for (int temp = 0; temp < list3.getLength(); temp++) {

				try {
					Node node3 = list3.item(temp);

					if (node3.getNodeType() == Node.ELEMENT_NODE) {

						Element element3 = (Element) node3;
						int iterator = 0;
						System.out.println("Current Element : " + node3.getNodeName());
						for (; true;) {
							para = element3.getElementsByTagName("p").item(iterator).getTextContent().toString();
							para = para.replaceAll("(?U)[\\W_]+", " ");
							enP = enP + para + " ";
							iterator = iterator + 1;
						}

					}
				} catch (Exception e) {
					System.out.println("Exception Caught at getting Data from p tag of XML");
				}

			}

			d.insertIntoDataBase(title, author, enP);
			return true;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("Eception caught at data accesss");
		}
		return false;
	}

	/**
	 * 
	 * @param a
	 * @param w
	 * @return
	 */
	public boolean checkFrequency(ArrayList<WordFrequency> a, String w) {
		for (WordFrequency aa : a) {
			if (aa.getWord().equalsIgnoreCase(w)) {
				aa.setFrequency(aa.getFrequency() + 1);
				return false;
			}
		}
		return true;
	}

	
	public void maintainTableIdWithFile() {
		String[] helper = null;
		for (String s1 : d.maintainWordIDWithFile()) {
			parentId++;
			ArrayList<String> oneFileAllWords = new ArrayList<String>();// valid for every iteration and renews after
																		// after iteration
			helper = s1.split(" ");
			for (String singleWord : helper) {
				Int obj = new Int();
				if (wordListLocal.contains(singleWord)) {
					if (!(oneFileAllWords.contains(singleWord))) {
						int g = 0;
						obj.setParentId(parentId);
						for (int k = 0; k < in.size(); k++) {
							Int obj1 = in.get(k);
							if (obj1.getWord().equals(singleWord)) {
								g = obj1.getId();
							}
						}
						obj.setId(g);
						obj.setWord(singleWord);
						in.add(obj);
						oneFileAllWords.add(singleWord);
					}
				} else {
					wordId++;
					wordListLocal.add(singleWord);
					oneFileAllWords.add(singleWord);
					obj.setParentId(parentId);
					obj.setId(wordId);
					obj.setWord(singleWord);
					in.add(obj);
					
				}
				WordFrequency w = new WordFrequency(singleWord);
				if(checkFrequency(wf , singleWord)== true)
				{
					wf.add(w);
				}

			}

		}
		for (int i = 0; i < in.size(); i++) {
			d.insertWordIdInTable(in.get(i).getParentId(), in.get(i).getId());
		}
		for (WordFrequency aa : wf) {
			d.insertWordInDataBase(aa.getWord(), aa.getFrequency());
		}
	}

}

class WordFrequency {
	private int frequency;

	/**
	 * 
	 * @param w
	 */
	WordFrequency(String w) {
		word = w;
		frequency = 1;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	private String word;

}

/**
 * 
 * @author maste
 *
 */
class Int {
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String word;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	private int parentId;

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	private int id;

}
