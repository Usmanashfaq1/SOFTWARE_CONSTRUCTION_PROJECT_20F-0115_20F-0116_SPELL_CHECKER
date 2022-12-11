package main;

import model.*;
import view.*;

import java.util.logging.Level;

import com.mysql.cj.log.*;

import controller.*;

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
		
		Log my_log = new Log("log.txt");
		// TODO Auto-generated method stub
		my_log.logger.setLevel(Level.WARNING);
		Model m = new Model();
		MainScreen v = new MainScreen();
		v.setVisible(true);
		Controller c = new Controller(m, v);
		my_log.logger.warning("Warning message");
		my_log.logger.severe("Server message");
		m.database();
		c.startExecution();

	}

}
