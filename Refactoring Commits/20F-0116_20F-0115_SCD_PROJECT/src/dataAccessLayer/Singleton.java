package dataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;

public class Singleton {
	private static Connection connectionObject = null;
	Connection connection = null;

	private Singleton() {
	}

	public static Connection SqlConnection() {
		try {
			if (connectionObject == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connectionObject = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_of_scd", "root", "");
			}

		} catch (Exception e) {

			// throw new Exception("");
		}

		return connectionObject;
	}
}