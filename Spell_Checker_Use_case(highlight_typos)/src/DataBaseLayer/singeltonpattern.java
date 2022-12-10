package DataBaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;

public class singeltonpattern {
	private static Connection conObject = null;
	 Connection connection=null;
	private singeltonpattern() {
	}

	public static Connection SqlConnection() {
		try {
			if (conObject == null) {
				Class.forName("com.mysql.jdbc.Driver");  
				conObject=DriverManager.getConnection("jdbc:mysql://localhost:3306/spell_checker","root","");
			}

		} catch (Exception e) {
			
			// throw new Exception("");
		}

		return conObject;
	}
}