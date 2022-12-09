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

	      //  System.out.println("enter text for checking :: ");
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	           
	            while(txtfile.hasNextLine())//here comparing with whole dictrionary
	            {
	               String compare = txtfile.nextLine(); //first word first iteration
	              // System.out.println("compare val is : "+ compare);
	               
	            //  String[] compare= compare1.split(" ");// new split logic
	               
	                if(compare.equalsIgnoreCase(splitSentence[i])) // start from 1st word to onward
	                {
	                	
	                  //  System.out.println("index no : "+i+"  "+ splitSentence[i] + " : correct");
	                    exist=true;
	                    break;
	                }
	                
	            }
	            if(exist==false)
	            {
	               //System.out.println(splitSentence[i] + " : incorrect(not found)");
	               model.highlight(view.textArea,splitSentence[i] ); //calling here highlight function
	               model.setwordwrong(splitSentence[i]);//getting wrong word here
	               // here start recommendation process
	               List<String> list = new ArrayList<String>();
	               model.read_txt(list);
	               List<String> suggestion = new ArrayList<String>();
	               int mat_val=2; // edit distance between words for matching possibles matches (with correct word)
                    
	               // for each loop 
	               for (String word: list) 
	               {
	               int dist=model.edit_distance_val(splitSentence[i], word);
	               if(dist<=mat_val)
	               {
	               suggestion.add(word);
	               }
	               }
	               //print

	               // spell checker here


	               if(suggestion.size()>0)
	               {
	            	   DefaultTableModel model = (DefaultTableModel)view.table.getModel();
	               for (String word1: suggestion) 
	               {
	              // System.out.println("did you mean?");  
	              // System.out.println(word1);  
	               model.addRow( new Object[]{ word1} );
	               view.suggest.setText(word1);
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
		//here this is where i get word after clicking on it and replace it with incorrect word in textfield
		view.table.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				 int index = view.table.getSelectedRow();
				 int val=view.table.getSelectedColumn();

				 DefaultTableModel model1 = (DefaultTableModel) view.table.getModel();
				 String val1 = (model1.getValueAt(index, val).toString());// correct val
				// System.out.println(val1);
				 
				 String sentence45 = view.word_input.getText();
				//
				 String[] splitSentence1 = sentence45.split(" "); // spliting it 
				// System.out.println(model.getwordwrong());
			        for(int i = 0; i < splitSentence1.length; i++)
			        {
			        	if(splitSentence1[i].equalsIgnoreCase(model.getwordwrong()))
			        	{
			        		 //System.out.println("found in list");
			        		splitSentence1[i]=val1;
			        	}
			        	 
			        	
			        }
			        String sen=" ";
			        for (String word: splitSentence1 ) 
		               {
			        	 sen+=" "+word+" ";
			        	 System.out.println(word);
		               }
				 //replacing and reinstalizaing text field with correct input
				 
				 view.word_input.setText(sen);
			}
			
		});
		//
		
	}
	// main function
	//start
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		Model m = new Model();
		m.database();
		View v = new View();
		v.setVisible(true);
		Controller c = new Controller(m, v);
		c.check();
		

		
		
	}
	//end
	
	
}
