package dataBaseLayer;

import tranferObjects.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * @author maste
 *
 */
public class DataBaseLayer {
	private ArrayList<TransferObject> tO = new ArrayList<TransferObject>();
	private Statement state;
	private ArrayList<String> alf = new ArrayList<String>();

	/**
	 * 
	 * @return
	 */
//	public boolean sqlConnection() {
//
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_of_scd", "root", "");
//			state = con.createStatement();
//			return true;
//		} catch (Exception e) {
//
//			System.out.println("Connection Unable to be Established with Sql");
//		}
//		return false;
//	}

	/**
	 * 
	 * @param t
	 * @param n
	 * @param p
	 * @return
	 */
	public boolean insertIntoDataBase(String t, String n, String p) {
		Connection con = Singleton.SqlConnection();
		try {
			state = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (t != " " && n != " " && p != "") {
			try {

				state.executeUpdate("insert into maintable ( title , AUTHOR, paragraph) VALUES( '" + t + "' , '" + n
						+ "', '" + p + "');");
				return true;
			} catch (Exception e) {
				System.out.println("Unable to insert in main table of dataBase Project_of_scd in Sql");
			}

		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<TransferObject> getWordForViewingInTable() {
		Connection con = Singleton.SqlConnection();
		try {
			state = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println("db");
			ResultSet st = state.executeQuery("select * from `wordtable` order by frequency ");

			while (st.next()) {
				TransferObject t = new TransferObject();
				t.setWordId(st.getInt("wid"));
				t.setFrequency(st.getInt("frequency"));
				t.setWord(st.getString("word"));
				tO.add(t);
			}
		} catch (SQLException e) {
			System.out.println("SQL Query Exception Caught");
		}
		int i =0;
		for(TransferObject t : tO)
		{
			System.out.println(" " + t.getWord());
			i++;
			if (i ==7)
				break;
		}
		return tO;
	}

	public ArrayList<String> maintainWordIDWithFile() {
		Connection con = Singleton.SqlConnection();
		try {
			state = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String var = "";
		try {

			ResultSet s = state.executeQuery("select * from `maintable` order by ID");

			while (s.next()) {
				var = s.getString("paragraph");
				alf.add(var);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to fetch data from main table of dataBase Project_of_scd in Sql");
		}
		return alf;
	}

	/**
	 * 
	 * @param w
	 * @param f
	 * @return
	 */
	public boolean insertWordInDataBase(String w, int f) {
		Connection con = Singleton.SqlConnection();
		try {
			state = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (f != 0) {
			try {
				state.executeUpdate("insert into wordtable ( frequency, word ) VALUES( '" + f + "', '" + w + "');");
				return true;
			} catch (Exception e) {
				System.out.println("Unable to insert in word table of dataBase Project_of_scd in Sql");
			}
		}
		return false;
	}

	public void insertWordIdInTable(int mid, int wid) {
		Connection con = Singleton.SqlConnection();
		try {
			state = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			state.executeUpdate("insert into idtable ( mid, wid ) VALUES( '" + mid + "', '" + wid + "');");

		} catch (Exception e) {
			System.out.println("Unable to insert in ID table for word ID of dataBase Project_of_scd in Sql");
		}
	}
	
	public void updateWord(int id , String updatedWord)
	{
		Connection con = Singleton.SqlConnection();
		try {
			state = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println("id : " + id + " Updated word : " + updatedWord);
			state.executeUpdate("update wordtable set word =  '" + updatedWord + "'  where wid = '" + id + "';");

		} catch (Exception e) 
			{
			System.out.println("Unable to Update in word table of dataBase Project_of_scd in Sql");
		}
	}
	
	public void deleteWord(int id)
	{
		Connection con = Singleton.SqlConnection();
		try {
			state = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			state.executeUpdate("Delete from wordtable where wid = '" + id + "';");

		} catch (Exception e) {
			System.out.println("Unable to delete in word table of dataBase Project_of_scd in Sql");
		}
	}


}
