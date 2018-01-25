package rj.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import reactor.core.publisher.Flux;
import rj.model.Quote;
import rj.model.User;
import rj.security.JWTTokenBuilder;
import rj.service.UserService;

@Controller
public class MainController {
	
	private static final String phpGroup = "s2157478";
	
	@Autowired
	private UserService userService;
		

	@RequestMapping("/")
	public String index() {
		return "hello.html";
	}
	
	@RequestMapping("/test")
	public @ResponseBody <T> T test() {
		
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("one", "two");
		
		RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        map.put("quote", quote);
		
		
		return (T) map;
	}
	
	@RequestMapping("/check/token")	
	public @ResponseBody <T> T checkToken(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// check security token here
		String webToken = request.getParameter("web-token");
		
		//Assert.notNull(webToken, "Token is null");
		
		if (webToken == null || webToken.isEmpty()) {			
			throw new Exception("Token is missing");
		}
		
		JWTTokenBuilder generateToken = new JWTTokenBuilder();
		String issuer = generateToken.verifyIssuer(webToken);		
		
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("issuer", issuer);
		
		return (T) map;
	}
	
	// http://localhost:8045/generate/token
	@RequestMapping("/generate/token")	
	public @ResponseBody <T> T generateToken(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JWTTokenBuilder generateToken = new JWTTokenBuilder();		
		String webToken = generateToken.createJWT(phpGroup);
		
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("web-token", webToken);
		
		return (T) map;
	}
	
	
	//@GetMapping(path = "/users")
	@RequestMapping("/users")
	public @ResponseBody Flux<User>  getAllUsers() {
		Map<String, Object> map = new LinkedHashMap<>();
		//map.put("web-token", );
		
		//System.out.println(this.userService.getAllUsers());
		
		return this.userService.getAllUsers();
	}		
	
	@RequestMapping("/users/map")
	public @ResponseBody <T> T  getAllUsersMap() {
		Map<String, Object> map = new LinkedHashMap<>();
		//map.put("web-token", );
		
		//System.out.println(this.userService.getAllUsers());
		
		return (T) this.userService.getAllUsersMap();
	}
}
