package projectTestCase;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.text.View;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import businessLogicLayer.Model;
import controller.Controller;
import presentationLayer.MainScreen;
import presentationLayer.ViewTypos;

class ProjectTestCaseF200115 {

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
	void checkobj()
	{
		Model m = new Model();
		MainScreen v = new MainScreen();
		Controller c = new Controller(m, v);
		assertNotNull(c, " ");
	}
	
	@org.junit.jupiter.api.Test
	void checkobj2()
	{
		Model m = new Model();
		MainScreen v = new MainScreen();
		Controller c = new Controller(m, v);
		assertNull(c, " ");
	}
	
	@org.junit.jupiter.api.Test
	void input_check()
	{
		ViewTypos v = new ViewTypos();
		assertEquals(v.getWord_input(),v.inputWord );
	}
	
}
