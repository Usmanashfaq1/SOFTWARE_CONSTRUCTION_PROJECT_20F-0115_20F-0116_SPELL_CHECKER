package Test_case;



import Controller.Controller;
import Model.Model;

import view.View;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;


class Test {

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
		View v = new View();
		Controller c = new Controller(m, v);
		assertNotNull(c, " ");
	}
	
	@org.junit.jupiter.api.Test
	void checkobj2()
	{
		Model m = new Model();
		View v = new View();
		Controller c = new Controller(m, v);
		assertNull(c, " ");
	}
	
	@org.junit.jupiter.api.Test
	void input_check()
	{
		Model m = new Model();
		View v = new View();
		Controller c = new Controller(m, v);
		assertEquals(v.getWord_input(),v.word_input );
	}
	

}
