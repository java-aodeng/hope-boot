package com.hope.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AesHope工具类
 *
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-18 11:34
 **/
public class AesHopeUtil {
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /***
     * AES加密
     * @param password
     * @param content
     * @return
     * @throws Exception
     */
    public static String encrypt(String password, String content) throws Exception {
        //创建密码器
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        byte[] bytes = content.getBytes("utf-8");
        //初始化密码器
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKeySpec(password));
        //加密
        byte[] bytes1 = cipher.doFinal(bytes);
        //通过BASE64转码返回
        return Base64.encodeBase64String(bytes1);
    }

    /***
     * AES解密
     * @param password
     * @param encryted
     * @return
     * @throws Exception
     */
    public static String decryt(String password, String encryted) throws Exception {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        //使用密钥初始化解密
        cipher.init(Cipher.DECRYPT_MODE, getSecretKeySpec(password));
        byte[] bytes = cipher.doFinal(Base64.decodeBase64(encryted));
        return new String(bytes, "utf-8");
    }

    /***
     * 生成加密密钥
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static SecretKeySpec getSecretKeySpec(final String password) throws NoSuchAlgorithmException {
        //返回密钥生成器对象
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());
        //设置AES密钥长度
        keyGenerator.init(128, secureRandom);
        //生成一个密钥
        SecretKey secretKey = keyGenerator.generateKey();
        //转换为AES密钥
        return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
    }
}