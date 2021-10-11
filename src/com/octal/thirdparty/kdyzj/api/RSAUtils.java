package com.octal.thirdparty.kdyzj.api;

import java.security.spec.*;
import java.security.*;
import javax.crypto.spec.*;
import javax.crypto.*;

public class RSAUtils
{
    public static KeyPair genKeyPair() throws Exception {
        final KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        return kpg.genKeyPair();
    }
    
    public static PrivateKey restorePrivateKey(final byte[] bytes) throws Exception {
        final PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(bytes);
        final KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(pkcs);
    }
    
    public static PublicKey restorePublicKey(final byte[] bytes) throws Exception {
        final X509EncodedKeySpec pkcs = new X509EncodedKeySpec(bytes);
        final KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(pkcs);
    }
    
    public static byte[] encrypt(final byte[] src, final Key key) throws Exception {
        final Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, key);
        return cipher.doFinal(src);
    }
    
    public static byte[] decrypt(final byte[] dest, final Key key) throws Exception {
        final Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, key);
        return cipher.doFinal(dest);
    }
    
    public static byte[] sign(final byte[] src, final PrivateKey prvKey) throws Exception {
        final Signature sig = Signature.getInstance("MD5withRSA");
        sig.initSign(prvKey);
        sig.update(src);
        return sig.sign();
    }
    
    public static boolean verifySign(final byte[] src, final byte[] dest, final PublicKey pubKey) throws Exception {
        final Signature sig = Signature.getInstance("MD5withRSA");
        sig.initVerify(pubKey);
        sig.update(src);
        return sig.verify(dest);
    }
    
    public static byte[] encryptLarger(final byte[] input, final Key key) throws Exception {
        final SecureRandom random = new SecureRandom();
        final byte[] secretKey = new byte[16];
        random.nextBytes(secretKey);
        final byte[] encryptedSecretKey = encrypt(secretKey, key);
        final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        final SecretKeySpec sks = new SecretKeySpec(secretKey, "AES");
        aes.init(1, sks);
        final byte[] encryptedData = aes.doFinal(input);
        final byte[] result = new byte[encryptedSecretKey.length + encryptedData.length];
        System.arraycopy(encryptedSecretKey, 0, result, 0, encryptedSecretKey.length);
        System.arraycopy(encryptedData, 0, result, encryptedSecretKey.length, encryptedData.length);
        return result;
    }
    
    public static byte[] decryptLarger(final byte[] data, final Key key) throws Exception {
        final byte[] encryptedSecretKey = new byte[128];
        System.arraycopy(data, 0, encryptedSecretKey, 0, encryptedSecretKey.length);
        final byte[] encryptedData = new byte[data.length - encryptedSecretKey.length];
        System.arraycopy(data, encryptedSecretKey.length, encryptedData, 0, encryptedData.length);
        final byte[] secretKey = decrypt(encryptedSecretKey, key);
        final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        final SecretKeySpec sks = new SecretKeySpec(secretKey, "AES");
        aes.init(2, sks);
        return aes.doFinal(encryptedData);
    }
    
    public static byte[] encodeHmacSHA1(final byte[] input, final byte[] key) throws Exception {
        final SecretKey secretKey = new SecretKeySpec(key, "HmacSHA1");
        final Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(input);
    }
    
    public static byte[] initHmacSHA1Key() throws Exception {
        final KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA1");
        final SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }
}
