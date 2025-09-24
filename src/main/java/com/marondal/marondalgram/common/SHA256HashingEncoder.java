package com.marondal.marondalgram.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256HashingEncoder {

    public static String encode(String message) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("sha256");

            byte[] bytes = message.getBytes();

            messageDigest.update(bytes);

            byte[] digest = messageDigest.digest();

            String result = "";
            for(int i = 0; i < digest.length; i++) {
                result += Integer.toHexString(digest[i] & 0xff);
            }

            return result;

        } catch (NoSuchAlgorithmException e) {

            return null;
        }
    }
}
