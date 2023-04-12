package com.ggy.user;

import com.ggy.fegin.ResourceClient;
import com.ggy.user.util.JwtUtil;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
class UserServiceApplicationTests {
//    @Autowired
//    private ResourceClient resourceClient;

    @Test
    void contextLoads() {
    }
//    @Test
//    public void test1(){
//        long i=1;
////        System.out.println(resourceClient.index());
//        System.out.println(resourceClient.findResourceById(i));
//    }
    //创建
    @Test
    public void createToken(){
        String login = JwtUtil.createJWT("1", "login", null);
        System.out.println(login);
        //eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiLllK_kuIDnmoTooajnpLoiLCJzdWIiOiLkuLvpopgiLCJpc3MiOiJ5eGlubWlyYWNsZSIsImlhdCI6MTYyOTAyMzc1MH0.cuOCbsdQpP9zuLadT-4F_kgA2Wpu6qJk72TDQKDFt7U
    }

    //解析
    @Test
    public void parseToken() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoibG9naW4iLCJpc3MiOiJhZG1pbiIsImlhdCI6MTY4MTI2OTY4NywiZXhwIjoxNjgxMjczMjg3fQ.ZUXYVE3Pj3uLLnDzaWqyVvwyLeKLCd3mvB4BhZlHYwE";
//        // 解析
//        JwtParser parser = Jwts.parser();
        // 设置签名的秘钥
        Claims claims = JwtUtil.parseJWT(token);
//        UUID.randomUUID()

        System.out.println(claims); // {jti=唯一的表示, sub=主题, iss=yxinmiracle, iat=1629023750}
//        System.out.println(header); // {alg=HS256}
    }

}
