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

//
import DataBaseLayer.DataBaseLayer;



public class Model 
{
	public String val3;
	
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
           
            while(txtfile.hasNextLine())//here comparing with whole dictrionary
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
	   
	   //edit distance function 
	   public static int edit_distance_val(String word1, String word2)
	   {
		   
		   //validation
		   if((word1 == null || word1.length() == 0) && (word2 == null || word2.length() == 0))
		   {
			   return 0;
		   }
		   // string lengths 
			int row1 = word1.length(); 
			int col2 = word2.length();
		 
			// len1+1, len2+1, 
			// crearting an 2d array(table) for value according to words sizes
			int[][] arr = new int[row1 + 1][col2 + 1];
		 
			//intitalizng with int values acc to sizes
			for (int i = 0; i <= row1; i++) 
			{
				
				//arr[i][0] = i;
			
		 
			for (int j = 0; j <= col2; j++) 
			{
				if(j==0)
				{
				arr[i][0] = i;
				}
				
				if(i==0)
				{
					arr[0][j]=j;
				}
			}
				
				
		    }
			
			
			int i=0;
			while(i<row1)
			{
				char w1 = word1.charAt(i);
				//System.out.println(c1);
				
				for (int j = 0; j < col2; j++) 
				{
					//getting char at last place of array jth
					char w2 = word2.charAt(j);
					//System.out.println(c2);
					//baseball     zzesball
		 
					//if two characters are equal 
					if (w1 == w2)  // no need to perform actions (same size words, no edit distance?)
					{
						//System.out.println("if executed!");
						//update dp value for +1 length
						arr[i + 1][j + 1] = arr[i][j]; //skip 
					} 
					else //operations forming edit distances!
					{
						//System.out.println("last is no same!");
						//replaement 
						int replacment_val = arr[i][j] + 1;
						//insertion
						int insertion_val = arr[i][j + 1] + 1;   // droping j last and then inserting back in
						//deletion
						int deletion_val = arr[i + 1][j] + 1; 
		 
						int edit_value;
						if(replacment_val > insertion_val)
						{
							edit_value=insertion_val;
						}
						else 
						{
							edit_value=replacment_val;
						}
						
						if(!(deletion_val>edit_value))
						{
							edit_value=deletion_val;
//							min=min;
						}
//						else
//						{
//							
//						}

						arr[i + 1][j + 1] = edit_value;// returning here
						//System.out.println("min val is :: "+ min);
						
					}
				}
				i++;
				
			}
		 
			
		 
			return arr[row1][col2]; // returning edit distance value here
		}
	   
	   
	   

	   
	   public void database()
	   {
		   
		   DataBaseLayer obj=new DataBaseLayer();
		  // Connection abc=new Connection();
		  
		   obj.txtfilegenerate();
		   
	   }
		
		
	 //getting wrong word from input word textfield
		public	void setwordwrong(String a)
		{
			val3=a;
		}
		public	String getwordwrong()
		{
			return val3;
		}
		

		
		
		
	    
	    

}
