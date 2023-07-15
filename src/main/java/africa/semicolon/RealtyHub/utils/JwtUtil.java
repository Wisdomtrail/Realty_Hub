package africa.semicolon.RealtyHub.utils;

import africa.semicolon.RealtyHub.exceptions.RealtyHubException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

import static africa.semicolon.RealtyHub.utils.AppUtils.CLAIMS_VALUE;

@AllArgsConstructor
@Getter
public class JwtUtil {
    private final String secret;

    public Map<String, Claim> extractClaimsFrom(String token) throws RealtyHubException {
        validateToken(token);
        DecodedJWT decodedJwt = validateToken(token);
        if (decodedJwt.getClaim(CLAIMS_VALUE)==null) throw new RealtyHubException("");
        return decodedJwt.getClaims();
    }

    private DecodedJWT validateToken(String token) {
        return JWT.require(Algorithm.HMAC512(secret))
                .build().verify(token);
    }
    public static String generateToken(int length) {
        byte[] bytes = new byte[length];
        new SecureRandom().nextBytes(bytes);
        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);
    }
}
