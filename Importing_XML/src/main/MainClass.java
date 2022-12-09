package main;

import model.*;
import view.*;
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
		// TODO Auto-generated method stub
		Model m = new Model();
		View v = new View();
		v.setVisible(true);
		Controller c = new Controller(m, v);
		c.startExecution();

	}

}
