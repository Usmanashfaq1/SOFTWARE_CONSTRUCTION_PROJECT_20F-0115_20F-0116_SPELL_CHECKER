package projectTestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dataBaseLayer.DataBaseLayer;
import model.Model;

class ProjectTestCases {

	private DataBaseLayer d = new DataBaseLayer();
	private Model m = new Model();

	@Test
	/**
	 * it will return true because the connection is established correctly
	 */
	void testSqlConnectionExsists() {
		Assertions.assertEquals(true , d.sqlConnection());
	}
	
	@Test
	/**
	 * it will return false the because the address does not take to correct file location and file didn't get read
	 */
	void testCorrectPathExists() {
		Assertions.assertTrue( m.readXmlFile("C:\\Users\\maste\\Documents\\Custom Office Templates"));
	}
	
	@Test
	/**
	 * it will return false the because null values are not allowed 
	 */
	void testFileDataInsertedInDataBase() {
		Assertions.assertTrue(d.insertIntoDataBase("", "", ""));
	}
	
	@Test
	/**
	 * it will return false the because if word is present its frequency should be 1
	 */
	void testWordInsertedInDataBase() {
		Assertions.assertFalse(d.insertWordInDataBase("ٹیسٹنگ",  0));
	}

}
