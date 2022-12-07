package Model;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.sql.Statement;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;



public class Model 
{
	public String val3;
	private Statement stmt;
	// setting color here
	 // default highlighter instance
   Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter(Color.CYAN);// color
// A private subclass of the default highlight painter
   class MyHighlightPainter  extends DefaultHighlighter.DefaultHighlightPainter 
           {
       public MyHighlightPainter(Color color) 
       {
           super(color);//
       }
   }
//
	
// function:
			//
		 // Creates highlights around all occurrences of text in given comp //pattern
	    public void highlight(JTextComponent area, String input_string)
	    {

	        try {
	            Highlighter highlight = area.getHighlighter();
	            Document len = area.getDocument(); //text area
	         
	           String text = len.getText(0, len.getLength());
	            

	            int position = 0;
	            
	            while ((position = text.indexOf(input_string, position)) >= 0) 
	            {
	                ////highlighting here the pattern of given string
	                highlight.addHighlight(position, position + input_string.length(), myHighlightPainter);
	                position += input_string.length();
	             
	            }

	        } catch (BadLocationException e) 
	        {
	        	
	        }
	    }
	    
	    
	    // reading txt file 
	   public void read_txt(List<String> l1)
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
           
            while(txtfile.hasNextLine())
            {
               String compare = txtfile.nextLine(); //first word first iteration
             //  System.out.println("compare val is : "+ compare);
               l1.add(compare);
               
            //  String[] compare= compare1.split(" ");// new split logic
               
               
                
            }
            
//            for(String output:l1) 
//            {
//            	System.out.println(output); 
//            	}
	
	    
	    }
	   
	   
	  
		
	    
	    

}
