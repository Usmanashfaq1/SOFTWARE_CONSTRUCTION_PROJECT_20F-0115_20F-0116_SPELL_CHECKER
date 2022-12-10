package dataBaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;

public class Singleton {
	private static Connection conObject = null;
	 Connection connection=null;
	private Singleton() {
	}

	public static Connection SqlConnection() {
		try {
			if (conObject == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conObject = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_of_scd", "root", "");
			}

		} catch (Exception e) {
			
			// throw new Exception("");
		}

		return conObject;
	}
}