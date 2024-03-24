package com.cb.common.utils;

import java.util.Random;

/*
*   生成随机验证码
* */
public class RandomCodeUtils {
    static Random rd = new Random();

    public static String randomNumbers(int length) {

        return randomString("0123456789", length);
    }

    public static String randomString(String baseString, int length) {

            StringBuilder sb = new StringBuilder(length);
            if (length < 1) {
                length = 1;
            }

            int baseLength = baseString.length();

            for(int i = 0; i < length; ++i) {
//                int number = randomInt(baseLength);
                int number = rd.nextInt(baseLength);

                sb.append(baseString.charAt(number));
            }

            return sb.toString();
    }
}
