package com.jzsf.tuitor.common.token;

import com.jzsf.tuitor.common.exception.CustomException;
import com.jzsf.tuitor.rpcDomain.ResultCode;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author by plain yuan
 * @since 2020/04/13
 * JWT工具类
 */
public class JwtTokenUtil {

    private static Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    public static final String AUTH_HEADER_KEY = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 解析jwt
     *
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken)
                    .getBody();
            return claims;
        } catch (ExpiredJwtException eje) {
            log.error("===== Token过期 =====", eje);
            throw new CustomException(ResultCode.PERMISSION_TOKEN_EXPIRED);
        } catch (Exception e) {
            log.error("===== Token解析异常 =====", e);
            throw new CustomException(ResultCode.PERMISSION_TOKEN_INVALID);
        }
    }

    /**
     * 构建jwt
     *
     * @param userId
     * @param username
     * @param audience
     * @return
     */
    public static String createJWT(String userId, String username, Audience audience) {
        try {
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            //生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audience.getBase64Secret());
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            //添加构成JWT的参数
            JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                    .claim("userId", userId)
                    // 代表这个JWT的主体，即它的所有人
                    .setSubject(username)
                    // 代表这个JWT的签发主体；
                    .setIssuer(audience.getClientId())
                    // 是一个时间戳，代表这个JWT的签发时间；
                    .setIssuedAt(new Date())
                    // 代表这个JWT的接收对象；
                    .setAudience(audience.getName())
                    .signWith(signatureAlgorithm, signingKey);
            //添加Token过期时间
            int TTLMillis = audience.getExpiresSecond();
            if (TTLMillis >= 0) {
                long expMillis = nowMillis + TTLMillis;
                Date exp = new Date(expMillis);
                // 时间戳：此token的过期时间；
                builder.setExpiration(exp)
                        // 是一个时间戳：此token生效的开始时间
                        .setNotBefore(now);
            }

            //生成JWT
            return builder.compact();
        } catch (Exception e) {
            log.error("签名失败", e);
            throw new CustomException(ResultCode.PERMISSION_SIGNATURE_ERROR);
        }
    }

    /**
     * 从token中获取用户名
     *
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUsername(String token, String base64Security) {
        return parseJWT(token, base64Security).getSubject();
    }

    /**
     * 从token中获取用户ID
     *
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUserId(String token, String base64Security) {
        return parseJWT(token, base64Security).get("userId", String.class);
    }

    /**
     * 是否已过期
     *
     * @param token
     * @param base64Security
     * @return
     */
    public static boolean isExpiration(String token, String base64Security) {
        return parseJWT(token, base64Security).getExpiration().before(new Date());
    }

    /**
     * 返回token
     */
    public static String getToken(String reqHeaderValue) {
        return StringUtils.isNotBlank(reqHeaderValue) ? reqHeaderValue.substring(7) : reqHeaderValue;
    }

    /**
     * 设置立即过期
     */
    public static void setExpiration(String token, String base64Security) {
        parseJWT(token, base64Security).setExpiration(new Date(System.currentTimeMillis()));
    }

    //todo token的刷新

}
