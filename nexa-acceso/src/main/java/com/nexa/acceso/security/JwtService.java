package com.nexa.acceso.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey key;
    private final long expiration;
    public JwtService(@Value("${app.jwt.secret}") String secret,
                      @Value("${app.jwt.expiration-ms}") long expiration) {
        if(secret.length()<32) throw new IllegalArgumentException("JWT_SECRET debe tener al menos 32 caracteres.");
        this.key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiration=expiration;
    }
    public String generar(Long cuentaId, Long sesionId, String rol) {
        Date ahora=new Date();
        return Jwts.builder().subject(cuentaId.toString())
          .claim("sid",sesionId).claim("rol",rol)
          .issuedAt(ahora).expiration(new Date(ahora.getTime()+expiration))
          .signWith(key).compact();
    }
    public Claims validar(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }
}
