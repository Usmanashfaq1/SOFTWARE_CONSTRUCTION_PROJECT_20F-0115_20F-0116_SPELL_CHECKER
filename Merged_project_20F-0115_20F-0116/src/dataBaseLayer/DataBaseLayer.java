package dataBaseLayer;

import tranferObjects.*;
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
import java.io.File;
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
	private Statement state;
	private ArrayList<String> alf = new ArrayList<String>();

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
		ArrayList<TransferObject> tO = new ArrayList<TransferObject>();
		Connection con = Singleton.SqlConnection();
		try {
			state = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
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

	public void updateWord(int id, String updatedWord) {
		Connection con = Singleton.SqlConnection();
		try {
			state = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			state.executeUpdate("update wordtable set word =  '" + updatedWord + "'  where wid = '" + id + "';");

		} catch (Exception e) {
			System.out.println("Unable to Update in word table of dataBase Project_of_scd in Sql");
		}
	}

	public void deleteWord(int id) {
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
/////////////////////////////////////////////////////////

	/// 20f-0115

	public void txtfilegenerate() {
		Connection con = Singleton.SqlConnection();
		try {
			state = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//
		try {
			File myObj = new File("word.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
				//
				BufferedWriter out = null;
				try {
					File file = new File("word.txt"); // your file name
					out = new BufferedWriter(new FileWriter(file, true));
					try {

						ResultSet st = state.executeQuery("select * from `wordtable` order by frequency");
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

			} else {
				System.out.println("File already exists!!!!!!!!!!!");
				BufferedReader br = new BufferedReader(new FileReader("word.txt"));
				if (br.readLine() == null) {
					System.out.println("file is empty!!!!!!!!!!!!");
					//
					BufferedWriter out = null;
					try {
						File file = new File("word.txt"); // your file name
						out = new BufferedWriter(new FileWriter(file, true));
						try {

							ResultSet st = state.executeQuery("select * from `wordtable` order by frequency");
							while (st.next()) {
								out.write(st.getString("word") + "\n");
								System.out.println(st.getString("word"));
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							System.out
									.println("Unable to fetch data from main table of dataBase Project_of_scd in Sql");
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
	///////////////////////////////////////

}
