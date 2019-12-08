package com.lzf.ez4webcast.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 17:56
 */
public final class MD5Utils {

    private static final MessageDigest digest;

    private static final String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8",
            "9", "a", "b", "c", "d", "e", "f"};

    static {
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new Error(e);
        }
    }

    /**
     * 将明文字符串编码成MD5
     * @param str 明文
     * @return 编码后的字符串
     */
    public static String encode(String str) {
        byte[] r = digest.digest(str.getBytes());
        return byteArrayToHexString(r);
    }


    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(byteToHexString(b));
        }
        return sb.toString();
    }


    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }


    private MD5Utils() { }
}
