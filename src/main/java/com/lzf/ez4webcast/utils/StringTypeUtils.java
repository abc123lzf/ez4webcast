package com.lzf.ez4webcast.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 17:32
 */
public final class StringTypeUtils {

    private static final Pattern NUMBER = Pattern.compile("\\d+");
    private static final Pattern EMAIL = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

    public static boolean isNumber(String str) {
        return NUMBER.matcher(str).matches();
    }

    public static boolean isEmail(String str) {
        return EMAIL.matcher(str).matches();
    }

    private StringTypeUtils() { }

}
