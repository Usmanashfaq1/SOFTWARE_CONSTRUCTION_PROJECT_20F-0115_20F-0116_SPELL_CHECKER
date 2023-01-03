package businessLogicLayer;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.sql.Statement;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

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

import dataAccessLayer.*;

/**
 * 
 * @author maste
 *
 */
public class BusinessLogicLayer {
	/**
	 * 
	 * @param path
	 */
	private ArrayList<WordFrequency> wordArrayList = new ArrayList<WordFrequency>();
	private DataAccessLayer dataAccesslayerObject = new DataAccessLayer();
	private ArrayList<WordId> wordParentIdList = new ArrayList<WordId>();
	private ArrayList<String> wordListLocal = new ArrayList<String>();
	private int parentId = 0;
	private int wordId = 0;

	/**
	 * 
	 * @param path
	 */
	public void openDirectory(String path) {
		File directoryPath = new File(path);
		File filesList[] = directoryPath.listFiles();
		for (File file : filesList) {
			readXmlFile(file.getAbsolutePath().toString());
			System.out.println("Accomplished file : " + file.getName());
		}
		maintainTableIdWithFile();
		System.out.println("compeleted");
	}

	public ArrayList<TransferObject> getListOfWords() {
		return dataAccesslayerObject.getWordForViewingInTable();
	}

	public void getValueofUpdatedWord(TransferObject transferObject) {
		dataAccesslayerObject.manipulateWord(transferObject.getWordId(), transferObject.getWord());

	}

	public boolean checkWordExistsInDb(String word) {
		for (TransferObject transferObject : getListOfWords()) {
			if (word.equals(transferObject.getWord())) {
				return true;
			}
		}
		return false;

	}

	public void deleteWordIdForDb(TransferObject obj) {
		dataAccesslayerObject.manipulateWord(obj.getWordId());
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public boolean readXmlFile(String file) {

		DocumentBuilderFactory documnetBuilderFactory = DocumentBuilderFactory.newInstance();

		try {
			documnetBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder documentBuilder = documnetBuilderFactory.newDocumentBuilder();
			org.w3c.dom.Document document = documentBuilder.parse(new File(file));
			document.getDocumentElement().normalize();
			System.out.println("Rooot Element :" + document.getDocumentElement().getNodeName());
			System.out.println("***************");
			String title, author, paragraph;
			try {
				NodeList metaNodeList = document.getElementsByTagName("meta");
				Node node = metaNodeList.item(0);
				Element metaElement = (Element) node;
				title = metaElement.getElementsByTagName("title").item(0).getTextContent().toString();
				title = title.replaceAll("(?U)[\\W_]+", " ");
				NodeList authorNodeList = document.getElementsByTagName("author");

				Node authornode = authorNodeList.item(0);
				Element authorElement = (Element) authornode;

				author = authorElement.getElementsByTagName("name").item(0).getTextContent().toString();

				author = author.replaceAll("(?U)[\\W_]+", " ");

			} catch (NullPointerException e) {
				title = "نہیں معلوم";
				author = "نہیں جانتے";
				System.out.println("Unable to insert with out title and Author name");

			}

			NodeList sectionNodeList = document.getElementsByTagName("section");
			String entireParagraph = "";
			for (int i = 0; i < sectionNodeList.getLength(); i++) {

				try {
					Node sectionNode = sectionNodeList.item(i);
					
					if (sectionNode.getNodeType() == Node.ELEMENT_NODE) {

						Element sectionElement = (Element) sectionNode;
						int iterator = 0;
						System.out.println("Current Element : " + sectionNode.getNodeName());
						for (; true;) {
							paragraph = sectionElement.getElementsByTagName("p").item(iterator).getTextContent().toString();
							paragraph = paragraph.replaceAll("(?U)[\\W_]+", " ");
							entireParagraph = entireParagraph + paragraph + " ";
							iterator = iterator + 1;
						}

					}
				} catch (Exception e) {
					System.out.println("Exception Caught at getting Data from p tag of XML");
				}

			}

			dataAccesslayerObject.insertIntoDataBase(title, author, entireParagraph);
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
	public boolean checkFrequency(ArrayList<WordFrequency> wordArrayList, String word) {
		for (WordFrequency singleWord : wordArrayList) {
			if (singleWord.getWord().equalsIgnoreCase(word)) {
				singleWord.setFrequency(singleWord.getFrequency() + 1);
				return false;
			}
		}
		return true;
	}

	public void maintainTableIdWithFile() {
		String[] entireParagraphs = null;
		for (String s1 : dataAccesslayerObject.maintainWordIDWithFile()) {
			parentId++;
			ArrayList<String> oneFileAllWords = new ArrayList<String>();// valid for every iteration and renews after
																		// after iteration
			entireParagraphs = s1.split(" ");
			for (String singleWord : entireParagraphs) {
				WordId wordIdObject = new WordId();
				if (wordListLocal.contains(singleWord)) {
					if (!(oneFileAllWords.contains(singleWord))) {
						int g = 0;
						wordIdObject.setParentId(parentId);
						for (int k = 0; k < wordParentIdList.size(); k++) {
							WordId obj1 = wordParentIdList.get(k);
							if (obj1.getWord().equals(singleWord)) {
								g = obj1.getId();
							}
						}
						wordIdObject.setId(g);
						wordIdObject.setWord(singleWord);
						wordParentIdList.add(wordIdObject);
						oneFileAllWords.add(singleWord);
					}
				} else {
					wordId++;
					wordListLocal.add(singleWord);
					oneFileAllWords.add(singleWord);
					wordIdObject.setParentId(parentId);
					wordIdObject.setId(wordId);
					wordIdObject.setWord(singleWord);
					wordParentIdList.add(wordIdObject);

				}
				WordFrequency w = new WordFrequency(singleWord);
				if (checkFrequency(wordArrayList, singleWord) == true) {
					wordArrayList.add(w);
				}

			}

		}
		for (int i = 0; i < wordParentIdList.size(); i++) {
			dataAccesslayerObject.insertIntoDataBase(wordParentIdList.get(i).getParentId(), wordParentIdList.get(i).getId());
		}
		for (WordFrequency aa : wordArrayList) {
			dataAccesslayerObject.insertIntoDataBase(aa.getWord(), aa.getFrequency());
		}
	}

///////////////////////////////////////////
	// 20f-0115
	//////////////////
	public String word;
	// setting color here
	// default highlighter instance
	Highlighter.HighlightPainter highlightPainterObject = new MyHighlightPainter(Color.CYAN);// color
// A private subclass of the default highlight painter

	class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
		public MyHighlightPainter(Color color) {
			super(color);//
		}
	}
//

// function:
	//
	// Creates highlights around all occurrences of text in given comp //pattern
	public void highlight(JTextComponent area, String inputSentence) {

		try {
			Highlighter highlight = area.getHighlighter();
			Document textAreaDocument = area.getDocument(); // text area

			String text = textAreaDocument.getText(0, textAreaDocument.getLength());

			int position = 0;

			while ((position = text.indexOf(inputSentence, position)) >= 0) {
				//// highlighting here the pattern of given string
				highlight.addHighlight(position, position + inputSentence.length(), highlightPainterObject);
				position += inputSentence.length();

			}

		} catch (BadLocationException e) {

		}
	}

	// reading txt file
	public void readTextFile(List<String> wordFileList) {
		//boolean exist = false; // flag
		Scanner textFile = null;
		try {
			textFile = new Scanner(new File("word.txt")); // reading file here
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (textFile.hasNextLine())// here comparing with whole dictrionary
		{
			String compare = textFile.nextLine(); // first word first iteration
			// System.out.println("compare val is : "+ compare);
			wordFileList.add(compare);

			// String[] compare= compare1.split(" ");// new split logic

		}

//            for(String output:l1) 
//            {
//            	System.out.println(output); 
//            	}

	}

	// edit distance function
	public static int editDistanceValue(String word1, String word2) {

		// validation
		if ((word1 == null || word1.length() == 0) && (word2 == null || word2.length() == 0)) {
			return 0;
		}
		// string lengths
		int lengthWord1 = word1.length();
		int lengthWord2 = word2.length();

		// len1+1, len2+1,
		// crearting an 2d array(table) for value according to words sizes
		int[][] twoDimensionalArray = new int[lengthWord1 + 1][lengthWord2 + 1];

		// intitalizng with int values acc to sizes
		for (int i = 0; i <= lengthWord1; i++) {

			// arr[i][0] = i;

			for (int j = 0; j <= lengthWord2; j++) {
				if (j == 0) {
					twoDimensionalArray[i][0] = i;
				}

				if (i == 0) {
					twoDimensionalArray[0][j] = j;
				}
			}

		}

		int i = 0;
		while (i < lengthWord1) {
			char characterWord1 = word1.charAt(i);
			// System.out.println(c1);

			for (int j = 0; j < lengthWord2; j++) {
				// getting char at last place of array jth
				char characterWord2 = word2.charAt(j);
				// System.out.println(c2);
				// baseball zzesball

				// if two characters are equal
				if (characterWord1 == characterWord2) // no need to perform actions (same size words, no edit distance?)
				{
					// System.out.println("if executed!");
					// update dp value for +1 length
					twoDimensionalArray[i + 1][j + 1] = twoDimensionalArray[i][j]; // skip
				} else // operations forming edit distances!
				{
					// System.out.println("last is no same!");
					// replaement
					int replacmentValue = twoDimensionalArray[i][j] + 1;
					// insertion
					int insertionValue = twoDimensionalArray[i][j + 1] + 1; // droping j last and then inserting back in
					// deletion
					int deletionValue = twoDimensionalArray[i + 1][j] + 1;

					int editValue;
					if (replacmentValue > insertionValue) {
						editValue = insertionValue;
					} else {
						editValue = replacmentValue;
					}

					if (!(deletionValue > editValue)) {
						editValue = deletionValue;
//							min=min;
					}
//						else
//						{
//							
//						}

					twoDimensionalArray[i + 1][j + 1] = editValue;// returning here
					// System.out.println("min val is :: "+ min);

				}
			}
			i++;

		}

		return twoDimensionalArray[lengthWord1][lengthWord2]; // returning edit distance value here
	}

	public void accessDatabase() {

		//DataAccessLayer dataBaseLayerObject = new DataAccessLayer();
		// Connection abc=new Connection();
		/**
		 * Added inline function refactoring
		 */
		dataAccesslayerObject.textFileGeneration();

	}

	// getting wrong word from input word textfield
	public void setwordwrong(String word) {
		this.word = word;
	}

	public String getWordWrong() {
		return word;
	}
	/////////////////////////////////////////
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
class WordId {
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
