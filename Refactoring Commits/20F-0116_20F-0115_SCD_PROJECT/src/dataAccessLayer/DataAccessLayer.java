package dataAccessLayer;

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
import java.util.logging.Level;

import main.Log;

/**
 * 
 * @author maste
 *
 */
public class DataAccessLayer implements Facade {
	private Statement state;
	private ArrayList<String> stringArrayList = new ArrayList<String>();

	/**
	 * 
	 * @param title
	 * @param name
	 * @param paragraph
	 * @return
	 */
	@Override
	public boolean insertIntoDataBase(String title, String name, String paragraph) {
		Connection con = Singleton.SqlConnection();
		Log log = new Log("log.txt");
		try {
			state = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (title != " " && name != " " && paragraph != "") {
			try {

				state.executeUpdate("insert into maintable ( title , AUTHOR, paragraph) VALUES( '" + title + "' , '" + name
						+ "', '" + paragraph + "');");
				log.logger.info("Info message: Inserted title , Author and Paragraph in DB Main Table");
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
	@Override
	public ArrayList<TransferObject> getWordForViewingInTable() {
		ArrayList<TransferObject> transferObjectArrayList = new ArrayList<TransferObject>();
		Connection connection = Singleton.SqlConnection();
		Log log = new Log("log.txt");
		try {
			state = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ResultSet st = state.executeQuery("select * from `wordtable` order by frequency ");

			while (st.next()) {
				TransferObject transferObject = new TransferObject();
				transferObject.setWordId(st.getInt("wid"));
				transferObject.setFrequency(st.getInt("frequency"));
				transferObject.setWord(st.getString("word"));
				transferObjectArrayList.add(transferObject);
			}
			log.logger.info("Info message: Read Data of Word table from DB");
		} catch (SQLException e) {
			System.out.println("SQL Query Exception Caught");
		}
		return transferObjectArrayList;
	}

	@Override
	public ArrayList<String> maintainWordIDWithFile() {
		Connection connection = Singleton.SqlConnection();
		Log log = new Log("log.txt");
		try {
			state = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String paragraphWord = "";
		try {

			ResultSet resultSet = state.executeQuery("select * from `maintable` order by ID");

			while (resultSet.next()) {
				paragraphWord = resultSet.getString("paragraph");
				stringArrayList.add(paragraphWord);
			}
			log.logger.info("Info message: Maintaining word Id CrossPonding to Each Paragraph");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to fetch data from main table of dataBase Project_of_scd in Sql");
		}
		return stringArrayList;
	}

	/**
	 * 
	 * @param word
	 * @param frequency
	 * @return
	 */
	@Override
	public boolean insertIntoDataBase(String word, int frequency) {
		Connection connection = Singleton.SqlConnection();
		try {
			state = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assert(frequency > 0);
		if (frequency != 0) {
			try {
				state.executeUpdate("insert into wordtable ( frequency, word ) VALUES( '" + frequency + "', '" + word + "');");
				Log log = new Log("log.txt");
				log.logger.info("Info message: Inserted word in word table");
				return true;
			} catch (Exception e) {
				System.out.println("Unable to insert in word table of dataBase Project_of_scd in Sql");
			}
		}
		return false;
	}

	@Override
	public boolean insertIntoDataBase(int parentId, int wordId) {
		Connection connection = Singleton.SqlConnection();
		try {
			state = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			state.executeUpdate("insert into idtable ( mid, wid ) VALUES( '" + parentId + "', '" + wordId + "');");
			Log log = new Log("log.txt");
			log.logger.info("Info message: Inserted word Id in Id table crossponding to main Table");
			return true;
		} catch (Exception e) {
			System.out.println("Unable to insert in ID table for word ID of dataBase Project_of_scd in Sql");
		}
		return false;
	}

	@Override
	public boolean manipulateWord(int id, String updatedWord) {
		Connection connection = Singleton.SqlConnection();
		try {
			state = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			state.executeUpdate("update wordtable set word =  '" + updatedWord + "'  where wid = '" + id + "';");
			Log log = new Log("log.txt");
			log.logger.info("Info message: Updated word in word table");
			return true;
		} catch (Exception e) {
			System.out.println("Unable to Update in word table of dataBase Project_of_scd in Sql");
		}
		return false;
	}

	@Override
	public boolean manipulateWord(int id) {
		Connection connection = Singleton.SqlConnection();
		try {
			state = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			state.executeUpdate("Delete from wordtable where wid = '" + id + "';");
			Log log = new Log("log.txt");
			log.logger.info("Info message: Deleted word in word table");
			return true;
		} catch (Exception e) {
			System.out.println("Unable to delete in word table of dataBase Project_of_scd in Sql");
		}
		return false;
	}
/////////////////////////////////////////////////////////

	/// 20f-0115
	@Override
	public boolean textFileGeneration() {
		Connection connection = Singleton.SqlConnection();
		try {
			state = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//
		try {
			File fileObject = new File("word.txt");
			if (fileObject.createNewFile()) {
				System.out.println("File created: " + fileObject.getName());
				//
				BufferedWriter out = null;
				try {
					File file = new File("word.txt"); // your file name
					out = new BufferedWriter(new FileWriter(file, true));
					try {

						ResultSet resultSet = state.executeQuery("select * from `wordtable` order by frequency");
						while (resultSet.next()) {
							out.write(resultSet.getString("word") + "\n");
							System.out.println(resultSet.getString("word"));
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
				BufferedReader bufferedReader = new BufferedReader(new FileReader("word.txt"));
				if (bufferedReader.readLine() == null) {
					System.out.println("file is empty!!!!!!!!!!!!");
					//
					BufferedWriter bufferedWriter = null;
					try {
						File file = new File("word.txt"); // your file name
						bufferedWriter = new BufferedWriter(new FileWriter(file, true));
						try {

							ResultSet resultSet = state.executeQuery("select * from `wordtable` order by frequency");
							while (resultSet.next()) {
								bufferedWriter.write(resultSet.getString("word") + "\n");
								System.out.println(resultSet.getString("word"));
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							System.out
									.println("Unable to fetch data from main table of dataBase Project_of_scd in Sql");
						}
						bufferedWriter.close();
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
		return true;
	}
	///////////////////////////////////////

}
