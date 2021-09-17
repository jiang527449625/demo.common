package com.demo.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author :jky
 * @Description:
 * @ Date: Created in 2021-04-27 14:18
 */
public class Sha1Util {

    public static String getSha1(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "123account=12345678900apiKey=testcity=cstmOrderNo=134134134123deliveryAddress=deliveryArea=deliveryZip=phoneNumber=12345678900productCode=abc123provice=receiveUserName=receiveUserTel=returl=http://aa.bb.comtimeStamp=1577328846000123";
        try {
            System.out.println("06D834E813374F6AD7A528AF63EF73154D116C88".equals(getSha1(s.getBytes()).toUpperCase()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
