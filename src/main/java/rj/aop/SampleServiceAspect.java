package rj.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SampleServiceAspect {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(SampleServiceAspect.class);
    
    @Before("execution(* rj.controllers.MainController.test())")
    public void beforeSampleCreation() throws Exception {
        LOGGER.info("trap 1.1");
        
        if(check()){
        	//throw new Exception("test");
        }
    }
    
    private static boolean check(){
    	return true;
    }
}