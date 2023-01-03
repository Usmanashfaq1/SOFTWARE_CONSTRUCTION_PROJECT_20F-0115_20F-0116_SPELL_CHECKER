package main;

import presentationLayer.*;

import java.util.logging.Level;

import com.mysql.cj.log.*;

import businessLogicLayer.*;
//import controller.*;

/**
 * 
 * @author maste
 *
 */
public class MainClass {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Log log = new Log("log.txt");
		// TODO Auto-generated method stub
		log.logger.setLevel(Level.WARNING);
		Model model = new Model();
		MainScreen view = new MainScreen();
		view.setVisible(true);
		Controller controller = new Controller(model, view);
		log.logger.warning("Warning message");
		log.logger.severe("Server message");
		model.accessDatabase();
		controller.startExecution();

	}

}
