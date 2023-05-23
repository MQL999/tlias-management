package com.minqiliang;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    void testUUID() {
        for (int i = 0; i <1000 ; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

    @Test
    void testGenJWT() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id","1907381125");
        claims.put("name","minqiliang");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "minqiliang")// 签名算法
                .setClaims(claims) // 载荷部分
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置有效期为一个小时
                .compact();
        System.out.println(jwt);
    }


    @Test
    void testParseJWT() {
        Claims claims = Jwts.parser()
                .setSigningKey("minqiliang")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoibWlucWlsaWFuZyIsImlkIjoiMTkwNzM4MTEyNSIsImV4cCI6MTY4NDQ4NDg5OH0.sC5Tlz1rwnAAbxXGS-j3Cl8vJf_we7KA7Cuz5YzhJEI")
                .getBody();
        System.out.println(claims);
    }


}
