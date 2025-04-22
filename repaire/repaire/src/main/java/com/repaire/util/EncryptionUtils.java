package com.repaire.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtils {

    private static final String ALGORITHM = "AES";  //加密算法的名称
    private static final String SECRET_KEY = "1234567890123456"; // 密钥长度必须是16的倍数

    //加密方法(持久化的时候使用 注册)
    public static String encrypt(String data) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    //解密方法(登录的时候)
    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }

//    public static void main(String[] args) {
//        try {
//            String originalData = "123456";
//            String encryptedData = encrypt(originalData);
//            String decryptedData = decrypt(encryptedData);
//
//            System.out.println("Original Data: " + originalData);
//            System.out.println("Encrypted Data: " + encryptedData);
//            System.out.println("Decrypted Data: " + decryptedData);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
