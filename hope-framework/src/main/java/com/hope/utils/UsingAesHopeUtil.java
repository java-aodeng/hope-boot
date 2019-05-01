package com.hope.utils;

import com.hope.consts.CommonConst;

/**
 * UsingAesHope工具类
 *
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-18 13:49
 **/
public class UsingAesHopeUtil {
    /***
     * 使用AES加密
     * @param password
     * @param salt
     * @return
     * @throws Exception
     */
    public static String encrypt(String password, String salt) throws Exception {
        return AesHopeUtil.encrypt(Md5HopeUtil.MD5Util(CommonConst.ZYD_SECURITY_KEY, salt), password);
    }

    /***
     * 使用AES解密
     * @param encryptPassword
     * @param salt
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptPassword, String salt) throws Exception {
        return AesHopeUtil.decryt(Md5HopeUtil.MD5Util(CommonConst.ZYD_SECURITY_KEY, salt), encryptPassword);
    }

    public static void main(String[] args) {
        try {
            String temp= UsingAesHopeUtil.encrypt("123456","admin");
            System.out.printf(temp);
        }catch (Exception e){
        }

    }
}