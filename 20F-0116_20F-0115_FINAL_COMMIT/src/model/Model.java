package model;

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
		return d.getWordForViewingInTable();
	}

	public void getValueofUpdatedWord(TransferObject obj) {
		d.manipulateWord(obj.getWordId(), obj.getWord());

	}

	public boolean checkWordExistsInDb(String word) {
		for (TransferObject t : getListOfWords()) {
			if (word.equals(t.getWord())) {
				return true;
			}
		}
		return false;

	}

	public void deleteWordIdForDb(TransferObject obj) {
		d.manipulateWord(obj.getWordId());
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
				if (checkFrequency(wf, singleWord) == true) {
					wf.add(w);
				}

			}

		}
		for (int i = 0; i < in.size(); i++) {
			d.insertIntoDataBase(in.get(i).getParentId(), in.get(i).getId());
		}
		for (WordFrequency aa : wf) {
			d.insertIntoDataBase(aa.getWord(), aa.getFrequency());
		}
	}

///////////////////////////////////////////
	// 20f-0115
	//////////////////
	public String val3;

	// setting color here
	// default highlighter instance
	Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter(Color.CYAN);// color
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
	public void highlight(JTextComponent area, String input_string) {

		try {
			Highlighter highlight = area.getHighlighter();
			Document len = area.getDocument(); // text area

			String text = len.getText(0, len.getLength());

			int position = 0;

			while ((position = text.indexOf(input_string, position)) >= 0) {
				//// highlighting here the pattern of given string
				highlight.addHighlight(position, position + input_string.length(), myHighlightPainter);
				position += input_string.length();

			}

		} catch (BadLocationException e) {

		}
	}

	// reading txt file
	public void read_txt(List<String> l1) {
		boolean exist = false; // flag
		Scanner txtfile = null;
		try {
			txtfile = new Scanner(new File("word.txt")); // reading file here
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (txtfile.hasNextLine())// here comparing with whole dictrionary
		{
			String compare = txtfile.nextLine(); // first word first iteration
			// System.out.println("compare val is : "+ compare);
			l1.add(compare);

			// String[] compare= compare1.split(" ");// new split logic

		}

//            for(String output:l1) 
//            {
//            	System.out.println(output); 
//            	}

	}

	// edit distance function
	public static int edit_distance_val(String word1, String word2) {

		// validation
		if ((word1 == null || word1.length() == 0) && (word2 == null || word2.length() == 0)) {
			return 0;
		}
		// string lengths
		int row1 = word1.length();
		int col2 = word2.length();

		// len1+1, len2+1,
		// crearting an 2d array(table) for value according to words sizes
		int[][] arr = new int[row1 + 1][col2 + 1];

		// intitalizng with int values acc to sizes
		for (int i = 0; i <= row1; i++) {

			// arr[i][0] = i;

			for (int j = 0; j <= col2; j++) {
				if (j == 0) {
					arr[i][0] = i;
				}

				if (i == 0) {
					arr[0][j] = j;
				}
			}

		}

		int i = 0;
		while (i < row1) {
			char w1 = word1.charAt(i);
			// System.out.println(c1);

			for (int j = 0; j < col2; j++) {
				// getting char at last place of array jth
				char w2 = word2.charAt(j);
				// System.out.println(c2);
				// baseball zzesball

				// if two characters are equal
				if (w1 == w2) // no need to perform actions (same size words, no edit distance?)
				{
					// System.out.println("if executed!");
					// update dp value for +1 length
					arr[i + 1][j + 1] = arr[i][j]; // skip
				} else // operations forming edit distances!
				{
					// System.out.println("last is no same!");
					// replaement
					int replacment_val = arr[i][j] + 1;
					// insertion
					int insertion_val = arr[i][j + 1] + 1; // droping j last and then inserting back in
					// deletion
					int deletion_val = arr[i + 1][j] + 1;

					int edit_value;
					if (replacment_val > insertion_val) {
						edit_value = insertion_val;
					} else {
						edit_value = replacment_val;
					}

					if (!(deletion_val > edit_value)) {
						edit_value = deletion_val;
//							min=min;
					}
//						else
//						{
//							
//						}

					arr[i + 1][j + 1] = edit_value;// returning here
					// System.out.println("min val is :: "+ min);

				}
			}
			i++;

		}

		return arr[row1][col2]; // returning edit distance value here
	}

	public void database() {

		DataBaseLayer obj = new DataBaseLayer();
		// Connection abc=new Connection();

		obj.txtFileGeneration();

	}

	// getting wrong word from input word textfield
	public void setwordwrong(String a) {
		val3 = a;
	}

	public String getwordwrong() {
		return val3;
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
