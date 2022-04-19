package com.example.androidproyecto2;

import java.security.MessageDigest;

public class sha {

    public static byte[] encrytSHA(byte[] data, String shaN)throws Exception{
        MessageDigest sha= MessageDigest.getInstance(shaN);
        sha.update(data);
        return sha.digest();
    }
}
