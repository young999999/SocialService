package com.py.jjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

public class ParseJjwt {
    public static void main(String[] args) {
        Claims claims = Jwts.parser().setSigningKey("itcast")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTY4NTM1NTQ2MDcxNDg2NDY0Iiwic3ViIjoi5bCP5piOIiwiaWF0IjoxNTY3NDM1NTgxLCJyb2xlcyI6ImFkbWluIiwiZXhwIjoxNTY3NDM5MTgxfQ.xonHnZJXo__3gEp2ceQgfTOtWFQTNmSOc0hzW9GfyRI")
                .getBody();
        System.out.println("ID"+claims.getId());
        System.out.println("用户名"+claims.getSubject());
        System.out.println("登录时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
        System.out.println("过期时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));


    }
}
