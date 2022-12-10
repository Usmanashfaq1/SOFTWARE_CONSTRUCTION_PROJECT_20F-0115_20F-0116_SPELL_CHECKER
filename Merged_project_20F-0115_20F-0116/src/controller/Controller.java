package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.*;
import tranferObjects.TransferObject;
import view.*;

/**
 * 
 * @author maste
 *
 */
public class Controller {
	private TransferObject obj = new TransferObject();

	public TransferObject getObj() {
		return obj;
	}

	public void setObj(TransferObject obj) {
		this.obj = obj;
	}

	private Model model;
	private MainScreen vie;

	/**
	 * 
	 * @param model
	 * @param view
	 */
	public Controller(Model model, MainScreen vie) {
		this.model = model;
		this.vie = vie;
	}

	public void startExecution() {

		vie.getBtnNewButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vie.dispose();
				// view.setVisible(false);
				firstScreen();
				// view.setVisible(true);

			}
		});

		vie.getBtnNewButton_1().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vie.dispose();
				// view.setVisible(false);
				actionListenerToSeconfFrame();
			}

		});

		vie.getBtnNewButton_2().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vie.dispose();
				checkTypos();
			}
		});

	}

	public void actionListenerToSeconfFrame() {
		SecondFrame sf = new SecondFrame();
		sf.setTableData(model.getListOfWords());
		sf.setVisible(true);
		sf.gettTextField().setText("");
		sf.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sf.setIndexRowSelected(sf.getTable().getSelectedRow());
				TableModel model = sf.getTable().getModel();
				String word = model.getValueAt(sf.getIndexRowSelected(), 2).toString();
				sf.gettTextField().setText(word);
			}
		});
		sf.setIndexRowSelected(-1);
		sf.getBtnNewButton().addActionListener(e -> {

			sf.getTableModel();
			if (sf.getIndexRowSelected() == -1 || (sf.gettTextField().getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "جناب ٹیبل سے لفظ منتخب کریں۔");
				sf.gettTextField().setText("");

			} else {
				int id = Integer.parseInt(sf.getTableModel().getValueAt(sf.getIndexRowSelected(), 0).toString());
				if (!(sf.gettTextField().getText().equals(""))) {

					String word = sf.getTextField();
					int i = word.indexOf(' ');
					word = word.substring(0, i);
					obj.setWordId(id);
					obj.setWord(word);
					model.getValueofUpdatedWord(obj);
					sf.gettTextField().setText("");
					try  
					{         
					File f= new File("word.txt");           //file to be delete  
					if(f.delete())                      //returns Boolean value  
					{  
					System.out.println(f.getName() + " deleted");   //getting and printing the file name  
					}  
					else  
					{  
					System.out.println("failed");  
					}  
					}  
					catch(Exception e1)  
					{  
					e1.printStackTrace();  
					}
					sf.setTableData(model.getListOfWords());

				} else if ((sf.getTextField().contentEquals("(?U)[\\\\W_]+"))) {
					JOptionPane.showMessageDialog(null, "جناب برائے مہربانی صرف اردو الفاظ درج کریں۔");
				} else {
					JOptionPane.showMessageDialog(null, "براہ کرم پہلے ایک لفظ کا انتخاب کریں");
				}
			}
		});
		sf.setIndexRowSelected(-1);
		sf.getBtnNewButton_1().addActionListener(e -> {
			sf.getTableModel();
			if (sf.getIndexRowSelected() == -1 || (sf.gettTextField().getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "جناب ٹیبل سے لفظ منتخب کریں۔");
				sf.gettTextField().setText("");

			} else {
				if (!(sf.gettTextField().getText().equals(""))) {
					int id = Integer.parseInt(sf.getTableModel().getValueAt(sf.getIndexRowSelected(), 0).toString());
					obj.setWordId(id);
					System.out.println("Deleting Id is : " + obj.getWordId());
					model.deleteWordIdForDb(obj);
					sf.gettTextField().setText("");
					try  
					{         
					File f= new File("word.txt");           //file to be delete  
					if(f.delete())                      //returns Boolean value  
					{  
					System.out.println(f.getName() + " deleted");   //getting and printing the file name  
					}  
					else  
					{  
					System.out.println("failed");  
					}  
					}  
					catch(Exception e1)  
					{  
					e1.printStackTrace();  
					}
					sf.setTableData(model.getListOfWords());
				} else {
					JOptionPane.showMessageDialog(null, "براہ کرم پہلے ایک لفظ کا انتخاب کریں");
				}
			}
		});
		sf.setIndexRowSelected(-1);
	}

	public void firstScreen() {
		View v = new View();
		v.setVisible(true);
		v.getBtnNewButton().addActionListener(e -> {

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int response = fileChooser.showOpenDialog(null);
			if (response == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsoluteFile(), "");
				String path = file.toString();
				model.openDirectory(path);
				// v.setVisible(false);
				// view.setVisible(true);
			}
		});
	}

	public void checkTypos() {
		ViewTypos v = new ViewTypos();
		v.setVisible(true);
		v.getBtnNewButton().addActionListener(e -> {

			Scanner write = new Scanner(System.in);

			// System.out.println("enter text for checking :: ");
			String sentence = v.word_input.getText();
			v.textArea.setText(sentence);
			String[] splitSentence = sentence.split(" "); // spliting it

			for (int i = 0; i < splitSentence.length; i++) {
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

					// String[] compare= compare1.split(" ");// new split logic

					if (compare.equalsIgnoreCase(splitSentence[i])) // start from 1st word to onward
					{

						// System.out.println("index no : "+i+" "+ splitSentence[i] + " : correct");
						exist = true;
						break;
					}

				}
				if (exist == false) {
					// System.out.println(splitSentence[i] + " : incorrect(not found)");
					model.highlight(v.textArea, splitSentence[i]); // calling here highlight function
					model.setwordwrong(splitSentence[i]);// getting wrong word here
					// here start recommendation process
					List<String> list = new ArrayList<String>();
					model.read_txt(list);
					List<String> suggestion = new ArrayList<String>();
					int mat_val = 2; // edit distance between words for matching possibles matches (with correct
										// word)

					// for each loop
					for (String word : list) {
						int dist = model.edit_distance_val(splitSentence[i], word);
						if (dist <= mat_val) {
							suggestion.add(word);
						}
					}
					// print

					// spell checker here

					if (suggestion.size() > 0) {
						DefaultTableModel model = (DefaultTableModel) v.table.getModel();
						for (String word1 : suggestion) {
							// System.out.println("did you mean?");
							// System.out.println(word1);
							model.addRow(new Object[] { word1 });
							v.suggest.setText(word1);
						}
					}

				}
			}

			// end
			//

		});

//		view.getBtnNewButton().addActionListener(e ->
//		{
		// table mouse click
		// here this is where i get word after clicking on it and replace it with
		// incorrect word in textfield
		v.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = v.table.getSelectedRow();
				int val = v.table.getSelectedColumn();

				DefaultTableModel model1 = (DefaultTableModel) v.table.getModel();
				String val1 = (model1.getValueAt(index, val).toString());// correct val
				// System.out.println(val1);

				String sentence45 = v.word_input.getText();
				//
				String[] splitSentence1 = sentence45.split(" "); // spliting it
				// System.out.println(model.getwordwrong());
				for (int i = 0; i < splitSentence1.length; i++) {
					if (splitSentence1[i].equalsIgnoreCase(model.getwordwrong())) {
						// System.out.println("found in list");
						splitSentence1[i] = val1;
					}

				}
				String sen = " ";
				for (String word : splitSentence1) {
					sen += " " + word + " ";
					System.out.println(word);
				}
				// replacing and reinstalizaing text field with correct input

				v.word_input.setText(sen);
			}

		});
		//
	}

}
