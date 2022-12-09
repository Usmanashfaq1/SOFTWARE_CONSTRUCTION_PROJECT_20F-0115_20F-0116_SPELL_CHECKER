package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.table.TableModel;

import model.*;
import view.*;

/**
 * 
 * @author maste
 *
 */
public class Controller {

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
		sf.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sf.setIndexRowSelected(sf.getTable().getSelectedRow()) ;
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
			int id = Integer.parseInt(sf.getTableModel().getValueAt(sf.getIndexRowSelected(), 0).toString());
			int frequency = Integer.parseInt(sf.getTableModel().getValueAt(sf.getIndexRowSelected(), 1).toString());
			String word = sf.getTextField();
			int i = word.indexOf(' ');
			word = word.substring(0, i);
			System.out.println("Updated Id is : " + id + " frequency is : " + frequency + " word is : " + word);
		});
		sf.getBtnNewButton_1().addActionListener(e -> {
			sf.getTableModel();
			int id = Integer.parseInt(sf.getTableModel().getValueAt(sf.getIndexRowSelected(), 0).toString());
			System.out.println("Deleting Id is : " + id );
		});
	}
}
