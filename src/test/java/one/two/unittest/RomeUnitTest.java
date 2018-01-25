package one.two.unittest;

import org.junit.Test;
import static org.junit.Assert.*;	

public class RomeUnitTest {

	@Test
    public void testConcatenate() {
		
		System.out.println("{testConcatenate} Unit Test");
		
        String result = concatenate("one", "two");
        assertEquals("onetwo", result);
    }
	
	public String concatenate(String one, String two){
        return one + two;
    }
}
