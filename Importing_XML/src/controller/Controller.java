package controller;

import java.io.File;
import javax.swing.JFileChooser;
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
		view.getBtnNewButton().addActionListener(e ->{
			
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
	}

}
