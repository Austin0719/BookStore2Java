package com.example.bookStore.util;

import java.security.SecureRandom;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class TokenUtility {
	private static final long TOKEN_= 30 * 60 * 1000; // 30 minutes
	private static final SecretKey KEY = Keys.hmacShaKeyFor(generateRandomBytes(64));

	private static byte[] generateRandomBytes(int length) {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[length];
		random.nextBytes(bytes);
		return bytes;
	}

	public String createEncryptedToken(String data) {
		Date now = new Date();

		//設定30min過期
		Date expireDate =  new Date(System.currentTimeMillis()+ 30 * 60 * 1000);
		

		JwtBuilder builder = Jwts.builder().subject(data) // JWT內容主體
				.issuedAt(now) // 建立時間
				.expiration(expireDate) // 過期時限
				.signWith(KEY, Jwts.SIG.HS512);
		String token = builder.compact(); // 將所有之前設置的 JWT 訊息（如 subject、發行時間、過期時間等）編碼並壓縮成一個單一的字符串。
		return token;
	}
	
	 public boolean validateToken(String token) {
	        try {
	            Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }

}
