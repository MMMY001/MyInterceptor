package com.example.singledemo.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;


import com.alibaba.druid.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    // 密匙
    public static final String SECRET = "MYsecret";

    // 过期时间 10天
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 10;


    /**
     * 生成token
     * JWT构成：header，payload，signature
     *
     * @param uname
     * @return
     * @throws Exception
     */
    public static String createToken(String uname,Long time) throws Exception {
        Date iatDate = new Date();

        // 过期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        // header map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups{iss:Service,aud:app}
        String token = JWT.create().withHeader(map) // header
                .withClaim("iss", "Service") // payload
                .withClaim("aud", "APP").withClaim("uname", uname).withClaim("time",time)
                .withIssuedAt(iatDate) // sign time
                .withExpiresAt(expiresDate) // expire time
                .sign(Algorithm.HMAC256(SECRET)); // signature
        return token;
    }

    /**
     * 解密Token
     *
     * @param token
     * @return
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (UnsupportedEncodingException e) {
            // token校验失败，抛出异常
            e.printStackTrace();
        }
        return jwt.getClaims();
    }

    /**
     *
     * @param token
     * @return
     */
    public static Long getAppUID(String token) {
        Map<String, Claim> claims = verifyToken(token);
        Claim uname_claim = claims.get("uname");
        if (null == uname_claim || StringUtils.isEmpty(uname_claim.asString())) {
            // token 校验失败，抛出token验证非法异常
        }
        return Long.valueOf(uname_claim.asString());
    }


}
