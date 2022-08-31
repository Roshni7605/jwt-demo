package com.springpractice.jwt.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

@Component
public class TokenUtility {

    private static final String TOKEN_SECRET = "Roshni";

    /**
     * To create a token
     * @param id
     * @return
     */
    public String createToken(Long id){
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        String token = JWT.create().withClaim("user_Id", id).sign(algorithm);
        return token;
    }

    /**
     * To decode a token
     * @param token
     * @return
     */
    public Long decodeToken(String token){
        Long user_Id;
        Verification verification = null;
        verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        JWTVerifier jwtverifier=verification.build();
        DecodedJWT decodedjwt=jwtverifier.verify(token);
        Claim claim=decodedjwt.getClaim("user_Id");
        user_Id=claim.asLong();
        return user_Id;
    }

}
