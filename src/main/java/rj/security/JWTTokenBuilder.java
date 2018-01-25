package rj.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTTokenBuilder {
	
	private static final String secretKey= "RlM2MNUyNzN1a2w1Z05kUUlWTE=e";
	
	//private static final String phpGroup = "s2157478";
	private static final String issuer = "packtpub";
	private static final long phpTokenExpiry = 1000 * 60 * 30; //30 mins

	public String createJWT(String subject, String issuer, long ttlMillis) {
		 
		 SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		 
	    //The JWT signature algorithm we will be using to sign the token
		long nowMillis = System.currentTimeMillis();
   	//System.out.println("{current time  " + nowMillis);
	    Date now = new Date(nowMillis);
	   // System.out.println("{current date  " + now);
	    
	    
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	    
	    JwtBuilder builder = Jwts.builder()
               .setSubject(subject)
               .setIssuer(issuer)
               .signWith(signatureAlgorithm, signingKey);


	    if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		        Date exp = new Date(expMillis);
		        builder.setExpiration(exp);
		    }
   
	    
	    return builder.compact();
	}
	
	public String createJWT(String subject) {
		 
		 SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		 
	    //The JWT signature algorithm we will be using to sign the token
		long nowMillis = System.currentTimeMillis();
  	//System.out.println("{current time  " + nowMillis);
	    Date now = new Date(nowMillis);
	   // System.out.println("{current date  " + now);
	    
	    
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	    
	    JwtBuilder builder = Jwts.builder()
              .setSubject(subject)
              .setIssuer(issuer)
              .signWith(signatureAlgorithm, signingKey);


	    if (phpTokenExpiry >= 0) {
		    long expMillis = nowMillis + phpTokenExpiry;
		        Date exp = new Date(expMillis);
		        builder.setExpiration(exp);
		    }
  
	    
	    return builder.compact();
	}
	
	public String verifyIssuer(String token){
		Claims claims = Jwts.parser()         
			       .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
			       .parseClaimsJws(token).getBody();
		
		String issuer = claims.getIssuer();
		
		return issuer;		
	}
}
