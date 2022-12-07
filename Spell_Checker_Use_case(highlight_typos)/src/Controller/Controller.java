package Controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

import Model.Model;

import view.View;


public class Controller
{
	
	private Model model;
	private View view;
	public Controller(Model model, 	View view)
	{
		//super();
		this.model = model;
		this.view = view;
	}
	public void check()
	{
		view.getBtnNewButton().addActionListener(e ->
		{
			
			Scanner write = new Scanner(System.in);

	        System.out.println("enter text for checking :: ");
	        String sentence = view.word_input.getText();
	        view.textArea.setText(sentence);
	        String[] splitSentence = sentence.split(" "); // spliting it 
	        
	        for(int i = 0; i < splitSentence.length; i++)
	        {
	        	boolean exist = false; //flag
	            Scanner txtfile = null;
				try 
				{
				txtfile = new Scanner(new File("word.txt")); // reading file here
				} catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				}
	           
	            while(txtfile.hasNextLine())//here comparing with whole dictrionary
	            {
	               String compare = txtfile.nextLine(); //first word first iteration
	               System.out.println("compare val is : "+ compare);
	               
	            //  String[] compare= compare1.split(" ");// new split logic
	               
	                if(compare.equalsIgnoreCase(splitSentence[i])) // start from 1st word to onward
	                {
	                	
	                    System.out.println("index no : "+i+"  "+ splitSentence[i] + " : correct");
	                    exist=true;
	                    break;
	                }
	                
	            }
	            if(exist==false)
	            {
	               System.out.println(splitSentence[i] + " : incorrect(not found)");
	               model.highlight(view.textArea,splitSentence[i] ); //calling here highlight function
	             
	               // creating a suggestion list to compare it with words(dictionary)
	               List<String> list = new ArrayList<String>();
	               model.read_txt(list);
	               List<String> suggestion = new ArrayList<String>();
	             
	               
	               
	               
	               
	            }
	        }
	        
		 
		});
		

		
	}
	// main function
	//start
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Model m = new Model();
		View v = new View();
		v.setVisible(true);//
		Controller c = new Controller(m, v);
		c.check();
		
//		m.read_txt(list);
//		for(String output:list) 
//		{
//			
//			System.out.println(output); 
//			}
		
		
	}
	//end
	
	
}
