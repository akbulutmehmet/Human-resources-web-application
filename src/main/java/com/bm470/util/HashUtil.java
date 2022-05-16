package com.bm470.util;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    private String message;
    private String hashMessage;

    public String getHashMessage() {
        return hashMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setHashMessage(String hashMessage) {
        this.hashMessage = hashMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashUtil (String message) {
        this.message = message;

    }
    public String md5 () {
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte [] messageByteArray = messageDigest.digest(message.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0;i<messageByteArray.length;i++) {
                stringBuilder.append(Integer.toString((messageByteArray[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashMessage = stringBuilder.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashMessage;
    }
}
