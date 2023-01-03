package presentationLayer;

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

import businessLogicLayer.*;
//import presentationLayer.*;
import tranferObjects.TransferObject;

/**
 * 
 * @author maste
 *
 */
public class Controller {
	private TransferObject transferObject = new TransferObject();

	public TransferObject getObj() {
		return transferObject;
	}

	public void setObj(TransferObject obj) {
		this.transferObject = obj;
	}

	private BusinessLogicLayer model;
	private MainScreen mainScreenView;

	/**
	 * 
	 * @param model
	 * @param view
	 */
	public Controller(BusinessLogicLayer model, MainScreen mainScreenView) {
		this.model = model;
		this.mainScreenView = mainScreenView;
	}

	public void startExecution() {

		mainScreenView.getBtnNewButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenView.dispose();
				// view.setVisible(false);
				firstScreen();
				// view.setVisible(true);

			}
		});

		mainScreenView.getBtnNewButton_1().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenView.dispose();
				// view.setVisible(false);
				actionListenerToSecondFrame();
			}

		});

		mainScreenView.getBtnNewButton_2().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenView.dispose();
				checkTypos();
			}
		});

	}

	public void actionListenerToSecondFrame() {
		SecondFrame secondFrame = new SecondFrame();
		secondFrame.setTableData(model.getListOfWords());
		secondFrame.setVisible(true);
		secondFrame.gettTextField().setText("");
		secondFrame.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				secondFrame.setIndexRowSelected(secondFrame.getTable().getSelectedRow());
				TableModel model = secondFrame.getTable().getModel();
				String word = model.getValueAt(secondFrame.getIndexRowSelected(), 2).toString();
				secondFrame.gettTextField().setText(word);
			}
		});
		secondFrame.setIndexRowSelected(-1);
		secondFrame.getUpdateButton().addActionListener(e -> {

			secondFrame.getTableModel();
			if (secondFrame.getIndexRowSelected() == -1 || (secondFrame.gettTextField().getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "جناب ٹیبل سے لفظ منتخب کریں۔");
				secondFrame.gettTextField().setText("");

			} else {
				int id = Integer.parseInt(
						secondFrame.getTableModel().getValueAt(secondFrame.getIndexRowSelected(), 0).toString());
				if (!(secondFrame.gettTextField().getText().equals(""))) {

					String word = secondFrame.getTextField();
					int wordIndex = word.indexOf(' ');
					word = word.substring(0, wordIndex);
					transferObject.setWordId(id);
					transferObject.setWord(word);
					model.getValueofUpdatedWord(transferObject);
					secondFrame.gettTextField().setText("");
					try {
						File file = new File("word.txt"); // file to be delete
						if (file.delete()) // returns Boolean value
						{
							System.out.println(file.getName() + " deleted"); // getting and printing the file name
						} else {
							System.out.println("failed");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					secondFrame.setTableData(model.getListOfWords());

				} else if ((secondFrame.getTextField().contentEquals("(?U)[\\\\W_]+"))) {
					JOptionPane.showMessageDialog(null, "جناب برائے مہربانی صرف اردو الفاظ درج کریں۔");
				} else {
					JOptionPane.showMessageDialog(null, "براہ کرم پہلے ایک لفظ کا انتخاب کریں");
				}
			}
		});
		secondFrame.setIndexRowSelected(-1);
		secondFrame.getDeleteButton().addActionListener(e -> {
			secondFrame.getTableModel();
			if (secondFrame.getIndexRowSelected() == -1 || (secondFrame.gettTextField().getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "جناب ٹیبل سے لفظ منتخب کریں۔");
				secondFrame.gettTextField().setText("");

			} else {
				if (!(secondFrame.gettTextField().getText().equals(""))) {
					int id = Integer.parseInt(
							secondFrame.getTableModel().getValueAt(secondFrame.getIndexRowSelected(), 0).toString());
					transferObject.setWordId(id);
					System.out.println("Deleting Id is : " + transferObject.getWordId());
					model.deleteWordIdForDb(transferObject);
					secondFrame.gettTextField().setText("");
					try {
						File file = new File("word.txt"); // file to be delete
						if (file.delete()) // returns Boolean value
						{
							System.out.println(file.getName() + " deleted"); // getting and printing the file name
						} else {
							System.out.println("failed");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					secondFrame.setTableData(model.getListOfWords());
				} else {
					JOptionPane.showMessageDialog(null, "براہ کرم پہلے ایک لفظ کا انتخاب کریں");
				}
			}
		});
		secondFrame.setIndexRowSelected(-1);
	}

	/**
	 * Applied Extract function
	 */
	public void chooseFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int response = fileChooser.showOpenDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			File file = new File(fileChooser.getSelectedFile().getAbsoluteFile(), "");
			String path = file.toString();
			model.openDirectory(path);

		}
	}


	public void firstScreen() {
		View view = new View();
		view.setVisible(true);
		view.getBrowseButton().addActionListener(e -> {
			chooseFile();
		});
	}

	public void checkTypos() {
		ViewTypos viewTypos = new ViewTypos();
		viewTypos.setVisible(true);
		viewTypos.getCheckButton().addActionListener(e -> {

			Scanner write = new Scanner(System.in);

			// System.out.println("enter text for checking :: ");
			String sentence = viewTypos.inputWord.getText();
			viewTypos.textArea.setText(sentence);
			String[] splitSentence = sentence.split(" "); // spliting it

			for (int i = 0; i < splitSentence.length; i++) {
				boolean exist = false; // flag
				Scanner textfile = null;
				try {
					textfile = new Scanner(new File("word.txt")); // reading file here
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				while (textfile.hasNextLine())// here comparing with whole dictrionary
				{
					String compare = textfile.nextLine(); // first word first iteration
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
					model.highlight(viewTypos.textArea, splitSentence[i]); // calling here highlight function
					model.setwordwrong(splitSentence[i]);// getting wrong word here
					// here start recommendation process
					List<String> list = new ArrayList<String>();
					model.readTextFile(list);
					List<String> suggestion = new ArrayList<String>();
					int valueLimit = 2; // edit distance between words for matching possibles matches (with correct
										// word)

					// for each loop
					for (String word : list) {
						int distance = model.editDistanceValue(splitSentence[i], word);

						if (distance <= valueLimit) {
							suggestion.add(word);
						}
					}
					// print

					// spell checker here
					assert (suggestion.size() > 0);
					if (suggestion.size() > 0) {
						DefaultTableModel model = (DefaultTableModel) viewTypos.table.getModel();
						for (String word : suggestion) {
							// System.out.println("did you mean?");
							// System.out.println(word1);
							model.addRow(new Object[] { word });
							viewTypos.suggest.setText(word);
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
		viewTypos.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = viewTypos.table.getSelectedRow();
				int value = viewTypos.table.getSelectedColumn();

				DefaultTableModel model1 = (DefaultTableModel) viewTypos.table.getModel();
				String suggestedWord = (model1.getValueAt(index, value).toString());// correct val
				// System.out.println(val1);

				String sentence = viewTypos.inputWord.getText();
				//
				String[] splitSentence = sentence.split(" "); // spliting it
				// System.out.println(model.getwordwrong());
				for (int i = 0; i < splitSentence.length; i++) {
					if (splitSentence[i].equalsIgnoreCase(model.getWordWrong())) {
						// System.out.println("found in list");
						splitSentence[i] = suggestedWord;
					}

				}
				String combineWord = " ";
				for (String word : splitSentence) {
					combineWord += " " + word + " ";
					System.out.println(word);
				}
				// replacing and reinstalizaing text field with correct input

				viewTypos.inputWord.setText(combineWord);
			}

		});
		//
	}

}
