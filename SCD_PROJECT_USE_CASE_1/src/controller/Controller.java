package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
	private View view;

	/**
	 * 
	 * @param model
	 * @param view
	 */
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	public void startExecution() {
		view.getBtnNewButton().addActionListener(e -> {

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int response = fileChooser.showOpenDialog(null);
			if (response == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsoluteFile(), "");
				String path = file.toString();
				start2Execution(path);

			}
		});
	}

	public void start2Execution(String path) {
		model.openDirectory(path);
		view.dispose();
		SecondFrame sf = new SecondFrame(model.getListOfWords());
		sf.setVisible(true);
		sf.gettTextField().setText("");
		sf.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sf.setIndexRowSelected(sf.getTable().getSelectedRow());
				TableModel model = sf.getTable().getModel();
//				int id = Integer.parseInt(model.getValueAt(indexRowSelected, 0).toString());
//				int frequency = Integer.parseInt(model.getValueAt(indexRowSelected, 1).toString());
				String word = model.getValueAt(sf.getIndexRowSelected(), 2).toString();
				sf.gettTextField().setText(word);
				// System.out.println( "Id is : " + id + " frequency is : " + frequency + " word
				// is : " + word );//JOptionPane.showMessageDialog
			}
		});
		sf.getBtnNewButton().addActionListener(e -> {
			sf.getTableModel();
			if (sf.getIndexRowSelected() == -1) {
				JOptionPane.showMessageDialog(null, "جناب ٹیبل سے لفظ منتخب کریں۔");
				sf.gettTextField().setText("");
				return;
			}
			int id = Integer.parseInt(sf.getTableModel().getValueAt(sf.getIndexRowSelected(), 0).toString());
			int frequency = Integer.parseInt(sf.getTableModel().getValueAt(sf.getIndexRowSelected(), 1).toString());
			if (!(sf.gettTextField().getText().equals(""))) {

				String word = sf.getTextField();
				int i = word.indexOf(' ');
				word = word.substring(0, i);
				obj.setWordId(id);
				obj.setWord(word);
				System.out.println(
						"Updated Id is : " + id + " frequency is : " + frequency + " word is : " + obj.getWord());
				model.getValueofUpdatedWord(obj);
				sf.gettTextField().setText("");
			} else {
				JOptionPane.showMessageDialog(null, "براہ کرم پہلے ایک لفظ کا انتخاب کریں");
			}
		});
		sf.getBtnNewButton_1().addActionListener(e -> {
			sf.getTableModel();
			if (sf.getIndexRowSelected() == -1) {
				JOptionPane.showMessageDialog(null, "جناب ٹیبل سے لفظ منتخب کریں۔");
				sf.gettTextField().setText("");
				return;
			}
			if (!(sf.gettTextField().getText().equals(""))) {
				int id = Integer.parseInt(sf.getTableModel().getValueAt(sf.getIndexRowSelected(), 0).toString());
				obj.setWordId(id);
				System.out.println("Deleting Id is : " + obj.getWordId());
				model.deleteWordIdForDb(obj);
				sf.gettTextField().setText("");
			} else {
				JOptionPane.showMessageDialog(null, "براہ کرم پہلے ایک لفظ کا انتخاب کریں");
			}
		});
	}

}
