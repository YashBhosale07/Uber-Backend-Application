package in.yash.UberApplication.Security;

import in.yash.UberApplication.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    private String jwtSecurityKey="91211283ff83c0d5ed693fccb20a072966d300628711bf83b65af2e113e93065e17bbb4107e108aaef3fe93db7f145ddd4491f308b347a4590404ec8510dd04f2f06950cf8ead378503f198066eb2f434bb56aa0f389677cd7edaf5900825b65c12bbf63391ca04ccd3fa59921cd204cf23d7116691c33bf3ee0bd72ee9012aa";
    private SecretKey secretKey(){
        return Keys.hmacShaKeyFor(jwtSecurityKey.getBytes(StandardCharsets.UTF_8));
    }
    public String generateAcessToken(User user){
        return Jwts.builder()
                .claim("email",user.getEmail())
                .claim("roles",user.getRoles())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(secretKey())
                .compact();
    }

    public String generateRefreshToken(User user){
        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24*30*6))
                .signWith(secretKey())
                .compact();
    }

    public Long getUserIdFromToken(String token){
        Claims claims=Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.valueOf(claims.getId());
    }

}
