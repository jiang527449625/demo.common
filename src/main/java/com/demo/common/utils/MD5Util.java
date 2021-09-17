package com.demo.common.utils;

import org.apache.shiro.codec.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    /*
     * base64加密
     * */
    public static String encodeBase64(String str) {
        return Base64.encodeToString(str.getBytes());
    }

    /*
     * base64解密
     * */

    public static String decodeBase64(String str) {
        return Base64.decodeToString(str);
    }

	 /** 
     * 获取该输入流的MD5值 
     *  
     * @param is 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws IOException 
     */  
    public static String getMD5(InputStream is) throws NoSuchAlgorithmException, IOException {  
        StringBuffer md5 = new StringBuffer();  
        MessageDigest md = MessageDigest.getInstance("MD5");  
        byte[] dataBytes = new byte[1024];  
          
        int nread = 0;   
        while ((nread = is.read(dataBytes)) != -1) {  
            md.update(dataBytes, 0, nread);  
        };  
        byte[] mdbytes = md.digest();  
          
        // convert the byte to hex format  
        for (int i = 0; i < mdbytes.length; i++) {  
            md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));  
        }  
        return md5.toString();  
    }  
      
    /** 
     * 获取该文件的MD5值 
     *  
     * @param file 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws IOException 
     */  
    public static String getMD5(File file) throws NoSuchAlgorithmException, IOException {  
        FileInputStream fis = new FileInputStream(file);  
        return getMD5(fis);  
    }  
      
    /** 
     * 获取指定路径文件的MD5值 
     *  
     * @param path 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws IOException 
     */  
    public static String getMD5(String path) throws NoSuchAlgorithmException, IOException {  
        FileInputStream fis = new FileInputStream(path);  
        return getMD5(fis);  
    }  
  
    /** 
     * 校验该输入流的MD5值 
     *  
     * @param is 
     * @param toBeCheckSum 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws IOException 
     */  
    public static boolean md5CheckSum(InputStream is, String toBeCheckSum) throws NoSuchAlgorithmException, IOException {  
        return getMD5(is).equals(toBeCheckSum);  
    }  
      
    /** 
     * 校验该文件的MD5值 
     *  
     * @param file 
     * @param toBeCheckSum 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws IOException 
     */  
    public static boolean md5CheckSum(File file, String toBeCheckSum) throws NoSuchAlgorithmException, IOException {  
        return getMD5(file).equals(toBeCheckSum);  
    }  
      
    /** 
     * 校验指定路径文件的MD5值 
     *  
     * @param path 
     * @param toBeCheckSum 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws IOException 
     */  
    public static boolean md5CheckSum(String path, String toBeCheckSum) throws NoSuchAlgorithmException, IOException {  
        return getMD5(path).equals(toBeCheckSum);  
    }  
      
    /* MD5 TEST 
    public static void main(String[] args) { 
        try { 
            System.out.println(getMD5("D:/work/BJ/ESB_CSS_CSS_ImportCustCompFormRejectInfoSrv.xml")); 
        } catch (NoSuchAlgorithmException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 
    */  
    public final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}  

