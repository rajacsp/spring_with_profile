package one.two.integrationtest;

import org.junit.Test;
import static org.junit.Assert.*;	

public class PostIntegrationTest {

	@Test
    public void testConcatenate() {
		System.out.println("{testConcatenate} Integration Test");
		
        String result = concatenate("one", "two");
        assertEquals("onetwo", result);
    }
	
	public String concatenate(String one, String two){
        return one + two;
    }
}
