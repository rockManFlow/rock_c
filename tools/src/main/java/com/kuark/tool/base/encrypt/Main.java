package com.kuark.tool.base.encrypt;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by caoqingyuan on 2017/11/3.
 */
public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        ////12345678   25d55ad283aa400af464c76d713c07ad
        String self="ro101010"; //72ad3b05835ea58ba194d2c58dd508b1
        String s = DigestUtils.md5DigestAsHex(self.getBytes());
        System.out.println(s);
    }
}
