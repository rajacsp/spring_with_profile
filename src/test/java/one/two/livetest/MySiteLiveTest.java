package one.two.livetest;

import org.junit.Test;
import static org.junit.Assert.*;	

public class MySiteLiveTest {

	@Test
    public void testConcatenate() {
		
		System.out.println("{testConcatenate} Live Test");
		
        String result = concatenate("one", "two");
        assertEquals("onetwo", result);
    }
	
	public String concatenate(String one, String two){
        return one + two;
    }
}
