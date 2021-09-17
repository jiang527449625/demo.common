package com.demo.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author :jky
 * @Description:
 * @ Date: Created in 2021-04-27 10:11
 */

public class RSAUtil {

    /**
     * 私钥加密，公钥解密   --加密
     *
     * @param obj 加密内容
     * @param key 私钥
     * @return
     */
    public static String encrypt(Object obj, String key) {

        try {
            String encryptStr = JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
            byte[] result1 = encryptStr.getBytes("utf-8");
            byte[] buffer = Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8));
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory instance = KeyFactory.getInstance("RSA");
            RSAPrivateKey privateKey = (RSAPrivateKey) instance.generatePrivate(keySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            int inputLength = result1.length;
            System.out.println("加密字节数：" + inputLength);

            int MAX_ENCRYPT_BLOCK = privateKey.getModulus().bitLength() / 8 - 11;
            // 标识
            int offSet = 0;
            byte[] resultBytes = {};
            byte[] cache = {};
            while (inputLength - offSet > 0) {
                if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(result1, offSet, MAX_ENCRYPT_BLOCK);
                    offSet += MAX_ENCRYPT_BLOCK;
                } else {
                    cache = cipher.doFinal(result1, offSet, inputLength - offSet);
                    offSet = inputLength;
                }
                resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
                System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
            }

            String result11 = Base64.getEncoder().encodeToString(resultBytes);
            System.out.println("私钥加密，公钥解密   --加密: " + result11);
            return result11;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static byte[] stringToBytes(String data) {
        String[] strArr = data.split(" ");
        int len = strArr.length;
        byte[] clone = new byte[len];
        for (int i = 0; i < len; i++) {
            clone[i] = Byte.parseByte(strArr[i]);
        }

        return clone;
    }

    /**
     * 私钥加密，公钥解密   --解密
     *
     * @param encryptStr 加密内容
     * @param key        公钥
     * @return
     */
    public static String decrypt(String encryptStr, String key) {
        //3 私钥加密，公钥解密   --解密
        try {
            byte[] buffer = Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8));
            byte[] result1 = Base64.getDecoder().decode(encryptStr.getBytes());
//            byte[] result1 = org.apache.commons.codec.binary.Base64.decodeBase64(ss);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            int inputLength = result1.length;
            int MAX_ENCRYPT_BLOCK = publicKey.getModulus().bitLength() / 8;
            // 标识
            int offSet = 0;
            byte[] resultBytes = {};
            byte[] cache = {};
            while (inputLength - offSet > 0) {
                if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(result1, offSet, MAX_ENCRYPT_BLOCK);
                    offSet += MAX_ENCRYPT_BLOCK;
                } else {
                    cache = cipher.doFinal(result1, offSet, inputLength - offSet);
                    offSet = inputLength;
                }
                resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
                System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
            }
            System.out.println("私钥加密，公钥解密   --解密: " + javax.xml.bind.DatatypeConverter.printHexBinary(resultBytes));
            System.out.println("私钥加密，公钥解密   --解密: " + new String(resultBytes, "utf-8"));
            return new String(resultBytes, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
