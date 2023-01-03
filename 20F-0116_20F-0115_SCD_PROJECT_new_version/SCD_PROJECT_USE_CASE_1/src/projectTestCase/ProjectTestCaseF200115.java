package projectTestCase;

import static org.junit.jupiter.api.Assertions.*;
import dataAccessLayer.DataAccessLayer;
import javax.swing.text.View;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import businessLogicLayer.BusinessLogicLayer;
//import controller.Controller;
import presentationLayer.*;
import presentationLayer.MainScreen;
import presentationLayer.ViewTypos;

class ProjectTestCaseF200115 {

	private DataAccessLayer dataBaseLayerObject = new DataAccessLayer();

	@AfterEach
	void tearDown() throws Exception
	{
	}

	@org.junit.jupiter.api.Test
	void test()
	{
		fail("Not yet implemented");
		
		
	}
	
	@org.junit.jupiter.api.Test
	void checkObj()
	{
		BusinessLogicLayer model = new BusinessLogicLayer();
		MainScreen viewScreen = new MainScreen();
		Controller controller = new Controller(model, viewScreen);
		assertNotNull(controller, " ");
	}
	
	@org.junit.jupiter.api.Test
	void checkObjTwo()
	{
		BusinessLogicLayer model = new BusinessLogicLayer();
		MainScreen viewScreen = new MainScreen();
		Controller controller = new Controller(model, viewScreen);
		assertNull(controller, " ");
	}
	
	@org.junit.jupiter.api.Test
	void inputCheck()
	{
		ViewTypos viewTyos = new ViewTypos();
		assertEquals(viewTyos.getWordInput(),viewTyos.inputWord );
	}
	
	@org.junit.jupiter.api.Test
	void textFileCheck()
	{
		Assertions.assertTrue(dataBaseLayerObject.textFileGeneration());
	}
	
}
