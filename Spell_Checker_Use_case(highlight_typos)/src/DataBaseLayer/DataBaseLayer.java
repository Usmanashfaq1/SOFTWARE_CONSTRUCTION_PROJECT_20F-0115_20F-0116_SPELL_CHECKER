package DataBaseLayer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;



public class DataBaseLayer 
{
	private Statement stmt;
	//
	public boolean connection()
	{
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/spell_checker","root","");  
			
			 stmt=con.createStatement();  
			System.out.println("connected");
			
						 
			return true;
			
			 
			
	    }
		catch (Exception e) 
		{
			System.out.println("Connection Unable to be Established with Sq l");
	    }
			return false;
	}
	//
	public void txtfilegenerate()
	{
		
		
		//
		try {
		      File myObj = new File("word.txt");
		      if (myObj.createNewFile()) 
		      {
		        System.out.println("File created: " + myObj.getName());
		        //
		        BufferedWriter out = null;
				try {
					File file = new File("word.txt"); // your file name
					out = new BufferedWriter(new FileWriter(file, true));
				try {
					
					ResultSet st = stmt.executeQuery("select * from `wordtable` order by frequency");
					while (st.next()) {
						out.write(st.getString("word") + "\n");
						System.out.println(st.getString("word"));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Unable to fetch data from main table of dataBase Project_of_scd in Sql");
				}
				out.close();
				} catch (IOException e) {
				}
		        //
		        
		        
		      } else 
		      {
		        System.out.println("File already exists!!!!!!!!!!!");
		        BufferedReader br = new BufferedReader(new FileReader("word.txt"));     
		        if (br.readLine() == null) 
		        {
		            System.out.println("file is empty!!!!!!!!!!!!");
		            //
			        BufferedWriter out = null;
					try {
						File file = new File("word.txt"); // your file name
						out = new BufferedWriter(new FileWriter(file, true));
					try {
						
						ResultSet st = stmt.executeQuery("select * from `wordtable` order by frequency");
						while (st.next()) {
							out.write(st.getString("word") + "\n");
							System.out.println(st.getString("word"));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Unable to fetch data from main table of dataBase Project_of_scd in Sql");
					}
					out.close();
					} catch (IOException e) {
					}
			        //
		            
		        }
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		//
		
		
	
		
		
		}
	

}
