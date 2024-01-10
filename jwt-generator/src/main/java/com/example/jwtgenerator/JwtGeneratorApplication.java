package com.example.jwtgenerator;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootApplication
public class JwtGeneratorApplication {

	private static final String SECRET_KEY = "T+p-?N$Q$HzRURAwtxa+4?sy";

	public static void main(String[] args) {
		String token = createToken("adnane", new ArrayList<>());
		System.out.println(token);
		validateToken(token);
	}

	public static String createToken(String username, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(username).setIssuer("CHAABINETSERVICES");

		Date now = new Date();

		Date validity = new Date(now.getTime() + 6000000);

		return Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}

	private static boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			log.info("Invalid JWT signature.");
			log.trace("Invalid JWT signature trace: {}", e);
		} catch (MalformedJwtException e) {
			log.info("Invalid JWT token.");
			log.trace("Invalid JWT token trace: {}", e);
		} catch (ExpiredJwtException e) {
			log.info("Expired JWT token.");
			log.trace("Expired JWT token trace: {}", e);
		} catch (UnsupportedJwtException e) {
			log.info("Unsupported JWT token.");
			log.trace("Unsupported JWT token trace: {}", e);
		} catch (IllegalArgumentException e) {
			log.info("JWT token compact of handler are invalid.");
			log.trace("JWT token compact of handler are invalid trace: {}", e);
		}
		return false;
	}

}