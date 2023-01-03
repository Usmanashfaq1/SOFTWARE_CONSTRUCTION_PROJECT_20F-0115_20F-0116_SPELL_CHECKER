package projectTestCase;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;

import businessLogicLayer.BusinessLogicLayer;
import dataAccessLayer.DataAccessLayer;

class ProjectTestCases {

	private DataAccessLayer dataBaseLayerObject = new DataAccessLayer();
	private BusinessLogicLayer modelObject = new BusinessLogicLayer();

	@Test
	void test()
	{
		fail("Not terminted yet");
	}

	@Test
	/**
	 * it will return false the because the address does not take to correct file
	 * location and file didn't get read
	 */
	void testCorrectPathExists() {
		Assertions.assertTrue(modelObject.readXmlFile("C:\\Users\\maste\\Documents\\Custom Office Templates"));
	}

	@Test
	/**
	 * it will return false the because null values are not allowed
	 */
	void testFileDataInsertedInDataBase() {
		Assertions.assertTrue(dataBaseLayerObject.insertIntoDataBase("", "", ""));
	}

	@Test
	/**
	 * it will return false the because if word is present its frequency should be 1
	 */
	void testWordInsertedInDataBase() {
		Assertions.assertFalse(dataBaseLayerObject.insertIntoDataBase("ٹیسٹنگ", 0));
	}
	
	
	@Test
	/**
	 * it will return true the because if word is present its deleted
	 */
	void testDelete() {
		Assertions.assertTrue(dataBaseLayerObject.manipulateWord(5));
	}
	
	@Test
	/**
	 * it will return true the because if word is present its updated
	 */
	void testUpdate() {
		Assertions.assertTrue(dataBaseLayerObject.manipulateWord(5,"تازہ کاری"));
	}

}
