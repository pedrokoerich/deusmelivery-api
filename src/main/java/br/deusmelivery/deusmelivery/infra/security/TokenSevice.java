package br.deusmelivery.deusmelivery.infra.security;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.deusmelivery.deusmelivery.users.entity.Users;

@Service
public class TokenSevice {
    @Value("${token.secretKey}")
    private String secretKey = "secretKey";

    public String generateToken(Users users)
	{
		try
		{
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			return JWT.create().withIssuer("login-deusmelivery-api").withSubject(users.getLogin())
				.withExpiresAt(generateExpirationDate()).sign(algorithm);
		}
		catch (JWTCreationException ex)
		{
			throw new RuntimeException("Erro ao gerar token");
		}
	}

	private Instant generateExpirationDate() {
        return Instant.now().plusSeconds(86400);
    }

    public String validateToken(String token)
	{
		try{
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			return JWT.require(algorithm).withIssuer("login-deusmelivery-api").build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }

}

}


